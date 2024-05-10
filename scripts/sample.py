#!/usr/bin/python3
import argparse
import random
import sqlite3

import tqdm.autonotebook as tqdm

CREATE_TABLES = """
DROP TABLE IF EXISTS nfts;
CREATE TABLE nfts
    (
        address TEXT NOT NULL UNIQUE ON CONFLICT FAIL,
        name TEXT,
        symbol TEXT,
        UNIQUE(address, name, symbol)
    );

DROP TABLE IF EXISTS checkpoint;
CREATE TABLE checkpoint
    (
        event_type STRING,
        offset INTEGER
    );

DROP TABLE IF EXISTS mints;
CREATE TABLE mints
    (
        event_id TEXT NOT NULL UNIQUE ON CONFLICT FAIL,
        transaction_hash TEXT,
        block_number INTEGER,
        nft_address TEXT REFERENCES nfts(address),
        token_id TEXT,
        from_address TEXT,
        to_address TEXT,
        transaction_value INTEGER,
        timestamp INTEGER
    );

DROP TABLE IF EXISTS transfers;
CREATE TABLE transfers
    (
        event_id TEXT NOT NULL UNIQUE ON CONFLICT FAIL,
        transaction_hash TEXT,
        block_number INTEGER,
        nft_address TEXT REFERENCES nfts(address),
        token_id TEXT,
        from_address TEXT,
        to_address TEXT,
        transaction_value INTEGER,
        timestamp INTEGER
    );

DROP TABLE IF EXISTS transfer_values_quartile_10_distribution_per_address;
CREATE TABLE transfer_values_quartile_10_distribution_per_address(
  address TEXT,
  quartiles TEXT,
  relative_value
);

DROP TABLE IF EXISTS current_owners;
CREATE TABLE current_owners(
  nft_address TEXT,
  token_id TEXT,
  owner
);

DROP TABLE IF EXISTS current_market_values;
CREATE TABLE current_market_values(
  nft_address TEXT,
  token_id TEXT,
  market_value
);

DROP TABLE IF EXISTS market_values_distribution;
CREATE TABLE market_values_distribution(
  address TEXT,
  token_id TEXT,
  relative_value
);

DROP TABLE IF EXISTS transfer_statistics_by_address;
CREATE TABLE transfer_statistics_by_address(
  address TEXT,
  transfers_out,
  transfers_in
);

DROP TABLE IF EXISTS transfer_values_quantile_10_distribution_per_address;
CREATE TABLE transfer_values_quantile_10_distribution_per_address(
  address TEXT,
  quantiles TEXT,
  relative_value
);

DROP TABLE IF EXISTS transfer_values_quantile_25_distribution_per_address;
CREATE TABLE transfer_values_quantile_25_distribution_per_address(
  address TEXT,
  quantiles TEXT,
  relative_value
);

DROP TABLE IF EXISTS transfers_mints;
CREATE TABLE transfers_mints(transfer_id TEXT,mint_id TEXT);

DROP TABLE IF EXISTS mint_holding_times;
CREATE TABLE mint_holding_times(days,num_holds);

DROP TABLE IF EXISTS transfer_holding_times;
CREATE TABLE transfer_holding_times(
  days,
  num_holds
);

DROP TABLE IF EXISTS ownership_transitions;
CREATE TABLE ownership_transitions(
  from_address,
  to_address,
  num_transitions
);
"""


def parse_args():
    """Parses command line arguments.

    Returns:
        argparse.Namespace: Namespace with parsed arguments.
    """
    parser = argparse.ArgumentParser()
    parser.add_argument('input_db')
    parser.add_argument('output_db')
    parser.add_argument('--seed', default=42, type=int)
    parser.add_argument('--batch-size',
                        dest='batch_size',
                        type=int,
                        default=10000)
    return parser.parse_args()


def batchify(iterable, batch_size):
    """Batches an iterable.

    Args:
        iterable(Iterable[T]): Iterable to batchify.
        batch_size (int): Size of returned batches.

    Yields:
        list[T]: Batch of size less than batch_size.
    """
    batch = []
    for item in iterable:
        batch.append(item)
        if len(batch) >= batch_size:
            yield batch
    if batch:
        yield batch


SKIP_TABLES = [
    'transfer_values_quartile_10_distribution_per_address',
    'transfer_values_quantile_10_distribution_per_address',
    'transfer_values_quantile_25_distribution_per_address',
]


def main():
    """Entry point of the program.
    """
    args = parse_args()
    input_sqlite = sqlite3.connect(args.input_db)
    input_cursor = input_sqlite.cursor()
    output_sqlite = sqlite3.connect(args.output_db)
    output_cursor = output_sqlite.cursor()
    input_cursor.execute('DROP TABLE IF EXISTS sampled_tokens;')
    input_cursor.execute("SELECT name FROM sqlite_master WHERE type='table';")
    output_cursor.executescript(CREATE_TABLES)
    table_names = [name[0] for name in input_cursor.fetchall()]
    token_ids = []
    input_cursor.execute('SELECT DISTINCT token_id FROM mints;')
    with tqdm.tqdm(desc='Fetching token IDs') as pbar:
        while True:
            row = input_cursor.fetchone()
            if not row:
                break
            token_id, = row
            token_ids.append(token_id)
            pbar.update(1)
    token_ids.sort()
    random.seed(args.seed)
    sampled_token_ids = set(random.sample(token_ids, 100000))
    input_cursor.execute('CREATE TABLE sampled_tokens (token_id TEXT);')
    for token_id in tqdm.tqdm(sampled_token_ids,
                              desc='Inserting sampled tokens into a table'):
        input_cursor.execute(
            f'INSERT INTO sampled_tokens (token_id) VALUES ({token_id})')
    for table_name in table_names:
        if table_name in SKIP_TABLES:
            continue
        input_cursor.execute(f"PRAGMA table_info({table_name});")
        column_names = [column[1] for column in input_cursor.fetchall()]
        col_names = ", ".join(column_names)
        if 'token_id' not in column_names:
            n_rows, = input_cursor.execute(
                f'select count(*) from {table_name};').fetchone()
            input_cursor.execute(f"SELECT * FROM {table_name};")
        else:
            n_rows, = input_cursor.execute(f"""\
                SELECT count(*)
                FROM {table_name} NATURAL JOIN sampled_tokens
            """).fetchone()
            input_cursor.execute(f"""\
                SELECT *
                FROM {table_name} NATURAL JOIN sampled_tokens
            """)
        with tqdm.tqdm(
                total=n_rows,
                unit='rows',
                desc=f'Importing {table_name}',
        ) as pbar:
            while True:
                row = input_cursor.fetchone()
                if not row:
                    break
                insert_query = f"INSERT INTO {table_name} ({col_names}) VALUES ({','.join('?' for _ in row)});"
                output_cursor.execute(insert_query, row)
                pbar.update(1)
    input_cursor.execute('DROP TABLE IF EXISTS sampled_tokens;')
    input_cursor.close()
    input_sqlite.close()
    output_cursor.execute("COMMIT;")
    output_cursor.close()
    output_sqlite.close()


if __name__ == '__main__':
    main()

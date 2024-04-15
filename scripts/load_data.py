#!/usr/bin/python3
# TODO split into separate scripts
import argparse
import sqlite3

import psycopg2
import tqdm.autonotebook as tqdm

CREATE_TABLES = '''
DROP TABLE IF EXISTS nfts CASCADE;
CREATE TABLE nfts
    (
        address TEXT NOT NULL UNIQUE,
        name TEXT,
        symbol TEXT,
        UNIQUE(address, name, symbol)
    );
DROP TABLE IF EXISTS checkpoint;
CREATE TABLE checkpoint(
    event_type TEXT,
    offset_ INT
);
DROP TABLE IF EXISTS transfer_values_quartile_10_distribution_per_address;
CREATE TABLE transfer_values_quartile_10_distribution_per_address(
  address TEXT,
  quartiles TEXT,
  relative_value REAL
);
DROP TABLE IF EXISTS current_owners;
CREATE TABLE current_owners(
  nft_address TEXT,
  token_id TEXT,
  owner TEXT
);
DROP TABLE IF EXISTS current_market_values;
CREATE TABLE current_market_values(
  nft_address TEXT,
  token_id TEXT,
  market_value REAL
);
DROP TABLE IF EXISTS market_values_distribution;
CREATE TABLE market_values_distribution(
  address TEXT,
  token_id TEXT,
  relative_value REAL
);
DROP TABLE IF EXISTS transfer_statistics_by_address;
CREATE TABLE transfer_statistics_by_address(
  address TEXT,
  transfers_out INT,
  transfers_in INT
);
DROP TABLE IF EXISTS transfer_values_quantile_10_distribution_per_address;
CREATE TABLE transfer_values_quantile_10_distribution_per_address(
  address TEXT,
  quantiles TEXT,
  relative_value REAL
);
DROP TABLE IF EXISTS transfer_values_quantile_25_distribution_per_address;
CREATE TABLE transfer_values_quantile_25_distribution_per_address(
  address TEXT,
  quantiles TEXT,
  relative_value REAL
);
DROP TABLE IF EXISTS transfers_mints;
CREATE TABLE transfers_mints(
    transfer_id TEXT,
    mint_id TEXT
);
DROP TABLE IF EXISTS mint_holding_times;
CREATE TABLE mint_holding_times(
    days INT,
    num_holds INT
);
DROP TABLE IF EXISTS transfer_holding_times;
CREATE TABLE transfer_holding_times(
  days INT,
  num_holds INT
);
DROP TABLE IF EXISTS ownership_transitions;
CREATE TABLE ownership_transitions(
  from_address TEXT,
  to_address TEXT,
  num_transitions INT
);
DROP TABLE IF EXISTS mints;
CREATE TABLE IF NOT EXISTS "mints"
    (
        event_id TEXT NOT NULL UNIQUE,
        transaction_hash TEXT,
        block_number INTEGER,
        nft_address TEXT REFERENCES nfts(address),
        token_id TEXT,
        from_address TEXT,
        to_address TEXT,
        transaction_value REAL,
        timestamp INTEGER
    );
DROP TABLE IF EXISTS transfers;
CREATE TABLE IF NOT EXISTS "transfers"
    (
        event_id TEXT NOT NULL UNIQUE,
        transaction_hash TEXT,
        block_number INTEGER,
        nft_address TEXT REFERENCES nfts(address),
        token_id TEXT,
        from_address TEXT,
        to_address TEXT,
        transaction_value REAL,
        timestamp INTEGER
    );
'''


def parse_args():
    """Parses arguments from the command line.

    Returns:
        Namespace: Namespace with the parsed arguments.
    """
    parser = argparse.ArgumentParser()
    parser.add_argument('sqlite_file')
    parser.add_argument('--user')
    parser.add_argument('--host')
    parser.add_argument('--port', type=int)
    parser.add_argument('--dbname')
    parser.add_argument('--password')
    parser.add_argument('--batch_size',
                        metavar='batch-size',
                        type=int,
                        default=100)
    return parser.parse_args()


def main():
    """Entry point of the program.
    """
    args = parse_args()
    conn_sqlite = sqlite3.connect(args.sqlite_file)
    conn_postgres = psycopg2.connect(
        host=args.host,
        port=args.port,
        user=args.user,
        dbname=args.dbname,
        password=args.password,
    )
    cursor_sqlite = conn_sqlite.cursor()
    cursor_postgres = conn_postgres.cursor()
    cursor_postgres.execute(CREATE_TABLES)
    cursor_sqlite.execute("SELECT name FROM sqlite_master WHERE type='table';")
    table_names = [name[0] for name in cursor_sqlite.fetchall()]
    for table_name in table_names:
        if table_name == 'checkpoint':
            continue
        cursor_sqlite.execute(f"PRAGMA table_info({table_name});")
        column_names = [column[1] for column in cursor_sqlite.fetchall()]
        n_rows, = cursor_sqlite.execute(
            f'select count(*) from {table_name};').fetchone()
        col_names = ", ".join(column_names)
        cursor_sqlite.execute(f"SELECT * FROM {table_name};")
        with tqdm.tqdm(
                total=n_rows,
                unit='rows',
                desc=f'Importing {table_name}',
        ) as pbar:
            while True:
                rows = cursor_sqlite.fetchmany(args.batch_size)
                if not rows:
                    break
                records_list_template = ','.join(['%s'] * len(rows))
                insert_query = (f"INSERT INTO {table_name} ({col_names})"
                                f" VALUES {records_list_template};")
                cursor_postgres.execute(insert_query, rows)
                pbar.update(n=len(rows))
    conn_postgres.commit()
    cursor_postgres.close()
    conn_postgres.close()
    cursor_sqlite.close()
    conn_sqlite.close()


if __name__ == '__main__':
    main()

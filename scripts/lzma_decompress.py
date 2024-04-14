#!/usr/bin/python3
import argparse
import lzma
import pathlib

import tqdm


def existing_file(path):
    path = pathlib.Path(path)
    if not path.exists():
        raise FileNotFoundError(f'File {path} does not exist')
    if not path.is_file():
        raise FileNotFoundError(f'File {path} is a directory')
    return path


def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('input_file', type=existing_file, metavar='input-file')
    parser.add_argument(
        '-o',
        '--output-file',
        default=None,
    )
    parser.add_argument('--quiet', action='store_true')
    parser.add_argument('--chunk_bytes', type=int, default=1024)
    return parser.parse_args()


def decompress_lzma(input_file, output_file, chunk_bytes=1024, quiet=False):
    with (
            lzma.open(input_file) as fin,
            open(output_file, 'wb') as fout,
            tqdm.tqdm(desc='Decompressing',
                      unit='B',
                      unit_divisor=1024,
                      unit_scale=True,
                      disable=quiet) as pbar,
    ):

        def chunk_file(file, chunk_bytes):
            while True:
                data = file.read(chunk_bytes)
                if not data:
                    return
                yield data

        for chunk in chunk_file(fin, chunk_bytes):
            fout.write(chunk)
            pbar.update(chunk_bytes)


def main():
    args = parse_args()
    if args.output_file is None:
        if pathlib.Path(args.input_file).suffix != '.lzma':
            raise RuntimeError("Couldn't infer the decompressed file name.")
        args.output_file = pathlib.Path(args.input_file).with_suffix('')
    decompress_lzma(
        args.input_file,
        args.output_file,
        chunk_bytes=args.chunk_bytes,
        quiet=args.quiet,
    )


if __name__ == "__main__":
    main()

#!/usr/bin/python3
import argparse
import lzma
import pathlib


def existing_file(path):
    path = pathlib.Path(path)
    if not path.exists():
        raise FileNotFoundError(f'File {path} does not exist')
    if not path.is_file():
        raise FileNotFoundError(f'File {path} is a directory')
    return path


def not_dir(path):
    path = pathlib.Path(path)
    if path.exists() and not path.is_dir():
        raise FileExistsError(f'{path} is a directory')
    return path


def parse_args():
    parser = argparse.ArgumentParser()
    parser.add_argument('input-file', type=existing_file)
    parser.add_argument(
        'output-file',
        type=not_dir,
        required=False,
        default=None,
    )


def decompress_lzma(input_file, output_file, chunk_bytes=1024):
    with (
            lzma.open(input_file) as fin,
            open(output_file, 'wb') as fout,
    ):
        while True:
            data = fin.read(chunk_bytes)
            if not data:
                break
            fout.write(data)


def main():
    args = parse_args()
    decompress_lzma(args.input_file, args.output_file)

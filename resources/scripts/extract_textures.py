import base64
import os
import sys
from typing import List

import click

from .bbmodel import load


def get_model_files(source_path: str) -> List[str]:
    if source_path.endswith(".bbmodel"):
        return [source_path]

    model_files = []
    for root, subdirs, files in os.walk(source_path):
        for file in files:
            if file.endswith(".bbmodel"):
                model_files.append(os.path.join(root, file))
    return model_files


def extract_texture(texture, output_dir: str):
    namespace: str = texture["namespace"]

    if namespace in ("conquest", "minecraft", ""):
        return

    folder: str = texture["folder"]
    name: str = texture["name"]
    filename = name if name.endswith(".png") else name + ".png"
    output_dir: str = os.path.join(output_dir, "assets", namespace, "textures", folder)

    data: str = texture["source"][len("data:image/png;base64,"):]

    print(f"Outputting texture to {os.path.join(output_dir, filename)}")
    os.makedirs(output_dir, 0o777, True)
    with open(os.path.join(output_dir, filename), 'wb') as f:
        f.write(base64.b64decode(data))


def extract_textures(model_path: str, output_path: str):
    model = load(model_path)
    model_type = model["meta"]["model_format"]

    if model_type != "java_block":
        return

    textures = model["textures"]
    for texture in textures:
        extract_texture(texture, output_path)


@click.command(name="extract-textures")
@click.argument('model_paths', nargs=-1)
@click.argument('output_path', nargs=1)
def extract_textures_cmd(model_paths: List[str], output_path: str):
    for model_path in model_paths:
        extract_textures(model_path, output_path)


if __name__ == '__main__':
    extract_textures_cmd()

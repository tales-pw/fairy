import copy
import glob
import os
import sys
from typing import List, Dict

import click

from .bbmodel import load, save


def full_glob(paths: List[str]):
    result = []
    for path in paths:
        result.extend(glob.glob(path))
    return result


def create_pose_file(model: Dict, pose_model: Dict):
    new_model = copy.deepcopy(model)
    new_model["display"] = copy.deepcopy(pose_model.get("display", {}))
    return new_model


def main():
    if len(sys.argv) < 3:
        raise Exception("You should define model, output and poses paths")

    model_path = sys.argv[1]
    output_path = sys.argv[2]
    pose_paths = full_glob(sys.argv[3:])

    model = load(model_path)
    for pose_path in pose_paths:
        pose_model = load(pose_path)
        new_model = create_pose_file(model, pose_model)
        save(os.path.join(output_path, os.path.basename(pose_path)), new_model)


@click.command(name="update-pose")
@click.argument('pose_paths', nargs=-1)
@click.argument('model_path', nargs=1)
@click.argument('output_path', nargs=1)
def update_pose_cmd(pose_paths: List[str], output_path: str, model_path: str):
    model = load(model_path)
    for pose_path in pose_paths:
        pose_model = load(pose_path)
        new_model = create_pose_file(model, pose_model)
        save(os.path.join(output_path, os.path.basename(pose_path)), new_model)


if __name__ == '__main__':
    update_pose_cmd()

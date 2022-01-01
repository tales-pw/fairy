from copy import deepcopy
from os import path
from typing import List, Dict
from .bbmodel import load, save

import click

TRANSFER_LIST = ["thirdperson_righthand", "thirdperson_lefthand", "firstperson_righthand", "firstperson_lefthand", "pose"]


def get_new_path(model_path: str, model_name):
    model_dir = path.dirname(model_path)
    return path.join(model_dir, "pose", f"{model_name}.bbmodel")


def transfer_pose(model: Dict, pose_model: Dict):
    pose = pose_model["display"]

    new_model = deepcopy(model)
    new_display = new_model["display"]

    for TRANSFER_ITEM in TRANSFER_LIST:
        new_display[TRANSFER_ITEM] = pose[TRANSFER_ITEM]

    new_model_name = f'{model["name"]}_{pose_model["name"]}'
    new_model["name"] = new_model_name
    new_model["display"] = new_display

    return new_model_name, new_model


@click.command(name="transfer-pose")
@click.argument('pose_paths', nargs=-1)
@click.argument('model_path', nargs=1)
def transfer_pose_cmd(pose_paths: List[str], model_path: str):
    model = load(model_path)

    for pose_path in pose_paths:
        pose_model = load(pose_path)
        new_model_name, new_model = transfer_pose(model, pose_model)
        new_path = get_new_path(model_path, new_model_name)
        save(new_path, new_model)


if __name__ == '__main__':
    transfer_pose_cmd()

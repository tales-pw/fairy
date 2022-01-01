import os
import sys

import click

sys.path.append(os.path.dirname(__file__))

from scripts import *


@click.group()
def cli():
    pass


cli.add_command(transfer_pose_cmd)
cli.add_command(extract_textures_cmd)
cli.add_command(update_pose_cmd)

if __name__ == '__main__':
    cli()

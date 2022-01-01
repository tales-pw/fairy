import json
import os
from os import path
from typing import Dict


def load(file_path: str) -> Dict:
    with open(file_path) as file:
        return json.load(file)


def save(file_path: str, model: Dict):
    os.makedirs(path.dirname(file_path), exist_ok=True)

    with open(file_path, "w") as file:
        json.dump(model, file)
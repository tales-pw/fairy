import json
import os

model = json.loads(open("couch_source.json").read())

new_files = {}
for group in model["groups"]:
    name = group["name"]
    new_files[name] = {
        "textures": model["textures"],
        "display": model["display"],
        "elements": []
    }

    for child in group["children"]:
        new_files[name]["elements"].append(model["elements"][child])

for filename, content in new_files.items():
    if not os.path.exists("./compiled"):
        os.makedirs("./compiled")

    with open(f'./compiled/{filename}.json', 'w') as outfile:
        json.dump(content, outfile)

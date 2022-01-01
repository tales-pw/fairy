import os
from os.path import isfile, join

template = '''
{
  "forge_marker": 1,
  "defaults": {
    "model": "fairy:head_like/0",
    "textures": {
      "skin": "%texture%",
      "particle": "%texture%"
    }
  },
  "variants": {
    "rotation": {
      "0": {"model": "fairy:head_like/0"},
      "1": {"model": "fairy:head_like/1"},
      "2": {"model": "fairy:head_like/2"},
      "3": {"model": "fairy:head_like/3"},
      "4": {"model": "fairy:head_like/4"},
      "5": {"model": "fairy:head_like/5"},
      "6": {"model": "fairy:head_like/6"},
      "7": {"model": "fairy:head_like/7"},
      "8": {"model": "fairy:head_like/8"},
      "9": {"model": "fairy:head_like/9"},
      "10": {"model": "fairy:head_like/10"},
      "11": {"model": "fairy:head_like/11"},
      "12": {"model": "fairy:head_like/12"},
      "13": {"model": "fairy:head_like/13"},
      "14": {"model": "fairy:head_like/14"},
      "15": {"model": "fairy:head_like/15"}
    },
    "inventory": {
      "transform": "forge:default-block"
    }
  }
}'''

for filename in [f for f in os.listdir("") if isfile(join("", f))]:
    if filename.startswith("smallcube"):
        continue

    if not filename.endswith(".png"):
        continue

    basename = os.path.splitext(os.path.basename(filename))[0]
    texture = f"fairy:blocks/coloroed_small_cube/smallcube_{basename}"

    with open(f"./blockstates/smallcube_{basename}.json", "w") as file:
        file.write(template.replace("%texture%", texture))

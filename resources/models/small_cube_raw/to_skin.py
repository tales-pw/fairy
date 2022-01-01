import os
from PIL import Image
from os.path import isfile, join

size = (256, 256)


def move(x, y):
    return (x * 32, y * 32, (x + 1) * 32, (y + 1) * 32)


def texture_to_skin(path, to_path):
    img = Image.open(path).crop((0, 0, 32, 32))
    result = Image.new('RGBA', size)

    result.paste(img, move(0, 1))
    result.paste(img, move(1, 1))
    result.paste(img, move(2, 1))
    result.paste(img, move(3, 1))

    result.paste(img, move(1, 0))
    result.paste(img, move(2, 0))

    result.save(to_path)


for filename in [f for f in os.listdir("") if isfile(join("", f))]:
    if filename.startswith("smallcube"):
        continue

    if not filename.endswith(".png"):
        continue

    basename = os.path.splitext(os.path.basename(filename))[0]
    print(f"smallcube_{basename}")
    texture_to_skin(filename, f"smallcube_{basename}.png")

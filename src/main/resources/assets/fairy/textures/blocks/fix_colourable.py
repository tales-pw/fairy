import sys
from PIL import Image

filename = sys.argv[1]

img = Image.open(filename)

w, h = img.size
pix = img.load()

color = 0

for i in range(w):
    for j in range(h):
        r, g, b, a = pix[i, j]
        if a == 0:
            continue

        if r > color:
            color = r

for i in range(w):
    for j in range(h):
        r, g, b, a = pix[i, j]
        if a == 0:
            continue
        value = r + (255 - color)
        pix[i, j] = (value, value, value, a)

img.save(filename, "PNG")

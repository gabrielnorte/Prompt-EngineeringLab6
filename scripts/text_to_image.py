#!/usr/bin/env python3
from PIL import Image, ImageDraw, ImageFont
import os

infile = os.path.join('screenshots', 'session1.txt')
outfile = os.path.join('screenshots', 'session1.png')

with open(infile, 'r', encoding='utf-8', errors='ignore') as f:
    lines = [l.rstrip('\n') for l in f.readlines()]

if not lines:
    lines = ['(no content)']

font = ImageFont.load_default()
# compute max width using a temporary draw
tmp_img = Image.new('RGB', (10, 10))
tmp_draw = ImageDraw.Draw(tmp_img)
maxw = 0
line_h = 0
for line in lines:
    try:
        bbox = tmp_draw.textbbox((0, 0), line, font=font)
        w = bbox[2] - bbox[0]
        h = bbox[3] - bbox[1]
    except Exception:
        w, h = font.getmask(line).size
    if w > maxw:
        maxw = w
    if h > line_h:
        line_h = h
line_h += 2
pad = 12
img_w = maxw + pad*2
img_h = line_h * len(lines) + pad*2
img = Image.new('RGB', (img_w, img_h), color=(30,30,30))
draw = ImageDraw.Draw(img)
y = pad
fill = (230,230,230)
for line in lines:
    draw.text((pad, y), line, font=font, fill=fill)
    y += line_h

img.save(outfile)
print('Wrote', outfile)

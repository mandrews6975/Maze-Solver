import sys
from PIL import Image

img = Image.open(sys.argv[1]).show()

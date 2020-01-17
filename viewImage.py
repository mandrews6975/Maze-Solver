import eel, platform, sys

eel.init("web")

eel.loadImage(sys.argv[0])

try:
    eel.start("main.html", mode="chrome", size=(1000, 1000))
except EnvironmentError:
    if sys.platform in ["win32", "win64"] and int(platform.release()) >= 10:
        eel.start("main.html", mode="edge", size=(1000, 1000))
    else:
        raise

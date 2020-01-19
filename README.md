# Maze-Solver
A maze solver capable of processing black and white maze images and finding paths from one point to another through various path-finding algorithms.

![](https://raw.githubusercontent.com/mandrews6975/Maze-Solver/master/02.bmp)

# Introduction
This maze solver is capable of finding paths within black and white images (no slight variation in RGB values) through various algorithms. To easily generate maze images, I recommend downloading [Daedalus Maze Creator](https://sourceforge.net/projects/daedalus/). Currently, breadth-first-search, depth-first-search, Dijkstra's algorithm, and A* are supported. When the solver processes your image, black pixels are considered to be open space and white pixels are considered to be walls. The solver is run from the commandline through the command shown below.

# Execution
In order to run the solver, use the following command:

`java -jar Maze_Solver.jar maze_image_filepath algorithm origin_x origin_y destination_x destination_y`

Available algorithm command arguments include: BFS, DFS, Dijkstra, A*.

Note: coordinates are zero-indexed.

When the maze is solved, an image of the maze with the highlighted search area/shortest path (depends on algorithm used) will be saved to the current directory and automatically opened in your default image viewer (via Python script execution).

## Example
![](https://raw.githubusercontent.com/mandrews6975/Maze-Solver/master/01.PNG)

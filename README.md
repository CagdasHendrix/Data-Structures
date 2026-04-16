# Data-Structures
# MazeSolver

This repository contains a Java-based maze solver that utilizes a **Backtracking** algorithm to navigate through a **15x15** grid. The project is designed to demonstrate the implementation of a manual **Stack** data structure without using standard Java Collections.

## Features

* **Custom Stack Implementation:** Uses a manually built `MyStack` class to manage navigation history.
* **Backtracking Algorithm:** Employs Depth-First Search (DFS) logic to find a path from the entrance (top row) to the exit (bottom row).
* **File I/O:** Dynamically reads the maze structure from an external `maze.txt` file.
* **Coordinate Output:** Prints the final path as a sequence of (row, col) coordinates in the console.

## How It Works

1. **Input:** The program reads a 15x15 grid from a file named `maze.txt`.
   * **1** represents a **wall**.
   * **0** represents a **path**.
2. **Navigation:** It starts from a detected opening in the first row and attempts to reach an opening in the last row.
3. **Backtracking:** If the algorithm hits a dead end, it pops the last position from the stack and tries an alternative direction.

## File Requirements

For the program to run correctly, a `maze.txt` file must be present in the project's root directory. It should contain **15 lines of 15 characters** each.

**Example maze.txt:**

011111111111111
000001010101001
111101010101011
000000000000000
111111111111110

How to Run (IDE)
You don't need to use the terminal; you can run the project directly through your IDE:
Open the project folder in IntelliJ IDEA, Eclipse, or VS Code.
Ensure maze.txt is located in the root directory of your project (not just inside the src folder).
Open MazeSolver.java and click the Green Play (Run) button next to the main method.
The path coordinates will be printed in the console output.

Algorithm Details
The algorithm visits each cell and marks it as visited. The time complexity of the solution is approximately:

O(V + E)

Where V is the number of cells (15 * 15 = 225) and E is the possible transitions between them.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Point {
    int row, col;

    Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return "(" + row + "," + col + ")";
    }
}

class MyStack {
    private Point[] data;
    private int topIndex = -1;

    MyStack(int capacity) {
        data = new Point[capacity];
    }

    void addPoint(Point p) {
        topIndex++;
        data[topIndex] = p;
    }

    Point showUp() {
        if (topIndex == -1) {
            throw new IllegalStateException();
        }
        Point p = data[topIndex];
        topIndex--;
        return p;
    }

    Point peep() {
        if (topIndex == -1) {
            throw new IllegalStateException();
        }
        return data[topIndex];
    }

    boolean isClear() {
        return topIndex == -1;
    }

    int vastSize() {
        return topIndex + 1;
    }

    Point getAt(int Index) {
        return data[Index];
    }
}

public class MazeSolver {

    static final int SIZE = 15;
    static int[][] maze = new int[SIZE][SIZE];
    static boolean[][] visited = new boolean[SIZE][SIZE];

    static boolean readMaze(String filename) {
        try {
            Scanner input = new Scanner(new File(filename));
            int row = 0;

            while (input.hasNextLine() && row < SIZE) {
                String line = input.nextLine().trim();
                for (int col = 0; col < SIZE && col < line.length(); col++) {
                    char c = line.charAt(col);
                    if (c == '1') {
                        maze[row][col] = 1;
                    } else {
                        maze[row][col] = 0;
                    }
                }
                row++;
            }
            input.close();
            System.out.println("Maze loaded successfully!");
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return false;
        }
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < SIZE && c < SIZE && maze[r][c] == 0 && !visited[r][c];
    }

    static boolean solveMaze(MyStack stack) {
        int startCol = -1;
        int endCol = -1;

        for (int c = 0; c < SIZE; c++) {
            if (maze[0][c] == 0) startCol = c;
            if (maze[SIZE - 1][c] == 0) endCol = c;
        }

        if (startCol == -1 || endCol == -1) {
            System.out.println("Entrance or exit not found!");
            return false;
        }

        stack.addPoint(new Point(0, startCol));
        visited[0][startCol] = true;

        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while (!stack.isClear()) {
            Point current = stack.peep();

            if (current.row == SIZE - 1 && current.col == endCol) {
                return true;
            }

            boolean moved = false;

            for (int[] d : directions) {
                int newRow = current.row + d[0];
                int newCol = current.col + d[1];

                if (isValid(newRow, newCol)) {
                    stack.addPoint(new Point(newRow, newCol));
                    visited[newRow][newCol] = true;
                    moved = true;
                    break;
                }
            }

            if (!moved) {
                stack.showUp();
            }
        }

        return false;
    }

    public static void main(String[] args) {
        boolean loaded = readMaze("maze.txt");

        if (!loaded) {
            System.out.println("Maze could not be loaded. Exiting program...");
            return;
        }

        MyStack path = new MyStack(SIZE * SIZE);

        if (solveMaze(path)) {
            System.out.println("Path found:");
            for (int i = 0; i < path.vastSize(); i++) {
                System.out.println(path.getAt(i));
            }
        } else {
            System.out.println("No path found!");
        }
    }
}
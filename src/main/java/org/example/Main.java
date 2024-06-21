package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        int Y = 6, X = 6;
        int[][] grid = new int[Y][X];

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                grid[y][x] = (int) Math.round(Math.random() * 1);
            }
        }
        int generations = 5;

        for (int i = 0; i < generations; i++) {
            printGrid(grid, Y, X);
            grid = nextGeneration(grid, Y, X);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void printGrid(int[][] grid, int Y, int X) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 0) {
                    System.out.print(" ■ ");
                } else {
                    System.out.print("\033[92m ■ \033[0m");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    static int[][] nextGeneration(int[][] grid, int Y, int X) {
        int[][] nextGrid = new int[Y][X];

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                int alive = 0;
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int newY = y + i;
                        int newX = x + j;
                        if (newY >= 0 && newY < Y && newX >= 0 && newX < X) {
                            alive += grid[newY][newX];
                        }
                    }
                }
                alive -= grid[y][x];

                if ((grid[y][x] == 1) && (alive < 2)) {
                    nextGrid[y][x] = 0;
                } else if ((grid[y][x] == 1) && (alive > 3)) {
                    nextGrid[y][x] = 0;
                } else if ((grid[y][x] == 0) && (alive == 3)) {
                    nextGrid[y][x] = 1;
                } else {
                    nextGrid[y][x] = grid[y][x];
                }
            }
        }
        return nextGrid;
    }
}
import java.util.Random;
import java.util.Scanner;

public class FifteenPuzzle {
    static int SIZE = 4;
    static int[][] board = new int[SIZE][SIZE];
    int emptyRow, emptyCol;

    public FifteenPuzzle() {
        initializeBoard();
        shuffleBoard();
    }

    public void initializeBoard() {
        int value = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = value++;
            }
        }
        board[SIZE - 1][SIZE - 1] = 0; 
        emptyRow = SIZE - 1;
        emptyCol = SIZE - 1;
    }


    public void shuffleBoard() {
    Random rand = new Random();
    for (int i = 0; i < 32; i++) {
        int direction = rand.nextInt(4);
        switch (direction) {
            case 0:
                if (emptyRow > 0) { 
                    swap(emptyRow, emptyCol, emptyRow - 1, emptyCol);
                    emptyRow--;
                }
                break;
            case 1:
                if (emptyRow < SIZE - 1) { 
                    swap(emptyRow, emptyCol, emptyRow + 1, emptyCol);
                    emptyRow++;
                }
                break;
            case 2:
                if (emptyCol > 0) { 
                    swap(emptyRow, emptyCol, emptyRow, emptyCol - 1);
                    emptyCol--;
                }
                break;
            case 3:
                if (emptyCol < SIZE - 1) {
                    swap(emptyRow, emptyCol, emptyRow, emptyCol + 1);
                    emptyCol++;
                }
                break;
        }
    }
}
    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    System.out.print("   ");
                } else {
                    System.out.printf("%2d ", board[i][j]);
                }
            }
            System.out.println();
        }
        System.out.println();
    }


    public boolean move(int num, char direction) {
        int numRow = -1;
        int numCol = -1;


        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == num) {
                    numRow = i;
                    numCol = j;
                    break;
                }
            }
        }


        if (numRow == -1 || numCol == -1) {
            return false;
        }

        switch (direction) {
            case 'U': 
                if (numRow > 0 && board[numRow - 1][numCol] == 0) {
                    swap(numRow, numCol, numRow - 1, numCol);
                    return true;
                }
                break;
            case 'D':
                if (numRow < SIZE - 1 && board[numRow + 1][numCol] == 0) {
                    swap(numRow, numCol, numRow + 1, numCol);
                    return true;
                }
                break;
            case 'L': 
                if (numCol > 0 && board[numRow][numCol - 1] == 0) {
                    swap(numRow, numCol, numRow, numCol - 1);
                    return true;
                }
                break;
            case 'R':
                if (numCol < SIZE - 1 && board[numRow][numCol + 1] == 0) {
                    swap(numRow, numCol, numRow, numCol + 1);
                    return true;
                }
                break;
            default:
                System.out.println("Invalid direction! Use U (up), D (down), L (left), R (right).");
                return false;
        }

        System.out.println("Invalid move, can't move in that direction.");
        return false;
    }

    public boolean isSolved() {
        int value = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == SIZE - 1 && j == SIZE - 1) {
                    return board[i][j] == 0;
                }
                if (board[i][j] != value++) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void swap(int row1, int col1, int row2, int col2) {
        int temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FifteenPuzzle puzzle = new FifteenPuzzle();

        System.out.println("Welcome to the 15 Puzzle Game!");
        puzzle.printBoard();

        while (!puzzle.isSolved()) {
            System.out.print("Enter the number to swap (1-15): ");
            int number = sc.nextInt();
            System.out.print("Enter the direction to move (U, D, L, R): ");
            char direction = sc.next().toUpperCase().charAt(0);

            if (puzzle.move(number, direction)) {
                puzzle.printBoard();
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
        System.out.println("Congratulations! You've solved the puzzle!");
        sc.close();
    }
}

import java.util.Scanner;

public class TicTacToe {
    static Scanner scan = new Scanner(System.in);
    static char[] board = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    static char turn = 'X';

    public static void main(String[] args) {
        while (!gameOver()) {
            printBoard();
            makeMove();
            changeTurn();
        }
        printBoard();
        printResult();
    }

    static void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.println("| " + board[i] + " " + board[i + 1] + " " + board[i + 2] + " |");
        }
        System.out.println("---------");
    }

    static void makeMove() {
        int x, y;
        do {
            System.out.println("Enter the coordinates:");
            x = scan.nextInt();
            y = scan.nextInt();
        } while (!validMove(x, y));
        // translate coordinates and make move
        board[(3 - y) * 3 + (x - 1)] = turn;
    }

    static boolean validMove(int x, int y) {
        if (x < 1 || x > 3 || y < 1 || y > 3) {
            System.out.println("Coordinates should be from 1 to 3!");
            return false;
        }
        if (board[(3 - y) * 3 + (x - 1)] != ' ') {
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }
        return true;
    }

    static void changeTurn() {
        if (turn == 'X') turn = 'O';
        else turn = 'X';
    }

    static boolean gameOver() {
        // check rows, columns and diagonals
        for (int i = 0; i < 3; ++i) {
            if (check(board[i * 3], board[i * 3 + 1], board[i * 3 + 2]) || check(board[i], board[i + 3], board[i + 6])) {
                return true;
            }
        }
        if (check(board[0], board[4], board[8]) || check(board[2], board[4], board[6])) {
            return true;
        }
        // check for draw
        for (char cell : board) {
            if (cell == ' ') return false;  // if we found an empty cell that means the game isn't over yet
        }
        return true;
    }

    static boolean check(char cell1, char cell2, char cell3) {
        return ((cell1 != ' ') && (cell1 == cell2) && (cell2 == cell3));
    }

    static void printResult() {
        if (turn == 'X') System.out.println("O wins");
        else if (turn == 'O') System.out.println("X wins");
        else System.out.println("Draw");
    }
}
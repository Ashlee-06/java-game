
import java.util.Scanner;

public class TicTacToe {
    public static final int BOXES = 3;
    public static final char Player_1 = 'X';
    public static final char Player_2 = 'O';
    private char grids[][];
    private char currentPlayer;
    private Scanner in;

    public TicTacToe() {
        grids = new char[BOXES][BOXES];
        int gridNumbers = 0;
        for (int i = 0; i < BOXES; i++) {
            for (int j = 0; j < BOXES; j++) {
                grids[i][j] = (char) ('0' + gridNumbers);
                gridNumbers++;
            }
        }
        currentPlayer = Player_1;
        in = new Scanner(System.in);
    }

    // Method to print the grids
    public void print() {
        for (int i = 0; i < BOXES; i++) {
            for (int j = 0; j < BOXES; j++) {
                System.out.printf(" %c ", grids[i][j]);
                if (j + 1 < BOXES) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i + 1 < BOXES) {
                System.out.println("-----------");
            }
        }
    }

    // Method to check the winner
    public boolean winnerChecker(char player) {
        // check for the rows
        for (int i = 0; i < BOXES; i++) {
            if (grids[i][0] == player && grids[i][1] == player && grids[i][2] == player) {
                return true;
            }
        }

        // check for the columns
        for (int j = 0; j < BOXES; j++) {
            if (grids[0][j] == player && grids[1][j] == player && grids[2][j] == player) {
                return true;
            }
        }

        // check diagonals
        if (grids[0][0] == player && grids[1][1] == player && grids[2][2] == player) {
            return true;
        }
        if (grids[2][0] == player && grids[1][1] == player && grids[0][2] == player) {
            return true;
        }

        // return false if there is no match
        return false;
    }

    // Method to check if the grids are full
    public boolean isGridsFull() {
        for (int i = 0; i < BOXES; i++) {
            for (int j = 0; j < BOXES; j++) {
                if (Character.isDigit(grids[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to check if the game is over
    public boolean isGameOver() {
        return winnerChecker(Player_1) || winnerChecker(Player_2) || isGridsFull();
    }

    // Method to place marker
    public int placeMarker() {
        System.out.println("Player " + currentPlayer + "'s turn. Choose a number from the grid:");
        int gridNumbers = in.nextInt();

        // validate grid number
        if (gridNumbers < 0 || gridNumbers > 8) {
            System.out.println("Input Grid Box is INVALID!! Try Again");
            return placeMarker();
        }

        // Checking if the box is empty
        int i = gridNumbers / BOXES;
        int j = gridNumbers % BOXES;
        if (grids[i][j] == Player_1 || grids[i][j] == Player_2) {
            System.out.println("Input Grid Box is already occupied!! Try Again");
            return placeMarker();
        }

        return gridNumbers;
    }

    // Method to play the game
    public void game() {
        while (!isGameOver()) {
            // Print the grids
            print();
            System.out.println();

            // Ask the current player to play
            int gridNumbers = placeMarker();

            // Place the marker for the current player
            int i = gridNumbers / BOXES;
            int j = gridNumbers % BOXES;
            grids[i][j] = currentPlayer;

            // Check for a winner
            if (winnerChecker(currentPlayer)) {
                print();
                System.out.println("Player " + currentPlayer + " WON!!");
                return;
            }

            // Switch players
            currentPlayer = (currentPlayer == Player_1) ? Player_2 : Player_1;
        }

        // If the grid is full and there's no winner, it's a draw
        print();
        if (isGridsFull()) {
            System.out.println("Game was drawn!");
        }
    }

    public static void main(String[] args) {
        TicTacToe tictactoe = new TicTacToe();
        tictactoe.game();
    }
}

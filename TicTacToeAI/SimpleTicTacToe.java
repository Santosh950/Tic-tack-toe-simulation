import java.util.Scanner;

public class SimpleTicTacToe {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeBoard();
        
        while (true) {
            printBoard();
            System.out.println("\nPlayer " + currentPlayer + "'s turn");
            
            if (currentPlayer == 'X') {
                // Human move
                int[] move = getHumanMove();
                board[move[0]][move[1]] = 'X';
            } else {
                // AI move
                System.out.println("AI is thinking...");
                int[] move = getAIMove();
                board[move[0]][move[1]] = 'O';
                System.out.println("AI plays at: " + move[0] + "," + move[1]);
            }
            
            // Check win
            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("\n" + currentPlayer + " wins!");
                break;
            }
            
            // Check draw
            if (isBoardFull()) {
                printBoard();
                System.out.println("\nIt's a draw!");
                break;
            }
            
            // Switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        
        scanner.close();
    }
    
    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    private static void printBoard() {
        System.out.println("\n  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) System.out.print("|");
            }
            System.out.println();
            if (i < 2) System.out.println("  -+-+-");
        }
    }
    
    private static int[] getHumanMove() {
        while (true) {
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter col (0-2): ");
            int col = scanner.nextInt();
            
            if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ') {
                return new int[]{row, col};
            }
            System.out.println("Invalid move! Try again.");
        }
    }
    
    private static int[] getAIMove() {
        // Simple AI: find first empty spot
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};
    }
    
    private static boolean checkWin(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        
        // Check diagonals
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
    
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
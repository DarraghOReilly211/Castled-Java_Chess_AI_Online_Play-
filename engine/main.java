public class main {

    // Create an 8 x 8 chess list which is the chess board.
    public static String[][] board() {
        String[][] board = new String[8][8];
        for (int i = 1; i <= 8; i++) {
            for (char a = 'a'; a <= 'h'; a++) {
                board[i-1][a-'a'] = a + Integer.toString(i);
                // System.out.println(a + Integer.toString(i));
            }
        } 
        System.out.println(board[0][0]);
        return board;
    }
    
    public static void main(String[] args) {
        String[][] board = board();
    }

    
}
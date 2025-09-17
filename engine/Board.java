public class Board {
    String name;
    String colour;
    Boolean occupied;
    int row;
    int index;
    Object piece;

    public Board(String name, String colour, Boolean occupied, int row, int index) {
        this.name = name;
        this.colour = colour;
        this.occupied = occupied;
        this.row = row;
        this.index = index;
        this.piece = null;
    }

    // Create an 8 x 8 chess list which is the chess board.
    public static Board[][] createBoard() {
        Board[][] board = new Board[8][8];

        // Updating logic to suit Board class
        // Using char fileChar = (char) ('a' + c); to increment character elements
        for (int r = 0; r < 8; r++) {
            int rank = r + 1;
            for (int c = 0; c < 8; c++) {
                char fileChar = (char) ('a' + c);
                String name = fileChar + Integer.toString(rank);

                String occupantColour;
                boolean occupied;

                if (rank == 1 || rank == 2) {
                    occupantColour = "white";
                    occupied = true;
                } else if (rank == 7 || rank == 8) {
                    occupantColour = "black";
                    occupied = true;
                } else {
                    occupantColour = "empty";
                    occupied = false;
                }

                board[r][c] = new Board(name, occupantColour, occupied, r, c);
            }
        }

        return board;
    }

    public static void main(String[] args){
        
    }
    // TODO Change board set up, after some research Ive decided to changes to the bitBoard method.
    // Since a chess board is 8x8 we can use a 64 bit long to represent the board.
    // Each piece type will have its own bitboard, so we can have a bitboard
    // Then I can use bitwise operations to calculate valid moves.
    // This will be much faster than using a 2D array and iterating through it.
    // More info here: https://www.chessprogramming.org/Bitboards
    // https://www.chessprogramming.org/Sliding_Piece_Attacks#Magic_Bitboards
}

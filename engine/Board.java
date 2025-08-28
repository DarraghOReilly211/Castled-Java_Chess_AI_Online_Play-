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
}

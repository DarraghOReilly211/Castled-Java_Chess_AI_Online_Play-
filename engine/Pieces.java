import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Pieces {

    public static class piece {
        String name;
        String color;
        Board position;

        //Initialise pieces and continually keep track of position.
        private piece(String name, String color, Board position) {
            this.name = name;
            this.color = color;
            this.position = position;

        }
    }

    //Creating array for pieces
    public static void main(String[] args) {
        Map<String, String> pieces = new HashMap<>();
        pieces.put("pawn", "8");
        pieces.put("knight", "2");
        pieces.put("rook", "2");
        pieces.put("bishop", "2");
        pieces.put("queen", "1");
        pieces.put("king", "1");

        /*
        To-do: Set up method to create pieces, assign starting position, color and name.
        Next: Set up loop to create colors for each group of pieces.
        After: Create pieces for both players and place them on the board.
            - Use nested loops to iterate through the board and assign pieces to their starting positions. (Note: Attempt to find faster method, e.g., if "pawn" rank == 2 and loop through fileChar, other pieces will be static perhaps create dictionary for piece types and their starting positions. Required: Name pieces e.g, rook1 rook2 and use library for index !Colour based)

        */
        String color = "white";


        //Ensure board object is correct and working.
        Board[][] board = Board.createBoard();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                System.out.println(board[r][c].name + " " + board[r][c].colour + " " + board[r][c].occupied);
            }
        }

    }
}

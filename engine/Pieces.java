import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Pieces {

    public static class piece {
        String name;
        String color;
        Board position;

        // Initialise pieces and continually keep track of position.
        private piece(String name, String color, Board position) {
            this.name = name;
            this.color = color;
            this.position = position;
        }
    }

    // Creating array for pieces
    public static void main(String[] args) {

        //Making a list of object "piece" so built pieces arent lost to GC (Garbage Collection).
        List<piece> whitePieces = new ArrayList<>();
        List<piece> blackPieces = new ArrayList<>();

        // Ensure board object is correct and working.
        Board[][] board = Board.createBoard();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                System.out.println(board[r][c].name + " " + board[r][c].colour + " " + board[r][c].occupied);
            }
        }

        // Changing Map<String, String to Map<String, Integer>
        Map<String, Integer> pieces = new HashMap<>();
        pieces.put("pawn", 8);
        pieces.put("knight", 2);
        pieces.put("rook", 2);
        pieces.put("bishop", 2);
        pieces.put("queen", 1);
        pieces.put("king", 1);

        /*
            To DO: Clean up naming style, use PascalCase e.g., piece would be Piece.
                - Update fields to suit convention. Set up getters and setters as well as setting the name and color to final.
                - Correct my dreadful spelling
                - Use enums instead of Strings for piece names and colors avoids my bad spelling and makes string comparison easier.
                -Try to correct piece generation so it will follow a spefic order.
                -Advice via code review with colaboratror Stawwin
                -Had to take a break today due to being sick, more real updates tomorrow 

            Next: Set up method for piece movement, and rules per group.
            
        */

        String color = "white";
        for (int colorIdx = 0; colorIdx < 2; colorIdx++) {
            for (Map.Entry<String, Integer> entry : pieces.entrySet()) {
                String pieceName = entry.getKey();
                int pieceCount = entry.getValue();
                for (int idx = 1; idx <= pieceCount; idx++) {
                    if (color.equals("white")) {
                        if (pieceName.equals("pawn")) {
                            whitePieces.add(new piece(pieceName + idx, color, board[1][idx - 1]));
                            board[1][idx - 1].occupied = true;
                            board[1][idx - 1].piece = pieceName + idx;
                        } else if (pieceName.equals("rook")) {
                            if (idx == 1) {
                                whitePieces.add(new piece(pieceName + idx, color, board[0][0]));
                                board[0][0].occupied = true;
                                board[0][0].piece = pieceName + idx;
                            } else {
                                whitePieces.add(new piece(pieceName + idx, color, board[0][7]));
                                board[0][7].occupied = true;
                                board[0][7].piece = pieceName + idx;
                            }
                        } else if (pieceName.equals("knight")) {
                            if (idx == 1) {
                                whitePieces.add(new piece(pieceName + idx, color, board[0][1]));
                                board[0][1].occupied = true;
                                board[0][1].piece = pieceName + idx;
                            } else {
                                whitePieces.add(new piece(pieceName + idx, color, board[0][6]));
                                board[0][6].occupied = true;
                                board[0][6].piece = pieceName + idx;
                            }
                        } else if (pieceName.equals("bishop")) {
                            if (idx == 1) {
                                whitePieces.add(new piece(pieceName + idx, color, board[0][2]));
                                board[0][2].occupied = true;
                                board[0][2].piece = pieceName + idx;
                            } else {
                                whitePieces.add(new piece(pieceName + idx, color, board[0][5]));
                                board[0][5].occupied = true;
                                board[0][5].piece = pieceName + idx;
                            }
                        } else if (pieceName.equals("queen")) {
                            whitePieces.add(new piece(pieceName + idx, color, board[0][3]));
                            board[0][3].occupied = true;
                            board[0][3].piece = pieceName + idx;
                        } else if (pieceName.equals("king")) {
                            whitePieces.add(new piece(pieceName + idx, color, board[0][4]));
                            board[0][4].occupied = true;
                            board[0][4].piece = pieceName + idx;
                        } else {
                            continue;
                        }
                    } else {
                        if (pieceName.equals("pawn")) {
                            blackPieces.add(new piece(pieceName + idx, color, board[6][idx - 1]));
                            board[6][idx - 1].occupied = true;
                            board[6][idx - 1].piece = pieceName + idx;
                        } else if (pieceName.equals("rook")) {
                            if (idx == 1) {
                                blackPieces.add(new piece(pieceName + idx, color, board[7][0]));
                                board[7][0].occupied = true;
                                board[7][0].piece = pieceName + idx;
                            } else {
                                blackPieces.add(new piece(pieceName + idx, color, board[7][7]));
                                board[7][7].occupied = true;
                                board[7][7].piece = pieceName + idx;
                            }
                        } else if (pieceName.equals("knight")) {
                            if (idx == 1) {
                                blackPieces.add(new piece(pieceName + idx, color, board[7][1]));
                                board[7][1].occupied = true;
                                board[7][1].piece = pieceName + idx;
                            } else {
                                blackPieces.add(new piece(pieceName + idx, color, board[7][6]));
                                board[7][6].occupied = true;
                                board[7][6].piece = pieceName + idx;
                            }
                        } else if (pieceName.equals("bishop")) {
                            if (idx == 1) {
                                blackPieces.add(new piece(pieceName + idx, color, board[7][2]));
                                board[7][2].occupied = true;
                                board[7][2].piece = pieceName + idx;
                            } else {
                                blackPieces.add(new piece(pieceName + idx, color, board[7][5]));
                                board[7][5].occupied = true;
                                board[7][5].piece = pieceName + idx;
                            }
                        } else if (pieceName.equals("queen")) {
                            blackPieces.add(new piece(pieceName + idx, color, board[7][3]));
                            board[7][3].occupied = true;
                            board[7][3].piece = pieceName + idx;
                        } else if (pieceName.equals("king")) {
                            blackPieces.add(new piece(pieceName + idx, color, board[7][4]));
                            board[7][4].occupied = true;
                            board[7][4].piece = pieceName + idx;
                        } else {
                            continue;
                        }
                    }
                }
            }
            color = "black";
        }
        // Print out board to verify pieces are in correct position.
        for (Board[] row : board) {
            for (Board square : row) {
                System.out.println(square.name + " " + square.colour + " " + square.occupied + " " + square.piece);
            }
            
        }
    }
}

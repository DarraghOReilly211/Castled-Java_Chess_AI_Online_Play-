import java.util.List;
import java.util.ArrayList;

public class Pieces {

    // Using enums avoids typo hell and makes comparisons type-safe.
    enum PieceType {
        PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING
    }

    enum PieceColor {
        WHITE, BLACK
    }

    public static class Piece {
        // Everything is immutable except position which is the bit that moves.
        private final String id;
        private final PieceType type;
        private final PieceColor color;
        private Board position;

        // Initialise piece and track its location.
        private Piece(PieceType type, PieceColor color, String id, Board position) {
            this.type = type;
            this.color = color;
            this.id = id;
            this.position = position;
        }

        // Getters: To follow coding convention.
        public String getId() {
            return id;
        }

        public PieceType getType() {
            return type;
        }

        public PieceColor getColor() {
            return color;
        }

        public Board getPosition() {
            return position;
        }

        // Setter only for position as the position is the only thing that changes.
        public void setPosition(Board position) {
            this.position = position;
        }

        @Override
        public String toString() {
            // When a piece is captured, position is null; call it "Taken" for clarity while debugging.
            if (position == null) {
                return color + " " + type + " Captured";
            }
            return color + " " + type + " @ " + position.name;
        }
    }

    // Entry point just builds a fresh board and drops all pieces in standard order.
    public static void main(String[] args) {

        // Hold references so the GC doesn’t eat my army.
        List<Piece> whitePieces = new ArrayList<>();
        List<Piece> blackPieces = new ArrayList<>();

        // Sanity: build board and peek at squares.
        Board[][] board = Board.createBoard();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                System.out.println(board[r][c].name + " " + board[r][c].colour + " " + board[r][c].occupied);
            }
        }

        // Drop all pieces in a standard chess layout (no more map iteration randomness).
        for (PieceColor color : PieceColor.values()) {

            // From White’s POV: back rank row 0, pawns row 1. For Black: pawns 6, back rank 7.
            final int pawnRow = (color == PieceColor.WHITE) ? 1 : 6;
            final int backRow = (color == PieceColor.WHITE) ? 0 : 7;

            // --- Pawns: files a..h => cols 0..7, left-to-right ---
            for (int file = 0; file < 8; file++) {
                String id = color.name().toLowerCase() + "_pawn" + (file + 1);
                Piece pawn = new Piece(PieceType.PAWN, color, id, board[pawnRow][file]);

                if (color == PieceColor.WHITE) {
                    whitePieces.add(pawn);
                } else {
                    blackPieces.add(pawn);
                }

                // Keep the board’s square state in sync.
                board[pawnRow][file].occupied = true;
                board[pawnRow][file].piece = id;
            }

            // --- Back rank order: R, N, B, Q, K, B, N, R ---
            PieceType[] backOrder = new PieceType[] {
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK
            };

            // Indices for nice, stable IDs (rook1/rook2, etc.).
            int rookIdx = 0, knightIdx = 0, bishopIdx = 0, queenIdx = 0, kingIdx = 0;

            for (int col = 0; col < 8; col++) {
                PieceType type = backOrder[col];

                // Build readable IDs for squares and debugging.
                String typeNameLower = switch (type) {
                    case ROOK   -> { rookIdx++;   yield "rook" + rookIdx; }
                    case KNIGHT -> { knightIdx++; yield "knight" + knightIdx; }
                    case BISHOP -> { bishopIdx++; yield "bishop" + bishopIdx; }
                    case QUEEN  -> { queenIdx++;  yield "queen" + queenIdx; }
                    case KING   -> { kingIdx++;   yield "king" + kingIdx; }
                    case PAWN   -> "pawn"; // Not used on back rank just here for default
                };

                String id = color.name().toLowerCase() + "_" + typeNameLower;
                Piece backRankPiece = new Piece(type, color, id, board[backRow][col]);

                if (color == PieceColor.WHITE) {
                    whitePieces.add(backRankPiece);
                } else {
                    blackPieces.add(backRankPiece);
                }

                // Square bookkeeping.
                board[backRow][col].occupied = true;
                board[backRow][col].piece = id;
            }
        }

        // Quick dump of the board to confirm everything landed where it should.
        for (Board[] row : board) {
            for (Board square : row) {
                System.out.println(square.name + " " + square.colour + " " + square.occupied + " " + square.piece);
            }
        }
    }
}

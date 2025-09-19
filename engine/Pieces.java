import java.util.List;
import java.util.ArrayList;

public class Pieces {

    // Using enums avoids typo hell and makes comparisons type-safe.
    enum PieceType { PAWN, KNIGHT, BISHOP, ROOK, QUEEN, KING }
    enum PieceColour { WHITE, BLACK }

    // History of moves (not yet used).
    public List<String> history = new ArrayList<>();

    // === Game state owned by Pieces ===
    private final Board[][] board;                 // single source of truth for the board

    // Inject the already-created board; do NOT recreate it.
    public Pieces(Board[][] board) {
        this.board = board;
    }

    // === Piece model ===
    // Make this static so we can construct it from static main without an outer instance ref.
    public static class Piece {
        // Everything is immutable except position which is the bit that moves.
        private final String id;
        private final PieceType type;
        private final PieceColour colour;
        private Board position;
        private List<Board> validMoves;

        // Initialise piece and track its location.
        public Piece(PieceType type, PieceColour colour, String id, Board position) {
            this.type = type;
            this.colour = colour;
            this.id = id;
            this.position = position;
        }

        // Getters: To follow coding convention.
        public String getId() { return id; }
        public PieceType getType() { return type; }
        public PieceColour getColour() { return colour; }
        public Board getPosition() { return position; }

        // Setter only for position as the position is the only thing that changes.
        public void setPosition(Board position) { this.position = position; }

        @Override
        public String toString() {
            // When a piece is captured, position is null; call it "Taken" for clarity while debugging.
            if (position == null) {
                return colour + " " + type + " Captured";
            }
            return colour + " " + type + " @ " + position.name;
        }
    }

    // Example: iterate board, return empty list for now
    public List<Board> calculateValidMoves() {
        List<Board> validMoves = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                System.out.println(board[r][c].piece);
            }
        }
        return validMoves;
    }

    private Piece movePiece(Piece piece, Board newPosition) {
        int limit;
        // Update the piece's position.
        switch (piece.type) {
            case PAWN:
                if (piece.position.toString().contains("2") || piece.position.toString().contains("7")) {
                    limit = 2;
                } else {
                    limit = 1;
                }
                break;
            case KNIGHT:
                limit = 3;
                break;
            case BISHOP:
            case ROOK:
            case QUEEN:
                limit = 10;
                break;
            case KING:
                limit = 1;
                break;
            default:
                limit = 0;
        }

        piece.setPosition(newPosition);

        // If you want to refresh moves, do it BEFORE returning (and use this.board internally).
        // calculateValidMoves();

        return piece; // nothing after this line runs
    }

    // Entry point just builds a fresh board and drops all pieces in standard order.
    public static void main(String[] args) {

        // Sanity: build board and peek at squares.
        Board[][] board = Board.createBoard();

        // Create the game wrapper that *owns* the board reference.
        Pieces game = new Pieces(board);

        // Hold references so the GC doesn’t eat my army.
        List<Pieces.Piece> whitePieces = new ArrayList<>();
        List<Pieces.Piece> blackPieces = new ArrayList<>();

        // Drop all pieces in a standard chess layout (no more map iteration randomness).
        for (PieceColour colour : PieceColour.values()) {

            // From White’s POV: back rank row 0, pawns row 1. For Black: pawns 6, back rank 7.
            final int pawnRow = (colour == PieceColour.WHITE) ? 1 : 6;
            final int backRow = (colour == PieceColour.WHITE) ? 0 : 7;

            // --- Pawns: files a..h => cols 0..7, left-to-right ---
            for (int file = 0; file < 8; file++) {
                String id = colour.name().toLowerCase() + "_pawn" + (file + 1);
                Pieces.Piece pawn = new Pieces.Piece(PieceType.PAWN, colour, id, board[pawnRow][file]);

                if (colour == PieceColour.WHITE) {
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
                    case PAWN   -> "pawn"; // Not used on back rank; here for completeness
                };

                String id = colour.name().toLowerCase() + "_" + typeNameLower;
                Pieces.Piece backRankPiece = new Pieces.Piece(type, colour, id, board[backRow][col]);

                if (colour == PieceColour.WHITE) {
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
            System.out.println();
            System.out.print("|");
            for (Board square : row) {
                System.out.print((square.piece != null ? square.piece : "empty") + "\t|");
            }
        }
        System.out.println();

        // Example call that uses the injected board without passing it in:
        game.calculateValidMoves();

    }
}
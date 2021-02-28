package chessGame;

/**
 * Used to create pieces of type Pawn.
 * 
 * @author Cannon Jensen
 *
 */
public class Pawn extends Piece {
	private PieceType type;
	
	/**
	 * Creates pieces of type pawn by calling the super constructor from class piece.
	 * 
	 * @param x
	 * @param y
	 * @param isWhite
	 * @param ID
	 */
	public Pawn(int x, int y, boolean isWhite, int ID) {
		super(x, y, isWhite, ID);
		this.type = PieceType.PAWN;
	}

	/**
	 * Returns true or false for a valid path for a pawn.
	 */
	@Override
	public boolean isValidPath(Board board, int startX, int startY, int endX, int endY) {
		if(super.isValidPath(board, startX, startY, endX, endY) == false) return false;
		if(startX == endX && startY == endY) return true;
		
		//white pawns
		if(this.isWhite()) {
			//if the pawn hasn't moved and wants to move 2 forward
			if((endX - startX == 2) && (startY == endY)) {
				if(board.getPiece(endX - 1, endY) == null && board.getPiece(endX, endY) == null) {
					if(!this.hasMoved()) return true;
				}
			}
			//move one space forward
			else if((endX - startX) == 1 && (startY == endY)) {
				if(board.getPiece(endX, endY) == null) return true;
			}
			//move diaganol to kill enemy piece
			else if((endX - startX == 1) && (Math.abs(startY - endY) == 1)) {
				if(board.getPiece(endX, endY) != null) {
					if(!board.getPiece(endX, endY).isWhite()) return true;
				}
			}
		}
		//black pawns
		else {
			//if the pawn hasn't moved and wants to move 2 forward
			if((startX - endX == 2) && (startY == endY)) {
				if(board.getPiece(endX + 1, endY) == null && board.getPiece(endX, endY) == null) {
					if(!this.hasMoved()) return true;
				}
			}
			//move one space forward
			else if((startX - endX) == 1 && (startY == endY)) {
				if(board.getPiece(endX, endY) == null) return true;
			}
			//move diaganol to kill enemy piece
			else if((startX - endX == 1) && (Math.abs(startY - endY) == 1)) {
				if(board.getPiece(endX, endY) != null) {
					if(board.getPiece(endX, endY).isWhite()) return true;
				}
			}
		}
		//if pawn tries to move a distance greater than 1 or 2 forwards or move diagonal without an enemy piece to take out
		return false;
	}

	/**
	 * Returns the type of the piece.
	 */
	@Override
	public PieceType getType() {
		return this.type;
	}

	/**
	 * Returns a string representation of the piece.
	 */
	@Override
	public String toString() {
		if(this.isWhite()) return "WP";
		return "BP";
	}

}

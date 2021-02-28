package chessGame;

/**
 * Used to create pieces of type King.
 * 
 * @author Cannon Jensen
 *
 */
public class King extends Piece {
	private PieceType type;
	
	/**
	 * Creates pieces of type king by calling the super constructor.
	 * @param x
	 * @param y
	 * @param isWhite
	 * @param ID
	 */
	public King(int x, int y, boolean isWhite, int ID) {
		super(x, y, isWhite, ID);
		this.type = PieceType.KING;
	}

	/**
	 * Checks for a valid path for the king.
	 */
	@Override
	public boolean isValidPath(Board board, int startX, int startY, int endX, int endY) {
		if(super.isValidPath(board, startX, startY, endX, endY) == false) return false;
		if(startX == endX && startY == endY) return true;
		if (Math.abs(endX - startX) > 1) return false;
		if (Math.abs(endY - startY) > 1) return false;
		return true;
	}

	/**
	 * Returns the type of the piece.
	 */
	@Override
	public PieceType getType() {
		return this.type;
	}

	/**
	 * Returns a string representation of the King object.
	 */
	@Override
	public String toString() {
		if(this.isWhite()) return "WKing";
		return "BKing";
	}

}

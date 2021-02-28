package chessGame;

/**
 * Used to create pieces of type Knight.
 * 
 * @author Cannon Jensen
 *
 */
public class Knight extends Piece {
	private PieceType type;

	/**
	 * Use the super constructor from class piece to create a piece of type Knight.
	 * 
	 * @param x
	 * @param y
	 * @param isWhite
	 * @param ID
	 */
	public Knight(int x, int y, boolean isWhite, int ID) {
		super(x, y, isWhite, ID);
		this.type = PieceType.KNIGHT;
	}

	/**
	 * Check for a valid path for the knight.
	 */
	@Override
	public boolean isValidPath(Board board, int startX, int startY, int endX, int endY) {
		if(super.isValidPath(board, startX, startY, endX, endY) == false) return false;
		if(startX == endX && startY == endY) return true;
		if(board.isFull(endX, endY)) {
			if(board.getPiece(endX, endY).isWhite() == this.isWhite()) return false;
		}
		double distance = Math.sqrt(5);
		double x_dis = endX - this.getX();
		double y_dis = endY - this.getY();
		if(distance == Math.sqrt(Math.pow(x_dis, 2) + Math.pow(y_dis, 2))) return true;
		return false;
	}

	/**
	 * Return the type of the piece.
	 */
	@Override
	public PieceType getType() {
		return this.type;
	}

	/**
	 * Return "BK" as a string representation of the knight.
	 */
	@Override
	public String toString() {
		if(this.isWhite()) return "WK";
		return "BK";
	}

}

package chessGame;

/**
 * Used to create pieces of type Rook.
 * 
 * @author Cannon Jensen
 *
 */
public class Rook extends Piece {
	private PieceType type;

	/**
	 * Constructs piece objects of type Rook.
	 * 
	 * @param x
	 * @param y
	 * @param isWhite
	 * @param ID
	 */
	public Rook(int x, int y, boolean isWhite, int ID) {
		super(x, y, isWhite, ID);
		this.type = PieceType.ROOK;
	}

	/**
	 * Returns true or false for a valid path for a rook.
	 */
	@Override
	public boolean isValidPath(Board board, int startX, int startY, int endX, int endY) {
		if(super.isValidPath(board, startX, startY, endX, endY) == false) return false;
		if(startX == endX && startY == endY) return true;

		int countX = Math.abs(startX - endX); 
		int countY = Math.abs(startY - endY);
		int length;
		
		//determine direction
		// vertical or horizontal
		boolean isVertical = true;
		boolean isNorth = true;
		boolean isEast = true;
		if(countX == 0) isVertical = false;
		
		if(isVertical) {
			if(startX > endX) isNorth = false;
		} else {
			if(startY > endY) isEast = false;
		}
		
		if(startX == endX || startY == endY) {
			// Rook is moving vertically
			if(isVertical) {
				// Rook moves north
				if(isNorth) {
					length = startX + 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.isFull(length, startY)) {
							return false;
						}
						length++;
					}
					return true;
				}
				// Rook moves south
				else {
					length = startX - 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.isFull(length, startY)) {
							return false;
						}
						length--;
					}
					return true;
				}
			}
			// Rook is moving horizontally
			else {
				// Rook moves east
				if(isEast) {
					length = startY + 1;
					for(int i = 0; i < countY - 1; i++) {
						if(board.isFull(startX, length)) {
							return false;
						}
						length++;
					}
					return true;
				}
				// Rook moves west
				else {
					length = startY - 1;
					for(int i = 0; i < countY - 1; i++) {
						if(board.isFull(startX, length)) {
							return false;
						}
						length--;
					}
					return true;
				}
			}
		}
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
		if(this.isWhite()) return "WR";
		return "BR";
	}

}

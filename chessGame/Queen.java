package chessGame;

/**
 * Used to create objects of type Piece.
 * 
 * @author Cannon Jensen
 *
 */
public class Queen extends Piece {
	private PieceType type;
	
	/**
	 * Creates pieces of type Queen by calling the super constructor from class piece.
	 * 
	 * @param x
	 * @param y
	 * @param isWhite
	 * @param ID
	 */
	public Queen(int x, int y, boolean isWhite, int ID) {
		super(x, y, isWhite, ID);
		this.type = PieceType.QUEEN;
	}

	/**
	 * Returns true or false for a valid path for a queen.
	 */
	@Override
	public boolean isValidPath(Board board, int startX, int startY, int endX, int endY) {
		if(super.isValidPath(board, startX, startY, endX, endY) == false) return false;
		if(startX == endX && startY == endY) return true;
		
		boolean isEast = false;
		boolean isNorth = false;
		
		int countX = Math.abs(startX - endX); 
		int countY = Math.abs(startY - endY);
		int x;
		int y;
		int length;
		
		if(startY < endY) isEast = true;
		if(startX < endX) isNorth = true;
		
		//if move is diaganol
		if (countX == countY) {
			//is east
			if(isEast) {
				//is NE
				if(isNorth) {
					x = startX + 1;
					y = startY + 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.getPiece(x, y) != null) return false;
						x++;
						y++;
					}
					return true;
				}
				//is SE
				else {
					x = startX - 1;
					y = startY + 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.getPiece(x, y) != null) return false;
						x--;
						y++;
					}
					return true;
				}
			}
			//is west
			else {
				//is NW
				if(isNorth) {
					x = startX + 1;
					y = startY - 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.getPiece(x, y) != null) return false;
						x++;
						y--;
					}
					return true;
				}
				//is SW
				else {
					x = startX - 1;
					y = startY - 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.getPiece(x, y) != null) return false;
						x--;
						y--;
					}
					return true;
				}
			}
		}
			
		//if not diaganol, if horizontal
		else if(startX == endX || startY == endY) {
			boolean isVertical = true;
			if(countX == 0) isVertical = false;
			if(isVertical) {
				if(startX > endX) isNorth = false;
			} else {
				if(startY > endY) isEast = false;
			}
			
			// Rook is moving vertically
			if(isVertical) {
				// Rook moves north
				if(isNorth) {
					length = startX + 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.getPiece(length, startY) != null) return false;
						length++;
					}
					return true;
				}
				// Rook moves south
				else {
					length = startX - 1;
					for(int i = 0; i < countX - 1; i++) {
						if(board.getPiece(length, startY) != null) return false;
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
						if(board.getPiece(startX, length) != null) return false;
						length++;
					}
					return true;
				}
				// Rook moves west
				else {
					length = startY - 1;
					for(int i = 0; i < countY - 1; i++) {
						if(board.getPiece(startX, length) != null) return false;
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
		if(this.isWhite()) return "WQ";
		return "BQ";
	}

}

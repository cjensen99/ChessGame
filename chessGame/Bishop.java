package chessGame;

/**
 * Used to create pieces of type Bishop.
 * 
 * @author Cannon Jensen
 *
 */
public class Bishop extends Piece{
	private PieceType type;

	/**
	 * Use the super constructor from class piece to create a piece of type Bishop.
	 * 
	 * @param x
	 * @param y
	 * @param isWhite
	 * @param ID
	 */
	public Bishop(int x, int y, boolean isWhite, int ID) {
		super(x, y, isWhite, ID);
		this.type = PieceType.BISHOP;
	}

	/**
	 * Check for a valid path for the bishop.
	 */
	@Override
	public boolean isValidPath(Board board, int startX, int startY, int endX, int endY) {
		if(super.isValidPath(board, startX, startY, endX, endY) == false) return false;
		if(startX == endX && startY == endY) return true;
//		System.err.println("why is this valid?");

		boolean xIsEast = false;
		boolean yIsNorth = false;
		//determine direction
		if(startY < endY) xIsEast = true;
		if(startX < endX) yIsNorth = true;
		
		//loop through spaces that will be moved through to ensure that no piece is blocking the path
		
		int countX = Math.abs(startX - endX);
		int countY = Math.abs(startY - endY);
		int x;
		int y;
		
		if (countX == countY) {
			//is east
			if(xIsEast) {
				//is NE
				if(yIsNorth) {
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
				if(yIsNorth) {
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
		return false;
	}

	/**
	 * Return type of the piece.
	 */
	@Override
	public PieceType getType() {
		return this.type;
	}

	/**
	 * Return "WB" or "BB" as a string representation of the knight.
	 */
	@Override
	public String toString() {
		if(this.isWhite()) return "WB";
		return "BB";
	}

}

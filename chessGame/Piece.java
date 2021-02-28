package chessGame;

/**
 * Used to create methods to manipulate the pieces on the chess board.
 * @author Cannon Jensen
 *
 */
public abstract class Piece{
	
	private int x;
	private int y;
	private boolean isWhite;
	private boolean hasMoved = false;
	private int ID;
	
	/**
	 * Super constructor used to create objects of it's subclasses.
	 * 
	 * @param x
	 * @param y
	 * @param isWhite
	 * @param ID
	 */
	public Piece(int x, int y, boolean isWhite, int ID) { // ID is used to keep track of living/killed pieces
		this.x = x;
		this.y = y;
		this.isWhite = isWhite;
		this.hasMoved = false;
		this.ID = ID;
	}
	
	/**
	 * Get x value of a piece.
	 * @return
	 */
	public int getX() {
		return this.x;
	}
	
	/*
	 * Get y value of a piece.
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Set x value of a piece.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Set y value of a piece.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Set that a piece has moved.
	 */
	public void setMoved() {
		this.hasMoved = true;
	}
	
	/**
	 * @return if piece has moved or not.
	 */
	public boolean hasMoved() {
		return this.hasMoved;
	}
	
	/**
	 * @return if piece is white or black.
	 */
	public boolean isWhite() {
		return this.isWhite;
	}
	
	/**
	 * @return piece ID
	 */
	public int getID() {
		return this.ID;
	}
	
	public boolean isValidPath(Board board, int startX, int startY, int endX, int endY) {
		 //Attempt to move outside of board boundaries or move a piece from outside the boundaries
		if(startX < 0 || startX > 7 || endX < 0 || endX > 7 || startY < 0 || startY > 7 || endY < 0 || endY > 7) return false;
		//if no move is done
		if(startX == endX && startY == endY) return true;
		//if end point has a piece and is the same color as the piece being moved, return false
		if(board.isFull(endX, endY)) {
			if(this.isWhite() == board.getPiece(endX, endY).isWhite()) return false;
		}
		//if move is within valid boundaries and a piece is set to move
		return true;
	}
	
	public abstract PieceType getType();
	
	public abstract String toString();
	
}

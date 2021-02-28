package chessGame;

import edu.princeton.cs.algs4.Queue;

/**
 * Creates players which are used to initialize pieces onto the board.
 * 
 * @author Cannon Jensen
 *
 */
public class Player {
	private boolean isWhite;
	private Queue<Piece> queue1 = new Queue<Piece>();
	private Queue<Piece> queue2 = new Queue<Piece>();
	
	/**
	 * Create object of type Player.
	 * 
	 * @param isWhite
	 */
	public Player(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	/**
	 * Initialize queues with the pieces for a chess game.
	 */
	public void setupPieces() {
		if(isWhite) {
			for(int i = 0; i < 8; i++) {
				queue1.enqueue(new Pawn(1,i,true,i));
			}
			
			queue1.enqueue(new Rook(0,0,true,8));
			queue1.enqueue(new Knight(0,1,true,9));
			queue1.enqueue(new Bishop(0,2,true,10));
			queue1.enqueue(new King(0,3,true,11));
			queue1.enqueue(new Queen(0,4,true,12));
			queue1.enqueue(new Bishop(0,5,true,13));
			queue1.enqueue(new Knight(0,6,true,14));
			queue1.enqueue(new Rook(0,7,true,15));
		}
		else {
			for(int i = 0; i < 8; i++) {
				queue2.enqueue(new Pawn(6,i,false,i));
			}
			
			queue2.enqueue(new Rook(7,0,false,8));
			queue2.enqueue(new Knight(7,1,false,9));
			queue2.enqueue(new Bishop(7,2,false,10));
			queue2.enqueue(new Queen(7,3,false,11));
			queue2.enqueue(new King(7,4,false,12));
			queue2.enqueue(new Bishop(7,5,false,13));
			queue2.enqueue(new Knight(7,6,false,14));
			queue2.enqueue(new Rook(7,7,false,15));
		}
	}
	
	/**
	 * @return the queue of pieces for either player.
	 */
	public Iterable<Piece> pieces() {
		if (this.isWhite) return queue1;
		return queue2;
	}
	
	/**
	 * @return if player isWhite or not.
	 */
	public boolean isWhite() {
		return this.isWhite;
	}
}

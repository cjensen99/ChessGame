package chessGame;

/**
 * Initializes the chess board to store the information of where pieces are at.
 * Uses methods to move the pieces around the board and check for check/checkmate.
 * 
 * @author Cannon Jensen
 *
 */
public class Board {
	private Piece[][] board;
	private Player player1 = new Player(true);
	private Player player2 = new Player(false);
	private Piece[] whitePieces = new Piece[16];
	private Piece[] blackPieces = new Piece[16];
	private int whiteKingX;
	private int whiteKingY;
	private int blackKingX;
	private int blackKingY;
	private int threatX;
	private int threatY;
	
	public Board() {
		this.board = new Piece[8][8];
	}
	
	public void initializePieces() {
		int i = 0;
		player1.setupPieces();
		player2.setupPieces();
		for (Piece p : player1.pieces()) {
			if(p.getType() == PieceType.KING) {
				whiteKingX = p.getX();
				whiteKingY = p.getY();
			}
			putPiece(p.getX(), p.getY(), p);
			whitePieces[i] = p;
			i++;
		}
		i = 0;
		for (Piece p : player2.pieces()) {
			if(p.getType() == PieceType.KING) {
				blackKingX = p.getX();
				blackKingY = p.getY();
			}
			putPiece(p.getX(), p.getY(), p);
			blackPieces[i] = p;
			i++;
		}
	}
	
	/**
	 * Checks to see if there is a piece at a given spot on the board.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isFull(int x, int y) {	// does a given position have a piece on it? 
		if(board[x][y] == null) return false;
		return true;
	}
	
	/**
	 * Puts a piece onto the board.
	 * 
	 * @param x
	 * @param y
	 * @param piece
	 */
	public void putPiece(int x, int y, Piece piece) {		// put a piece onto the board
		board[x][y] = piece;
	}
	
	/**
	 * Removes a piece from the board.
	 * 
	 * @param x
	 * @param y
	 */
	public void removePiece(int x, int y) {		// remove a piece from a given spot to move it
		board[x][y] = null;
	}
	
	/**
	 * Stores information when a piece has been captured and is no longer on the board.
	 * 
	 * @param x
	 * @param y
	 */
	private void killPiece(int x, int y) {	// if killed, remove from array of pieces
		if(board[x][y].isWhite()) whitePieces[board[x][y].getID()] = null;
		else blackPieces[board[x][y].getID()] = null;
	}
	
//	/**
//	 * Checks to see if either player is in check
//	 * 
//	 * @param isWhite
//	 * @param kingX
//	 * @param kingY
//	 * @return
//	 */
//	// if isWhite == true, check to see if white is in check. Vice versa for black
//	public boolean isInCheck(boolean isWhite, int kingX, int kingY) { 
//		boolean isInCheck = false;
//		if(isWhite) {	// loop through all living black pieces, if any has a valid path to white king return true
//			for(int i = 0; i < 16; i++) {
//
//					if(blackPieces[i] != null) {
//						if(blackPieces[i].isValidPath(this, blackPieces[i].getX(), blackPieces[i].getY(), kingX, kingY)) {
//							threatX = blackPieces[i].getX();
//							threatY = blackPieces[i].getY();
//							return true;
//						}
//					}
//			}
//		} else {	// loop through all living white pieces, if any has a valid path to black king return true
//			for(int i = 0; i < 16; i++) {
//				if(whitePieces[i] != null) {
//					if(whitePieces[i].isValidPath(this, whitePieces[i].getX(), whitePieces[i].getY(), kingX, kingY)) {
//						threatX = whitePieces[i].getX();
//						threatY = whitePieces[i].getY();
//						return true;
////							isInCheck = true;
////							break;
//					}
////						if(isInCheck = true) break;
//				}
//			}
//		}
//		return false; // if no piece has a valid path to the enemy king return false
////		return isInCheck;
//	}
	
//	/**
//	 * Checks to see if either player is in checkMate
//	 * 
//	 * @param isWhite
//	 * @param isInCheck
//	 * @return
//	 */
//	// only to be used after isInCheck returns true so that it does not have to run isInCheck method again
//	public boolean isInCheckMate(boolean isWhite, boolean isInCheck) {
//		if(isInCheck == false) return false;
//		int sumx = 0;
//		int sumy = 0;
//		
//		if(isWhite) {
//			// first check to see if the king can move somewhere to safety
//			for(int x = -1; x < 2; x++) { // increment x values
//				for (int y = -1; y < 2; y++) { // increment y values to find all possible king movements
//					//check for king valid path anywhere around it
//					//if it has a valid path, move the king in the game board and check for check there
//					//if check then == false, move the king back and return false
//					//if check then == true, move the king back and restart with the next possible movement for the king
//					if(board[whiteKingX][whiteKingY].isValidPath(this, whiteKingX, whiteKingY, whiteKingX + x, whiteKingY + y)) {
//						System.err.println("New spot, X: " + whiteKingX + x + " Y: " + whiteKingY + y);
//						if(!isInCheck(true, whiteKingX + x, whiteKingY + y)) {
//							System.err.println("New spot is not check");
//							return false;
//						} else {
//							System.err.println("New spot is not in check");
//						}
//					}
//				}
//			}
//			// second check to see if the another piece can move to protect the king or kill piece threatening the king
//			//TODO
//			// doesn't check to see if any allying pieces can move inbetween the king and threat piece yet
//			for(int i = 0; i < 16; i++) {
//				if(whitePieces[i] != null) {
//					if(whitePieces[i].isValidPath(this, whitePieces[i].getX(), whitePieces[i].getY(), threatX, threatY)) {
//						return false;
//					}
//				}
//			}
//			//if neither of the above return false, white is in checkmate
//			return true;
//		  //checking to see if black is in checkmate
//		} else {
//			// first check to see if the king can move somewhere to safety
//			for(int x = -1; x < 2; x++) { // increment x values
//				for (int y = -1; y < 2; y++) { // increment y values to find all possible king movements
//					if(x != 0 || y != 0) {
////						System.err.println("X: " + x + " Y: " + y);
////						System.err.println("Isn't valid yet");
//						sumx = blackKingX + x;
//						sumy = blackKingY + y;
//						if(blackKingX + x >= 0 && blackKingX + x < 8 && blackKingY + y >= 0 && blackKingY + y < 8) {
////							System.err.println("blackKing spot: " + " x: " + blackKingX + " y: " + blackKingY);
////							System.err.println("x: " + x + " y: " + y);
////							System.err.println("blackKing spot: " + " x+x: " + sumx + " y+y: " + sumy);
//							if(board[blackKingX][blackKingY].isValidPath(this, blackKingX, blackKingY, blackKingX + x, blackKingY + y)) {
////								System.err.println("Is valid");
////								sumx = blackKingX + x;
////								sumy = blackKingY + y;
//	//							System.err.println("New spot, X: " + sumx + " Y: " + sumy);
//								if(!isInCheck(false, blackKingX + x, blackKingY + y)) {
//	//								System.err.println("New spot is not check");
//									return false;
//								} 
////								else {
//	//								System.err.println("New spot is not in check");
////								}
//							}
//						}
//					}
//				}
//				
//			}
//			// second check to see if the another piece can move to protect the king or kill piece threatening the king
//			//TODO
//			// doesn't check to see if any allying pieces can move inbetween the king and threat piece yet
//			Piece pieceThreat = getPiece(threatX, threatY);
//			for(int i = 0; i < 16; i++) {
//				if(blackPieces[i] != null) {
//					if(blackPieces[i].isValidPath(this, blackPieces[i].getX(), blackPieces[i].getY(), threatX, threatY)) {
//						
//						//remove piece that can kill the white king
//						removePiece(blackPieces[i].getX(), blackPieces[i].getY());
//						//remove piece from the list to check for check
//						killPiece(threatX, threatY);
//						//remove piece that can kill the white king
//						removePiece(threatX, threatY);
//						putPiece(threatX, threatY, blackPieces[i]);
//						System.err.println("black piece: " + blackPieces[i]);
//						System.err.println(pieceThreat);
//						System.err.println("Piece at (6,5): " + getPiece(6, 5));
//						
////						if(!isInCheck(false, blackKingX, blackKingY)) {
////							System.err.println("caught here");
////							return false;
////						}
//						//TODO has to check to see if move is putting itself in check
//						
//						removePiece(threatX, threatY);
//						whitePieces[pieceThreat.getID()] = pieceThreat;
//						putPiece(blackPieces[i].getX(), blackPieces[i].getY(), blackPieces[i]);
//						putPiece(threatX, threatY, pieceThreat);
//						System.err.println("After change: Piece at (6,5): " + getPiece(6, 5));
////						System.err.println("threat piece: " + getPiece(threatX, threatY));
////						
////						System.out.println("Saving the king is: " + blackPieces[i]);
////						System.out.println("X: " + blackPieces[i].getX() + " Y: " + blackPieces[i].getY());
//						return false;
//					}
//				}
//			}
//			//if neither of the above return false, white is in checkmate
//			return true;
//		}
//	}
	
	/**
	 * Moves a piece from one spot on the board to another.
	 * 
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 */
	public void movePiece(int startX, int startY, int endX, int endY) { //move piece from one spot to another	
		if(board[startX][startY].isValidPath(this, startX, startY, endX, endY)) {
//			Piece pieceToMove = board[startX][startY];
//			Piece pieceKilled = board[endX][endY];
			
			if(isFull(endX, endY)) {
				killPiece(endX, endY);
				removePiece(endX, endY);
			}
			
			putPiece(endX, endY, board[startX][startY]);
			removePiece(startX,startY);			
			
			//update x and y values of the piece being moved
			board[endX][endY].setX(endX);
			board[endX][endY].setY(endY);
			if(board[endX][endY].hasMoved() == false) {
				board[endX][endY].setMoved();
			}
			//update x and y values of the king location
			if(this.getPiece(endX, endY).getType() == PieceType.KING) {
				if(this.getPiece(endX, endY).isWhite()) {
					whiteKingX = endX;
					whiteKingY = endY;
				} else {
					blackKingX = endX;
					blackKingY = endY;
				}
			}
		}
	}
	
	/**
	 * Returns a piece at a given location on the board
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece getPiece(int x, int y) {
		return board[x][y];
	}
}

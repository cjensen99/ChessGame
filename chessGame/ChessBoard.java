package chessGame;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

/**
 * Creates the gui for the chess board.
 * 
 * @author Cannon Jensen
 *
 */
@SuppressWarnings("serial")
public class ChessBoard extends JFrame {

	private JPanel contentPane;
	private int turnCounter = 0;
	private Board board;
	private int startRow = 0;
	private int startCol = 0;
	private int mouseClicks = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChessBoard frame = new ChessBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChessBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8, 8));
		
		createButtons();
		this.board = new Board();
		board.initializePieces();
		
	}
	
	// 1st click per turn
	private void mouse1(int position, JButton button) {
		int row = position / 8;
		int col = position % 8; 
		
		// it is white's turn
		if(turnCounter % 2 == 0) {
			//if selected piece is white
			if(board.getPiece(row, col) == null) JOptionPane.showConfirmDialog(null, "No piece found, please select a piece to move",
					"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			else if(board.getPiece(row, col).isWhite()) {
				this.startCol = col;
				this.startRow = row;
				this.mouseClicks = 1;
				button.setIcon(null);
			}
			//if selected piece is black
			else if(!board.getPiece(row, col).isWhite()){
				JOptionPane.showConfirmDialog(null, "It is not black's turn to move, please select a white piece to move",
						"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
			//if no piece is at the selected location
			else {
				JOptionPane.showConfirmDialog(null, "No piece found, please select a piece to move",
						"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		}
		// it is black's turn
		else {
			//if selected piece is black
			if(board.getPiece(row, col) == null) JOptionPane.showConfirmDialog(null, "No piece found, please select a piece to move",
					"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			else if (!board.getPiece(row, col).isWhite()) {
				this.startCol = col;
				this.startRow = row;
				this.mouseClicks = 1;
				button.setIcon(null);
			}
			//if selected piece is white
			else if(board.getPiece(row, col).isWhite()){
				JOptionPane.showConfirmDialog(null, "It is not white's turn to move, please select a black piece to move",
						"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
			//if no piece is at the selected location
			else {
				JOptionPane.showConfirmDialog(null, "No piece found, please select a piece to move",
						"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
	
	// 2nd click per turn
	private void mouse2(int position, JButton button) {
		int row = position / 8;
		int col = position % 8;
		
		if(board.getPiece(startRow, startCol).isValidPath(board, startRow, startCol, row, col)) {
			Piece piece = board.getPiece(startRow, startCol);
			if(piece.getType() == PieceType.PAWN && piece.isWhite()) setPawnImage(button);
			if(piece.getType() == PieceType.PAWN && !piece.isWhite()) setPawnImageB(button);
			if(piece.getType() == PieceType.ROOK && piece.isWhite()) setRookImage(button);
			if(piece.getType() == PieceType.ROOK && !piece.isWhite()) setRookImageB(button);
			if(piece.getType() == PieceType.KNIGHT && piece.isWhite()) setKnightImage(button);
			if(piece.getType() == PieceType.KNIGHT && !piece.isWhite()) setKnightImageB(button);
			if(piece.getType() == PieceType.BISHOP && piece.isWhite()) setBishopImage(button);
			if(piece.getType() == PieceType.BISHOP && !piece.isWhite()) setBishopImageB(button);
			if(piece.getType() == PieceType.KING && piece.isWhite()) setKingImage(button);
			if(piece.getType() == PieceType.KING && !piece.isWhite()) setKingImageB(button);
			if(piece.getType() == PieceType.QUEEN && piece.isWhite()) setQueenImage(button);
			if(piece.getType() == PieceType.QUEEN && !piece.isWhite()) setQueenImageB(button);
			
			// move piece in the game board
			if(row != startRow || col != startCol) {
				board.movePiece(startRow, startCol, row, col);
				turnCounter++;
			}
			this.mouseClicks = 0;
		}
		else {
			JOptionPane.showConfirmDialog(null, "Invalid move, please select a valid end point",
					"Invalid", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	private void setPawnImage(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
	}
	
	private void setPawnImageB(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
	}
	
	private void setRookImage(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wrook.png")));
	}
	
	private void setRookImageB(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/brook.png")));
	}
	
	private void setBishopImage(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wbishop.png")));
	}
	
	private void setBishopImageB(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bbishop.png")));
	}
	
	private void setKnightImage(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wknight.png")));
	}
	
	private void setKnightImageB(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bknight.png")));
	}
	
	private void setQueenImage(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wqueen.png")));
	}
	
	private void setQueenImageB(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bqueen.png")));
	}
	
	private void setKingImage(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wking.png")));
	}
	
	private void setKingImageB(JButton button) {
		button.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bking.png")));
	}

	private void createButtons() {
		JButton btnNewButton_0 = new JButton();
		btnNewButton_0.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/brook.png")));
		btnNewButton_0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(56, btnNewButton_0);
				else if(mouseClicks == 1) mouse2(56, btnNewButton_0);
			}
		});
		contentPane.add(btnNewButton_0);
		
		JButton btnNewButton_1 = new JButton();
		// position 57
		btnNewButton_1.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bknight.png")));
		btnNewButton_1.setBackground(new Color(139, 69, 19));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(57, btnNewButton_1);
				else if(mouseClicks == 1) mouse2(57, btnNewButton_1);
			}
		});
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton();
		// position 58
		btnNewButton_2.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bbishop.png")));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(58, btnNewButton_2);
				else if(mouseClicks == 1) mouse2(58, btnNewButton_2);
			}
		});
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton();
		// position 59
		btnNewButton_3.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bqueen.png")));
		btnNewButton_3.setBackground(new Color(139, 69, 19));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(59, btnNewButton_3);
				else if(mouseClicks == 1) mouse2(59, btnNewButton_3);
			}
		});
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton();
		// position 60
		btnNewButton_4.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bking.png")));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(60, btnNewButton_4);
				else if(mouseClicks == 1) mouse2(60, btnNewButton_4);
			}
		});
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton();
		// position 61
		btnNewButton_5.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bbishop.png")));
		btnNewButton_5.setBackground(new Color(139, 69, 19));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(61, btnNewButton_5);
				else if(mouseClicks == 1) mouse2(61, btnNewButton_5);
			}
		});
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton();
		// position 62
		btnNewButton_6.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bknight.png")));
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(62, btnNewButton_6);
				else if(mouseClicks == 1) mouse2(62, btnNewButton_6);
			}
		});
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton();
		// position 63
		btnNewButton_7.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/brook.png")));
		btnNewButton_7.setBackground(new Color(139, 69, 19));
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(63, btnNewButton_7);
				else if(mouseClicks == 1) mouse2(63, btnNewButton_7);
			}
		});
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton();
		// position 48
		btnNewButton_8.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_8.setBackground(new Color(139, 69, 19));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(48, btnNewButton_8);
				else if(mouseClicks == 1) mouse2(48, btnNewButton_8);
			}
		});
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton();
		// position 49
		btnNewButton_9.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(49, btnNewButton_9);
				else if(mouseClicks == 1) mouse2(49, btnNewButton_9);
			}
		});
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton();
		// position 50
		btnNewButton_10.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_10.setBackground(new Color(139, 69, 19));
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(50, btnNewButton_10);
				else if(mouseClicks == 1) mouse2(50, btnNewButton_10);
			}
		});
		contentPane.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton();
		// position 51
		btnNewButton_11.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(51, btnNewButton_11);
				else if(mouseClicks == 1) mouse2(51, btnNewButton_11);
			}
		});
		contentPane.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton();
		// position 52
		btnNewButton_12.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_12.setBackground(new Color(139, 69, 19));
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(52, btnNewButton_12);
				else if(mouseClicks == 1) mouse2(52, btnNewButton_12);
			}
		});
		contentPane.add(btnNewButton_12);
		
		JButton btnNewButton_13 = new JButton();
		// position 53
		btnNewButton_13.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(53, btnNewButton_13);
				else if(mouseClicks == 1) mouse2(53, btnNewButton_13);
			}
		});
		contentPane.add(btnNewButton_13);
		
		JButton btnNewButton_14 = new JButton();
		// position 54
		btnNewButton_14.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_14.setBackground(new Color(139, 69, 19));
		btnNewButton_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(54, btnNewButton_14);
				else if(mouseClicks == 1) mouse2(54, btnNewButton_14);
			}
		});
		contentPane.add(btnNewButton_14);
		
		JButton btnNewButton_15 = new JButton();
		// position 55
		btnNewButton_15.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/bpawn.png")));
		btnNewButton_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(55, btnNewButton_15);
				else if(mouseClicks == 1) mouse2(55, btnNewButton_15);
			}
		});
		contentPane.add(btnNewButton_15);
		
		JButton btnNewButton_16 = new JButton();
		// position 40
		btnNewButton_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(40, btnNewButton_16);
				else if(mouseClicks == 1) mouse2(40, btnNewButton_16);
			}
		});
		contentPane.add(btnNewButton_16);
		
		JButton btnNewButton_17 = new JButton();
		// position 41
		btnNewButton_17.setBackground(new Color(139, 69, 19));
		btnNewButton_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(41, btnNewButton_17);
				else if(mouseClicks == 1) mouse2(41, btnNewButton_17);
			}
		});
		contentPane.add(btnNewButton_17);
		
		JButton btnNewButton_18 = new JButton();
		// position 42
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(42, btnNewButton_18);
				else if(mouseClicks == 1) mouse2(42, btnNewButton_18);
			}
		});
		contentPane.add(btnNewButton_18);
		
		JButton btnNewButton_19 = new JButton();
		// position 43
		btnNewButton_19.setBackground(new Color(139, 69, 19));
		btnNewButton_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(43, btnNewButton_19);
				else if(mouseClicks == 1) mouse2(43, btnNewButton_19);
			}
		});
		contentPane.add(btnNewButton_19);
		
		JButton btnNewButton_20 = new JButton();
		// position 44
		btnNewButton_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(44, btnNewButton_20);
				else if(mouseClicks == 1) mouse2(44, btnNewButton_20);
			}
		});
		contentPane.add(btnNewButton_20);
		
		JButton btnNewButton_21 = new JButton();
		// position 45
		btnNewButton_21.setBackground(new Color(139, 69, 19));
		btnNewButton_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(45, btnNewButton_21);
				else if(mouseClicks == 1) mouse2(45, btnNewButton_21);
			}
		});
		contentPane.add(btnNewButton_21);
		
		JButton btnNewButton_22 = new JButton();
		// position 46
		btnNewButton_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(46, btnNewButton_22);
				else if(mouseClicks == 1) mouse2(46, btnNewButton_22);
			}
		});
		contentPane.add(btnNewButton_22);
		
		JButton btnNewButton_23 = new JButton();
		// position 47
		btnNewButton_23.setBackground(new Color(139, 69, 19));
		btnNewButton_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(47, btnNewButton_23);
				else if(mouseClicks == 1) mouse2(47, btnNewButton_23);
			}
		});
		contentPane.add(btnNewButton_23);
		
		JButton btnNewButton_24 = new JButton();
		// position 32
		btnNewButton_24.setBackground(new Color(139, 69, 19));
		btnNewButton_24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(32, btnNewButton_24);
				else if(mouseClicks == 1) mouse2(32, btnNewButton_24);
			}
		});
		contentPane.add(btnNewButton_24);
		
		JButton btnNewButton_25 = new JButton();
		// position 33
		btnNewButton_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(33, btnNewButton_25);
				else if(mouseClicks == 1) mouse2(33, btnNewButton_25);
			}
		});
		contentPane.add(btnNewButton_25);
		
		JButton btnNewButton_26 = new JButton();
		// position 34
		btnNewButton_26.setBackground(new Color(139, 69, 19));
		btnNewButton_26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(34, btnNewButton_26);
				else if(mouseClicks == 1) mouse2(34, btnNewButton_26);
			}
		});
		contentPane.add(btnNewButton_26);
		
		JButton btnNewButton_27 = new JButton();
		// position 35
		btnNewButton_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(35, btnNewButton_27);
				else if(mouseClicks == 1) mouse2(35, btnNewButton_27);
			}
		});
		contentPane.add(btnNewButton_27);
		
		JButton btnNewButton_28 = new JButton();
		// position 36
		btnNewButton_28.setBackground(new Color(139, 69, 19));
		btnNewButton_28.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(36, btnNewButton_28);
				else if(mouseClicks == 1) mouse2(36, btnNewButton_28);
			}
		});
		contentPane.add(btnNewButton_28);
		
		JButton btnNewButton_29 = new JButton();
		// position 37
		btnNewButton_29.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(37, btnNewButton_29);
				else if(mouseClicks == 1) mouse2(37, btnNewButton_29);
			}
		});
		contentPane.add(btnNewButton_29);
		
		JButton btnNewButton_30 = new JButton();
		// position 38
		btnNewButton_30.setBackground(new Color(139, 69, 19));
		btnNewButton_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(38, btnNewButton_30);
				else if(mouseClicks == 1) mouse2(38, btnNewButton_30);
			}
		});
		contentPane.add(btnNewButton_30);
		
		JButton btnNewButton_31 = new JButton();
		// position 39
		btnNewButton_31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(39, btnNewButton_31);
				else if(mouseClicks == 1) mouse2(39, btnNewButton_31);
			}
		});
		contentPane.add(btnNewButton_31);
		
		JButton btnNewButton_32 = new JButton();
		// position 24
		btnNewButton_32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(24, btnNewButton_32);
				else if(mouseClicks == 1) mouse2(24, btnNewButton_32);
			}
		});
		contentPane.add(btnNewButton_32);
		
		JButton btnNewButton_33 = new JButton();
		// position 25
		btnNewButton_33.setBackground(new Color(139, 69, 19));
		btnNewButton_33.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(25, btnNewButton_33);
				else if(mouseClicks == 1) mouse2(25, btnNewButton_33);
			}
		});
		contentPane.add(btnNewButton_33);
		
		JButton btnNewButton_34 = new JButton();
		// position 26
		btnNewButton_34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(26, btnNewButton_34);
				else if(mouseClicks == 1) mouse2(26, btnNewButton_34);
			}
		});
		contentPane.add(btnNewButton_34);
		
		JButton btnNewButton_35 = new JButton();
		// position 27
		btnNewButton_35.setBackground(new Color(139, 69, 19));
		btnNewButton_35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(27, btnNewButton_35);
				else if(mouseClicks == 1) mouse2(27, btnNewButton_35);
			}
		});
		contentPane.add(btnNewButton_35);
		
		JButton btnNewButton_36 = new JButton();
		// position 28
		btnNewButton_36.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(28, btnNewButton_36);
				else if(mouseClicks == 1) mouse2(28, btnNewButton_36);
			}
		});
		contentPane.add(btnNewButton_36);
		
		JButton btnNewButton_37 = new JButton();
		// position 29
		btnNewButton_37.setBackground(new Color(139, 69, 19));
		btnNewButton_37.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(29, btnNewButton_37);
				else if(mouseClicks == 1) mouse2(29, btnNewButton_37);
			}
		});
		contentPane.add(btnNewButton_37);
		
		JButton btnNewButton_38 = new JButton();
		// position 30
		btnNewButton_38.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(30, btnNewButton_38);
				else if(mouseClicks == 1) mouse2(30, btnNewButton_38);
			}
		});
		contentPane.add(btnNewButton_38);
		
		JButton btnNewButton_39 = new JButton();
		// position 31
		btnNewButton_39.setBackground(new Color(139, 69, 19));
		btnNewButton_39.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(31, btnNewButton_39);
				else if(mouseClicks == 1) mouse2(31, btnNewButton_39);
			}
		});
		contentPane.add(btnNewButton_39);
		
		JButton btnNewButton_40 = new JButton();
		// position 16
		btnNewButton_40.setBackground(new Color(139, 69, 19));
		btnNewButton_40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(16, btnNewButton_40);
				else if(mouseClicks == 1) mouse2(16, btnNewButton_40);
			}
		});
		contentPane.add(btnNewButton_40);
		
		JButton btnNewButton_41 = new JButton();
		// position 17
		btnNewButton_41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(17, btnNewButton_41);
				else if(mouseClicks == 1) mouse2(17, btnNewButton_41);
			}
		});
		contentPane.add(btnNewButton_41);
		
		JButton btnNewButton_42 = new JButton();
		// position 18
		btnNewButton_42.setBackground(new Color(139, 69, 19));
		btnNewButton_42.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(18, btnNewButton_42);
				else if(mouseClicks == 1) mouse2(18, btnNewButton_42);
			}
		});
		contentPane.add(btnNewButton_42);
		
		JButton btnNewButton_43 = new JButton();
		btnNewButton_43.setIcon(null);
		// position 19
		btnNewButton_43.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(19, btnNewButton_43);
				else if(mouseClicks == 1) mouse2(19, btnNewButton_43);
			}
		});
		contentPane.add(btnNewButton_43);
		
		JButton btnNewButton_44 = new JButton();
		// position 20
		btnNewButton_44.setBackground(new Color(139, 69, 19));
		btnNewButton_44.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(20, btnNewButton_44);
				else if(mouseClicks == 1) mouse2(20, btnNewButton_44);
			}
		});
		contentPane.add(btnNewButton_44);
		
		JButton btnNewButton_45 = new JButton();
		// position 21
		btnNewButton_45.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(21, btnNewButton_45);
				else if(mouseClicks == 1) mouse2(21, btnNewButton_45);
			}
		});
		contentPane.add(btnNewButton_45);
		
		JButton btnNewButton_46 = new JButton();
		// position 22
		btnNewButton_46.setBackground(new Color(139, 69, 19));
		btnNewButton_46.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(22, btnNewButton_46);
				else if(mouseClicks == 1) mouse2(22, btnNewButton_46);
			}
		});
		contentPane.add(btnNewButton_46);
		
		JButton btnNewButton_47 = new JButton();
		// position 23
		btnNewButton_47.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(23, btnNewButton_47);
				else if(mouseClicks == 1) mouse2(23, btnNewButton_47);
			}
		});
		contentPane.add(btnNewButton_47);
		
		JButton btnNewButton_48 = new JButton();
		// position 8
		btnNewButton_48.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_48.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(8, btnNewButton_48);
				else if(mouseClicks == 1) mouse2(8, btnNewButton_48);
			}
		});
		contentPane.add(btnNewButton_48);
		
		JButton btnNewButton_49 = new JButton();
		// position 9
		btnNewButton_49.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_49.setBackground(new Color(139, 69, 19));
		btnNewButton_49.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(9, btnNewButton_49);
				else if(mouseClicks == 1) mouse2(9, btnNewButton_49);
			}
		});
		contentPane.add(btnNewButton_49);
		
		JButton btnNewButton_50 = new JButton();
		// position 10
		btnNewButton_50.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_50.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(10, btnNewButton_50);
				else if(mouseClicks == 1) mouse2(10, btnNewButton_50);
			}
		});
		contentPane.add(btnNewButton_50);
		
		JButton btnNewButton_51 = new JButton();
		// position 11
		btnNewButton_51.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_51.setBackground(new Color(139, 69, 19));
		btnNewButton_51.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(11, btnNewButton_51);
				else if(mouseClicks == 1) mouse2(11, btnNewButton_51);
			}
		});
		contentPane.add(btnNewButton_51);
		
		JButton btnNewButton_52 = new JButton();
		// position 12
		btnNewButton_52.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_52.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(12, btnNewButton_52);
				else if(mouseClicks == 1) mouse2(12, btnNewButton_52);
			}
		});
		contentPane.add(btnNewButton_52);
		
		JButton btnNewButton_53 = new JButton();
		// position 13
		btnNewButton_53.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_53.setBackground(new Color(139, 69, 19));
		btnNewButton_53.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(13, btnNewButton_53);
				else if(mouseClicks == 1) mouse2(13, btnNewButton_53);
			}
		});
		contentPane.add(btnNewButton_53);
		
		JButton btnNewButton_54 = new JButton();
		// position 14
		btnNewButton_54.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_54.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(14, btnNewButton_54);
				else if(mouseClicks == 1) mouse2(14, btnNewButton_54);
			}
		});
		contentPane.add(btnNewButton_54);
		
		JButton btnNewButton_55 = new JButton();
		// position 15
		btnNewButton_55.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wpawn.png")));
		btnNewButton_55.setBackground(new Color(139, 69, 19));
		btnNewButton_55.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(15, btnNewButton_55);
				else if(mouseClicks == 1) mouse2(15, btnNewButton_55);
			}
		});
		contentPane.add(btnNewButton_55);
		
		JButton btnNewButton_56 = new JButton();
		// position 0
		btnNewButton_56.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wrook.png")));
		btnNewButton_56.setBackground(new Color(139, 69, 19));
		btnNewButton_56.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(0, btnNewButton_56);
				else if(mouseClicks == 1) mouse2(0, btnNewButton_56);
			}
		});
		contentPane.add(btnNewButton_56);
		
		JButton btnNewButton_57 = new JButton();
		// position 1
		btnNewButton_57.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wknight.png")));
		btnNewButton_57.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(1, btnNewButton_57);
				else if(mouseClicks == 1) mouse2(1, btnNewButton_57);
			}
		});
		contentPane.add(btnNewButton_57);
		
		JButton btnNewButton_58 = new JButton();
		// position 2
		btnNewButton_58.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wbishop.png")));
		btnNewButton_58.setBackground(new Color(139, 69, 19));
		btnNewButton_58.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(2, btnNewButton_58);
				else if(mouseClicks == 1) mouse2(2, btnNewButton_58);
			}
		});
		contentPane.add(btnNewButton_58);
		
		JButton btnNewButton_59 = new JButton();
		// position 3
		btnNewButton_59.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wking.png")));
		btnNewButton_59.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(3, btnNewButton_59);
				else if(mouseClicks == 1) mouse2(3, btnNewButton_59);
			}
		});
		contentPane.add(btnNewButton_59);
		
		JButton btnNewButton_60 = new JButton();
		// position 4
		btnNewButton_60.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wqueen.png")));
		btnNewButton_60.setBackground(new Color(139, 69, 19));
		btnNewButton_60.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(4, btnNewButton_60);
				else if(mouseClicks == 1) mouse2(4, btnNewButton_60);
			}
		});
		contentPane.add(btnNewButton_60);
		
		JButton btnNewButton_61 = new JButton();
		// position 5
		btnNewButton_61.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wbishop.png")));
		btnNewButton_61.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(5, btnNewButton_61);
				else if(mouseClicks == 1) mouse2(5, btnNewButton_61);
			}
		});
		contentPane.add(btnNewButton_61);
		
		JButton btnNewButton_62 = new JButton();
		// position 6
		btnNewButton_62.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wknight.png")));
		btnNewButton_62.setBackground(new Color(139, 69, 19));
		btnNewButton_62.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(6, btnNewButton_62);
				else if(mouseClicks == 1) mouse2(6, btnNewButton_62);
			}
		});
		contentPane.add(btnNewButton_62);
		
		JButton btnNewButton_63 = new JButton();
		// position 7
		btnNewButton_63.setIcon(new ImageIcon(ChessBoard.class.getResource("/chessGame/chessImages/wrook.png")));
		btnNewButton_63.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mouseClicks == 0) mouse1(7, btnNewButton_63);
				else if(mouseClicks == 1) mouse2(7, btnNewButton_63);
			}
		});
		contentPane.add(btnNewButton_63);
	}
}

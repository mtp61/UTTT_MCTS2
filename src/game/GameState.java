package game;

import java.awt.Color;
import java.awt.Graphics;

public class GameState {
	private int[][] board;  // the game board, first dim is sub board, second is position within subboard
	private int[] bigBoard;  // 
	private int toMove;  // 1 or -1
	private int moveBoard;  // subgame for next move, -1 is anywhere
	
	public GameState() {
		// create empty boards
		board = new int[9][9];
		bigBoard = new int[9];
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				board[i][j] = 0;
			}
			bigBoard[i] = 0;
		}
		toMove = 1;
		moveBoard = -1;
		
		// TODO testing
		board[0][0] = 1;
		board[1][0] = 1;
		board[0][1] = 1;
		board[0][2] = 1;
		board[0][3] = 1;
		board[0][5] = -1;
	}

	public GameState(int[][] board, int[] bigBoard, int toMove, int moveBoard) {
		this.board = board;
		this.bigBoard = bigBoard;
		this.toMove = toMove;
		this.moveBoard = moveBoard;
	}
	
	public void draw(Graphics g, int x, int y, int width, int height) {
		int subBoardOffset = 10;
		int markSpace = 10;  // TODO move these
		
		// draw big board
		g.setColor(Color.WHITE);
		for (int i = 1; i < 3; ++i) {
			g.drawLine(i * width / 3 + x,
					y, 
					i * width / 3 + x,
					height + y);
			g.drawLine(x, 
					i * height / 3 + y, 
					x + width, 
					i * height / 3 + y);

		}
		
		// draw sub boards
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				// for each sub board
				// lines
				for (int k = 1; k < 3; ++k) {
					g.drawLine(k * width / 9 + j * width / 3 + x,
							i * height / 3 + subBoardOffset + y,
							k * width / 9 + j * width / 3 + x,
							height / 3 + i * height / 3 - subBoardOffset + y);
					g.drawLine(
							j * width / 3 + subBoardOffset + x,
							k * height / 9 + i * height / 3 + y,
							width / 3 + j * width / 3 - subBoardOffset + x,
							k * height / 9 + i * height / 3 + y);

				}
					
				// moves
				// traverse sub board
				for (int k = 0; k < 3; ++k) {
					for (int l = 0; l < 3; ++l) {
						if (board[i + 3 * j][k + 3 * l] == 1) {
							g.setColor(Color.RED);
							g.drawOval(i * width / 3 + k * width / 9 + markSpace + x,
									j * height / 3 + l * height / 9 + markSpace + y,
									width / 9 - 2 * markSpace,
									height / 9 - 2 * markSpace);
							g.setColor(Color.WHITE);
						} else if (board[i + 3 * j][k + 3 * l] == -1) {
							g.setColor(Color.CYAN);
							g.drawOval(i * width / 3 + k * width / 9 + markSpace + x,
									j * height / 3 + l * height / 9 + markSpace + y,
									width / 9 - 2 * markSpace,
									height / 9 - 2 * markSpace);
							g.setColor(Color.WHITE);
							// TODO cross instead of circle
						}
					}
				}
				
				// TODO
			}
		}
		
		// draw coordinates
		
		// TODO
	}
	
	// returns 1 if p1 wins, -1 if p2 wins, 10 if all cells are filled but no win, 0 otherwise	
	public static int check(int[] board) {
		// check for a winner
		int s1, s2;
		for (int i = 0; i < 3; ++i) {
			s1 = 0;
			s2 = 0;
			for (int j = 0; j < 3; ++j) {
				s1 += board[3 * i + j];
				s2 += board[i + 3 * j];
			}
			
			if (s1 == 3 || s2 == 3) {
				return 1;
			} else if (s1 == -3 || s2 == -3) {
				return -1;
			}
		}
		
		s1 = board[0] + board[4] + board[8];
		s2 = board[2] + board[4] + board[6];
		if (s1 == 3 || s2 == 3) {
			return 1;
		} else if (s1 == -3 || s2 == -3) {
			return -1;
		}
		
		// check if board filled
		// TODO ?
		for (int i = 0; i < 9; ++i) {
			if (board[i] == 0) {
				return 0; 
			}
		}
		return 10;
	}
}

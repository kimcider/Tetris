package tetris;

import javax.swing.*;
import java.awt.*;
import static tetris.Main.BLOCK_SIZE;
import static tetris.Main.BOARD_HEIGHT;
import static tetris.Main.BOARD_WIDTH;

import java.awt.*;
public class Board extends JPanel{
	private block[][] board;
	public Board() {
		setVisible(true);
		setSize(BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
		board = new block[BOARD_HEIGHT][BOARD_WIDTH];
		for(int i=0;i<BOARD_HEIGHT;i++) {
			for(int j=0;j<BOARD_WIDTH;j++) {
				board[i][j] = new block();
			}
		}
	}
	public void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			for(int j = 0; j < BOARD_WIDTH; j++) {
				g.setColor(board[i][j].getType().getColor());
				g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

				/* 격자 선그리기 */
				g.setColor(new Color(150,75,0));
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
				x = x + BLOCK_SIZE;
			}
			x = 0;
			y = y + BLOCK_SIZE;
		}
	}
	
	class block {
		private MinoType type;
		Color color;
		public block() {
			type = MinoType.EMPTY;
			color = MinoType.EMPTY.getColor();
		}
		
		public void setType(MinoType type) {
			this.type = type;
		}
		
		public boolean isFilled() {
			if(type == MinoType.EMPTY) {
				return false;
			}else {
				return true;
			}
		}
		public MinoType getType() {
			return type;
		}
	}
}

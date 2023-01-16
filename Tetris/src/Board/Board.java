package Board;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import Mino.MinoType;
import static Tetris.Main.*;

public class Board extends JPanel{
	private block[][] board;
	public Board() {
		setVisible(true);
		setSize(BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
		setBounds(BOARD_START_WIDTH,BOARD_START_HEIGHT,getWidth(),getHeight());
		board = new block[BOARD_WIDTH][BOARD_HEIGHT];
		for(int i=0;i<BOARD_WIDTH;i++) {
			for(int j=0;j<BOARD_HEIGHT;j++) {
				board[i][j] = new block();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		int y = 0;
		int x = 0;
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				g.setColor(board[i][j].getType().getColor());
				g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);

				/* 격자 선그리기 */
				g.setColor(new Color(150,75,0));
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
				y = y + BLOCK_SIZE;
			}
			y = 0;
			x = x + BLOCK_SIZE;
		}
	}

	/*
	 * 각 baseMino들이 요구한 위치에 그들을 위치시킬 수 있는지 여부를 파악. 
	 * 모든 baseMino를 그들이 요구한 위치에 위치시킬 수 있다면 true를, 없다면 false를 반환. 
	 */
	public boolean checkToMove(int[][] position) {
		boolean answer = true;
		
		for(int i = 0;i < 4; i++) {
			if(position[i][0] >= BOARD_WIDTH || position[i][0] < 0) {
				System.out.println();
				return false;
			}
			if(position[i][1] > BOARD_HEIGHT - 1 || position[i][1] < 0) {
				System.out.println();
				return false;
			}
		}
		System.out.println();
		
		return answer;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/*******************************************  test ****************************************************************/
	public void testBoard1() {
		
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


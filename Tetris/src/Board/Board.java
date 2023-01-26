package Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import Mino.Mino;
import Mino.MinoType;
import static Tetris.Main.*;

public class Board extends JPanel{
	private block[][] board;//게임보드
	private int topY;// 현재 가장 높이 쌓인 블록의 위치.

	
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
		topY = BOARD_HEIGHT + 10;
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
		
		int boardX;
		int boardY;
		for(int i = 0;i < 4; i++) {

			if(position[i][0] >= BOARD_WIDTH || position[i][0] < 0) {
				return false;
			}
			if(position[i][1] > BOARD_HEIGHT - 1 || position[i][1] < 0) {
				return false;
			}
			
			boardX = position[i][0];
			boardY = position[i][1];
			if(board[boardX][boardY].isFilled()) {
				return false;
			}
		}
		return answer;
	}
	
	
	/*
	 * mino를 board에 확정시키는 작업.
	 */
	private int erasedLineCounter;
	public int stack(Mino mino, int x, int y, int rotate) {
		erasedLineCounter = 0;
		int position[][] = mino.getPosition(x, y, rotate);
		
		int boardX;
		int boardY;

		ArrayList<Integer> listLine = new ArrayList<>(Arrays.asList());
		
		MinoType minoType = mino.getType();
		for(int i = 0; i < 4; i++) {
			boardX = position[i][0];
			boardY = position[i][1];
			board[boardX][boardY].setType(minoType);
			listLine.add(boardY);
		}

		listLine.sort(Comparator.naturalOrder());
		
		if(listLine.get(0) < topY) { 
			// 가장 높이 쌓인 블록위치 바꾸기. 
			topY = listLine.get(0);
		}
		lineFull(listLine);
		return erasedLineCounter;
	}
	
	/*
	 * TODO: lineFull()함수 
	 * 
	 * 	stack함수시 stack된 라인에서 발동. 
	 */
	public void lineFull(ArrayList<Integer> list) {
		int beforeNumber = -99; 
		for(int i = 0; i < list.size(); i++) {
			int number = list.get(i);
			if(beforeNumber != number) {
				boolean state = true;
				
				for(int j = 0; j < BOARD_WIDTH; j++) {
					if(board[j][number].isFilled() == true) {
						continue;
					}else {
						state = false;
						break;
					}
				}
				
				if(state == true) {
					eraseLine(number);
					erasedLineCounter++;
				}	
				beforeNumber = number;
			}
		}
	}
	/*
	 * 	lineFull()함수가 full일경우 발동. 
	 */
	public void eraseLine(int targetY) {
		for(int i = 0; i < BOARD_WIDTH; i++) {
			board[i][targetY].setType(MinoType.EMPTY);
		}
		
//		for(int y = targetY; y >= topY + 1; y--) {
		for(int y = targetY; y >= topY + 1; y--) {
			for(int x = 0; x < BOARD_WIDTH; x++) {
				/* 
				 * TODO: 한쪽이 맥스까지 쌓여있어서 board[x][-1]에서 받아올 경우의 수 생각하기.  
				 */
				board[x][y].setType(board[x][y - 1].getType());
			}
		}
		for(int i = 0; i < BOARD_WIDTH; i++) {
			board[i][topY].setType(MinoType.EMPTY);
		}
		topY = topY + 1;
		repaint();
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


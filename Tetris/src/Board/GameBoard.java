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

public class GameBoard extends JPanel{
	private block[][] gameBoard;
	private int stackedHighestY;
	
	public GameBoard() {
		setVisible(true);
		setSize(BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
		setBounds(BOARD_START_WIDTH,BOARD_START_HEIGHT,getWidth(),getHeight());
		
		gameBoard = new block[BOARD_WIDTH][BOARD_HEIGHT];
		for(int i=0;i<BOARD_WIDTH;i++) {
			for(int j=0;j<BOARD_HEIGHT;j++) {
				gameBoard[i][j] = new block();
			}
		}
		stackedHighestY = BOARD_HEIGHT +9999;
	}
	
	public void paintComponent(Graphics g) {
		int y = 0;
		int x = 0;
		for(int i = 0; i < BOARD_WIDTH; i++) {
			for(int j = 0; j < BOARD_HEIGHT; j++) {
				/* block 그리기 */
				g.setColor(gameBoard[i][j].getType().getColor());
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


	public boolean canMinoMove(int[][] position) {
		int boardXPosition;
		int boardYPosition;
		for(int i = 0;i < 4; i++) {
			
			if(position[i][0] >= BOARD_WIDTH || position[i][0] < 0) {
				return false;
			}
			if(position[i][1] > BOARD_HEIGHT - 1 || position[i][1] < 0) {
				return false;
			}
			
			boardXPosition = position[i][0];
			boardYPosition = position[i][1];
			if(gameBoard[boardXPosition][boardYPosition].isFilled()) {
				return false;
			}
		}
		return true;
	}
	
	private int erasedLineCounter;
	public int stackMinoToBoard(Mino mino) {
		erasedLineCounter = 0;
		int position[][] = mino.getPosition(mino.getX(), mino.getY(), mino.getRotation());
		
		int xPosition;
		int yPosition;

		ArrayList<Integer> lines = new ArrayList<>(Arrays.asList());
		
		for(int i = 0; i < 4; i++) {
			xPosition = position[i][0];
			yPosition = position[i][1];
			gameBoard[xPosition][yPosition].setType(mino.getType());
			lines.add(yPosition);
		}

		lines.sort(Comparator.naturalOrder());
		
		if(lines.get(0) < stackedHighestY) { 
			stackedHighestY = lines.get(0);
		}
		checkIsLineFull(lines);
		return erasedLineCounter;
	}
	
	public void checkIsLineFull(ArrayList<Integer> list) {
		int beforeNumber = -9999; 
		for(int i = 0; i < list.size(); i++) {
			int number = list.get(i);
			if(beforeNumber != number) {
				boolean flagOfFullLine = true;
				
				for(int j = 0; j < BOARD_WIDTH; j++) {
					if(gameBoard[j][number].isFilled() == false) {
						flagOfFullLine = false;
						break;
					}
				}
				
				if(flagOfFullLine == true) {
					eraseLine(number);
					erasedLineCounter++;
				}	
				beforeNumber = number;
			}
		}
	}

	public void eraseLine(int targetLine) {
		for(int y = targetLine; y >= stackedHighestY + 1; y--) {
			for(int x = 0; x < BOARD_WIDTH; x++) {
				gameBoard[x][y].setType(gameBoard[x][y - 1].getType());
			}
		}
		for(int i = 0; i < BOARD_WIDTH; i++) {
			gameBoard[i][stackedHighestY].setType(MinoType.EMPTY);
		}
		stackedHighestY = stackedHighestY + 1;
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


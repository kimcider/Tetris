package Board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import Tetris.Point;
import Mino.Mino;
import Mino.MinoType;
import Mino.WallKick;

import static Tetris.Main.*;
import static Tetris.Control.*;

public class GameBoard extends JPanel{
	private block[][] gameBoard;
	private int stackedHighestY;
	
	public GameBoard() {
		setVisible(true);
		setSize(BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
		setBounds(BOARD_START_WIDTH,BOARD_START_HEIGHT,getWidth(),getHeight());
		
		initilizeGameBoard();
		stackedHighestY = BOARD_HEIGHT +9999;
	}
	
	private void initilizeGameBoard() {
		gameBoard = new block[BOARD_WIDTH][BOARD_HEIGHT];
		for(int i=0;i<BOARD_WIDTH;i++) {
			for(int j=0;j<BOARD_HEIGHT;j++) {
				gameBoard[i][j] = new block();
			}
		}
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

	public boolean isEmptySpaces(Point[] baseMinosPoint) {
		for(int i = 0;i < 4; i++) {
			
			if(baseMinosPoint[i].getX() >= BOARD_WIDTH || baseMinosPoint[i].getX() < 0) {
				return false;
			}
			if(baseMinosPoint[i].getY() > BOARD_HEIGHT - 1 || baseMinosPoint[i].getY() < 0) {
				return false;
			}
			
			if(gameBoard[baseMinosPoint[i].getX()][baseMinosPoint[i].getY()].isFilled()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canMinoMove(Mino mino, int direction) {
		int xVector = 0;
		int yVector = 0;
		
		switch(direction) {
		case DOWN:
			yVector = 1;
			break;
		default:
			xVector = direction;
			break;
		}
		
		Point[] baseMinoPoints = mino.getBaseMinosPoints(mino.getPoint().getX() + xVector, mino.getPoint().getY() + yVector, mino.getRotation());
		
		boolean canMinoMove = isEmptySpaces(baseMinoPoints);
		return canMinoMove;
	}

	public Point canMinoRotate(Mino mino, int rotate) {
		Point rotatablePoint = null;
		Point[] rotationOffset = getRotationOffset(mino, rotate);
		
		for(int i = 0; i < rotationOffset.length; i++) {
			int offsetX = rotationOffset[i].getX();
			int offsetY = -rotationOffset[i].getY(); //음수를 취하는 이유는 WallKick클래스의 주석 참조. 
			
			rotatablePoint = new Point(mino.getPoint().getX() + offsetX, mino.getPoint().getY() + offsetY);
			
			Point[] baseMinoPoints = mino.getBaseMinosPoints(rotatablePoint.getX(), rotatablePoint.getY(), (mino.getRotation() + 4 + rotate) % 4);
			
			boolean canMinoRotate = isEmptySpaces(baseMinoPoints);
			
			if(canMinoRotate == true) {
				break;
			}
			else {
				rotatablePoint = null;
			}
		}
		return rotatablePoint;
	}
	
	public Point[] getRotationOffset(Mino mino, int rotate) {
		Point[] rotationOffset;
		if(rotate == RIGHT) {
			rotationOffset = WallKick.getRotationOffset(mino.getType(), mino.getRotation(), 0);
		}
		else {
			rotationOffset = WallKick.getRotationOffset(mino.getType(), mino.getRotation(), 1);
		}
		return rotationOffset;
	}
	
	
	public int stackMinoToBoard(Mino mino) {
		int erasedLineCounter = 0;
		Point baseMinoPoints[] = mino.getBaseMinosPoints(mino.getPoint().getX(), mino.getPoint().getY(), mino.getRotation());

		ArrayList<Integer> lines = new ArrayList<>(Arrays.asList());
		
		for(int i = 0; i < 4; i++) {
			int x = baseMinoPoints[i].getX();
			int y = baseMinoPoints[i].getY();
			gameBoard[x][y].setType(mino.getType());
			lines.add(y);
		}

		lines.sort(Comparator.naturalOrder());
		
		if(lines.get(0) < stackedHighestY) { 
			stackedHighestY = lines.get(0);
		}
		erasedLineCounter = checkIfLineIsFull(lines);
		return erasedLineCounter;
	}
	
	public int checkIfLineIsFull(ArrayList<Integer> list) {
		int erasedLineCounter = 0;
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
		return erasedLineCounter;
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
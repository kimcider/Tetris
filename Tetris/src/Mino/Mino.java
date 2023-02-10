package Mino;

import javax.swing.JPanel;
import Tetris.Point;
import static Tetris.Main.*;
import static Tetris.Control.*;
import Board.GameBoard;

/*
 * Mino는 4개의 baseMino객체로 구성된다.
 * Mino객체 각각은 JPanel이 아니며, BaseMino객체 각각이 JPanel이다. 
 * 
 * Mino의 위치는 기준이 되는 basePoint정보와 각각의 baseMino들의 상대적인 위치에 의해 결정된다. 
 * baseMino의 상대적 위치는 enum MinoType의 getBaseMinosRelativePoint()함수를 통해 얻는다.
 */
public class Mino {
	private static final int MINO_INITIAL_X_POSITION = 5;
	private static final int MINO_INITIAL_Y_POSITION = 0;
	
	private GameBoard gameBoard;
	
	private MinoType type;
	private int rotation;
	private BaseMino[] mino = new BaseMino[4];
	private Point basePoint;
	
	
	public Mino(GameBoard gameBoard, MinoType type) {
		this.gameBoard = gameBoard;
		
		this.type = type;
		rotation = 0;
		basePoint = new Point(MINO_INITIAL_X_POSITION, MINO_INITIAL_Y_POSITION);
		
		Point[] relativepoints = MinoType.EMPTY.getBaseMinosRelativePoint(type, 0);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(type);
			mino[i].setRelativePoint(relativepoints[i]);
		}
	}
	
	public BaseMino getBaseMino(int num) {
		return mino[num];
	}
	
	public Point[] getBaseMinosPoints(int baseX, int baseY, int rotate){
		Point[] relativePoints = type.getBaseMinosRelativePoint(getType(), rotate);
		Point[] baseMinoPoints = new Point[4];
		for(int i = 0; i < 4; i++) {
			baseMinoPoints[i] = new Point(baseX + relativePoints[i].getX(), baseY + relativePoints[i].getY());
		}
		return baseMinoPoints;
	}
	
	public MinoType getType() {
		return type;
	}
	
	public Point getPoint() {
		return basePoint;
	}
	
	private void setPoint(int x, int y) {
		basePoint.setPoint(x, y);
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(x * BLOCK_SIZE + mino[i].getRelativePoint().getX() * BLOCK_SIZE + 1,y * BLOCK_SIZE + mino[i].getRelativePoint().getY() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	
	public int getRotation() {
		return rotation;
	}


	public void moveMino(int direction) {
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
		setPoint(basePoint.getX() + xVector, basePoint.getY() + yVector);
	}	
	public void rotateMino(Point rotatablePoint, int direction) {
		if(rotatablePoint != null) {
			rotation = (rotation + direction + 4) % 4;
			Point[] relativePoint = MinoType.EMPTY.getBaseMinosRelativePoint(type, rotation);
			for(int i = 0; i < 4; i++) {
				mino[i].setRelativePoint(relativePoint[i]);
			}
			setPoint(rotatablePoint.getX(), rotatablePoint.getY());
		}
	}
	
	public void addMinoToGameBoard() {
		setPoint(MINO_INITIAL_X_POSITION, MINO_INITIAL_Y_POSITION);
		for(int i = 0; i < 4; i++) {
			gameBoard.add(getBaseMino(i));
		}
		gameBoard.repaint();
	}
	
	public void addMinoToSaveBoard(JPanel board, int x, int y) {
		setBaseMinoBoundsForOtherBoard(x, y);
		for(int i = 0;i < 4; i++) {
			board.add(getBaseMino(i));
		}
		board.repaint();
	}
	
	public void addMinoToNextMinoBoard(JPanel board, int x, int y, int waitNumber) {
		setBaseMinoBoundsForOtherBoard(x, y + (waitNumber * 4));
		for(int i = 0;i < 4; i++) {
			board.add(getBaseMino(i));
		}
		board.repaint();
	}
	
	public void setBaseMinoBoundsForOtherBoard(int x, int y) {
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(x * OTHER_BOARD_BLOCK_SIZE + mino[i].getRelativePoint().getX() * OTHER_BOARD_BLOCK_SIZE + 1,y * OTHER_BOARD_BLOCK_SIZE + mino[i].getRelativePoint().getY() * OTHER_BOARD_BLOCK_SIZE + 1, OTHER_BOARD_BLOCK_SIZE - 2,OTHER_BOARD_BLOCK_SIZE - 2);
		}
	}
	
	public void removeMinoFromBoard(JPanel board) {
		for(int i = 0; i < 4; i++) {
			board.remove(getBaseMino(i));
		}
	}
}

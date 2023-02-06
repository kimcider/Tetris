package Mino;

import javax.swing.JPanel;
import static Tetris.Main.*;
import static Tetris.Control.*;
import Board.GameBoard;

/*
 * Mino는 4개의 baseMino객체로 구성된다.
 * Mino객체 각각은 JPanel이 아니며, BaseMino객체 각각이 JPanel이다. 
 * 
 * Mino의 위치는 기준이 되는 위치인 baseXPosition, baseYPosition정보와 각각의 baseMino들의 상대적인 위치에 의해 결정된다. 
 * BaseMino의 상대적 위치는 enum MinoType의 getBaseMinosRelativePositions()함수를 통해 받아올 수 있다. 
 */
public class Mino {
	private GameBoard gameBoard;
	private BaseMino[] mino = new BaseMino[4];
	private int baseXPosition;
	private int baseYPosition;
	private int rotation;
	private MinoType type;
	
	public Mino(GameBoard gameBoard, MinoType type) {
		this.gameBoard = gameBoard;
		this.type = type;
		baseXPosition = MINO_INITIAL_X_POSITION;
		baseYPosition = MINO_INITIAL_Y_POSITION;
		rotation = 0;
		
		int[][] relativePosition = MinoType.EMPTY.getBaseMinosRelativePositions(type,0);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(type);
			mino[i].setRelativePosition(relativePosition[i]);
		}
	}
	
	public BaseMino getBaseMino(int num) {
		return mino[num];
	}
	
	/*
	 * return baseMinoPositions[a][b]
	 * 	a: baseMino number
	 * 	b: x, y좌표
	 * 		b == 0: x좌표
	 * 		b == 1: y좌표  
	 */
	public int[][] getBaseMinoPositions(int baseXPosition, int baseYPosition, int rotate){
		int[][] relativePosition = type.getBaseMinosRelativePositions(getType(), rotate);
		int [][] baseMinoPositions = new int[4][2];
		for(int i = 0; i < 4; i++) {
			baseMinoPositions[i][0] = baseXPosition + relativePosition[i][0];
			baseMinoPositions[i][1] = baseYPosition + relativePosition[i][1];
		}
		return baseMinoPositions;
	}
	
	public MinoType getType() {
		return type;
	}
	
	public int getX() {
		return baseXPosition;
	}
	public int getY() {
		return baseYPosition;
	}
	
	public int getRotation() {
		return rotation;
	}

	public void rotateMino(int rotate) {
		int[][] rotationOffset;
		int testingX;
		int testingY;
		
		if(rotate == RIGHT) {
			rotationOffset = WallKick.getRotationOffset(type, rotation, 0);
		}
		else {
			rotationOffset = WallKick.getRotationOffset(type, rotation, 1);
		}
		
		for(int i = 0; i < 5; i++) {	
			int offsetX = rotationOffset[i][0];
			int offsetY = -rotationOffset[i][1];
			
			testingX = getX() + offsetX;
			testingY = getY() + offsetY;
			
			boolean canMoveMino;		
			if(rotate == RIGHT) {
				canMoveMino = canMinoMove(testingX, testingY, (rotation + 4 + RIGHT) % 4);
			}else {
				canMoveMino = canMinoMove(testingX, testingY, (rotation + 4 + LEFT) % 4);
			}
			
			if(canMoveMino == true) {
				rotation = (rotation + rotate + 4) % 4;
				int[][] relativePosition = MinoType.EMPTY.getBaseMinosRelativePositions(type, rotation);
				for(int j = 0; j < 4; j++) {
					mino[j].setRelativePosition(relativePosition[j]);
				}
				for(int j = 0; j < 4; j++) {
					mino[j].setBounds(testingX * BLOCK_SIZE + mino[j].getRelativeXPosition() * BLOCK_SIZE + 1, testingY * BLOCK_SIZE + mino[j].getRelativeYPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2, BLOCK_SIZE - 2);
					
				}
				
				baseXPosition = testingX;
				baseYPosition = testingY;
				return;
			}
		}
	}
	
	public void addMinoToGameBoard() {
		setMinoPosition(MINO_INITIAL_X_POSITION, MINO_INITIAL_Y_POSITION);
		for(int i = 0; i < 4; i++) {
			gameBoard.add(getBaseMino(i));
		}
		gameBoard.repaint();
	}

	public void setMinoPosition(int x, int y) {
		baseXPosition = x;
		baseYPosition = y;
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(x * BLOCK_SIZE + mino[i].getRelativeXPosition() * BLOCK_SIZE + 1,y * BLOCK_SIZE + mino[i].getRelativeYPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
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
			mino[i].setBounds(x * OTHER_BOARD_BLOCK_SIZE + mino[i].getRelativeXPosition() * OTHER_BOARD_BLOCK_SIZE + 1,y * OTHER_BOARD_BLOCK_SIZE + mino[i].getRelativeYPosition() * OTHER_BOARD_BLOCK_SIZE + 1, OTHER_BOARD_BLOCK_SIZE - 2,OTHER_BOARD_BLOCK_SIZE - 2);
		}
	}
	
	public void removeMinoFromBoard(JPanel board) {
		for(int i = 0; i < 4; i++) {
			board.remove(getBaseMino(i));
		}
	}

	public boolean canMinoMove(int direction) {
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
		int[][] baseMinoPositions = getBaseMinoPositions(baseXPosition + xVector, baseYPosition + yVector, rotation);
		return gameBoard.canMinoMove(baseMinoPositions);
	}
	
	/*
	 * WallKick을 위해 x,y값도 받아서 수행하는 canMinoMove
	 */
	public boolean canMinoMove(int x, int y, int rotate){
		int [][] baseMinoPositions = getBaseMinoPositions(x,y,rotate);
		return gameBoard.canMinoMove(baseMinoPositions);
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
		setMinoPosition(baseXPosition + xVector, baseYPosition + yVector);
	}	
}

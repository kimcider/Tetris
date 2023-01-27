package Mino;

import javax.swing.JPanel;
import static Tetris.Main.*;

import Board.GameBoard;

/*
 * Mino는 4개의 baseMino로 구성된다. 
 * 각각의 baseMino는 BLOCK_SIZE * BLOCK_SIZE 크기의 JPanel이다. (Mino는 그 자체로는 JPanel이 아니다)
 * 각각의 baseMino는 각자의 position정보를 갖으며, 이 포지션 정보는 특정 지점에 대한 상대적 위치로 표현된다. 
 * 
 * Mino의 position은 setPosition(x, y)함수에 들어오는 x, y좌표정보와 baseMino들의 상대적 위치정보를 더해 결정된다.
 * 
 * Mino는 7개의 타입을 갖으며, 모든 타입의 미노는 각각 Mino는 4개의 회전 상태를 갖는다. 
 */
public class Mino {
	private BaseMino[] mino = new BaseMino[4];
	private int rotation;
	private MinoType type;
	
	public Mino(MinoType type) {
		this.type = type;
		rotation = 0;
		
		int[][] relativePosition = MinoType.EMPTY.getBaseMinoRelativePosition(type,0);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(type);
			mino[i].setRelativePosition(relativePosition[i]);
		}
	}
	
	public BaseMino getBaseMino(int num) {
		return mino[num];
	}
	
	public MinoType getType() {
		return type;
	}
	
	/*
	 * return position[a][b]
	 * 	a: baseMino number
	 * 	b: x, y좌표
	 * 		b == 0: x좌표
	 * 		b == 1: y좌표  
	 */
	public int[][] getPosition(int baseX, int baseY, int rotate){
		int[][] relativePosition = new int[4][2];
		relativePosition = type.getBaseMinoRelativePosition(this.getType(), rotate);
		
		int [][] position = new int[4][2];
		for(int i = 0; i < 4; i++) {
			position[i][0] = baseX + relativePosition[i][0];
			position[i][1] = baseY + relativePosition[i][1];
		}
		return position;
	}
	
	public int getRotation() {
		return rotation;
	}

	public void rotateMino(int x, int y, int rotate) {
		rotation = (rotation + rotate + 4) % 4;
		int[][] relativePosition = new int[4][2];
		relativePosition = MinoType.EMPTY.getBaseMinoRelativePosition(type, rotation);
		for(int i = 0; i < 4; i++) {
			mino[i].setRelativePosition(relativePosition[i]);
		}
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(x * BLOCK_SIZE + mino[i].getRelativeXPosition() * BLOCK_SIZE + 1,y * BLOCK_SIZE + mino[i].getRelativeYPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	
	public void setRotate(int rotate, int x, int y) {
		while(rotation != rotate) {
			rotateMino(x, y, 1);
		}
	}
	
	
	public void addMinoToGameBoard(JPanel gameBoard, int x, int y) {
		setBaseMinoBoundsForGameBoard(x, y);
		for(int i = 0; i < 4; i++) {
			gameBoard.add(getBaseMino(i));
		}
		gameBoard.repaint();
	}

	public void setBaseMinoBoundsForGameBoard(int x, int y) {
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(x * BLOCK_SIZE + mino[i].getRelativeXPosition() * BLOCK_SIZE + 1,y * BLOCK_SIZE + mino[i].getRelativeYPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	
	
	/* 
	 * waitNumber는 nextMinoBoard에 넣을 경우 사용된다. 
	 * next n'th mino
	 */
	public void addMinoToOtherBoard(JPanel board, int x, int y) {
		setBaseMinoBoundsForOtherBoard(x, y);
		for(int i = 0;i < 4; i++) {
			board.add(getBaseMino(i));
		}
		board.repaint();
	}
	public void addMinoToOtherBoard(JPanel board, int x, int y, int waitNumber) {
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

	public boolean canMinoMove(GameBoard gameBoard, int x, int y, int rotate){
		int [][] baseMinoPositions = getPosition(x,y,rotate);
		return gameBoard.canMinoMove(baseMinoPositions);
	}
}

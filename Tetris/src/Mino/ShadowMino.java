package Mino;

import Board.GameBoard;
import Tetris.Point;
import static Mino.Mino.*;
import static Tetris.Main.BLOCK_SIZE;

import javax.swing.JPanel;

public class ShadowMino {
	private GameBoard gameBoard;
	
	private Mino superMino;
	private BaseMino[] mino = new BaseMino[4];
	private Point basePoint;
	
	public ShadowMino(GameBoard gameBoard, Mino superMino) {
		this.gameBoard = gameBoard;
		this.superMino = superMino;
		
		basePoint = new Point(superMino.getX(), superMino.getY());
		Point[] relativepoints = MinoType.EMPTY.getBaseMinosRelativePoint(superMino.getType(), 0);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(superMino.getType(), superMino.getType().shadowColor());
			mino[i].setRelativePoint(relativepoints[i]);
			
		}		
	}
	
	public void moveShadow() {
		this.basePoint.setPoint(superMino.getX(), superMino.getY());
		
		Point bottomPoint = gameBoard.getBottomPoint(superMino);
		Point[] relativePoint = MinoType.EMPTY.getBaseMinosRelativePoint(superMino.getType(), superMino.getRotation());
		for(int i = 0; i < 4; i++) {
			mino[i].setRelativePoint(relativePoint[i]);
		}
		setPoint(bottomPoint.getX(), bottomPoint.getY());

	}
	
	public BaseMino getBaseMino(int num) {
		return mino[num];
	}
	public void addMinoToGameBoard() {
		setPoint(basePoint.getX(), basePoint.getY());
		for(int i = 0; i < 4; i++) {
			gameBoard.add(getBaseMino(i));
		}
		gameBoard.repaint();
	}
	private void setPoint(int x, int y) {
		
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(x * BLOCK_SIZE + mino[i].getRelativePoint().getX() * BLOCK_SIZE + 1,y * BLOCK_SIZE + mino[i].getRelativePoint().getY() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	public void removeMinoFromBoard(JPanel board) {
		for(int i = 0; i < 4; i++) {
			board.remove(getBaseMino(i));
		}
	}
}

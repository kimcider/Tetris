package Tetris;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Tetris.Main.*;
import Mino.Mino;
import Mino.WallKick;
import Board.*;

public class Control extends JFrame{
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int HOLD = 0;
	public static final int DOWN = 1;
	public static final int MINO_INITIAL_X_POSITION = 5;
	public static final int MINO_INITIAL_Y_POSITION = 0;
	
	private GameBoard gameBoard;
	private Mino mino;
	private NextMinoBoard nextMinoBoard;
	private SaveBoard saveBoard;
	private ScoreBoard scoreBoard;
	private Timer  timer;
	private int score;
	private boolean saveMinoFlag;
	private boolean isMovedJustBefore;
	private boolean endFlag;
	private boolean moveDownSemaphore;
	
	public Control() {
		setLayout(null);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyboardListener());
	}
	
	public void paint(Graphics g) {
		if(endFlag == false) {
			g.setColor(Color.green);
			g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
			g.setColor(Color.blue);
			g.fillRect(BOARD_START_WIDTH - 10, BOARD_START_HEIGHT + 15, BLOCK_SIZE * BOARD_WIDTH + 20, BLOCK_SIZE * BOARD_HEIGHT + 25);	
		}else {
			g.setColor(Color.gray);
			g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Serif", Font.PLAIN, 50));
			g.drawString("Score is ", getWidth()/2 - 180, getHeight()/2);
			g.drawString(String.valueOf(score),getWidth()/2, getHeight()/2);
		}
	}
	
	public void gameStart() {
		score = 0;
		saveMinoFlag = false;
		endFlag = false;
		moveDownSemaphore = true;
		
		gameBoard = new GameBoard();
		nextMinoBoard = new NextMinoBoard(gameBoard);
		saveBoard = new SaveBoard(gameBoard);
		scoreBoard = new ScoreBoard();

		add(gameBoard);
		add(nextMinoBoard);
		add(saveBoard);
		add(scoreBoard);
		
		gameBoard.repaint();
		nextMinoBoard.repaint();
		saveBoard.repaint();
		scoreBoard.repaint();
		
		mino = nextMinoBoard.getMino();
		mino.addMinoToGameBoard();

		timer = new Timer();
		timer.run();
	}
	
	public void gameEnd() {
		timer.stopTimer();
		remove(gameBoard);
		remove(nextMinoBoard);
		remove(saveBoard);
		revalidate();
		repaint();
		
		gameBoard = null;
		nextMinoBoard = null;
		saveBoard = null;
		timer = null; 
		
		endFlag = true;
	}
	
	
	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			//Move Mino to right
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveMinoToRight();
			}
			//Move Mino to left
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveMinoToLeft();
			}
			//Rotate Mino to right
			else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
				rotateMino(mino, RIGHT);
			}
			//Rotate Mino to left
			else if(e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyChar() == 'Z' || e.getKeyChar() == 'z') {
				rotateMino(mino, LEFT);
			}
			
			//Move Mino to down
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				if(moveDownSemaphore) {
					moveDownSemaphore = false;
					
					moveMinoToDown();
					
					moveDownSemaphore = true;
				}
				
			}
			
			//Move Mino to bottom
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				if(moveDownSemaphore) {
					moveDownSemaphore = false;
					
//					do {
//						boolean originalIsMovedJustBefore = isMovedJustBefore;
//						isMovedJustBefore = mino.moveMinoToDown();
//						if(isMovedJustBefore == false) {
//							if(originalIsMovedJustBefore == true) {
//								stackMinoToGameBoard(mino);
//							}else {
//								gameEnd();
//							}
//						}
//					}while(isMovedJustBefore);
					
					moveMinoToBottom();
					
					moveDownSemaphore = true;
				}
				
			}
			
			//Save Mino
			else if( e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyChar() == 'C' || e.getKeyChar() == 'c' ){
				saveMino(mino);
			}
			
			//Game end
			else if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
				gameEnd();
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
	
	public void moveMinoToRight() {
		isMovedJustBefore = mino.moveMinoToRight();
	}
	public void moveMinoToLeft() {
		isMovedJustBefore = mino.moveMinoToLeft();
	}
	public boolean moveMinoToDown() {
		boolean isMoved = mino.moveMinoToDown();
		if(isMoved == false) {
			if(isMovedJustBefore == true) {
				stackMinoToGameBoard(mino);
			}else {
				gameEnd();
			}
		}
		isMovedJustBefore = isMoved;
		return isMoved;
	}

	public void moveMinoToBottom() {
		while(moveMinoToDown());
		//while(moveMinoToDown(mino));
//		while(mino.moveMinoToDown())

//		do {
//		boolean originalIsMovedJustBefore = isMovedJustBefore;
//		isMovedJustBefore = mino.moveMinoToDown();
//		if(isMovedJustBefore == false) {
//			if(originalIsMovedJustBefore == true) {
//				stackMinoToGameBoard(mino);
//			}else {
//				gameEnd();
//			}
//		}
//	}while(isMovedJustBefore);		
		
		
	}
	
	public void saveMino(Mino target) {
		if(saveMinoFlag == false) {
			mino = saveBoard.saveMino(mino);
			
			if(mino == null) {
				mino = nextMinoBoard.getMino();
			}
			mino.addMinoToGameBoard();
			saveMinoFlag = true;
		}
	}
	
	public void rotateMino(Mino mino, int rotate) {
		int[][] rotationOffset;
		int tempX;
		int tempY;
		
		if(rotate == RIGHT) {
			rotationOffset = WallKick.getRotationOffset(mino.getType(), mino.getRotation(), 0);
		}
		else {
			rotationOffset = WallKick.getRotationOffset(mino.getType(), mino.getRotation(), 1);
		}
		
		for(int i = 0; i < 5; i++) {	
			int offsetX = rotationOffset[i][0];
			int offsetY = -rotationOffset[i][1];
			
			tempX = mino.getX() + offsetX;
			tempY = mino.getY() + offsetY;
			
			boolean answer;		
			if(rotate == RIGHT) {
				answer = mino.canMinoMove(tempX, tempY, (mino.getRotation() + 4 + RIGHT) % 4);
			}else {
				answer = mino.canMinoMove(tempX, tempY, (mino.getRotation() + 4 + LEFT) % 4);
			}
			if(answer == true) {
				mino.rotateMino(tempX, tempY, rotate);
				mino.setX(tempX);
				mino.setY(tempY);
				return;
			}
		}
	}
	


	
	public void stackMinoToGameBoard(Mino mino) {
		int erasedLineCounter = gameBoard.stackMinoToBoard(mino);
		changeMino();
		lineErased(erasedLineCounter);
	}
	
	public void changeMino() {
		mino.removeMinoFromBoard(gameBoard);
		mino = nextMinoBoard.getMino();
		mino.addMinoToGameBoard();
		saveMinoFlag = false;
		isMovedJustBefore = false;
	}
	
	public void lineErased(int erasedLineCounter) {
		timer.speedUp(erasedLineCounter);
		score = score + (erasedLineCounter * erasedLineCounter) * 100;
		scoreBoard.setScore(score);
		scoreBoard.repaint();
	}
	
	
	
	class Timer extends Thread{
		double interval = 1.0;
		boolean usingTimerFlag = true;
		
		public void run() {
			while(usingTimerFlag) {
				try {
					sleep((long)(1000/interval));
					if(usingTimerFlag && moveDownSemaphore) {
						moveDownSemaphore = false;
						
						
						boolean isMoved = mino.moveMinoToDown();
						if(isMoved == false) {
							if(isMovedJustBefore == true) {
								stackMinoToGameBoard(mino);
							}else {
								gameEnd();
							}
						}
						isMovedJustBefore = isMoved;
						
						
						moveDownSemaphore = true;
					}
				}catch(InterruptedException e) {
					;
				}
			}
		}
		
		public void speedUp(int num) {
			interval = interval + (0.1 * num);
		}

		public void stopTimer() {
			usingTimerFlag = false;
		}
	}
	
	

}

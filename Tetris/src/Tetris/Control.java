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
	private static final int RIGHT = 1;
	private static final int LEFT = -1;
	private static final int HOLD = 0;
	private static final int DOWN = 1;

	
	private GameBoard gameBoard;
	private Mino mino;
	private NextMinoBoard nextMinoBoard;
	private SaveBoard saveBoard;
	private ScoreBoard scoreBoard;
	private Timer  timer;
	private int xPosition;
	private int yPosition;
	private int xInitValue;
	private int yInitValue;
	private int score;
	private boolean saveMinoFlag;
	private boolean movedFlag;
	private boolean endFlag;
	
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
		xInitValue = 5;
		yInitValue = 0;
		xPosition = xInitValue;
		yPosition = yInitValue;
		saveMinoFlag = false;
		endFlag = false;
		
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
		mino.addMinoToGameBoard(gameBoard,xPosition,yPosition);

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
	
	public void stackMinoToGameBoard(Mino mino) {
		int erasedLineCounter = gameBoard.stackMinoToBoard(mino, xPosition, yPosition, mino.getRotation());
		changeMino();
		lineErased(erasedLineCounter);
	}
	
	public void changeMino() {
		xPosition = xInitValue;
		yPosition = yInitValue;
		mino.removeMinoFromBoard(gameBoard);
		mino = nextMinoBoard.getMino();
		mino.addMinoToGameBoard(gameBoard,xPosition,yPosition);
		saveMinoFlag = false;
		movedFlag = false;
	}
	
	public void lineErased(int erasedLineCounter) {
		timer.speedUp(erasedLineCounter);
		score = score + (erasedLineCounter * erasedLineCounter) * 100;
		scoreBoard.setScore(score);
		scoreBoard.repaint();
	}
	
	public void saveMino(Mino target, int x, int y) {
		if(saveMinoFlag == false) {
			target.setRotate(0, x, y);
			mino = saveBoard.saveMino(mino);
			
			if(mino == null) {
				mino = nextMinoBoard.getMino();
			}
			
			xPosition = xInitValue;
			yPosition = yInitValue;
			mino.addMinoToGameBoard(gameBoard, xPosition, yPosition);
			
			saveMinoFlag = true;
		}
	}
	
	public boolean moveMino(Mino mino, int xVector, int yVector) {
		boolean answer = mino.canMinoMove(gameBoard, xPosition + xVector, yPosition + yVector, mino.getRotation());
		if(answer == true) {
			xPosition = xPosition + xVector;
			yPosition = yPosition + yVector;
			mino.setBaseMinoBoundsForGameBoard(xPosition , yPosition);
			movedFlag = true;
		}else {
			if(yVector == 1) {
				if(movedFlag == false) {
					gameEnd();
				}
				else {
					stackMinoToGameBoard(mino);	
				}
			}
		}
		return answer;
	}
	
	public void moveMinoToBottom(Mino mino) {
		while(moveMino(mino, HOLD, DOWN));
	}
	
	public void rotateMino(Mino mino, int x, int y, int rotate) {
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
			
			tempX = x + offsetX;
			tempY = y + offsetY;
			
			boolean answer;		
			if(rotate == RIGHT) {
				answer = mino.canMinoMove(gameBoard, tempX, tempY, (mino.getRotation() + 4 + RIGHT) % 4);
			}else {
				answer = mino.canMinoMove(gameBoard, tempX, tempY, (mino.getRotation() + 4 + LEFT) % 4);
			}
			if(answer == true) {
				mino.rotateMino(tempX, tempY, rotate);
				xPosition = tempX;
				yPosition = tempY;
				return;
			}
		}
	}
	
	class Timer extends Thread{
		double interval = 1.0;
		boolean usingTimerFlag = true;
		
		public void run() {
			while(usingTimerFlag) {
				try {
					sleep((long)(1000/interval));
					if(usingTimerFlag) {
						moveMino(mino, HOLD, DOWN);
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
	
	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			//move right
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveMino(mino, RIGHT, HOLD);
			}
			//move left
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveMino(mino, LEFT, HOLD);
			}
			//right rotate
			else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
				rotateMino(mino, xPosition, yPosition, RIGHT);
			}
			//left rotate
			else if(e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyChar() == 'Z' || e.getKeyChar() == 'z') {
				rotateMino(mino, xPosition, yPosition, LEFT);
			}
			
			//move down
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				moveMino(mino, HOLD, DOWN);
			}
			
			//quick move down
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				moveMinoToBottom(mino);
			}
			
			//save mino
			else if( e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyChar() == 'C' || e.getKeyChar() == 'c' ){
				saveMino(mino, xPosition, yPosition);
			}
			
			// game end
			else if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
				gameEnd();
			}
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

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
	public static final int DOWN = 0;
	public static final int MINO_INITIAL_X_POSITION = 5;
	public static final int MINO_INITIAL_Y_POSITION = 0;
	
	private KeyboardListener keyboardListener;
	private GameBoard gameBoard;
	private Mino mino;
	private NextMinoBoard nextMinoBoard;
	private SaveBoard saveBoard;
	private ScoreBoard scoreBoard;
	private Timer  timer;
	private int score;
	private boolean canSaveMino;
	private boolean isMovedJustBefore;
	private boolean gameEndFlag;
	
	private boolean moveDownSemaphore;
	
	public Control() {
		setLayout(null);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		keyboardListener = new KeyboardListener();
		addKeyListener(keyboardListener);
	}
	
	public void paint(Graphics g) {
		if(gameEndFlag == false) {
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
		canSaveMino = true;
		gameEndFlag = false;
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

		timer = new Timer(this);
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
		gameEndFlag = true;
	}
	
	public void saveMino() {
		if(canSaveMino == true) {
			mino = saveBoard.saveMino(mino);
			
			if(mino == null) {
				mino = nextMinoBoard.getMino();
			}
			mino.addMinoToGameBoard();
			canSaveMino = false;
		}
	}
	
	
	public void stackMinoToGameBoard() {
		int erasedLineCounter = gameBoard.stackMinoToBoard(mino);
		lineErased(erasedLineCounter);
		changeMino();
	}

	public void lineErased(int erasedLineCounter) {
		timer.speedUp(erasedLineCounter);
		score = score + (erasedLineCounter * erasedLineCounter) * 100;
		scoreBoard.setScore(score);
		scoreBoard.repaint();
	}
	
	public void changeMino() {
		mino.removeMinoFromBoard(gameBoard);
		mino = nextMinoBoard.getMino();
		mino.addMinoToGameBoard();
		canSaveMino = true;
		isMovedJustBefore = false;
	}
	
	
	class Timer extends Thread{
		double interval = 1.0;
		boolean ongoingFlag = true;
		JFrame frame;
		
		public Timer(JFrame jframe) {
			frame = jframe;
		}
		public void run() {
			while(ongoingFlag) {
				try {
					sleep((long)(1000/interval));
					if(ongoingFlag) {
						KeyEvent keyEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED);
						keyboardListener.keyPressed(keyEvent);
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
			ongoingFlag = false;
		}
	}
	
	
	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if(mino.canMinoMove(RIGHT)) {
					mino.moveMino(RIGHT);
				}
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				if(mino.canMinoMove(LEFT)) {
					mino.moveMino(LEFT);
				}
			}
			
			/*
			 * Move Mino To DOwn
			 */
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				if(moveDownSemaphore) {
					moveDownSemaphore = false;
					
					boolean canMinoMove = mino.canMinoMove(DOWN);
					if(canMinoMove == true) {
						mino.moveMino(DOWN);
					}else {
						if(isMovedJustBefore == true) {
							stackMinoToGameBoard();
						}else {
							gameEnd();
						}
					}
					isMovedJustBefore = canMinoMove;
					
					moveDownSemaphore = true;
				}
			}
			
			/*
			 * Move Mino To Bottom
			 */
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				if(moveDownSemaphore) {
					moveDownSemaphore = false;
					
					boolean canMinoMove = mino.canMinoMove(DOWN);
					while(canMinoMove) {
						mino.moveMino(DOWN);
						isMovedJustBefore = true;
						canMinoMove = mino.canMinoMove(DOWN);
					}
					
					if(isMovedJustBefore == true) {
						stackMinoToGameBoard();
					}else {
						gameEnd();
					}
					isMovedJustBefore = false;
					
					moveDownSemaphore = true;
				}
				
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
				mino.rotateMino(RIGHT);
			}
			
			else if(e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyChar() == 'Z' || e.getKeyChar() == 'z') {
				mino.rotateMino(LEFT);
			}
			

			
			else if( e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyChar() == 'C' || e.getKeyChar() == 'c' ){
				saveMino();
			}

			else if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
				gameEnd();
			}
		}
		
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}

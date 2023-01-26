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
	private GameBoard gameBoard;
	private Mino mino;
	private NextMinoBoard nextMinoBoard;
	private SaveBoard saveBoard;
	private ScoreBoard scoreBoard;
	private Timer  timer;
	private int x;
	private int y;
	private int xInitValue;
	private int yInitValue;
	private int erasedLine;
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
		endFlag = false;
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
	
	/*
	 * 게임 진행
	 */
	public void gameStart() {
		score = 0;
		xInitValue = 5;
		yInitValue = 0;
		x = xInitValue;
		y = yInitValue;
		erasedLine = 1;
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
		
		revalidate();
		gameBoard.repaint();
		nextMinoBoard.repaint();
		saveBoard.repaint();
		scoreBoard.repaint();
		
		mino = nextMinoBoard.getMino();
		mino.addMinoToBoard(gameBoard,x,y);

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

	public Mino getMino() {
		return mino;
	}
	
	/*
	 * 현재의 mino를 제거하고 새로운 mino를 받아온다. 
	 */
	public void changeMino() {
		x = xInitValue;
		y = yInitValue;
		mino.removeMinoFromBoard(gameBoard);
		mino = nextMinoBoard.getMino();
		mino.addMinoToBoard(gameBoard,x,y);
		saveMinoFlag = false;
		movedFlag = false;
	}

	
	/*
	 * 미노를 세이브한다. 
	 * 
	 * savedMinoFlag는 changMino()시에만 false로 바뀜을 주의하라.
	 */
	public void saveMino(Mino target, int x, int y) {
		target.setRotate(0, x, y);
		if(saveMinoFlag == false) {
			mino = saveBoard.save(mino);
			
			if(mino == null) {
				mino = nextMinoBoard.getMino();
				this.x = xInitValue;
				this.y = yInitValue;
				mino.addMinoToBoard(gameBoard, this.x, this.y);
			}else {
				this.x = xInitValue;
				this.y = yInitValue;
				mino.addMinoToBoard(gameBoard, this.x, this.y);
			}
			saveMinoFlag = true;
		}
	}
	
	public boolean moveMino(Mino mino, int x, int y, int xVector, int yVector) {
		boolean answer = mino.checkToMove(gameBoard, x + xVector, y + yVector, mino.getRotation());
		if(answer == true) {
			this.x = x + xVector;
			this.y = y + yVector;
			mino.setPosition(this.x , this.y);
			movedFlag = true;
			return true;
		}else {
			if(yVector == 1) {
				if(movedFlag == false) {
					/*
					 * 게임종료 
					 */
					gameEnd();
					return false;
				}
				int erasedLineCounter = gameBoard.stack(mino, x, y, mino.getRotation());
				changeMino();
				lineErased(erasedLineCounter);
				
			}
			return false;	
		}
		
	}
	
	/*
	 * 현재의 미노를 맨 아래로 이동시킨다. 
	 *
	 */
	public void moveMinoToBottom(Mino mino, int x, int y) {
		boolean result = true;
		while(result) {
			result = moveMino(mino, this.x, this.y, 0, 1);
		}
	}
	
	
	/*
	 * Mino를 회전시킨다. 
	 * 
	 * mino: 회전시킬 미노 
	 * 
	 * x: mino의 현재 x좌표
	 * y: mino의 현재 y좌표 
	 * 
	 * rotate == 1 : right rotate
	 * rotate == 0 : left rotate
	 *  
	 */
	public void rotateMino(Mino mino, int x, int y, int rotate) {
		int[][] offset;
		int originalX = x;
		int originalY = y;
		
		/*
		 * WallKick.getOffset(type, rotation, direction)
		 * diretion == 0 : right rotation
		 * direction == 1 : left rotation
		 */
		if(rotate == 1) {
			offset = WallKick.getOffset(mino.getType(), mino.getRotation(), 0);
		}
		else {
			offset = WallKick.getOffset(mino.getType(), mino.getRotation(), 1);
		}
		
		/*
		 * WallKick의 5가지 offset을 각각체크
		 */
		for(int i = 0; i < 5; i++) {	
			int offsetX = offset[i][0];
			int offsetY = -offset[i][1];
			
			x = originalX + offsetX;
			y = originalY + offsetY;
			
			boolean answer;		
			if(rotate == 1) {
				answer  = mino.checkToMove(gameBoard, x, y, (mino.getRotation() + 1) % 4);
				if(answer == true) {
					mino.rotate(x, y, 1);
					this.x = x;
					this.y = y;
					return;
				}
			}else {
				answer = mino.checkToMove(gameBoard, x, y, (mino.getRotation() + 4 - 1) % 4);
				if(answer == true) {
					mino.rotate(x, y, -1);
					this.x = x;
					this.y = y;
					return;
				}
			}

		}

	}
	
	
	/*
	 * line이 지워졌을 때의 부수작업들을 수행.
	 *	ex) 점수계산, 게임 속도 증가 
	 */
	public void lineErased(int erasedLineCounter) {
		timer.speedUp(erasedLineCounter);
		score = score + (erasedLineCounter * erasedLineCounter) * 100;
		scoreBoard.setScore(score);
		scoreBoard.repaint();
	}
	
	class Timer extends Thread{
		double interval = 1.0;
		boolean flag = true;
		public void stopTimer() {
			flag = false;
		}
		public void run() {
			while(flag) {
				try {
					sleep((long)(1000/interval));
					if(flag) {
						moveMino(mino, x, y, 0, 1);
					}
				}catch(InterruptedException e) {
					
				}
			}
		}
		public void speedUp(int num) {
			interval = interval + (0.1 * num);
		}
	}
	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			//move right
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				moveMino(mino, x, y, 1, 0);
			}
			//move left
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				moveMino(mino, x, y, -1, 0);
			}
			
			//right rotate
			else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
				rotateMino(mino, x, y, 1);
			}
			//left rotate
			else if(e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyChar() == 'Z' || e.getKeyChar() == 'z') {
				rotateMino(mino, x, y, 0);
			}
			
			//move down
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				moveMino(mino, x, y, 0, 1);
			}
			
			//quick move down
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				moveMinoToBottom(mino, x, y);
			}
			
			//save mino
			else if( e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyChar() == 'C' || e.getKeyChar() == 'c' ){
				saveMino(mino, x, y);
			}
			
			else if(e.getKeyChar() == 'e' || e.getKeyChar() == 'E') {
				gameEnd();
			}
			
			else if(e.getKeyChar() == 'r' || e.getKeyChar() == 'R') {
				//gameStart();
			}
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

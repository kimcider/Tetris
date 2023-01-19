package Tetris;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Tetris.Main.*;
import Mino.Mino;
import Mino.WallKick;
import Board.*;

public class Control extends JFrame{
	private Board board;
	private Mino mino;
	private NextMinoBoard nextMinoBoard;
	private SaveBoard saveBoard;
	int x;
	int y;
	int xInitValue;
	int yInitValue;
	
	boolean saveMinoFlag;
	
	public Control() {
		setLayout(null);
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyboardListener());
		xInitValue = 5;
		yInitValue = 0;
		x = xInitValue;
		y = yInitValue;
		
		saveMinoFlag = false;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	/*
	 * 게임 진행
	 */
	public void start() {
		board = new Board();
		add(board);

		nextMinoBoard = new NextMinoBoard(board);
		add(nextMinoBoard);
		
		saveBoard = new SaveBoard(board);
		add(saveBoard);
		
		mino = nextMinoBoard.getMino();
		mino.addMinoToBoard(board,x,y);
		
		
		repaint();
	}
	
	/*
	 * 현재의 mino를 제거하고 새로운 mino를 받아온다. 
	 */
	public void changeMino() {
		x = xInitValue;
		y = yInitValue;
		mino.removeMinoFromBoard(board);
		mino = nextMinoBoard.getMino();
		mino.addMinoToBoard(board,x,y);
		saveMinoFlag = false;
	}

	
	/*
	 * 미노를 세이브한다. 
	 * 
	 * savedMinoFlag는 changMino()시에만 false로 바뀜을 주의하라.
	 */
	public void saveMino(Mino target) {
		target.setRotate(0);
		if(saveMinoFlag == false) {
			mino = saveBoard.save(mino);
			
			if(mino == null) {
				mino = nextMinoBoard.getMino();
				x = xInitValue;
				y = yInitValue;
				mino.addMinoToBoard(board, x, y);
			}else {
				x = xInitValue;
				y = yInitValue;
				mino.addMinoToBoard(board, x, y);
			}
			saveMinoFlag = true;
		}
	}
	
	public boolean moveMino(Mino mino, int x, int y, int xVector, int yVector) {
		boolean answer = mino.checkToMove(board, x + xVector, y + yVector, mino.getRotation());
		if(answer == true) {
			this.x = x + xVector;
			this.y = y + yVector;
			mino.setPosition(this.x , this.y);
			return true;
		}
		return false;
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
				answer  = mino.checkToMove(board, x, y, (mino.getRotation() + 1) % 4);
				if(answer == true) {
					mino.rotate(x, y, 1);
					this.x = x;
					this.y = y;
					return;
				}
			}else {
				answer = mino.checkToMove(board, x, y, (mino.getRotation() + 4 - 1) % 4);
				if(answer == true) {
					mino.rotate(x, y, -1);
					this.x = x;
					this.y = y;
					return;
				}
			}

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
			else if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyChar() == 'ㅌ' || e.getKeyChar() == 'X' || e.getKeyChar() == 'x') {
				rotateMino(mino, x, y, 1);
			}
			//left rotate
			else if(e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyChar() == 'ㅋ' || e.getKeyChar() == 'Z' || e.getKeyChar() == 'z') {
				rotateMino(mino, x, y, 0);
			}
			
			//move down
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				moveMino(mino, x, y, 0, 1);
			}
			
			//quick move down
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				moveMinoToBottom(mino, x, y);
				board.stack(mino, x, y, mino.getRotation());
				changeMino();
			}
			
			//save mino
			else if( e.getKeyCode() == KeyEvent.VK_SHIFT || e.getKeyChar() == 'ㅊ'|| e.getKeyChar() == 'C' || e.getKeyChar() == 'c' ){
				saveMino(mino);
			}
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

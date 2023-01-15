package Tetris;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static Tetris.Main.*;
import Mino.Mino;
import Board.*;

public class Control extends JFrame{
	Board board;
	Mino mino;
	NextMinoBoard nextMinoBoard;
	SaveBoard saveBoard;
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
	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				x += 1;
				mino.setPosition(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				x -= 1;
				mino.setPosition(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				mino.rotate(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				y += 1;
				mino.setPosition(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				changeMino();
			}else if(e.getKeyChar() == 'a' || e.getKeyChar() == 'ㅁ' || e.getKeyChar() == 'A'){
				saveMino(mino);
			}
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

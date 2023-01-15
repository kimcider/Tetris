package tetris;

import static tetris.Main.*;
import javax.swing.*;
import Mino.BaseMino;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Mino.*;
import java.util.Random;
import java.util.Arrays;
public class Control extends JFrame{
	Board board;
	Mino mino;
	NextMinoBoard nextMinoBoard;
	int x;
	int y;
	int xInitValue;
	int yInitValue;
	
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
		
		mino = nextMinoBoard.getMino();
		mino.addMinoToBoard(board,x,y);
		repaint();
	}
	
	/*
	 * 현재의 mino를 제거하고 다음 미노를 mino로 한다. 
	 */
	public void changeMino() {
		x = xInitValue;
		y = yInitValue;
		mino.removeMinoFromBoard(board);
		mino = nextMinoBoard.getMino();
		mino.addMinoToBoard(board,x,y);
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
			}
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

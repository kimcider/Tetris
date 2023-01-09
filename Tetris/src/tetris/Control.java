package tetris;

import static tetris.Main.*;
import javax.swing.*;
import Mino.BaseMino;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Mino.*;
public class Control extends JFrame{
	Board board;
	O_Mino oMino;
	int x;
	int y;
	public Control() {
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyboardListener());
		x = 5;
		y = 0;
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
	}
	public void start() {
		board = new Board();
		add(board);
		board.setBounds(BOARD_START_WIDTH,BOARD_START_HEIGHT,board.getWidth(),board.getHeight());
		add_O_Mino();
	}
	public void add_O_Mino() {
		oMino = new O_Mino();
		oMino.setPosition(x,y);
		for(int i = 0; i < 4; i++) {
			board.add(oMino.getBaseMino(i));
		}
		board.repaint();
		
	}

	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				x += 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				x -= 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				y -= 1;
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				y += 1;
			}
//			baseMino.setBounds(x,y,baseMino.getWidth(),baseMino.getHeight());
			oMino.setPosition(x, y);
			
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

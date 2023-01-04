package Tetris;
import static Tetris.Main.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Control extends JFrame{
	Board bd;
	Block bl;
	int x;
	int y;
	public Control() {
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyboardListener());
		x = 100;
		y = 100;
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
	}
	public void start() {
		bd = new Board();
		add(bd);
		bd.setBounds(BOARD_START_WIDTH,BOARD_START_HEIGHT,bd.getWidth(),bd.getHeight());
		addBlock();
	}
	public void addBlock() {
		bl = new Block();	
		bl.setBounds(x,y,bl.getWidth(),bl.getHeight());
		bd.add(bl);
	}

	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				x += BLOCK_SIZE;
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				x -= BLOCK_SIZE;
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				y -= BLOCK_SIZE;
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				y += BLOCK_SIZE;
			}
			bl.setBounds(x,y,bl.getWidth(),bl.getHeight());
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

package tetris;

import javax.swing.*;
import java.awt.*;
import static tetris.Main.BLOCK_SIZE;
import static tetris.Main.BOARD_HEIGHT;
import static tetris.Main.BOARD_WIDTH;

import java.awt.*;
public class Board extends JPanel{
	public Board() {
		setVisible(true);
		setSize(BLOCK_SIZE * BOARD_WIDTH, BLOCK_SIZE * BOARD_HEIGHT);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		int x = 0;
		int y = 0;
		
		g.setColor(new Color(150,75,0));
		for(int i = 0; i < BOARD_HEIGHT; i++) {
			for(int j = 0; j < BOARD_WIDTH; j++) {
				g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
				x = x + BLOCK_SIZE;
			}
			x = 0;
			y = y + BLOCK_SIZE;
		}
	}
}

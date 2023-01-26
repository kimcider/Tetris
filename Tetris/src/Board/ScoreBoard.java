package Board;
import java.awt.Graphics;

import static Tetris.Main.BLOCK_SIZE;
import static Tetris.Main.BOARD_START_HEIGHT;
import static Tetris.Main.BOARD_START_WIDTH;
import static Tetris.Main.BOARD_WIDTH;
import static Tetris.Main.OTHER_BOARD_BLOCK_SIZE;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import Mino.Mino;
public class ScoreBoard extends JPanel{
	int height;
	int width;
	boolean isSaved;
	Mino mino;
	int score;
	public ScoreBoard() {
		setVisible(true);
		width = OTHER_BOARD_BLOCK_SIZE * 9;
		height = OTHER_BOARD_BLOCK_SIZE * 4;
		setSize(width,height);
		setBounds(BOARD_START_WIDTH + BLOCK_SIZE * (BOARD_WIDTH + 1), BOARD_START_HEIGHT + (OTHER_BOARD_BLOCK_SIZE * 4 * 6) + BLOCK_SIZE * 2, getWidth() ,getHeight());
		score = 0;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(10, 10, width - 20, height - 20);
		g.setColor(Color.white);
		g.setFont(new Font("Serif", Font.PLAIN, 24));
		g.drawString(String.valueOf(score), 20, getHeight() / 2 + 5);
	}
	public void setScore(int score) {
		this.score = score;
	}
}

package Mino;

import java.awt.Color;
import javax.swing.JPanel;
import static Tetris.Main.*;


public class BaseMino extends JPanel{
	private Color color;

	/*
	 * position[0]은 baseMino의 xPosition을,
	 * position[1]은 baseMino의 yPosition을 지칭한다.
	 */
	private int[] position = new int[2];
	
	public BaseMino(MinoType type) {
		setVisible(true);
		setSize(BLOCK_SIZE,BLOCK_SIZE);
		setBackground(type.getColor());
	}
	public void setRelativePosition(int[] position) {
		this.position[0] = position[0];
		this.position[1] = position[1];
	}
	public int[] getRelativePosition() {
		return position;
	}
	
	public int getRelativeXPosition() { 
		return position[0];
	}
	
	public int getRelativeYPosition() { 
		return position[1];
	}
	
}
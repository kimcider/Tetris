package Mino;

import static Tetris.Main.*;

import java.awt.*;
import javax.swing.*;

public class BaseMino extends JPanel{
	Color color;
	
	int[] position = new int[2];
	 /* 
	 * position[0]은 baseMino의 Height를,
	 * position[1]은 baseMino의 Width를 지칭한다.
	 */
	public BaseMino(MinoType type) {
		setVisible(true);
		setSize(BLOCK_SIZE,BLOCK_SIZE);
		setBackground(type.getColor());
	}
	public void setPosition(int[] position) {
		this.position[0] = position[0];
		this.position[1] = position[1];
	}
	public int[] getPosition() {
		return position;
	}
	
	/* Y좌표 반환 */
	public int getHeightPosition() { 
		return position[0];
	}
	
	/* X좌표 반환 */
	public int getWidthPosition() { 
		return position[1];
	}
}
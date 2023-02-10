package Mino;

import java.awt.Color;
import javax.swing.JPanel;
import Tetris.Point;
import static Tetris.Main.*;


public class BaseMino extends JPanel{
	private Point point;
	
	public BaseMino(MinoType type) {
		setVisible(true);
		setSize(BLOCK_SIZE,BLOCK_SIZE);
		setBackground(type.getColor());
		point = new Point();
	}
	
	public BaseMino(MinoType type, Color color) {
		setVisible(true);
		setSize(BLOCK_SIZE,BLOCK_SIZE);
		setBackground(color);
		point = new Point();
	}
	
	public void setRelativePoint(Point point) {
		this.point.setPoint(point.getX(), point.getY());
	}
	
	public Point getRelativePoint() {
		return point;
	}
}
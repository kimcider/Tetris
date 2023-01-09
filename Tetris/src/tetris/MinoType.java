package tetris;
import java.awt.*;
import Mino.*;
public enum MinoType {
	EMPTY(Color.black),
	I_Mino(new Color(00,255,255)),
	O_Mino(Color.yellow),
	Z_Mino(Color.red),
	S_Mino(new Color(173,255,47)),
	J_Mino(Color.blue),
	L_Mino(Color.orange),
	T_Mino(Color.magenta);
	
	final private Color color;
	private MinoType(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}

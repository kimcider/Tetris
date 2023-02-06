package Mino;

import java.awt.Color;
import Tetris.Point;
/*
 * 모든 미노는 7개의 타입 중 하나의 타입을 갖으며, 모든 타입의 미노는 각각 4개의 회전상태를 갖는다. 
 */

public enum MinoType {
	EMPTY(Color.black, MinoRelativePosition.EmptyPosition()),
	I_Mino(new Color(0,255,255), MinoRelativePosition.I_MinoPoint()),
	O_Mino(Color.yellow, MinoRelativePosition.O_MinoPoint()),
	Z_Mino(Color.red, MinoRelativePosition.Z_MinoPoint()),
	S_Mino(new Color(173,255,47), MinoRelativePosition.S_MinoPoint()),
	J_Mino(Color.blue, MinoRelativePosition.J_MinoPoint()),
	L_Mino(Color.orange, MinoRelativePosition.L_MinoPoint()),
	T_Mino(Color.magenta, MinoRelativePosition.T_MinoPoint());
	
	final private Color color;
	final Point[][] baseMinoRelativePoints;
	
	private MinoType(Color color,Point[][] point) {
		this.color = color;
		this.baseMinoRelativePoints = point;
	}
	public Color getColor() {
		return color;
	}
	
	public Point[] getBaseMinosRelativePoint(MinoType type, int rotate) {
		return type.baseMinoRelativePoints[rotate];
	}
}

/*
 * Mino의 각 rotation별로의 BaseMino들의 상대적 위치.
 *
 * point[a][b]. 
 * a: rotation number
 * b: BaseMino number
 * 		ex) position[2][2]: 2번째 rotation미노의 2번째 baseMino의 Point 
 */
class MinoRelativePosition{
	public static Point[][] getEmptyPoint() {
		Point point[][] = new Point[4][4];
		for(int i = 0; i < point.length; i++) {
			for(int j = 0; j < point[i].length; j++) {
				point[i][j] = new Point();
			}
		}
		return point;
	}
	public static Point[][] EmptyPosition(){
		return new Point[4][4];
	}

	public static Point[][] O_MinoPoint() {
		Point[][] point = getEmptyPoint();
		point[0][0].setPoint(-1, 0);
		point[0][1].setPoint(0, 0);
		point[0][2].setPoint(-1, 1);
		point[0][3].setPoint(0, 1);
		
		point[1][0].setPoint(-1, 0);
		point[1][1].setPoint(0, 0);
		point[1][2].setPoint(-1, 1);
		point[1][3].setPoint(0, 1);

		point[2][0].setPoint(-1, 0);
		point[2][1].setPoint(0, 0);
		point[2][2].setPoint(-1, 1);
		point[2][3].setPoint(0, 1);
		
		point[3][0].setPoint(-1, 0);
		point[3][1].setPoint(0, 0);
		point[3][2].setPoint(-1, 1);
		point[3][3].setPoint(0, 1);
		return point;
	}
	public static Point[][] I_MinoPoint(){
		Point[][] point = getEmptyPoint();
		point[0][0].setPoint(-2, 0);
		point[0][1].setPoint(-1, 0);
		point[0][2].setPoint(0, 0);
		point[0][3].setPoint(1, 0);
		
		point[1][0].setPoint(0, -1);
		point[1][1].setPoint(0, 0);
		point[1][2].setPoint(0, 1);
		point[1][3].setPoint(0, 2);

		point[2][0].setPoint(-2, 1);
		point[2][1].setPoint(-1, 1);
		point[2][2].setPoint(0, 1);
		point[2][3].setPoint(1, 1);
		
		point[3][0].setPoint(-1, -1);
		point[3][1].setPoint(-1, 0);
		point[3][2].setPoint(-1, 1);
		point[3][3].setPoint(-1, 2);
		
		return point;
	}
	public static Point[][] Z_MinoPoint(){
		Point[][] point = getEmptyPoint();
		point[0][0].setPoint(-1, 0);
		point[0][1].setPoint(0, 0);
		point[0][2].setPoint(0, 1);
		point[0][3].setPoint(1, 1);
		
		point[1][0].setPoint(1, 0);
		point[1][1].setPoint(1, 1);
		point[1][2].setPoint(0, 1);
		point[1][3].setPoint(0, 2);
		
		point[2][0].setPoint(-1, 1);
		point[2][1].setPoint(0, 1);
		point[2][2].setPoint(0, 2);
		point[2][3].setPoint(1, 2);
		
		point[3][0].setPoint(0, 0);
		point[3][1].setPoint(0, 1);
		point[3][2].setPoint(-1, 1);
		point[3][3].setPoint(-1, 2);
		return point;
	}
	public static Point[][] S_MinoPoint(){
		Point[][] point = getEmptyPoint();
		
		point[0][0].setPoint(-1, 0);
		point[0][1].setPoint(0, 0);
		point[0][2].setPoint(-1, 1);
		point[0][3].setPoint(-2, 1);
		
		point[1][0].setPoint(-1, 0);
		point[1][1].setPoint(-1, 1);
		point[1][2].setPoint(0, 1);
		point[1][3].setPoint(0, 2);
		
		point[2][0].setPoint(-1, 1);
		point[2][1].setPoint(0, 1);
		point[2][2].setPoint(-1, 2);
		point[2][3].setPoint(-2, 2);
		
		point[3][0].setPoint(-2, 0);
		point[3][1].setPoint(-2, 1);
		point[3][2].setPoint(-1, 1);
		point[3][3].setPoint(-1, 2);
		return point;
	}
	public static Point[][] J_MinoPoint(){
		Point[][] point = getEmptyPoint();
		point[0][0].setPoint(-1, 0);
		point[0][1].setPoint(-1, 1);
		point[0][2].setPoint(0, 1);
		point[0][3].setPoint(1, 1);
		
		point[1][0].setPoint(0, 0);
		point[1][1].setPoint(1, 0);
		point[1][2].setPoint(0, 1);
		point[1][3].setPoint(0, 2);
		
		point[2][0].setPoint(-1, 1);
		point[2][1].setPoint(0, 1);
		point[2][2].setPoint(1, 1);
		point[2][3].setPoint(1, 2);
		
		point[3][0].setPoint(0, 0);
		point[3][1].setPoint(0, 1);
		point[3][2].setPoint(0, 2);
		point[3][3].setPoint(-1, 2);
		return point;
	}
	public static Point[][] L_MinoPoint(){
		Point[][] point = getEmptyPoint();

		point[0][0].setPoint(1, 0);
		point[0][1].setPoint(1, 1);
		point[0][2].setPoint(0, 1);
		point[0][3].setPoint(-1, 1);
		
		point[1][0].setPoint(0, 0);
		point[1][1].setPoint(0, 1);
		point[1][2].setPoint(0, 2);
		point[1][3].setPoint(1, 2);
		
		point[2][0].setPoint(1, 1);
		point[2][1].setPoint(0, 1);
		point[2][2].setPoint(-1, 1);
		point[2][3].setPoint(-1, 2);

		point[3][0].setPoint(-1, 0);
		point[3][1].setPoint(0, 0);
		point[3][2].setPoint(0, 1);
		point[3][3].setPoint(0, 2);
		return point;
	}
	public static Point[][] T_MinoPoint(){
		Point[][] point = getEmptyPoint();

		point[0][0].setPoint(0, 0);
		point[0][1].setPoint(0, 1);
		point[0][2].setPoint(-1, 1);
		point[0][3].setPoint(1, 1);
		
		point[1][0].setPoint(0, 0);
		point[1][1].setPoint(0, 1);
		point[1][2].setPoint(0, 2);
		point[1][3].setPoint(1, 1);

		point[2][0].setPoint(-1, 1);
		point[2][1].setPoint(0, 1);
		point[2][2].setPoint(1, 1);
		point[2][3].setPoint(0, 2);
		
		point[3][0].setPoint(0, 0);
		point[3][1].setPoint(0, 1);
		point[3][2].setPoint(0, 2);
		point[3][3].setPoint(-1, 1);
		return point;
	}
}


package Mino;

import java.awt.Color;

/*
 * 모든 미노는 7개의 타입 중 하나의 타입을 갖으며, 모든 타입의 미노는 각각 4개의 회전상태를 갖는다. 
 */

public enum MinoType {
	EMPTY(Color.black, MinoRelativePosition.EmptyPosition()),
	I_Mino(new Color(0,255,255), MinoRelativePosition.I_MinoPosition()),
	O_Mino(Color.yellow, MinoRelativePosition.O_MinoPosition()),
	Z_Mino(Color.red, MinoRelativePosition.Z_MinoPosition()),
	S_Mino(new Color(173,255,47), MinoRelativePosition.S_MinoPosition()),
	J_Mino(Color.blue, MinoRelativePosition.J_MinoPosition()),
	L_Mino(Color.orange, MinoRelativePosition.L_MinoPosition()),
	T_Mino(Color.magenta, MinoRelativePosition.T_MinoPosition());
	
	final private Color color;
	final int[][][] baseMinoRelativePosiions;
	
	private MinoType(Color color,int[][][] position) {
		this.color = color;
		this.baseMinoRelativePosiions = position;
	}
	public Color getColor() {
		return color;
	}
	public int[][] getBaseMinosRelativePositions(MinoType type,int rotate){
		return type.baseMinoRelativePosiions[rotate];
	}
}

/*
 * Mino의 각 rotation별로의 BaseMino들의 상대적 위치.
 *
 * position[a][b][c]. 
 * a: rotation number
 * b: BaseMino number
 * c: BaseMino position
 * 
 * position[n][m][0]은 BaseMino의 Width (x좌표)를
 * position[n][m][1]은 BaseMino의 Height (y좌표)를 저장한다.
 * 
 * 		ex) position[2][2][1]: 2번째 rotation미노의 2번째블록의 Width(x좌표). 
 */
class MinoRelativePosition{
	public static int[][][] EmptyPosition(){
		return new int[4][4][2];
	}
	public static int[][][] O_MinoPosition() {
		int position[][][] = new int[4][4][2];
		position[0][0][0] = -1;
		position[0][0][1] = 0;
		position[0][1][0] = 0;
		position[0][1][1] = 0;
		position[0][2][0] = -1;
		position[0][2][1] = 1;
		position[0][3][0] = 0;
		position[0][3][1] = 1;

		position[1][0][0] = -1;
		position[1][0][1] = 0;
		position[1][1][0] = 0;
		position[1][1][1] = 0;
		position[1][2][0] = -1;
		position[1][2][1] = 1;
		position[1][3][0] = 0;
		position[1][3][1] = 1;

		position[2][0][0] = -1;
		position[2][0][1] = 0;
		position[2][1][0] = 0;
		position[2][1][1] = 0;
		position[2][2][0] = -1;
		position[2][2][1] = 1;
		position[2][3][0] = 0;
		position[2][3][1] = 1;

		position[3][0][0] = -1;
		position[3][0][1] = 0;
		position[3][1][0] = 0;
		position[3][1][1] = 0;
		position[3][2][0] = -1;
		position[3][2][1] = 1;
		position[3][3][0] = 0;
		position[3][3][1] = 1;
		return position;
	}
	public static int[][][] I_MinoPosition(){
		int [][][] position = new int[4][4][2];
		position[0][0][0] = -2;
		position[0][0][1] = 0;
		position[0][1][0] = -1;
		position[0][1][1] = 0;
		position[0][2][0] = 0;
		position[0][2][1] = 0;
		position[0][3][0] = 1;
		position[0][3][1] = 0;
		
		position[1][0][0] = 0;
		position[1][0][1] = -1;
		position[1][1][0] = 0;
		position[1][1][1] = 0;
		position[1][2][0] = 0;
		position[1][2][1] = 1;
		position[1][3][0] = 0;
		position[1][3][1] = 2;
		
		position[2][0][0] = -2;
		position[2][0][1] = 1;
		position[2][1][0] = -1;
		position[2][1][1] = 1;
		position[2][2][0] = 0;
		position[2][2][1] = 1;
		position[2][3][0] = 1;
		position[2][3][1] = 1;
		
		position[3][0][0] = -1;
		position[3][0][1] = -1;
		position[3][1][0] = -1;
		position[3][1][1] = 0;
		position[3][2][0] = -1;
		position[3][2][1] = 1;
		position[3][3][0] = -1;
		position[3][3][1] = 2;
		return position;
	}
	public static int[][][] Z_MinoPosition(){
		int[][][]position = new int[4][4][2];
		position[0][0][0] = -1;
		position[0][0][1] = 0;   
		position[0][1][0] = 0;
		position[0][1][1] = 0;
		position[0][2][0] = 0;
		position[0][2][1] = 1;
		position[0][3][0] = 1;
		position[0][3][1] = 1;
		
		position[1][0][0] = 1;
		position[1][0][1] = 0;
		position[1][1][0] = 1;
		position[1][1][1] = 1;
		position[1][2][0] = 0;
		position[1][2][1] = 1;
		position[1][3][0] = 0;
		position[1][3][1] = 2;
		
		position[2][0][0] = -1;
		position[2][0][1] = 1;
		position[2][1][0] = 0;
		position[2][1][1] = 1;
		position[2][2][0] = 0;
		position[2][2][1] = 2;
		position[2][3][0] = 1;
		position[2][3][1] = 2;
		
		position[3][0][0] = 0;
		position[3][0][1] = 0;
		position[3][1][0] = 0;
		position[3][1][1] = 1;
		position[3][2][0] = -1;
		position[3][2][1] = 1;
		position[3][3][0] = -1;
		position[3][3][1] = 2;
		return position;
	}
	public static int[][][] S_MinoPosition(){
		int[][][]position = new int[4][4][2];
		position[0][0][0] = -1;
		position[0][0][1] = 0;
		position[0][1][0] = 0;
		position[0][1][1] = 0;
		position[0][2][0] = -1;
		position[0][2][1] = 1;
		position[0][3][0] = -2;
		position[0][3][1] = 1;
		
		position[1][0][0] = -1;
		position[1][0][1] = 0;
		position[1][1][0] = -1;
		position[1][1][1] = 1;
		position[1][2][0] = 0;
		position[1][2][1] = 1;
		position[1][3][0] = 0;
		position[1][3][1] = 2;
		
		position[2][0][0] = -1;
		position[2][0][1] = 1;
		position[2][1][0] = -0;
		position[2][1][1] = 1;
		position[2][2][0] = -1;
		position[2][2][1] = 2;
		position[2][3][0] = -2;
		position[2][3][1] = 2;
		
		position[3][0][0] = -2;
		position[3][0][1] = 0;
		position[3][1][0] = -2;
		position[3][1][1] = 1;
		position[3][2][0] = -1;
		position[3][2][1] = 1;
		position[3][3][0] = -1;
		position[3][3][1] = 2;
		return position;
	}
	public static int[][][] J_MinoPosition(){
		int[][][]position = new int[4][4][2];
		position[0][0][0] = -1;
		position[0][0][1] = 0;
		position[0][1][0] = -1;
		position[0][1][1] = 1;
		position[0][2][0] = 0;
		position[0][2][1] = 1;
		position[0][3][0] = 1;
		position[0][3][1] = 1;
		
		position[1][0][0] = 0;
		position[1][0][1] = 0;
		position[1][1][0] = 1;
		position[1][1][1] = 0;
		position[1][2][0] = 0;
		position[1][2][1] = 1;
		position[1][3][0] = 0;
		position[1][3][1] = 2;
		
		position[2][0][0] = -1;
		position[2][0][1] = 1;
		position[2][1][0] = 0;
		position[2][1][1] = 1;
		position[2][2][0] = 1;
		position[2][2][1] = 1;
		position[2][3][0] = 1;
		position[2][3][1] = 2;
		
		position[3][0][0] = 0;
		position[3][0][1] = 0;
		position[3][1][0] = 0;
		position[3][1][1] = 1;
		position[3][2][0] = 0;
		position[3][2][1] = 2;
		position[3][3][0] = -1;
		position[3][3][1] = 2;
		return position;
	}
	public static int[][][] L_MinoPosition(){
		int[][][]position = new int[4][4][2];
		position[0][0][0] = 1;
		position[0][0][1] = 0;
		position[0][1][0] = 1;
		position[0][1][1] = 1;
		position[0][2][0] = 0;
		position[0][2][1] = 1;
		position[0][3][0] = -1;
		position[0][3][1] = 1;
		
		position[1][0][0] = 0;
		position[1][0][1] = 0;
		position[1][1][0] = 0;
		position[1][1][1] = 1;
		position[1][2][0] = 0;
		position[1][2][1] = 2;
		position[1][3][0] = 1;
		position[1][3][1] = 2;
		
		position[2][0][0] = 1;
		position[2][0][1] = 1;
		position[2][1][0] = 0;
		position[2][1][1] = 1;
		position[2][2][0] = -1;
		position[2][2][1] = 1;
		position[2][3][0] = -1;
		position[2][3][1] = 2;
		
		position[3][0][0] = -1;
		position[3][0][1] = 0;
		position[3][1][0] = 0;
		position[3][1][1] = 0;
		position[3][2][0] = 0;
		position[3][2][1] = 1;
		position[3][3][0] = 0;
		position[3][3][1] = 2;
		return position;
	}
	public static int[][][] T_MinoPosition(){
		int[][][]position = new int[4][4][2];
		position[0][0][0] = 0;
		position[0][0][1] = 0;
		position[0][1][0] = 0;
		position[0][1][1] = 1;
		position[0][2][0] = -1;
		position[0][2][1] = 1;
		position[0][3][0] = 1;
		position[0][3][1] = 1;
		
		position[1][0][0] = 0;
		position[1][0][1] = 0;
		position[1][1][0] = 0;
		position[1][1][1] = 1;
		position[1][2][0] = 0;
		position[1][2][1] = 2;
		position[1][3][0] = 1;
		position[1][3][1] = 1;
		
		position[2][0][0] = -1;
		position[2][0][1] = 1;
		position[2][1][0] = 0;
		position[2][1][1] = 1;
		position[2][2][0] = 1;
		position[2][2][1] = 1;
		position[2][3][0] = 0;
		position[2][3][1] = 2;
		
		position[3][0][0] = 0;
		position[3][0][1] = 0;
		position[3][1][0] = 0;
		position[3][1][1] = 1;
		position[3][2][0] = 0;
		position[3][2][1] = 2;
		position[3][3][0] = -1;
		position[3][3][1] = 1;
		return position;
	}
}


//package Mino;
//
//import java.awt.Color;
//
//public enum MinoType {
//	EMPTY(Color.black, MinoPosition.EmptyPosition()),
//	I_Mino(new Color(00,255,255), MinoPosition.I_MinoPosition()),
//	O_Mino(Color.yellow, MinoPosition.O_MinoPosition()),
//	Z_Mino(Color.red, MinoPosition.Z_MinoPosition()),
//	S_Mino(new Color(173,255,47), MinoPosition.S_MinoPosition()),
//	J_Mino(Color.blue, MinoPosition.J_MinoPosition()),
//	L_Mino(Color.orange, MinoPosition.L_MinoPosition()),
//	T_Mino(Color.magenta, MinoPosition.T_MinoPosition());
//	
//	
//	/* Mino의 색은 임의로 지정한다.*/
//	final private Color color;
//	/* Mino의 각 BaseMino의 position은 아래 MinoPosition class에서 받아온다.*/
//	final int[][][] position;
//	
//	private MinoType(Color color,int[][][] position) {
//		this.color = color;
//		this.position = position;
//	}
//	public Color getColor() {
//		return color;
//	}
//	public int[][] getPosition(MinoType type,int num){
//		return type.position[num];
//	}
//}
//
//
///*
// * Mino의 각 rotation별로의 BaseMino들의 위치.
// *
// * position[a][b][c]. 
// * a: rotation number
// * b: BaseMino number
// * c: BaseMino position
// * 
// * position[n][m][0]은 BaseMino의 Width (x좌표)를
// * position[n][m][1]은 BaseMino의 Height (y좌표)를 저장한다.
// * 
// * 		ex) position[2][2][1]: 2번째 rotation미노의 2번째블록의 Width(x좌표). 
// */
//class MinoPosition{
//	public static int[][][] EmptyPosition(){
//		return new int[4][4][2];
//	}
//	public static int[][][] O_MinoPosition() {
//		int position[][][] = new int[4][4][2];
//		position[0][0][0] = -1;
//		position[0][0][1] = 0;
//		position[0][1][0] = 0;
//		position[0][1][1] = 0;
//		position[0][2][0] = -1;
//		position[0][2][1] = 1;
//		position[0][3][0] = 0;
//		position[0][3][1] = 1;
//
//		position[1][0][0] = -1;
//		position[1][0][1] = 0;
//		position[1][1][0] = 0;
//		position[1][1][1] = 0;
//		position[1][2][0] = -1;
//		position[1][2][1] = 1;
//		position[1][3][0] = 0;
//		position[1][3][1] = 1;
//
//		position[2][0][0] = -1;
//		position[2][0][1] = 0;
//		position[2][1][0] = 0;
//		position[2][1][1] = 0;
//		position[2][2][0] = -1;
//		position[2][2][1] = 1;
//		position[2][3][0] = 0;
//		position[2][3][1] = 1;
//
//		position[3][0][0] = -1;
//		position[3][0][1] = 0;
//		position[3][1][0] = 0;
//		position[3][1][1] = 0;
//		position[3][2][0] = -1;
//		position[3][2][1] = 1;
//		position[3][3][0] = 0;
//		position[3][3][1] = 1;
//		return position;
//	}
//	public static int[][][] I_MinoPosition(){
//		int [][][] position = new int[4][4][2];
//		position[0][0][0] = -2;
//		position[0][0][1] = 0;
//		position[0][1][0] = -1;
//		position[0][1][1] = 0;
//		position[0][2][0] = 0;
//		position[0][2][1] = 0;
//		position[0][3][0] = 1;
//		position[0][3][1] = 0;
//		
//		position[1][0][0] = 0;
//		position[1][0][1] = -1;
//		position[1][1][0] = 0;
//		position[1][1][1] = 0;
//		position[1][2][0] = 0;
//		position[1][2][1] = 1;
//		position[1][3][0] = 0;
//		position[1][3][1] = 2;
//		
//		position[2][0][0] = -2;
//		position[2][0][1] = 1;
//		position[2][1][0] = -1;
//		position[2][1][1] = 1;
//		position[2][2][0] = 0;
//		position[2][2][1] = 1;
//		position[2][3][0] = 1;
//		position[2][3][1] = 1;
//		
//		position[3][0][0] = -1;
//		position[3][0][1] = -1;
//		position[3][1][0] = -1;
//		position[3][1][1] = 0;
//		position[3][2][0] = -1;
//		position[3][2][1] = 1;
//		position[3][3][0] = -1;
//		position[3][3][1] = 2;
//		return position;
//	}
//	public static int[][][] Z_MinoPosition(){
//		int[][][]position = new int[4][4][2];
//		position[0][0][0] = -1;
//		position[0][0][1] = 0;
//		position[0][1][0] = 0;
//		position[0][1][1] = 0;
//		position[0][2][0] = 0;
//		position[0][2][1] = 1;
//		position[0][3][0] = 1;
//		position[0][3][1] = 1;
//		
//		position[1][0][0] = 1;
//		position[1][0][1] = 0;
//		position[1][1][0] = 1;
//		position[1][1][1] = 1;
//		position[1][2][0] = 0;
//		position[1][2][1] = 1;
//		position[1][3][0] = 0;
//		position[1][3][1] = 2;
//		
//		position[2][0][0] = -1;
//		position[2][0][1] = 1;
//		position[2][1][0] = 0;
//		position[2][1][1] = 1;
//		position[2][2][0] = 0;
//		position[2][2][1] = 2;
//		position[2][3][0] = 1;
//		position[2][3][1] = 2;
//		
//		position[3][0][0] = 0;
//		position[3][0][1] = 0;
//		position[3][1][0] = 0;
//		position[3][1][1] = 1;
//		position[3][2][0] = -1;
//		position[3][2][1] = 1;
//		position[3][3][0] = -1;
//		position[3][3][1] = 2;
//		return position;
//	}
//	public static int[][][] S_MinoPosition(){
//		int[][][]position = new int[4][4][2];
//		position[0][0][0] = -1;
//		position[0][0][1] = 0;
//		position[0][1][0] = 0;
//		position[0][1][1] = 0;
//		position[0][2][0] = -1;
//		position[0][2][1] = 1;
//		position[0][3][0] = -2;
//		position[0][3][1] = 1;
//		
//		position[1][0][0] = -1;
//		position[1][0][1] = 0;
//		position[1][1][0] = -1;
//		position[1][1][1] = 1;
//		position[1][2][0] = 0;
//		position[1][2][1] = 1;
//		position[1][3][0] = 0;
//		position[1][3][1] = 2;
//		
//		position[2][0][0] = -1;
//		position[2][0][1] = 1;
//		position[2][1][0] = -0;
//		position[2][1][1] = 1;
//		position[2][2][0] = -1;
//		position[2][2][1] = 2;
//		position[2][3][0] = -2;
//		position[2][3][1] = 2;
//		
//		position[3][0][0] = -2;
//		position[3][0][1] = 0;
//		position[3][1][0] = -2;
//		position[3][1][1] = 1;
//		position[3][2][0] = -1;
//		position[3][2][1] = 1;
//		position[3][3][0] = -1;
//		position[3][3][1] = 2;
//		return position;
//	}
//	public static int[][][] J_MinoPosition(){
//		int[][][]position = new int[4][4][2];
//		position[0][0][0] = -1;
//		position[0][0][1] = 0;
//		position[0][1][0] = -1;
//		position[0][1][1] = 1;
//		position[0][2][0] = 0;
//		position[0][2][1] = 1;
//		position[0][3][0] = 1;
//		position[0][3][1] = 1;
//		
//		position[1][0][0] = 0;
//		position[1][0][1] = 0;
//		position[1][1][0] = 1;
//		position[1][1][1] = 0;
//		position[1][2][0] = 0;
//		position[1][2][1] = 1;
//		position[1][3][0] = 0;
//		position[1][3][1] = 2;
//		
//		position[2][0][0] = -1;
//		position[2][0][1] = 1;
//		position[2][1][0] = 0;
//		position[2][1][1] = 1;
//		position[2][2][0] = 1;
//		position[2][2][1] = 1;
//		position[2][3][0] = 1;
//		position[2][3][1] = 2;
//		
//		position[3][0][0] = 0;
//		position[3][0][1] = 0;
//		position[3][1][0] = 0;
//		position[3][1][1] = 1;
//		position[3][2][0] = 0;
//		position[3][2][1] = 2;
//		position[3][3][0] = -1;
//		position[3][3][1] = 2;
//		return position;
//	}
//	public static int[][][] L_MinoPosition(){
//		int[][][]position = new int[4][4][2];
//		position[0][0][0] = 1;
//		position[0][0][1] = 0;
//		position[0][1][0] = 1;
//		position[0][1][1] = 1;
//		position[0][2][0] = 0;
//		position[0][2][1] = 1;
//		position[0][3][0] = -1;
//		position[0][3][1] = 1;
//		
//		position[1][0][0] = 0;
//		position[1][0][1] = 0;
//		position[1][1][0] = 0;
//		position[1][1][1] = 1;
//		position[1][2][0] = 0;
//		position[1][2][1] = 2;
//		position[1][3][0] = 1;
//		position[1][3][1] = 2;
//		
//		position[2][0][0] = 1;
//		position[2][0][1] = 1;
//		position[2][1][0] = 0;
//		position[2][1][1] = 1;
//		position[2][2][0] = -1;
//		position[2][2][1] = 1;
//		position[2][3][0] = -1;
//		position[2][3][1] = 2;
//		
//		position[3][0][0] = -1;
//		position[3][0][1] = 0;
//		position[3][1][0] = 0;
//		position[3][1][1] = 0;
//		position[3][2][0] = 0;
//		position[3][2][1] = 1;
//		position[3][3][0] = 0;
//		position[3][3][1] = 2;
//		return position;
//	}
//	public static int[][][] T_MinoPosition(){
//		int[][][]position = new int[4][4][2];
//		position[0][0][0] = 0;
//		position[0][0][1] = 0;
//		position[0][1][0] = 0;
//		position[0][1][1] = 1;
//		position[0][2][0] = -1;
//		position[0][2][1] = 1;
//		position[0][3][0] = 1;
//		position[0][3][1] = 1;
//		
//		position[1][0][0] = 0;
//		position[1][0][1] = 0;
//		position[1][1][0] = 0;
//		position[1][1][1] = 1;
//		position[1][2][0] = 0;
//		position[1][2][1] = 2;
//		position[1][3][0] = 1;
//		position[1][3][1] = 1;
//		
//		position[2][0][0] = -1;
//		position[2][0][1] = 1;
//		position[2][1][0] = 0;
//		position[2][1][1] = 1;
//		position[2][2][0] = 1;
//		position[2][2][1] = 1;
//		position[2][3][0] = 0;
//		position[2][3][1] = 2;
//		
//		position[3][0][0] = 0;
//		position[3][0][1] = 0;
//		position[3][1][0] = 0;
//		position[3][1][1] = 1;
//		position[3][2][0] = 0;
//		position[3][2][1] = 2;
//		position[3][3][0] = -1;
//		position[3][3][1] = 1;
//		return position;
//	}
//}
//
package Mino;

import java.awt.Color;

public enum MinoType {
	EMPTY(Color.black, MinoPosition.EmptyPosition()),
	I_Mino(new Color(00,255,255), MinoPosition.I_MinoPosition()),
	O_Mino(Color.yellow, MinoPosition.O_MinoPosition()),
	Z_Mino(Color.red, MinoPosition.Z_MinoPosition()),
	S_Mino(new Color(173,255,47), MinoPosition.S_MinoPosition()),
	J_Mino(Color.blue, MinoPosition.J_MinoPosition()),
	L_Mino(Color.orange, MinoPosition.L_MinoPosition()),
	T_Mino(Color.magenta, MinoPosition.T_MinoPosition());
	
	
	/* Mino의 색은 임의로 지정한다.*/
	final private Color color;
	/* Mino의 각 BaseMino의 position은 아래 MinoPosition class에서 받아온다.*/
	final int[][][] position;
	
	private MinoType(Color color,int[][][] position) {
		this.color = color;
		this.position = position;
	}
	public Color getColor() {
		return color;
	}
	public int[][] getPosition(MinoType type,int num){
		return type.position[num];
	}
}


/*
 * Mino의 각 rotation별로의 BaseMino들의 위치.
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
class MinoPosition{
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


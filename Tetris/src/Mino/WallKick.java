package Mino;

/*
 * WallKick은 "https://tetris.fandom.com/wiki/SRS"을 참조함.
 */

public  class WallKick {
	public static int[][] getOffset(MinoType type, int rotation, int direction){
		switch (type) {
		case O_Mino:
			return new int[5][2];
		case I_Mino:
			int offset1[][][][] = new int[4][4][5][2];
			/* rotate 0 -> rotate 1 */
			offset1[0][0][0][0] = 0;
			offset1[0][0][0][1] = 0;
			offset1[0][0][1][0] = -2;
			offset1[0][0][1][1] = 0;
			offset1[0][0][2][0] = 1;
			offset1[0][0][2][1] = 0;
			offset1[0][0][3][0] = -2;
			offset1[0][0][3][1] = -1;
			offset1[0][0][4][0] = 1;
			offset1[0][0][4][1] = 2;
			
			/* 0 -> 3 */
			offset1[0][1][0][0] = 0;
			offset1[0][1][0][1] = 0;
			offset1[0][1][1][0] = -1;
			offset1[0][1][1][1] = 0;
			offset1[0][1][2][0] = 2;
			offset1[0][1][2][1] = 0;
			offset1[0][1][3][0] = -1;
			offset1[0][1][3][1] = 2;
			offset1[0][1][4][0] = 2;
			offset1[0][1][4][1] = -1;
			
			/* 1 -> 2 */
			offset1[1][0][0][0] = 0;
			offset1[1][0][0][1] = 0;
			offset1[1][0][1][0] = -1;
			offset1[1][0][1][1] = 0;
			offset1[1][0][2][0] = 2;
			offset1[1][0][2][1] = 0;
			offset1[1][0][3][0] = -1;
			offset1[1][0][3][1] = 2;
			offset1[1][0][4][0] = 2;
			offset1[1][0][4][1] = -1;
			
			/* 1 -> 0 */
			offset1[1][1][0][0] = 0;
			offset1[1][1][0][1] = 0;
			offset1[1][1][1][0] = 2;
			offset1[1][1][1][1] = 0;
			offset1[1][1][2][0] = -1;
			offset1[1][1][2][1] = 0;
			offset1[1][1][3][0] = 2;
			offset1[1][1][3][1] = 1;
			offset1[1][1][4][0] = -1;
			offset1[1][1][4][1] = -2;
			
			/* 2 -> 3 */
			offset1[2][0][0][0] = 0;
			offset1[2][0][0][1] = 0;
			offset1[2][0][1][0] = 2;
			offset1[2][0][1][1] = 0;
			offset1[2][0][2][0] = -1;
			offset1[2][0][2][1] = 0;
			offset1[2][0][3][0] = 2;
			offset1[2][0][3][1] = 1;
			offset1[2][0][4][0] = -1;
			offset1[2][0][4][1] = -2;
			
			/* 2 -> 1 */
			offset1[2][1][0][0] = 0;
			offset1[2][1][0][1] = 0;
			offset1[2][1][1][0] = 1;
			offset1[2][1][1][1] = 0;
			offset1[2][1][2][0] = -2;
			offset1[2][1][2][1] = 0;
			offset1[2][1][3][0] = 1;
			offset1[2][1][3][1] = -2;
			offset1[2][1][4][0] = -2;
			offset1[2][1][4][1] = 1;
			
			/* 3 -> 0 */
			offset1[3][0][0][0] = 0;
			offset1[3][0][0][1] = 0;
			offset1[3][0][1][0] = 1;
			offset1[3][0][1][1] = 0;
			offset1[3][0][2][0] = -2;
			offset1[3][0][2][1] = 0;
			offset1[3][0][3][0] = 1;
			offset1[3][0][3][1] = -2;
			offset1[3][0][4][0] = -2;
			offset1[3][0][4][1] = 1;
			
			/* 3 -> 2 */
			offset1[3][1][0][0] = 0;
			offset1[3][1][0][1] = 0;
			offset1[3][1][1][0] = -2;
			offset1[3][1][1][1] = 0;
			offset1[3][1][2][0] = 1;
			offset1[3][1][2][1] = 0;
			offset1[3][1][3][0] = -2;
			offset1[3][1][3][1] = -1;
			offset1[3][1][4][0] = 1;
			offset1[3][1][4][1] = 2;
			
			return offset1[rotation][direction];
		default: 
			int offset2[][][][] = new int[4][4][5][2];
			/* rotate 0 -> rotate 1 */
			offset2[0][0][0][0] = 0;
			offset2[0][0][0][1] = 0;
			offset2[0][0][1][0] = -1;
			offset2[0][0][1][1] = 0;
			offset2[0][0][2][0] = -1;
			offset2[0][0][2][1] = 1;
			offset2[0][0][3][0] = 0;
			offset2[0][0][3][1] = -2;
			offset2[0][0][4][0] = -1;
			offset2[0][0][4][1] = -2;
			
			/* 0 -> 3 */
			offset2[0][1][0][0] = 0;
			offset2[0][1][0][1] = 0;
			offset2[0][1][1][0] = 1;
			offset2[0][1][1][1] = 0;
			offset2[0][1][2][0] = 1;
			offset2[0][1][2][1] = 1;
			offset2[0][1][3][0] = 0;
			offset2[0][1][3][1] = -2;
			offset2[0][1][4][0] = 1;
			offset2[0][1][4][1] = -2;
			
			/* 1 -> 2 */
			offset2[1][0][0][0] = 0;
			offset2[1][0][0][1] = 0;
			offset2[1][0][1][0] = 1;
			offset2[1][0][1][1] = 0;
			offset2[1][0][2][0] = 1;
			offset2[1][0][2][1] = -1;
			offset2[1][0][3][0] = 0;
			offset2[1][0][3][1] = 2;
			offset2[1][0][4][0] = 1;
			offset2[1][0][4][1] = 2;
			
			/* 1 -> 0 */
			offset2[1][1][0][0] = 0;
			offset2[1][1][0][1] = 0;
			offset2[1][1][1][0] = 1;
			offset2[1][1][1][1] = 0;
			offset2[1][1][2][0] = 1;
			offset2[1][1][2][1] = -1;
			offset2[1][1][3][0] = 0;
			offset2[1][1][3][1] = 2;
			offset2[1][1][4][0] = 1;
			offset2[1][1][4][1] = 2;
			
			/* 2 -> 3 */
			offset2[2][0][0][0] = 0;
			offset2[2][0][0][1] = 0;
			offset2[2][0][1][0] = 1;
			offset2[2][0][1][1] = 0;
			offset2[2][0][2][0] = 1;
			offset2[2][0][2][1] = 1;
			offset2[2][0][3][0] = 0;
			offset2[2][0][3][1] = -2;
			offset2[2][0][4][0] = 1;
			offset2[2][0][4][1] = -2;
			
			/* 2 -> 1 */
			offset2[2][1][0][0] = 0;
			offset2[2][1][0][1] = 0;
			offset2[2][1][1][0] = -1;
			offset2[2][1][1][1] = 0;
			offset2[2][1][2][0] = -1;
			offset2[2][1][2][1] = 1;
			offset2[2][1][3][0] = 0;
			offset2[2][1][3][1] = -2;
			offset2[2][1][4][0] = -1;
			offset2[2][1][4][1] = -2;
			
			/* 3 -> 0 */
			offset2[3][0][0][0] = 0;
			offset2[3][0][0][1] = 0;
			offset2[3][0][1][0] = -1;
			offset2[3][0][1][1] = 0;
			offset2[3][0][2][0] = -1;
			offset2[3][0][2][1] = -1;
			offset2[3][0][3][0] = 0;
			offset2[3][0][3][1] = 2;
			offset2[3][0][4][0] = -1;
			offset2[3][0][4][1] = 2;
			
			/* 3 -> 2 */
			offset2[3][1][0][0] = 0;
			offset2[3][1][0][1] = 0;
			offset2[3][1][1][0] = -1;
			offset2[3][1][1][1] = 0;
			offset2[3][1][2][0] = -1;
			offset2[3][1][2][1] = -1;
			offset2[3][1][3][0] = 0;
			offset2[3][1][3][1] = 2;
			offset2[3][1][4][0] = -1;
			offset2[3][1][4][1] = 2;

			return offset2[rotation][direction];
		}
	}
}

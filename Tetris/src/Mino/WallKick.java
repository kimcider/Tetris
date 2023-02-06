package Mino;

import Tetris.Point;
/*
 * WallKick은 "https://tetris.fandom.com/wiki/SRS"을 참조함.
 * Point offset[a][b][c];
 * a: rotate 전의 rotate state
 * b: rotate direction
 * 	b == 0 : right rotate
 * 	b == 1 : left rotate
 * c: test case number. 총 5개의 testcase를 검사
 * 
 *	위 참고 사이트의 y좌표는 바닥이 0이고 위로 갈수록 높은 값을 취한다. 
 *	그러나 이 프로그램의 좌표계는 천장이 0이고 아래로 갈수록 높은 값을 취하기에 offset의 y값을 음수를 취해 사용한다. 
 */

public  class WallKick {
	public static Point[] getRotationOffset(MinoType type, int rotation, int direction){
		switch (type) {
		case O_Mino:
			Point offset0[] = new Point[5];
			for(int i = 0; i < 4; i++) {
						offset0[i] = new Point(0,0);
			}
			return offset0;
		case I_Mino:
			Point offset1[][][] = new Point[4][2][5];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 2; j++) {
					for(int k = 0; k < 5; k++) {
						offset1[i][j][k] = new Point();
					}
				}
			}
			/* rotate 0 -> rotate 1 */
			offset1[0][0][0].setPoint(0, 0);
			offset1[0][0][1].setPoint(-2, 0);
			offset1[0][0][2].setPoint(1, 0);
			offset1[0][0][3].setPoint(-2, -1);
			offset1[0][0][4].setPoint(1, 2);
			
			/* 0 -> 3 */
			offset1[0][1][0].setPoint(0, 0);
			offset1[0][1][1].setPoint(-1, 0);
			offset1[0][1][2].setPoint(2, 0);
			offset1[0][1][3].setPoint(-1, 2);
			offset1[0][1][4].setPoint(2, -1);
			
			/* 1 -> 2 */
			offset1[1][0][0].setPoint(0, 0);
			offset1[1][0][1].setPoint(-1, 0);
			offset1[1][0][2].setPoint(2, 0);
			offset1[1][0][3].setPoint(-1, 2);
			offset1[1][0][4].setPoint(2, -1);;
			
			/* 1 -> 0 */
			offset1[1][1][0].setPoint(0, 0);
			offset1[1][1][1].setPoint(2, 0);
			offset1[1][1][2].setPoint(-1, 0);
			offset1[1][1][3].setPoint(2, 1);
			offset1[1][1][4].setPoint(-1, -2);
			
			/* 2 -> 3 */
			offset1[2][0][0].setPoint(0, 0);
			offset1[2][0][1].setPoint(2, 0);
			offset1[2][0][2].setPoint(-1, 0);
			offset1[2][0][3].setPoint(2, 1);
			offset1[2][0][4].setPoint(-1, -2);
			
			/* 2 -> 1 */
			offset1[2][1][0].setPoint(0, 0);
			offset1[2][1][1].setPoint(1, 0);
			offset1[2][1][2].setPoint(-2, 0);
			offset1[2][1][3].setPoint(1, -2);
			offset1[2][1][4].setPoint(-2, 1);
			
			/* 3 -> 0 */
			offset1[3][0][0].setPoint(0, 0);
			offset1[3][0][1].setPoint(1, 0);
			offset1[3][0][2].setPoint(-2, 0);
			offset1[3][0][3].setPoint(1, -2);
			offset1[3][0][4].setPoint(-2, 1);
			
			/* 3 -> 2 */
			offset1[3][1][0].setPoint(0, 0);
			offset1[3][1][1].setPoint(-2, 0);
			offset1[3][1][2].setPoint(1, 0);
			offset1[3][1][3].setPoint(-2, -1);
			offset1[3][1][4].setPoint(1, 2);
			
			return offset1[rotation][direction];
			
		default: 
			Point offset2[][][] = new Point[4][2][5];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 2; j++) {
					for(int k = 0; k < 5; k++) {
						offset2[i][j][k] = new Point();
					}
				}
			}
			/* rotate 0 -> rotate 1 */
			offset2[0][0][0].setPoint(0, 0);
			offset2[0][0][1].setPoint(-1, 0);
			offset2[0][0][2].setPoint(-1, 1);
			offset2[0][0][3].setPoint(0, -2);
			offset2[0][0][4].setPoint(-1, -2);
			
			/* 0 -> 3 */
			offset2[0][1][0].setPoint(0, 0);
			offset2[0][1][1].setPoint(1, 0);
			offset2[0][1][2].setPoint(1, 1);
			offset2[0][1][3].setPoint(0, -2);
			offset2[0][1][4].setPoint(1, -2);
			
			/* 1 -> 2 */
			offset2[1][0][0].setPoint(0, 0);
			offset2[1][0][1].setPoint(1, 0);
			offset2[1][0][2].setPoint(1, -1);
			offset2[1][0][3].setPoint(0, 2);
			offset2[1][0][4].setPoint(1, 2);
			
			/* 1 -> 0 */
			offset2[1][1][0].setPoint(0, 0);
			offset2[1][1][1].setPoint(1, 0);
			offset2[1][1][2].setPoint(1, -1);
			offset2[1][1][3].setPoint(0, 2);
			offset2[1][1][4].setPoint(1, 2);
			
			/* 2 -> 3 */
			offset2[2][0][0].setPoint(0, 0);
			offset2[2][0][1].setPoint(1, 0);
			offset2[2][0][2].setPoint(1, 1);
			offset2[2][0][3].setPoint(0, -2);
			offset2[2][0][4].setPoint(1, -2);
			
			/* 2 -> 1 */
			offset2[2][1][0].setPoint(0, 0);
			offset2[2][1][1].setPoint(-1, 0);
			offset2[2][1][2].setPoint(-1, 1);
			offset2[2][1][3].setPoint(0, -2);
			offset2[2][1][4].setPoint(-1, -2);
			
			/* 3 -> 0 */
			offset2[3][0][0].setPoint(0, 0);
			offset2[3][0][1].setPoint(-1, 0);
			offset2[3][0][2].setPoint(-1, -1);
			offset2[3][0][3].setPoint(0, 2);
			offset2[3][0][4].setPoint(-1, 2);
			
			/* 3 -> 2 */
			offset2[3][1][0].setPoint(0, 0);
			offset2[3][1][1].setPoint(-1, 0);
			offset2[3][1][2].setPoint(-1, -1);
			offset2[3][1][3].setPoint(0, 2);
			offset2[3][1][4].setPoint(-1, 2);

			return offset2[rotation][direction];
		}
	}
}

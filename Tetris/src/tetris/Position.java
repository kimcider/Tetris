package tetris;
import tetris.MinoType;


/*
 * Mino의 첫 번째 BaseMino에 대한 다른 BaseMino들의 상대적인 위치.
 * 
 * position[0]이 첫번째 BaseMino를, 
 * position[1]이 두번째 BaseMino를 ...... position[3]이 네번째 BaseMino를 지칭한다.
 * 
 * position[n][0]은 첫번째 BaseMino대비 n번째 baseMino의 Height를,
 * position[n][1]은 첫번째 BaseMino대비 n번째 baseMino의 Width를 지칭한다.
 */
public class Position {
	private int[][] position = new int[4][2];
	private MinoType type;
	public Position(MinoType type) {
		this.type = type;
		/* 첫번째 BaseMino가 기준이기에 첫번째 BaseMino의 position은 0으로 설정한다. */
		position[0][0] = 0;
		position[0][1] = 0;
		
		switch(type) {
		case O_Mino:
			position[1][0] = 1;
			position[1][1] = 0;

			position[2][0] = 0;
			position[2][1] = 1;

			position[3][0] = 1;
			position[3][1] = 1;
			break;
		}
	}
	public int[][] getPosition() {
		return position;
	}
	public int[] getPosition(int num) {
		return position[num];
	}
}

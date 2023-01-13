package Mino;

import static tetris.Main.BLOCK_SIZE;
import tetris.MinoType;


/*
 * Mino는 baseMino 4개로 구성된다. 
 * 각각의 baseMino는 각자의 position정보를 갖는다. 
 * 이 position정보는 특정 지점에 대한 상대적 위치이다. 
 * setPosition(h,w)로 미노에 들어오는 위치를 기반으로 하고, 각각의 baseMino들의 상대적인 위치정보를 더해서 미노가 BOARD에 출력되게된다. 
 * 
 * 모든 mino는 4개의 회전상태를 갖는다. 
 * 
 * 모든 미노는 7개 중 하나의 타입을 갖는다. 
 * 
 * 
 */
public class Mino {
	private BaseMino[] mino = new BaseMino[4];
	private int rotation;
	private MinoType type;
	
	public Mino(MinoType type) {
		this.type = type;
		rotation = 0;
		
		/*
		 * enumeration MinoType에서 받아온 각 baseMino의 position을 기반으로 각각의 baseMino의 위치를 지정. 
		 * 각각의 baseMino는 기준점으로부터 상하좌우로 어느정도 떨어져있는지로 표현된다. 
		 * 가령, I_Mino의 경우 각 baseMino는 차례대로 {0,0},{1,0},{2,0},{3,0} 이런식으로 표현된다. 
		 */
		int[][] temp_position = new int[4][2];
		temp_position = MinoType.EMPTY.getPosition(type,0);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(type);
			mino[i].setPosition(temp_position[i]);
		}
	}
	
	/*
	 * h와 w를 기준삼고, 각각의 mino들의 상대적인 위치를 활용하여 Board의 어느 위치에 Mino를 위치시킬지를 결정한다.
	 */
	public void setPosition(int h, int w) {
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(h * BLOCK_SIZE + mino[i].getHeightPosition() * BLOCK_SIZE + 1,w * BLOCK_SIZE + mino[i].getWidthPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	
	/*
	 * Mino는 그 자체로 JPanel이 아니다.
	 * 따라서 JPanel과 관련된 작업을 수행해야 할 경우를 위해 각각의 basemino들을 반환해준다. 
	 */
	public BaseMino getBaseMino(int num) {
		return mino[num];
	}
	
	/*
	 * 미노를 회전시킨다.
	 */
	public void rotate(int h, int w) {
		rotation = (rotation + 1) % 4;
		int[][] temp_position = new int[4][2];
		temp_position = MinoType.EMPTY.getPosition(type, rotation);
		for(int i = 0; i < 4; i++) {
			mino[i].setPosition(temp_position[i]);
		}
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(h * BLOCK_SIZE + mino[i].getHeightPosition() * BLOCK_SIZE + 1,w * BLOCK_SIZE + mino[i].getWidthPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	
	/*
	 *  어떤 종류의 미노인지를 반환한다.
	 */
	public MinoType getType() {
		return type;
	}
}

package Mino;

import static tetris.Main.BLOCK_SIZE;

import tetris.MinoType;
import tetris.Position;

public class Mino {
	BaseMino[] mino = new BaseMino[4];
	Position position;
	public Mino(MinoType type) {
		initMino(type);
	}
	
	public void initMino(MinoType type) {
		position = new Position(type);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(type);
			int[] temp_position = new int[2];
			temp_position = position.getPosition(i);
			mino[i].setPosition(temp_position);
		}
	}
	/*
	 * Position.Java에서 받아온 Mino별 Position을 바탕으로 각 BaseMino들의 위치를 설
	 */
	public void setPosition(int h, int w) {
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(h * BLOCK_SIZE + mino[i].getHeightPosition() * BLOCK_SIZE + 1,w * BLOCK_SIZE + mino[i].getWidthPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	public BaseMino getBaseMino(int num) {
		return mino[num];
	}
}

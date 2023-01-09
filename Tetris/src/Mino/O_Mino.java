package Mino;

import tetris.MinoType;
import tetris.Position;
import static tetris.Main.*;
public class O_Mino {
	BaseMino[] mino = new BaseMino[4];
	Position position;
	public O_Mino() {
		position = new Position(MinoType.O_Mino);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(MinoType.O_Mino);
			int[] temp_position = new int[2];
			temp_position = position.getPosition(i);
			mino[i].setPosition(temp_position);
		}
	}
	public void setPosition(int h, int w) {
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(h * BLOCK_SIZE + mino[i].getHeightPosition() * BLOCK_SIZE + 1,w * BLOCK_SIZE + mino[i].getWidthPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	public BaseMino getBaseMino(int num) {
		return mino[num];
	}
}

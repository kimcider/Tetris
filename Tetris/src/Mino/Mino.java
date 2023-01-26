package Mino;

import javax.swing.JPanel;
import static Tetris.Main.*;

import Board.Board;

/*
 * Mino는 baseMino 4개로 구성된다. 
 * 각각의 baseMino는 각자의 position정보를 갖는다. 
 * 이 position정보는 특정 지점에 대한 상대적 위치이다. 
 * setPosition(h,w)로 미노에 들어오는 위치를 기반으로 하고, 각각의 baseMino들의 상대적인 위치정보를 더해서 미노가 BOARD에 출력되게된다. 
 * 
 * 모든 mino는 4개의 회전상태를 갖는다. 
 * 
 * 모든 미노는 7개 중 하나의 타입을 갖는다. 
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
		temp_position = MinoType.EMPTY.getBaseMinoRelativePosition(type,0);
		for(int i = 0; i < 4; i++) {
			mino[i] = new BaseMino(type);
			mino[i].setPosition(temp_position[i]);
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
	 *  어떤 종류의 미노인지를 반환한다.
	 */
	public MinoType getType() {
		return type;
	}
	
	/*
	 * Mino를 Board에 부착.
	 * Mino 자체는 panel이 아니기 때문에 baseMino를 얻어와 각각의 baseMino들을 Board에 붙여준다.
	 */
	public void addMinoToBoard(JPanel board, int x, int y) {
		this.setPosition(x, y);
		for(int i = 0; i < 4; i++) {
			board.add(this.getBaseMino(i));
		}
		board.repaint();
	}
	
	/* 
	 * Mino를 OtherBoard에 부착.
	 * Mino 자체는 Panel이 아니기 때문에 BaseMino를 얻어와 각각의 baseMino들을 OtherBoard에 붙여준다.
	 * 
	 * 다음의 몇번째 미노인지에 따라서 미노가 출력되는 y좌표가 달라진다.
	 */
	public void addMinoToBoard(JPanel board, int x, int y, int size, int number) {
		this.setPosition(x, y + (number * 4) , size);
		for(int i = 0;i < 4; i++) {
			board.add(this.getBaseMino(i));
		}
		board.repaint();
	}
	
	/*
	 * Mino를 Board에서 제거. 
	 * Mino 자체는 Panel이 아니기 때문에 baseMino를 얻어와 각각의 baseMino를 Board에서 떼어준다.
	 */
	public void removeMinoFromBoard(JPanel board) {
		for(int i = 0; i < 4; i++) {
			board.remove(this.getBaseMino(i));
		}
	}

	/*
	 * h와 w를 기준삼고, 각각의 mino들의 상대적인 위치를 활용하여 Board의 어느 위치에 Mino를 위치시킬지를 결정한다.
	 * 기본적으로 Board에 넣는 것을 전제로 하기에 BLOCK_SIZE를 기준으로 한다. 
	 */
	public void setPosition(int w, int h) {
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(w * BLOCK_SIZE + mino[i].getWidthPosition() * BLOCK_SIZE + 1,h * BLOCK_SIZE + mino[i].getHeightPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	
	/*
	 * OtherBoard에 넣기 위해, OtherBoard의 블록 사이즈를 기준으로 한 setPosition의 overloading. 
	 */
	public void setPosition(int w, int h, int size) {
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(w * OTHER_BOARD_BLOCK_SIZE + mino[i].getWidthPosition() * OTHER_BOARD_BLOCK_SIZE + 1,h * OTHER_BOARD_BLOCK_SIZE + mino[i].getHeightPosition() * OTHER_BOARD_BLOCK_SIZE + 1, OTHER_BOARD_BLOCK_SIZE - 2,OTHER_BOARD_BLOCK_SIZE - 2);
		}
	}

	/*
	 * 미노를 회전시킨다.
	 */
	public void rotate(int w, int h, int rotate) {
		rotation = (rotation + rotate + 4) % 4;
		int[][] temp_position = new int[4][2];
		temp_position = MinoType.EMPTY.getBaseMinoRelativePosition(type, rotation);
		for(int i = 0; i < 4; i++) {
			mino[i].setPosition(temp_position[i]);
		}
		for(int i = 0; i < 4; i++) {
			mino[i].setBounds(w * BLOCK_SIZE + mino[i].getWidthPosition() * BLOCK_SIZE + 1,h * BLOCK_SIZE + mino[i].getHeightPosition() * BLOCK_SIZE + 1, BLOCK_SIZE - 2,BLOCK_SIZE - 2);
		}
	}
	
	public int getRotation() {
		return rotation;
	}
	
	public void setRotate(int rotate, int x, int y) {
		while(rotation != rotate) {
			rotate(x, y, 1);
		}
	}
	
	/*
	 * BaseMino들의 위치를 Board기준의 x,y좌표로 변환 후, board의 checkToMove()함수를 호출해 해당 위치로 이동할 수 있는지 여부를 체크. 
	 * 이동할 수 있으면 true를, 없으면 false를 반환. 
	 */
	public boolean checkToMove(Board board, int x, int y, int rotate){
		int [][] position = getPosition(x,y,rotate);
		
		/*
		 * 해당 포지션으로 이동할 수 있는지  Empty상태인지를 Board에서 확인. 
		 */
		boolean answer = board.checkToMove(position);
		if(answer == true) {
			return true;
		}
		else return false;
	}
	
	/*
	 * 각 미노들의 Board내에서의 position을 반환. 
	 * 
	 * 반환값: 
	 * position[a][b]
	 * a: baseMino number
	 * b: x,y좌표
	 * 	b == 0: x좌표
	 * 	b == 1: y좌표
	 * 
	 * ex) position[2][1] == 3번째 BaseMino의 Board에서의 y좌표.  
	 */
	public int[][] getPosition(int x, int y, int rotate){
		int[][] baseMinoPosition = new int[4][2];
		baseMinoPosition = type.getBaseMinoRelativePosition(this.getType(), rotate);
		
		int [][] position = new int[4][2];
		for(int i = 0; i < 4; i++) {
			position[i][0] = x + baseMinoPosition[i][0];
			position[i][1] = y + baseMinoPosition[i][1];
		}
		return position;
	}
}

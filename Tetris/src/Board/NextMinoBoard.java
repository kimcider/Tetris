package Board;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Random;

import static Tetris.Main.*;
import Mino.Mino;
import Mino.MinoType;

public class NextMinoBoard extends JPanel{
	int height;
	int width;
	JPanel gameBoard;
	Mino[][] nextMinoList;
	int index;
	
	public NextMinoBoard(JPanel board) {
		this.gameBoard = board;
		setVisible(true);
		width = OTHER_BOARD_BLOCK_SIZE * 6;
		height = OTHER_BOARD_BLOCK_SIZE * 4 * 6;
		setSize(width,height);
		setBackground(Color.black);
		setBounds(BOARD_START_WIDTH + BLOCK_SIZE * (BOARD_WIDTH + 1), BOARD_START_HEIGHT, getWidth(),getHeight());
		
		index = 0;
		nextMinoList = new Mino[2][7];
		for(int i = 0; i < 2; i++) {
			nextMinoList[i] = initMinoList();
		}
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(10, 10, width-20, height-20);
		
	}
	
	/*
	 * minoList내의 mino들의 순서를 랜덤하게 섞는다.
	 */
	public static void randomizeMino(Mino[] ary) {
		Random rd = new Random();
		int length = ary.length;
		for(int i = length - 1; i > 0; i--) {
			int j = rd.nextInt(i + 1);
			Mino temp = ary[i];
			ary[i] = ary[j];
			ary[j] = temp;
		}
	}
	
	/*
	 * 다음 7개 미노리스트를 생성한 후, 그들의 순서를 섞는다.
	 */
	public Mino[] initMinoList() {
		Mino[] minoList = new Mino[7];		
		minoList[0] = new Mino(MinoType.I_Mino);
		minoList[1] = new Mino(MinoType.O_Mino);
		minoList[2] = new Mino(MinoType.S_Mino);
		minoList[3] = new Mino(MinoType.Z_Mino);
		minoList[4] = new Mino(MinoType.J_Mino);
		minoList[5] = new Mino(MinoType.L_Mino);
		minoList[6] = new Mino(MinoType.T_Mino);
		randomizeMino(minoList);
		return minoList;
	}
	
	/*
	 * 다음 미노를 반환한다. 
	 * 7개의 미노를 다 반환할 경우, 다음 미노리스트를 생성한다.
	 */
	public Mino getMino() {
		nextMinoList[0][index].removeMinoFromBoard(this);
		Mino answer = nextMinoList[0][index];	
		index = index + 1;
		if(index == 7) {
			index = 0;
			nextMinoList[0] = nextMinoList[1];		
			nextMinoList[1] = initMinoList();
		}
		 
		reorderNextMinoBoard();
		Mino answerMino = new Mino(answer.getType());
		return answerMino;
	}
	
	public void reorderNextMinoBoard() {
		int temp_index = index;
		int list_index = 0;
		for(int i = 0; i < 6; i++) {
			if(temp_index < 7) {
				MinoType type = nextMinoList[list_index][temp_index].getType();
				if(type == MinoType.S_Mino || type == MinoType.I_Mino) {
					nextMinoList[list_index][temp_index].addMinoToBoard(this, 3, 1, OTHER_BOARD_BLOCK_SIZE, i);
				}
				else {
					nextMinoList[list_index][temp_index].addMinoToBoard(this, 2, 1, OTHER_BOARD_BLOCK_SIZE, i);
				}
			}
			temp_index++;
			if(temp_index == 7) {
				temp_index = 0;
				list_index = 1;
			}
		}
	}
}

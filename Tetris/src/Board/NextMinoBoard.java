package Board;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;
import java.util.Random;

import static Tetris.Main.*;
import Mino.Mino;
import Mino.MinoType;

public class NextMinoBoard extends JPanel{
	private int height;
	private int width;
	private JPanel gameBoard;
	private Mino[][] nextMinoList;
	private int nextMinoIndex;
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(10, 10, width-20, height-20);
	}
	
	public NextMinoBoard(JPanel board) {
		this.gameBoard = board;
		setVisible(true);
		width = OTHER_BOARD_BLOCK_SIZE * 6;
		height = OTHER_BOARD_BLOCK_SIZE * 4 * 6;
		setSize(width,height);
		setBackground(Color.black);
		setBounds(BOARD_START_WIDTH + BLOCK_SIZE * (BOARD_WIDTH + 1), BOARD_START_HEIGHT, getWidth(),getHeight());
		
		nextMinoIndex = 0;
		nextMinoList = new Mino[2][7];
		for(int i = 0; i < 2; i++) {
			nextMinoList[i] = initMinoList();
		}
	}
	
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
	
	public static void randomizeMino(Mino[] minoList) {
		Random random = new Random();
		int length = minoList.length;
		for(int i = length - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			Mino temp = minoList[i];
			minoList[i] = minoList[j];
			minoList[j] = temp;
		}
	}
	
	public Mino getMino() {
		nextMinoList[0][nextMinoIndex].removeMinoFromBoard(this);
		MinoType answerType = nextMinoList[0][nextMinoIndex].getType();	
		nextMinoIndex = nextMinoIndex + 1;
		
		if(nextMinoIndex == 7) {
			nextMinoIndex = 0;
			nextMinoList[0] = nextMinoList[1];		
			nextMinoList[1] = initMinoList();
		}
		 
		reorderNextMinoBoard();
		Mino answerMino = new Mino(answerType);
		return answerMino;
	}
	
	public void reorderNextMinoBoard() {
		int listNumber = 0;
		int tempIndex = nextMinoIndex;
		for(int i = 0; i < 6; i++) {
			if(tempIndex < 7) {
				MinoType type = nextMinoList[listNumber][tempIndex].getType();
				if(type == MinoType.S_Mino || type == MinoType.I_Mino) {
					nextMinoList[listNumber][tempIndex].addMinoToOtherBoard(this, 3, 1, i);
				}
				else {
					nextMinoList[listNumber][tempIndex].addMinoToOtherBoard(this, 2, 1, i);
				}
			}
			tempIndex++;
			if(tempIndex == 7) {
				tempIndex = 0;
				listNumber = 1;
			}
		}
	}
}

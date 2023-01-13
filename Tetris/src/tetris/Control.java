package tetris;

import static tetris.Main.*;
import javax.swing.*;
import Mino.BaseMino;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import Mino.*;
import java.util.Random;
import java.util.Arrays;
public class Control extends JFrame{
	Board board;
	Mino mino;
	Mino[] minoList;
	int minoNumber;
	int x;
	int y;
	int xInitValue;
	int yInitValue;
	public Control() {
		setSize(FRAME_WIDTH,FRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new KeyboardListener());
		xInitValue = 5;
		yInitValue = 0;
		minoNumber = 0;
		minoList = new Mino[7];
		minoList[0] = new Mino(MinoType.I_Mino);
		minoList[1] = new Mino(MinoType.O_Mino);
		minoList[2] = new Mino(MinoType.S_Mino);
		minoList[3] = new Mino(MinoType.Z_Mino);
		minoList[4] = new Mino(MinoType.J_Mino);
		minoList[5] = new Mino(MinoType.L_Mino);
		minoList[6] = new Mino(MinoType.T_Mino);
		randomizeMino(minoList);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
	}
	
	/*
	 * 게임 진행
	 */
	public void start() {
		board = new Board();
		add(board);
		board.setBounds(BOARD_START_WIDTH,BOARD_START_HEIGHT,board.getWidth(),board.getHeight());

		mino = minoList[minoNumber];
		addMinoToBoard(mino.getType());
	}
	

	/*
	 * 변수 minoNumber를 1 증가시킨다.
	 * 모든 미노가 사용된 후에 미노를 다시 섞어줘야 하기 때문에 randomizeMino를 수행한다. 
	 */
	public void changeMinoNumber() {
		minoNumber = minoNumber + 1;
		if(minoNumber == 7) {
			minoNumber = 0;
			randomizeMino(minoList);
		}
		
	}
	
	/*
	 * minoList내의 mino들의 순서를 랜덤하게 섞는다.
	 */
	public void randomizeMino(Mino[] ary) {
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
	 * Mino를 Board에 부착.
	 * Mino 자체는 panel이 아니기 때문에 baseMino를 얻어와 각각의 baseMino들을 Board에 붙여준다.
	 */
	public void addMinoToBoard(MinoType type) {
		x = xInitValue;
		y = yInitValue;
		mino = new Mino(type);
		mino.setPosition(x,y);
		for(int i = 0; i < 4; i++) {
			board.add(mino.getBaseMino(i));
		}
		board.repaint();
	}
	/*
	 * Mino를 Board에서 제거. 
	 * Mino 자체는 Panel이 아니기 때문에 baseMino를 얻어와 각각의 baseMino를 Board에서 떼어준다.
	 */
	public void removeMinoFromBoard(Mino target) {
		for(int i = 0; i < 4; i++) {
			board.remove(target.getBaseMino(i));
		}
		repaint();
	}
	
	/*
	 * 현재의 mino를 제거하고 다음 미노를 mino로 한다. 
	 */
	public void changeMino() {
		removeMinoFromBoard(mino);
		changeMinoNumber();
		addMinoToBoard(minoList[minoNumber].getType());
	}

	class KeyboardListener implements KeyListener{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				x += 1;
				mino.setPosition(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				x -= 1;
				mino.setPosition(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_UP) {
				mino.rotate(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
				y += 1;
				mino.setPosition(x, y);
			}
			else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				changeMino();
			}
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
	}

}

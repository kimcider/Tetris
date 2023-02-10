package Board;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import static Tetris.Main.*;
import Mino.Mino;
import Mino.MinoType;

public class SaveBoard extends JPanel{
	private int height;
	private int width;
	private boolean isSaved;
	private GameBoard gameBoard;
	private Mino mino;
	
	public SaveBoard(GameBoard board) {
		setVisible(true);
		width = OTHER_BOARD_BLOCK_SIZE * 6;
		height = OTHER_BOARD_BLOCK_SIZE * 6;
		setSize(width,height);
		setBounds(BOARD_START_WIDTH - OTHER_BOARD_BLOCK_SIZE * 8, BOARD_START_HEIGHT, getWidth(),getHeight());
		isSaved = false;
		this.gameBoard = board;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(10, 10, width - 20, height - 20);
	}
	
	public Mino replaceMino(Mino mino) {
		Mino returnMino = null;
		
		mino.removeMinoFromBoard(gameBoard);
		if(isSaved == true) {
			this.mino.removeMinoFromBoard(this);
			returnMino = new Mino(gameBoard, this.mino.getType());
		}
		else {
			isSaved = true;
		}
		this.mino = new Mino(gameBoard, mino.getType());
		attachMinoToBoard();
		return returnMino;
	}
	
	private void attachMinoToBoard() {
		if(mino.getType() == MinoType.S_Mino) {
			mino.addMinoToSaveBoard(this, 4, 2);
		}
		else{
			mino.addMinoToSaveBoard(this, 3, 2);
		}
	}
}

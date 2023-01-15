package Board;
import javax.swing.*;
import java.awt.*;
import static Tetris.Main.*;
import Mino.Mino;
import Mino.MinoType;

public class SaveBoard extends JPanel{
	int height;
	int width;
	boolean isSaved;
	JPanel board;
	Mino mino;
	public SaveBoard(JPanel board) {
		setVisible(true);
		width = OTHER_BOARD_BLOCK_SIZE * 6;
		height = OTHER_BOARD_BLOCK_SIZE * 6;
		setSize(width,height);
		setBounds(BOARD_START_WIDTH - OTHER_BOARD_BLOCK_SIZE * 8, BOARD_START_HEIGHT, getWidth(),getHeight());
		isSaved = false;
		this.board = board;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);
		g.fillRect(10, 10, width - 20, height - 20);
	}
	
	public Mino save(Mino mino) {
		Mino returnMino = null;
		
		mino.removeMinoFromBoard(board);
		if(isSaved == true) {
			this.mino.removeMinoFromOtherBoard(this);
		}
		
		if(isSaved == false) {
			isSaved = true;
			this.mino = mino;
		}else {
			returnMino = this.mino;
			this.mino = mino;
		}
		
		attach();
		return returnMino;
	}
	
	private void attach() {
		if(mino.getType() == MinoType.S_Mino) {
			mino.addMinoToOtherBoard(this, 4, 2, OTHER_BOARD_BLOCK_SIZE, 0);
		}
		else{
			mino.addMinoToOtherBoard(this, 3, 2, OTHER_BOARD_BLOCK_SIZE, 0);
		}
	}
}

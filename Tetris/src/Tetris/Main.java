package Tetris;

public class Main {
	public static final int FRAME_HEIGHT = 800;
	public static final int FRAME_WIDTH = 800;
	public static final int BLOCK_SIZE = 35;
	public static final int BOARD_HEIGHT = 20;
	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_START_HEIGHT = 40;
	public static final int BOARD_START_WIDTH = 200;
	public static void main(String[]args) {
		Control control = new Control();
		control.start();
		//control.addBlock();
	}
}

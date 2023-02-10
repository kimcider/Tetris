package Mino;
import Board.GameBoard;
public abstract class AbstractMino {
	GameBoard gameBoard;
	
	public AbstractMino(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
}

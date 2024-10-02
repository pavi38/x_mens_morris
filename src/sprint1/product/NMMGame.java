package sprint1.product;

import sprint1.product.Player;

public class NMMGame {

	public enum Cell {
		INVALID, EMPTY, BLUE, RED
	}
	public enum GameMode {
		NINE, FIVE
	}
	public enum GameState {
		PLACING, MOVING, MILLING, FLYING, GAMEOVER
	}
	private Cell[][] grid;
	private GameState currentGamestate;
	private Player turnPlayer;
	private int size = 7;
	private Player redPlayer;
	private Player bluePlayer;

	public NMMGame() {
		grid = new Cell[size][size];
		setValid();
		this.redPlayer = new Player('R',9);
		this.bluePlayer = new Player('B',9);
		this.turnPlayer = this.redPlayer;
		this.currentGamestate = GameState.PLACING;
	}

	public void setValid() {
		int middle = (size-1)/2;
		for (int row = 0; row < size; ++row) {
			for (int col = 0; col < size; ++col) {
				//Makes diagonal cells and the middle rows and columns valid spaces
				if (row == col || row == (size-1-col) || row == middle || col == middle)
					grid[row][col] = Cell.EMPTY;
				else
					grid[row][col] = Cell.INVALID;
				//Makes middle cell invalid
				if (row == middle && col == middle)
					grid[row][col] = Cell.INVALID;

			}
		}
	}

	public Cell getCell(int row, int column) {
		if (row >= 0 && row < size && column >= 0 && column < size)
			return grid[row][column];
		else
			return null;
	}

	public Player getTurnPlayer() {
		return turnPlayer;
	}

	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean makeMove(int row, int col) {
		if (getCell(row, col)== Cell.EMPTY){
			if (turnPlayer.getColor() == 'R')
				grid[row][col] = Cell.RED;
			else
				grid[row][col] = Cell.BLUE;
			turnPlayer.placeGamePiece(row, col);
			if (redPlayer.numberOfGamePieces() == 0 && bluePlayer.numberOfGamePieces() == 0)
				currentGamestate = GameState.MOVING;
			return true;
		}
		return false;

	}

	public void changeTurn() {
		turnPlayer = (turnPlayer.getColor() == 'R') ? bluePlayer : redPlayer;
	}

	public GameState getCurrentGamestate() {
		return currentGamestate;
	}

	public void setCurrentGamestate(GameState currentGamestate) {
		this.currentGamestate = currentGamestate;
	}
}

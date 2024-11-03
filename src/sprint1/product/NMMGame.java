package sprint1.product;

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
	private int size;
	private Player redPlayer;
	private Player bluePlayer;
	private GameMode gameMode;

	public NMMGame(int gameMode) {
		int pieces;
		this.gameMode = (gameMode == 9) ? GameMode.NINE : GameMode.FIVE;
		if (this.gameMode == GameMode.NINE){
			pieces = 9;
			this.size = 7;
		} else {
			pieces = 5;
			this.size = 5;
		}
		grid = new Cell[this.size][this.size];
		setValid();
		this.redPlayer = new Player('R',pieces);
		this.bluePlayer = new Player('B',pieces);
		this.turnPlayer = this.redPlayer;
		this.currentGamestate = GameState.PLACING;
	}

	public void setValid() {
		int middle = (this.size-1)/2;
		for (int row = 0; row < this.size; ++row) {
			for (int col = 0; col < this.size; ++col) {
				//Makes diagonal cells and the middle rows and columns valid spaces
				if (row == col || row == (this.size-1-col) || row == middle || col == middle)
					this.grid[row][col] = Cell.EMPTY;
				else
					this.grid[row][col] = Cell.INVALID;
				//Makes middle cell invalid
				if (row == middle && col == middle)
					this.grid[row][col] = Cell.INVALID;
			}
		}
	}

	public Cell getCell(int row, int column) {
		if (row >= 0 && row < this.size && column >= 0 && column < this.size)
			return this.grid[row][column];
		else
			return null;
	}

	public Player getTurnPlayer() {
		return this.turnPlayer;
	}

	public void setTurnPlayer(Player turnPlayer) {
		this.turnPlayer = turnPlayer;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean placePiece(int row, int col) {
		if (getCell(row, col)== Cell.EMPTY){
			if (this.turnPlayer.getColor() == 'R')
				this.grid[row][col] = Cell.RED;
			else
				this.grid[row][col] = Cell.BLUE;
			this.turnPlayer.getGamePiece(row, col);
			this.updateGameState();
			this.changeTurn();
			return true;
		}
		return false;

	}
	public boolean movePiece(int row, int col) {
		return false;
	}

	public void updateGameState() {
		if (this.redPlayer.numberOfGamePieces() == 0 && this.bluePlayer.numberOfGamePieces() == 0)
			this.currentGamestate = GameState.MOVING;
	}
	public void changeTurn() {
		this.turnPlayer = (this.turnPlayer.getColor() == 'R') ? this.bluePlayer : this.redPlayer;
	}


	public void gameOver() {

		if(currentGamestate == GameState.MOVING || currentGamestate == GameState.FLYING) {
			Cell PlayerColor = Cell.EMPTY;
			if(this.turnPlayer.getColor() == 'R'){
				PlayerColor = Cell.RED;
			}
			else{
				PlayerColor = Cell.BLUE;
			}
			if (this.turnPlayer.numberOfBoradPieces() < 3 || !LegalMove(PlayerColor)) {
				if (this.turnPlayer.getColor() == 'R') {
					System.out.println("Blue player won");
				} else {
					System.out.println("Red player won");
				}
			}
		}
	}
	private boolean LegalMove(Cell PlayerColor) {
		for(int i = 0; i < this.grid.length; i++){
			for(int j = 0; j < this.grid[i].length; j++){
				if(grid[i][j] == PlayerColor){
					return checkAdjPos(i, j); //check again
				}
			}
		}
		return false;
	}
	private boolean checkAdjPos(int x, int y){
		//  direactions (up, down, left, right)
		int[][] directions = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

		for (int[] dir : directions) {
			int newX = x + dir[0];
			int newY = y + dir[1];

			// Check if the new position is within bounds and empty
			if (isWithinBounds(newX, newY) && grid[newX][newY] == Cell.EMPTY) {
				return true;
			}
		}
		return false;
	}
	private boolean isWithinBounds(int x, int y) {
		return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length;
	}
	public GameState getCurrentGamestate() {
		return this.currentGamestate;
	}

	public void setCurrentGamestate(GameState currentGamestate) {
		this.currentGamestate = currentGamestate;
	}

	public Player getBluePlayer() {
		return bluePlayer;
	}

	public Player getRedPlayer() {
		return redPlayer;
	}

	public GameMode getGameMode() {
		return gameMode;
	}
}

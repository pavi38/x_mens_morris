package sprint1.product;

public class Player {
    private char color;
    private int gamePieces;
    private int boradPieces;


    public Player(char color, int gamePieces) {
        this.color = color;
        this.gamePieces = gamePieces;
        this.boradPieces = 0;
    }

    public int getGamePieces() {
        return gamePieces;
    }

    public void placeGamePiece() {
        this.gamePieces--;
        this.boradPieces++;
    }

    public void setGamePieces(int gamePieces) {
        this.gamePieces = gamePieces;
    }

    public char getColor() {
        return color;
    }
}

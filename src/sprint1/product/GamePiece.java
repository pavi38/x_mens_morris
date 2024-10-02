package sprint1.product;

public class GamePiece {
    int id;
    int row, col;

    public GamePiece(int i, char color) {
        this.id = i+color;
        System.out.println(this.id);
    }

    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
        System.out.println("("+row+", "+col+")");
    }
}

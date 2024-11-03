package sprint1.product;

public class CheckMill {
    private final Cell[][] grid;
    public CheckMill(Cell[][] grid) {
        this.grid = grid;

    }
public boolean checkMillAllDireactions(int row, int col) {
        return checkVerticalMillDown(row, col) ||
                checkVerticalMillTop(row, col) ||
                checkHorizontalMillRight(row, col) ||
                checkHorizontalMillLeft(row, col) ||
                checkVerticalMillMiddle(row, col) ||
                checkHorizontalMillMiddle(row, col);
}
private boolean checkMillCombo(int red_piece, int blue_piece){
    if(red_piece > 0 && blue_piece > 0){ //if opponent piece was found on mill
        return false;
    }
    return red_piece == 3 || blue_piece == 3; // return true if the mill is formed
}
private boolean checkVerticalMillDown(int row, int col) {
        int red_piece = 0;
        int blue_piece = 0;
        for (int i = row; i < grid[0].length; i++) {
            checkMillCombo(red_piece, blue_piece);
            if(grid[i][col] == Cell.RED){
                red_piece++;
            } else if (grid[i][col] == Cell.BLUE) {
                blue_piece++;
            }
        }
        return false; //if the loop terminates without retun than its not mill
}
    private boolean checkVerticalMillTop(int row, int col) {
        int red_piece = 0;
        int blue_piece = 0;
        for (int i = row; i >= 0; i--) {
            checkMillCombo(red_piece, blue_piece);
            if(grid[i][col] == Cell.RED){
                red_piece++;
            } else if (grid[i][col] == Cell.BLUE) {
                blue_piece++;
            }
        }
        return false; //if the loop terminates without retun than its not mill
    }
    private boolean checkHorizontalMillRight(int row, int col) {
        int red_piece = 0;
        int blue_piece = 0;
        for (int i = col; i < grid[0].length; i++) {
            checkMillCombo(red_piece, blue_piece);
            if(grid[row][i] == Cell.RED){
                red_piece++;
            } else if (grid[row][i] == Cell.BLUE) {
                blue_piece++;
            }
        }
        return false;
    }
    private boolean checkHorizontalMillLeft(int row, int col) {
        int red_piece = 0;
        int blue_piece = 0;
        for(int i = col; i >=0; i--){
            checkMillCombo(red_piece, blue_piece);
            if(grid[row][i] == Cell.RED){
                red_piece++;
            } else if (grid[row][i] == Cell.BLUE) {
                blue_piece++;
            }
        }
        return false;
    }
    private boolean checkHorizontalMillMiddle(int row, int col) {
        try{
            return ((grid[row][col- 1] == Cell.RED || grid[row][col - 1] == Cell.BLUE) && //check innner sq mill
                    (grid[row][col + 1] == Cell.RED || grid[row][col + 1] == Cell.BLUE)) ||
                    ((grid[row][col - 2] == Cell.RED || grid[row][col - 2] == Cell.BLUE) && //check outer sq mill
                            (grid[row][col + 2] == Cell.RED || grid[row][col + 2] == Cell.BLUE));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
    private boolean checkVerticalMillMiddle(int row, int col) {
        try{
            return ((grid[row - 1][col] == Cell.RED || grid[row - 1][col] == Cell.BLUE) && //check innner sq mill
                    (grid[row + 1][col] == Cell.RED || grid[row + 1][col] == Cell.BLUE)) ||
                    ((grid[row - 2][col] == Cell.RED || grid[row][col - 2] == Cell.BLUE) && //check outer sq mill
                            (grid[row + 2][col] == Cell.RED || grid[row + 2][col] == Cell.BLUE));
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }
}

//    private boolean checkVerticalMill(int row, int col) {
//        if(grid[row][col - 1] != Cell.INVALID || grid[row][col + 1] != Cell.INVALID){
//            //checking if the mill is in inner square or outer
//
//
//        }
//        return false;
//    }

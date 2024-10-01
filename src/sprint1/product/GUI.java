package sprint1.product;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class GUI extends Application {

	private GameSpace[][] gameSpace;

	private Label gameStatus = new Label("RED's Turn");

	private NMMGame game;

	private int gameSize = 0;

	@Override
	public void start(Stage primaryStage) {
		if (game == null) {
			game = new NMMGame();
		}
		gameSize = game.getSize();
		GridPane pane = new GridPane();
		gameSpace = new GameSpace[gameSize][gameSize];
		for (int row = 0; row < gameSize; row++)
			for (int col = 0; col < gameSize; col++)
				if (game.getCell(row, col) != NMMGame.Cell.INVALID)
					pane.add(gameSpace[row][col] = new GameSpace(row,col, true), col, row);
				else
					pane.add(gameSpace[row][col] = new GameSpace(row,col, false), col, row);


		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(pane);
		borderPane.setBottom(gameStatus);

		Scene scene = new Scene(borderPane, 450, 450);
		primaryStage.setTitle("Nine Men's Morris");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public class GameSpace extends Pane {

		private int row, col;

		public GameSpace(int row, int col, boolean valid) {
			this.row = row;
			this.col = col;
			this.setPrefSize(2000, 2000);
			if (valid){
				drawPoint();
				this.setOnMouseClicked(e -> handleMouseClick());
			} else {
				drawLine(row, col);
			}
		}

		private void drawPoint() {
			Circle circle = new Circle();
			circle.centerXProperty().bind(this.widthProperty().divide(2));
			circle.centerYProperty().bind(this.heightProperty().divide(2));
			circle.radiusProperty().bind(this.widthProperty().divide(8));
			circle.setFill(Color.BLACK);

			getChildren().add(circle);
		}
		private void drawLine(int row, int col) {
			int max = gameSize-1;
			Line line = new Line();
			line.setStroke(Color.BLACK);
			line.setStrokeWidth(5.0);
			if (row == 0 || row == max || (row == 1 && (col>0 && col<max)) || (row == max-1 && (col > 0 && col< max))) {
				line.startYProperty().bind(this.heightProperty().subtract(5).divide(2));
				line.endXProperty().bind(this.widthProperty());
				line.endYProperty().bind(this.heightProperty().subtract(5).divide(2));
			}
			else if (row==col){
				line.setStroke(Color.TRANSPARENT);
			}
			else {
				line.startXProperty().bind(this.widthProperty().subtract(5).divide(2));
				line.endXProperty().bind(this.widthProperty().subtract(5).divide(2));
				line.endYProperty().bind(this.heightProperty());
			}
			getChildren().add(line);
		}

		private void drawGamePiece() {
			Circle circle = new Circle();
			circle.centerXProperty().bind(this.widthProperty().divide(2));
			circle.centerYProperty().bind(this.heightProperty().divide(2));
			circle.radiusProperty().bind(this.widthProperty().divide(4));
			if (game.getTurnPlayer().getColor() =='R')
				circle.setFill(Color.RED);
			else
				circle.setFill(Color.BLUE);

			getChildren().add(circle);
		}

		private void handleMouseClick() {
			String updateGameStatus = gameStatus.getText();
			String gameState;
			System.out.println(updateGameStatus);
			if (game.getCurrentGamestate() == NMMGame.GameState.PLACING){
				if (game.makeMove(this.row, this.col)) {
					drawGamePiece();
					game.changeTurn();
				}
			}
			gameState = String.valueOf(game.getCurrentGamestate());
			updateGameStatus = (game.getTurnPlayer().getColor()=='R') ? gameState + " BLUE's Turn" : gameState + " RED's Turn";
			gameStatus.setText(updateGameStatus);

		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}

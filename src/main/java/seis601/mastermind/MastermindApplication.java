package seis601.mastermind;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MastermindApplication extends Application {
    @Override
    public void start(Stage stage) {
        Board board = new Board(12);

        // Test row 1
        Row row = board.getRow(0);
        row.setCodePeg(0, CodePeg.CodeColor.Red);
        row.setCodePeg(1, CodePeg.CodeColor.Blue);
        row.setCodePeg(2, CodePeg.CodeColor.Yellow);
        row.setCodePeg(3, CodePeg.CodeColor.Green);
        row.setKeyPeg(0, KeyPeg.KeyColor.Black);
        row.setKeyPeg(1, KeyPeg.KeyColor.White);

        // Test row 2
        Row row2 = board.getRow(1);
        row2.setCodePeg(0, CodePeg.CodeColor.Yellow);
        row2.setCodePeg(1, CodePeg.CodeColor.Green);
        row2.setCodePeg(2, CodePeg.CodeColor.White);
        row2.setCodePeg(3, CodePeg.CodeColor.Black);
        row2.setKeyPeg(0, KeyPeg.KeyColor.Black);
        row2.setKeyPeg(1, KeyPeg.KeyColor.White);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        Group group = new Group(grid);

        Canvas canvas = GameDraw.drawBoard(board);
        grid.add(canvas, 0, 0);

        Scene scene = new Scene(group, 480, 600);
        scene.setFill(Color.LIGHTGREEN);

        stage.setTitle("Mastermind");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
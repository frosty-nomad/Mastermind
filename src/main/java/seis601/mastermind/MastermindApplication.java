package seis601.mastermind;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MastermindApplication extends Application {
    @Override
    public void start(Stage stage) {
        // Initialize the guess pegs.
        CodePeg[] guessPegs = new CodePeg[] {
            new CodePeg(CodePeg.CodeColor.Red),
            new CodePeg(CodePeg.CodeColor.Red),
            new CodePeg(CodePeg.CodeColor.Red),
            new CodePeg(CodePeg.CodeColor.Red)
        };

        // Build the game controls
        GameControls controls = new GameControls(guessPegs);

        // Build out the GridPane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        // Add the controls to the grid
        grid.add(controls.getCanvasBoard(), 0, 0);
        grid.add(controls.getHBoxGuess(), 1, 0);
        grid.add(controls.getHBoxPicker(), 0,1);

        // Add the GridPane to the Group
        Group group = new Group(grid);

        // Build the scene
        Scene scene = new Scene(group, 480, 600);
        scene.setFill(Color.LIGHTGREEN);

        // Set the stage
        stage.setTitle("Mastermind");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
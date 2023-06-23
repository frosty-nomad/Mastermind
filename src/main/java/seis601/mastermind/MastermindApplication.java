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
        // Build the game controls
        GameControls controls = new GameControls(stage);

        // Build out the GridPane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        // Add the controls to the grid
        grid.add(controls.getHBoxGuess(), 0, 0);
        grid.add(controls.getCanvasBoard(), 0, 1);
        grid.add(controls.getHBoxPicker(), 0,2);

        // Add the GridPane to the Group
        Group group = new Group(grid);

        // Build the scene
        Scene scene = new Scene(group);
        scene.setFill(Color.LIGHTGREEN);

        // Set the stage
        stage.setTitle("Mastermind");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
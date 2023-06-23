package seis601.mastermind;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameControls {
    private Board gameBoard;
    private int guesses;
    private final Canvas canvasBoard;
    private HBox hBoxGuess;
    private HBox hBoxPicker;
    private VBox vBoxRules;
    private Stage stage;
    private CodePeg[] guessPegs;
    private Circle[] pegSelectors;

    public GameControls(Stage stage) {
        // Save the stage for resizing
        this.stage = stage;

        // Build out initial controls.
        guessPegs = new CodePeg[] {
                new CodePeg(CodePeg.CodeColor.Red),
                new CodePeg(CodePeg.CodeColor.Red),
                new CodePeg(CodePeg.CodeColor.Red),
                new CodePeg(CodePeg.CodeColor.Red)
        };
        buildGuessSelector();
        buildPegPicker();
        buildGameRules();

        // Build the initial game board
        gameBoard = new Board(guesses);

        // Create the board canvas
        canvasBoard = new Canvas();

        // Draw the initial game board
        GameDraw.drawBoard(canvasBoard, gameBoard);
    }

    public Canvas getCanvasBoard() {
        return canvasBoard;
    }

    public HBox getHBoxGuess() {
        return hBoxGuess;
    }

    public HBox getHBoxPicker() {
        return hBoxPicker;
    }

    public VBox getvBoxRules() {
        return vBoxRules;
    }

    private void alertLoser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sorry!");
        alert.setHeaderText(null);
        alert.setContentText("You've run out of guesses! Please try again.");
        alert.showAndWait();

        resetGame();
    }

    private void alertWinner() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Congratulations!");
        alert.setHeaderText(null);
        alert.setContentText("Winner Winner Chicken Dinner!");
        alert.showAndWait();

        resetGame();
    }

    private void buildGuessSelector() {
        Label lblGuesses = new Label("Guesses: ");
        lblGuesses.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, 16));

        ComboBox<Integer> cboGuesses = new ComboBox<>();
        cboGuesses.getItems().addAll(12, 11, 10, 9, 8);
        cboGuesses.setValue(12);
        cboGuesses.setOnAction(e -> {
            guesses = cboGuesses.getValue();
            resetGame();
        });

        hBoxGuess = new HBox();
        hBoxGuess.setAlignment(Pos.CENTER);
        hBoxGuess.getChildren().addAll(
                lblGuesses,
                cboGuesses
        );

        // Set the initial guesses
        guesses = cboGuesses.getValue();
    }

    private void buildPegPicker() {
        Button guessButton = new Button("Guess");
        guessButton.setFont(Font.font("Comic Sans MS", FontWeight.EXTRA_BOLD, 16));
        guessButton.setOnAction(e -> {
            guessClick();
        });

        pegSelectors = new Circle[] {
                pegSelector(guessPegs[0]),
                pegSelector(guessPegs[1]),
                pegSelector(guessPegs[2]),
                pegSelector(guessPegs[3]),
        };

        hBoxPicker = new HBox();
        hBoxPicker.setPadding(new Insets(0, 0, 0, 34));
        hBoxPicker.setSpacing(8);
        hBoxPicker.setAlignment(Pos.CENTER);
        hBoxPicker.getChildren().addAll(
                pegSelectors[0],
                pegSelectors[1],
                pegSelectors[2],
                pegSelectors[3],
                guessButton
        );
    }

    private void buildGameRules(){
        Label whiteKeyMeaning = new Label("White key peg: Color is correct but wrong position.");
        Label blackKeyMeaning = new Label( "Black key peg: Color and position both correct. ");
        Label keyPegPosition = new Label("The arrangement of the white and black key pegs does not matter. ");
        whiteKeyMeaning.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 15));
        blackKeyMeaning.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 15));
        keyPegPosition.setFont(Font.font("Comic Sans MS", FontWeight.SEMI_BOLD, 15));

        vBoxRules = new VBox();
        vBoxRules.setSpacing(8);
        vBoxRules.setAlignment(Pos.CENTER);
        vBoxRules.getChildren().addAll(
                whiteKeyMeaning,
                blackKeyMeaning,
                keyPegPosition
        );
    }

    private void guessClick() {
        // Add the guess to analyze
        gameBoard.addGuess(guessPegs);

        // Re-draw the game board
        GameDraw.drawBoard(canvasBoard, gameBoard);

        // Check for winner
        if (gameBoard.isWinner()) {
            alertWinner();
        }

        // Check for loser
        if (gameBoard.isFull()) {
            alertLoser();
        }
    }

    private void pegClick(Circle circle, CodePeg codePeg) {
        int color = codePeg.getCodeColor().ordinal();
        color = color == (CodePeg.CodeColor.values().length - 1) ? 1 : ++color;
        codePeg.setCodeColor(CodePeg.CodeColor.values()[color]);
        circle.setFill(GameDraw.getPegFill(codePeg));   //reset circle color == reset CodePeg[] guessPegs color
    }

    //event handler for each code peg when click
    private Circle pegSelector(CodePeg codePeg) {
        Circle circle = new Circle();
        GameDraw.circleFill(circle, codePeg);
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            pegClick(circle, codePeg);
        });
        return circle;
    }

    private void resetGame() {
        // Reset the guess pegs
        for (CodePeg guessPeg : guessPegs) {
            guessPeg.setCodeColor(CodePeg.CodeColor.Red);
        }

        // Build a new game board
        gameBoard = new Board(guesses);
        GameDraw.drawBoard(canvasBoard, gameBoard);

        // Reset the pegSelectors
        for (int p = 0; p < pegSelectors.length; p++) {
            GameDraw.circleFill(pegSelectors[p], guessPegs[p]);
        }

        stage.sizeToScene();
    }
}

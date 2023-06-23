package seis601.mastermind;

import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class GameControls {
    private Board gameBoard;
    private int guesses;
    private Canvas canvasBoard;
    private HBox hboxGuess;
    private HBox hboxPicker;
    private CodePeg[] guessPegs;

    public GameControls(CodePeg[] codePegs) {
        // Build out initial controls.
        initControls();

        // Build the initial game board
        initGameBoard();

        int width = (GameDraw.pegWidth * 5) + GameDraw.padding;
        int height = GameDraw.rowHeight * guesses;
        canvasBoard = new Canvas(width + 3, height + 3);

        GameDraw.drawBoard(canvasBoard, gameBoard);
    }

    public Canvas getCanvasBoard() {
        return canvasBoard;
    }

    public HBox getHBoxGuess() {
        return hboxGuess;
    }

    public HBox getHBoxPicker() {
        return hboxPicker;
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
        lblGuesses.setFont(Font.font(12));

        ComboBox cboGuesses = new ComboBox();
        cboGuesses.setValue("12");
        cboGuesses.getItems().addAll("12", "10", "8");

        hboxGuess = new HBox();
        hboxGuess.getChildren().addAll(
                lblGuesses,
                cboGuesses
        );

        // Set the initial guesses
        guesses = Integer.parseInt((String)cboGuesses.getValue());
    }

    private void buildPegPicker() {
        Button guessButton = new Button("Guess");
        guessButton.setOnAction(e -> {
            guessClick();
        });

        hboxPicker = new HBox();
        hboxPicker.setPadding(new Insets(0, 0, 0, 6));
        hboxPicker.setSpacing(6);
        hboxPicker.getChildren().addAll(
                pegSelector(guessPegs[0]),
                pegSelector(guessPegs[1]),
                pegSelector(guessPegs[2]),
                pegSelector(guessPegs[3]),
                guessButton
        );
    }

    private void initControls() {
        guessPegs = new CodePeg[] {
                new CodePeg(CodePeg.CodeColor.Red),
                new CodePeg(CodePeg.CodeColor.Red),
                new CodePeg(CodePeg.CodeColor.Red),
                new CodePeg(CodePeg.CodeColor.Red)
        };

        buildGuessSelector();
        buildPegPicker();
    }

    public void initGameBoard() {
        gameBoard = new Board(guesses);
        gameBoard = new Board(guesses);
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
        circle.setFill(GameDraw.getPegFill(codePeg));
    }

    private Circle pegSelector(CodePeg codePeg) {
        Circle circle = new Circle();
        circle.setRadius(GameDraw.pegSize / 2);
        circle.setFill(GameDraw.getPegFill(codePeg));
        circle.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            pegClick(circle, codePeg);
        });
        return circle;
    }

    private void resetGame() {
        // Reset the guess pegs
        for (int p = 0; p < guessPegs.length; p++) {
            guessPegs[p].setCodeColor(CodePeg.CodeColor.Red);
        }

        initGameBoard();
        GameDraw.drawBoard(canvasBoard, gameBoard);
        buildPegPicker();
    }
}

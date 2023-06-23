package seis601.mastermind;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class GameDraw {
    public static int padding = 6;
    public static int pegSize = 30;
    public static int pegWidth = padding + pegSize;
    public static int rowHeight = pegSize + (padding * 2);

    public static void circleFill(Circle circle, CodePeg codePeg) {
        circle.setRadius(GameDraw.pegSize / 2.0);
        circle.setFill(GameDraw.getPegFill(codePeg));
    }

    public static void drawBoard(Canvas canvas, Board board) {
        int guesses = board.getGuesses();
        int width = (pegWidth * 5) + padding;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Resize the canvas
        canvas.setWidth(width + 4);
        canvas.setHeight((rowHeight * guesses) + 4);

        // Draw the board shadow
        gc.setFill(Color.GRAY);
        gc.fillRect(1, 1, width + 2, (rowHeight * guesses) + 2);

        // Draw the board
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(0, 0, width, rowHeight * guesses);

        // Draw the code/key separator
        gc.setStroke(Color.LIGHTGOLDENRODYELLOW);
        gc.strokeLine(width - pegWidth - 3, 0, width - pegWidth - 3, (rowHeight * guesses) - 1);

        for(int i = 0; i < guesses; i++) {
            drawRow(canvas, i, board.getRow(i));
            if (i < (board.getGuesses() - 1))
                gc.strokeLine(1, rowHeight * (i + 1), width, rowHeight * (i + 1));
        }
    }

    private static void drawCodePeg(GraphicsContext gc, int xPos, int yPos, CodePeg codePeg) {
        gc.setFill(getPegFill(codePeg));
        gc.strokeOval(xPos, yPos, pegSize, pegSize);
        gc.fillOval(xPos, yPos, pegSize, pegSize);
    }

    private static void drawKeyPeg(GraphicsContext gc, int xPos, int yPos, int size, KeyPeg keyPeg) {
        gc.setFill(getPegFill(keyPeg));
        gc.strokeOval(xPos, yPos, size, size);
        gc.fillOval(xPos, yPos, size, size);
    }

    private static void drawKeyPegs(GraphicsContext gc, int xPos, int yPos, Row boardRow){
        int size = pegSize / 2;
        int pad = padding / 2;
        int xsPos = xPos + size + pad;
        int ysPos = yPos + size + pad;

        drawKeyPeg(gc, xPos, yPos - 1, size, boardRow.getKeyPeg(0));
        drawKeyPeg(gc, xsPos, yPos - 1, size, boardRow.getKeyPeg(1));
        drawKeyPeg(gc, xPos, ysPos - 1, size, boardRow.getKeyPeg(2));
        drawKeyPeg(gc, xsPos, ysPos - 1, size, boardRow.getKeyPeg(3));
    }

    private static void drawRow(Canvas canvas, int row, Row boardRow) {
        int yPos = (rowHeight * row) + padding;

        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawCodePeg(gc, padding, yPos, boardRow.getCodePeg(0));
        drawCodePeg(gc, padding + pegWidth, yPos, boardRow.getCodePeg(1));
        drawCodePeg(gc, padding + (pegWidth * 2), yPos, boardRow.getCodePeg(2));
        drawCodePeg(gc, padding + (pegWidth * 3), yPos, boardRow.getCodePeg(3));

        drawKeyPegs(gc, padding + (pegWidth * 4), yPos, boardRow);
    }

    public static RadialGradient getPegFill(CodePeg codePeg) {
        Stop[] stops;

        switch(codePeg.getCodeColor()) {
            case None:
                stops = new Stop[] { new Stop(0, Color.LIGHTGRAY), new Stop(1, codePeg.getColor())};
                break;
            case Black:
                stops = new Stop[] { new Stop(0, Color.DIMGRAY), new Stop(1, codePeg.getColor())};
                break;
            case White:
                stops = new Stop[] { new Stop(0, codePeg.getColor()), new Stop(1, Color.DARKGRAY)};
                break;
            default:
                stops = new Stop[] { new Stop(0, codePeg.getColor()), new Stop(1, Color.BLACK)};
                break;
        }

        RadialGradient rg = codePeg.getCodeColor() == CodePeg.CodeColor.None ?
                new RadialGradient(1, 0.1, 0.7, 0.7, 0.8, true, CycleMethod.NO_CYCLE, stops) :
                new RadialGradient(1, 0.1, 0.3, 0.3, 0.8, true, CycleMethod.NO_CYCLE, stops);

        return rg;
    }

    public static RadialGradient getPegFill(KeyPeg keyPeg) {
        Stop[] stops;

        switch(keyPeg.getKeyColor()) {
            case Black:
                stops = new Stop[] { new Stop(0, Color.DIMGRAY), new Stop(1, keyPeg.getColor())};
                break;
            case White:
                stops = new Stop[] { new Stop(0, keyPeg.getColor()), new Stop(1, Color.DARKGRAY)};
                break;
            default:
                stops = new Stop[] { new Stop(0, Color.LIGHTGRAY), new Stop(1, keyPeg.getColor())};
                break;
        }

        RadialGradient rg = keyPeg.getKeyColor() == KeyPeg.KeyColor.None ?
                new RadialGradient(1, 0.1, 0.7, 0.7, 0.8, true, CycleMethod.NO_CYCLE, stops) :
                new RadialGradient(1, 0.1, 0.3, 0.3, 0.8, true, CycleMethod.NO_CYCLE, stops);

        return rg;
    }
}

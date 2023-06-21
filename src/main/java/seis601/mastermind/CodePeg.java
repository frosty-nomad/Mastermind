package seis601.mastermind;

import javafx.scene.paint.Color;

public class CodePeg {
    private CodeColor codeColor;

    public enum CodeColor { None, Red, Blue, Yellow, Green, White, Black }

    // Constructors
    public CodePeg() {
        setCodeColor(CodeColor.None);
    }
    public CodePeg(CodeColor codeColor) {
        setCodeColor(codeColor);
    }

    // Public methods
    public Color getColor() {
        return convertCodeColor(codeColor);
    }

    public CodeColor getCodeColor() {
        return codeColor;
    }

    public void setCodeColor(CodeColor codeColor) {
        this.codeColor = codeColor;
    }

    // Private methods
    private Color convertCodeColor(CodeColor codeColor) {
        switch (codeColor) {
            case Red:
                return Color.RED;
            case Blue:
                return Color.BLUE;
            case Yellow:
                return Color.YELLOW;
            case Green:
                return Color.GREEN;
            case White:
                return Color.WHITE;
            case Black:
                return Color.BLACK;
            case None:
            default:
                return Color.BURLYWOOD;
        }
    }

    public static CodePeg generateRandomCodePeg(){
        int random = (int) (Math.random() * 6);
        return new CodePeg(CodeColor.values()[random]);
    }
}

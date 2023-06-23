package seis601.mastermind;

import javafx.scene.paint.Color;

public class KeyPeg {
    private KeyColor keyColor;
    public enum KeyColor { None, White, Black }

    // Constructors
    public KeyPeg() {
        setKeyColor(KeyColor.None);
    }
    public KeyPeg(KeyColor color) {
        setKeyColor(color);
    }

    // Public methods
    public Color getColor() {
        return convertKeyColor(keyColor);
    }

    public KeyColor getKeyColor() {
        return keyColor;
    }

    public void setKeyColor(KeyColor keyColor) {
        this.keyColor = keyColor;
    }

    // Private methods
    private Color convertKeyColor(KeyColor keyColor) {
        switch (keyColor) {
            case White:
                return Color.WHITE;
            case Black:
                return Color.BLACK;
            case None:
            default:
                return Color.NAVAJOWHITE;
        }
    }
}

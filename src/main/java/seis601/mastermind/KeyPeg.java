package seis601.mastermind;

public class KeyPeg {
    private Color color;
    public enum Color { White, Black }

    // Constructors
    public KeyPeg(Color color){
        this.color = color;
    }

    // Public methods
    public void setColor(Color color){
        this.color = color;
    }
}

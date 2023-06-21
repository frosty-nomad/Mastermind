package seis601.mastermind;

public class KeyPeg {
    private Color color;
    public enum Color { White, Black, None }

    // Constructors
    public KeyPeg(Color color){
        this.color = color;
    }

    // Public methods
    public Color getColor(){
        return color;
    }
}

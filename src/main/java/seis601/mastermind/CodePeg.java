package seis601.mastermind;

public class CodePeg {
    private Color color;
    public enum Color { Red, Blue, Yellow, Green, White, Black }

    // Constructors
    public CodePeg(Color color) {
        this.color = color;
    }

    // Public methods
    public Color getColor(){ return color;}

    public static CodePeg generateRandomCodePeg(){
        int random = (int) (Math.random() * 6);
        return new CodePeg(Color.values()[random]);
    }

}

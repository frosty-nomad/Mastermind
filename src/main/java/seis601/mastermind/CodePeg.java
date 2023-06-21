package seis601.mastermind;

import javafx.scene.paint.Color;
import java.util.Random;

public class CodePeg {
    private CodeColor codeColor;
    private Boolean isValid;
    public enum CodeColor { None, Red, Blue, Yellow, Green, White, Black }
    private static int[] getRandomNumber = new int[4];  //for unit testing
    private static int index = 0;   //for unit testing
    public static final int CODECOLORLENGTH = CodeColor.values().length;

    // Constructors
    public CodePeg() {
        setCodeColor(CodeColor.None);
        isValid = true;
    }
    public CodePeg(CodeColor codeColor) {
        setCodeColor(codeColor);
        isValid = true;
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
        Random generator = new Random();
        int randomInt = generator.nextInt(1, CODECOLORLENGTH);  // returns 1 to 6
//        randomInt = 2; //for unit testing
//        System.out.println("number" + randomInt);  //for unit testing
        getRandomNumber[index] = (randomInt);    //for unit testing
        index ++;       //for unit testing
        return new CodePeg(CodeColor.values()[randomInt]);
    }

    public boolean isValid(){
        return isValid;
    }

    public void setValid(Boolean used){
        isValid = used;
    }

    public static int[] getRandomNumber() {  //for unit testing
        return getRandomNumber;
    }
}

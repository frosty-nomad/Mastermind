package seis601.mastermind;

public class Board {
    private int guesses;
    private Row[] rows;
    private CodePeg[] keyCode;

    // Constructors
    public Board(int guesses) {
        this.guesses = guesses;
        rows = new Row[guesses];
        for (int i = 0; i < guesses; i++) {
            rows[i] = new Row();
        }
    }

    // Public methods
    // initializeBoard(int guesses)
    // addGuess(CodePeg[] guess)
    // isWinner()
    // isFull()

    public Row getRow(int index) {
        return index < guesses ? rows[index] : new Row();
    }

    public int getGuesses() {
        return guesses;
    }
}

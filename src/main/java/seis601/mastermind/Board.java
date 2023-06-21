package seis601.mastermind;

public class Board {
    private final int guesses;   //how many chances total
    private Row[] rows;
    private CodePeg[] keyCode;  //answer
    private int currentGuess;
    
    // Constructors
    public Board(int guesses) {
        this.guesses = guesses;
        keyCode = new CodePeg[4];
        rows = new Row[guesses];
        for (int i = 0; i < guesses; i++) {
            rows[i] = new Row();
        }
        currentGuess = 0;
        initializeBoard();
    }

    // Public methods
     public void initializeBoard(){
         currentGuess = 0;
         for (int i = 0; i <= 3; i ++) {
             keyCode[i] = CodePeg.generateRandomCodePeg();
         }
     }
     public void addGuess(CodePeg[] guess) {
         rows[currentGuess] = new Row(guess, keyCode);
         currentGuess ++;
     }

     public boolean isWinner(){
         if (currentGuess < 0) {
             return false;
         }
         return rows[currentGuess -1].isWinner();
     }

    public boolean isFull(){
        return currentGuess >= guesses;
    } //ran out of chance

    public Row getRow(int index) {
        return index < guesses ? rows[index] : new Row();
    }

    public int getGuesses() {
        return guesses;
    }
}

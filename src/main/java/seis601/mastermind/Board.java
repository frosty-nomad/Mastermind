package seis601.mastermind;

public class Board {
    private final int GUESSES;   //how many chances total
    private Row[] rows;
    private CodePeg[] keyCode;  //answer
    private int currentGuess;
    
    // Constructors
    public Board(int guesses) {
        this.GUESSES = guesses;
        keyCode = new CodePeg[4];
        rows = new Row[guesses];
        for (int i = 0; i < guesses; i++) {
            rows[i] = new Row();
        }
        generateKeyCode();
    }

    // Public methods
     public void generateKeyCode(){
         currentGuess = 0;
         for (int i = 0; i <= 3; i ++) {
             keyCode[i] = CodePeg.generateRandomCodePeg();
         }
     }
     public int addGuess(CodePeg[] guess) {
        if (isFull()) {
            return -1;
        }
        rows[currentGuess] = new Row(copyCodePegs(guess), copyCodePegs(keyCode));
        currentGuess++;
        return 0;
     }

     public boolean isWinner(){
         if (currentGuess < 0) {
             return false;
         }
         return rows[currentGuess -1].isWinner();
     }

    public boolean isFull(){
        return currentGuess >= GUESSES;
    } //ran out of chance

    public Row getRow(int index) {
        return index < GUESSES ? rows[index] : new Row();
    }

    public int getGuesses() {
        return GUESSES;
    }

    private CodePeg[] copyCodePegs(CodePeg[] codePegs) {
        CodePeg[] newPegs = new CodePeg[codePegs.length];
        for (int p = 0; p < codePegs.length; p++) {
            newPegs[p] = CodePeg.copy(codePegs[p]);
        }
        return  newPegs;
    }
}

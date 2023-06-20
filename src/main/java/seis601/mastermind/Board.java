package seis601.mastermind;

public class Board {
    private final int guesses;
    private Row[] rows;
    private CodePeg[] keyCode;  //answer
    private int currentGuess;

    // Constructors
     public Board(int guesses){
         this.guesses = guesses;
         keyCode = new CodePeg[4];
         rows = new Row[guesses];
         currentGuess = 0;
     }

    // Public methods
     public void initializeBoard(int guesses){
         currentGuess = 0;
         keyCode[0] = CodePeg.generateRandomCodePeg();
         keyCode[1] = CodePeg.generateRandomCodePeg();
         keyCode[2] = CodePeg.generateRandomCodePeg();
         keyCode[3] = CodePeg.generateRandomCodePeg();
     }
    // addGuess(CodePeg[] guess)
//     public boolean isWinner(){
//     }

     public boolean isFull(){
        return currentGuess >= guesses;
     } //ran out of chance

    // getGuesses()
}

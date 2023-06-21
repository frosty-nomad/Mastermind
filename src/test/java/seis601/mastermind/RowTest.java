package seis601.mastermind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    @Test
    void testConstructorKeyPeg() { //i = 1 j = 0
        CodePeg[] guesses = {new CodePeg(CodePeg.Color.Red), new CodePeg(CodePeg.Color.Blue)
                            , new CodePeg(CodePeg.Color.Blue), new CodePeg(CodePeg.Color.Red) };
        CodePeg[] answer = {new CodePeg(CodePeg.Color.Blue), new CodePeg(CodePeg.Color.Red)
                            , new CodePeg(CodePeg.Color.Blue), new CodePeg(CodePeg.Color.Red) };

        Row r = new Row(guesses, answer);
        assertEquals(false, r.isWinner());

        KeyPeg[] checkKeyPegs = r.getKeyPegs();
        KeyPeg.Color[] colorKeyPegs = new KeyPeg.Color[4];
        for (int i = 0; i < 4; i++ ){
            colorKeyPegs[i] = checkKeyPegs[i].getColor();
        }

        KeyPeg.Color[] answerKeyPeg = {KeyPeg.Color.White, KeyPeg.Color.White,
                                KeyPeg.Color.Black, KeyPeg.Color.Black};
        assertArrayEquals(answerKeyPeg, colorKeyPegs);
    }
}
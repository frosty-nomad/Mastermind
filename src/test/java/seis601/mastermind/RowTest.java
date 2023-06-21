package seis601.mastermind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    @Test
    void testConstructorKeyPeg() { //i = 1 j = 0
        CodePeg[] guesses = {new CodePeg(CodePeg.CodeColor.Red), new CodePeg(CodePeg.CodeColor.Blue)
                            , new CodePeg(CodePeg.CodeColor.Blue), new CodePeg(CodePeg.CodeColor.Red) };
        CodePeg[] answer = {new CodePeg(CodePeg.CodeColor.Blue), new CodePeg(CodePeg.CodeColor.Red)
                            , new CodePeg(CodePeg.CodeColor.Blue), new CodePeg(CodePeg.CodeColor.Red) };

        Row r = new Row(guesses, answer);
        assertEquals(false, r.isWinner());

        KeyPeg[] checkKeyPegs = r.getKeyPegs();
        KeyPeg.KeyColor[] colorKeyPegs = new KeyPeg.KeyColor[4];
        for (int i = 0; i < 4; i++ ){
            colorKeyPegs[i] = checkKeyPegs[i].getKeyColor();
        }

        KeyPeg.KeyColor[] answerKeyPeg = {KeyPeg.KeyColor.White, KeyPeg.KeyColor.White,
                                KeyPeg.KeyColor.Black, KeyPeg.KeyColor.Black};
        assertArrayEquals(answerKeyPeg, colorKeyPegs);
    }
}
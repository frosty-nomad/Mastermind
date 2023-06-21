package seis601.mastermind;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RowTest {
    @Test
    void testConstructorKeyPeg() { //i = 1 j = 0
        CodePeg[] guesses = {new CodePeg(CodePeg.CodeColor.Green), new CodePeg(CodePeg.CodeColor.White)
                            , new CodePeg(CodePeg.CodeColor.Blue), new CodePeg(CodePeg.CodeColor.Red) };

        CodePeg[] answer = {new CodePeg(CodePeg.CodeColor.Yellow), new CodePeg(CodePeg.CodeColor.White)
                            , new CodePeg(CodePeg.CodeColor.Green), new CodePeg(CodePeg.CodeColor.Red) };

        Row r = new Row(guesses, answer);
        assertEquals(false, r.isWinner());

        KeyPeg[] checkKeyPegs = r.getKeyPegs();
        int[] guessColorCount = keyPegColorCount(checkKeyPegs);


        KeyPeg[] answerKeyPegs = {new KeyPeg(KeyPeg.KeyColor.White), new KeyPeg(KeyPeg.KeyColor.None),
                                new KeyPeg(KeyPeg.KeyColor.Black), new KeyPeg(KeyPeg.KeyColor.Black)};
        int[] answerColorCount = keyPegColorCount(answerKeyPegs);

        assertArrayEquals(answerColorCount, guessColorCount);
    }

    private int[] keyPegColorCount (KeyPeg[] keyPegs) {
        int[] result = new int[3];  //order = None, White, Black
        for (KeyPeg keyPeg : keyPegs) {
            if (keyPeg.getColor() == Color.BURLYWOOD) {
                result[0]++;
            } else if (keyPeg.getColor() == Color.WHITE) {
                result[1]++;
            } else {
                result[2]++;
            }
        }
        return result;
    }
}
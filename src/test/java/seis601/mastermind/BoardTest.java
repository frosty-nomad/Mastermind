package seis601.mastermind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.paint.Color;

public class BoardTest {
    private static Color[] selfRandomColor = new Color[4];

    @Test
    void testKeyCode() {
        Board Board = new Board(12);
        CodePeg[] randomKeyCode = Board.getKeyCode();
        Color[] randomKeyColor = new Color[4];
        for (int i = 0; i < 4; i++) {
            randomKeyColor[i] = randomKeyCode[i].getColor();
            System.out.println("Actual " + randomKeyColor[i] + "\t");
        }

// Removed getRandomNumber from CodePeg class. It was causing a runtime error when second Board is instantiated:
//    Board board1 = new Board(12);
//    Board board2 = new Board(12); <-- Throws runtime error.
// Not sure what you're testing here. Let me know if you need help writing this test.
//        int[] selfTestRandomInt = CodePeg.getRandomNumber();
//        getSelfRandomColor(selfTestRandomInt);
//        Color[] selfRandomColor = {Color.BLUE, Color.BLUE, Color.BLUE, Color.BLUE};

//        assertArrayEquals(selfRandomColor, randomKeyColor);
//        System.out.println("Raw color: " + Color.BURLYWOOD + "\t" + Color.RED + "\t" + Color.BLUE + "\t"
//                                         + Color.YELLOW + "\t" + Color.GREEN + "\t" + Color.WHITE + "\t"
//                                            + Color.BLACK);
    }

        private static void getSelfRandomColor ( int[] randomInt){
            int indexC = 0;
            for (int i : randomInt) {
                selfRandomColor[indexC] = new CodePeg(CodePeg.CodeColor.values()[i]).getColor();
                System.out.print(selfRandomColor[indexC] + "\t");
                indexC++;
            }
        }
}
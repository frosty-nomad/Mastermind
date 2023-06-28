package seis601.mastermind;

import javafx.scene.paint.Color;

public class Row {
    private CodePeg[] codePegs;
    private KeyPeg[] keyPegs;

    // Constructors
    public Row() {
        codePegs = new CodePeg[] { new CodePeg(), new CodePeg(), new CodePeg(), new CodePeg() };
        keyPegs = new KeyPeg[] { new KeyPeg(), new KeyPeg(), new KeyPeg(), new KeyPeg() };
    }

    public Row(CodePeg[] guess, CodePeg[] keyCodes){
        codePegs = guess;
        keyPegs = new KeyPeg[] { new KeyPeg(), new KeyPeg(), new KeyPeg(), new KeyPeg() };
        setKeyPegs(keyCodes);
    }

    // Public methods
    public CodePeg getCodePeg(int index) {
        return index < codePegs.length ? codePegs[index] : null;
    }

    public KeyPeg getKeyPeg(int index) {
        return index < keyPegs.length ? keyPegs[index] : null;
    }

    public KeyPeg[] getKeyPegs(){
        return keyPegs;
    }

    public Boolean isWinner(){
        int [] colorCount = keyPegColorCount(); //order = None, White, Black
        return colorCount[2] == 4;
    }

    // Private methods
    private void setKeyPegs(CodePeg[] keyCodes){
        int keyPegCount = 0;
        for (int i = 0; i <= 3; i ++) {
            for (int j = 0; j <= 3; j++){
                if (codePegs[j].isValid()) {
                    if (keyCodes[i].isValid()){
                        if (codePegs[j].getColor() == keyCodes[j].getColor()) {
                            keyPegs[keyPegCount].setKeyColor(KeyPeg.KeyColor.Black);
                            keyCodes[j].setValid(false);
                            codePegs[j].setValid(false);
                            keyPegCount ++;
                        }
                        else if (keyCodes[i].getColor() == codePegs[j].getColor()) {
                            keyPegs[keyPegCount].setKeyColor(KeyPeg.KeyColor.White);
                            keyCodes[i].setValid(false);
                            codePegs[j].setValid(false);
                            keyPegCount++;
                        }
                    }
                }
            }
        }
    }

    private int[] keyPegColorCount () {
        int[] result = new int[3];  //order = None, White, Black
        for (KeyPeg peg : keyPegs) {
            if (peg.getColor() == Color.BLACK) {
                result[2]++;
            } else if (peg.getColor() == Color.WHITE) {
                result[1]++;
            } else {
                result[0]++;
            }
        }
        return result;
    }
}


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

    public void setCodePeg(int index, CodePeg.CodeColor codeColor) {
        if (index < codePegs.length)
            codePegs[index].setCodeColor(codeColor);
    }

    public KeyPeg getKeyPeg(int index) {
        return index < keyPegs.length ? keyPegs[index] : null;
    }

    public KeyPeg[] getKeyPegs(){
        return keyPegs;
    }

    public void setKeyPeg(int index, KeyPeg.KeyColor keyColor) {
        if (index < keyPegs.length)
            keyPegs[index].setKeyColor(keyColor);
    }

    public Boolean isWinner(){
        int [] colorCount = keyPegColorCount(); //order = None, White, Black
        if (colorCount[2] == 4){
            return true;
        }
        return false;
    }

    // Private methods
    private void setKeyPegs(CodePeg[] keyCodes){
        int keyPegCount = 0;
        for (int i = 0; i <= 3; i ++) {
            for (int j = 0; j <= 3; j++){
                if (codePegs[j].isValid()) {
                    if (keyCodes[i].isValid()){
                        if (keyCodes[j].isValid()){
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
    }

    private int[] keyPegColorCount () {
        int[] result = new int[3];  //order = None, White, Black
        for (KeyPeg peg : keyPegs) {
            if (peg.getColor() == Color.BURLYWOOD) {
                result[0]++;
            } else if (peg.getColor() == Color.WHITE) {
                result[1]++;
            } else {
                result[2]++;
            }
        }
        return result;
    }
}


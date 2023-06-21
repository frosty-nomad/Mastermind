package seis601.mastermind;

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

    public void setKeyPeg(int index, KeyPeg.KeyColor keyColor) {
        if (index < keyPegs.length)
            keyPegs[index].setKeyColor(keyColor);
    }

    // bool isWinner()
    // Private methods
    private void setKeyPegs(CodePeg[] keyCodes){
        keyPegs = new KeyPeg[4];
        int keyPegCount = 0;
//        this.keyCode = keyCode;
        for (int i = 0; i <= 3; i ++) {
            for (int j = 0; j <= 3; j++){
                if (codePegs[j].isValid()) {
                    if (keyCodes[i].isValid()){
                        if (keyCodes[j].getColor() == codePegs[j].getColor()) {
                            keyPegs[keyPegCount] = new KeyPeg(KeyPeg.KeyColor.Black);
                            keyCodes[j].setValid(false);
                            codePegs[j].setValid(false);
                            keyPegCount ++;
                        }
                        else if (keyCodes[i].getColor() == codePegs[j].getColor()) {
                            if (j == i) {
                                keyPegs[keyPegCount] = new KeyPeg(KeyPeg.KeyColor.Black);
                            }
                            else {
                                keyPegs[keyPegCount] = new KeyPeg(KeyPeg.KeyColor.White);
                            }
                            keyCodes[i].setValid(false);
                            codePegs[j].setValid(false);
                            keyPegCount++;
                        }
                    }
                }
            }
        }
        for (int c = keyPegCount ; c < 4; c ++){
            keyPegs[c] = new KeyPeg(KeyPeg.KeyColor.None);
        }
    }

    // Public methods
    public Boolean isWinner(){
        for (KeyPeg peg: keyPegs){
            if (peg.getKeyColor() != KeyPeg.KeyColor.Black){
                return false;
            }
        }
        return true;
    }

    public KeyPeg[] getKeyPegs(){
        return keyPegs;
    }
}


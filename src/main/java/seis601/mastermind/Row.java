package seis601.mastermind;

public class Row {
    private CodePeg[] codePegs;
    private KeyPeg[] keyPegs;

    public Row(CodePeg[] guess, CodePeg[] keyCodes){
        codePegs = guess;
        setKeyPegs(keyCodes);
    }

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
                            keyPegs[keyPegCount] = new KeyPeg(KeyPeg.Color.Black);
                            keyCodes[j].setValid(false);
                            codePegs[j].setValid(false);
                            keyPegCount ++;
                        }
                        else if (keyCodes[i].getColor() == codePegs[j].getColor()) {
                            if (j == i) {
                                keyPegs[keyPegCount] = new KeyPeg(KeyPeg.Color.Black);
                            }
                            else {
                                keyPegs[keyPegCount] = new KeyPeg(KeyPeg.Color.White);
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
            keyPegs[c] = new KeyPeg(KeyPeg.Color.None);
        }
    }

    // Public methods
    public Boolean isWinner(){
        for (KeyPeg peg: keyPegs){
            if (peg.getColor() != KeyPeg.Color.Black){
                return false;
            }
        }
        return true;
    }

    public KeyPeg[] getKeyPegs(){
        return keyPegs;
    }
}


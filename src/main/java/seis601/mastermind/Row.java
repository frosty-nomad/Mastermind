package seis601.mastermind;

public class Row {
    private CodePeg[] codePegs;
    private KeyPeg[] keyPegs;

    // Constructors
    public Row() {
        codePegs = new CodePeg[] { new CodePeg(), new CodePeg(), new CodePeg(), new CodePeg() };
        keyPegs = new KeyPeg[] { new KeyPeg(), new KeyPeg(), new KeyPeg(), new KeyPeg() };
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
}

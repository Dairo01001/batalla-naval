package components;

import javax.swing.JButton;

public class Button extends JButton {

    public Button(String text) {
        super(text);
        init();
    }

    private void init() {
        setFont(Const.FONT);
    }
}

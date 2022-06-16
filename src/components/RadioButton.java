package components;

import javax.swing.JRadioButton;

public class RadioButton extends JRadioButton {

    public RadioButton(String text) {
        super(text);
        init();
    }

    private void init() {
        setFont(Const.FONT);
    }
}

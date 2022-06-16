package components;

import javax.swing.JTextField;

public class TextField extends JTextField {

    public TextField(int columns) {
        super(columns);
        init();
    }
    
    private void init() {
        setFont(Const.FONT);
    }
}

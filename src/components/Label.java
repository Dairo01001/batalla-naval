package components;

import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel {

    private final Font FONT = new Font("Courier New", Font.BOLD, 20);

    public Label(String text) {
        super(text);
        init();
    }

    private void init() {
        setFont(FONT);
    }
}

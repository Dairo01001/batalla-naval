package main;

import components.Button;
import javax.swing.JPanel;

public class PanelScore extends JPanel {

    private Button back;

    public PanelScore() {
        init();
        initComponents();
    }

    private void init() {

    }

    public Button getBack() {
        return back;
    }

    private void initComponents() {
        back = new Button("ATRAS");

        add(back);
    }
}

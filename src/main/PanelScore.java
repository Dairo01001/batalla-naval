package main;

import components.Button;
import components.Const;
import components.Label;
import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import utils.Open;

public class PanelScore extends JPanel {

    private Button back;
    private JTextArea log;

    public PanelScore() {
        init();
        initComponents();
    }

    private void init() {
        setLayout(new BorderLayout());
    }

    public Button getBack() {
        return back;
    }
    
    public JTextArea getLog() {
        return log;
    }

    private void initComponents() {
        back = new Button("ATRAS");
        log = new JTextArea();
        log.setFont(Const.FONT);
        try {
            log.append(Open.openScore());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PanelScore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JScrollPane jsp = new JScrollPane(log);
        
        Label title = new Label("Puntajes");
        
        add(title, BorderLayout.NORTH);
        add(jsp, BorderLayout.CENTER);
        add(back, BorderLayout.SOUTH);
    }
}

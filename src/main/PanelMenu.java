package main;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import components.Button;
import components.TextField;
import javax.swing.JPanel;
import utils.BorderPanel;

public class PanelMenu extends JPanel {

    private Button play;
    private Button scores;
    private Button exit;
    private TextField name;

    public PanelMenu() {
        init();
        initComponents();
    }

    public Button getPlay() {
        return play;
    }

    public Button getScores() {
        return scores;
    }

    public Button getExit() {
        return exit;
    }

    public String getNamePlayer() {
        return name.getText();
    }

    private void init() {
        setLayout(new BorderLayout());
    }

    private void initComponents() {
        play = new Button("JUGAR");
        scores = new Button("SCORE");
        exit = new Button("SALIR");
        name = new TextField(15);

        JPanel sout = new JPanel();
        sout.add(exit);
        
        JPanel nort =  BorderPanel.initPanelBorder("NOMBRE");
        nort.add(name);
        
        add(new PanelCenter(), BorderLayout.CENTER);
        add(sout, BorderLayout.SOUTH);
        add(nort, BorderLayout.NORTH);
    }

    private class PanelCenter extends JPanel {

        public PanelCenter() {
            init();
        }

        private void init() {
            setLayout(new GridBagLayout());
            add(play);
            add(scores);
        }
    }
}

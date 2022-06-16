package main;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import components.Button;
import javax.swing.JPanel;

public class PanelMenu extends JPanel {
    private Button play;
    private Button scores;
    private Button exit;

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
    
    private void init() {
        setLayout(new BorderLayout());
    }
    
    private void initComponents() {
        play = new Button("JUGAR");
        scores = new Button("SCORE");
        exit = new Button("SALIR");
        
        JPanel sout = new JPanel();
        sout.add(exit);
        
        add(new PanelCenter(), BorderLayout.CENTER);
        add(sout, BorderLayout.SOUTH);
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

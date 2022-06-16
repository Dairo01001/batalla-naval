package main;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    
    private CardLayout panels;
    private JPanel mainPanel;
    private PanelMenu panelMenu;
    private PanelScore panelScore;
    private PanelGame panelGame;
    
    public Main() {
        init();
        initComponents();
    }
    
    private void init() {
        setTitle("Batalla Naval");
        setSize(800 , 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    private void initComponents() {
        panels = new CardLayout();
        mainPanel = new JPanel(panels);
        
        panelMenu = new PanelMenu();
        panelMenu.getExit().addActionListener(new ActionExit());
        panelMenu.getScores().addActionListener(new ActionScore());
        panelMenu.getPlay().addActionListener(new ActionPlay());
        
        panelScore = new PanelScore();
        panelScore.getBack().addActionListener(new ActionBack());
        
        panelGame = new PanelGame();
        panelGame.getBack().addActionListener(new ActionBack());
        
        mainPanel.add(panelMenu, "Menu");
        mainPanel.add(panelScore, "Score");
        mainPanel.add(panelGame, "Play");
        
        add(mainPanel);
    }
    
    private class ActionExit implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    
    private class ActionBack implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            panels.show(mainPanel, "Menu");
        }
    }
    
    private class ActionScore implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            panels.show(mainPanel, "Score");
        }
    }
    
    private class ActionPlay implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            panels.show(mainPanel, "Play");
        }
    }
    
    public static void main(String[] args) {
        Main wim = new Main();
        wim.setVisible(true);
    }
}

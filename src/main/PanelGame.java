package main;

import battleship.BattleShip;
import boat.AircraftCarrier;
import boat.Boat;
import boat.Direction;
import boat.Ironcland;
import boat.Submarine;
import boat.Wrecker;
import components.Button;
import components.Const;
import components.Label;
import components.TextField;
import coordinate.Coordinate;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import utils.CheckInputs;

public class PanelGame extends JPanel {

    private final int TURN_PLAYER = 0;
    private final int TURN_MACHINE = 1;

    private CardLayout panels;
    private JPanel mainPanel;
    private PanelConfig panelConfig;
    private PanelStartGame panelStartGame;
    private BattleShip battleShip;
    private PanelBoard boardPlayer;
    private PanelBoard boardMachine;
    private Button start;
    private Button back;

    public PanelGame() {
        battleShip = new BattleShip();
        init();
        initComponents();
    }

    private void init() {
        setFont(Const.FONT);
    }

    private void initComponents() {
        start = new Button("COMENZAR");
        back = new Button("ATRAS");

        panels = new CardLayout();
        mainPanel = new JPanel(panels);

        panelConfig = new PanelConfig();
        panelStartGame = new PanelStartGame();

        mainPanel.add(panelConfig, "config");
        mainPanel.add(panelStartGame, "startGame");

        add(mainPanel);
    }

    public Button getBack() {
        return back;
    }

    private class PanelStartGame extends JPanel {

        private TextField puntoX;
        private TextField puntoY;
        private JTextArea log;
        private Button atack;
        private int turn = TURN_PLAYER;

        public PanelStartGame() {
            init();
            initComponents();
        }

        private void init() {
            boardPlayer = new PanelBoard();
            log = new JTextArea();
            boardMachine = new PanelBoard();

            puntoX = new TextField(4);
            puntoY = new TextField(4);
            atack = new Button("ATACAR");
        }

        private void initComponents() {
            log.append("Data \n");
            log.setEditable(false);
            JPanel panelPlayer = new JPanel(new BorderLayout());
            JPanel panelControlers = new JPanel();
            
            JScrollPane scroll = new JScrollPane(log);
            panelPlayer.add(scroll, BorderLayout.NORTH);

            JPanel titlePuntoX = initPanelBorder("Y");
            titlePuntoX.add(puntoX);

            JPanel titlePuntoY = initPanelBorder("X");
            titlePuntoY.add(puntoY);

            panelControlers.add(titlePuntoY);
            panelControlers.add(titlePuntoX);
            panelControlers.add(atack);

            atack.addActionListener(new ActionAtack());

            JPanel panelBoardPlayer = initPanelBorder("Tablero Jugador");
            panelBoardPlayer.add(boardPlayer);

            panelPlayer.add(panelBoardPlayer, BorderLayout.CENTER);
            panelPlayer.add(panelControlers, BorderLayout.SOUTH);

            JPanel panelBoardMachine = initPanelBorder("Maquina!");
            panelBoardMachine.add(boardMachine);

            add(panelPlayer);
            add(panelBoardMachine);
        }

        private void turnOfMachine() {
            Coordinate shot = battleShip.getMachine().attack();
            if (!battleShip.getBoardPlayer().evaluateShot(shot)) {
                turn = TURN_PLAYER;
            }
            log.append("Machine: ");
            log.append(shot.toString() + "\n");
            boardPlayer.setModelBoard(battleShip.getBoardPlayer());
        }

        private class ActionAtack implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if (CheckInputs.isNumber(puntoX.getText()) && CheckInputs.isNumber(puntoY.getText())) {
                    int x = Integer.parseInt(puntoX.getText());
                    int y = Integer.parseInt(puntoY.getText());

                    if (checkRange(x) && checkRange(y)) {
                        Coordinate shot = new Coordinate(x, y);
                        if (battleShip.getBoardMachine().evaluateShot(shot)) {
                            JOptionPane.showMessageDialog(mainPanel, "Me diste Perro!");
                        }
                        boardMachine.setModelForMachine(battleShip.getBoardMachine());
                        puntoX.setText("");
                        puntoY.setText("");
                        log.append("Player: ");
                        log.append(shot.toString() + "\n");
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, String.format("Ingresa un numero mayor a %d y  menor que %d.", 0, Const.BOART_SIZE), "FUERA DE RANGO", JOptionPane.WARNING_MESSAGE);

                    }
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Ingresa numeros perro Chandoso!");
                }

                if (battleShip.getBoardMachine().isDead()) {
                    JOptionPane.showMessageDialog(mainPanel, "Has Ganado!");
                    
                    panels.show(mainPanel, "config");
                }

                turn = TURN_MACHINE;

                while (turn == TURN_MACHINE) {
                    turnOfMachine();
                }

                if (battleShip.getBoardPlayer().isDead()) {
                    JOptionPane.showMessageDialog(mainPanel, "Has Perdido!");
                    panels.show(mainPanel, "config");
                }
            }
        }
    }

    private class ActionStartGame implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!battleShip.getBoardPlayer().isDone()) {
                JOptionPane.showMessageDialog(mainPanel, "Tienes que ubicar todos tus barcos!");
            } else {
                panels.show(mainPanel, "startGame");
                boardPlayer.setModelBoard(battleShip.getBoardPlayer());
                battleShip.getMachine().buildingBoard(battleShip.getBoardMachine());
                boardMachine.setModelForMachine(battleShip.getBoardMachine());
            }
        }
    }

    private class PanelConfig extends JPanel {

        private Button addBoat;
        private JComboBox dir;
        private PanelBoard boardConfig;
        private TextField coorX;
        private TextField coorY;
        private JComboBox boats;
        int[] countBoats = {0, 0, 0, 0};
        private Label labelBoats[];

        public PanelConfig() {
            init();
            initComponents();
        }

        private void init() {
            setLayout(new BorderLayout());
        }

        private void initComponents() {
            addBoat = new Button("AGREGAR");
            addBoat.addActionListener(new ActionAddBoat());
            coorX = new TextField(5);
            coorY = new TextField(5);
            labelBoats = new Label[4];

            start.addActionListener(new ActionStartGame());

            boardConfig = new PanelBoard();

            JPanel controllers = new JPanel();
            controllers.add(addBoat);
            controllers.add(start);

            JPanel inputs = new JPanel();
            inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));

            JPanel countBoatsPanel = initPanelBorder("Barcos");
            countBoatsPanel.setLayout(new BoxLayout(countBoatsPanel, BoxLayout.Y_AXIS));

            for (int i = 0; i < countBoats.length; i++) {
                labelBoats[i] = new Label(Const.NAMES_BOATS[i] + " " + (Const.QUANTITY_BOATS_TYPE - countBoats[i]));
                countBoatsPanel.add(labelBoats[i]);
            }

            JPanel panelCoorX = initPanelBorder("COORDENADA Y");
            panelCoorX.add(coorX);

            JPanel panelCoorY = initPanelBorder("COORDENADA X");
            panelCoorY.add(coorY);

            JPanel panelDirection = initPanelBorder("ORIENTACION");
            String[] dirs = {"Vertical", "Horizontal"};
            dir = new JComboBox(dirs);
            dir.setFont(Const.FONT);
            panelDirection.add(dir);

            JPanel panelBoats = initPanelBorder("TIPOS DE BARCO");
            boats = new JComboBox(Const.NAMES_BOATS);
            boats.setFont(Const.FONT);
            panelBoats.add(boats);

            inputs.add(countBoatsPanel);
            inputs.add(panelBoats);
            inputs.add(panelCoorY);
            inputs.add(panelCoorX);
            inputs.add(panelDirection);

            add(inputs, BorderLayout.WEST);
            add(controllers, BorderLayout.SOUTH);
            add(boardConfig, BorderLayout.EAST);
            add(back, BorderLayout.NORTH);
        }

        private class ActionAddBoat implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent ae) {
                int index = boats.getSelectedIndex();
                if (countBoats[index] < Const.QUANTITY_BOATS_TYPE) {
                    checkAndCreate(index);
                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Elije otro tipo de Barco!", "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            private void checkAndCreate(int index) {
                if (CheckInputs.isNumber(coorX.getText()) && CheckInputs.isNumber(coorY.getText())) {
                    int x = Integer.parseInt(coorX.getText());
                    int y = Integer.parseInt(coorY.getText());

                    if (checkRange(x) && checkRange(y)) {
                        Boat newBoat = null;
                        Direction newBoatDir = dir.getSelectedItem().toString().equals("Vertical") ? Direction.VERTICAL : Direction.HORIZONTAL;
                        Coordinate newBoatCoor = new Coordinate(x, y);

                        switch (Const.TYPES_BOATS[index]) {
                            case AIRCRAFT_CARRIER:
                                newBoat = new AircraftCarrier(newBoatDir, newBoatCoor);
                                break;
                            case IRONCLAD:
                                newBoat = new Ironcland(newBoatDir, newBoatCoor);
                                break;
                            case SUBMARINE:
                                newBoat = new Submarine(newBoatDir, newBoatCoor);
                                break;
                            case WRECKER:
                                newBoat = new Wrecker(newBoatDir, newBoatCoor);
                                break;
                            default:
                                break;
                        }

                        if (battleShip.getBoardPlayer().addBoat(newBoat)) {
                            boardConfig.setModelBoard(battleShip.getBoardPlayer());
                            countBoats[index]++;

                            for (int i = 0; i < countBoats.length; i++) {
                                labelBoats[i].setText(Const.NAMES_BOATS[i] + " " + (Const.QUANTITY_BOATS_TYPE - countBoats[i]));
                            }

                            coorX.setText("");
                            coorY.setText("");

                        } else {
                            JOptionPane.showMessageDialog(mainPanel, "Fallo al insertar el Barco no debe salirse del tablero ni chocar con algun otro.", "POSICION INVALIDA", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(mainPanel, String.format("Ingresa un numero mayor a %d y  menor que %d.", 0, Const.BOART_SIZE), "FUERA DE RANGO", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(mainPanel, "Ingresa valores validos para las coordenadas!", "ERROR INPUT", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        private JPanel initPanelBorder(String title) {
            JPanel borderPanel = new JPanel();
            borderPanel.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createTitledBorder(null, title, 0, 0, Const.FONT), BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));
            return borderPanel;
        }
    }

    private boolean checkRange(int num) {
        return num >= 0 && num < Const.BOART_SIZE;
    }

    private JPanel initPanelBorder(String title) {
        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, title, 0, 0, Const.FONT), BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return borderPanel;
    }
    
    private void clearBoard() {
        battleShip.getBoardMachine();
    }
}

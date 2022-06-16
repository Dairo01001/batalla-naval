package main;

import battleship.BattleShip;
import boat.AircraftCarrier;
import boat.Boat;
import boat.Direction;
import boat.Ironcland;
import boat.Submarine;
import boat.TypeBoat;
import boat.Wrecker;
import components.Button;
import components.Const;
import components.RadioButton;
import components.TextField;
import coordinate.Coordinate;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.CheckInputs;

public class PanelGame extends JPanel {

    private CardLayout panels;
    private JPanel mainPanel;
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

        mainPanel.add(new PanelConfig(), "Config");

        add(mainPanel);
    }

    public Button getBack() {
        return back;
    }

    private class PanelConfig extends JPanel {

        private Button addBoat;
        private TextField name;
        private JComboBox dir;
        private PanelBoard boardConfig;
        private TextField coorX;
        private TextField coorY;
        private JComboBox boats;
        int[] countBoats = {0, 0, 0, 0};

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
            name = new TextField(15);
            coorX = new TextField(5);
            coorY = new TextField(5);

            boardConfig = new PanelBoard();

            JPanel controllers = new JPanel();
            controllers.add(addBoat);
            controllers.add(start);

            JPanel inputs = new JPanel();
            inputs.setLayout(new BoxLayout(inputs, BoxLayout.Y_AXIS));

            JPanel panelName = initPanelBorder("NOMBRE JUGADOR");
            panelName.add(name);

            JPanel panelCoorX = initPanelBorder("COORDENADA X");
            panelCoorX.add(coorX);

            JPanel panelCoorY = initPanelBorder("COORDENADA Y");
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

            inputs.add(panelName);
            inputs.add(panelBoats);
            inputs.add(panelCoorX);
            inputs.add(panelCoorY);
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
                if (countBoats[index] < 2) {
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
                        Direction newBoatDir = dir.getSelectedItem().toString().equals("Vertical") ? Direction.VERTICAL: Direction.HORIZONTAL;
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

            private boolean checkRange(int num) {
                return num >= 0 && num < Const.BOART_SIZE;
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
}

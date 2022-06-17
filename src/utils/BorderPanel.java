package utils;

import components.Const;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class BorderPanel {

    public static JPanel initPanelBorder(String title) {
        JPanel borderPanel = new JPanel();
        borderPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(null, title, 0, 0, Const.FONT), BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        return borderPanel;
    }
}

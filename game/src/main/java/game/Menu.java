package game;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {

    public Menu() {
        JButton button = new JButton("Start");
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuCallback.getInstance().startOnlineGame();
            }
        });

        this.add(button);
    }

}

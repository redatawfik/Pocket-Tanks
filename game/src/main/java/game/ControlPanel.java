package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private static ControlPanel instance;

    private JLabel powerLabel;
    private JLabel angelLabel;
    private JLabel numOfMovesLabel;
    private JButton pauseButton;

    private ControlPanel() {
        init();
    }

    public static ControlPanel getInstance() {
        if (instance == null)
            instance = new ControlPanel();
        return instance;
    }

    private void init() {
        setLayout(new GridLayout(1, 5));

        powerLabel = new JLabel();
        powerLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        powerLabel.setBackground(Color.BLACK);

        angelLabel = new JLabel();
        angelLabel.setBackground(Color.BLACK);
        angelLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));

        numOfMovesLabel = new JLabel();
        numOfMovesLabel.setBackground(Color.ORANGE);
        numOfMovesLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));

        pauseButton = new JButton("End the game");
        pauseButton.setBackground(Color.RED);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setFont(new Font("Monospaced", Font.BOLD, 20));

        pauseButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                World.destroy();
                GameFrame.getInstance().changeDisplayToMenu();
            }
        });

        add(powerLabel);
        add(angelLabel);
        add(numOfMovesLabel);
        add(pauseButton);
    }

    public void setPower(int power) {
        powerLabel.setText("Power: " + power);
    }

    public void setAngel(int angel) {
        if (angel > 360) {
            angelLabel.setText("Angel: " + (angel - 360));
        } else if (angel < 0) {
            angelLabel.setText("Angel: " + (angel + 360));
        } else {
            angelLabel.setText("Angel: " + angel);
        }
    }

    public void setNumOfMoves(int movesLeft) {
        numOfMovesLabel.setText("Moves left: " + movesLeft);
    }
}

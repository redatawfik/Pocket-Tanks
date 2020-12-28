package game;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private static ControlPanel instance;

    private JLabel powerLabel;
    private JLabel angelLabel;
    private JLabel numOfMovesLabel;

    private ControlPanel() {
        init();
    }

    public static ControlPanel getInstance() {
        if (instance == null)
            instance = new ControlPanel();
        return instance;
    }

    private void init() {
        setLayout(new GridLayout(1, 4));

        powerLabel = new JLabel();
        powerLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        powerLabel.setBackground(Color.BLACK);

        angelLabel = new JLabel();
        angelLabel.setBackground(Color.BLACK);
        angelLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));

        numOfMovesLabel = new JLabel();
        numOfMovesLabel.setBackground(Color.ORANGE);
        numOfMovesLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));

        add(powerLabel);
        add(angelLabel);
        add(numOfMovesLabel);
    }

    public void setPower(int power) {
        powerLabel.setText(String.valueOf(power));
    }

    public void setAngel(int angel) {
        angelLabel.setText(String.valueOf(angel));
    }

    public void setNumOfMoves(int movesLeft) {
        numOfMovesLabel.setText(String.valueOf(movesLeft));
    }
}

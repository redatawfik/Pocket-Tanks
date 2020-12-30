package game;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private static ControlPanel instance;

    private JLabel powerLabel;
    private JLabel angelLabel;
    private JLabel numOfMovesLabel;
    private JLabel scoreLabel;

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

        scoreLabel = new JLabel();
        scoreLabel.setBackground(Color.BLACK);
        scoreLabel.setFont(new Font(Font.SERIF, Font.BOLD, 30));

        add(powerLabel);
        add(angelLabel);
        add(numOfMovesLabel);
        add(scoreLabel);
    }

    public void setPower(int power) {
        powerLabel.setText("Power: " + power);
    }

    public void setAngel(int angel) {
        angelLabel.setText("Angel: " + angel);
    }

    public void setNumOfMoves(int movesLeft) {
        numOfMovesLabel.setText("Moves left: " + movesLeft);
    }

    public void setScore(int score) {
        scoreLabel.setText("");
    }
}

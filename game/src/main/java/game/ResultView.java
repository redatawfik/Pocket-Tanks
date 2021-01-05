package game;

import game.menu.GameMode;
import game.networking.Site;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class ResultView extends JPanel implements ActionListener {

    private final JLabel resultLabel;
    private final JButton closeButton;
    private Image backgroundImage;

    public ResultView(String result) {
        setLayout(new GridLayout(2, 1));
        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource("/menu_background.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        JPanel resultPanel = new JPanel();
        resultPanel.setBackground(new Color(1, 0, 0, 0));
        resultLabel = new JLabel(result);
        resultLabel.setForeground(Color.RED);
        resultLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        resultPanel.add(resultLabel);

        JPanel closePanel = new JPanel();
        closePanel.setBackground(new Color(0, 0, 0, 0));
        closeButton = new JButton("Close");
        closeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        closeButton.addActionListener(this);
        closePanel.add(closeButton);

        add(resultPanel);
        add(closePanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            // Notify websocket about end of the game.
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {

                // Winner client sends results
                if (World.getInstance().getMyTank().getScore() >= World.getInstance().getEnemyTank().getScore()) {
                    // To Be formate as a JSON object
                    System.out.println("/////////////////////////////////////////////////");
                    Site.sendResult(Site.getUserName() + Site.getEnemyUsername() +
                            World.getInstance().getMyTank().getScore() +
                            World.getInstance().getEnemyTank().getScore());
                }
            }

            World.destroy();
            GameFrame.getInstance().changeDisplayToMenu();
        }
    }
}

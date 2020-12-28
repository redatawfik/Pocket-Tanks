package game.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Menu extends JPanel implements ActionListener {

    private static Menu instance;

    private Image backgroundImage;
    private final JButton startOnlineButton;
    private final JButton startLocalButton;
    private final JButton exitButton;

    private Menu() {
        setLayout(new GridLayout(4, 1));
        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource("/menu_background.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 0, 0, 0));
        JLabel title = new JLabel("Pocket Tanks");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
        titlePanel.add(title);

        JPanel startOnlineMatchPanel = new JPanel();
        startOnlineMatchPanel.setBackground(new Color(0, 0, 0, 0));
        startOnlineButton = new JButton("Start Online Match");
        startOnlineButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        startOnlineButton.addActionListener(this);
        startOnlineMatchPanel.add(startOnlineButton);

        JPanel startLocalGamePanel = new JPanel();
        startLocalGamePanel.setBackground(new Color(0, 0, 0, 0));
        startLocalButton = new JButton("Start Local Game");
        startLocalButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        startLocalButton.addActionListener(this);
        startLocalGamePanel.add(startLocalButton);

        JPanel exitPanel = new JPanel();
        exitPanel.setBackground(new Color(0, 0, 0, 0));
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        exitButton.addActionListener(this);
        exitPanel.add(exitButton);

        add(titlePanel);
        add(startOnlineMatchPanel);
        // add(loadingPanel());
        add(startLocalGamePanel);
        add(exitPanel);
    }

    public static Menu getInstance() {
        if (instance == null)
            instance = new Menu();
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startOnlineButton) {
            MenuCallback.getInstance().startOnlineGame();
        } else if (e.getSource() == startLocalButton) {
            MenuCallback.getInstance().startLocalGame();
        } else if (e.getSource() == exitButton) {
            MenuCallback.getInstance().exit();
        }
    }
}

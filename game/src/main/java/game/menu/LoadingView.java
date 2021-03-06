package game.menu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class LoadingView extends JPanel {
    private static LoadingView instance;

    private Image backgroundImage;

    private LoadingView() {
        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource("/menu_background.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 0, 0, 0));
        JLabel title = new JLabel("Pocket Tanks");
        title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
        titlePanel.add(title);

        BoxLayout layoutMgr = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(layoutMgr);

        URL imageURL = getClass().getResource("/spinner.gif");
        ImageIcon imageIcon = new ImageIcon(imageURL);
        JLabel iconLabel = new JLabel();
        iconLabel.setIcon(imageIcon);
        iconLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        imageIcon.setImageObserver(iconLabel);

        JLabel label = new JLabel("Loading...");
        label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 33));
        label.setForeground(Color.WHITE);
        label.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        this.add(titlePanel);
        this.add(iconLabel);
        this.add(label);
    }

    public static LoadingView getInstance() {
        if (instance == null)
            instance = new LoadingView();
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
}

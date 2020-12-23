package game;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

    public Menu() {
        //set the JFrame title
        super("Pocket Tanks");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton button = new JButton();
        button.setText("Start");
        button.addActionListener(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        Game game = new Game();
                        game.start();
                        setVisible(false);
                }
        });

        getContentPane().add(button, BorderLayout.CENTER);
        setSize(500, 300);

        //getContentPane().removeAll();
        //this.setResizable(false);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        // pack();

        //center the JFrame on the screen

            setVisible(true);
    }

}

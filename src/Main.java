import snake.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by allan on 02/12/2017.
 */

public class Main extends JFrame {


    public Main() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("PLC - Snake That Eat Parrots");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame ex = new Main();
                ex.setVisible(true);

            }
        });
    }
}

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

        String HEAD_DOWN = "assets/snake/down_head.png";

        add(new Board());

        setResizable(false);
        pack();

        ImageIcon head_down = new ImageIcon(HEAD_DOWN);
        Image head_down_img = head_down.getImage();

        setIconImage(head_down_img);
        setTitle("Python");
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

import snake.Board;

import javax.swing.*;
import java.awt.*;

/**
 * Created by allan on 02/12/2017.
 */
public class Main extends JFrame {


    public Main() {

        add(new Board());

        setResizable(false);
        pack();

        setTitle("Cobra Debochada");
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

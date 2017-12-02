package auxiliar;

import javax.swing.*;

/**
 * Created by joaoluiz on 12/2/17.
 */

public class WindowBuilder {

    public WindowBuilder(JFrame window){
        window.pack();
        window.setVisible(true);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setName("PLC - Snake Score Threads");
        window.setTitle("PLC - Snake Score Threads");
    }

}

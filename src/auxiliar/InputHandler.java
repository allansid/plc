package auxiliar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class InputHandler implements KeyListener{

    private String[] key_names = new String[200];
    private boolean[] pressed_keys = new boolean[200];
    private boolean[] released_keys = new boolean[200];

    public InputHandler(){}

    public void createKey(String s, int i){
        key_names[i] = s;
    }

    public boolean isKeyPressed(String s){
        for (int i = 0; i < 200; i++) {
            if(s == key_names[i] && pressed_keys[i]){
                return true;
            }
        }
        return false;
    }

    public boolean isKeyTyped(String s){
        for (int i = 0; i < 200; i++) {
            if(s == key_names[i] && released_keys[i]){
                released_keys[i] = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e){
        pressed_keys[e.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        pressed_keys[e.getKeyCode()] = false;
        released_keys[e.getKeyCode()] = true;
    }

}
package auxiliar;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import java.awt.event.*;

public class GameSettings extends ApplicationHandler{
    //snake
    int tamanho = 10;
    int x_vel = 10;
    int y_vel = 0;
    int max_tamanho = 50*50;
    int[][] cobra = new int[max_tamanho][2];
    int tamanho_atual = 4;
    //---

    //food
    int x_f,y_f;
    Random r = new Random();
    //---

    public void setup(){
        setFps(30);
        setAltura(500);
        setLargura(500);
        for (int i = 0;i < max_tamanho; i++ ) {
            for (int j = 0;j < 2; j++ ) {
                cobra[i][j] = -1;
            }
        }
        cobra[0][0] = 250;
        cobra[0][1] = 250;
        cobra[1][0] = 240;
        cobra[1][1] = 250;
        cobra[2][0] = 230;
        cobra[2][1] = 250;
        cobra[3][0] = 220;
        cobra[3][1] = 250;
        cobra[4][0] = 210;
        cobra[4][1] = 250;
        x_f =r.nextInt(450)+25;
        y_f =r.nextInt(450)+25;

        createKey("UP",KeyEvent.VK_UP);
        createKey("DOWN",KeyEvent.VK_DOWN);
        createKey("RIGHT",KeyEvent.VK_RIGHT);
        createKey("LEFT",KeyEvent.VK_LEFT);
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0,0,getLargura(),getAltura());

        g.setColor(Color.WHITE);
        for (int i = 0;i < max_tamanho;i++) {
            if(cobra[i][0] != -1)
                g.fillOval(cobra[i][0],cobra[i][1],tamanho,tamanho);
        }

        g.setColor(Color.CYAN);
        g.fillOval(x_f,y_f,tamanho,tamanho);
    }

    public void update(){
        //collision
        int dx,dy,ad,d;
        dx = Math.abs(x_f-cobra[0][0]);
        dy = Math.abs(y_f-cobra[0][1]);
        ad=(int)Math.sqrt((dx*dx)+(dy*dy));
        d = tamanho*2;
        if (ad < d) {
            x_f =r.nextInt(450)+25;
            y_f =r.nextInt(450)+25;
            cobra[tamanho_atual][0] = 0;
            tamanho_atual++;
        }

        //tail follow
        for (int i = max_tamanho-1;i>0;i--) {
            if (cobra[i][0] != -1) {
                cobra[i][0] = cobra[i-1][0];
                cobra[i][1] = cobra[i-1][1];
            }
        }
        cobra[0][0] = cobra[0][0]+=x_vel;
        cobra[0][1] = cobra[0][1]+=y_vel;

        //border loop
        if (cobra[0][0] > getLargura()) {
            cobra[0][0] = 0;
        }else if (cobra[0][0] < 0) {
            cobra[0][0] = getLargura();
        }else if (cobra[0][1] > getAltura()) {
            cobra[0][1] = 0;
        }else if (cobra[0][1] < 0) {
            cobra[0][1] = getAltura();
        }

        //tail collision
        for (int i = 5;i<max_tamanho;i++) {
            if(cobra[i][0] != -1){
                dx = Math.abs(cobra[i][0]-cobra[0][0]);
                dy = Math.abs(cobra[i][1]-cobra[0][1]);
                ad=(int)Math.sqrt((dx*dx)+(dy*dy));
                d = tamanho*2;
                if (ad < d) {
                    for (int k = 5;k < max_tamanho; k++) {
                        for (int j = 0;j < 2; j++ ) {
                            cobra[k][j] = -1;
                        }
                    }
                    tamanho_atual=4;
                    break;
                }
            }
        }
    }

    public void checkInput(){
        if(isKeyPressed("UP") && y_vel == 0){
            x_vel = 0;
            y_vel = -tamanho;
        }else if(isKeyPressed("DOWN") && y_vel == 0){
            x_vel = 0;
            y_vel = tamanho;
        }else if(isKeyPressed("RIGHT") && x_vel == 0){
            x_vel = tamanho;
            y_vel = 0;
        }else if(isKeyPressed("LEFT") && x_vel == 0){
            x_vel = -tamanho;
            y_vel = 0;
        }
    }
}
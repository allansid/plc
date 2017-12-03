package snake;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Board extends JPanel implements ActionListener, Runnable {

    private final int DOT_SIZE = 35;
    private final int B_WIDTH = DOT_SIZE*35;
    private final int B_HEIGHT = DOT_SIZE*20;
    private final int ALL_DOTS = (B_HEIGHT*B_WIDTH)/(DOT_SIZE*DOT_SIZE);
    private final int RAND_POS_X = 34;
    private final int RAND_POS_Y = 19;
    private int DELAY = 140;

//    SNAKE ASSETS PATH
    private final String HEAD_UP = "assets/snake/up_head.png";
    private final String HEAD_DOWN = "assets/snake/down_head.png";
    private final String HEAD_LEFT = "assets/snake/left_head.png";
    private final String HEAD_RIGHT = "assets/snake/right_head.png";
    private final String TAIL_UP = "assets/snake/up_tail.png";
    private final String TAIL_DOWN = "assets/snake/down_tail.png";
    private final String TAIL_LEFT = "assets/snake/left_tail.png";
    private final String TAIL_RIGHT = "assets/snake/right_tail.png";

//    ANIMALS ASSETS PATH
    private final String CAVALO = "assets/animals/cavalo.png";
    private final String COELHO = "assets/animals/coelho.png";
    private final String ELEFANTE = "assets/animals/elefante.png";
    private final String GIRAFA = "assets/animals/girafa.png";
    private final String MACACO = "assets/animals/macaco.png";
    private final String PANDA = "assets/animals/panda.png";
    private final String PAPAGAIO = "assets/animals/papagaio.png";
    private final String PINGUIN = "assets/animals/pinguin.png";
    private final String PORCO = "assets/animals/porco.png";

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean esc = false;
    private boolean inGame = true;
    private boolean pause = false;
    private int score = 0;
    private Timer timer;
    private JLabel scoreLabel = new JLabel("Score: "+score);

    //    Snake Images
    private Image tail_up_img;
    private Image tail_down_img;
    private Image tail_left_img;
    private Image tail_right_img;
    private Image head_up_img;
    private Image head_down_img;
    private Image head_left_img;
    private Image head_right_img;

//     Animals Images
    private Image cavalo_img;
    private Image coelho_img;
    private Image elefante_img;
    private Image girafa_img;
    private Image macaco_img;
    private Image panda_img;
    private Image papagaio_img;
    private Image pinguin_img;
    private Image porco_img;
    private Image prey_img;

    public Board() {

        scoreLabel.setForeground(Color.white);
        scoreLabel.setSize(new Dimension(60,30));
        addKeyListener(new TAdapter());
        add(scoreLabel);
        setBackground(Color.DARK_GRAY);
        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        Thread a = new Thread(this);
        a.start();
        initGame();
    }

    private void loadImages() {

//        Snake
        ImageIcon head_up = new ImageIcon(HEAD_UP);
        head_up_img = head_up.getImage();

        ImageIcon head_down = new ImageIcon(HEAD_DOWN);
        head_down_img = head_down.getImage();

        ImageIcon head_left = new ImageIcon(HEAD_LEFT);
        head_left_img = head_left.getImage();

        ImageIcon head_right = new ImageIcon(HEAD_RIGHT);
        head_right_img = head_right.getImage();

        ImageIcon tail_up = new ImageIcon(TAIL_UP);
        tail_up_img = tail_up.getImage();

        ImageIcon tail_down = new ImageIcon(TAIL_DOWN);
        tail_down_img = tail_down.getImage();

        ImageIcon tail_left = new ImageIcon(TAIL_LEFT);
        tail_left_img = tail_left.getImage();

        ImageIcon tail_right = new ImageIcon(TAIL_RIGHT);
        tail_right_img = tail_right.getImage();

//        Animals

        ImageIcon cavalo = new ImageIcon(CAVALO);
        cavalo_img = cavalo.getImage();

        ImageIcon coelho = new ImageIcon(COELHO);
        coelho_img = coelho.getImage();

        ImageIcon elefante = new ImageIcon(ELEFANTE);
        elefante_img = elefante.getImage();

        ImageIcon girafa = new ImageIcon(GIRAFA);
        girafa_img = girafa.getImage();

        ImageIcon macaco = new ImageIcon(MACACO);
        macaco_img = macaco.getImage();

        ImageIcon panda = new ImageIcon(PANDA);
        panda_img = panda.getImage();

        ImageIcon papagaio = new ImageIcon(PAPAGAIO);
        papagaio_img = papagaio.getImage();

        ImageIcon pinguin = new ImageIcon(PINGUIN);
        pinguin_img = pinguin.getImage();

        ImageIcon porco = new ImageIcon(PORCO);
        porco_img = porco.getImage();
    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = DOT_SIZE - (z * 10);
            y[z] = DOT_SIZE;
        }

        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        if(esc){
            System.exit(0);
        }
        if (inGame) {
            g.drawImage(prey_img, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    if (downDirection) {
                        g.drawImage(head_down_img, x[z], y[z], this);
                    } else if (upDirection) {
                        g.drawImage(head_up_img, x[z], y[z], this);
                    } else if (leftDirection) {
                        g.drawImage(head_left_img, x[z], y[z], this);
                    } else if (rightDirection) {
                        g.drawImage(head_right_img, x[z], y[z], this);
                    }
                } else {
                    if (downDirection) {
                        g.drawImage(tail_down_img, x[z], y[z], this);
                    } else if (upDirection) {
                        g.drawImage(tail_up_img, x[z], y[z], this);
                    } else if (leftDirection) {
                        g.drawImage(tail_left_img, x[z], y[z], this);
                    } else if (rightDirection) {
                        g.drawImage(tail_right_img, x[z], y[z], this);
                    }
                }
//                System.out.println("z: "+z);
//                System.out.println("x: " + x[z] + "  y: "+y[z]);
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }

        if (pause) {
            timer.stop();
        } else {
            timer.start();
        }
    }

    private void gameOver(Graphics g) {

        String msg = "Game Over" + '\n'+ " Score: " + score;
        Font small = new Font("f", Font.BOLD, 40);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.red);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }


    private synchronized void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            if (prey_img == cavalo_img || prey_img == girafa_img){
                score = score + 6;
            } else if (prey_img == elefante_img) {
                score = score + 10;
            } else if (prey_img == macaco_img || prey_img == panda_img) {
                score = score + 3;
            } else if (prey_img == pinguin_img) {
                score = score + 2;
            } else {
                score++;
            }
            dots++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

//        System.out.println(x[0] + " " + y[0]);
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            System.out.println("Entrei" + x[0]);
            inGame = false;
        }

        if(!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r;

        r = (int) (Math.random() * RAND_POS_X);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS_Y);
        apple_y = ((r * DOT_SIZE));
//        System.out.println("Coord Apple: " + apple_x + ", " + apple_y);
        int prey = (int) (Math.random() * 9);

        if (prey == 0) {
          prey_img = elefante_img;
        } else if (prey == 1) {
            prey_img = cavalo_img;
        } else if (prey == 2) {
            prey_img = coelho_img;
        } else if (prey == 3) {
            prey_img = girafa_img;
        } else if (prey == 4) {
            prey_img = macaco_img;
        } else if (prey == 5) {
            prey_img = panda_img;
        } else if (prey == 6) {
            prey_img = papagaio_img;
        } else if (prey == 7) {
            prey_img = pinguin_img;
        } else if (prey == 8) {
            prey_img = porco_img;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {
            checkApple();
            checkCollision();
            move();
        }
        repaint();

    }

    @Override
    public void run() {
        while(true){
            scoreLabel.setText("Score: "+score);
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if (key == KeyEvent.VK_ESCAPE) {
                esc = true;
            }

            if (key == KeyEvent.VK_P) {
                pause = !pause;
            }

            if (key == KeyEvent.VK_M){
                DELAY = DELAY + 10;
            }

            if (key == KeyEvent.VK_N){
                DELAY = DELAY - 10;
            }

        }
    }
}
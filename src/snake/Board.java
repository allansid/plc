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
    private final String HIPPO = "assets/animals/hippo.png";
    private final String RABBIT = "assets/animals/rabbit.png";
    private final String ELEPHANT = "assets/animals/elephant.png";
    private final String GIRAFFE = "assets/animals/giraffe.png";
    private final String MONKEY = "assets/animals/monkey.png";
    private final String PANDA = "assets/animals/panda.png";
    private final String PARROT = "assets/animals/parrot.png";
    private final String PENGUIN = "assets/animals/penguin.png";
    private final String PIG = "assets/animals/pig.png";

//    OTHERS ASSETS PATH
    private final String APPLE = "assets/apple.png";
    private final String BOMB = "assets/bomb.png";

    private final String WALL_CENTER = "assets/muro_centro.png";
    private final String WALL_LEFT = "assets/muro_esq.png";
    private final String WALL_RIGHT = "assets/muro_dir.png";

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];
    private final int xWall[] = new int[5*DOT_SIZE];
    private final int yWall[] = new int[5*DOT_SIZE];

//    private final int x2[] = new int[ALL_DOTS];
//    private final int y2[] = new int[ALL_DOTS];

    private int dots;
//    private int dots2;
    private int animal_x;
    private int animal_y;
    private int apple_x;
    private int apple_y;
    private int bomb_x;
    private int bomb_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
//    private boolean aDirection = false;
//    private boolean dDirection = true;
//    private boolean wDirection = false;
//    private boolean sDirection = false;
    private boolean esc = false;
    private boolean inGame = true;
    private boolean pause = false;
    private Timer timer;

    private long seg = 0;
    private long time = 0;
    private int score = 0;
    private int fps = 0;
    private JLabel timeLabel = new JLabel("Time:" + time);
    private JLabel scoreLabel = new JLabel("Score: " + score);
    private JLabel fpsLabel = new JLabel("FPS:" + fps);

//    Snake Images
    private Image tail_up_img;
    private Image tail_down_img;
    private Image tail_left_img;
    private Image tail_right_img;
    private Image head_up_img;
    private Image head_down_img;
    private Image head_left_img;
    private Image head_right_img;

//    Animals Images
    private Image hippo_img;
    private Image rabbit_img;
    private Image elephant_img;
    private Image giraffe_img;
    private Image monkey_img;
    private Image panda_img;
    private Image parrot_img;
    private Image penguin_img;
    private Image pig_img;

    private Image prey_img;

//    Wall Images
    private Image wall_left_img;
    private Image wall_right_img;
    private Image wall_center_img;

//    Images
    private Image apple_img;
    private Image bomb_img;

    public Board() {

        scoreLabel.setForeground(Color.white);
        scoreLabel.setSize(new Dimension(60,30));
        timeLabel.setForeground(Color.red);
        timeLabel.setSize(new Dimension(30,15));
        addKeyListener(new TAdapter());
        add(scoreLabel);
        add(timeLabel);

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

        ImageIcon hippo = new ImageIcon(HIPPO);
        hippo_img = hippo.getImage();

        ImageIcon rabbit = new ImageIcon(RABBIT);
        rabbit_img = rabbit.getImage();

        ImageIcon elephant = new ImageIcon(ELEPHANT);
        elephant_img = elephant.getImage();

        ImageIcon giraffe = new ImageIcon(GIRAFFE);
        giraffe_img = giraffe.getImage();

        ImageIcon monkey = new ImageIcon(MONKEY);
        monkey_img = monkey.getImage();

        ImageIcon panda = new ImageIcon(PANDA);
        panda_img = panda.getImage();

        ImageIcon parrot = new ImageIcon(PARROT);
        parrot_img = parrot.getImage();

        ImageIcon penguin = new ImageIcon(PENGUIN);
        penguin_img = penguin.getImage();

        ImageIcon pig = new ImageIcon(PIG);
        pig_img = pig.getImage();

//        Others

        ImageIcon apple = new ImageIcon(APPLE);
        apple_img = apple.getImage();
        ImageIcon bomb = new ImageIcon(BOMB);
        bomb_img = bomb.getImage();


        ImageIcon wall_center = new ImageIcon(WALL_CENTER);
        wall_center_img = wall_center.getImage();
        ImageIcon wall_left = new ImageIcon(WALL_LEFT);
        wall_left_img = wall_left.getImage();
        ImageIcon wall_right = new ImageIcon(WALL_RIGHT);
        wall_right_img = wall_right.getImage();
    }

    private void initGame() {

        initPlayer1();

//        if (false) {
//            initPlayer2();
//        }

        locateApple();

        seg = System.currentTimeMillis()/1000;

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initPlayer1() {
        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = DOT_SIZE - (z * 10);
            y[z] = DOT_SIZE;
        }
    }

//    private void initPlayer2() {
//        dots2 = 3;
//
//        for (int z = 0; z < dots2; z++) {
//            x2[z] = DOT_SIZE - (z * 10);
//            y2[z] = DOT_SIZE;
//        }
//    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        time += System.currentTimeMillis()/1000 - seg;
        seg = System.currentTimeMillis()/1000;

        if(esc){
            System.exit(0);
        }

        if (inGame) {
            g.drawImage(prey_img, animal_x, animal_y, this);

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
            }

            //            if (false) {
//                for (int z = 0; z < dots2; z++) {
//                    if (z == 0) {
//                        if (sDirection) {
//                            g.drawImage(head_down_img, x2[z], y2[z], this);
//                        } else if (wDirection) {
//                            g.drawImage(head_up_img, x2[z], y2[z], this);
//                        } else if (aDirection) {
//                            g.drawImage(head_left_img, x2[z], y2[z], this);
//                        } else if (dDirection) {
//                            g.drawImage(head_right_img, x2[z], y2[z], this);
//                        }
//                    } else {
//                        if (sDirection) {
//                            g.drawImage(tail_down_img, x2[z], y2[z], this);
//                        } else if (wDirection) {
//                            g.drawImage(tail_up_img, x2[z], y2[z], this);
//                        } else if (aDirection) {
//                            g.drawImage(tail_left_img, x2[z], y2[z], this);
//                        } else if (dDirection) {
//                            g.drawImage(tail_right_img, x2[z], y2[z], this);
//                        }
//                    }
//                    //                System.out.println("z: "+z);
//                    //                System.out.println("x: " + x[z] + "  y: "+y[z]);
//                }
//            }

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

        if ((x[0] == animal_x) && (y[0] == animal_y)) {

            if (prey_img == hippo_img || prey_img == giraffe_img){
                score = score + 6;
            } else if (prey_img == elephant_img) {
                score = score + 10;
            } else if (prey_img == monkey_img || prey_img == panda_img) {
                score = score + 3;
            } else if (prey_img == penguin_img) {
                score = score + 2;
            } else {
                score++;
            }
            dots++;
//            dots2++;
            locateApple();
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }
//        for (int z = dots2; z > 0; z--) {
//            x2[z] = x2[(z - 1)];
//            y2[z] = y2[(z - 1)];
//        }
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

//        if (aDirection) {
//            x2[0] -= DOT_SIZE;
//        }
//
//        if (dDirection) {
//            x2[0] += DOT_SIZE;
//        }
//
//        if (wDirection) {
//            y2[0] -= DOT_SIZE;
//        }
//
//        if (sDirection) {
//            y2[0] += DOT_SIZE;
//        }
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
//            System.out.println("Entrei" + x[0]);
            inGame = false;
        }

        if(!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {
        int r;

        r = (int) (Math.random() * RAND_POS_X);
        animal_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS_Y);
        animal_y = ((r * DOT_SIZE));
//        System.out.println("Coord Apple: " + animal_x + ", " + animal_y);
        int prey = (int) (Math.random() * 9);

        if (prey == 0) {
          prey_img = elephant_img;
        } else if (prey == 1) {
            prey_img = hippo_img;
        } else if (prey == 2) {
            prey_img = rabbit_img;
        } else if (prey == 3) {
            prey_img = giraffe_img;
        } else if (prey == 4) {
            prey_img = monkey_img;
        } else if (prey == 5) {
            prey_img = panda_img;
        } else if (prey == 6) {
            prey_img = parrot_img;
        } else if (prey == 7) {
            prey_img = penguin_img;
        } else if (prey == 8) {
            prey_img = pig_img;
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
            timeLabel.setText("Time: "+time);
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


//            if ((key == KeyEvent.VK_A) && (!dDirection)) {
//                aDirection = true;
//                wDirection = false;
//                dDirection = false;
//            }
//
//            if ((key == KeyEvent.VK_F) && (!aDirection)) {
//                dDirection = true;
//                wDirection = false;
//                sDirection = false;
//            }
//
//            if ((key == KeyEvent.VK_W) && (!sDirection)) {
//                wDirection = true;
//                dDirection = false;
//                aDirection = false;
//            }
//
//            if ((key == KeyEvent.VK_S) && (!wDirection)) {
//                sDirection = true;
//                dDirection = false;
//                aDirection = false;
//            }

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
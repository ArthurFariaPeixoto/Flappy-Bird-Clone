package main;

import entities.*;
import graficos.*;
import world.TuboGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public static JFrame frame;
    private Thread thread;
    private boolean isRunning = true;
    public static final int WIDTH = 260;
    public static final int HEIGHT = 180;
    public static final int SCALE = 3;

    private BufferedImage image;

    public static List<Entitie> entities;
    public static Spritesheet spritesheet;
    public static Player player;

    public static TuboGenerator tubogenerator;

    public static UI ui;

    public static double score = 0;
    public static double highScore = 0;

    public Game(){

        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        ui = new UI();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        /*Iniciando objetos*/
        entities = new ArrayList<Entitie>();
        spritesheet = new Spritesheet("/Spritesheet.png");
        player = new Player(WIDTH/2 -100, HEIGHT/2, 20, 20, spritesheet.getSprite(0, 0, 20, 20));
        tubogenerator = new TuboGenerator();
        entities.add(player);


    }

    public void initFrame() {
        frame = new JFrame("Flappy Bird");
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }

    public void tick() {

        tubogenerator.tick();
        for (int i = 0; i < entities.size(); i++) {
            Entitie e = entities.get(i);
            e.tick();

        }
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(20, 100, 250));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for(int i=0; i<entities.size(); i++){
            Entitie e = entities.get(i);
            e.render(g);
        }

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        ui.render(g);
        bs.show();
    }

    public void run() {
        long lastTime = System.nanoTime();
        double amoutOfTicks = 60.0;
        double ns = 1000000000 / amoutOfTicks;
        double delta = 0;
        int frames = 0;
        double timer = System.currentTimeMillis();
        requestFocus();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                frames++;
                delta--;
            }
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
        stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            Player.up=true;
            Sound.wing.play();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            Player.up=false;
        }
    }


}

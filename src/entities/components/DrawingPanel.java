package entities.components;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Random;

public class DrawingPanel extends JPanel
{
    // Fields
    private final int PANEL_WIDTH = 500;
    private final int PANEL_HEIGHT = 500;
    private final Image enemy;
    private final Image background;
    private final Timer timer;
    private int xVelocity = 5;
    private int yVelocity = 5;
    private int x = 0;
    private int y = 0;

    // Constructors
    public DrawingPanel()
    {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.black);
        enemy = new ImageIcon(getClass().getResource("/resources/images/enemy.png")).getImage();
        background = new ImageIcon(getClass().getResource("/resources/images/space_background.png")).getImage();

        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/resources/audio/audio.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
        }
        catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex){
            ex.printStackTrace();
        }

        timer = new Timer(1, this::timer_Action);
        timer.start();
    }

    // Methods
    @Override
    public void paint(Graphics gobj)
    {
        super.paint(gobj);
        Graphics2D g = (Graphics2D) gobj;
        g.drawImage(background, 0, 0, null);
        g.drawImage(enemy, x, y, null);
    }

    // Events
    private void timer_Action(ActionEvent e)
    {
        Random rand = new Random();
        xVelocity += rand.nextInt(-1, 2);
        yVelocity += rand.nextInt(-1, 2);

        if(xVelocity > 5) xVelocity = 5;
        if(xVelocity < -5) xVelocity = -5;
        if(yVelocity > 5) yVelocity = 5;
        if(yVelocity < -5) yVelocity = -5;

        x += xVelocity;
        y += yVelocity;

        if(x < 0)
        {
            xVelocity *= -1;
            x = 0;
        }
        if(x + enemy.getWidth(null) > PANEL_WIDTH)
        {
            xVelocity *= -1;
            x = PANEL_WIDTH - enemy.getWidth(null);
        }
        if(y < 0)
        {
            yVelocity *= -1;
            y = 0;
        }
        if(y + enemy.getHeight(null) > PANEL_HEIGHT)
        {
            yVelocity *= -1;
            y = PANEL_HEIGHT - enemy.getHeight(null);
        }

        repaint();
    }
}

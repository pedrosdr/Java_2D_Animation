package entities.frames;

import entities.components.DrawingPanel;

import javax.swing.*;

public class MainFrame extends JFrame
{
    // Components
    private DrawingPanel panel;

    // Constructors
    public MainFrame()
    {
        setTitle("My 2D Application");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initialize();
        setVisible(true);
    }

    // Methods
    private void initialize()
    {
        // panel
        panel = new DrawingPanel();
        add(panel);
        pack();
    }
}

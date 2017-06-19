package fractals;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * Displays a fractal BufferedImage provided by FractalImager.
 */
public class FractalView extends JPanel
{

    private BufferedImage image; // The image to draw

    /**
     * Constructor for FractalViews.
     */
    public FractalView()
    {
        super();
        image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        Graphics big = image.createGraphics();
        big.setColor(Color.GRAY);
        big.fillRect(0, 0, 1000, 1000);
        setPreferredSize(new Dimension(1000, 1000));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Draws a deterministic fractal.
     * @param ifs The IFS to use.
     * @param depth The depth to recurse to.
     * @param initPixX The initial pixel x co-ordinate.
     * @param initPixY The initial pixel y co-ordinate.
     */
    public void renderDeterministic(IFS ifs, int depth, int initPixX, int initPixY)
    {
        image = FractalImager.renderDeterministic(ifs, depth, initPixX, initPixY);
        repaint();
    }

    /**
     * Draws a chaos-game fractal.
     * @param ifs The IFS to use.
     * @param depth The depth to recurse to.
     * @param threshold The threshold beneath which to ignore points.
     * @param initPixX The initial pixel x co-ordinate.
     * @param initPixY The initial pixel y co-ordinate.
     */
    public void renderRandom(IFS ifs, int depth, int threshold, int initPixX, int initPixY)
    {
        image = FractalImager.renderRandom(ifs, depth, threshold, initPixX, initPixY);
        repaint();
    }

}

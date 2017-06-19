package fractals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Requests boolean matrices from provided IFSes, and
 * turns them into black and grey images.
 */
public class FractalImager
{

    /**
     * Prepares a 1000 by 1000 boolean matrix with a single 'on' cell.
     * @param initPixX The x co-ordinate of the single 'on' cell.
     * @param initPixY The y co-ordinate of the single 'on' cell.
     * @return The 1000 by 1000 boolean matrix.
     */
    private static boolean[][] prepMatrix(int initPixX, int initPixY)
    {
        boolean[][] matrix = new boolean[1000][1000];
        matrix[initPixX][initPixY] = true;
        return matrix;
    }

    /**
     * Renders a deterministic fractal using the provided parameters.
     * @param ifs The IFS to use.
     * @param depth The depth to recurse to.
     * @param initPixX The initial pixel x co-ordinate.
     * @param initPixY The initial pixel y co-ordinate.
     * @return The finished fractal.
     */
    public static BufferedImage renderDeterministic(IFS ifs, int depth, int initPixX, int initPixY)
    {
        boolean[][] matrix = prepMatrix(initPixX, initPixY);
        matrix = ifs.evolve(depth, matrix);
        return drawMatrix(matrix);
    }

    /**
     * Renders a chaos-game fractal using the provided parameters.
     * @param ifs The IFS to use.
     * @param depth The depth to recurse to.
     * @param threshold The threshold beneath which to ignore points.
     * @param initPixX The initial pixel x co-ordinate.
     * @param initPixY The initial pixel y co-ordinate.
     * @return The finished fractal.
     */
    public static BufferedImage renderRandom(IFS ifs, int depth, int threshold, int initPixX, int initPixY)
    {
        boolean[][] matrix = prepMatrix(initPixX, initPixY);
        matrix = ifs.ranUpdate(depth, 0, threshold, matrix);
        return drawMatrix(matrix);
    }

    /**
     * Turns a provided boolean matrix into a 1000 by 1000 pixel black-on-grey BufferedImage.
     * @param matrix The matrix to draw from.
     * @return The resulting image.
     */
    private static BufferedImage drawMatrix(boolean[][] matrix)
    {
        BufferedImage bi = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        Graphics big = bi.createGraphics();
        big.setColor(Color.GRAY);
        big.fillRect(0, 0, 1000, 1000);
        big.setColor(Color.BLACK);
        for (int x = 0, xx = matrix.length; x < xx; x++)
        {
            for (int y = 0, yy = matrix[0].length; y < yy; y++)
            {
                if (matrix[x][y])
                {
                    big.fillRect(x, y, 1, 1);
                }
            }
        }
        return bi;
    }

}

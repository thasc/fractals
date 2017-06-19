package fractals;

import java.awt.Point;

/**
 * Represents an IFS Map, a collection of double values used to push points around.
 */
public class IFSMap {

    private double a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double p;

    public IFSMap(double a, double b, double c, double d, double e, double f, double p) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.p = p;
    }

    /**
     * Maps an x co-ordinate.
     * @param x
     * @param y
     * @return The new x.
     */
    public double mapX(double x, double y) {
        return a * x + b * y + e;
    }

    /**
     * Maps an y co-ordinate.
     * @param x
     * @param y
     * @return The new y.
     */
    public double mapY(double x, double y) {
        return c * x + d * y + f;
    }

    /**
     * Maps a whole point.
     * @param point
     * @return The new point.
     */
    public Point mapPoint(Point point) {
        double x = point.getX();
        double y = point.getY();
        return new Point((int) mapX(x, y), (int) mapY(x, y));
    }

    /**
     * @return the a
     */
    public double getA() {
        return a;
    }

    /**
     * @return the b
     */
    public double getB() {
        return b;
    }

    /**
     * @return the c
     */
    public double getC() {
        return c;
    }

    /**
     * @return the d
     */
    public double getD() {
        return d;
    }

    /**
     * @return the e
     */
    public double getE() {
        return e;
    }

    /**
     * @return the f
     */
    public double getF() {
        return f;
    }

    /**
     * @return the p
     */
    public double getP() {
        return p;
    }
}

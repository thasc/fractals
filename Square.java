package fractals;

/**
 * Represents the Square IFS.
 */
public class Square extends IFS {

    /**
     * Constructor for Squares.
     */
    public Square() {
        super();
        maps.add(new IFSMap(0.5, 0, 0, 0.5, 0, 0, (1.0 / 4.0)));
        maps.add(new IFSMap(0.5, 0, 0, 0.5, 499, 0, (1.0 / 4.0)));
        maps.add(new IFSMap(0.5, 0, 0, 0.5, 0, 499, (1.0 / 4.0)));
        maps.add(new IFSMap(0.5, 0, 0, 0.5, 499, 499, (1.0 / 4.0)));
    }
}

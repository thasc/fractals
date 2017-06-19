package fractals;

/**
 * Represents the Sierpinski Gasket IFS.
 */
public class Gasket extends IFS {

    /**
     * Constructor for Gaskets.
     */
    public Gasket() {
        super();
        maps.add(new IFSMap(0.5, 0, 0, 0.5, 0, 0, (1.0 / 3.0)));
        maps.add(new IFSMap(0.5, 0, 0, 0.5, 499, 0, (1.0 / 3.0)));
        maps.add(new IFSMap(0.5, 0, 0, 0.5, 0, 499, (1.0 / 3.0)));
    }
}

package fractals;

/**
 * Represents the Fern IFS.
 */
public class Fern extends IFS {

    /**
     * Constructor for Ferns.
     */
    public Fern() {
        super();
        maps.add(new IFSMap(0, 0, 0, 0.16, 499, 0, 0.01));
        maps.add(new IFSMap(0.85, 0.04, -0.04, 0.85, 499 * 0.15, 160 + 499 * 0.04, 0.85));
        maps.add(new IFSMap(0.2, -0.26, 0.23, 0.22, 499 * 0.8, 160 - 499 * 0.23, 0.07));
        maps.add(new IFSMap(-0.15, 0.28, 0.26, 0.24, 499 * 1.15, 44 - 499 * 0.26, 0.07));
    }
}

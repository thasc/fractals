package fractals;

/**
 * Represents the Heighway Dragon IFS.
 */
public class Heighway extends IFS
{

    /**
     * Constructor for Heighways.
     */
    public Heighway()
    {
        super();
        maps.add(new IFSMap(0.5, -0.5, 0.5, 0.5, 0, 0, (1.0 / 2.0)));
        maps.add(new IFSMap(-0.5, -0.5, 0.5, -0.5, 999, 0, (1.0 / 2.0)));
    }

}

package fractals;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents an IFS, and stores a list of IFSMaps.
 */
public class IFS
{

    protected ArrayList<IFSMap> maps;
    protected ArrayList<Double> listOfQs; // list of probabilities stacked on top of each other, used by find()
    protected Random random;

    /**
     * Constructor for IFSes
     */
    public IFS()
    {
        maps = new ArrayList<IFSMap>();
        random = new Random();
    }

    /**
     * Returns the maps.
     * @return The maps.
     */
    public ArrayList<IFSMap> getMaps()
    {
        return maps;
    }

    /**
     * Gets a random number and returns its associated IFS map.
     * @return A random but probabilistically-weighted IFS map.
     */
    public IFSMap choose()
    {
        if(listOfQs == null)
        {
            // not exactly a taxing process, but it might get called hundreds of times
            // so make it only happen once
            listOfQs = createListOfQs();
        }
        double u = random.nextDouble();
        IFSMap chosen = maps.get(find(u));
        return chosen;
    }

    /**
     * Creates a stacked list of probabilities for the find() method.
     * @return A stacked list of probabilities.
     */
    private ArrayList<Double> createListOfQs()
    {
        listOfQs = new ArrayList<Double>();
        double currentQ = 0.0;
        for (int k = 0, kk = maps.size(); k < kk; k++)
        {
            currentQ += maps.get(k).getP();
            listOfQs.add(currentQ);
        }
        return listOfQs;
    }

    /**
     * Given a number between 0 and 1, returns the associated IFS map.
     * @param u The random number.
     * @return The associated IFS map.
     */
    private int find(double u)
    {
        double lastQ = 0.0;
        for (int q = 0, qq = listOfQs.size(); q < qq; q++)
        {
            if (u > lastQ && u < listOfQs.get(q))
            {
                return q;
            }
            else
            {
                lastQ = listOfQs.get(q);
            }
        }
        return 0; // the user's definition should prevent this
    }

    /**
     * Deterministically evolves a boolean matrix.
     * @param depth The recursion depth.
     * @param matrix The matrix to evolve.
     * @return The evolved form.
     */
    public boolean[][] evolve(int depth, boolean[][] matrix)
    {
        if (depth == 0)
        {
            return matrix;
        }
        else
        {
            boolean[][] tempMatrix = new boolean[matrix.length][matrix[0].length];
            for (int x = 0, xx = matrix.length; x < xx; x++)
            {
                for (int y = 0, yy = matrix[0].length; y < yy; y++)
                {
                    if (matrix[x][y])
                    {
                        for (IFSMap map : getMaps())
                        {
                            int newX = (int) java.lang.StrictMath.round(map.mapX(x, y));
                            int newY = (int) java.lang.StrictMath.round(map.mapY(x, y));
                            if (isWithinBounds(newX, newY, tempMatrix))
                            {
                                tempMatrix[newX][newY] = true;
                            }
                        }
                    }
                }
            }
            for (int x = 0, xx = tempMatrix.length; x < xx; x++)
            {
                for (int y = 0, yy = tempMatrix[0].length; y < yy; y++)
                {
                    if (tempMatrix[x][y])
                    {
                        matrix[x][y] = true;
                    }
                }
            }
            return evolve(depth - 1, matrix);
        }
    }

    /**
     * Randomly evolves a boolean matrix.
     * @param finalDepth The final depth of the recursion.
     * @param depth The current depth of the recursion.
     * @param threshold The threshold beneath which points are ignored.
     * @param matrix The matrix to evolve.
     * @return The matrix's evolved form.
     */
    public boolean[][] ranUpdate(int finalDepth, int depth, int threshold, boolean[][] matrix)
    {
        // seems to be a bug with this, no idea why, fix it tomorrow
        if (depth == finalDepth)
        {
            return matrix;
        }
        else
        {
            boolean[][] tempMatrix = new boolean[matrix.length][matrix[0].length];
            IFSMap map = choose();
            for (int x = 0, xx = matrix.length; x < xx; x++)
            {
                for (int y = 0, yy = matrix[0].length; y < yy; y++)
                {
                    if (matrix[x][y])
                    {
                        int newX = (int) java.lang.StrictMath.round(map.mapX(x, y));
                        int newY = (int) java.lang.StrictMath.round(map.mapY(x, y));
                        if (isWithinBounds(newX, newY, tempMatrix))
                        {
                            tempMatrix[newX][newY] = true;
                        }
                    }
                }
            }
            if (depth > threshold)
            {
                for (int x = 0, xx = tempMatrix.length; x < xx; x++)
                {
                    for (int y = 0, yy = tempMatrix[0].length; y < yy; y++)
                    {
                        if (tempMatrix[x][y])
                        {
                            matrix[x][y] = true;
                        }
                    }
                }
                return ranUpdate(finalDepth, depth + 1, threshold, matrix);
            }
            else
            {
                return ranUpdate(finalDepth, depth + 1, threshold, tempMatrix);
            }
        }
    }

    /**
     * Checks that a given point is within the matrix.
     * @param x
     * @param y
     * @param matrix The matrix to check with.
     * @return True if within bounds, false otherwise.
     */
    private boolean isWithinBounds(int x, int y, boolean[][] matrix)
    {
        return x >= 0 && y >= 0 && x < matrix.length && y < matrix[0].length;
    }

    /**
     * Directly adds a map to this IFS.
     * @param map
     */
    public void addMap(IFSMap map)
    {
        maps.add(map);
    }

}

package fractals;

import javax.swing.JTextField;

/**
 * A collection of JTextFields for a certain map in the IFSDefFrame.
 * Collected together for ease of processing.
 */
public class TextFieldGroup
{

    private JTextField a;
    private JTextField b;
    private JTextField c;
    private JTextField d;
    private JTextField e;
    private JTextField f;
    private JTextField p;

    /**
     * Constructor for TextFieldGroups.
     */
    public TextFieldGroup()
    {
        a = new JTextField(5);
        b = new JTextField(5);
        c = new JTextField(5);
        d = new JTextField(5);
        e = new JTextField(5);
        f = new JTextField(5);
        p = new JTextField(5);
    }

    /**
     * Alternative constructor for TextFieldGroups, copies an IFS map's values.
     */
    public TextFieldGroup(IFSMap map)
    {
        a = new JTextField(5);
        b = new JTextField(5);
        c = new JTextField(5);
        d = new JTextField(5);
        e = new JTextField(5);
        f = new JTextField(5);
        p = new JTextField(5);
        a.setText(Double.toString(map.getA()));
        b.setText(Double.toString(map.getB()));
        c.setText(Double.toString(map.getC()));
        d.setText(Double.toString(map.getD()));
        e.setText(Double.toString(map.getE()));
        f.setText(Double.toString(map.getF()));
        p.setText(Double.toString(map.getP()));
    }

    /**
     * @return the a
     */
    public JTextField getA()
    {
        return a;
    }

    /**
     * @param a the a to set
     */
    public void setA(JTextField a)
    {
        this.a = a;
    }

    /**
     * @return the b
     */
    public JTextField getB()
    {
        return b;
    }

    /**
     * @param b the b to set
     */
    public void setB(JTextField b)
    {
        this.b = b;
    }

    /**
     * @return the c
     */
    public JTextField getC()
    {
        return c;
    }

    /**
     * @param c the c to set
     */
    public void setC(JTextField c)
    {
        this.c = c;
    }

    /**
     * @return the d
     */
    public JTextField getD()
    {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(JTextField d)
    {
        this.d = d;
    }

    /**
     * @return the e
     */
    public JTextField getE()
    {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(JTextField e)
    {
        this.e = e;
    }

    /**
     * @return the f
     */
    public JTextField getF()
    {
        return f;
    }

    /**
     * @param f the f to set
     */
    public void setF(JTextField f)
    {
        this.f = f;
    }

    /**
     * @return the p
     */
    public JTextField getP()
    {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(JTextField p)
    {
        this.p = p;
    }

}

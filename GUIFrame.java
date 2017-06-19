package fractals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 * GUI for the Fractaliser. Main method is the entry point for the program.
 */
public class GUIFrame extends JFrame implements IFSDefFrameListener
{

    private static final int DETERMINISTIC = 0;
    private static final int RANDOM = 1;
    private int algorithmType;
    private boolean usingCustom;
    private int initPixX;
    private int initPixY;
    private int depth;
    private int threshold;
    private IFS ifs;
    private IFS customIFS;
    private JPanel panel;
    private FractalView fv;
    // menubar items
    private JMenuBar menuBar;
    private JMenu algorithmMenu;
    private JRadioButtonMenuItem aDetMenuItem;
    private JRadioButtonMenuItem aRandMenuItem;
    private JMenu ifsMenu;
    private JRadioButtonMenuItem iFernMenuItem;
    private JRadioButtonMenuItem iGasketMenuItem;
    private JRadioButtonMenuItem iSquareMenuItem;
    private JRadioButtonMenuItem iCustomMenuItem;
    private JMenuItem iDefCustomMenuItem;
    private JMenu renderingMenu;
    private JMenuItem rInitPixMenuItem;
    private JMenuItem rRecurDepthMenuItem;
    private JMenuItem rRenderThresMenuItem;
    private JMenuItem rRenderMenuItem;

    /**
     * Entry point for the program. No arguments used.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                new GUIFrame().setVisible(true);
            }

        });
    }

    /**
     * Constructor for GUIFrames.
     */
    public GUIFrame()
    {
        super("Fractaliser");
        // set up default values...
        algorithmType = DETERMINISTIC;
        usingCustom = false;
        initPixX = 500;
        initPixY = 500;
        depth = 50;
        threshold = 25;
        ifs = new Fern();
        customIFS = new IFS();
        initComponents();
    }

    /**
     * Sets up GUI Frame components.
     */
    private void initComponents()
    {
        // create menubar
        menuBar = new JMenuBar();
        // create Algorithms menu
        algorithmMenu = new JMenu("Algorithm");
        ButtonGroup algorithmButtonGroup = new ButtonGroup();
        aDetMenuItem = new JRadioButtonMenuItem("Deterministic");
        aDetMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                aDetMenuItemActionPerformed(evt);
            }

        });
        aDetMenuItem.setSelected(true);
        aRandMenuItem = new JRadioButtonMenuItem("Random");
        aRandMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                aRandMenuItemActionPerformed(evt);
            }

        });
        algorithmButtonGroup.add(aDetMenuItem);
        algorithmButtonGroup.add(aRandMenuItem);
        algorithmMenu.add(aDetMenuItem);
        algorithmMenu.add(aRandMenuItem);
        // create IFS menu
        ifsMenu = new JMenu("IFS");
        ButtonGroup ifsButtonGroup = new ButtonGroup();
        iFernMenuItem = new JRadioButtonMenuItem("Fern");
        iFernMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                iFernMenuItemActionPerformed(evt);
            }

        });
        iFernMenuItem.setSelected(true);
        iGasketMenuItem = new JRadioButtonMenuItem("Gasket");
        iGasketMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                iGasketMenuItemActionPerformed(evt);
            }

        });
        iSquareMenuItem = new JRadioButtonMenuItem("Square");
        iSquareMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                iSquareMenuItemActionPerformed(evt);
            }

        });
        iCustomMenuItem = new JRadioButtonMenuItem("Custom");
        iCustomMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                iCustomMenuItemActionPerformed(evt);
            }

        });
        iDefCustomMenuItem = new JMenuItem("Define custom...");
        iDefCustomMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                iDefCustomMenuItemActionPerformed(evt);
            }

        });
        ifsButtonGroup.add(iFernMenuItem);
        ifsButtonGroup.add(iGasketMenuItem);
        ifsButtonGroup.add(iSquareMenuItem);
        ifsButtonGroup.add(iCustomMenuItem);
        ifsMenu.add(iFernMenuItem);
        ifsMenu.add(iGasketMenuItem);
        ifsMenu.add(iSquareMenuItem);
        ifsMenu.add(iCustomMenuItem);
        ifsMenu.addSeparator();
        ifsMenu.add(iDefCustomMenuItem);
        // create Rendering Menu
        renderingMenu = new JMenu("Rendering");
        rInitPixMenuItem = new JMenuItem("Initial pixel...");
        rInitPixMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rInitPixMenuItemActionPerformed(evt);
            }

        });
        rRecurDepthMenuItem = new JMenuItem("Recursion depth...");
        rRecurDepthMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rRecurDepthMenuItemActionPerformed(evt);
            }

        });
        rRenderThresMenuItem = new JMenuItem("Rendering threshold...");
        rRenderThresMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rRenderThresMenuItemActionPerformed(evt);
            }

        });
        rRenderMenuItem = new JMenuItem("Render");
        rRenderMenuItem.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                rRenderMenuItemActionPerformed(evt);
            }

        });
        rRenderMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0));
        renderingMenu.add(rInitPixMenuItem);
        renderingMenu.add(rRecurDepthMenuItem);
        renderingMenu.add(rRenderThresMenuItem);
        renderingMenu.addSeparator();
        renderingMenu.add(rRenderMenuItem);
        // bring it all together
        menuBar.add(algorithmMenu);
        menuBar.add(ifsMenu);
        menuBar.add(renderingMenu);
        setJMenuBar(menuBar);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.CENTER;
        fv = new FractalView();
        panel.add(fv, c);
        setContentPane(panel);
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // Centre on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Reacts to the Algorithm/Deterministic button being clicked.
     * Sets the current algorithm to Determinstic.
     * @param evt
     */
    public void aDetMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        algorithmType = DETERMINISTIC;
    }

    /**
     * Reacts to the Algorithm/Random button being clicked.
     * Sets the current algorithm to Random.
     * @param evt
     */
    public void aRandMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        algorithmType = RANDOM;
    }

    /**
     * Reacts to the IFS/Fern button being clicked.
     * Sets the current IFS to Fern.
     * @param evt
     */
    public void iFernMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        ifs = new Fern();
        usingCustom = false;
    }

    /**
     * Reacts to the IFS/Gasket button being clicked.
     * Sets the curretn IFS to Gasket.
     * @param evt
     */
    public void iGasketMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        ifs = new Gasket();
        usingCustom = false;
    }

    /**
     * Reacts to the IFS/Square button being clicked.
     * Sets the current IFS to Square.
     * @param evt
     */
    public void iSquareMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        ifs = new Square();
        usingCustom = false;
    }

    /**
     * Reacts to the IFS/Custom button being clicked.
     * Sets the current IFS to Custom.
     * @param evt
     */
    public void iCustomMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        ifs = customIFS;
        usingCustom = true;
    }

    /**
     * Reacts to the IFS/Define Custom... button being clicked.
     * Displays IFSDefFrame to define a custom IFS.
     * @param evt
     */
    public void iDefCustomMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        new IFSDefFrame(this).setVisible(true);
    }

    /**
     * Reacts to the Rendering/Initial Pixel... button being clicked.
     * Displays dialog box to get the initial pixel co-ordinates.
     * @param evt
     */
    public void rInitPixMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        String input = JOptionPane.showInputDialog(this, "Enter co-ordinates, each between 0 and 999 inclusive (format: x, y)...");
        if (input != null)
        {
            String[] inputs = input.split(",");
            if (inputs.length == 2)
            {
                try
                {
                    int xvalue = Integer.parseInt(inputs[0].trim());
                    int yvalue = Integer.parseInt(inputs[1].trim());
                    if (xvalue >= 0 && xvalue < 1000 && yvalue >= 0 && yvalue < 1000)
                    {
                        initPixX = xvalue;
                        initPixY = yvalue;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Both numbers must be between 0 and 999 inclusive.");
                    }
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(this, "One or both values are not valid integers.");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "The format is x, y - two numbers separated by a comma.");
            }
        }
    }

    /**
     * Reacts to the Rendering/Recursion Depth... button being clicked.
     * Displays a dialog box to get the recursion depth.
     * @param evt
     */
    public void rRecurDepthMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        String input = JOptionPane.showInputDialog(this, "Recursion depth...");
        if (input != null)
        {
            try
            {
                int value = Integer.parseInt(input);
                if (value >= 0)
                {
                    depth = value;
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Recursion depth must be positive.");
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "That is not a valid number.");
            }
        }
    }

    /**
     * Reacts to the Rendering/Rendering Threshold... button being clicked.
     * Displays a dialog box to get the rendering threshold.
     * @param evt
     */
    public void rRenderThresMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        String input = JOptionPane.showInputDialog(this, "Rendering threshold...");
        if (input != null)
        {
            try
            {
                int value = Integer.parseInt(input);
                if (value >= 0)
                {
                    threshold = value;
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Rendering threshold must be positive.");
                }
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "That is not a valid number.");
            }
        }
    }

    /**
     * Reacts to the Rendering/Render button being clicked.
     * Renders the currently-set IFS and options.
     * @param evt
     */
    public void rRenderMenuItemActionPerformed(java.awt.event.ActionEvent evt)
    {
        if(algorithmType == RANDOM && threshold >= depth)
        {
            JOptionPane.showMessageDialog(this, "Rendering threshold equals or exceeds recursion depth.\nPrepare for an underwhelming prefractal.");
        }
        setTitle("Fractaliser - Rendering...");
        if (algorithmType == DETERMINISTIC)
        {
            fv.renderDeterministic(ifs, depth, initPixX, initPixY);
        }
        else if (algorithmType == RANDOM)
        {
            fv.renderRandom(ifs, depth, threshold, initPixX, initPixY);
        }
        else
        {
            // well, this shouldn't happen
        }
        setTitle("Fractaliser");
    }

    /**
     * Sets the custom IFS.
     * @param customIFS The IFS to set.
     */
    public void setCustomIFS(IFS customIFS)
    {
        this.customIFS = customIFS;
        if(usingCustom)
        {
            ifs = customIFS;
        }
    }

}

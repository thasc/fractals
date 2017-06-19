package fractals;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI for defining the maps in a custom IFS.
 */
public class IFSDefFrame extends JFrame
{

    private IFSDefFrameListener listener;
    private ArrayList<TextFieldGroup> fieldGroups;
    private JPanel fieldPanel;
    private JButton addNewMapButton;
    private JButton fernButton;
    private JButton gasketButton;
    private JButton squareButton;
    private JButton acceptIFSButton;
    private GridBagConstraints mainConstraints;

    /**
     * Constructor for IFSDefFrames
     * @param listener The listener to inform of the new IFS.
     */
    public IFSDefFrame(IFSDefFrameListener listener)
    {
        super("Define custom IFS...");
        this.listener = listener;
        fieldGroups = new ArrayList<TextFieldGroup>();
        // five initial blank field groups...
        fieldGroups.add(new TextFieldGroup());
        fieldGroups.add(new TextFieldGroup());
        fieldGroups.add(new TextFieldGroup());
        fieldGroups.add(new TextFieldGroup());
        fieldGroups.add(new TextFieldGroup());
        initComponents();
    }

    /**
     * Sets up the IFSDefFrame.
     */
    private void initComponents()
    {
        setLayout(new GridBagLayout());
        mainConstraints = new GridBagConstraints();
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 0;
        //mainConstraints.fill = GridBagConstraints.HORIZONTAL;
        mainConstraints.insets = new Insets(5, 5, 5, 5);
        addNewMapButton = new JButton("Add map");
        addNewMapButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                addNewMapButtonActionPerformed(evt);
            }

        });
        add(addNewMapButton, mainConstraints);
        mainConstraints.gridx++;
        fernButton = new JButton("Fern preset");
        fernButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                fernButtonActionPerformed(evt);
            }

        });
        add(fernButton, mainConstraints);
        mainConstraints.gridx++;
        gasketButton = new JButton("Gasket preset");
        gasketButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                gasketButtonActionPerformed(evt);
            }

        });
        add(gasketButton, mainConstraints);
        mainConstraints.gridx++;
        squareButton = new JButton("Square preset");
        squareButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                squareButtonActionPerformed(evt);
            }

        });
        add(squareButton, mainConstraints);
        mainConstraints.gridx++;
        acceptIFSButton = new JButton("Accept");
        acceptIFSButton.addActionListener(new java.awt.event.ActionListener()
        {

            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                acceptIFSButtonActionPerformed(evt);
            }

        });
        add(acceptIFSButton, mainConstraints);
        mainConstraints.gridwidth = mainConstraints.gridx + 1;
        mainConstraints.gridx = 0;
        mainConstraints.gridy = 1;
        add(new JLabel("Note: maps with any empty cells will not be added."), mainConstraints);
        initFieldPanel();
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // Centre on the screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Sets up the IFS map definition panel within the frame. Expands dynamically.
     */
    private void initFieldPanel()
    {
        mainConstraints.gridy = 2;
        if (fieldPanel != null)
        {
            remove(fieldPanel);
        }
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        GridBagConstraints fpc = new GridBagConstraints();
        fpc.insets = new Insets(5, 5, 5, 5);
        fpc.gridx = 0;
        fpc.gridy = 0;
        for (int i = 0, ii = fieldGroups.size(); i < ii; i++)
        {
            TextFieldGroup fieldGroup = fieldGroups.get(i);
            String mapNumber = mapNumber = Integer.toString(i);
            if(i < 10)
            {
                mapNumber = "0" + mapNumber;
            }
            fieldPanel.add(new JLabel("Map " + mapNumber + " - "), fpc);
            fpc.gridx++;
            fieldPanel.add(new JLabel("A: "), fpc);
            fpc.gridx++;
            fieldPanel.add(fieldGroup.getA(), fpc);
            fpc.gridx++;
            fieldPanel.add(new JLabel("B: "), fpc);
            fpc.gridx++;
            fieldPanel.add(fieldGroup.getB(), fpc);
            fpc.gridx++;
            fieldPanel.add(new JLabel("C: "), fpc);
            fpc.gridx++;
            fieldPanel.add(fieldGroup.getC(), fpc);
            fpc.gridx++;
            fieldPanel.add(new JLabel("D: "), fpc);
            fpc.gridx++;
            fieldPanel.add(fieldGroup.getD(), fpc);
            fpc.gridx++;
            fieldPanel.add(new JLabel("E: "), fpc);
            fpc.gridx++;
            fieldPanel.add(fieldGroup.getE(), fpc);
            fpc.gridx++;
            fieldPanel.add(new JLabel("F: "), fpc);
            fpc.gridx++;
            fieldPanel.add(fieldGroup.getF(), fpc);
            fpc.gridx++;
            fieldPanel.add(new JLabel("P: "), fpc);
            fpc.gridx++;
            fieldPanel.add(fieldGroup.getP(), fpc);
            fpc.gridx = 0;
            fpc.gridy++;
        }
        mainConstraints.gridheight = 5;
        add(fieldPanel, mainConstraints);
        pack();
    }

    /**
     * Checks and sets an IFS provided by the user.
     */
    private void acceptIFS()
    {
        // run checks, prune empty maps
        IFS customIFS = new IFS();
        for (TextFieldGroup group : fieldGroups)
        {
            try
            {
                double a = Double.parseDouble(group.getA().getText());
                double b = Double.parseDouble(group.getB().getText());
                double c = Double.parseDouble(group.getC().getText());
                double d = Double.parseDouble(group.getD().getText());
                double e = Double.parseDouble(group.getE().getText());
                double f = Double.parseDouble(group.getF().getText());
                double p = Double.parseDouble(group.getP().getText());
                IFSMap map = new IFSMap(a, b, c, d, e, f, p);
                customIFS.addMap(map);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Invalid map found, discarding");
            }
        }
        listener.setCustomIFS(customIFS);
        dispose();
    }

    public void addNewMapButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        fieldGroups.add(new TextFieldGroup());
        initFieldPanel();
    }

    public void fernButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        setSpecificIFS(new Fern());
    }

    public void gasketButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        setSpecificIFS(new Gasket());
    }

    public void squareButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        setSpecificIFS(new Square());
    }

    /**
     * Sets a specific IFS (Fern, Gasket, Square).
     * @param ifs
     */
    private void setSpecificIFS(IFS ifs)
    {
        fieldGroups.clear();
        for (IFSMap map : ifs.getMaps())
        {
            fieldGroups.add(new TextFieldGroup(map));
        }
        initFieldPanel();
    }

    public void acceptIFSButtonActionPerformed(java.awt.event.ActionEvent evt)
    {
        acceptIFS();
    }

}

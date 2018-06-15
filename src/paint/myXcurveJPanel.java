package src.paint;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BasicStroke;
import java.awt.Image;
import java.awt.image.BufferedImage;


import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.AttributeSet;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


import java.text.DateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.nio.file.Path;

import javax.imageio.ImageIO;

import net.java.dev.designgridlayout.DesignGridLayout;



import java.lang.reflect.Field;



import java.util.ArrayList;
import java.util.Vector;


//a#############################################################################################
//a##
//a##        myXcurveParasJFrame -> the tcurve parameters basic frame
//a##
//a##            myXcurveJFrame -> the frame include a curve panel
//a##
//a##            myXcurveJPanel -> the panel to paint 
//a##
//a##        myXcurveAboutDialog -> About dialog
//a##
//a############################################################################################# 
//a#################################
//a##
//a##  myXcurveJPanel for myXcurveJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXcurveJPanel extends JPanel {

    private int n;
    private float[] line;
    public String txtfile;
    private float maxz;
    private float minz;

    private Graphics2D g2d;

    private float linewidth;

    private BasicStroke  stokeLine;
    private BasicStroke  stokeRuler;

    private Color lineColor;

    public myXcurveJPanel(int n, String txtfile, int linewidth, int linecolor){

        this.n = n;
        this.txtfile = txtfile;
        this.linewidth = (float)linewidth;

        if(linecolor==1)lineColor = Color.RED;
        if(linecolor==2)lineColor = Color.BLUE;
        if(linecolor==3)lineColor = Color.GREEN;
        if(linecolor==4)lineColor = Color.BLACK;

        int count = readTxt(n,txtfile);
        if(count<n)System.out.println("count<n");

        this.n = count-1;

        getMinMax();
        stokeLine = new BasicStroke(linewidth);
        stokeRuler = new BasicStroke(1.0f);

        repaint();
      }
    /* paint */
    public void paint(Graphics g) {

        super.paint(g);
        g2d = (Graphics2D) g;

        drawRuler(g2d);

        float radiox = (float)(getWidth()-40)/(float)n;
        float radioz = (float)(getHeight()-40)/(maxz-minz);



        for(int in =0 ;in<n-1;in++){

            int x0 = (int)(in*radiox)+20;
            int z0 = getHeight()-20-(int)((line[in]-minz)*radioz);
            int x1 = (int)((in+1)*radiox)+20;
            int z1 = getHeight()-20-(int)((line[in+1]-minz)*radioz);

            int zz0 = getHeight()-20-(int)((-minz)*radioz);

            g2d.setColor(lineColor);
            g2d.setStroke(stokeLine);
            g2d.drawLine(x0, z0, x1, z1);

            zeroAxis(g2d, x0, x1, zz0);
            }


      }
    public void zeroAxis(Graphics2D g, int x0, int x1, int z) {
        g2d.setStroke(stokeRuler);
        g.setColor(Color.BLACK );
        g.drawLine(x0, z, x1, z);

      }
    public void getMinMax(){

        maxz = minz = line[0];
        for(int i=0;i<n;i++){
            if(maxz<line[i])maxz=line[i];
            if(minz>line[i])minz=line[i];
            }
        System.out.println("max = "+maxz+", minz = "+minz);
      }
    /*read TXT*/
    public int readTxt(int n, String txtName){
        
        line = new float[n];
        int count = 0 ;
        try{
            Scanner scanner=new Scanner(new FileInputStream(txtName));
            while(scanner.hasNextDouble()&&count<n){
                line[count++] = (float)scanner.nextDouble();

                  }
        }catch(FileNotFoundException e){
            System.out.println("The "+txtName+" not found.");
            return 0;
            }
    
        return count;
      }
    /* ruler */
    public void drawRuler(Graphics2D g) {

        int height = getSize().height;
        int width = getSize().width;

        g2d.setStroke(stokeRuler);
        g.setColor(Color.BLUE );

        for (int iz=height-20; iz > 0; iz--) {
            int pos = (iz-height);

            if (pos % 50 == 0) {      
                if(iz>=20)g.drawLine(0, iz, 20, iz);
            }else if (pos % 10 == 0) {     
                if(iz>=20)g.drawLine(10, iz, 20, iz);
            }else if (pos % 2 == 0) {      
                if(iz>=20)g.drawLine(20, iz, 20, iz);
                  }
            }
        for (int ix=0; ix < width-20; ix++) {
            if (ix % 50 == 0) {
                if(ix>=20)g.drawLine(ix, height-20, ix, height);
            }else if (ix % 10 == 0) {
                if(ix>=20)g.drawLine(ix, height-20, ix, height-10);
            }else if (ix % 2 == 0) {
                if(ix>=20)g.drawLine(ix, height-20, ix, height-20);
                  }
            }
        g.drawLine(20, height-20, width-20, height-20);
        g.drawLine(20, 20, width-20, 20);
        g.drawLine(20, 20, 20, height-20);
        g.drawLine(width-20, 20, width-20, height-20);
      }
}

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
//a##        myXMutiCurveParasJFrame -> the tcurve parameters basic frame
//a##
//a##            myXMultiCurveJFrame -> the frame include a curve panel
//a##
//a##            myXMultiCurveJPanel -> the panel to paint 
//a##
//a##        myXMultiCurveAboutDialog -> About dialog
//a##
//a############################################################################################# 
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
//a#################################
//a##
//a##  myXMultiCurveJPanel for myXMultiCurveJFrame
//a##
//a#################################
public class myXMultiCurveJPanel extends JPanel {

    private int n;
    private float[][] line;
    public String[] txtfile;
    private float maxz;
    private float minz;

    private float[] minperline, maxperline;

    private Graphics2D g2d;

    private int[] linewidth;
    private int[] linecolor;

    private BasicStroke[]  lineWidth;
    private BasicStroke  stokeRuler;

    private Color[] lineColor;

    public myXMultiCurveJPanel(int n, String[] txtfile, int[] linewidth, int[] linecolor){

        this.n = n;
        this.txtfile = txtfile;
        this.linewidth = linewidth;
        this.linecolor = linecolor;

        getlineColor();
        getlineWidth();

        minperline = new float[txtfile.length];
        maxperline = new float[txtfile.length];

        int count = readTxt(n,txtfile);
        if(count<n)System.out.println("count<n");

        this.n = count-1;

        getMinMax();

        stokeRuler = new BasicStroke(1.0f);

        repaint();
      }

    public void getlineColor(){

        lineColor = new Color[linecolor.length];

        for(int k=0;k<linecolor.length;k++){
            if(linecolor[k]==1)lineColor[k] = Color.RED;
            else if(linecolor[k]==2)lineColor[k] = Color.BLUE;
            else if(linecolor[k]==3)lineColor[k] = Color.GREEN;
            else if(linecolor[k]==4)lineColor[k] = Color.BLACK;
            else if(linecolor[k]==5)lineColor[k] = Color.GRAY;
            else if(linecolor[k]==6)lineColor[k] = Color.YELLOW;
            else if(linecolor[k]==7)lineColor[k] = Color.PINK;
            else if(linecolor[k]==8)lineColor[k] = Color.CYAN;
            else if(linecolor[k]==9)lineColor[k] = Color.MAGENTA;
            else if(linecolor[k]==10)lineColor[k] = Color.ORANGE;
            else lineColor[k] = Color.BLACK;
            }
      }
    public void getlineWidth(){

        lineWidth = new BasicStroke[linewidth.length];

        for(int k=0;k<linewidth.length;k++){
            lineWidth[k] = new BasicStroke(linewidth[k]);
            }
      }
    /* paint */
    public void paint(Graphics g) {

        super.paint(g);
        g2d = (Graphics2D) g;

        drawRuler(g2d);

        float radiox = (float)(getWidth()-40)/(float)n;
        float radioz = (float)(getHeight()-40)/(maxz-minz);


        for(int l=0;l<txtfile.length;l++)
        for(int in =0 ;in<n;in++){

            int x0 = (int)(in*radiox)+20;
            int z0 = getHeight()-20-(int)((line[l][in]-minz)*radioz);
            int x1 = (int)((in+1)*radiox)+20;
            int z1 = getHeight()-20-(int)((line[l][in+1]-minz)*radioz);

            int zz0 = getHeight()-20-(int)((-minz)*radioz);

            g2d.setColor(lineColor[l]);
            g2d.setStroke(lineWidth[l]);
            if(z0>=20&&z1>=20)
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

        maxz = minz = line[0][0];
        for(int l=0;l<txtfile.length;l++){
            minperline[l] = line[l][0];
            maxperline[l] = line[l][0];
            for(int i=0;i<n;i++){
                if(maxz<line[l][i])maxz=line[l][i];
                if(minz>line[l][i])minz=line[l][i];
                if(minperline[l]>line[l][i])minperline[l]=line[l][i];
                if(maxperline[l]<line[l][i])maxperline[l]=line[l][i];
                  }
            }
        System.out.println("max = "+maxz+", minz = "+minz);
      }
    /*read TXT*/
    public int readTxt(int n, String[] txtName){
        
        float[] tmp = new float[n];
        int l = txtName.length;
        
        line = new float[l][n];

        int[] count = new int[l] ;
        try{
            for(int i=0;i<l;i++){
            count[i] = 0;
            Scanner scanner=new Scanner(new FileInputStream(txtName[i]));
            while(scanner.hasNextDouble()&&count[i]<n){
                tmp[count[i]++] = (float)scanner.nextDouble();
                line[i][count[i]-1] = tmp[count[i]-1];
                System.out.println("val = "+line[i][count[i]-1]);
                  }}
        }catch(FileNotFoundException e){
            System.out.println("The "+txtName+" not found.");
            return 0;
            }
    
        int countmin = count[0];
        for(int m=0;m<l;m++)if(countmin>count[m])countmin = count[m];

        return countmin;
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

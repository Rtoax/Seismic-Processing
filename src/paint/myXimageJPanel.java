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



//a#################################
//a##
//a##  myXimageJPanel for myXimageJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXimageJPanel extends JPanel {


    private int nx,xbeg,nz;
    private int width,height;

    private String filename;

    private float[] Itemp;
    private float[] Image;

    private float imax, imin;

    /*small paint*/
    private int minX ;
    private int minZ ;
    private int maxX ;
    private int maxZ ;

 

    public myXimageJPanel(int nx,int xbeg, int nz,int width,int height, String filename
            ,int minX, int minZ, int maxX, int maxZ){




        this.nx = nx; 
        this.xbeg = xbeg; 
        this.nz = nz;
        this.width = width;
        this.height = height;
        this.filename = filename;
        this.minX = minX;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxZ = maxZ;


        Itemp = new float[nx*nz];
        Image = new float[nx*nz];

        //setSize(width,height);
        setPreferredSize(new Dimension(width-20,height-20));


        readFile(xbeg);
        getMaxMin();

      }

    /* get min & max */
    public void getMaxMin(){

        imax = imin = Image[0];
        for(int i=0;i<nx*nz;i++){

            if(imin>Image[i])imin=Image[i];
            if(imax<Image[i])imax=Image[i];
            }
      }

    /* read file */
    public void readFile(int nskip){
        DataInputStream fp = null;
        try{    
            if(!new File(filename).exists()){  
                System.out.println("The "+filename+" dont't exists");
                return;  
                  } 
            fp = new DataInputStream(new FileInputStream(new File(filename)));
            int i = 0;
            fp.skip(301*4*nskip);
            while(fp.available()>0&&i<nx*nz){   
                Itemp[i++] = fp.readFloat();  
                Image[i-1]=swap(Itemp[i-1]);
                  } 
        }catch(Exception e){  
            e.printStackTrace();  
            }
      }
    /* swap */
    public static float swap (float value){

        int intValue = Float.floatToRawIntBits (value);
        intValue = swap (intValue);
        return Float.intBitsToFloat (intValue);
      }
    public static int swap (int value){

        int b1 = (value >>  0) & 0xff;
        int b2 = (value >>  8) & 0xff;
        int b3 = (value >> 16) & 0xff;
        int b4 = (value >> 24) & 0xff;
        return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
      }
    /* paint */
    public void paintComponent(Graphics g) {


        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;


        drawRuler(g2d);

        Dimension size = getSize();
        Insets insets = getInsets();

        int[] rgb = new int[3];

        for (int ix = minX; ix<maxX; ix ++)
        for (int iz = minZ; iz<maxZ; iz ++){

            int i = ix*nz+iz;

            /* gray */
            rgb[0] = rgb[1] = rgb[2]  
                   = (int)( 1.0f*(255-(Image[i]-imin)*255.0f/(imax-imin)) );

            /* red blue */
            /*rgb[0] = (int)( 1.0f*(255-(Image[i]-imin)*255.0f/(imax-imin)) );
            rgb[1] = (int)( Image[i]>=0?(255-Image[i]/imax*255.0f)
                    :(255-Image[i]/imin*255.0f) );
            rgb[2] = (int)( 1.0f*(255-(Image[i]-imin)*255.0f/(imax-imin)) );  */

            int R = (int)( rgb[0] );
            int G = (int)( rgb[1] );
            int B = (int)( rgb[2] );

            g2d.setColor(new Color(R, G, B) );





            float radiox = (float)(getWidth()-40)/(float)(maxX-minX);
            float radioz = (float)(getHeight()-40)/(float)(maxZ-minZ);

            int drawx = (int)((float)(ix-minX)*radiox);
            int drawz = (int)((float)(iz-minZ)*radioz);



            for(int j=0;j<=(int)(radiox);j++)
                for(int k=0;k<=(int)(radioz);k++)
                    g2d.drawLine(drawx+j+20, drawz+k+20 
                           , drawx+j+20, drawz+k+20);


            }
        //repaint();


      }
    /* ruler */
    public void drawRuler(Graphics2D g) {

        g.setColor(Color.blue);

        int height = getSize().height;
        int width = getSize().width;

        for (int iz=height; iz > 0; iz--) {
            int pos = (iz-height);

            if (pos % 50 == 0) {      
                if(iz>=20&&iz<height-20)g.drawLine(0, iz, 20, iz);
            }else if (pos % 10 == 0) {     
                if(iz>=20&&iz<height-20)g.drawLine(10, iz, 20, iz);
            }else if (pos % 2 == 0) {      
                if(iz>=20&&iz<height-20)g.drawLine(20, iz, 20, iz);
                  }
            }
        for (int ix=0; ix < width; ix++) {
            if (ix % 50 == 0) {
                if(ix>=20&&ix<width-20)g.drawLine(ix, 0, ix, 20);
            }else if (ix % 10 == 0) {
                if(ix>=20&&ix<width-20)g.drawLine(ix, 10, ix, 20);
            }else if (ix % 2 == 0) {
                if(ix>=20&&ix<width-20)g.drawLine(ix, 20, ix, 20);
                  }
            }
        g.drawLine(20, 19, width-20, 19);
        g.drawLine(20, height-19, width-20, height-19);
        g.drawLine(19, 20, 19, height-20);
        g.drawLine(width-19, 20, width-19, height-20);
      }
}

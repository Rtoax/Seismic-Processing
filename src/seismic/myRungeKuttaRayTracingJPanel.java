package src.seismic;


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

//a################################################################
//a##
//a##  myRungeKuttaRayTracingJPanel to myRungeKuttaRayTracingJFrame
//a##
//a################################################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myRungeKuttaRayTracingJPanel extends JPanel{

    private float[][][] raypath;

    private float[] v0;
    private float imax, imin;

    private int nx,nz,nt,nray;
    private float dx,dz;

    private int raypathcolor;
    private int raypathwidth;

    private int flag_selextfile = 0;

    private boolean flag_SelectFile;

    public myRungeKuttaRayTracingJPanel(String FNv,String FNd,String FNe,String FNt,
                     String FNp,String FNl,int nt, int nx, int nz,
                     int na, int nsx, float dx, float dz, float dt,
                     float fa, float da, float fsx,float dsx,float sz,
                     int raypathcolor,int raypathwidth,
                     boolean flag_SelectFile, float constv,float conste,
                     float constd){

        this.nx = nx;
        this.nz = nz;
        this.dx = dx;
        this.dz = dz;
        this.nt = nt;
        this.nray = nsx*na;
        this.raypathcolor = raypathcolor;
        this.raypathwidth = raypathwidth;
        this.flag_SelectFile = flag_SelectFile;


        if(flag_SelectFile)flag_selextfile = 1;
        else flag_selextfile = 0;


        /*vel, delta, epsilon, time, raypath, length*/
        String[] file = {FNv,FNd,FNe,FNt,FNp,FNl};
        /*nt ,nx, nz, na, nsx*/
        int[] parasint = {nt, nx, nz, na, nsx, flag_selextfile};
        /*dx, dz, dt, fa, da, fsx, dsx, sz*/
        float[] parasfloat = {dx, dz, dt, fa, da, fsx, dsx, sz, constv, conste,constd};

        raypath = RungeKutta.makeRay(file, parasint,parasfloat);

        v0 = new float[nx*nz];
        if(flag_SelectFile)readVelFile(FNv);
        getMaxMin();
      }


    public void paintComponent(Graphics g) {


        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        Dimension size = getSize();
        Insets insets = getInsets();

        drawRuler(g2d);

        if(flag_SelectFile){
            int[] rgb = new int[3];
            float radiox = (float)(getWidth()-40)/(float)(nx-0);
            float radioz = (float)(getHeight()-40)/(float)(nz-0);
            for (int ix = 0; ix<nx; ix ++)
            for (int iz = 0; iz<nz; iz ++){

                int i = ix*nz+iz;
                /* gray */
                rgb[0] = rgb[1] = rgb[2]
                       = (int)( 1.0f*(255-(v0[i]-imin)*255.0f/(imax-imin)) );

                int R = (int)( rgb[0] );
                int G = (int)( rgb[1] );
                int B = (int)( rgb[2] );

                g2d.setColor(new Color(R, G, B) );

                int drawx = (int)((float)(ix-0)*radiox+20);
                int drawz = (int)((float)(iz-0)*radioz+20);

                for(int j=0;j<=(int)(radiox);j++)
                    for(int k=0;k<=(int)(radioz);k++)
                        g2d.drawLine(drawx+j, drawz+k, drawx+j, drawz+k);
                 }
            }
        float radioX = (float)(getWidth()-40)/(float)(nx*dx);
        float radioZ = (float)(getHeight()-40)/(float)(nz*dz);
        g2d.setColor(getRaypathColor(raypathcolor));
        g2d.setStroke(getRaypathWidth(raypathwidth));
        for(int iray=0;iray<nray;iray++){
               for(int i=0;i<nt-1;i++){

                int x0 = (int)(radioX*raypath[iray][0][i]+20);
                int x1 = (int)(radioX*raypath[iray][0][i+1]+20);
                int z0 = (int)(radioZ*raypath[iray][1][i]+20);
                int z1 = (int)(radioZ*raypath[iray][1][i+1]+20);
                if(Math.abs(x1-x0)<8f&&Math.abs(z1-z0)<8f)
                g2d.drawLine(x0,z0,x1,z1);
                }
            }
      }
    /* get min & max */
    public void getMaxMin(){

        imax = imin = v0[0];
        for(int i=0;i<nx*nz;i++){

            if(imin>v0[i])imin=v0[i];
            if(imax<v0[i])imax=v0[i];
            }
      }
    /* get raypath line width */
    public BasicStroke getRaypathWidth(int dim){

        BasicStroke tmp = new BasicStroke(dim);
        return tmp;
      }
    /* get raypath line color */
    public Color getRaypathColor(int dim){

        if(dim==1)return Color.RED;
        else if(dim==2)return Color.BLUE;
        else if(dim==3)return Color.GREEN;
        else if(dim==4)return Color.BLACK;
        else if(dim==5)return Color.GRAY;
        else if(dim==6)return Color.YELLOW;
        else if(dim==7)return Color.PINK;
        else if(dim==8)return Color.CYAN;
        else if(dim==9)return Color.MAGENTA;
        else if(dim==10)return Color.ORANGE;
        else return Color.BLACK;
      }
    /* read file */
    public void readVelFile(String filename){
            DataInputStream fp = null;
            try{    
                if(!new File(filename).exists()){  
                    System.out.println("The "+filename+" dont't exists");
                    return;  
                      } 
                fp = new DataInputStream(new FileInputStream(new File(filename)));
                int i = 0;
                while(fp.available()>0&&i<nx*nz){   
                    v0[i++] = swap(fp.readFloat());  
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

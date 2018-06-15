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


//a#############################################################################################
//a##
//a##        myLaganRayTracingParasJFrame -> the timage parameters basic frame
//a##
//a##            myLaganRayTracingJFrame -> the frame include a image panel
//a##
//a##            myLaganRayTracingJPanel -> the panel to paint
//a##
//a##        myLaganRayTracingAboutDialog -> About dialog
//a##
//a############################################################################################# 
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
//a########################################################
//a##
//a##   Lagan Raytracing
//a##
//a########################################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class Lagan{

    private float pi = 3.141592653f;
    private int sx, sz, nx, nz, ngrid;
    private float dx,dz,s;
    private float[] v0;
    private int countbndr;

    public Lagan(int sx, int sz, int nx, int nz, float dx, float dz, float[]v0, float s){
        this.sx = sx;
        this.sz = sz;
        this.nx = nx;
        this.nz = nz;
        this.dx = dx;
        this.dz = dz;
        this.v0 = v0;
        this.s = s;
        ngrid = nx * nz;
      }
    public float[][] makeRay(float angle)throws java.io.IOException{

        float p_x,p_z,n_x,n_z,l_x,l_z;
        float p_xend,p_zend;
        float n_xnew,n_znew;
        int ip_lux,ip_ldx,ip_rux,ip_rdx;
        int ip_luz,ip_ldz,ip_ruz,ip_rdz;
        float time,v;

        angle = angle*pi/180.0f;

        /** get the raypath length -> countbndr **/
        p_x = sx*dx;
        p_z = sz*dz;
        n_x = (float)Math.sin(angle);
        n_z = (float)Math.cos(angle);
        countbndr = 0;
        do{
            /* cal_gridpoint */
            ip_lux = (int)(p_x/dx);
            ip_luz = (int)(p_z/dz);
            ip_ldx = ip_lux;
            ip_ldz = ip_luz+1;
            ip_rux = ip_lux+1;
            ip_ruz = ip_luz;
            ip_rdx = ip_lux+1;
            ip_rdz = ip_luz+1;

            /* cal_gridvel */
            float l_x1 = (v0[ip_rux*nz + ip_ruz]-v0[ip_lux*nz + ip_luz])/dx;
            float l_x2 = (v0[ip_rdx*nz + ip_rdz]-v0[ip_ldx*nz + ip_ldz])/dx;
            l_x = ( l_x1+l_x2 ) / 2.0f;
            float l_z1 = (v0[ip_ldx*nz + ip_ldz]-v0[ip_lux*nz + ip_luz])/dz;
            float l_z2 = (v0[ip_rdx*nz + ip_rdz]-v0[ip_rux*nz + ip_ruz])/dz;
            l_z = (l_z1+l_z2)/2.0f;
            v = v0[ip_rux*nz + ip_ruz]
                +v0[ip_lux*nz + ip_luz]
                +v0[ip_rdx*nz + ip_rdz]
                +v0[ip_ldx*nz + ip_ldz];
            v = v/4.0f;
            if(v==0.0)break;

            /* cal_path */
            float dotmult_ln = l_x*n_x+l_z*n_z;
            float dotmult_ll = l_x*l_x+l_z*l_z;
            p_xend = p_x+n_x*s*(1f+dotmult_ln*0.5f*s/v)-0.5f*l_x*s*s/v
                    -n_x*s*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(6.0f*v*v);
            p_zend = p_z+n_z*s*(1f+dotmult_ln*0.5f*s/v)-0.5f*l_z*s*s/v
                    -n_z*s*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(6.0f*v*v);
            n_xnew = n_x*(1f+dotmult_ln*s/v)-l_x*s/v
                    -n_x*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(2.0f*v*v);
            n_znew = n_z*(1f+dotmult_ln*s/v)-l_z*s/v
                    -n_z*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(2.0f*v*v);

            /* buffer */
            p_x = p_xend;
            p_z = p_zend;
            n_x = n_xnew;
            n_z = n_znew;
            
            countbndr++;
        }while((p_xend>=0.0f)&&(p_xend<((nx-1f)*dx))
             &&(p_zend>=0.0f)&&(p_zend<(nz-1f)*dz));

        /** make raytracing -> save to raypath[2][..] **/
        p_x = sx*dx;
        p_z = sz*dz;
        n_x = (float)Math.sin(angle);
        n_z = (float)Math.cos(angle);
        time = 0.0f;
        /* initial raypath */
        float [][] raypath = new float[2][countbndr];

        for(int i=0;i<countbndr;i++){

            /* cal_gridpoint */
            ip_lux = (int)(p_x/dx);
            ip_luz = (int)(p_z/dz);
            ip_ldx = ip_lux;
            ip_ldz = ip_luz+1;
            ip_rux = ip_lux+1;
            ip_ruz = ip_luz;
            ip_rdx = ip_lux+1;
            ip_rdz = ip_luz+1;

            /* cal_gridvel */
            float l_x1 = (v0[ip_rux*nz + ip_ruz]-v0[ip_lux*nz + ip_luz])/dx;
            float l_x2 = (v0[ip_rdx*nz + ip_rdz]-v0[ip_ldx*nz + ip_ldz])/dx;
            l_x = ( l_x1+l_x2 ) / 2.0f;
            float l_z1 = (v0[ip_ldx*nz + ip_ldz]-v0[ip_lux*nz + ip_luz])/dz;
            float l_z2 = (v0[ip_rdx*nz + ip_rdz]-v0[ip_rux*nz + ip_ruz])/dz;
            l_z = (l_z1+l_z2)/2.0f;
            v = v0[ip_rux*nz + ip_ruz]
                +v0[ip_lux*nz + ip_luz]
                +v0[ip_rdx*nz + ip_rdz]
                +v0[ip_ldx*nz + ip_ldz];
            v = v/4.0f;
            /* cal_path */
            float dotmult_ln = l_x*n_x+l_z*n_z;
            float dotmult_ll = l_x*l_x+l_z*l_z;
            p_xend = p_x+n_x*s*(1f+dotmult_ln*0.5f*s/v)-0.5f*l_x*s*s/v
                    -n_x*s*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(6.0f*v*v);
            p_zend = p_z+n_z*s*(1f+dotmult_ln*0.5f*s/v)-0.5f*l_z*s*s/v
                    -n_z*s*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(6.0f*v*v);
            n_xnew = n_x*(1f+dotmult_ln*s/v)-l_x*s/v
                    -n_x*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(2.0f*v*v);
            n_znew = n_z*(1f+dotmult_ln*s/v)-l_z*s/v
                    -n_z*s*s*(dotmult_ll-dotmult_ln*dotmult_ln)/(2.0f*v*v);
            time += s/v*(1f+s*s*(dotmult_ll+dotmult_ln*dotmult_ln)
                    /(6f*v*v)-dotmult_ln*s/(2f*v));
            /* buffer */
            p_x = p_xend;
            p_z = p_zend;
            n_x = n_xnew;
            n_z = n_znew;
            raypath[0][i] = p_x;
            raypath[1][i] = p_z;
            }
        return raypath;
      }
}



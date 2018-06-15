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



//a####################################################################################################
//a##
//a##    myXraypathParasJFrame -> basic frame of Xraypath
//a##        innerClass: myXraypathJFrame -> frame for paint panel
//a##               innerClass: myXraypathJPanel -> panel for paint
//a##
//a##    myXraypathDialog -> about dialog
//a##
//a##~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//a##
//a## Ps: ___________________
//a##     |File    _| |*|
//a##     |_________________|
//a##     |         |
//a##     |     Paras       |
//a##     |         ~~~~|
//a##     |         |OK |
//a##     | Click OK to Go  |
//a##     ___________________
//a##
//a##~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//a##
//a##  /********************************** look at the global structure ******************************/
//a##  /********************************** look at the global structure ******************************/
//a##
//a##   class myXraypathParasJFrame{
//a##
//a##       private myXraypathJFrame  myxraypathJFrame;
//a##
//a##       private int txtLength, nray;
//a##
//a##       myXraypathParasJFrame(){
//a##
//a##           OK.Listener{
//a##
//a##               txtLength = getLength0();
//a##
//a##               if(Paras isn't accurate){
//a##
//a##                   JOptionPane;
//a##
//a##               }else txtLength = getLength();
//a##           }
//a##
//a##       }
//a##       int getLength0(){  
//a##
//a##           return textfile length;//(x,z)'s number, include -999999.9
//a##       }
//a##
//a##       int getLength(){
//a##
//a##           get "nray";
//a##
//a##           if(nray == 0){
//a##               JOptionPane;
//a##           }else
//a##               myxraypathJFrame = new myXraypathJFrame();
//a##       }
//a##       class myXraypathJFrame{
//a##           
//a##           class myXraypathJPanel{
//a##
//a##           }
//a##       }
//a##   }
//a##
//a##
//a####################################################################################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
//a#################################
//a##
//a##  myXraypathParasJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXraypathParasJFrame extends JFrame{

    private Toolkit toolkit;

    private JButton buttonOK;

    private myXraypathJFrame  myxraypathJFrame;

    private String txtfile;
    private String datfile;
    private float linewidth;
    private int linecolor;
    private int nx,nz;
    private float dx,dz;


    private int nray = 0;

    private int txtLength;

    private JLabel tmplabel;

    public myXraypathParasJFrame(){

        setTitle("ToaRaypathing");
        setSize(600, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setMaximumSize(new Dimension(350,300));

        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/4 - getWidth()/2, (size.height -getHeight())/2);

        /* menu: save, close */
        JMenuBar menubar = new JMenuBar();
        JMenu menu=new JMenu("File");
        menu.setFont(new Font("Georgia", Font.BOLD, 20));
        JMenuItem about=new JMenuItem("About");
        about.setFont(new Font("Georgia", Font.BOLD, 15));
        menu.add(about);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new myXraypathDialog();
                  }
            });
        JMenuItem close=new JMenuItem("Close");
        close.setFont(new Font("Georgia", Font.BOLD, 15));
        menu.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });
        menubar.add(menu);
        setJMenuBar(menubar);


        ImageIcon folder = new ImageIcon("picture/ButtonImg/folder32.png");

        /* include 2 toolbars */
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        /* toolbar1 */
        final JToolBar toolbar1 = new JToolBar();

        /*txtfile*/
        tmplabel = new JLabel("raypath.txt:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar1.add(tmplabel);
        final JTextField JFtxtfile = new JTextField(20);
        JFtxtfile.setFont(new Font("Georgia", Font.BOLD, 15));
        JFtxtfile.setText("/home/");

        toolbar1.add(JFtxtfile);

        JButton buttontxtfile = new JButton(folder);
        buttontxtfile.setAlignmentX(1f);
        buttontxtfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filtertxt = new FileNameExtensionFilter("*.txt", "txt");
                FileFilter filterpar = new FileNameExtensionFilter("*.par", "par");
                fileopen.addChoosableFileFilter(filtertxt);
                fileopen.addChoosableFileFilter(filterpar);

                int ret = fileopen.showDialog(panel, "Select");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    JFtxtfile.setText(file.toString());
                    txtfile = file.toString();
                    System.out.println("raypath.txt name : "+txtfile);
                    }
                  }
            });
        toolbar1.add(buttontxtfile);
        panel.add(toolbar1);

        /* toolbar2 */
        final JToolBar toolbar2 = new JToolBar();

        /*datfile*/
        tmplabel = new JLabel(" binary.dat:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar2.add(tmplabel);
        final JTextField JFdatfile = new JTextField(20);
        JFdatfile.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdatfile.setText("/home/");

        toolbar2.add(JFdatfile);

        JButton buttondatfile = new JButton(folder);
        buttondatfile.setAlignmentX(1f);
        buttondatfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filterdat = new FileNameExtensionFilter("*.dat", "dat");
                FileFilter filterbin = new FileNameExtensionFilter("*.bin", "bin");
                fileopen.addChoosableFileFilter(filterdat);
                fileopen.addChoosableFileFilter(filterbin);

                int ret = fileopen.showDialog(panel, "Select");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    JFdatfile.setText(file.toString());
                    datfile = file.toString();
                    System.out.println("binary.dat name : "+datfile);
                    }
                  }
            });
        toolbar2.add(buttondatfile);
        panel.add(toolbar2);


        /* toolbar3 */
        final JToolBar toolbar3 = new JToolBar();

        /* linewidth */
        tmplabel = new JLabel("LineWidth");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar3.add(tmplabel);

        String[] LW = {" 1 "," 2 "," 3 "," 4 "};//linewidth
        JComboBox comboxLW = new JComboBox<String>(LW);
        comboxLW.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLW.setSelectedIndex(0);
        linewidth = 1;
        comboxLW.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    linewidth = (float)((index+1));
                    System.out.println("linewidth = "+(index+1));
                    }
                  }
            });
        toolbar3.add(comboxLW);


        /* linecolor */
        tmplabel = new JLabel("  LineColor");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar3.add(tmplabel);

        String[] LC = {" red "," blue "," green "," black "," white "};
        JComboBox comboxLC = new JComboBox<String>(LC);
        comboxLC.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLC.setSelectedIndex(0);
        linecolor = 1;
        comboxLC.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    linecolor = (index+1);
                    System.out.println("linecolor = "+(index+1));
                    }
                  }
            });
        toolbar3.add(comboxLC);
        panel.add(toolbar3);


        /* toolbar4 */
        final JToolBar toolbar4 = new JToolBar();

        /*nx*/
        tmplabel = new JLabel(" nx:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar4.add(tmplabel);
        final JTextField JFnx = new JTextField(5);
        JFnx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnx.setText("601");
        JFnx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnx.getText()))
                    JOptionPane.showMessageDialog(JFnx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        toolbar4.add(JFnx);

        /*nz*/
        tmplabel = new JLabel(" nz:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar4.add(tmplabel);
        final JTextField JFnz = new JTextField(5);
        JFnz.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnz.setText("301");
        JFnz.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnz.getText()))
                    JOptionPane.showMessageDialog(JFnz, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        toolbar4.add(JFnz);

        /*dx*/
        tmplabel = new JLabel(" dx[m]:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar4.add(tmplabel);
        final JTextField JFdx = new JTextField(5);
        JFdx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdx.setText("5");
        JFdx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFdx.getText()))
                    JOptionPane.showMessageDialog(JFdx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        toolbar4.add(JFdx);

        /*dz*/
        tmplabel = new JLabel(" dz[m]:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar4.add(tmplabel);
        final JTextField JFdz = new JTextField(5);
        JFdz.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdz.setText("5");
        JFdz.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFdz.getText()))
                    JOptionPane.showMessageDialog(JFdz, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        toolbar4.add(JFdz);


        /*OK button*/
        toolbar4.add(new JLabel("   "));
        ImageIcon iconOK = new ImageIcon("picture/ButtonImg/ok32.png");
        buttonOK = new JButton(iconOK);
        buttonOK.setAlignmentX(1f);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                nx = Integer.parseInt( JFnx.getText() );
                nz = Integer.parseInt( JFnz.getText() );
                dx = (float)Integer.parseInt( JFdx.getText() );
                dz = (float)Integer.parseInt( JFdz.getText() );

                txtfile = JFtxtfile.getText();
                datfile = JFdatfile.getText();

                txtLength = getLength0(txtfile);

                if(txtfile.equals("/home/")||datfile.equals("/home/"))
                    JOptionPane.showMessageDialog(JFdz, 
                           "Please select file [1.txt or 2.dat]!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                else    txtLength = getLength(txtfile);

                  }
            });
        toolbar4.add(buttonOK);


        panel.add(toolbar4);


        add(panel, BorderLayout.NORTH);


        setVisible(true);
      }
    public static boolean isNumeric(String str){
    /*Copyright: http://javapub.iteye.com/blog/666544*/
        for (int i = 0; i < str.length(); i++){
            if (!Character.isDigit(str.charAt(i))){
                return false;
                  }
              }
        return true;
      }
    /*getLength0 -> for every time you click OK button get length of txtfile*/
    public int getLength0(String txtName){
                
        nray = 0;
        int len = 0 ;
        float tmp;
        try{
            Scanner scanner=new Scanner(new FileInputStream(txtName));
            while(scanner.hasNextDouble()){
                tmp = (float)scanner.nextDouble();
                if(tmp<-999.9)nray++;
                len++;
                  }
        }catch(FileNotFoundException e){
            System.out.println("The "+txtName+" not found.");return 0;
            }

        nray/=2;

        return len/2;
      }
    /*getLength*/
    public int getLength(String txtName){
             
        nray = 0;       
        int len = 0 ;
        float tmp;
        try{
            Scanner scanner=new Scanner(new FileInputStream(txtName));
            while(scanner.hasNextDouble()){
                tmp = (float)scanner.nextDouble();
                if(tmp<-999.9)nray++;
                len++;
                  }
        }catch(FileNotFoundException e){
            System.out.println("The "+txtName+" not found.");return 0;
            }

        nray/=2;

        if(nray==0){
            JOptionPane.showMessageDialog(buttonOK,
                             "Rays seperate use -999999.9  -999999.9",
                             "Error", JOptionPane.ERROR_MESSAGE);
        }else
            myxraypathJFrame = new myXraypathJFrame
                           (txtfile,datfile,linewidth,linecolor,nx,nz,dx,dz);
        return len/2;
      }

    //a#################################
    //a##
    //a##  myXraypathJFrame
    //a##
    //a#################################
    class myXraypathJFrame extends JFrame{

        private Toolkit toolkit;

        private myXraypathJPanel myxraypathJPanel;

        private File FileSave;


        myXraypathJFrame(String txtfile, String datfile,float linewidth,int linecolor
                    , int nx,int nz,float dx,float dz){


            setTitle(txtfile);
            setSize(500, 400);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            toolkit = getToolkit();
            Dimension size = toolkit.getScreenSize();
            setLocation((size.width/2 - getWidth())/2, (size.height - getWidth())/2);
               
            /* menu: save, close */
            JMenuBar JMenuBarSave;
            final JMenu JMenuSave;
            JMenuItem JMenuItemSaveClose,JMenuItemSaveSave;

            JMenuBarSave=new JMenuBar();
            JMenuSave=new JMenu("File");
            JMenuSave.setFont(new Font("Georgia", Font.BOLD, 20));
            JMenuItemSaveSave=new JMenuItem("Save as Image");
            JMenuItemSaveSave.setFont(new Font("Georgia", Font.BOLD, 15));
            JMenuItemSaveClose=new JMenuItem("Close");
            JMenuItemSaveClose.setFont(new Font("Georgia", Font.BOLD, 15));

            JMenuSave.add(JMenuItemSaveSave);
            JMenuSave.add(JMenuItemSaveClose);
            JMenuBarSave.add(JMenuSave);

            setJMenuBar(JMenuBarSave);


            /* xraypath show panel in xraypathJFrame */
            myxraypathJPanel = new myXraypathJPanel
                          (txtfile, datfile,linewidth,linecolor, nx,nz,dx,dz);


            JMenuItemSaveClose.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                      }
                  });
            JMenuItemSaveSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    saveJPanel.saveImage(myxraypathJPanel, JMenuSave);
                      }
                  });


            add(myxraypathJPanel);


            setVisible(true);
            }
        //a#################################
        //a##
        //a##  myXraypathJPanel for myXraypathJFrame
        //a##
        //a#################################
        class myXraypathJPanel extends JPanel {



            public String txtfile;
            public String datfile;

            private Graphics2D g2d;

            private float[] X;
            private float[] Z;

            private float minX,minZ,maxX,maxZ;

            private int nx,nz;
            private float dx,dz;

            private float[] Image;

            private float minI,maxI;

            private BasicStroke  stokeLine;
            private Color lineColor;
            private float linewidth;

            myXraypathJPanel(String txtfile, String datfile
                    ,float linewidth,int linecolor
                    ,int nx,int nz,float dx,float dz){

                this.txtfile = txtfile;
                this.datfile = datfile;

                this.linewidth = linewidth;

                this.nx = nx;
                this.nz = nz;
                this.dx = dx;
                this.dz = dz;

                if(linecolor==1)lineColor = Color.RED;
                if(linecolor==2)lineColor = Color.BLUE;
                if(linecolor==3)lineColor = Color.GREEN;
                if(linecolor==4)lineColor = Color.BLACK;
                if(linecolor==5)lineColor = Color.WHITE;


                Image = new float[nx*nz];
                readFile();



                readTxt(txtfile);
                getMinMax();


                System.out.println("len = "+txtLength+", nray = "+nray
                        +", X:"+minX+"--"+maxX+",  Z: "+minZ+"--"+maxZ
                        +",  I:"+minI+"--"+maxI );


                stokeLine = new BasicStroke(linewidth);

                repaint();
                  }
            /* paint */
            public void paint(Graphics g) {

                super.paint(g);
                g2d = (Graphics2D) g;

                drawRuler(g2d);

                int iray = 0;

                int[] rgb = new int[3];

                float radioIx = (float)(getWidth()-40)/(float)(nx*dx);
                float radioIz = (float)(getHeight()-40)/(float)(nz*dz);

                for (int ix = 0; ix<nx; ix ++)
                for (int iz = 0; iz<nz; iz ++){

                    int i = ix*nz+iz;

                    /* gray */
                    rgb[0] = rgb[1] = rgb[2] 
                           = (int)( 1.0f*(255-(Image[i]-minI)*255.0f
                                   /(maxI-minI)/1f) );

                    int R = (int)( rgb[0] );
                    int G = (int)( rgb[1] );
                    int B = (int)( rgb[2] );

                    g2d.setColor(new Color(R, G, B) );

                    int drawx = (int)((float)(ix*dx)*radioIx+20);
                    int drawz = (int)((float)(iz*dz)*radioIz+20);

                    for(int j=0;j<=(int)(radioIx*dx);j++)
                        for(int k=0;k<=(int)(radioIz*dz);k++)
                            g2d.drawLine(drawx+j, drawz+k,
                                     drawx+j, drawz+k);


                    }

                float radiox = (float)(getWidth()-40)/(nx*dx);
                float radioz = (float)(getHeight()-40)/(nz*dz);

                for(int i =1 ;i < txtLength-1; i++){


                    g2d.setColor(lineColor);
                    g2d.setStroke(stokeLine);



                    if(X[i+1]>=0.0f&&Z[i+1]>=0.0f){
                        int x0 = (int)(radiox*X[i]+20);
                        int x1 = (int)(radiox*X[i+1]+20);
                        int z0 = (int)(radioz*Z[i]+20);
                        int z1 = (int)(radioz*Z[i+1]+20);
                        g2d.drawLine(x0, z0, x1, z1);
                    }else i+=2;

                      }

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
            /* read file */
            public void readFile(){
                DataInputStream fp = null;
                try{    
                    if(!new File(datfile).exists()){  
                        System.out.println("The "+datfile+" dont't exists");
                        return;  
                          } 
                    fp = new DataInputStream(
                           new FileInputStream(
                              new File(datfile)));
                    int i = 0;
                    while(fp.available()>0&&i<nx*nz){   
                        Image[i++] = fp.readFloat();  
                        Image[i-1]=swap(Image[i-1]);
                          } 
                }catch(Exception e){  
                    e.printStackTrace();  
                      }
                  }

            public void getMinMax(){

                minX = maxX = X[0];
                minZ = maxZ = Z[0];

                for(int i=0;i<txtLength;i++){
                    if(X[i]>=0.0f){
                        if(minX>X[i])minX=X[i];
                        if(maxX<X[i])maxX=X[i];
                          }
                    if(Z[i]>=0.0f){
                        if(minZ>Z[i])minZ=Z[i];
                        if(maxZ<Z[i])maxZ=Z[i];
                          }
                    }
                maxI = minI = Image[0];
                for(int i=0;i<nx*nz;i++){

                    if(minI>Image[i])minI=Image[i];
                    if(maxI<Image[i])maxI=Image[i];
                    }
                  }

            /*read TXT*/
            public void readTxt(String txtName){
                
                X = new float[txtLength];
                Z = new float[txtLength];

                try{
                    Scanner scanner=new Scanner(new FileInputStream(txtName));
                    for(int i=0;i<txtLength;i++){
                        X[i] = (float)scanner.nextDouble();
                        Z[i] = (float)scanner.nextDouble();

                          }
                }catch(FileNotFoundException e){
                    System.out.println("The "+txtName+" not found.");return ;
                    }
                  }
            /* swap */
            public float swap (float value){

                int intValue = Float.floatToRawIntBits (value);
                intValue = swap (intValue);
                return Float.intBitsToFloat (intValue);
                  }
            public int swap (int value){

                int b1 = (value >>  0) & 0xff;
                int b2 = (value >>  8) & 0xff;
                int b3 = (value >> 16) & 0xff;
                int b4 = (value >> 24) & 0xff;
                return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
                }
            }

      }
}


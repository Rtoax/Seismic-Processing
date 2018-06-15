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



//a########################################################
//a##
//a##  myLaganRayTracingParasJFrame
//a##
//a########################################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myLaganRayTracingParasJFrame extends JFrame{

    private Toolkit toolkit;
    private boolean flag_SelectVelFile = false;


    private int nx,nz,sx,sz,nray,raypathcolor,raypathwidth;
    private float dx,dz,s,fangle,dangle;
    private String FNvel,raypathtext;

    private float constvel;

    private JLabel tmplabel;

    private DesignGridLayout layout1,layout2,layout3;

    private JSeparator js1 = new JSeparator();
    private JSeparator js2 = new JSeparator();

    public myLaganRayTracingParasJFrame(){

        setTitle("Toa ISO Lagan RayTracing. Copyright@ Rong Tao");
        setSize(700, 460);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width/2 - getWidth())/2, (size.height - getWidth())/2);

        /* menu: About, close */
        JMenuBar menubar = new JMenuBar();
        JMenu menu=new JMenu("File");
        menu.setFont(new Font("Georgia", Font.BOLD, 20));
        JMenuItem about=new JMenuItem("About");
        about.setFont(new Font("Georgia", Font.BOLD, 15));
        menu.add(about);
        about.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new myLaganRayTracingAboutDialog();
                          }
                    });
                  }
            });
        JMenuItem close =new JMenuItem("Close");
        close.setFont(new Font("Georgia", Font.BOLD, 15));
        menu.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });
        menubar.add(menu);
        setJMenuBar(menubar);




        /* include  toolbars */
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));


        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        layout1 = new DesignGridLayout(panel1);
        layout2 = new DesignGridLayout(panel2);
        layout3 = new DesignGridLayout(panel3);

        /* toolbar0 for velocity*/
        final JToolBar toolbar0 = new JToolBar();

        JCheckBox checkboxSelectFile = new JCheckBox("Select File[Y/N]", false);
        checkboxSelectFile.setFocusable(false);
        checkboxSelectFile.setFont(new Font("Georgia", Font.BOLD, 15));

        final JLabel selectYN = new JLabel(" > NO");
        selectYN.setFont(new Font("Georgia", Font.BOLD, 15));


        toolbar0.add(checkboxSelectFile);
        toolbar0.add(selectYN);

        ImageIcon folder = new ImageIcon("picture/ButtonImg/folder32.png");

        /* toolbar01 for FNvel*/
        final JToolBar toolbar01 = new JToolBar();
        tmplabel = new JLabel("Velocity:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar01.add(tmplabel);
        final JTextField JFselectvelocity = new JTextField(20);
        JFselectvelocity.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectvelocity.setText("/home/");
        FNvel = JFselectvelocity.getText();
        toolbar01.add(JFselectvelocity);

        final JButton buttonvelocity = new JButton(folder);
        buttonvelocity.setAlignmentX(1f);
        buttonvelocity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filtertxt = new FileNameExtensionFilter("*.dat", "dat");
                FileFilter filterpar = new FileNameExtensionFilter("*.bin", "bin");
                fileopen.addChoosableFileFilter(filtertxt);
                fileopen.addChoosableFileFilter(filterpar);

                int ret = fileopen.showDialog(panel, "Select");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    JFselectvelocity.setText(file.toString());
                    FNvel = file.toString();
                    System.out.println("Velocity file name : "+FNvel);
                    }
                  }
            });
        toolbar01.add(buttonvelocity);

        JFselectvelocity.setEnabled(false);
        buttonvelocity.setEnabled(false);


        /*constant velocity*/

        final JTextField JFconstvel = new JTextField(5);
        JFconstvel.setFont(new Font("Georgia", Font.BOLD, 15));
        JFconstvel.setText("2000");
        JFconstvel.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFconstvel.getText()))
                    JOptionPane.showMessageDialog(JFconstvel, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        checkboxSelectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                flag_SelectVelFile = !flag_SelectVelFile;

                if (flag_SelectVelFile) {
                    selectYN.setText(">YES ");
                } else {
                    selectYN.setText(">NO  ");
                    }
                JFselectvelocity.setEnabled(flag_SelectVelFile);
                buttonvelocity.setEnabled(flag_SelectVelFile);

                JFconstvel.setEnabled(!flag_SelectVelFile);
                  }
            });



        /*nx*/
        JLabel labelnx = new JLabel(" nx:");
        labelnx.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnx = new JTextField(5);
        JFnx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnx.setText("200");
        final JTextField JFsx = new JTextField(5);
        JFsx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFsx.setText("100");
        JFnx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnx.getText()))
                    JOptionPane.showMessageDialog(JFnx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                else JFsx.setText( Integer.toString(
                           Integer.parseInt( JFnx.getText() )/2) );
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {  
                JFsx.setText( Integer.toString(
                          Integer.parseInt( JFnx.getText() )/2) );
                }   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*nz*/
        JLabel labelnz = new JLabel(" nz:");
        labelnz.setFont(new Font("Georgia", Font.BOLD, 15));
        final JTextField JFnz = new JTextField(5);
        JFnz.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnz.setText("200");
        final JTextField JFsz = new JTextField(5);
        JFsz.setFont(new Font("Georgia", Font.BOLD, 15));
        JFsz.setText("100");
        JFnz.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnz.getText()))
                    JOptionPane.showMessageDialog(JFnz, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                else JFsz.setText( Integer.toString(
                           Integer.parseInt( JFnz.getText() )/2) );
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {  
                JFsz.setText( Integer.toString(
                          Integer.parseInt( JFnz.getText() )/2) );
                }   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*dx*/
        JLabel labeldx = new JLabel(" dx[m]:");
        labeldx.setFont(new Font("Georgia", Font.BOLD, 15));

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

        /*dz*/
        JLabel labeldz = new JLabel(" dz[m]:");
        labeldz.setFont(new Font("Georgia", Font.BOLD, 15));

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

        /*sx*/
        JLabel labelsx = new JLabel(" sx:");
        labelsx.setFont(new Font("Georgia", Font.BOLD, 15));

        JFsx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFsx.getText())){
                    JOptionPane.showMessageDialog(JFsx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                    return ;
                    }
                if(Integer.parseInt( JFnx.getText() )-4
                  <Integer.parseInt( JFsx.getText() ))
                    JOptionPane.showMessageDialog(JFsx, 
                           "You must set: sx <= nx-4",
                           "Error", JOptionPane.ERROR_MESSAGE);

                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*sz*/
        JLabel labelsz = new JLabel(" sz:");
        labelsz.setFont(new Font("Georgia", Font.BOLD, 15));

        JFsz.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFsz.getText())){
                    JOptionPane.showMessageDialog(JFsz, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                    return ;
                    }
                if(Integer.parseInt( JFnz.getText() )-4
                  <Integer.parseInt( JFsz.getText() ))
                    JOptionPane.showMessageDialog(JFsz, 
                           "You must set: sz <= nz-4",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });


        layout1.row().grid(labelnx).add(JFnx).grid(labelnz).add(JFnz);
        layout1.row().grid(labeldx).add(JFdx).grid(labeldz).add(JFdz);
        layout1.row().grid(labelsx).add(JFsx).grid(labelsz).add(JFsz);



        /*nray*/
        JLabel labelnray = new JLabel(" nray:");
        labelnray.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnray = new JTextField(5);
        JFnray.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnray.setText("90");
        JFnray.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnray.getText()))
                    JOptionPane.showMessageDialog(JFnray, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });


        /*raypathwidth*/
        String[] LW = {"   1   ","   2   ","   3   ","   4   ",
                    "   5   ","   6   ","   7   ","   8   ","   9   "};
        final JComboBox comboxLW = new JComboBox<String>(LW);
        comboxLW.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLW.setSelectedIndex(0);
        raypathwidth = 1;
        comboxLW.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    raypathwidth = index+1;
                    System.out.println("raypath linewidth = "+raypathwidth);
                    }
                  }
            });
        JLabel labelraywidth = new JLabel(" RayWidth:");
        labelraywidth.setFont(new Font("Georgia", Font.BOLD, 15));


        /*raypathcolor*/
        String[] LC = {"  red  ","  blue  ","  green  ","  black  "
                  ,"  gray  ","  yellow ","  pink  "
                  ,"  cyan  ","  magenta  ","  orange  "};
        final JComboBox comboxLC = new JComboBox<String>(LC);
        comboxLC.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLC.setSelectedIndex(0);
        raypathcolor = 1;
        comboxLC.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    raypathcolor = index+1;
                    System.out.println("Raypath linecolor = "+raypathcolor);
                    }
                  }
            });
        JLabel labelraycolor = new JLabel(" RayColor:");
        labelraycolor.setFont(new Font("Georgia", Font.BOLD, 15));


        /*s*/
        JLabel labels = new JLabel(" s[cm]:");
        labels.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFs = new JTextField(5);
        JFs.setFont(new Font("Georgia", Font.BOLD, 15));
        JFs.setText("50");
        JFs.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFs.getText()))
                    JOptionPane.showMessageDialog(JFs, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });



        /* toolbar3 for raypath.txt*/
        final JToolBar toolbar3 = new JToolBar();
        tmplabel = new JLabel("OutPath:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar3.add(tmplabel);
        final JTextField JFraypathtext = new JTextField(20);
        JFraypathtext.setFont(new Font("Georgia", Font.BOLD, 15));
        JFraypathtext.setText("raypath.txt");
        raypathtext = JFraypathtext.getText();
        toolbar3.add(JFraypathtext);

        final JButton buttonraypathtext = new JButton(folder);
        buttonraypathtext.setAlignmentX(1f);
        buttonraypathtext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();

                int ret = fileopen.showDialog(panel, "Cover");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    JFraypathtext.setText(file.toString());
                    raypathtext = file.toString();
                    System.out.println("Raypath text file name : "+raypathtext);
                    }
                  }
            });
        toolbar3.add(buttonraypathtext);




        /*fangle*/
        JLabel labelfangle = new JLabel(" fangle[o]:");
        labelfangle.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFfangle = new JTextField(5);
        JFfangle.setFont(new Font("Georgia", Font.BOLD, 15));
        JFfangle.setText("45");
        JFfangle.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFfangle.getText()))
                    JOptionPane.showMessageDialog(JFfangle, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });


        /*dangle*/
        JLabel labeldangle = new JLabel(" dangle[o]:");
        labeldangle.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFdangle = new JTextField(5);
        JFdangle.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdangle.setText("1");
        JFdangle.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFdangle.getText()))
                    JOptionPane.showMessageDialog(JFdangle, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });



        JLabel labelconstvel = new JLabel("m/s");
        labelconstvel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar01.add(new JLabel("  "));
        toolbar01.add(JFconstvel);
        toolbar01.add(labelconstvel);

        /*OK button*/
        ImageIcon iconOK = new ImageIcon("picture/ButtonImg/ok32.png");
        JButton buttonOK = new JButton(iconOK);
        buttonOK.setAlignmentX(1f);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                nx = Integer.parseInt( JFnx.getText() );
                nz = Integer.parseInt( JFnz.getText() );
                sx = Integer.parseInt( JFsx.getText() );
                sz = Integer.parseInt( JFsz.getText() );
                dx = (float)Integer.parseInt( JFdx.getText() );
                dz = (float)Integer.parseInt( JFdz.getText() );

                nray = Integer.parseInt( JFnray.getText() );
                fangle = (float)Integer.parseInt( JFfangle.getText() );
                dangle = (float)Integer.parseInt( JFdangle.getText() );
                s = (float)(Integer.parseInt( JFs.getText() )/100f);

                constvel = (float)Integer.parseInt( JFconstvel.getText() );

                FNvel = JFselectvelocity.getText();
                raypathtext = JFraypathtext.getText();

                System.out.println("\nnx = "+nx+",  dx = "+dx+"\n");
                System.out.println("nz = "+nz+",  dz = "+dz+"\n");
                System.out.println("sz = "+sz+",  sz = "+sz+"\n");
                System.out.println("fangle = "+fangle+",  dangle = "+dangle+"\n");
                System.out.println("raypathcolor = "+raypathcolor
                          +",  raypathwidth = "+raypathwidth+"\n");
                System.out.println("nray = "+nray+"\n");
                System.out.println("s = "+s+"\n");
                System.out.println("constant velocity = "+constvel+"\n");
                System.out.println("velocity file path:"+FNvel+"\n");
                System.out.println("Output raypath file path:"+raypathtext+"\n");



                if(sx<0||sx>nx||sz<0||sz>nz){
                    JOptionPane.showMessageDialog(JFdz, 
                         "shot location out of boundary!",
                         "Error", JOptionPane.ERROR_MESSAGE);
                }else{

                    try{
                    if(flag_SelectVelFile){
                        if(FNvel.equals("/home/")){
                            JOptionPane.showMessageDialog(JFdz, 
                                 "Please select velocity file!",
                                 "Error", JOptionPane.ERROR_MESSAGE);

                        }else new myLaganRayTracingJFrame(
                                    nx,nz,sx,sz,dx,dz,s,FNvel,
                                    nray,fangle,dangle,
                                    raypathcolor,raypathwidth,
                                    raypathtext, true, constvel);
                    }else{
                        new myLaganRayTracingJFrame(
                                    nx,nz,sx,sz,dx,dz,s,FNvel,
                                    nray,fangle,dangle,
                                    raypathcolor,raypathwidth,
                                    raypathtext, false, constvel);
                          }
                    }catch(Exception ee){ee.printStackTrace();}
                    }
                  }
            });
        

        toolbar0.add(new JLabel("     "));
        toolbar0.add(buttonOK);


        layout2.row().grid(labelnray).add(JFnray).grid(labels).add(JFs);
        layout2.row().grid(labelraywidth).add(comboxLW).grid(labelraycolor).add(comboxLC);
        layout2.row().grid(labelfangle).add(JFfangle).grid(labeldangle).add(JFdangle);

        layout3.row().left().add(js1).fill().withOwnRowWidth();
        layout3.row().grid().add(panel1);
        layout3.row().left().add(js2).fill().withOwnRowWidth();
        layout3.row().grid().add(panel2);

        panel.add(toolbar0);
        panel.add(toolbar01);
        panel.add(toolbar3);


        add(panel, BorderLayout.NORTH);
        add(panel3, BorderLayout.CENTER);

        pack();

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
}



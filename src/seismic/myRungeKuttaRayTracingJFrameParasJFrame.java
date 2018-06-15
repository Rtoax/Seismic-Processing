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
//a##  myRungeKuttaRayTracingJFrameParasJFrame
//a##
//a########################################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myRungeKuttaRayTracingJFrameParasJFrame extends JFrame{

    private Toolkit toolkit;

    private String FNv = "vel.dat";
    private String FNd = "delta.dat";
    private String FNe = "epsilon.dat";
    private String FNt = "time.dat";
    private String FNp = "raypath.txt";
    private String FNl = "length.txt";
    private int nx=301,nz=301,nt=501,na=50,nsx=2;
    private float dx=5f,dz=5f,dt=0.0005f,fa=300f,da=1f,fsx=nx*dx/2f,dsx=nx*dx/3f,sz=1f;

    private int raypathcolor=1;
    private int raypathwidth=2;

    private boolean flag_SelectFile = false;

    private float constv, conste, constd;

    private String outputPath;

    private JLabel tmplabel;


    private DesignGridLayout layout1,layout2,layout3,layout4,layout5;

    private JSeparator js1 = new JSeparator();
    private JSeparator js2 = new JSeparator();
    private JSeparator js3 = new JSeparator();
    private JSeparator js4 = new JSeparator();

    public myRungeKuttaRayTracingJFrameParasJFrame(){

        setTitle("Toa ISO Lagan RayTracing. Copyright@ Rong Tao");
        setSize(700, 660);
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
                        new myRungeKuttaRayTracingAboutDialog();
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
        panel.setLayout(new GridLayout(5,1));

        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        final JPanel panel4 = new JPanel();
        final JPanel panel5 = new JPanel();
        layout1 = new DesignGridLayout(panel1);
        layout2 = new DesignGridLayout(panel2);
        layout3 = new DesignGridLayout(panel3);
        layout4 = new DesignGridLayout(panel4);
        layout5 = new DesignGridLayout(panel5);


        /* toolbar0 for velocity*/
        final JToolBar toolbar0 = new JToolBar();
        JCheckBox checkbox = new JCheckBox("Select File[Y/N]", false);
        checkbox.setFocusable(false);
        checkbox.setFont(new Font("Georgia", Font.BOLD, 15));
        final JLabel selectYN = new JLabel(">NO ");
        selectYN.setFont(new Font("Georgia", Font.BOLD, 15));

        toolbar0.add(checkbox);
        toolbar0.add(selectYN);

        ImageIcon folder = new ImageIcon("picture/ButtonImg/folder32.png");

        /* toolbar01 for FNv*/
        final JToolBar toolbar01 = new JToolBar();
        tmplabel = new JLabel("Velocity:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar01.add(tmplabel);
        final JTextField JFselectvelocity = new JTextField(20);
        JFselectvelocity.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectvelocity.setText("/home/");
        FNv = JFselectvelocity.getText();
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
                    FNv = file.toString();
                    System.out.println("Velocity file name : "+FNv);
                    }
                  }
            });
        toolbar01.add(buttonvelocity);

        JFselectvelocity.setEnabled(false);
        buttonvelocity.setEnabled(false);

        /* toolbar02 for FNe*/
        final JToolBar toolbar02 = new JToolBar();
        tmplabel = new JLabel(" Epsilon:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar02.add(tmplabel);
        final JTextField JFselectepsilon = new JTextField(20);
        JFselectepsilon.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectepsilon.setText("/home/");
        FNe = JFselectepsilon.getText();
        toolbar02.add(JFselectepsilon);

        final JButton buttonepsilon = new JButton(folder);
        buttonepsilon.setAlignmentX(1f);
        buttonepsilon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filtertxt = new FileNameExtensionFilter("*.dat", "dat");
                FileFilter filterpar = new FileNameExtensionFilter("*.bin", "bin");
                fileopen.addChoosableFileFilter(filtertxt);
                fileopen.addChoosableFileFilter(filterpar);

                int ret = fileopen.showDialog(panel, "Select");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    JFselectepsilon.setText(file.toString());
                    FNe = file.toString();
                    System.out.println("Epsilon file name : "+FNe);
                    }
                  }
            });
        toolbar02.add(buttonepsilon);

        JFselectepsilon.setEnabled(false);
        buttonepsilon.setEnabled(false);


        /* toolbar03 for FNd*/
        final JToolBar toolbar03 = new JToolBar();
        tmplabel = new JLabel("   Delta:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar03.add(tmplabel);
        final JTextField JFselectdelta = new JTextField(20);
        JFselectdelta.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectdelta.setText("/home/");
        FNd = JFselectdelta.getText();
        toolbar03.add(JFselectdelta);

        final JButton buttondelta = new JButton(folder);
        buttondelta.setAlignmentX(1f);
        buttondelta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filtertxt = new FileNameExtensionFilter("*.dat", "dat");
                FileFilter filterpar = new FileNameExtensionFilter("*.bin", "bin");
                fileopen.addChoosableFileFilter(filtertxt);
                fileopen.addChoosableFileFilter(filterpar);

                int ret = fileopen.showDialog(panel, "Select");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    JFselectdelta.setText(file.toString());
                    FNd = file.toString();
                    System.out.println("delta file name : "+FNd);
                    }
                  }
            });
        toolbar03.add(buttondelta);

        JFselectdelta.setEnabled(false);
        buttondelta.setEnabled(false);


        /* toolbar031 for output path*/
        final JToolBar toolbar031 = new JToolBar();
        tmplabel = new JLabel(" OutPath:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar031.add(tmplabel);
        final JTextField JFselectPath = new JTextField(20);
        JFselectPath.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectPath.setText("/home/Toa");
        outputPath = JFselectPath.getText();

        toolbar031.add(JFselectPath);

        final JButton buttonPath = new JButton(folder);
        buttonPath.setAlignmentX(1f);
        buttonPath.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                fileopen.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int ret = fileopen.showDialog(panel, "SelectPath");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    JFselectPath.setText(file.toString());
                    outputPath = file.toString();
                    System.out.println("Output File Path: "+outputPath);
                    }
                  }
            });
        toolbar031.add(buttonPath);



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
        JLabel labelconstvel = new JLabel("m/s");
        labelconstvel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar01.add(JFconstvel);
        toolbar01.add(labelconstvel);


        /*constant epsilon*/
        final JTextField JFconsteps = new JTextField(5);
        JFconsteps.setFont(new Font("Georgia", Font.BOLD, 15));
        JFconsteps.setText("30");
        JFconsteps.getDocument().addDocumentListener (new DocumentListener() {  
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
        JLabel labelconsteps = new JLabel("E-2");
        labelconsteps.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar02.add(JFconsteps);
        toolbar02.add(labelconsteps);


        /*constant delta*/
        final JTextField JFconstdel = new JTextField(5);
        JFconstdel.setFont(new Font("Georgia", Font.BOLD, 15));
        JFconstdel.setText("20");
        JFconstdel.getDocument().addDocumentListener (new DocumentListener() {  
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
        JLabel labelconstdel = new JLabel("E-2");
        labelconstdel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar03.add(JFconstdel);
        toolbar03.add(labelconstdel);


        /* checkbox determean wither select binary file for v,e,d */
        checkbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                flag_SelectFile = !flag_SelectFile;

                if (flag_SelectFile) {
                    selectYN.setText(">YES");
                } else {
                    selectYN.setText(">NO ");
                    }
                JFselectvelocity.setEnabled(flag_SelectFile);
                buttonvelocity.setEnabled(flag_SelectFile);
                JFselectepsilon.setEnabled(flag_SelectFile);
                buttonepsilon.setEnabled(flag_SelectFile);
                JFselectdelta.setEnabled(flag_SelectFile);
                buttondelta.setEnabled(flag_SelectFile);

                JFconstvel.setEnabled(!flag_SelectFile);
                JFconsteps.setEnabled(!flag_SelectFile);
                JFconstdel.setEnabled(!flag_SelectFile);
                  }
            });





        /*nx*/
        JLabel labelnx = new JLabel("nx:");
        labelnx.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnx = new JTextField(5);
        JFnx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnx.setText("300");
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

        /*nz*/
        JLabel labelnz = new JLabel("nz:");
        labelnz.setFont(new Font("Georgia", Font.BOLD, 15));
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
        /*dx*/
        JLabel labeldx = new JLabel("dx[m]:");
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
        JLabel labeldz = new JLabel("dz[m]:");
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

        layout1.row().grid(labelnx).add(JFnx).grid(labelnz).add(JFnz);
        layout1.row().grid(labeldx).add(JFdx).grid(labeldz).add(JFdz);


        /*nsx*/
        JLabel labelnsx = new JLabel("nsx:");
        labelnsx.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnsx = new JTextField(5);
        JFnsx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnsx.setText("1");
        JFnsx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnsx.getText()))
                    JOptionPane.showMessageDialog(JFnsx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*fsx*/
        JLabel labelfsx = new JLabel("fsx[m]:");
        labelfsx.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFfsx = new JTextField(5);
        JFfsx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFfsx.setText("0");
        JFfsx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFfsx.getText()))
                    JOptionPane.showMessageDialog(JFfsx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*dsx*/
        JLabel labeldsx = new JLabel("dsx[m]:");
        labeldsx.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFdsx = new JTextField(5);
        JFdsx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdsx.setText("0");
        JFdsx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFdsx.getText()))
                    JOptionPane.showMessageDialog(JFdsx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*sz*/
        JLabel labelsz = new JLabel(" sz[m]:");
        labelsz.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFsz = new JTextField(5);
        JFsz.setFont(new Font("Georgia", Font.BOLD, 15));
        JFsz.setText("0");
        JFsz.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFsz.getText()))
                    JOptionPane.showMessageDialog(JFsz, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        layout2.row().grid(labelnsx).add(JFnsx).grid(labelfsx).add(JFfsx);
        layout2.row().grid(labeldsx).add(JFdsx).grid(labelsz).add(JFsz);



        /*nt*/
        JLabel labelnt = new JLabel("nt:");
        labelnt.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnt = new JTextField(5);
        JFnt.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnt.setText("201");
        JFnt.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnt.getText()))
                    JOptionPane.showMessageDialog(JFnt, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*dt*/
        JLabel labeldt = new JLabel("dt[us]:");
        labeldt.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFdt = new JTextField(5);
        JFdt.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdt.setText("500");
        JFdt.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFdt.getText()))
                    JOptionPane.showMessageDialog(JFdt, 
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
        JLabel labelraywidth = new JLabel("RayWidth:");
        labelraywidth.setFont(new Font("Georgia", Font.BOLD, 15));

        /*raypathcolor*/
        String[] LC = {"  red  ","  blue  ","  green  ","  black  "
                  ,"  gray  ","  yellow ","  pink  ","  cyan  ","  magenta  ","  orange  "};
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
        JLabel labelraycolor = new JLabel("RayColor:");
        labelraycolor.setFont(new Font("Georgia", Font.BOLD, 15));


        layout3.row().grid(labelnt).add(JFnt).grid(labeldt).add(JFdt);
        layout3.row().grid(labelraywidth).add(comboxLW).grid(labelraycolor).add(comboxLC);


        /*na*/
        JLabel labelna = new JLabel("na:");
        labelna.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFna = new JTextField(5);
        JFna.setFont(new Font("Georgia", Font.BOLD, 15));
        JFna.setText("90");
        JFna.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFna.getText()))
                    JOptionPane.showMessageDialog(JFna, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*fa*/
        JLabel labelfa = new JLabel("fa[o]:");
        labelfa.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFfa = new JTextField(5);
        JFfa.setFont(new Font("Georgia", Font.BOLD, 15));
        JFfa.setText("0");
        JFfa.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFfa.getText()))
                    JOptionPane.showMessageDialog(JFfa, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*da*/
        JLabel labelda = new JLabel("da[o]:");
        labelda.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFda = new JTextField(5);
        JFda.setFont(new Font("Georgia", Font.BOLD, 15));
        JFda.setText("1");
        JFda.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFda.getText()))
                    JOptionPane.showMessageDialog(JFda, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });


        layout4.row().grid(labelna).add(JFna).grid();
        layout4.row().grid(labelfa).add(JFfa).grid(labelda).add(JFda);

        /*OK button*/
        ImageIcon iconOK = new ImageIcon("picture/ButtonImg/ok32.png");
        final JButton buttonOK = new JButton(iconOK);
        buttonOK.setAlignmentX(1f);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                nt = Integer.parseInt( JFnt.getText() );
                dt = (float)(Integer.parseInt( JFdt.getText() )/1000000f);

                nx = Integer.parseInt( JFnx.getText() );
                nz = Integer.parseInt( JFnz.getText() );
                dx = (float)Integer.parseInt( JFdx.getText() );
                dz = (float)Integer.parseInt( JFdz.getText() );

                nsx = Integer.parseInt( JFnsx.getText() );
                fsx = (float)Integer.parseInt( JFfsx.getText() );
                dsx = (float)Integer.parseInt( JFdsx.getText() );
                sz = (float)Integer.parseInt( JFsz.getText() );

                na = Integer.parseInt( JFna.getText() );
                fa = (float)Integer.parseInt( JFfa.getText() );
                da = (float)Integer.parseInt( JFda.getText() );

                constv = (float)Integer.parseInt( JFconstvel.getText() );
                conste = (float)(Integer.parseInt( JFconsteps.getText() )/100f);
                constd = (float)(Integer.parseInt( JFconstdel.getText() )/100f);

                FNv = JFselectvelocity.getText();
                FNe = JFselectepsilon.getText();
                FNd = JFselectdelta.getText();

                outputPath = JFselectPath.getText();

                FNt = outputPath+"/time.dat";
                FNp = outputPath+"/raypath.txt";
                FNl = outputPath+"/length.txt";

                File file = new File(outputPath);  
                if(!file.exists()) {  
                    file.mkdirs();  
                    }

                System.out.println("nt = "+nt+", dt = "+dt+"\n");
                System.out.println("nx = "+nx+",  dx = "+dx+"\n");
                System.out.println("nz = "+nz+",  dz = "+dz+"\n");
                System.out.println("nsx = "+nsx+", fsx = "+fsx+", dsx = "+dsx+ ",  sz = "+sz+"\n");
                System.out.println("na = "+na+", fa = "+fa+",  da = "+da+"\n");
                System.out.println("raypathcolor = "+raypathcolor
                          +",  raypathwidth = "+raypathwidth+"\n");
                System.out.println("cv = "+constv+", ce = "+conste+", cd = "+constd+"\n");
                System.out.println("velocity file path:"+FNv+"\n");
                System.out.println("epsilon file path:"+FNe+"\n");
                System.out.println("delta file path:"+FNd+"\n");
                System.out.println("time file path:"+FNt+"\n");
                System.out.println("raypath file path:"+FNp+"\n");
                System.out.println("ray length file path:"+FNl+"\n");


                if(fsx<0||fsx>(nx-1)*dx||fsx+(nsx-1)*dsx<0||fsx+(nsx-1)*dsx>(nx-1)*dx
                    ||sz<0||sz>(nz-1)*dz){
                    JOptionPane.showMessageDialog(buttonOK, 
                          "Shot locations exceed model dimensions!",
                          "Error", JOptionPane.ERROR_MESSAGE);

                }else{
                    new myRungeKuttaRayTracingJFrame(FNv,FNd,FNe,FNt,FNp,FNl,
                                 nt, nx, nz,na, nsx, dx, dz,
                                 dt,fa, da, fsx,dsx,sz,
                                 raypathcolor,raypathwidth,
                                 flag_SelectFile,constv,conste,constd);
                    }

                  }
            });

        toolbar0.add(new JLabel("     "));
        toolbar0.add(buttonOK);


        panel.add(toolbar0);
        panel.add(toolbar01);
        panel.add(toolbar02);
        panel.add(toolbar03);
        panel.add(toolbar031);

        layout5.row().left().add(js1).fill().withOwnRowWidth();
        layout5.row().grid().add(panel1);
        layout5.row().left().add(js2).fill().withOwnRowWidth();
        layout5.row().grid().add(panel2);
        layout5.row().left().add(js3).fill().withOwnRowWidth();
        layout5.row().grid().add(panel3);
        layout5.row().left().add(js4).fill().withOwnRowWidth();
        layout5.row().grid().add(panel4);




        add(panel, BorderLayout.NORTH);
        add(panel5, BorderLayout.CENTER);

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



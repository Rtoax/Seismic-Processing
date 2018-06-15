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


//a#################################
//a##
//a##  myXmovie2OrderAbsParasJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXmovie2OrderAbsParasJFrame extends JFrame{


    private Toolkit toolkit;

    private int NX,NZ,SX,SZ,NT,MM,npd;
    private float velocity,epsilon,delta,DX,DZ,DT,FM;

    private boolean flag_SelectFile = false;
    private boolean flag_VTI = true;
    private String filev, filee, filed;

    private JLabel tmplabel;

    private DesignGridLayout layout1,layout2,layout3;

    private JSeparator js1 = new JSeparator();
    private JSeparator js2 = new JSeparator();

    public myXmovie2OrderAbsParasJFrame(){

        setTitle("Toa VTI Snapshot Movie");
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
                        new myXmovie2OrderAbsAboutDialog();
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

        /* include 2 toolbars */
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,1));

        final JPanel panel1 = new JPanel();
        final JPanel panel2 = new JPanel();
        final JPanel panel3 = new JPanel();
        layout1 = new DesignGridLayout(panel1);
        layout2 = new DesignGridLayout(panel2);
        layout3 = new DesignGridLayout(panel3);

        /* toolbar0 */
        final JToolBar toolbar0 = new JToolBar();

        final JCheckBox checkboxVTI = new JCheckBox("vti", true);
        checkboxVTI.setFocusable(false);
        checkboxVTI.setFont(new Font("Georgia", Font.BOLD, 15));

        final JCheckBox checkboxISO = new JCheckBox("iso", false);
        checkboxISO.setFocusable(false);
        checkboxISO.setFont(new Font("Georgia", Font.BOLD, 15));


        JCheckBox checkboxSelectFile = new JCheckBox("Select File[Y/N]", false);
        checkboxSelectFile.setFocusable(false);
        checkboxSelectFile.setFont(new Font("Georgia", Font.BOLD, 15));

        final JLabel selectYN = new JLabel(">NO    ");
        selectYN.setFont(new Font("Georgia", Font.BOLD, 15));

        final JLabel labelvtiiso = new JLabel(">VTI   ");
        labelvtiiso.setFont(new Font("Georgia", Font.BOLD, 15));


        toolbar0.add(checkboxVTI);
        toolbar0.add(checkboxISO);
        toolbar0.add(labelvtiiso);
        toolbar0.add(checkboxSelectFile);
        toolbar0.add(selectYN);

        ImageIcon folder = new ImageIcon("picture/ButtonImg/folder32.png");

        /* toolbar01 */
        final JToolBar toolbar01 = new JToolBar();
        tmplabel = new JLabel("Velocity:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar01.add(tmplabel);
        final JTextField JFselectvelocity = new JTextField(30);
        JFselectvelocity.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectvelocity.setText("/home/");
        filev = JFselectvelocity.getText();
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
                    filev = file.toString();
                    System.out.println("Velocity file name : "+filev);
                    }
                  }
            });
        toolbar01.add(buttonvelocity);

        /* toolbar02 */
        final JToolBar toolbar02 = new JToolBar();
        tmplabel = new JLabel(" Epsilon:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar02.add(tmplabel);
        final JTextField JFselectepsilon = new JTextField(30);
        JFselectepsilon.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectepsilon.setText("/home/");
        filee = JFselectepsilon.getText();
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
                    filee = file.toString();
                    System.out.println("epsilon file name : "+filee);
                    }
                  }
            });
        toolbar02.add(buttonepsilon);

        /* toolbar03 */
        final JToolBar toolbar03 = new JToolBar();
        tmplabel = new JLabel("   Delta:");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar03.add(tmplabel);
        final JTextField JFselectdelta = new JTextField(30);
        JFselectdelta.setFont(new Font("Georgia", Font.BOLD, 15));
        JFselectdelta.setText("/home/");
        filed = JFselectdelta.getText();
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
                    filed = file.toString();
                    System.out.println("delta file name : "+filed);
                    }
                  }
            });
        toolbar03.add(buttondelta);

        JFselectvelocity.setEnabled(false);
        buttonvelocity.setEnabled(false);
        JFselectepsilon.setEnabled(false);
        buttonepsilon.setEnabled(false);
        JFselectdelta.setEnabled(false);
        buttondelta.setEnabled(false);

        final JTextField JFvelocity = new JTextField(5);
        JFvelocity.setFont(new Font("Georgia", Font.BOLD, 15));
        JFvelocity.setText("2000");
        JFvelocity.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFvelocity.getText()))
                    JOptionPane.showMessageDialog(JFvelocity, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        final JTextField JFepsilon = new JTextField(5);
        JFepsilon.setFont(new Font("Georgia", Font.BOLD, 15));
        JFepsilon.setText("40");
        JFepsilon.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFepsilon.getText()))
                    JOptionPane.showMessageDialog(JFvelocity, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        final JTextField JFdelta = new JTextField(5);
        JFdelta.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdelta.setText("20");
        JFdelta.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFdelta.getText()))
                    JOptionPane.showMessageDialog(JFvelocity, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        checkboxISO.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                checkboxISO.setSelected(true);
                checkboxVTI.setSelected(false);
                flag_VTI = false;
                 labelvtiiso.setText(">ISO   ");
                JFselectepsilon.setEnabled(flag_SelectFile & flag_VTI);
                buttonepsilon.setEnabled(flag_SelectFile & flag_VTI);
                JFselectdelta.setEnabled(flag_SelectFile & flag_VTI);
                buttondelta.setEnabled(flag_SelectFile & flag_VTI);
                JFepsilon.setEnabled(!flag_SelectFile & flag_VTI);
                JFdelta.setEnabled(!flag_SelectFile & flag_VTI);
                  }
            });
        checkboxVTI.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                checkboxVTI.setSelected(true);
                checkboxISO.setSelected(false);
                flag_VTI = true;
                labelvtiiso.setText(">VTI   ");
                JFselectepsilon.setEnabled(flag_SelectFile & flag_VTI);
                buttonepsilon.setEnabled(flag_SelectFile & flag_VTI);
                JFselectdelta.setEnabled(flag_SelectFile & flag_VTI);
                buttondelta.setEnabled(flag_SelectFile & flag_VTI);
                JFepsilon.setEnabled(!flag_SelectFile & flag_VTI);
                JFdelta.setEnabled(!flag_SelectFile & flag_VTI);
                  }
            });
        checkboxSelectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                flag_SelectFile = !flag_SelectFile;

                if (flag_SelectFile) {
                    selectYN.setText(">YES   ");
                } else {
                    selectYN.setText(">NO    ");
                    }
                JFselectvelocity.setEnabled(flag_SelectFile);
                buttonvelocity.setEnabled(flag_SelectFile);
                JFselectepsilon.setEnabled(flag_SelectFile & flag_VTI);
                buttonepsilon.setEnabled(flag_SelectFile & flag_VTI);
                JFselectdelta.setEnabled(flag_SelectFile & flag_VTI);
                buttondelta.setEnabled(flag_SelectFile & flag_VTI);

                JFvelocity.setEnabled(!flag_SelectFile);
                JFepsilon.setEnabled(!flag_SelectFile & flag_VTI);
                JFdelta.setEnabled(!flag_SelectFile & flag_VTI);
                  }
            });


        /* toolbar1 */
        final JToolBar toolbar1 = new JToolBar();
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


        /* toolbar2 */
        JToolBar toolbar2 = new JToolBar();
        /*nt*/
        JLabel labelnt = new JLabel(" nt:");
        labelnt.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnt = new JTextField(5);
        JFnt.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnt.setText("300");
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
        JLabel labeldt = new JLabel(" dt(us):");
        labeldt.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFdt = new JTextField(5);
        JFdt.setFont(new Font("Georgia", Font.BOLD, 15));
        JFdt.setText("1000");
        JFdt.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFdt.getText()))
                    JOptionPane.showMessageDialog(JFnt, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*fm*/
        JLabel labelfm = new JLabel(" f(Hz):");
        labelfm.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFfm = new JTextField(5);
        JFfm.setFont(new Font("Georgia", Font.BOLD, 15));
        JFfm.setText("25");
        JFfm.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFfm.getText()))
                    JOptionPane.showMessageDialog(JFnt, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*npd*/
        JLabel labelnpd = new JLabel(" npd:");
        labelnpd.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnpd = new JTextField(5);
        JFnpd.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnpd.setText("20");
        JFnpd.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(JFnpd.getText()))
                    JOptionPane.showMessageDialog(JFnt, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });

        /*mm*/
        JLabel labelmm = new JLabel(" mm:");
        labelmm.setFont(new Font("Georgia", Font.BOLD, 15));

        String[] m = {" 1 "," 2 "," 3 "," 4 "};
        JComboBox comboxM = new JComboBox<String>(m);
        comboxM.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxM.setSelectedIndex(3);
        MM = 4;
        comboxM.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    MM = index+1;
                    }
                  }
            });



        layout2.row().grid(labelfm).add(JFfm).grid().add(new JLabel(""));
        layout2.row().grid(labelnt).add(JFnt).grid(labeldt).add(JFdt);
        layout2.row().grid(labelnpd).add(JFnpd).grid(labelmm).add(comboxM);

        /*velocity*/
        tmplabel = new JLabel("m/s");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar01.add(JFvelocity);
        toolbar01.add(tmplabel);

        /*epsilon*/
        tmplabel = new JLabel("E-2");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar02.add(JFepsilon);
        toolbar02.add(tmplabel);



        /*delta*/
        tmplabel = new JLabel("E-2");
        tmplabel.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar03.add(JFdelta);
        toolbar03.add(tmplabel);




        /*OK button*/
        ImageIcon iconOK = new ImageIcon("picture/ButtonImg/ok32.png");
        JButton buttonOK = new JButton(iconOK);
        buttonOK.setAlignmentX(1f);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                NX = Integer.parseInt( JFnx.getText() );
                NZ = Integer.parseInt( JFnz.getText() );
                DX = (float)Integer.parseInt( JFdx.getText() );
                DZ = (float)Integer.parseInt( JFdz.getText() );
                SX = Integer.parseInt( JFsx.getText() );
                SZ = Integer.parseInt( JFsz.getText() );
                NT = Integer.parseInt( JFnt.getText() );
                DT = (float)(Integer.parseInt( JFdt.getText() )/1000000.0f);
                FM = (float)Integer.parseInt( JFfm.getText() );
                npd = Integer.parseInt( JFnpd.getText() );

            if(!flag_SelectFile){
                velocity = (float)Integer.parseInt( JFvelocity.getText() );
                epsilon = (float)(Integer.parseInt( JFepsilon.getText() )/100.0f);
                delta = (float)(Integer.parseInt( JFdelta.getText() )/100.0f);
            }else{
                velocity = 0.0f;
                epsilon = 0.0f;
                delta = 0.0f;
                  }
                System.out.println("nx = "+NX);
                System.out.println("nz = "+NZ);
                System.out.println("dx = "+DX);
                System.out.println("dz = "+DZ);
                System.out.println("sx = "+SX);
                System.out.println("sz = "+SZ);
                System.out.println("nt = "+NT);
                System.out.println("dt = "+DT);
                System.out.println("fm = "+FM);
                System.out.println("npd = "+npd);
                System.out.println("mm = "+MM);

            if(!flag_SelectFile){
                System.out.println("velocity = "+velocity);
                System.out.println("epsilon = "+epsilon);
                System.out.println("delta = "+delta);
                   }

                new myXmovie2OrderAbsJFrame(velocity,epsilon,delta,
                        SX,SZ,NX,NZ,DX,DZ,NT,DT,FM,MM,npd,
                        filev,filee,filed,flag_SelectFile,flag_VTI);
                  }
            });
        toolbar0.add(new JLabel("   "));
        toolbar0.add(buttonOK);

        layout3.row().left().add(js1).fill().withOwnRowWidth();
        layout3.row().grid().add(panel1);
        layout3.row().left().add(js2).fill().withOwnRowWidth();
        layout3.row().grid().add(panel2);

        panel.add(toolbar0);
        panel.add(toolbar01);
        panel.add(toolbar02);
        panel.add(toolbar03);
        //panel.add(panel3);

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



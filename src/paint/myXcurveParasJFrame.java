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
//a##  myXcurveParasJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXcurveParasJFrame extends JFrame{

    private Toolkit toolkit;

    private Graphics2D g2d;

    private myXcurveJPanel myXcurveJPanel;

    private int nx ;
    private String txtfile ;
    private int linewidth;
    private int linecolor;


    private DesignGridLayout designgridlayout;

    private JSeparator js1 = new JSeparator();
    private JSeparator js2 = new JSeparator();
    private JSeparator js3 = new JSeparator();
    private JSeparator js4 = new JSeparator();
    private JSeparator js5 = new JSeparator();
    private JSeparator js6 = new JSeparator();

    public myXcurveParasJFrame(){


        final FileDialog FileDialogSave;


        setTitle("Toa Curve");
        setSize(600, 220);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width/2 - getWidth())/2, (size.height - getWidth())/2);

        /* menu: save, close */
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
                        new myXcurveAboutDialog();
                          }
                    });
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

        final JPanel panel = new JPanel();
        designgridlayout = new DesignGridLayout(panel);

        //JLabel titlecurve = new JLabel("Curve");
        //titlecurve.setFont(new Font("Georgia", Font.BOLD, 15));


        //designgridlayout.row().left().add(js1).fill().withOwnRowWidth();
        //designgridlayout.row().center().add(titlecurve);
        designgridlayout.row().left().add(js2).fill().withOwnRowWidth();
        designgridlayout.row().left().add(js3).fill().withOwnRowWidth();



        /* file  */
        JLabel labelfile = new JLabel("File");
        labelfile.setFont(new Font("Georgia", Font.BOLD, 15));
        final JTextField textfieldfilename = new JTextField(20);
        textfieldfilename.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldfilename.setText("/home/");
        txtfile = "/home/";


        JButton buttonfilename = new JButton("select file path");
        buttonfilename.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonfilename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filterdat = new FileNameExtensionFilter("*.txt", "txt");
                FileFilter filterbin = new FileNameExtensionFilter("*.par", "par");
                fileopen.addChoosableFileFilter(filterdat);
                fileopen.addChoosableFileFilter(filterbin);

                int ret = fileopen.showDialog(panel, "Select");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    textfieldfilename.setText(file.toString());
                    txtfile = file.toString();
                    System.out.println("txtfile: "+txtfile);
                    }
                  }
            });

        designgridlayout.row().grid(labelfile).add(textfieldfilename).add(buttonfilename);
        designgridlayout.row().left().add(js4).fill().withOwnRowWidth();


        /* length  */
        JLabel labellength = new JLabel("length");
        labellength.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField JFnx = new JTextField(5);
        JFnx.setFont(new Font("Georgia", Font.BOLD, 15));
        JFnx.setText("100");
        /*JFnx*/
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


        /* width  */
        JLabel labelwidth = new JLabel("linewidth");
        labelwidth.setFont(new Font("Georgia", Font.BOLD, 15));

        String[] LW = {"--1--","--2--","--3--","--4--",
                    "--5--","--6--","--7--","--8--","--9--"};
        JComboBox comboxLW = new JComboBox<String>(LW);
        comboxLW.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLW.setSelectedIndex(0);
        linewidth = 1;
        comboxLW.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    linewidth = index+1;
                    System.out.println("width = "+(index+1));
                    }
                  }
            });


        /* color  */
        JLabel labelcolor = new JLabel("linecolor");
        labelcolor.setFont(new Font("Georgia", Font.BOLD, 15));

        String[] LC = {"red  ","blue ","green","black"};
        JComboBox comboxLC = new JComboBox<String>(LC);
        comboxLC.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLC.setSelectedIndex(0);
        linecolor  = 1;
        comboxLC.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    linecolor = index+1;
                    System.out.println("color = "+(index+1));
                    }
                  }
            });
        designgridlayout.row().grid(labellength).add(JFnx)
                      .grid(labelwidth).add(comboxLW)
                      .grid(labelcolor).add(comboxLC);

        /* OK */
        JButton buttonOK = new JButton("show the line");
        buttonOK.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                nx = Integer.parseInt(JFnx.getText()); 
                if(txtfile.equals("/home/")){
                     JOptionPane.showMessageDialog(textfieldfilename, 
                                      "Please Select File.",
                                      "Error", 
                                     JOptionPane.ERROR_MESSAGE);
                }else
                    new myXcurveJFrame(nx, txtfile, linewidth, linecolor);
                  }
            });
        designgridlayout.row().left().add(js5).fill().withOwnRowWidth();
        designgridlayout.row().left().add(js6).fill().withOwnRowWidth();
        designgridlayout.row().grid().grid().add(buttonOK);


        add(panel, BorderLayout.NORTH);


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

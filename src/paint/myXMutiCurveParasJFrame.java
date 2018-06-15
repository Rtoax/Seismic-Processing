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
//a##  myXMutiCurveParasJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXMutiCurveParasJFrame extends JFrame{

    private Toolkit toolkit;

    private Graphics2D g2d;

    private myXMultiCurveJPanel myXMultiCurveJPanel;

    private int nx ;
    private String[] txtfile ;
    private int[] linewidth;
    private int[] linecolor;

    private int nline = 2;

    private String tmptxtfile;
    private int tmplinewidth;
    private int tmplinecolor;

    private int countline = 0;

    private String text = "<p><b>Select Line File List</b></p>";



    private DesignGridLayout designgridlayout;

    private JSeparator js1 = new JSeparator();
    private JSeparator js2 = new JSeparator();
    private JSeparator js3 = new JSeparator();
    private JSeparator js4 = new JSeparator();
    private JSeparator js5 = new JSeparator();
    private JSeparator js6 = new JSeparator();



    public myXMutiCurveParasJFrame(){



        final FileDialog FileDialogSave;


        setTitle("Toa Curve");
        setSize(600, 720);
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
                        new myXMultiCurveAboutDialog();
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


        JLabel labelfile = new JLabel("File");
        labelfile.setFont(new Font("Georgia", Font.BOLD, 15));



        final JTextField textfieldfilename = new JTextField(20);
        textfieldfilename.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldfilename.setText("/home/");
        textfieldfilename.setEnabled(false);



        final JButton buttonfilename = new JButton("select file path");
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
                    tmptxtfile = file.toString();
                    System.out.println("txtfile: "+tmptxtfile);

                    }

                  }
            });

        buttonfilename.setEnabled(false);



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
        JFnx.setEnabled(false);

        String[] LW = {"--1--","--2--","--3--","--4--",
                    "--5--","--6--","--7--","--8--","--9--"};
        final JComboBox comboxLW = new JComboBox<String>(LW);
        comboxLW.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLW.setSelectedIndex(0);
        tmplinewidth = 1;
        comboxLW.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    tmplinewidth = index+1;
                    System.out.println("linewidth = "+tmplinewidth);
                    }
                  }
            });
        comboxLW.setEnabled(false);

        String[] LC = {"red    ","blue   ","green  ","black  "
                  ,"gray   ","yellow ","pink   ","cyan   "
                  ,"magenta","orange "};
        final JComboBox comboxLC = new JComboBox<String>(LC);
        comboxLC.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxLC.setSelectedIndex(0);
        tmplinecolor = 1;
        comboxLC.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    tmplinecolor = index+1;
                    System.out.println("linecolor = "+tmplinecolor);
                    }
                  }
            });
        comboxLC.setEnabled(false);


        final JButton buttonOK = new JButton("show the line");
        buttonOK.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                nx = Integer.parseInt(JFnx.getText()); 
                if(nline>countline){
                     JOptionPane.showMessageDialog(textfieldfilename, 
                                     "Please continue select file.",
                                     "Error", 
                                     JOptionPane.ERROR_MESSAGE);
                }else
                    new myXMultiCurveJFrame(nx, txtfile, linewidth, linecolor);
                  }
            });
        buttonOK.setEnabled(false);



        final JTextPane paneLineList = new JTextPane();
        paneLineList.setContentType("text/html");
        paneLineList.setFont(new Font("Georgia", Font.BOLD, 15));
        paneLineList.setText(text);
        paneLineList.setEditable(false);


        final JButton buttonsetline = new JButton(">LoadLine<");
        buttonsetline.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonsetline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                countline += 1;
                int cc = countline-1;


                if(cc<nline){
                    txtfile[cc] = tmptxtfile;
                    linewidth[cc] = tmplinewidth;
                    linecolor[cc] = tmplinecolor;
                    System.out.println("No."+(cc+1)+"  line "+
                            ",  linefile: "+txtfile[cc]+
                            ",  linewidth: "+linewidth[cc]+
                            ",  linecolor: "+linecolor[cc]);


                    text += "<p>.No."+cc+": "+"  File:"+txtfile[cc]
                              +", color:"+indexColor(linecolor[cc])
                                +", width:"+linewidth[cc]+".</p>";
                    paneLineList.setText(text);
                    }
                if(nline==countline)buttonOK.setEnabled(true);
                textfieldfilename.setText("/home/");
                setSize(getWidth(),getHeight()+30);
                  }
            });
        buttonsetline.setEnabled(false);



        String[] NL = {"null","2","3","4", "5","6","7","8","9", "10"};
        final JComboBox comboxNL = new JComboBox<String>(NL);
        comboxNL.setFont(new Font("Georgia", Font.BOLD, 15));
        comboxNL.setSelectedIndex(0);
        comboxNL.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    int index = combo.getSelectedIndex();
                    nline = index+1;
                    countline = 0;
                    System.out.println("nline = "+(index+1));

                    txtfile = new String[nline];
                    linecolor  = new int[nline];
                    linewidth  = new int[nline];

                    comboxLC.setEnabled(true);
                    buttonfilename.setEnabled(true);
                    textfieldfilename.setEnabled(true);
                    JFnx.setEnabled(true);
                    comboxLW.setEnabled(true);
                    buttonsetline.setEnabled(true);

                    if(nline==1){
                    comboxLC.setEnabled(false);
                    buttonfilename.setEnabled(false);
                    textfieldfilename.setEnabled(false);
                    JFnx.setEnabled(false);
                    comboxLW.setEnabled(false);
                    buttonsetline.setEnabled(false);
                          }
                    textfieldfilename.setText("/home/");
                    text = "<p><b>Select Line File List</b></p>";
                    paneLineList.setText(text);
                    buttonOK.setEnabled(false);
                    }
                  }
            });
        JLabel labellength = new JLabel("length");
        labellength.setFont(new Font("Georgia", Font.BOLD, 15));
        JLabel labelwidth = new JLabel("linewidth");
        labelwidth.setFont(new Font("Georgia", Font.BOLD, 15));
        JLabel labelcolor = new JLabel("linecolor");
        labelcolor.setFont(new Font("Georgia", Font.BOLD, 15));
        JLabel labelnline = new JLabel("NLine:");
        labelnline.setFont(new Font("Georgia", Font.BOLD, 15));

        //designgridlayout.row().left().add(js1).fill().withOwnRowWidth();
        //designgridlayout.row().center().add(titlecurve);
        designgridlayout.row().left().add(js2).fill().withOwnRowWidth();
        designgridlayout.row().grid(labelnline).add(comboxNL).grid().grid();
        designgridlayout.row().left().add(js3).fill().withOwnRowWidth();


        designgridlayout.row().grid(labelfile).add(textfieldfilename).add(buttonfilename);
        designgridlayout.row().left().add(js4).fill().withOwnRowWidth();


        designgridlayout.row().grid(labellength).add(JFnx)
                      .grid(labelwidth).add(comboxLW)
                      .grid(labelcolor).add(comboxLC);


        designgridlayout.row().left().add(js5).fill().withOwnRowWidth();
        designgridlayout.row().grid().add(buttonsetline).add(buttonOK);
        designgridlayout.row().left().add(js6).fill().withOwnRowWidth();

        designgridlayout.row().left().add(paneLineList);
        


        add(panel, BorderLayout.NORTH);



        pack();
        setVisible(true);
      }
    public String indexColor(int i){

        if(i==1)     return "red";
        else if(i==2)return "blue";
        else if(i==3)return "green";
        else if(i==4)return "blace";
        else if(i==5)return "gray";
        else if(i==6)return "yellow";
        else if(i==7)return "pink";
        else if(i==8)return "cyan";
        else if(i==9)return "magenta";
        else     return "orange";
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

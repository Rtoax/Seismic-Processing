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
//a##        myXimageParasJFrame -> the timage parameters basic frame
//a##
//a##            myXimageJFrame -> the frame include a image panel
//a##
//a##            myXimageJPanel -> the panel to paint
//a##
//a##        myXimageAboutDialog -> About dialog
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
//a##  myXimageParasJFrame to myXimageJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXimageParasJFrame extends JFrame{

    private Toolkit toolkit;
    private String filename;
    private String title;
    private int xbeg;
    private int nx;
    private int nz;
    private int minX,minZ,maxX,maxZ;

    private DesignGridLayout designgridlayout;

    private JSeparator js1 = new JSeparator();
    private JSeparator js2 = new JSeparator();
    private JSeparator js3 = new JSeparator();
    private JSeparator js4 = new JSeparator();
    private JSeparator js5 = new JSeparator();
    private JSeparator js6 = new JSeparator();

    public myXimageParasJFrame() {

        setTitle("ToaImage");
        setSize(600, 500);
        //setMaximumSize(new Dimension(350,300));

        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/4 - getWidth()/2, (size.height -getHeight())/2);


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
                        new myXimageAboutDialog();
                          }
                    });
                  }
            });
        JMenuItem close0 =new JMenuItem("Close");
        close0.setFont(new Font("Georgia", Font.BOLD, 15));
        menu.add(close0);
        close0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });


        menubar.add(menu);
        setJMenuBar(menubar);

/***************** set Label Title *****************/
        //JLabel labelTitle = new JLabel("Toa_Image",JLabel.CENTER);
        //labelTitle.setFont(new Font("Georgia", Font.BOLD, 20));



/***************** parameter panel *****************/
        final JPanel parasPanel = new JPanel();
        designgridlayout = new DesignGridLayout(parasPanel);



        //designgridlayout.row().left().add(js1).fill().withOwnRowWidth();
        //designgridlayout.row().center().add(labelTitle);
        designgridlayout.row().left().add(js2).fill().withOwnRowWidth();
        designgridlayout.row().left().add(js5).fill().withOwnRowWidth();



        /* title */
        JLabel labeltitle = new JLabel("Title: ");
        labeltitle.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField textfieldtitle = new JTextField(20);
        textfieldtitle.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldtitle.setText("change title...");







        /* filename */


        JLabel labelfilename = new JLabel(" File: ");
        labelfilename.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField textfieldfilename = new JTextField(20);
        textfieldfilename.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldfilename.setText("/home/");
        filename = "";


        JButton buttonfilename = new JButton("select file path");
        buttonfilename.setFont(new Font("Georgia", Font.BOLD, 15));
        buttonfilename.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileopen = new JFileChooser();
                FileFilter filterdat = new FileNameExtensionFilter( 
                                "*.dat", "dat");
                FileFilter filterbin = new FileNameExtensionFilter( 
                                "*.bin", "bin");
                FileFilter filterbinary = new FileNameExtensionFilter( 
                                "*.binary", "binary");
                fileopen.addChoosableFileFilter(filterdat);
                fileopen.addChoosableFileFilter(filterbin);
                fileopen.addChoosableFileFilter(filterbinary);

                int ret = fileopen.showDialog(parasPanel, "Select");

                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileopen.getSelectedFile();
                    textfieldfilename.setText(file.toString());
                    filename=file.toString();
                    System.out.println("FileName: "+filename);
                    }
                  }
            });
        designgridlayout.row().grid(labelfilename).add(textfieldfilename);
        designgridlayout.row().grid(labeltitle).add(textfieldtitle).add(buttonfilename);
        designgridlayout.row().left().add(js3).fill().withOwnRowWidth();



        /* xbeg */
        JLabel labelxbeg = new JLabel(" xbeg: ");
        labelxbeg.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField textfieldxbeg = new JTextField(20);
        textfieldxbeg.setHorizontalAlignment(JTextField.RIGHT);
        textfieldxbeg.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldxbeg.setText("0");

        designgridlayout.row().grid(labelxbeg).add(textfieldxbeg).add(new JLabel(""));




        /* for -> minX. minZ, maxX, maxZ  */
        final JTextField textfieldminX = new JTextField(9);
        final JTextField textfieldminZ = new JTextField(9);
        final JTextField textfieldmaxX = new JTextField(9);
        final JTextField textfieldmaxZ = new JTextField(9);
        textfieldminX.setHorizontalAlignment(JTextField.RIGHT);
        textfieldminZ.setHorizontalAlignment(JTextField.RIGHT);
        textfieldmaxX.setHorizontalAlignment(JTextField.RIGHT);
        textfieldmaxZ.setHorizontalAlignment(JTextField.RIGHT);


        /* nx, nz */
        JLabel labelnx = new JLabel("(nx,nz):");
        labelnx.setFont(new Font("Georgia", Font.BOLD, 15));

        final JTextField textfieldnx = new JTextField(9);
        textfieldnx.setHorizontalAlignment(JTextField.RIGHT);
        textfieldnx.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldnx.setText("200");
        textfieldnx.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                textfieldmaxX.setText(textfieldnx.getText());
                }
            @Override
            public void removeUpdate(DocumentEvent de) {
                textfieldmaxX.setText(textfieldnx.getText());
                }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //Plain text components don't fire these events.
                }
            });
        final JTextField textfieldnz = new JTextField(9);
        textfieldnz.setHorizontalAlignment(JTextField.RIGHT);
        textfieldnz.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldnz.setText("301");
        textfieldnz.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent de) {
                textfieldmaxZ.setText(textfieldnz.getText());
                }
            @Override
            public void removeUpdate(DocumentEvent de) {
                textfieldmaxZ.setText(textfieldnz.getText());
                }

            @Override
            public void changedUpdate(DocumentEvent de) {
                //Plain text components don't fire these events.
                }
            });

        designgridlayout.row().grid(labelnx).add(textfieldnx).add(textfieldnz);







        /* minX. minZ */
        JLabel labelXZ0 = new JLabel("beg:(x,z)");
        labelXZ0.setFont(new Font("Georgia", Font.BOLD, 15));

        /*minX*/

        textfieldminX.setFont(new Font("Georgia", Font.BOLD,15));
        textfieldminX.setText("0");

        /*minZ*/

        textfieldminZ.setFont(new Font("Georgia", Font.BOLD,15));
        textfieldminZ.setText("0");


        designgridlayout.row().grid(labelXZ0).add(textfieldminX).add(textfieldminZ);





        /*  maxX, maxZ */
        JLabel labelXZ1 = new JLabel("end:(x,z)");
        labelXZ1.setFont(new Font("Georgia", Font.BOLD, 15));

        /*maxX*/

        textfieldmaxX.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldmaxX.setText(textfieldnx.getText());

        /*maxZ*/

        textfieldmaxZ.setFont(new Font("Georgia", Font.BOLD, 15));
        textfieldmaxZ.setText(textfieldnz.getText());

        JButton buttonXZ1 = new JButton(">>>");
        buttonXZ1.setAlignmentX(1f);
        buttonXZ1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                System.out.println("maxX: "+textfieldmaxX.getText()
                          +", maxZ: "+textfieldmaxZ.getText());
                  }
            });

        designgridlayout.row().grid(labelXZ1).add(textfieldmaxX).add(textfieldmaxZ);



/******* all number parameter isNumeric Listener *********/
        /*textfieldminX*/
        textfieldminX.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(textfieldminX.getText()))
                    JOptionPane.showMessageDialog(textfieldminX, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*textfieldminZ*/
        textfieldminZ.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(textfieldminZ.getText()))
                    JOptionPane.showMessageDialog(textfieldminZ, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*textfieldmaxX*/
        textfieldmaxX.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(textfieldmaxX.getText()))
                    JOptionPane.showMessageDialog(textfieldmaxX, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                if(isNumeric(textfieldmaxX.getText())
                   &&Integer.parseInt(textfieldmaxX.getText())
                    >Integer.parseInt(textfieldnx.getText()))
                    JOptionPane.showMessageDialog(textfieldmaxX, 
                           " must < nx ",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*textfieldmaxZ*/
        textfieldmaxZ.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(textfieldmaxZ.getText()))
                    JOptionPane.showMessageDialog(textfieldmaxZ, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                if(isNumeric(textfieldmaxZ.getText())
                     &&Integer.parseInt(textfieldmaxZ.getText())
                    >Integer.parseInt(textfieldnz.getText()))
                    JOptionPane.showMessageDialog(textfieldmaxZ, 
                           " must < nz ",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*textfieldxbeg*/
        textfieldxbeg.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(textfieldxbeg.getText()))
                    JOptionPane.showMessageDialog(textfieldxbeg, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*textfieldnx*/
        textfieldnx.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(textfieldnx.getText()))
                    JOptionPane.showMessageDialog(textfieldnx, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });
        /*textfieldnz*/
        textfieldnz.getDocument().addDocumentListener (new DocumentListener() {  
            @Override  
            public void insertUpdate(DocumentEvent e) {  
                if(!isNumeric(textfieldnz.getText()))
                    JOptionPane.showMessageDialog(textfieldnz, 
                           "Input is not digital(0-9)!",
                           "Error", JOptionPane.ERROR_MESSAGE);
                }  
            @Override  
            public void removeUpdate(DocumentEvent e) {}   
            @Override  
            public void changedUpdate(DocumentEvent e) {}    
            });


/***************** panel for two buttons *****************/

        final JPanel twobuttonPanel = new JPanel();
        twobuttonPanel.setAlignmentX(1f);
        twobuttonPanel.setLayout(new BoxLayout(twobuttonPanel, BoxLayout.X_AXIS));
        twobuttonPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        /* OK run button */
        JButton ok = new JButton("show the binary");
        ok.setToolTipText("OK");
        ok.setFont(new Font("Georgia", Font.BOLD, 15));
        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                /** make sure don't have Blank  **/
                if(textfieldxbeg.getText().equals("")
                 ||textfieldnx.getText().equals("")
                 ||textfieldnz.getText().equals("")
                 ||textfieldminX.getText().equals("")
                 ||textfieldminZ.getText().equals("")
                 ||textfieldmaxX.getText().equals("")
                 ||textfieldmaxZ.getText().equals("")
                 ||textfieldfilename.getText().equals("")
                 ||textfieldtitle.getText().equals("")){
                    JOptionPane.showMessageDialog(textfieldxbeg, 
                                      "Please Fill Blank(s)!",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }else{

                    xbeg = Integer.parseInt(textfieldxbeg.getText());
                    nx = Integer.parseInt(textfieldnx.getText()); 
                    nz = Integer.parseInt(textfieldnz.getText());
                    minX = Integer.parseInt(textfieldminX.getText());
                    minZ = Integer.parseInt(textfieldminZ.getText());
                    maxX = Integer.parseInt(textfieldmaxX.getText());
                    maxZ = Integer.parseInt(textfieldmaxZ.getText());
                    filename = textfieldfilename.getText();
                    title = textfieldtitle.getText();

                    if(title.equals("change title...")){
                    int t = JOptionPane.showConfirmDialog(textfieldtitle, 
                                     "Blank Title (Y/N)?",
                                     "Warning",    
                                     JOptionPane.YES_NO_OPTION);
                    if(t==0){
                        title = " ";

                        if(filename.equals("/home/")){
                        JOptionPane.showMessageDialog(textfieldfilename, 
                                      "Please Select File.",
                                      "Error", 
                                     JOptionPane.ERROR_MESSAGE);
                        }else
                         new myXimageJFrame(nx,xbeg,nz,filename
                                   ,title,minX, minZ, maxX, maxZ);
                          }
                    }else{
                       if(filename.equals("/home/")){
                        JOptionPane.showMessageDialog(textfieldfilename, 
                                      "Please Select File.",
                                      "Error", 
                                     JOptionPane.ERROR_MESSAGE);
                        }else
                         new myXimageJFrame(nx,xbeg,nz,filename
                                   ,title,minX, minZ, maxX, maxZ);
                         }
                     }
                  }
            });

        /* close button */
        JButton close = new JButton("close the window");
        close.setToolTipText("close the window");
        close.setFont(new Font("Georgia", Font.BOLD, 15));
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
                  }
            });
        designgridlayout.row().left().add(js6).fill().withOwnRowWidth();
        designgridlayout.row().left().add(js4).fill().withOwnRowWidth();
        designgridlayout.row().grid().add(ok).add(close);


        add(parasPanel);



        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        //setResizable(false);
        pack();
      }

    private GridBagConstraints makeGbc(int ix, int iz) {
    /*Copyright: https://stackoverflow.com/questions/4699892/how-to-set-the-component-size-with-gridlayout-is-there-a-better-way*/
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.gridx = ix;
        gbc.gridy = iz;
        gbc.weightx = ix;
        gbc.weighty = 1.0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = (ix == 0) ? GridBagConstraints.LINE_START  
                       : GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
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

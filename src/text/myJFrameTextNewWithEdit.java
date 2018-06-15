package src.text;


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
//a##  myJFrameTextNewWithEdit to myJMenuBar @File->New
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myJFrameTextNewWithEdit extends JFrame {

    private File FileSave;

    private StyledDocument doc;

    private JTextPane textpane;

    private int FontSizeIndex = 1;

    private int FontIndex = 0;

    public myJFrameTextNewWithEdit() {


        final FileDialog FileDialogSave;

        setTitle("New Text With Edit ");
        setBounds(200, 200, 300, 200);

        /* Menu */
        JMenuBar JMenuBarSave;
        JMenu JMenuSave;
        JMenuItem JMenuItemSaveClose,JMenuItemSaveSave;

        JMenuBarSave=new JMenuBar();
        JMenuSave=new JMenu("File");
        JMenuItemSaveSave=new JMenuItem("Save");
        JMenuItemSaveClose=new JMenuItem("Close");
		
        JMenuSave.add(JMenuItemSaveSave);
        JMenuSave.add(JMenuItemSaveClose);
        JMenuBarSave.add(JMenuSave);


        FileDialogSave = new FileDialog(this, "Save", FileDialog.SAVE);

        setJMenuBar(JMenuBarSave);

        JMenuItemSaveClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });
        JMenuItemSaveSave.addActionListener(new ActionListener() {
			
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FileSave==null){
                    FileDialogSave.setVisible(true);
                    String dirPath=FileDialogSave.getDirectory();
                    String fileName=FileDialogSave.getFile();
                    System.out.println("Dir: "+dirPath+fileName);


                    if(dirPath==null && fileName==null){
                        return;
                          }
 
                    FileSave=new File(dirPath,fileName);

                    try {
                    BufferedWriter bufw=new BufferedWriter( 
                                 new FileWriter(FileSave));
                    bufw.write(textpane.getText());
                    bufw.close();
						
                    } catch (Exception e2) {
                    throw new RuntimeException("Save Error!");

                          }
                    }
                dispose();
				
                }
           });

        /* Tool Bar */
        /* Tool Bar */
        final JToolBar toolbar = new JToolBar();

        ImageIcon bold = new ImageIcon("picture/ButtonImg/fontbold16.png");
        ImageIcon italic = new ImageIcon("picture/ButtonImg/fontitalic16.png");
        ImageIcon strike = new ImageIcon("picture/ButtonImg/fontstrike16.png");
        ImageIcon underline = new ImageIcon("picture/ButtonImg/fontunderline16.png");

        JButton boldb = new JButton(bold);
        boldb.setToolTipText("Bold");
        JButton italb = new JButton(italic);
        italb.setToolTipText("Italic");
        JButton strib = new JButton(strike);
        strib.setToolTipText("Strike");
        JButton undeb = new JButton(underline);
        undeb.setToolTipText("UnderLine");

        toolbar.add(boldb);
        toolbar.add(italb);
        toolbar.add(strib);
        toolbar.add(undeb);




        /* Set Color */
        /* Set Color */
        ImageIcon fontColorButton = new ImageIcon("picture/ButtonImg/color16.png");
        JButton fontcolorbutton = new JButton(fontColorButton);
        fontcolorbutton.setMnemonic(KeyEvent.VK_C);
        fontcolorbutton.setToolTipText("Color");

        fontcolorbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JColorChooser clr = new JColorChooser();
                Color color = clr.showDialog(toolbar,  
                                 "Choose Color",Color.white);
                textpane.setForeground(color);

                  }
            });
        toolbar.add(fontcolorbutton);




        /* Set Font */
        /* Set Font */
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final String[] fonts = ge.getAvailableFontFamilyNames();
        final JComboBox fontcombobox = new JComboBox<String>(fonts);
        fontcombobox.setToolTipText("Font");
        fontcombobox.setSelectedIndex(3);
        fontcombobox.setPreferredSize(new Dimension(140, 30));
        fontcombobox.setMaximumSize(new Dimension(140, 30));

        fontcombobox.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    FontIndex = combo.getSelectedIndex();


                    textpane.setFont(new Font(fonts[FontIndex] 
                              ,Font.PLAIN 
                              ,(2*FontSizeIndex+10)));
                    }
                  }
            });

        toolbar.add(Box.createRigidArea(new Dimension(20, 0)));
        toolbar.add(fontcombobox);






        /* Text panel */
        /* Text panel */
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JScrollPane pane = new JScrollPane();

        textpane = new JTextPane();

        textpane.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        textpane.setFont(new Font(fonts[FontIndex],Font.PLAIN,(2*FontSizeIndex+10)));
        textpane.setFont(new Font("Georgia", Font.PLAIN, 16));

        doc = textpane.getStyledDocument();

        Style style = textpane.addStyle("Bold", null);
        StyleConstants.setBold(style, true);
        style = textpane.addStyle("Italic", null);
        StyleConstants.setItalic(style, true);
        style = textpane.addStyle("Underline", null);
        StyleConstants.setUnderline(style, true);
        style = textpane.addStyle("Strike", null);
        StyleConstants.setStrikeThrough(style, true);

        boldb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doc.setCharacterAttributes(textpane.getSelectionStart()
                               ,textpane.getSelectionEnd() -
                                textpane.getSelectionStart()
                               ,textpane.getStyle("Bold"), false);

                //textpane.requestFocus();
                }
            });
        italb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doc.setCharacterAttributes(textpane.getSelectionStart()
                               ,textpane.getSelectionEnd() -
                                textpane.getSelectionStart()
                               ,textpane.getStyle("Italic"), false);
                }
            });
        strib.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doc.setCharacterAttributes(textpane.getSelectionStart()
                               ,textpane.getSelectionEnd() -
                                textpane.getSelectionStart()
                               ,textpane.getStyle("Strike"), false);
                }
            });
        undeb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doc.setCharacterAttributes(textpane.getSelectionStart()
                               ,textpane.getSelectionEnd() -
                                textpane.getSelectionStart()
                            ,textpane.getStyle("Underline"), false);
                }
            });

        pane.getViewport().add(textpane);
        panel.add(pane);



        /* Font Size */
        /* Font Size */
        String[] strFontSize = {"10","12","14","16","18","20","22","24","26","28","30"};
        JComboBox FontSizeBox = new JComboBox<String>(strFontSize);
        FontSizeBox.setSelectedIndex(3);
        FontSizeBox.setPreferredSize(new Dimension(60, 30));
        FontSizeBox.setMaximumSize(new Dimension(60, 30));
        FontSizeBox.setToolTipText("Font Size");
        FontSizeBox.addItemListener(new ItemListener(){

            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JComboBox combo = (JComboBox) e.getSource();
                    FontSizeIndex = combo.getSelectedIndex();
                    System.out.println("mm = "+(2*FontSizeIndex+10));

                    textpane.setFont(new Font(fonts[FontIndex], 
                                 Font.PLAIN,(2*FontSizeIndex+10)));
                    }
                  }
            });
        toolbar.add(Box.createRigidArea(new Dimension(10, 0)));
        toolbar.add(FontSizeBox);






        add(toolbar, BorderLayout.NORTH);
        add(panel);


        setSize(new Dimension(600, 500));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
      }

}




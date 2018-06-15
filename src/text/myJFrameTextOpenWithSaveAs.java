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
//a##  myJFrameTextOpenWithSaveAs to myJMenuBar @File->Open
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myJFrameTextOpenWithSaveAs extends JFrame{

    private Toolkit toolkit;

    private File FileSave;

    private File FileSaveas;

    public myJFrameTextOpenWithSaveAs(JComponent parent){




        setSize(700,500);
        toolkit = getToolkit();
        final Dimension size = toolkit.getScreenSize();
        setLocation((size.width/2 - this.getWidth())/2,
                     (size.height -this.getHeight())/2);


        final FileDialog FileDialogSaveas;
        final JPanel JPanelTextOpen = new JPanel();

        final JTextArea JTextAreaOpen = new JTextArea();
        JTextAreaOpen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //JTextAreaOpen.setEditable(false);
        JTextAreaOpen.setFont(new Font("Serif",Font.PLAIN,20));


        JMenuBar JMenuBar;
        JMenu JMenuSave;
        JMenuItem JMenuItemClose,JMenuItemSave,JMenuItemSaveas;
        JMenuBar=new JMenuBar();
        JMenuSave=new JMenu("File");
        JMenuItemSave=new JMenuItem("Save...");
        JMenuItemSaveas=new JMenuItem("Save as...");
        JMenuItemClose=new JMenuItem("Close");
        JMenuSave.add(JMenuItemSave);
        JMenuSave.add(JMenuItemSaveas);


        JMenuSave.addSeparator();
        JMenuSave.add(JMenuItemClose);


        JMenuItemClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });

        FileDialogSaveas = new FileDialog(this, "Save as..", FileDialog.SAVE);


        JMenuBar.add(JMenuSave);
        setJMenuBar(JMenuBar);


        JPanelTextOpen.setLayout(new BorderLayout());

        JFileChooser fileopen = new JFileChooser();

        fileopen.setLocation((size.width/2 - fileopen.getWidth())/2, 
                            (size.height -fileopen.getHeight())/2);

        FileFilter filterc = new FileNameExtensionFilter("*.c", "c");
        FileFilter filtercu = new FileNameExtensionFilter("*.cu", "cu");
        FileFilter filtertxt = new FileNameExtensionFilter("*.txt", "txt");
        FileFilter filterjava = new FileNameExtensionFilter("*.java", "java");
        FileFilter filtersh = new FileNameExtensionFilter("*.sh", "sh");
        FileFilter filterpar = new FileNameExtensionFilter("*.par", "par");
        FileFilter filterh = new FileNameExtensionFilter("*.h", "h");

        fileopen.addChoosableFileFilter(filterc);
        fileopen.addChoosableFileFilter(filtercu);
        fileopen.addChoosableFileFilter(filtertxt);
        fileopen.addChoosableFileFilter(filterjava);
        fileopen.addChoosableFileFilter(filtersh);
        fileopen.addChoosableFileFilter(filterpar);
        fileopen.addChoosableFileFilter(filterh);


        int ret = fileopen.showDialog(parent, "Open");

        if (ret == JFileChooser.APPROVE_OPTION) {

            File file = fileopen.getSelectedFile();

            FileSave = file;

            String text = readFile(file);
            JTextAreaOpen.setText(text);
            System.out.println(file);


            setTitle(file.toString());
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setVisible(true);
            }

        JScrollPane JScrollPaneOpen = new JScrollPane();

        JScrollPaneOpen.getViewport().add(JTextAreaOpen);

        JPanelTextOpen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanelTextOpen.add(JScrollPaneOpen);



        JMenuItemSave.addActionListener(new ActionListener() {
			
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FileSaveas==null){

                    try {
                    BufferedWriter bufw=new BufferedWriter( 
                                new FileWriter(FileSave));
                    bufw.write(JTextAreaOpen.getText());
                    bufw.close();
						
                    } catch (Exception e2) {
                    throw new RuntimeException("Save Error!");

                          }
                    }
                //dispose();
				
                }
           });

        JMenuItemSaveas.addActionListener(new ActionListener() {
			
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FileSaveas==null){
                    FileDialogSaveas.setVisible(true);
                    String dirPath=FileDialogSaveas.getDirectory();
                    String fileName=FileDialogSaveas.getFile();
                    System.out.println("Dir: "+dirPath+fileName);


                    if(dirPath==null && fileName==null){
                        return;
                          }
 
                    FileSaveas=new File(dirPath,fileName);

                    try {
                    BufferedWriter bufw=new BufferedWriter( 
                                new FileWriter(FileSaveas));
                    bufw.write(JTextAreaOpen.getText());
                    bufw.close();
						
                    } catch (Exception e2) {
                    throw new RuntimeException("Save Error!");

                          }
                    }
                //dispose();
				
                }
           });




        add(JPanelTextOpen);
      }

    public String readFile (File file) {

        StringBuffer fileBuffer = null;
        String fileString = null;
        String line = null;

        try {
            FileReader in = new FileReader (file);
            BufferedReader brd = new BufferedReader (in);
            fileBuffer = new StringBuffer() ;
            while ((line = brd.readLine()) != null) {
                fileBuffer.append(line +
                System.getProperty("line.separator"));
                  }
            in.close();
            fileString = fileBuffer.toString();
        }catch (IOException e ) {
            return null;
            }
        return fileString;
      }
}





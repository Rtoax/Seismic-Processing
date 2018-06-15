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
//a##  myLaganRayTracingJFrame to myLaganRayTracingParasJFrame
//a##
//a########################################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myLaganRayTracingJFrame extends JFrame{

    private Toolkit toolkit;

    private myLaganRayTracingJPanel mylaganraytracingJPanel;

    public myLaganRayTracingJFrame(int nx,int nz, int sx,int sz,float dx,float dz,
                float s,String FNvel,
                int nray, float fangle,float dangle,
                int raypathcolor,int raypathwidth,
                String raypathtext, boolean readvel, float constvel)
                throws java.io.IOException{

        setTitle("Lagan Raytracing use <"+FNvel
                +"> as background velocity.Copyright@Rong Tao");
        setSize(500, 600);
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
        JMenuItemSaveSave=new JMenuItem("Save as PNG Image");
        JMenuItemSaveSave.setFont(new Font("Georgia", Font.BOLD, 15));
        JMenuItemSaveClose=new JMenuItem("Close");
        JMenuItemSaveClose.setFont(new Font("Georgia", Font.BOLD, 15));

        JMenuSave.add(JMenuItemSaveSave);
        JMenuSave.add(JMenuItemSaveClose);
        JMenuBarSave.add(JMenuSave);

        setJMenuBar(JMenuBarSave);


        mylaganraytracingJPanel = new myLaganRayTracingJPanel(
                         nx,nz, sx,sz,dx,dz,s,FNvel,nray,
                         fangle,dangle,raypathcolor,
                         raypathwidth,raypathtext,
                         readvel,constvel);

        JMenuItemSaveClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });
        JMenuItemSaveSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJPanel.saveImage(mylaganraytracingJPanel, JMenuSave);
                  }
           });
        add(mylaganraytracingJPanel);
        setVisible(true);
      }
}

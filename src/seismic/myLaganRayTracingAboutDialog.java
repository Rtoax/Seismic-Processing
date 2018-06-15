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
//a##  myLaganRayTracingAboutDialog to myJMenuBar
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myLaganRayTracingAboutDialog extends JDialog {

    private Toolkit toolkit;

    private int itext =0 ;

    public myLaganRayTracingAboutDialog() {

        setTitle("About Information ");
        setSize(300, 350);
        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width/2 - getWidth())/2, (size.height -getHeight())/2);

        JPanel basic = new JPanel();
        basic.setLayout(new BoxLayout(basic, BoxLayout.Y_AXIS));
        add(basic);

        JPanel topPanel = new JPanel(new BorderLayout(0, 0));
        topPanel.setMaximumSize(new Dimension(450, 0));

        JLabel hint = new JLabel("About Lagan RayTracing");
        hint.setBorder(BorderFactory.createEmptyBorder(0, 25, 0, 0));
        topPanel.add(hint);

        ImageIcon icon = new ImageIcon("picture/ButtonImg/traytracingLG32.png");
        JLabel label = new JLabel(icon);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        topPanel.add(label, BorderLayout.EAST);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.gray);
        topPanel.add(separator, BorderLayout.SOUTH);

        basic.add(topPanel);

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        final JTextPane pane = new JTextPane();
        pane.setContentType("text/html");
        final String[] text ={ "<p><b>About The TLaganRayTracing</b></p>" +
                "<p>. Author: Rong Tao</p>" +
                "<p>. Time: 2017.7 </p>"+
                "<p>. @Copyright Rong Tao </p>"+
                "<p>. Location: @UPC </p>",

                "<p><b></b></p>" +
                "<p>.   You just change the velocity,</p>" +
                "<p>. or use constant velocity.  </p>"+
                "<p>.   This is a constant Paras. </p>"+
                "<p>.   </p>"+
                "<p>.   </p>",

                "<p><b>Use It Stydy</b></p>" +
                "<p>.   You can see the raytracing path.</p>" +
                "<p>. after click OK button.</p>"+
                "<p>.   You can change the shot location.</p>"+
                "<p>.  </p>",

                "<p><b>Hope you enjoy it.</b></p>" +
                "<p>. Learning makes me happy.</p>" +
                "<p>. I like fitness.</p>"+
                "<p>. Wish me Luck.</p>"+
                "<p>. Good Luck! </p>"};

        pane.setText(text[itext]);
        pane.setEditable(false);
        textPanel.add(pane);

        basic.add(textPanel);

        JPanel boxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));

        basic.add(boxPanel);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton previous = new JButton("<<-Previous");

        previous.setMnemonic(KeyEvent.VK_N);
        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(itext>0&&itext<=3)pane.setText(text[--itext]);
                  }
            });
        JButton next = new JButton("Next->>");

        next.setMnemonic(KeyEvent.VK_N);
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(itext>=0&&itext<3)pane.setText(text[++itext]);
                  }
            });
        JButton close = new JButton("Close");
        close.setMnemonic(KeyEvent.VK_C);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });
        bottom.add(previous);
        bottom.add(next);
        bottom.add(close);

        basic.add(bottom);
        bottom.setMaximumSize(new Dimension(300, 0));

        setResizable(false);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
      }
}



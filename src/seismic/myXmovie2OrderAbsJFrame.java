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


//a#############################################################################################
//a##
//a##        myXmovie2OrderAbsParasJFrame -> the tmovie parameters basic frame
//a##
//a##            myXmovie2OrderAbsJFrame -> the frame include a movie panel
//a##               innerClass: myXmovie2OrderAbsJPanel -> the panel to paint
//a##                      innerClass: Wavelet,Wave,vtiFD,vtiModel
//a##
//a##        myXmovie2OrderAbsAboutDialog -> show the about of the Tmovie
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
//a##  myXmovie2OrderAbsJFrame
//a##
//a#################################
/**
 * 
 *       Author: Rong Tao
 *     Location: UPC
 *         Time: 2017.04
 *    Modify by: Rong Tao
 */
public class myXmovie2OrderAbsJFrame extends JFrame{

    private Toolkit toolkit;

    private myXmovie2OrderAbsJPanel myxmovie2OrderAbsJPanel;

    private File FileSave;

    private int it = -1;

    private JProgressBar progressBar;
    private ActionListener updateProBar;

    Timer timer;
    Timer ntimer;

    public myXmovie2OrderAbsJFrame(float velocity, float epsilon, float delta, 
               int SX, int SZ, int NX, int NZ, float DX,float DZ,
               int NT, float DT, float FM, int MM, int npd,
            String filev, String filee, String filed,
            boolean flag_SelectFile, boolean flag_VTI){


        final FileDialog FileDialogSave;


        setTitle("Toa_Xmovie");
        setSize(300, 360);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                timer.stop();
                ntimer.stop();
                  }
            });

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




        /* toolbar */
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,1));
        final JToolBar toolbar0 = new JToolBar();

        final JLabel ntime = new JLabel("0");
        ntime.setFont(new Font("Georgia", Font.BOLD, 15));

        final int ntit = NT;
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        updateProBar = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int val = progressBar.getValue();
                if (it >= 0) {
                    ntime.setText(Integer.toString(it+1)+"/"+Integer.toString(ntit));
                    progressBar.setValue((int)(it*1f/ntit*100f)+1);
                    }
                if(it == ntit-1){
                    JOptionPane.showMessageDialog(panel, "Complete!",
                        "Info VTI snapshot", JOptionPane.INFORMATION_MESSAGE);
                    ntimer.stop();
                    }
                  }
            };
        ntimer = new Timer(100, updateProBar);
        ntimer.start();

        JLabel JLit = new JLabel("it:");
        JLit.setFont(new Font("Georgia", Font.BOLD, 15));
        toolbar0.add(JLit);
        toolbar0.add(progressBar);
        toolbar0.add(ntime);

        panel.add(toolbar0);

        toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation((size.width/2 - getWidth())/2, (size.height - getWidth())/2);

        /* xmovie show panel in xmovieJFrame */
        myxmovie2OrderAbsJPanel = new myXmovie2OrderAbsJPanel(velocity,epsilon,delta,
                            SX, SZ, NX, NZ, DX,DZ,NT, DT, FM, MM,npd,
                            filev,filee,filed,flag_SelectFile,flag_VTI);


        JMenuItemSaveClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                  }
            });
        JMenuItemSaveSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveJPanel.saveImage(myxmovie2OrderAbsJPanel, JMenuSave);
                  }
           });


        add(panel, BorderLayout.NORTH);
        add(myxmovie2OrderAbsJPanel);


        setVisible(true);
      }
    //a#################################
    //a##
    //a##  myXmovie2OrderAbsJPanel for myXmovie2OrderAbsJFrame
    //a##
    //a#################################
    class myXmovie2OrderAbsJPanel extends JPanel {


        private int NX,NZ;
        private int SX,SZ;
        private float DX,DZ;
        private int NT;
        private float DT;
        private float FM;
        private int MM;

        private float[] Image;

        private float imax, imin;

        private int npd;


        ActionListener go;

        myXmovie2OrderAbsJPanel(float velocity, float epsilon, float delta,
                   int SX, int SZ, int NX, int NZ, float DX,float DZ,
                   int NT, float DT, float FM, int MM, int npd,
                String filev, String filee, String filed,
                boolean flag_SelectFile, boolean flag_VTI){

            this.NX = NX;
            this.NZ = NZ;
            this.SX = SX;
            this.SZ = SZ;
            this.DX = DX;   
            this.DZ = DZ;
            this.NT = NT;
            this.DT = DT;
            this.FM = FM;
            this.MM = MM;
            this.npd = npd;

            final int nx = NX; 
            final int nz = NZ;
            final int sx = SX; 
            final int sz = SZ;
            final float dx = DX;
            final float dz = DZ;
            final int nt = NT;
            final float dt = DT;
            final float fm = FM;
            final int mm = MM;


            Image = new float[nx*nz];

            /* initial the wave(P0,P1,Q0,Q1) */
            final Wave wave = new Wave(nx, nz);
            wave.Init_Wave();
       

            /* get the wavelet<Gaussian or Ricker> */
            Wavelet wavelet = new Wavelet(nt,dt, fm); 
            wavelet.getWavelet(2);/* 1:Ricker;2:Gaussian */

            /* Model (int nx,int nz,float dx,float dz); */
            vtiModel model0 = new vtiModel(nx,nz,dx,dz);

            if(flag_VTI)
                model0.InitModel(velocity, epsilon, delta);
            else
                model0.InitModel(velocity, 0f, 0f);


            if(flag_SelectFile){
                if(flag_VTI)
                    model0.ReadVTIModel(filev,filee,filed);
                else    model0.ReadISOModel(filev);
                  }


            final vtiFD vtifd = new vtiFD(model0, wave, wavelet);

            go = new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    it++;
                    Image = vtifd.FD(sx,sz,mm,it); 

                    getMaxMin();
                    repaint();
                    if(it>=nt-1)timer.stop();
                      }
                };
            timer = new Timer(10, go);
            timer.start();
            }
        /* get min & max */
        public void getMaxMin(){
            imax = imin = Image[0];
            for(int i=0;i<NX*NZ;i++){

                if(imin>Image[i])imin=Image[i];
                if(imax<Image[i])imax=Image[i];
                }
            }
        /* paint */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.blue);



            Dimension size = getSize();
            Insets insets = getInsets();

            int[] rgb = new int[3];
            float radiox = (float)(getWidth())/(float)(NX);
            float radioz = (float)(getHeight())/(float)(NZ);

            for (int ix = 0; ix<NX; ix ++)
            for (int iz = 0; iz<NZ; iz ++){

                int i = ix*NZ+iz;

                /* gray */
                rgb[0] = rgb[1] = rgb[2] 
                       = (int)(( 1.0f*(255-(Image[i]-imin)*255.0f/(imax-imin)) )%255);

                int R = (int)( rgb[0] );
                int G = (int)( rgb[1] );
                int B = (int)( rgb[2] );

                g2d.setColor(new Color(R, G, B) );



                int drawx = (int)((float)(ix)*radiox);
                int drawz = (int)((float)(iz)*radioz);

                for(int j=0;j<=(int)(radiox);j++)
                    for(int k=0;k<=(int)(radioz);k++)
                        g2d.drawLine(drawx+j, drawz+k, drawx+j, drawz+k);



                }

            g2d.setColor(Color.RED);
            g2d.drawLine((int)(npd*radiox),(int)(npd*radioz),
                     (int)((NX-npd)*radiox),(int)(npd*radioz));
            g2d.drawLine((int)(npd*radiox),(int)((NZ-npd)*radioz),
                     (int)((NX-npd)*radiox),(int)((NZ-npd)*radioz));

            g2d.drawLine((int)(npd*radiox),(int)(npd*radioz),
                     (int)(npd*radiox),(int)((NZ-npd)*radioz));
            g2d.drawLine((int)((NX-npd)*radiox),(int)(npd*radioz),
                     (int)((NX-npd)*radiox),(int)((NZ-npd)*radioz));

            }

        //a#################################
        //a##
        //a##  inner class Wavelet for fd
        //a##
        //a#################################
        class Wavelet{

            private float pi = 3.141592653f;
            public int nt;
            public float dt, fm;
            public float[] wlt;
            public Wavelet(int nt, float dt, float fm){
                this.nt = nt;
                this.dt = dt;
                this.fm = fm;
            }//end
            /* ricker wavelet */
            public void getWavelet(int wtype){
                wlt = new float[nt];
                for(int i = 0; i<nt;i++)wlt[i]=0.0f;
                int it;
                float tdelay, t, ts, x, xx;
                for(t = 0.0f, it = 0; it < nt ; t = (++it)*dt){
                    tdelay = 1.0f/fm;
                    ts = t - tdelay;
                    if(wtype==1&&t <= 2*tdelay){

                        x = fm * ts;
                        xx = x * x;
                        wlt[it] = (float)((1-2*pi*pi*xx)
                                *Math.exp(-(pi*pi*xx)));
                    }//end if
                    if(wtype==2&&t <= 2*tdelay){

		                x = (float)((-4.0f)*(fm*fm*pi*pi)
                                /Math.log(0.1));
		                wlt[it] = (float)((-2.0f)*(pi*pi*ts)
                                   *Math.exp(-x*ts*ts));
                    }//end if
                }//end for
            }//end
            }
        //a#################################
        //a##
        //a##  inner class Wave for fd
        //a##
        //a#################################
        class Wave{

            public int nx,nz;
            public float[] P0;
            public float[] P1;
            public float[] Q0;
            public float[] Q1;
            public Wave(int nx,int nz){
                this.nx = nx;
                this.nz = nz;
                }
            public void Init_Wave(){
                P0 = new float[(nx)*(nz)];
                P1 = new float[(nx)*(nz)];
                Q0 = new float[(nx)*(nz)];
                Q1 = new float[(nx)*(nz)];
                for(int i = 0; i < (nx)*(nz); i++){
                    P0[i] = 0.0f;
                    P1[i] = 0.0f;
                    Q0[i] = 0.0f;
                    Q1[i] = 0.0f;
                }//end
            }//initial Wave
            }
        //a#################################
        //a##
        //a##  inner class vtiFD 
        //a##
        //a#################################
        class vtiFD {
            private float[]
               stencil = {-205.0f/72.0f,8.0f/5.0f,-1.0f/5.0f,8.0f/315.0f,-1.0f/560.0f};
            private float pi = 3.141592653f;
            vtiModel model;
            Wave wave;
            Wavelet wavelet;
            public vtiFD(vtiModel model, Wave wave, Wavelet wavelet){
                this.model = model;
                this.wave = wave;
                this.wavelet = wavelet;
            }//end struct function
            public float[] FD(int sx, int sz, int mm, int it){
                float dt2x2 = wavelet.dt*wavelet.dt/(model.dx*model.dx);
                float dt2z2 = wavelet.dt*wavelet.dt/(model.dz*model.dz);
                int i, j, id, im;
                float xx,zz,flag;

                    if(it%50==0)System.out.println("Time: "+(it*wavelet.dt)+" (s)");
                    addSource(wave, wavelet, sx, sz, it);
                    for(i=mm; i<(model.nx-mm); i++) {
                        for(j=mm;j<(model.nz-mm); j++) { 
                            id = i * model.nz + j;
                            xx = stencil[0] * wave.P1[id];
                            zz = stencil[0] * wave.Q1[id];
                            for(im=1;im<=mm;im++) {
                   xx += stencil[im]*(wave.P1[id+im * model.nz]+wave.P1[id-im * model.nz]);
                   zz += stencil[im]*(wave.Q1[id+im]+wave.Q1[id-im]);
                            }//end
                            xx *= dt2x2;
                            zz *= dt2z2;
                   wave.P0[id] =( 2.0f*wave.P1[id] - wave.P0[id] 
                            + xx*model.velocity[id]
                                *model.velocity[id]
                                *(1.0f+2.0f*model.epsilon[id]) 
                            + zz*model.velocity[id]*model.velocity[id] );
                   wave.Q0[id] =( 2.0f*wave.Q1[id] - wave.Q0[id] 
                            + xx*model.velocity[id]
                                *model.velocity[id]
                                *(1.0f+2.0f* model.delta [id]) 
                            + zz*model.velocity[id]*model.velocity[id] );
                        }//end j
                    }//end i
                buffer(wave);
                absorbBndr(wave,-0.25f);
                return wave.P0;
            }//end FD
            public void buffer(Wave wave){
                float tmp;        
                for(int l =0 ;l<model.nx*model.nz; l++){
                    tmp = wave.P1[l]; wave.P1[l] = wave.P0[l]; wave.P0[l] = tmp;
                    tmp = wave.Q1[l]; wave.Q1[l] = wave.Q0[l]; wave.Q0[l] = tmp;
                }//end for
            }//end
            public void addSource(Wave wave, Wavelet wavelet, int sx, int sz, int it){
                wave.P0[(sx-1)*(wave.nz)+sz-1] += wavelet.wlt[it];
                wave.Q0[(sx-1)*(wave.nz)+sz-1] += wavelet.wlt[it];
            }//end
            public void absorbBndr(Wave wave,float qp){
                int ix,iz,id;
                for(ix=0;ix<wave.nx;ix++){
                    for(iz=0;iz<wave.nz;iz++){
                        id = ix*(wave.nz) + iz;
                        if(ix<npd){
                   wave.P0[id]=(float)( qp*Math.pow((npd-ix)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P0[id];
                   wave.P1[id]=(float)( qp*Math.pow((npd-ix)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P1[id];
                   wave.Q0[id]=(float)( qp*Math.pow((npd-ix)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q0[id];
                   wave.Q1[id]=(float)( qp*Math.pow((npd-ix)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q1[id];
                        }else if(ix>=wave.nx-npd){
                   wave.P0[id]=(float)( qp*Math.pow((ix+npd-wave.nx)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P0[id];
                   wave.P1[id]=(float)( qp*Math.pow((ix+npd-wave.nx)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P1[id];
                   wave.Q0[id]=(float)( qp*Math.pow((ix+npd-wave.nx)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q0[id];
                   wave.Q1[id]=(float)( qp*Math.pow((ix+npd-wave.nx)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q1[id];
                        }if(iz<npd){
                   wave.P0[id]=(float)( qp*Math.pow((npd-iz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P0[id];
                   wave.P1[id]=(float)( qp*Math.pow((npd-iz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P1[id];
                   wave.Q0[id]=(float)( qp*Math.pow((npd-iz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q0[id];
                   wave.Q1[id]=(float)( qp*Math.pow((npd-iz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q1[id];
                        }else if(iz>=wave.nz-npd){
                   wave.P0[id]=(float)( qp*Math.pow((iz+npd-wave.nz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P0[id];
                   wave.P1[id]=(float)( qp*Math.pow((iz+npd-wave.nz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.P1[id];
                   wave.Q0[id]=(float)( qp*Math.pow((iz+npd-wave.nz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q0[id];
                   wave.Q1[id]=(float)( qp*Math.pow((iz+npd-wave.nz)
                            /(1.0f*npd),2.0f) + 1.0f )*wave.Q1[id];
                        }//if
                    }//iz
                }//ix
            }//end
            }
        //a#################################
        //a##
        //a##  inner class vtiModel for fd
        //a##
        //a#################################
        /* vtiModel */
        class vtiModel{
            public int nx;
            public int nz;
            public float dx;
            public float dz;
            public float[] velocity, epsilon, delta;
            public vtiModel(int nx,int nz,float dx,float dz){
                this.nx=nx;
                this.nz=nz;
                this.dx=dx;
                this.dz=dz;
            }//struct function ending
            public void InitModel(float v, float e, float d){
                velocity = new float[nx*nz];
                 epsilon = new float[nx*nz];
                   delta = new float[nx*nz];
                for(int i = 0; i < nx*nz; i++) {
                    velocity[i] = v;
                     epsilon[i] = e;
                       delta[i] = d;
                    }
            }//initial the vtiModel


            public void ReadVTIModel(String FileNameOfVelocity, 
                          String FileNameOfEpsilon, 
                          String FileNameOfDelta){

                DataInputStream fpVelocity = null, fpEpsilon = null, fpDelta = null;
                try{    
                    if(!new File(FileNameOfVelocity).exists()){  
                        System.out.println("The "+FileNameOfVelocity
                                  +" file dont't exists");
                        return;  
                          } 
                    if(!new File(FileNameOfEpsilon).exists()){  
                        System.out.println("The "+FileNameOfEpsilon
                                  +" file dont't exists");
                        return;  
                          } 
                    if(!new File(FileNameOfDelta).exists()){  
                        System.out.println("The "+FileNameOfDelta
                                  +" file dont't exists");
                        return;  
                          } 
                    fpVelocity = new DataInputStream(
                            new FileInputStream(
                              new File(FileNameOfVelocity)));
                     fpEpsilon = new DataInputStream(
                            new FileInputStream(
                              new File(FileNameOfEpsilon)));
                       fpDelta = new DataInputStream(
                            new FileInputStream(
                              new File(FileNameOfDelta))); 
                    int i = 0, j =0 , k =0 ;
                    while(fpVelocity.available()>0
                        &&fpEpsilon.available()>0
                        &&fpDelta.available()>0
                        &&i<nx*nz&&j<nx*nz&&k<nx*nz){   
                        velocity[i++] = fpVelocity.readFloat(); 
                         epsilon[j++] =  fpEpsilon.readFloat(); 
                           delta[k++] =    fpDelta.readFloat();  
                        velocity[i-1] = swap(velocity[i-1]);
                         epsilon[j-1] = swap( epsilon[j-1]);
                           delta[k-1] = swap(   delta[k-1]);
                          } 
                }catch(Exception e){  
                    e.printStackTrace();  
                    }
            }//read vtiModel


            public void ReadISOModel(String FileNameOfVelocity){

                DataInputStream fpVelocity = null;
                try{    
                    if(!new File(FileNameOfVelocity).exists()){  
                        System.out.println("The "+FileNameOfVelocity
                                  +" file dont't exists");
                        return;  
                          } 
                    fpVelocity = new DataInputStream(
                            new FileInputStream(
                              new File(FileNameOfVelocity)));
                    int i = 0;
                    while(fpVelocity.available()>0
                        &&i<nx*nz){   
                        velocity[i++] = fpVelocity.readFloat();  
                        velocity[i-1] = swap(velocity[i-1]);
                          } 
                }catch(Exception e){  
                    e.printStackTrace();  
                    }
            }//read isoModel

            public float swap (float value){
                int intValue = Float.floatToRawIntBits (value);
                intValue = swap (intValue);
                return Float.intBitsToFloat (intValue);
                  }
            public int swap (int value){
                int b1 = (value >>  0) & 0xff;
                int b2 = (value >>  8) & 0xff;
                int b3 = (value >> 16) & 0xff;
                int b4 = (value >> 24) & 0xff;
                return b1 << 24 | b2 << 16 | b3 << 8 | b4 << 0;
                }
            }
      }

}


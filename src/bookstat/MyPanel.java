/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author giacomo
 */
public class MyPanel extends JPanel{
    
    private final int w;
    private final int h;
    private final int pxFromBottom;
    private Graphics2D g2;
    
    private double s1[] = null;
    private double s2[] = null;
    
    public MyPanel(int width, int height, double[] stats1, double[] stats2) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        this.w = width;
        this.h = height;
        this.pxFromBottom = (int) ((h/100)*6);   
        
        this.s1 = stats1;
        this.s2 = stats2;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.w,this.h);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        this.g2 = (Graphics2D) g;        
        this.drawAxis(Color.red, 3);
        
        // se non viene passato una seconda lista valida, poiche' l'utente non ha selezionato un secondo file, bisogna disegnare solo
        // le statistiche relative al primo file, altrimenti ad entrambi
        
        if (s2 == null) {
            this.drawBars(this.s1);
        }
        else {
            this.drawBars(this.s1, this.s2);
        }
        
    }
    
    private void drawAxis (Color c, int lineWidth) {        
        g2.setColor(c);
        g2.setStroke(new BasicStroke(lineWidth));
        // x axis
        g2.drawLine(x(- w/2), y(0), x(w/2), y(0));
        //arrows
        g2.drawLine(x(w/2), y(0), w - (w*5)/100, y(0) + (h*2)/100);
        g2.drawLine(x(w/2), y(0), w - (w*5)/100 , y(0) - (h*2)/100);
    }
    
    private void drawBar (int x, int y, int width, int height, Color color) {
        g2.setColor(color);
        g2.fillRect(x, y, width, height);
    }
    
    private void drawSrting (String s, int x, int y, int width) {
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Liberation Mono", Font.PLAIN, width));        
        g2.drawString(s, x, y);
    }
    
    public void drawBars (double[] v) {
        // bars: number of bars
        int bars = v.length; 
        if (bars * 4 > w) {
            JOptionPane.showMessageDialog(this.getParent(), "Too many bars!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        else {
            // bw: bar width
            // accorcio il valore di un po' per rendere visibili le frecce dell'asse x
            int bw = (int) (w - bars - (w*5)/100) / bars;
            
            // inizializzo letter a 97 (che equivale al carattere 'a')
            int letter = 97;
            
            for (int i = 0; i < bars; i++) {
                // uly: upper left y: 
                // indica il valore della y nel classico sistema cartesiano con O al centro della finestra
                int uly;
                
                //uly = (int) (v[i] * (h/2 / 100));
                uly = (int) (v[i] * ((h - pxFromBottom) / 100));
                
                // disegno la barra
                this.drawBar(i*(bw+1), y(uly), bw, uly, Color.BLUE);
                // disegno la lettera corrsipondente alla barra
                this.drawSrting(String.valueOf(Character.toChars(letter)), (int) (i*(bw+1) + bw/4), (int) y((-pxFromBottom/2)-(h/100*2)), (int) (w/100)*3);
                letter ++;
                
                // disegno i separatori
                g2.setColor(Color.BLACK);
                g2.drawLine(i*(bw+1), y(0), i*(bw+1), y(-pxFromBottom));
            }
        }        
    }
        
    public void drawBars (double[] v1, double[] v2) {
        double[] t = new double[v1.length + v2.length];

        for (int i = 0; i < v1.length; i++) {
            t[i*2] = v1[i];
            t[i*2+1] = v2[i];
        }
        
        // bars: number of bars
        int bars = t.length; 
        if (bars * 4 > w) {
            System.out.println("ERRORE: Troppe barre.");
        }
        else {
            // bw: bar width
            // accorcio il valore di un po' per rendere visibili le frecce dell'asse x
            int bw = (int) (w - bars - (w*5)/100) / bars;
            
            // inizializzo letter a 97 (che equivale al carattere 'a')
            int letter = 97;
            
            for (int i = 0; i < bars; i++) {
                // uly: upper left y: 
                // indica il valore della y nel classico sistema cartesiano con O al centro della finestra
                int uly;
                
                //uly = (int) (v[i] * (h/2 / 100));
                uly = (int) (t[i] * ((h - pxFromBottom) / 100));
                
                // disegno la barra
                Color col;
                if (i%2 == 0) {
                    col = Color.BLUE;
                }
                else {
                    col = Color.RED;
                }
                this.drawBar(i*(bw+1), y(uly), bw, uly, col);
                // disegno la lettera corrsipondente alla barra
                if (i%2 == 0) {
                    this.drawSrting(String.valueOf(Character.toChars(letter)),(int) i*(bw+1) + (bw/2), (int) y((-pxFromBottom/2)-(h/100*2)), (int) (w/100)*3);
                    letter ++;
                    
                    g2.setColor(Color.BLACK);
                    g2.drawLine(i*(bw+1), y(0), i*(bw+1), y(-pxFromBottom));
                }                
            }
        }        
    }
    
    
    // ritorna le coordinate x nel nostro sistema di riferimento cartesiano
    private int x (int x) {
        return (w/2 + x);
    }
    
    // ritorna le coordinate y nel nostro sistema di riferimento cartesiano
    private int y (int y) {
        //return (h/2 - y);
        return (h - pxFromBottom - y);
    }
    
}

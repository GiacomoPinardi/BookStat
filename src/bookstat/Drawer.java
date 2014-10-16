/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookstat;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author giacomo
 */
public class Drawer {
    
    GraphicsContext cxt;
    double w, h;
    double pxFromBottom;
    
    public Drawer (GraphicsContext c, double axisYPxFromBottom) {
        this.cxt = c;
        this.w = cxt.getCanvas().getWidth();
        this.h = cxt.getCanvas().getHeight();
        this.pxFromBottom = axisYPxFromBottom;
    }
    
    public void drawAxis (Color c, double lineWidth) {
        
        cxt.setStroke(c);
        cxt.setLineWidth(lineWidth);
        // axis
        cxt.strokeLine(x(- w/2), y(0), x(w/2), y(0));
        //arrows
        cxt.strokeLine(x(w/2), y(0), w - (w*5)/100, y(0) + (h*2)/100);
        cxt.strokeLine(x(w/2), y(0), w - (w*5)/100 , y(0) - (h*2)/100);
    }
    
    public void drawBar (double x, double y, double w, double h) {
        cxt.fillRect(x, y, w, h);
    }
    
    public void drawBars (double[] v) {
        // bars: number of bars
        int bars = v.length; 
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
                uly = (int) (v[i] * ((h - pxFromBottom) / 100));
                
                // disegno la barra
                this.drawBar(i*(bw+1), y(uly), bw, uly);
                // disegno la lettera corrsipondente alla barra, 'w/70' indica i px da aggiungere per centrare la lettera, in rapporto a l'estensione di w
                cxt.fillText(String.valueOf(Character.toChars(letter)), i*(bw+1) + w/70, y(-pxFromBottom/2), (w/100)*3);
                letter ++;
            }
        }        
    }
    
    private double x (double x) {
        return (w/2 + x);
    }
    
    private double y (double y) {
        //return (h/2 - y);
        return (h - pxFromBottom - y);
    }
}

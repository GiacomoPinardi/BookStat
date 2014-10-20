/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author giacomo
 */
public class MyPanel extends JPanel{
    
    int width;
    int height;
    
    public MyPanel(int width, int height) {
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        this.width = width;
        this.height = height;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.width,this.height);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
        g.fillRect(100, 100, 30, 50);
    }  
}

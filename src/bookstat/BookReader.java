/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author giacomo
 */
public class BookReader {
    
    int[] freq = null;
    double[] perc = null;
    int tot;
    Scanner lettore = null;   
        
    public double[] read (File f) throws FileNotFoundException {
        String s;
        char c;
        freq = new int[26];
        perc = new double[26];
        
        for (int i = 0; i < freq.length; i++) {
            freq[i] = 0;
            perc[i] = 0;
        }
        tot = 0;
                
        lettore = new Scanner (f);
        
        while (lettore.hasNextLine()) {
            s = lettore.nextLine().toLowerCase();
            for (int i = 0; i < s.length(); i++) {
                c = s.charAt(i);
                if ((c >= 'a') && (c <= 'z')) {
                    freq[c -'a'] ++;
                    tot ++;
                }                
            }
        }
        
        if (tot != 0) {
            for (int i = 0; i < perc.length; i++) {
            perc[i] = (100d * freq[i])/tot;
            }
        }  
        
        return this.perc;
    }
    
    // DEBUG:
    public void write () {
        for (int i = 0; i < freq.length; i++) {
            System.out.print(perc[i]);
            System.out.print(" - ");
            System.out.println(freq[i]);
        }
        System.out.println("Tot: " + tot);
    }
         
}

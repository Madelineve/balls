/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rysownik;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class Rysownik extends JFrame{

    
    public static void main(String[] args) {
        JFrame frame= new JFrame("Rysownik kulek");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new KulkowyPanel());
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        frame.setLocation(screenSize.height / 2, screenSize.width / 14);
        frame.setPreferredSize(new Dimension(600,600));
        
        frame.pack();
        frame.setVisible(true);
        
    }
    
}

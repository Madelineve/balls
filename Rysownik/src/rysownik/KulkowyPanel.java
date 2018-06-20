
package rysownik;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import static java.awt.event.MouseWheelEvent.WHEEL_BLOCK_SCROLL;
import static java.awt.event.MouseWheelEvent.WHEEL_UNIT_SCROLL;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;





public class KulkowyPanel extends JPanel {
    private ArrayList<Kulki>  kulkowaLista;
    private Timer timer;
    private final int DELAY = 33; //oponienie 30ms dla 30ftp
    public int licznik=0;
    public KulkowyPanel(){
        kulkowaLista = new ArrayList<>();
        addMouseListener(new KulkiListener());
        addMouseWheelListener(new KulkiListener());
        timer = new Timer(DELAY, new KulkiListener());
        setBackground(Color.BLACK);
        timer.start();
        
        
        
    }
    @Override
    public void paintComponent(Graphics graf){
        super.paintComponent(graf);
      
        for(Kulki kule:kulkowaLista){
            graf.setColor(new Color(kule.colour)); 
            graf.fillOval(kule.x-kule.size/2, kule.y-kule.size/2, kule.size, kule.size);
           /* graf.setColor(new Color(kule.colour1));
            graf.fillOval(kule.x-kule.size/4, kule.y-kule.size/4, kule.size/2, kule.size/2);
            */
        }
        graf.setColor(Color.MAGENTA);
        
        //linie na dole?
        int xPoints[]={0,100 , getWidth()/2 , getWidth()-100 ,getWidth()};
        int yPoints[]={getHeight() , getHeight()-100 ,getHeight() , getHeight()-100, getHeight()};
        graf.drawPolyline(xPoints,yPoints,5);
        
        String a=Integer.toString(licznik);
        graf.setFont(new Font("Serif", Font.BOLD, 50));
        graf.drawString(a, 50, 100);
    }
    
    private class KulkiListener implements MouseListener,ActionListener,MouseWheelListener{

        
        @Override
        public void mouseClicked(MouseEvent me) {//klik 
            
        }
       
        @Override
        public void mousePressed(MouseEvent me) {//przytrzymanie
            
            
            kulkowaLista.add(new Kulki(me.getX(),me.getY(),40));
            licznik++;
            repaint();
        }

        @Override
        public void mouseReleased(MouseEvent me) { 
            
         
        }

        @Override
        public void mouseEntered(MouseEvent me) { // na wejscie
           
        }

        @Override
        public void mouseExited(MouseEvent me) {//na wyjscie
            
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            for(Kulki kula:kulkowaLista){
                kula.update();
            }
            repaint();
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent mwe) {
            if(mwe.getWheelRotation()==1)
            for(Kulki kula:kulkowaLista)
                kula.kolko1();
             
            else 
            for(Kulki kula:kulkowaLista){
                kula.kolko();   
           
            repaint();
             } 
        }
    }
    
    private class Kulki{
        public int x;
        public int y;
        public int size;
        public int xspeed;
        public int yspeed;
        private final int MAX_SPEED = 10;
        public int colour = (int)(Math.random()*256*256*256);
        public int colour1 = (int)(Math.random()*256*256*256);
        
        
        public Kulki(int x, int y, int size) {
            this.x = x;
            this.y = y; 
            this.size = size;
            this.colour = colour;
            this.colour1=colour1;
          
            
            xspeed = (int)(Math.random()*MAX_SPEED*2 - MAX_SPEED);
            yspeed = (int)(Math.random()*MAX_SPEED*2 - MAX_SPEED);
            if(xspeed == 0){
                xspeed = -4;
            }
            if(yspeed == 0){
                yspeed = 5;
            }
        }
       
        public void update(){
            x += xspeed;
            y += yspeed;
            
            if( x >= getWidth()-20 ||  x <= 20 ) {
                xspeed=-xspeed;
            }
              if(  y <= 20  ){
                yspeed=-yspeed;
            }
            
              if((x<100 && x+y>getHeight()-size) || (x>=100 && x<=getWidth()/2 && 2*y-x>800) || (x>=getWidth()/2 && x<getWidth()-100 && 2*y+x>1350)|| (x>=getWidth()-100 && y-x>0-size))
              {
                   xspeed=-xspeed;
                   yspeed=-yspeed;
                   
              }
            /*
            int i=0; 
             
            for(Kulki a:kulkowaLista )
            {
                
                       
                        if(    (a.x+size/2==kulkowaLista.get(i).x-size/2 && a.y-kulkowaLista.get(i).y>=0 && a.y-kulkowaLista.get(i).y<=size/2) || 
                                (a.x+size/2==kulkowaLista.get(i).x-size/2 && kulkowaLista.get(i).y-a.y>=0 && kulkowaLista.get(i).y-a.y<=size/2) ||
                                (a.y+size/2==kulkowaLista.get(i).y-size/2 &&))
                           
                        {
                            xspeed=-xspeed;
                            yspeed=yspeed;
                            
                        }
                           
                 i++;   
                */
        
            
        }
        public void kolko()
        {
            size+=5;
        }
        public void kolko1()
        {
            size-=5;
        }
    }
    
    }











/*a.x-size/2==b.x+size/2 && a.y-size/2==b.y-size/2)
                            || (a.x-size/2==b.x+size/2 && a.y+size/2==b.y+size/2)
                            || (a.x-size/2==b.x-size/2 && a.y-size/2==b.y+size/2)
                            || (a.x+size/2==b.x+size/2 && a.y-size/2==b.y+size/2)
                            || (a.x+size/2==b.x-size/2 && a.y-size/2==b.y-size/2)
                            || (a.x+size/2==b.x-size/2 && a.y+size/2==b.y+size/2)
                            || (a.x+size/2==b.x+size/2 && a.y+size/2==b.y-size/2)
                            || (a.x-size/2==b.x-size/2 && a.y+size/2==b.y-size/2)*/
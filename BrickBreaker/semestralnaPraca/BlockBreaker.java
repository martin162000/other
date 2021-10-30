import java.awt.Graphics;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;


    public class BlockBreaker extends JPanel implements KeyListener, ActionListener, Runnable {
 
         private boolean pohyb_vpravo = false;
         private boolean pohyb_vlavo = false;
         private boolean prehra = false;
         private boolean vyhra = false;
         private int skore = 0;
         private String vyhra_prehra;
         
         private Vypis text;
         private Pohyb speed;
         private Pozicia poz;
         private Rectangle Odrazac;
         private Rectangle Lopta;
         private Rectangle[] Blok = new Rectangle[28];
         private Thread hra;
     
     
     
         public BlockBreaker() { 
          this.poz = new Pozicia();
          this.speed = new Pohyb();
          this.text = new Vypis();
          this.Odrazac = new Rectangle(this.poz.get_pozicia_odrazaca("x"), this.poz.get_pozicia_odrazaca("y"), 80, 10);
          this.Lopta = new Rectangle(this.poz.get_pozicia_lopty("x"), this.poz.get_pozicia_lopty("y"), 13, 13); 
          this.hra = new Thread(this); 
          setFocusable(true);
          addKeyListener(this); // (override key eventy) 
          hra.start(); // zacne hra (override run)
          
         }
         
         public void bezanie_hry() {
             
          int poz_x = this.poz.get_pozicia_bloku("x");
          int poz_y = this.poz.get_pozicia_bloku("y");
          int pohyb_x = this.speed.get_pohyb("x");
          int pohyb_y = this.speed.get_pohyb("y");
          
           
          // Kazdy blok v poli dostane poziciu x,y,height,width
           for (int i = 0; i < this.Blok.length; i++) {
               this.Blok[i] = new Rectangle(poz_x, poz_y, 60, 15);
           
            switch (i) {
              case 6:
                poz_x = 70;
                poz_y = 70;
                break;
              case 11:
                poz_x = 100;
                poz_y = 90;
                break;
              case 15:
                poz_x = 70;
                poz_y = 110;
                break;
              case 20:
                poz_x = 7;
                poz_y = 130;
                break;
            }
           
           poz_x += 63;
          }

          while (this.prehra == false && this.vyhra == false) {
              
            // pre kazdy Blok vykonaj 
           for (int i = 0; i < this.Blok.length; i++) {
               
            // ak pole Blok[i] nie je prazdne   
            if (this.Blok[i] != null) {
                //ak Blok sa pretina s loptou
             if (this.Blok[i].intersects(this.Lopta)) {
              this.Blok[i] = null; // vymaz Blok
              this.skore++; // pridaj skore
              pohyb_y = ((this.skore >= 10) ? -4 : -3); // zvysi rychlost lopty
              pohyb_y = -pohyb_y;
             }
            }
           }
           
           // meni sa pozicia lopty
           this.Lopta.x += pohyb_x;
           this.Lopta.y += pohyb_y;
               
           // posunutie odrazaca vlavo
           if (this.pohyb_vlavo == true) {
            this.Odrazac.x -= 5; // vlavo
            this.pohyb_vpravo = false;
           }
           
           // posunutie odrazaca vpravo
           if (this.pohyb_vpravo == true) {
            this.Odrazac.x += 5; // vpravo
            this.pohyb_vlavo = false;
           }
           
           // ohranicenie posunutie odrazaca
           if (this.Odrazac.x <= 4) {
            this.Odrazac.x = 4;
           } else if (this.Odrazac.x >= 500) {
            this.Odrazac.x = 500;
           }
           
           // ohranicenie posunitie lopty vodorovne (x)
           if (this.Lopta.x <= 0 || this.Lopta.x + this.Lopta.height >= 585) {
            pohyb_x = -pohyb_x;
           }
           
           // ohranicenie posunitie lopty zvisle (y) alebo ak sa pretina s odrazacom
           if (this.Lopta.y <= 0 || this.Lopta.intersects(this.Odrazac)) {
            pohyb_y = -pohyb_y;
           }
           
           // Ak skore sa rovna poctu blokom a nie je prehra
           if (this.skore == this.Blok.length && this.prehra != true ) {
              this.vyhra = true;
              this.vyhra_prehra = this.text.get_vypis(this.vyhra);
              repaint();
           }
           
           // Ak lopta presiahla hracie pole z dola a nie je vyhra
           if (this.Lopta.y >= 720 && this.vyhra != true) {
               this.prehra = true;
               this.vyhra_prehra = this.text.get_vypis(this.vyhra);
               repaint();
             }
          

           try {
            Thread.sleep(10); // 10 milisekund oneskorenie
           } catch (Exception ex) {
           }
           
           // prekresli pomocou override paint
           repaint();
          }
        
         }
         
         @Override
         public void run() {
          // ak nie je vyhra ani prehra tak spusti metodu bezanie_hry()
          while (this.prehra == false && this.vyhra == false) {
              this.bezanie_hry();
            }
        }
        

         @Override
         public void paint(Graphics g) {
         
          // pozadie   
          g.setColor(Color.LIGHT_GRAY);
          g.fillRect(0, 0, 600, 800);
          
          //lopta
          g.setColor(Color.red);
          g.fillOval(this.Lopta.x, this.Lopta.y, this.Lopta.width, this.Lopta.height);
          
          //odrazac
          g.setColor(Color.yellow);
          g.fill3DRect(this.Odrazac.x, this.Odrazac.y, this.Odrazac.width, this.Odrazac.height, true);
          
          //prehra priestor (y)
          g.setColor(Color.GRAY);
          g.fillRect(0, 720, 800, 20);
          
          // Skore
          g.setColor(Color.red);
          g.setFont(new Font("Arial",Font.BOLD, 20));
          g.drawString("Body: "+this.skore, 10,25);
          
          
          
          // Vykreslenie blokov
          for (int i = 0; i < this.Blok.length; i++) {
           if (this.Blok[i] != null) {
                if(i >= 0 && i <= 6 || i >= 21 && i <= 27 || i >= 12 && i <= 15) {
                g.setColor(Color.black); 
            }else {
                g.setColor(Color.green); 
            }
            
            g.fill3DRect(this.Blok[i].x, this.Blok[i].y, this.Blok[i].width, this.Blok[i].height, true);
           }
           //System.out.println(i);
          }
          
          // Vypis (vyhra / prehra)
          if (prehra == true || vyhra == true) {
           Font f = new Font("Arial", Font.BOLD, 20);
           g.setFont(f);
           g.drawString(this.vyhra_prehra, 220, 350);
           this.prehra = false;
           this.vyhra = false;
          }
        
         }
        
        
         @Override // Stlacene
         public void keyPressed(KeyEvent e) {
          int keyCode = e.getKeyCode();
          if (keyCode == KeyEvent.VK_LEFT) {
           this.pohyb_vlavo = true;
          }
          
        
          if (keyCode == KeyEvent.VK_RIGHT) {
           this.pohyb_vpravo = true;
          }
         }
        
         @Override // Nestlacene
         public void keyReleased(KeyEvent e) {
          int keyCode = e.getKeyCode();
          if (keyCode == KeyEvent.VK_LEFT) {
           this.pohyb_vlavo = false;
          }
        
          if (keyCode == KeyEvent.VK_RIGHT) {
           this.pohyb_vpravo = false;
          }
         }
        
         @Override
         public void keyTyped(KeyEvent arg0) {
        
         }
        
         @Override // akcia
         public void actionPerformed(ActionEvent e) {
          String strr = e.getActionCommand();
          if (strr.equals("RESTART")) {
           this.restart(); // vykona metodu restart
          }
         }
        
         // metoda restart
         public void restart() {  
          int poz_x = this.poz.get_pozicia_bloku("x");
          int poz_y = this.poz.get_pozicia_bloku("y");
          int pohyb_x = this.speed.get_pohyb("x");
          int pohyb_y = this.speed.get_pohyb("y");
        
          requestFocus(true);
          
          this.prehra = false;
          this.vyhra = false;
          this.skore = 0;
          this.vyhra_prehra = null;

          this.Lopta = new Rectangle(this.poz.get_pozicia_lopty("x"), this.poz.get_pozicia_lopty("y"), 13, 13);
          this.Odrazac = new Rectangle(this.poz.get_pozicia_odrazaca("x"), this.poz.get_pozicia_odrazaca("y"), 80, 10);
          this.Blok = new Rectangle[28];
        
        
          for (int i = 0; i < this.Blok.length; i++) {
               this.Blok[i] = new Rectangle(poz_x, poz_y, 60, 15);
           
            switch (i) {
              case 6:
                poz_x = 70;
                poz_y = 70;
                break;
              case 11:
                poz_x = 100;
                poz_y = 90;
                break;
              case 15:
                poz_x = 70;
                poz_y = 110;
                break;
              case 20:
                poz_x = 7;
                poz_y = 130;
                break;
            }
           
           poz_x += 63;
          }
      repaint();
     }
}

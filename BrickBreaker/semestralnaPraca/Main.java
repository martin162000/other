
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class Main {
    
	public static void main(String[] args) {
          JFrame frame = new JFrame();
          JButton restart = new JButton("RESTART");
          BlockBreaker hra = new BlockBreaker();
          frame.setSize(600, 800);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
          frame.add(hra);
          
          frame.add(restart, BorderLayout.SOUTH);
          restart.addActionListener(hra);
          
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
          frame.setResizable(false);
          
          
	}

}
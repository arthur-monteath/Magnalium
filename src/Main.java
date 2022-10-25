import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	public static void main(String[] args) {

		//JFrame frame = new JFrame();
	    
		JFrame frame = new JFrame("Testing");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridBagLayout());
		frame.add(new JLabel("Boo!"));
		frame.setSize(1920, 1080);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,0));//etstes
		frame.add(new JLabel("test"));
		frame.add(new JLabel("alchemy lets gooo")).setForeground(new Color(255,255,255,255));
		
		Container c = new Container();
		Graphics g = frame.getGraphics();
		g.setColor(Color.red);
		g.fillRect(200, 200, 1000, 1000);
		c.paint(g);
		frame.setContentPane(c);
		
		frame.setVisible(true);
		/*frame.setSize(960,540);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setTitle("Magnalium");
	    frame.setShape(new Shape());
	    frame.setUndecorated(false);
	    frame.setLocationRelativeTo(null);
	    frame.setBackground(new Color(0,0,0,255));
	    frame.add(new JLabel(new ImageIcon("../res/Calcul4t0r.png")));
	    frame.setVisible(true);*/
	}

}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/* Affiche les noms des créateurs du jeu */

public class Credits extends JFrame {
	Container3 p1;

	public Credits () {
		super("Crédits");
		
		p1 = new Container3 (this);
		
		this.add(p1);
	
		this.setMinimumSize(new Dimension(300, 300));
		//this.setPreferredSize(new Dimension(30, 30));
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);		
		pack ();
	}
	
	public static void main (String [] args) {
		Credits f = new Credits ();
	}

}

/* class qui contient les autres classe d'affichage pour<br>la fenetre Crédits*/

class Container3 extends JPanel {
	Credits f;
	int i;
	JButton bouton;
	Clique clique;

	Color couleur;
	
	String s;
	
	JTextArea label;
		
	public Container3 (Credits f) {
		super ();
		
		couleur = new Color(-15756264);
		
		s = "Mastermind : \n\n\n" +
				"Paul Jeanmaire, Sampath Karthik,\n" +
				"\nIbrahima Timera, Cyrille de Gasquet.\n\n\n2013.";
		
		Font font = new Font("Serif", Font.PLAIN, 14);

		bouton = new JButton ("Ok");

		clique = new Clique (0);

		bouton.addActionListener(clique);

		this.add(bouton);		
		
		label = new JTextArea();
		label.setText(s);
		label.setFont(font);
		label.setEditable(false);
		label.setBackground(couleur);
		
		label.setForeground(Color.BLACK);
		
		label.setWrapStyleWord(true);
		
		this.add(label);
		
		this.f = f;
	}
	
	public void paintComponent (Graphics g) {
		int getWidth = this.getWidth();
		int getHeight = this.getHeight();
		
		g.setColor(couleur);	
		g.fillRect(0,0,getWidth,getHeight);
		
		label.setBounds(30,15,5000,5000);
		bouton.setBounds(getWidth/2-80/2,getHeight-40-12,80,40);

		//System.out.println("OK.");
		
		this.setPreferredSize(new Dimension(getWidth,getHeight));
	}

	/* Listener de la classe*/

	class Clique implements ActionListener {
		int n;
		
		int i;
		public Clique(int n) {
			this.n = n;
		}

		public void actionPerformed(ActionEvent e) {
			if (n==0) { //Principe du jeu
				f.dispose();
			}
		}
	}
}

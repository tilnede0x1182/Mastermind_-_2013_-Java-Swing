import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class Instructions extends JFrame {
	Container4 p1;

	public Instructions () {
		super("Instructions");
		
		p1 = new Container4 (this);
		
		this.add(p1);
	
		this.setMinimumSize(new Dimension(600, 400));
		this.setPreferredSize(new Dimension(640, 480));
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);		
		pack ();
	}
	
	public static void main (String [] args) {
		Instructions f = new Instructions ();
	}

}

class Container4 extends JPanel {
	Instructions f;
	Clique [] clique;
	int i;
	Color couleur;
	
	JTextArea [] label;
	
	JButton [] boutons;
	
	public Container4 (Instructions f) {
		super ();
		
		label = new JTextArea [4];
		
		String [] s = new String [4];
		
		for (i=0; i<s.length; i++) s[i] = "";
		
		s = contenuInstructions ();

		Font font = new Font("Serif", Font.PLAIN, 18);
		
		couleur = new Color(-16738048);
		
		for (i=0; i<4; i++) {
			label[i] = new JTextArea();
			label[i].setText(s[i]);
			label[i].setFont(font);
			label[i].setEditable(false);
			label[i].setBackground(couleur);
			label[i].setForeground(Color.WHITE);
			label[i].setWrapStyleWord(true);
			this.add(label[i]);
			label[i].setVisible(false);
		}
		
		clique = new Clique [8];
		
		boutons = new JButton [8];
		
		boutons[0] = new JButton("Principe du jeu");
		boutons[1] = new JButton("Comment jouer ?");
		boutons[2] = new JButton("Interphace graphique");
		boutons[3] = new JButton("Options et mode de jeu");
		boutons[4] = new JButton("Retour");
		boutons[5] = new JButton("Retour");
		boutons[6] = new JButton("Retour");
		boutons[7] = new JButton("Retour");
		
		
		for (i=0; i<boutons.length; i++) {
			clique[i] = new Clique(i);
			boutons[i].addActionListener(clique[i]);
			this.add(boutons[i]);
			if (i>3) boutons[i].setVisible(false);
		}
			
		this.f = f;
	}
	
	public void paintComponent (Graphics g) {
		int getWidth = this.getWidth();
		int getHeight = this.getHeight();
		
		g.setColor(couleur);	
		g.fillRect(0,0,getWidth,getHeight);
		
		for (i=0; i<4; i++) {
			boutons[i].setBounds(getWidth/12*6-250/2, getHeight/15*(2+3*i), 250,50);
			label[i].setBounds(getWidth/12*6-250/2, getHeight/15*(2), 450,250);
		}
		
		for (i=4; i<8; i++) boutons[i].setBounds(getWidth/12*6-250/2, getHeight/15*(11),250,50);
		
		//System.out.println("OK.");
		
		this.setPreferredSize(new Dimension(getWidth,getHeight));
	}
	
	public String [] contenuInstructions () {
		int i;
		String [] s = new String [4];
		
		for (i=0; i<s.length; i++) s[i] = "";
		
		s[0] = "Master mind est un jeu de tactique\n" +
				"dont le but du jeu sera de trouver\n" +
				"le plus rapidement possible\n" +
				"une combinaison de couleurs et de les placer\n" +
				"dans le bon ordre.";
		s[1] = "Pour vous aider, vous pourrez choisir\n" +
				"le nombre de chiffres, la difficulte, \n" +
				"le nombre de coups maximum \n"+
				"ainsi que le temps, si vous choisissez de jouer\n"+
				"en mode "+'"'+"contre la montre"+'"'+".";
		s[2] = "Pour jouer, il vous faudra, une fois\n" +
				"que vous aurez appuyé sur "+'"'+"Commencer"+'"'+", cliquez\n" +
				"sur les couleurs de votre choix et validez\n" +
				"la combinaison. Les réponses s'affichent sur\n" +
				"le côté droit du plateau de jeu : un pion\n" +
				"rouge signifie que la couleur est bien placée.\n" +
				"Un pion blanc signifie que la couleur est mal\n" +
				"placée dans la combinaison mais qu'elle existe\n" +
				"dans la solution. Enfin, un pion jaune signifie\n" +
				"que la couleur est bien ou mal placée.";
		s[3] = "Vous pouvez changer les couleurs\n" +
				"des pions du jeu avant de commencer une partie\n" +
				"en appuyant sur le bouton "+'"'+"Changer les\n" +
				"couleurs"+'"'+" et en cliquant sur les pions\n" +
				"de couleurs.";
		
		return s;
	}
	
	class Clique implements ActionListener {
		int n;
		
		int i;
		public Clique(int n) {
			this.n = n;
		}
		public void actionPerformed(ActionEvent e) {
			if (n==0) { //Principe du jeu
				for (i=0; i<4; i++) boutons[i].setVisible(false);
				label[0].setVisible(true);
				boutons[4].setVisible(true);
			}
			
			if (n==1) { //Comment jouer
				for (i=0; i<4; i++) boutons[i].setVisible(false);
				label[1].setVisible(true);
				boutons[5].setVisible(true);
			}
			
			if (n==2) { //
				for (i=0; i<4; i++) boutons[i].setVisible(false);
				label[2].setVisible(true);
				boutons[6].setVisible(true);
			}
			
			if (n==3) { //
				for (i=0; i<4; i++) boutons[i].setVisible(false);
				label[3].setVisible(true);
				boutons[7].setVisible(true);
			}
			
			if (n==4) { //Retour
				for (i=0; i<4; i++) boutons[i].setVisible(true);
				label[0].setVisible(false);
				boutons[4].setVisible(false);
			}
			
			if (n==5) { //Retour
				for (i=0; i<4; i++) boutons[i].setVisible(true);
				label[1].setVisible(false);
				boutons[5].setVisible(false);
			}
			
			if (n==6) { //Retour
				for (i=0; i<4; i++) boutons[i].setVisible(true);
				label[2].setVisible(false);
				boutons[6].setVisible(false);
			}
			
			if (n==7) { //Retour
				for (i=0; i<4; i++) boutons[i].setVisible(true);
				label[3].setVisible(false);
				boutons[7].setVisible(false);
			}
		}
	}
}

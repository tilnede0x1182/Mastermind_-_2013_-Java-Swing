import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/* Classe qui demande à l'utilisateur<br>d'entrer son nom pour ensuite le<br>ranger dans le fichier contenant les <br>scores et les statistiques.*/

public class EntrerNom extends JFrame {
	
	Container5 p1;
	WindowAdapter wa;

	public EntrerNom (final int tailleDesPolices) {
		super("Statistiques : entrez votre nom : ");
		
		p1 = new Container5 (this,tailleDesPolices);
		
		this.add(p1);
	
		this.setMinimumSize(new Dimension(300, 300));
		//this.setPreferredSize(new Dimension(640, 480));
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		wa = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				p1.enregistreLesStats (tailleDesPolices);
			}
		};

		this.addWindowListener(wa);		
	}

	public void afficheScoresNonTrouvés () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Aucun fichier de scores et statistiques n'a été trouvé.","Aucun fichier de scores et statistiques trouvé.", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setTabSauveStats (int [] t) {
		p1.setTabSauveStats (t);
	}

	public String getS () {
		return p1.getS ();
	}
	
	public static void main (String [] args) {
		EntrerNom f = new EntrerNom (12);
	}
}

/* class qui contient les autres classe d'affichage pour<br>la fenetre.<br> il ne faut pas que ce nom se répète, car il existe d'autres classes dont la fonction est la même (dans Crédits et Scores).*/

class Container5 extends JPanel {
	EntrerNom f;
	Clique3 clique;
	int i;
	Color couleur;
	String s;
	UtilFichiers utilFichiers;
	int [] tabSauveStats;
	int tailleDesPolices;
	
	JTextArea label;
	
	JTextField champNom;
	
	JButton bouton;
	
	public Container5 (EntrerNom f, int tailleDesPolices) {
		super ();

		this.tailleDesPolices=tailleDesPolices;

		utilFichiers = new UtilFichiers ();

		tabSauveStats = new int [7];

		s = "";
		
		champNom = new JTextField ();
		this.add(champNom);
		
		label = new JTextArea ("Entrez votre nom : ");
		
		Font font = new Font("Serif", Font.PLAIN, 16);
		
		couleur = new Color(-26368);
		
		label.setFont(font);
		label.setEditable(false);
		label.setBackground(couleur);
		label.setForeground(Color.BLACK);
		label.setWrapStyleWord(true);
		this.add(label);
			
		bouton = new JButton("Ok");

		clique = new Clique3(1);
		bouton.addActionListener(clique);
		this.add(bouton);
			
		this.f = f;
	}
	
	public void paintComponent (Graphics g) {
		int getWidth = this.getWidth();
		int getHeight = this.getHeight();
		
		g.setColor(couleur);	
		g.fillRect(0,0,getWidth,getHeight);

		label.setBounds(getWidth/2-150/2,getHeight/2-50/2-35, 150,35);
		champNom.setBounds(getWidth/2-150/2,getHeight/2-50/2, 150,35);
		bouton.setBounds(getWidth/2-75/2,getHeight-50-15, 75,35);
		
		//System.out.println("OK.");
	
	}
	
	class Clique3 implements ActionListener {
		int n;
		
		int i;
		public Clique3(int n) {
			this.n = n;
		}
		public void actionPerformed(ActionEvent e) {
			if (n==1) { //Ok
				enregistreLesStats (tailleDesPolices);
			}
			

		}
	}

	public void enregistreLesStats (int tailleDesPolices) {
		s = champNom.getText();
		if (s.equals("")) {
			s="Inconnu";
			System.out.println("OK1.");
		}

		System.out.println(s);

		String s1 = utilFichiers.sauveStat1(s,tabSauveStats[0],tabSauveStats[1],tabSauveStats[2],tabSauveStats[3],tabSauveStats[4],tabSauveStats[5],tabSauveStats[6]);

		utilFichiers.StatsFichier (s1);

		f.dispose();

		File ff2 = new File ("Sauvegardes"+File.separatorChar+"Statistiques.txt");

		if (!ff2.exists ()) f.afficheScoresNonTrouvés ();
		else {
			Scores scores = new Scores (tabSauveStats[0],tabSauveStats[1],tabSauveStats[2],true,tailleDesPolices);
		}
	}

	public void setTabSauveStats (int [] t) {
		int i;

		tabSauveStats = new int [t.length];		

		for (i=0; i<t.length; i++) tabSauveStats [i] = t[i];
	}

	public String getS () {
		return s;
	}
}

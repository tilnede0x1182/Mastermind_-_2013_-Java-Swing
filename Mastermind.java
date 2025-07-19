import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/* Classe principale : affiche la fenetre du jeu et appelle toutes les fonctions.*/

public class Mastermind extends JFrame {

	static WindowAdapter wa;
	Boolean b1;
	Boolean b2;
	int getHeight;
	int getWidth;
	P1 p1;
	EnregistrementFichiers enregistrementFichiers; 
	boolean clm;
	boolean coupsIllimités;

	public Mastermind (String titre) {
		super(titre);

		b1=false;
		b2=false;

		clm = false;
		coupsIllimités = false;

		getHeight=0;
		getWidth=0;

		int n11=4,a=1,b=n11,c=30,m11=2,h=2,ndco=6,nombreDePartiesJouées=0, he=0,  mi=5, se=15, tailleDesPolices=12; //déclaration des paramètres du jeu (options)
		boolean coupsIllimités1=false,premierCoups = false,tourEnCours = false,clm1 = false; //déclaration des paramètres du jeu (options)

		enregistrementFichiers = new EnregistrementFichiers ();

		if (new File("Sauvegardes"+File.separatorChar+"Parametres.txt").exists ()) {

			int [] tparametres = enregistrementFichiers.litParametreFichiers ();

			boolean [] tparametresB = enregistrementFichiers.decodeParametres(tparametres);

			n11=tparametres[0];
			a=tparametres[1];
			b=n11;
			c=tparametres[3];
			m11=tparametres[4];
			h=tparametres[5];
			ndco=tparametres[6];
			nombreDePartiesJouées=tparametres[7];
			coupsIllimités1=tparametresB[0];
			premierCoups = tparametresB[1];
			tourEnCours = tparametresB[2];
			clm1 = tparametresB[3];
			he=tparametres[12];
			mi=tparametres[13];
			se=tparametres[14];
			tailleDesPolices=tparametres[15];
		}

		p1 = new P1 (this,n11,m11,ndco,h,c,clm1,he,mi,se,nombreDePartiesJouées,coupsIllimités1,tailleDesPolices);
		
		this.add(p1);

		//this.add(new P2(this.getWidth(),this.getHeight()));

		wa = new WindowAdapter() {
			public void windowIconified (WindowEvent e)  {
				System.out.println("windowIconified");
			}

			public void windowClosing(WindowEvent e) {
				int n1 = 1;

				if (p1.getJeuCommence()) {
					if (clm) {
						p1.pause ();
					}
					n1 = demandeSauvegarde ();
					if (clm) {
						p1.reprendre ();
					}
				}

				//System.out.println("n1 = "+n1);

				if (n1==0) {
					p1.sauvePartie ();	
				}				

				if (n1==0 || n1==1) {
					p1.enregistreParametres ();

					System.exit(0);
				}
			}

			public void windowStateChanged(WindowEvent e)  {
				System.out.println("windowStateChanged");

				/*if (!b1) {
					getHeight = getHeight();
					getWidth = getWidth();

					b1=true;
				}
				else {
						resize(new Dimension(getWidth,getHeight));
						b1=false;
				}
				
				if (b1) b1=false;
				else {
					if (!b2) {
						getHeight = getHeight();
						getWidth = getWidth();
						b2=true;
					} else {
						resize(new Dimension(getWidth,getHeight));
						b2=false;
					}
				}*/
			}
		};
		
		this.addWindowStateListener(new WindowStateListener() {

			public void windowStateChanged(WindowEvent arg0) {
				if (arg0.getNewState()==MAXIMIZED_BOTH) {
					reDim1 ();
				}

				System.out.println("window maximized");

				if (arg0.getNewState()==ICONIFIED)

				System.out.println("window iconfied");

				if(arg0.getNewState()==NORMAL)

				System.out.println("window normal size");
			}
		});		
		
		this.setMinimumSize(new Dimension(800, 600));
		this.setPreferredSize(new Dimension(800, 600));

		//pack();

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void reDim1 () {
		/*boolean dd = (getExtendedState()==this.MAXIMIZED_BOTH);

		int getWidth = this.getWidth();
		int getHeight = this.getHeight();

		resize(new Dimension(getWidth+1,getHeight+1));
		resize(new Dimension(getWidth,getHeight));	

		if (dd) {
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			b1=true;
		}*/

		p1.tempsT1 = new Thread(new Dors ());
		p1.tempsT1.start ();
	}

	public void reDim () {
		this.revalidate();
	}

	class Dors implements Runnable{  
		public void run(){
			int i;

			try {
				for (i=0; i<500; i++) {
					p1.tempsT1.sleep(1);

					if (i%100==0) {
						p1.revalidate ();

						//System.out.println(i);
					}
				}

			}

			catch (InterruptedException  e)  {
				e.printStackTrace();
			}
		}
	}

	public void afficheMessage (String message, String titre, char type) {
		JOptionPane fOptions = new JOptionPane();

		if (type=='i') fOptions.showMessageDialog(this, message,titre, JOptionPane.PLAIN_MESSAGE);
		else if (type=='w') fOptions.showMessageDialog(this, message,titre, JOptionPane.WARNING_MESSAGE);
		else fOptions.showMessageDialog(this, message,titre, JOptionPane.PLAIN_MESSAGE);
	}

	public void afficheErreurPartieNonTrouvée () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Aucune partie n'a été trouvée.","Aucune partie trouvée.", JOptionPane.WARNING_MESSAGE);
	}

	public void erreurOption (String option, int a, int b) {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Le nombre de "+option+" est compris entre "+a+" et "+b+".","Erreur", JOptionPane.PLAIN_MESSAGE);
	}

	public void afficheErreurCouleurs () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Veuillez entrer une couleur différente (celle-ci existe déjà dans le choix de couleurs)","Existe déjà !", JOptionPane.WARNING_MESSAGE);
	}

	public void afficheErreurNombreDeCases () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Le nombre de cases est compris entre 3 et 500.","3<Nombre de cases<500", JOptionPane.WARNING_MESSAGE);
	}

	public void afficheErreurNombreDeCouleurs () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Le nombre de couleurs est compris entre 4 et 500.","4<Nombre de couleurs<500", JOptionPane.WARNING_MESSAGE);
	}

	public void afficheErreurNombreDeCouleursM12 () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Attention ! Ce mode de jeu n'admet pas plus de 56 couleurs.","Erreur : nombre de couleurs trop grand !", JOptionPane.WARNING_MESSAGE);
	}

	public void afficheCoupsAvantAbandon (int x, int c) {
		JOptionPane fOptions = new JOptionPane();

		if (!coupsIllimités) fOptions.showMessageDialog(this, "Nombre de coups avant abandon : "+x+"/"+c+"\n\nLes solutions s'afficheront en dessous du dernier coup.","Abandon de la partie", JOptionPane.WARNING_MESSAGE);
		else fOptions.showMessageDialog(this, "Nombre de coups avant abandon : "+x+"\n\nLes solutions s'afficheront en dessous du dernier coup.","Abandon de la partie", JOptionPane.WARNING_MESSAGE);
	}

	public int demandeSauvegarde () {
		JOptionPane fOptions = new JOptionPane();

		return fOptions.showConfirmDialog(this,"Voulez vous sauvegarder la partie ?","Sauvegarder la partie ?",JOptionPane.YES_NO_CANCEL_OPTION);
	}

	public int quitteLeJeu () {
		JOptionPane fOptions = new JOptionPane();

		return fOptions.showConfirmDialog(this,"Voulez vous vraiment arrêter la partie et revenir au menu principal ?","Arrêter la partie ?",JOptionPane.WARNING_MESSAGE);
	}

	public void afficheJeuGagne (int x, int c) {
		JOptionPane fOptions = new JOptionPane();

		String s1="";

		if (c>1) s1="s";

		if (!coupsIllimités) fOptions.showMessageDialog(this, "Vous avez gagné !\n\nNombre de coup"+s1+" : "+x+"/"+c,"Fin de la partie : gagné !", JOptionPane.PLAIN_MESSAGE);
		else fOptions.showMessageDialog(this, "Vous avez gagné !\n\nNombre de coup"+s1+" : "+x,"Fin de la partie : gagné !", JOptionPane.PLAIN_MESSAGE);
	}

	public void afficheJeuGagneContreLaMontre (String temps) {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Vous avez gagné !\n\nTemps : "+temps+".","Fin de la partie : gagné !", JOptionPane.PLAIN_MESSAGE);
	}

	public void afficheJeuGagneContreLaMontrePlus (int x, int c, String temps) {
		JOptionPane fOptions = new JOptionPane();

		String s1="";

		if (c>1) s1="s";

		if (!coupsIllimités) fOptions.showMessageDialog(this, "Vous avez gagné !\n\nNombre de coup"+s1+" : "+x+"/"+c+"\n\nTemps : "+temps+".","Fin de la partie : gagné !", JOptionPane.PLAIN_MESSAGE);
		else  fOptions.showMessageDialog(this, "Vous avez gagné !\n\nNombre de coup"+s1+" : "+x+"\n\nTemps : "+temps+".","Fin de la partie : gagné !", JOptionPane.PLAIN_MESSAGE);
	}

	public void affichePerdu () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Perdu.\n\nLes solutions vont s'afficheront en dessous du dernier coup.","Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
	}

	public void affichePerduContreLaMontre (String temps) {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Perdu.\n\nTemps restant : "+temps+".\n\nLes solutions vont s'afficheront en dessous du dernier coup.","Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
	}

	public void affichePerduContreLaMontreCoupsRestants (int x, int c) {
		JOptionPane fOptions = new JOptionPane();

		String s1="";

		if (c>1) s1="s";

		if (!coupsIllimités) fOptions.showMessageDialog(this, "Perdu.\n\nNombre de coup"+s1+" : "+x+"/"+c+"\n\nLes solutions vont s'afficheront en dessous du dernier coup.","Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
		else fOptions.showMessageDialog(this, "Perdu.\n\nNombre de coup"+s1+" : "+x+"\n\nLes solutions vont s'afficheront en dessous du dernier coup.","Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
	}

	public void afficheScoresNonTrouvés () {
		JOptionPane fOptions = new JOptionPane();

		fOptions.showMessageDialog(this, "Aucun fichier de scores et statistiques n'a été trouvé.","Aucun fichier de scores et statistiques trouvé.", JOptionPane.INFORMATION_MESSAGE);
	}

	public int exit () {
		JOptionPane fOptions = new JOptionPane();

		return fOptions.showConfirmDialog(this,"Voulez vous vraiment quitter ?","Quitter le jeu ?",JOptionPane.YES_NO_OPTION);
	}

	public void setCoupsIllimités (boolean a) {
		coupsIllimités = a;
	}

	public void setClm (boolean a) {
		clm = a;
	}

	public void setTailleDesPolices (int n) {
		p1.setTailleDesPolices (n);
	}

	public static void main (String [] args) {
		Mastermind f = new Mastermind("Mastermind");
		//Mastermind f = new Mastermind("Master Mind de luxe");

		f.addWindowListener(wa);
	}
}

/* Composant qui est relier à un mouse<br>listener pour renvoyer le numéro de la couleur<br>lorsque le joueur clique sur une couleur pendant le jeu.*/

class P2 extends Component {
	BufferedImage petitPion3d;

	public P2 () {
		super();

		String images = "Images"+new File("").separatorChar+"";

		try {
			this.petitPion3d = ImageIO.read(new File(images+"PetitPionTransparent.png"));
		} 

		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : petitPion3d");
		}
	}

	public void paintComponent (Graphics g) {
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		g.drawImage(petitPion3d,0,0,this.getWidth(), this.getHeight(), null);
	}
}

class P1 extends JPanel {

	private static int a;
	private static int b;
	private static int c;
	private static int h;
	private static int [] t1;
	private static int [] t4; //pour l'ia niveau 2 facile.
	private static int [] t11;
	private static int x;
	private static int n11;
	private static int m11;
	private static int e;
	private static int q;
	private static int sauve;
	private static int [][] couleurs;
	private static int [][] tcoups;
	private static int [][] tabReponses;
	private static boolean clm; //contre la montre

	UtilJeu utilJeu;
	UtilFenetre utilFenetre;
	UtilFichiers utilFichiers;

	BufferedImage caseVide;
	BufferedImage caseReponseVide;
	//BufferedImage caseVideMarron;
	//BufferedImage [] carreDesReponses;
	BufferedImage grandPionTransparent;
	BufferedImage pause;
	ImageIcon cacheInfo;

	boolean [] imageChargée;

	boolean coupsIllimités;

	BufferedImage pionBlanc;
	BufferedImage pionJaune;
	BufferedImage pionRouge;

	boolean reDimBonus;

	Color [] couleursDyn; //couleur dynamiques

	Color couleurBouton;  // couleur d'origine des boutons.

	private static IaNivFacile iaSMF;
	private static IaNivMoyen iaSMM;
	private static IaCalculsFacile iaCF;
	private static IaCalculsMoyen iaCM;
	private static IaCalculsDifficile iaCD;

	JButton cacheInfoB;

	JPanel p1;
	int cmp5;

	boolean premierCoups;

	P2 [] p2;
	int niveauDeLIA;
	ImageIcon imageB;
	JButton [] boutons;
	JButton [] couleurs3d;
	int ldPJ; // longueur du panel de Jeu.
	Mastermind f;
	int tdjpH; //taille du JPanel horizontale
	int avanceeDuTour;
	//Label monLabel;
	BufferedImage titreImg;
	BufferedImage petitPion3d;
	//BufferedImage bouton3d;
	boolean dessineCarreCouleurs;
	int [] placementB;
	int ldpdt; // longueur du pannel de temps
	boolean actb; //action couleurs transparente bis
	boolean tourEnCours;
	Clique [] action;
	BoutonTransparentAction [] actionBT;
	RedimBonus actionClique;
	int i;
	int [][] t10;
	PJeu pJeu;
	int tdcdr; //taille du carre de réponses
	boolean jeuCommence; //
	boolean abt; //Action boutons transparents

	int nombreDePartiesJouées;

	int ndco;  //nombre de couleurs

	boolean pia; // panel info activé

	int tcd; //taille de la colonne droite
	int tdbh; //taille horizontale des boutons

	int ndb; //nombre de boutons

	int tcvV; //taille verticale de la case vide
	int tcvH; //taille horizontale de la case vide
	int ndlC; //nombre de lignes de cases.

	int tdjpV; //taille du JPanel verticale

	int ajustH; //dans le cas ou il n'y a qu'une ligne
	int ajustV; //dans le cas ou il n'y a qu'une ligne

	Box box;
	Coup [] coups;

	Thread tempsT;
	Thread tempsT1;
	Thread jeuT;
	JProgressBar bar;

	String images;

	Clique cliqueBInfo;

	JPanel barrePanel;

	/* temps */

	int iTemps;

	int heures;
	int minutes;
	int secondes;

	/*------*/

	EnregistrementFichiers ff1;
	ChargerPartie cp;

	JScrollPane jsp1;

	UtilDate utilDate;

	int temps;
	int maxTemps; //max de la barre de temps

	//private JTextField texteInput;
	//private JLabel label;

	/* ia ------------------------*/

	int [] th4;

	boolean finJeuIA;

	int r1;
	int r2;
	int r3;

	/* Panel d'informations */

	int tdpi; //taille du pannel d'informations

	JTextArea label;

	Color CouleurDuLabel;

	String infosDuJeu;

	/* Cases texte */

	JTextField [] champNombre;

	JTextField [] champTemps;

	JTextArea [] texteOptions;
	JTextArea [] texteTemps;

	int tailleDesPolices;


	/*------------*/

	boolean paused;	

	public P1 (Mastermind f, int n11, int m11, int ndco, int h, int c, boolean clm, int heures, int minutes, int secondes, int nombreDePartiesJouées, boolean coupsIllimités, int tailleDesPolices) {
		super();

		couleurBouton = UIManager.getColor("Button.background"); // attribution e la couleur d'origine des boutons (classe swing).

		imageChargée = new boolean [10];

		for (i=0; i<10; i++) imageChargée [i] = true;

		utilJeu = new UtilJeu ();
		utilFenetre = new UtilFenetre ();
		utilFichiers = new UtilFichiers ();

		paused = false;

		this.tailleDesPolices=tailleDesPolices;

		/* Cases texte */

		champTemps = new JTextField [3];

		champNombre = new JTextField [3];

		texteOptions = new JTextArea [4];

		texteTemps = new JTextArea [3];

		String [] OptionsS = utilFenetre.texteDesOptions();

		Font font1 = new Font("Calibri", Font.PLAIN, 16);

		for (i=0; i<4; i++) {
			texteOptions[i] = new JTextArea();
			texteOptions[i].setText(OptionsS[i]);
			texteOptions[i].setFont(font1);
			this.add(texteOptions[i]);

			texteOptions[i].setBackground(Color.GREEN);

			texteOptions[i].setVisible(false);
		}

		font1 = new Font("Calibri", Font.PLAIN, 18);

		for (i=0; i<3; i++) {
			texteTemps[i] = new JTextArea();
			texteTemps[i].setFont(font1);
			this.add(texteTemps[i]);
			
			texteTemps[i].setBackground(Color.GREEN);

			texteTemps[i].setVisible(false);
		}

		texteTemps[0].setText("Heures");
		texteTemps[1].setText("Minutes");
		texteTemps[2].setText("Secondes");

		for (i=0; i<3; i++) {
			champTemps [i] = new JTextField();

			champNombre [i] = new JTextField();

			this.add(champTemps [i]);
			this.add(champNombre [i]);

			champTemps [i].setVisible(false);
			champNombre [i].setVisible(false);			
		}


		/*-------------*/

		images = "Images"+new File("").separatorChar+"";

		iTemps = 0;

		pia = true;

		/* date */

		utilDate = new UtilDate ();

		/* Temps */

		this.heures = heures;
		this.minutes = minutes;
		this.secondes = secondes;

		int [] ttemps = new int [3];

		ttemps[0] = heures;
		ttemps[1] = minutes;
		ttemps[2] = secondes;

		temps = utilDate.convertitEnSecondes(ttemps);

		/*-------*/

		/* Fichiers */

		ff1 = new EnregistrementFichiers ();

		/* Option nombre de chiffres et de couleurs */

		//private JTextField texteInput = new JTextField("Valeur par défaut");
		//private JLabel label = new JLabel("Un JTextField");

		/*-barre de temps*/
		
		barrePanel = new JPanel();

		maxTemps = 500;
		
		tempsT = new Thread(new Traitement());

		tempsT1 = new Thread(new Dors ());

		jeuT = new Thread(new AfficheIA());
		bar = new JProgressBar(JProgressBar.VERTICAL,0,maxTemps);
		bar.setForeground(new Color(-4868683));
		bar.setValue(maxTemps);			
		
		barrePanel.add(bar);

		this.add(barrePanel);

		this.clm=clm; // contre la montre
		
		/*----------------clm---------------*/

		if (clm) {
			ldpdt = 20;

			barrePanel.setVisible(true);

			f.setClm(true);
		}
		else {
			ldpdt = 0;

			barrePanel.setVisible(false);

			f.setClm(false);
		}
		
		//carreDesReponses = new BufferedImage[3];

		try {
			caseVide = ImageIO.read(new File(images+"CaseVideNoire.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : caseVide");
		}
		try {
			caseReponseVide = ImageIO.read(new File(images+"caseReponseVide.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			imageChargée [1] = false;
			System.out.println("Lecture du fichier impossible : caseReponseVide");
		}
		/*try {
			caseVideMarron = ImageIO.read(new File(images+"CaseVideMarron.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : caseVideMarron");
		}
		try {
			carreDesReponses [0] = ImageIO.read(new File(images+"CarreDesReponses.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Lecture du fichier impossible : carreDesReponses [0]");
		}
		try {
			carreDesReponses [1] = ImageIO.read(new File(images+"CarreDesReponses.png"));
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("Lecture du fichier impossible : carreDesReponses [1]");
		}
		try {
			carreDesReponses [2] = ImageIO.read(new File(images+"CarreDesReponses.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : carreDesReponses [2]");
		}*/
		try {
			grandPionTransparent = ImageIO.read(new File(images+"GrandPionTransparent.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : grandPionTransparent");
		}
		try {
			//cacheInfo = ImageIO.read(new File(images+"CacheInfo.png"));
		}
		catch(Exception e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : cacheInfo");
		}
		try {
			cacheInfo = new ImageIcon(new ImageIcon(images+"CacheInfo.png").getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT));
		}
		catch(Exception e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : cacheInfo");
		}
		try {
			pause = ImageIO.read(new File(images+"Pause.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : pause");
		}
		try {
			pionBlanc = ImageIO.read(new File(images+"PionBlanc.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : pionBlanc");
		}
		try {
			pionJaune = ImageIO.read(new File(images+"PionJaune.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : pionJaune");
		}
		try {
			pionRouge = ImageIO.read(new File(images+"PionRouge.png"));
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : pionRouge");
		}

		cacheInfoB = new JButton(cacheInfo);

		this.add(cacheInfoB);

		cliqueBInfo = new Clique (-1);

		cacheInfoB.addActionListener(cliqueBInfo);

		/*--------------------------------------------*/

		cmp5=1;

		a=1; // solutions entre a et b

		this.h=h; //difficulté

		niveauDeLIA=2;

		this.m11=m11;

		this.n11=n11;

		this.ndco = ndco;

		this.c=c;
		x=0;

		b=ndco; // solutions entre a et b

		tourEnCours = false;
		avanceeDuTour = 0;

		tcoups = new int [c+1][n11];

		tabReponses = new int [c+1][n11];

		t1 = new int[n11];

		t4 = new int [n11];


		/*--------------------------------------------*/

		abt = false;
		actb = false;

		premierCoups = false;

		jeuCommence = false;

		couleurs = couleursAleat(n11);

		pJeu = new PJeu(f);

		this.add(pJeu);

		reDimBonus = false;

		//pJeu.setVisible(false);

		repaint();

		f.reDim();

		this.setBackground(Color.GREEN);

		couleursDyn = new Color [6];

		int i,j,k;

		this.f = f;

		b=ndco; // solutions entre a et b

		//System.out.println("ndco = "+ndco+"\nb = "+b);

		t10 = couleursAleat(ndco);
		dessineCarreCouleurs = false;

		//System.out.println("ndco = "+ndco+"\nb = "+b);

		//monLabel = new Label("Mastermind", Label.CENTER); //Nom du jeu
		//monLabel.setForeground(Color.WHITE);
		//monLabel.setBackground(new Color(-65536));

		//this.add(monLabel);

		try {
			this.titreImg = ImageIO.read(new File(images+"Titre.png"));
		} 
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : titreImg");
		}
		/*try {
			this.bouton3d = ImageIO.read(new File(images+"Bouton3d.png"));
		} 
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : bouton3d");
		}*/
		try {
			this.petitPion3d = ImageIO.read(new File(images+"PetitPionTransparent.png"));
		} 
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : petitPion3d");
		}
		try {
			this.imageB = new ImageIcon(new ImageIcon(images+"FlecheGauche.png").getImage().getScaledInstance(145+45, 34, Image.SCALE_DEFAULT)); 
		} 
		catch(Exception e) {
			//e.printStackTrace();
			System.out.println("Lecture du fichier impossible : imageB");
		}

		actionBT = new BoutonTransparentAction [ndco];

		p2 = new P2 [ndco];

		//System.out.println("P2.length = "+P2.length);

		ndb = 49;

		this.f=f;

		tcd = 250;

		tdbh = 200;

		couleurs3d = new JButton[ndco];

		for (i=0; i<ndco; i++) {
			//System.out.println("i = "+i);		
			p2[i]= new P2();
			actionBT[i] = new BoutonTransparentAction(i);
			this.add(p2[i]);
			p2[i].addMouseListener(actionBT[i]);
			p2[i].setVisible(false); 
		}

		actionClique = new RedimBonus();

		this.addMouseListener(actionClique);

		pJeu.addMouseListener(actionClique);

		placementB = utilFenetre.placementBoutons(ndb+4);

		this.setPreferredSize(new Dimension(f.getWidth(),f.getHeight()));

		boutons = new JButton [ndb];

		action = new Clique [ndb];

		String [] s = utilFenetre.tabBouttons (ndb);
		
		Font font2 = new Font("Calibri", Font.BOLD, 12);
		if (tailleDesPolices!=12) font2 = new Font("Calibri", Font.BOLD, tailleDesPolices);

		//Color c = new Color(-16777063);
		
		for (i=0; i<ndb; i++) {
			boutons[i]= new JButton(s[i]);
			if (tailleDesPolices!=12) boutons[i].setFont(font2);

			if (i==31) { boutons[i] = new JButton(imageB); boutons[i].setBackground(Color.GREEN); }

			action[i] = new Clique(i);
			boutons[i].addActionListener(action[i]); 
			
			this.add(boutons[i]);			
		}

		//System.out.println(boutons[0].getFont());

		for (i=6; i<boutons.length; i++) boutons[i].setVisible(false);

		/* IA--------------------*/
		if ((niveauDeLIA==3) && (n11<5 && b<5)){
			if (h!=3) niveauDeLIA=2; 
			else niveauDeLIA=1;
		}

		iaSMF = new IaNivFacile (n11,a,b);
		iaSMM = new IaNivMoyen (n11,a,b);
		if (n11<5 && b<5) iaCF = new IaCalculsFacile(n11,a,b); else iaCF = new IaCalculsFacile();
		if (n11<5 && b<5) iaCM = new IaCalculsMoyen(n11,a,b); else iaCM = new IaCalculsMoyen();
		if (n11<5 && b<5) iaCD = new IaCalculsDifficile(n11,a,b); else iaCD = new IaCalculsDifficile();


		t4 = new int [n11];
		th4 = new int [n11];
		t11 = new int [n11];

		finJeuIA = false;

		r1=0;
		r2=0;
		r3=0;

		/* Panel d'informations */

		tdpi = 75;

		infosDuJeu = "";

		CouleurDuLabel = new Color (-5461980);
		
		Font font = new Font("Calibri", Font.PLAIN, 16);		
		
		label = new JTextArea();
		label.setText(infosDuJeu);
		label.setFont(font);
		label.setEditable(false);
		label.setBackground(CouleurDuLabel);
		
		//label.setForeground(new Color (-2769012));
		//label.setForeground(new Color (-4406));
		label.setForeground(new Color (-1818));
		//label.setForeground(Color.WHITE);
		
		//label.setWrapStyleWord(true);
		
		this.add(label);

		/* coups illimités */

		this.coupsIllimités = coupsIllimités;

		if (coupsIllimités) boutons[46].setBackground(new Color(-16724941));

		/*----------------------*/

		//System.out.println("ndco = "+ndco+"\nb = "+b);

		//System.out.println("ndco = "+ndco+"\nb = "+b);

		this.nombreDePartiesJouées = nombreDePartiesJouées;

		/*----------------------------------*/

		metAJourInfos ();

		this.revalidate ();
		tempsT1.start();
		this.revalidate ();
	}

	public void chargeUnePartie (int n) {
		int i,j;

		String s1 = ff1.lireSauvegarde(n);

		System.out.println(s1);

		tcoups = ff1.litTcoups(s1);

		//for (i=0; i<tcoups.length; i++) for (j=0; j<tcoups[0].length; j++) System.out.println("tcoups["+i+"]["+j+"] = "+tcoups[i][j]);

		couleurs = ff1.litTcouleurs(s1);

		tabReponses = ff1.litReponses(s1);

		t1 = ff1.litTcode(s1);

		int [] tparametres = ff1.litTparametres (s1);

		boolean [] tparametresB = ff1.decodeParametres(tparametres);

		n11 = tparametres[0];
		a = tparametres[1];
		ndco = tparametres[6];
		b = ndco;
		c = tparametres[3];
		m11 = tparametres[4];
		h = tparametres[5];
		x = tparametres[18];

		temps = tparametres[17]/1000;
		avanceeDuTour = tparametres[16];
		coupsIllimités = tparametresB[0];
		if (m11==1 || m11==3) premierCoups = tparametresB[1]; else premierCoups = false;
		tourEnCours = tparametresB[2];
		clm = tparametresB[3];

		metAJourInfos ();

		t10 = couleursAleat(ndco);

		tcoups = new int[c+1][n11];
		
		//box.setLayout(new BoxLayout(pJeu, BoxLayout.Y_AXIS));

		for (i=0; i<x; i++) nouveauCoup (i);

		System.out.println("tcoups.length = "+tcoups.length);
		System.out.println("tcoups[0].length = "+tcoups[0].length);
		System.out.println("c = "+c);
		System.out.println("ndco = "+ndco);
		System.out.println("n11 = "+n11);

		if (clm) {
			ldpdt = 20;

			barrePanel.setVisible(true);

			f.setClm(true);

			repaint();

			f.reDim1 ();
		}

		for (i=5; i<9; i++) boutons[i].setVisible(false);
		boutons[11].setVisible(false);

		placementB[0]+=10;

		for (i=0; i<ndco; i++) p2[i].setVisible(true);

		dessineCarreCouleurs = true;

		for (i=30; i<33; i++) boutons[i].setVisible(true);
		if (clm) boutons[47].setVisible(true);

		//for (i=0; i<ndco; i++) couleurs3d[i].setVisible(true);

		pJeu.setVisible(true);

		if (clm) boutons[47].setVisible(true);

		/* Suppression du panel d'info */

		tdpi = 0;

		pia = false;

		repaint ();

		/*-----------------------------*/

		//reDim();
		f.reDim1 ();

		reDimBonus = false;

		if (m11==1 || m11==3) premierCoups=true; 


		/*------------------------------------------------------------POUR LE TEST----------------------------------------------------------*/

		//for (i=0; i<n11; i++) System.out.println("t1["+i+"] = "+t1[i]); //affiche la réponse dans le terminal.

		/*----------------------------------------------------------------------------------------------------------------------------------*/

		if (avanceeDuTour<n11) if ((m11!=3 || (m11==3 && premierCoups)) && m11!=4) tourEnCours=true;
		

		r1=0;
		r2=0;
		r3=0;

		System.out.println("OK1");

		boutons[10].setBackground(couleurBouton);
		boutons[10].setEnabled(false);

		placementB[0]+=45;
		placementB[2]+=3;

		jeuCommence = true;

		if (clm) {
			tempsT = new Thread(new Traitement());
			bar.setValue(maxTemps);

			//int blabla1 = (utilDate.convertitEnSecondesBis(tparametres[12],tparametres[13],tparametres[14])/maxTemps);
			//if (blabla1==0) blabla1 = 1;

			iTemps = utilDate.convertitEnSecondesBis(tparametres[12],tparametres[13],tparametres[14])-temps;

			System.out.println("iTemps = "+iTemps);
			System.out.println("maxTemps = "+maxTemps);
			
			bar.setValue(iTemps);
			
			commenceTemps(iTemps*1000);

			//System.out.println("temps*1000 = "+temps*1000);
		}

		reDim ();

		System.out.println("Chargée.");	
	}

	public class ChargerPartie extends JFrame {
		Container1 p1;
		boolean b1;
		int tailleDesPolices;
		EnregistrementFichiers ff1;
		
		public ChargerPartie (int tailleDesPolices) {
			super("Charger une partie : ");

			this.tailleDesPolices=tailleDesPolices;

			ff1 = new EnregistrementFichiers ();
			
			b1 = false;

			String [] s0 = ff1.detecteParties ();
			
			p1 = new Container1 (this,s0,tailleDesPolices);
			
			this.add(p1);

			this.setPreferredSize(new Dimension(500, 450));
			this.setMinimumSize(new Dimension(300, 350));
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			
			pack();
		}

		public void metAjourP1 () {

			this.remove(p1);

			String [] s0 = ff1.detecteParties ();
			
			p1 = new Container1 (this,s0,tailleDesPolices);
			
			this.add(p1);

			repaint ();

			reDim ();
		}
		
		public void afficheErreurJouer1Partie () {
			JOptionPane fOptions = new JOptionPane();

			fOptions.showMessageDialog(this,"Vous devez sélectionner une partie.","Erreur", JOptionPane.PLAIN_MESSAGE);
		}
		
		public void afficheErreurJouerPLusieursParties () {
			JOptionPane fOptions = new JOptionPane();

			fOptions.showMessageDialog(this,"Vous devez sélectionner une seule partie.","Erreur", JOptionPane.PLAIN_MESSAGE);
		}
		
		public int demandeEffacer1 (String s0) {
			JOptionPane fOptions = new JOptionPane();

			return fOptions.showConfirmDialog(this,"Voulez vous effacer la partie : "+s0+" ?", "Effacer la partie : "+s0+" ?",JOptionPane.OK_CANCEL_OPTION);
		}
		
		public int demandeEffacerPlusieurs (int n) {
			JOptionPane fOptions = new JOptionPane();
			
			String s1 = "";
			if (n>1) s1="s";

			return fOptions.showConfirmDialog(this,"Voulez vous effacer "+n+" partie"+s1+" ?", "Effacer "+n+" partie"+s1+" ?",JOptionPane.OK_CANCEL_OPTION);
		}
		
		public void reDim1 () {
			boolean dd = (getExtendedState()==this.MAXIMIZED_BOTH);

			int getWidth = this.getWidth();
			int getHeight = this.getHeight();

			resize(new Dimension(getWidth+1,getHeight+1));
			resize(new Dimension(getWidth,getHeight));

			if (dd) {
				this.setExtendedState(JFrame.MAXIMIZED_BOTH);
				b1=true;
			}
		}

		public void reDim () {
			repaint ();

			this.revalidate();
		}
		
		public String [] aleatString (int n) {
			int i;
			String [] s0 = new String [n];
			
			for (i=0; i<n; i++) s0[i] = "";
			
			for (i=0; i<n; i++) {
				s0[i] = ""+(i+1);
			}
			
			return s0;
		}
	}

	class Boutons extends JPanel {
		String s;
		JLabel txt1;
		boolean activé;

		
		public Boutons(String s) {
			this.s = s;
			
			activé = false;
			
			txt1 = new JLabel(s, JLabel.CENTER);
			txt1.setForeground(Color.BLACK);
			
			this.add(txt1);
		}
		
		public boolean getActivé () {
			return activé;
		}
		
		public void setActivé (boolean b) {
			this.activé = b;
		}
		
		public void paintComponent (Graphics g) {
			int getWidth = this.getWidth();
			int getHeight = this.getHeight();
			
			g.setColor(Color.BLACK);
			g.fillRect(0,0,getWidth, 35);
			
			if (activé) g.setColor(new Color(-4592930)); else g.setColor(Color.WHITE);
			g.fillRect(2,1,getWidth-2-2, 35-3);
			
		}
	}

	class Container1 extends JPanel {

		int i;
		ChargerPartie f;	
		JButton jouer;
		JButton effacer;
		Clique clique1;
		Clique clique2;
		CliqueSouris cliquePanel;
		EnregistrementFichiers ff1;
		UtilDate utilDate;
		int tailleDesPolices;
		Parties p2;
		int [] t;
		
		String [] s0;
		
		int [] sélectionnés;
		
		boolean selected;
		boolean effacerPlusieurs;
		
		public Container1 (ChargerPartie f, String [] s0, int tailleDesPolices) {
			super();

			this.tailleDesPolices=tailleDesPolices;
			
			this.f = f;
			
			this.s0 = s0;

			utilDate = new UtilDate ();

			ff1 = new EnregistrementFichiers ();

			t = new int [s0.length];

			for (i=0; i<t.length; i++) t[i] = i+1;

			s0 = classTabDateC(s0,t);
			
			this.setPreferredSize(new Dimension(500, 450));
			
			clique1  = new Clique (1);
			clique2  = new Clique (2);
			cliquePanel = new CliqueSouris(1);
			
			sélectionnés = new int [s0.length];
			
			p2 = new Parties(f,s0);

			Font font2 = new Font("Calibri", Font.BOLD, 12);
			if (tailleDesPolices!=12) font2 = new Font("Calibri", Font.BOLD, tailleDesPolices);
			
			jouer = new JButton("Jouer");
			effacer = new JButton("Effacer/Effacer plusieurs");

			if (tailleDesPolices!=12) {
				jouer.setFont(font2);
				effacer.setFont(font2);
			}
			
			jouer.addActionListener(clique1);
			effacer.addActionListener(clique2);
			
			selected = false;
			
			effacerPlusieurs = false;
			
			this.add(jouer);
			this.add(effacer);
			
			this.addMouseListener(cliquePanel);
			
			this.add(p2);
			
			reDim();
		}
		
		class Parties extends JPanel {
			int i;
			ChargerPartie f;
			Boutons [] boutons;
			CliqueSouris [] cliqueSouris;
			Box box;
			
			public Parties (ChargerPartie f, String [] s) {
				super();
				
				boutons = new Boutons[s.length];

				cliqueSouris = new CliqueSouris[s.length];
				
				this.f = f;
				
				this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

				box = Box.createVerticalBox();
				
				JScrollPane jsp1 = new JScrollPane(box);
				
				this.setPreferredSize(new Dimension(300,400));

				jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				
				this.add(jsp1);

				Font font2 = new Font("Calibri", Font.BOLD, 12);
				if (tailleDesPolices!=12) font2 = new Font("Calibri", Font.BOLD, tailleDesPolices);
				
				for (i=0; i<s.length; i++) {
					boutons[i] = new Boutons(s[i]);
					if (tailleDesPolices!=12) boutons[i].setFont(font2);
					boutons[i].setPreferredSize(new Dimension(150,35));
					
					cliqueSouris[i] = new CliqueSouris(i);
					
					boutons[i].addMouseListener(cliqueSouris[i]);
					
					box.add(boutons[i]);
				}
				
			}
			
			class CliqueSouris implements MouseListener {
				int n;
				
				int i;
				
				public CliqueSouris(int n) {
					this.n = n;
				}
				
				public void mouseClicked(MouseEvent e) {
					if (!effacerPlusieurs) {
						for (i=0; i<n; i++) {
							boutons[i].setActivé(false);
							sélectionnés[i]=0;
						}
						
						for (i=n+1; i<boutons.length; i++) {
							boutons[i].setActivé(false);
							sélectionnés[i]=0;
						}
					}
					
					if (boutons[n].getActivé()){
						sélectionnés[n]=0;
						boutons[n].setActivé(false);
						//System.out.println("0");
					}
					else {
						sélectionnés[n]=1;
						boutons[n].setActivé(true);
						//System.out.println("1");
					}
					
					repaint();
				}
				
				public void mouseEntered(MouseEvent e) {
					System.out.print("");
				}
				
				public void mouseExited(MouseEvent e) {
					System.out.print("");
				}
				
				public void mousePressed(MouseEvent e) {
					System.out.print("");
				}
				
				public void mouseReleased(MouseEvent e) {
					System.out.print("");
				}
			}
			
			public void paintComponent (Graphics g) {
				int getWidth = this.getWidth();
				int getHeight = this.getHeight();
				
				g.setColor(Color.WHITE);
				g.fillRect(0,0,getWidth, getHeight);
				
				for (i=0; i<boutons.length; i++) boutons[i].setPreferredSize(new Dimension(150,35));
				
				//this.setPreferredSize(new Dimension(f.getWidth()-(f.getWidth()/15*2)-12, f.getHeight()-(f.getHeight()/30*6)-35/2-5-24));
				
			}
			
			public void reDim () {
				f.reDim();
			}
		}

		public String [] classTabDateC (String [] t, int [] t2) {
			int i,j, cmp=0;
			String s0 = "";
			String [] t1;
			int n=8; //date
			
			for (i=0; i<t.length; i++) {
				for (j=i+1; j<t.length; j++) {
					if (utilDate.compareDateMax(t[i],t[j])) { 
						s0=t[j]; t[j]=t[i]; t[i]=s0;

						cmp=t2[j]; t2[j]=t2[i]; t2[i]=cmp;
					}
				}
			}
			
			return t;
		}
		
		class CliqueSouris implements MouseListener {
			int n;
			
			public CliqueSouris(int n) {
				this.n = n;
			}
			
			public void mouseClicked(MouseEvent e) {
				if (n==1) { //panel
					repaint ();
					
					//reDim();
					f.reDim1 ();
				}
			}
			
			public void mouseEntered(MouseEvent e) {
				System.out.print("");
			}
			
			public void mouseExited(MouseEvent e) {
				System.out.print("");
			}
			
			public void mousePressed(MouseEvent e) {
				System.out.print("");
			}
			
			public void mouseReleased(MouseEvent e) {
				System.out.print("");
			}
		}
		
		class Clique implements ActionListener {
			int n;
			
			Color couleurBouton;
			
			int i;
			
			public Clique (int n) {
				couleurBouton = UIManager.getColor("Button.background"); // attribution e la couleur d'origine des boutons (classe swing).

				this.n=n;
			}

			public void actionPerformed(ActionEvent arg0) {
				if (n==1) { //Jouer
					if (okJouer ()) {
						//System.out.println(t[retourneSelectionné ()]);
						chargeUnePartie (t[retourneSelectionné ()]);

						f.dispose ();
					}
					else {
						if (!estSelectionné()) f.afficheErreurJouer1Partie();
						else if (selectionnePlusieurs()) f.afficheErreurJouerPLusieursParties();
					}
				}
				if (n==2) { //Effacer
					if (!effacerPlusieurs) {
						if (!estSelectionné ()) {
							effacerPlusieurs = true;
							effacer.setBackground(new Color(-16724941));
							//effacer.setText("EffacerPlusieurs");
						}
						else {
							int r1 = f.demandeEffacer1(s0[retourneSelectionné ()]);
							
							/*if (r1!=0) {
							for (i=0; i<p2.boutons.length; i++) {
								p2.boutons[i].setActivé(false);
								sélectionnés[i]=0;
							}
							
							repaint ();
						}*/
							if (r1==0) {
								ff1.effacePartie(t[retourneSelectionné ()]);

								f.metAjourP1 ();
							}
						}
					}
					else {
						if (!estSelectionné ()) {
							//effacer.setText("Effacer");
							effacer.setBackground(couleurBouton);
							
							for (i=0; i<p2.boutons.length; i++) {
								p2.boutons[i].setActivé(false);
								sélectionnés[i]=0;
							}	
							
							effacerPlusieurs = false;
						}
						else {
							int r1 = f.demandeEffacerPlusieurs(nbreElémentsSélectionnés());
							
							/*if (r1!=0) {
							for (i=0; i<p2.boutons.length; i++) {
								p2.boutons[i].setActivé(false);
								sélectionnés[i]=0;
							}
							
							repaint ();
						}*/
							if (r1==0) {
								for (i=0; i<sélectionnés.length; i++) if (sélectionnés[i]!=0) ff1.effacePartie(t[i]);
								//for (i=0; i<sélectionnés.length; i++) if (sélectionnés[i]!=0) System.out.println("t[i] = "+t[i]);

								f.metAjourP1 ();

								//reDim ();
								f.reDim1 ();
							}
						}
					}
				}
				
			}
		}
		
		public int retourneSelectionné () {
			int i;
			
			if (estSelectionné ()) {
				for (i=0; i<sélectionnés.length; i++) if (sélectionnés[i]!=0) return i;
				
				System.out.println("La fontion "+'"'+"retourneSelectionné ()"+'"'+" dit : Erreur : pas de partie sélectionnée.");
			}
			
			return -1;
		}
		
		public boolean estSelectionné () {
			int i;
			
			for (i=0; i<sélectionnés.length; i++) if (sélectionnés[i]!=0) return true;
			
			return false;
		}
		
		public boolean selectionnePlusieurs () {
			return (nbreElémentsSélectionnés()>1);
		}
		
		public boolean okJouer () {
			return (estSelectionné() && !selectionnePlusieurs ());
		}
		
		public int nbreElémentsSélectionnés () {
			int i, a=0;
			
			for (i=0; i<sélectionnés.length; i++) if (sélectionnés[i]!=0) a++;
			
			return a;
		}
		
		public void paintComponent (Graphics g) {
			int getWidth = this.getWidth();
			int getHeight = this.getHeight();
			
			this.setPreferredSize(new Dimension(f.getWidth(), f.getHeight()));
			
			g.setColor(new Color(-1393551));
			g.fillRect(0,0,getWidth, getHeight);
			
			jouer.setBounds(getWidth/15*3-150/2, getHeight-getHeight/30*3-35/2, 150, 35);
			effacer.setBounds(getWidth-getWidth/15*3-180/2, getHeight-getHeight/30*3-35/2, 180, 35);
			
			p2.setBounds(getWidth/15*1, getHeight/30*3, getWidth-(getWidth/15*2), getHeight-(getHeight/30*6)-35/2-5);
			
			p2.setPreferredSize(new Dimension(f.getWidth()-(f.getWidth()/15*2)-12, f.getHeight()-(f.getHeight()/30*6)-35/2-5-24));
			
			//p2.setBounds(getWidth/15*3-150/2, getHeight/30*3, getWidth-(getWidth-getWidth/15*3-150/2)-getWidth/15*3-150/2, getWidth-(getHeight/30*3-(getHeight-getHeight/30*3+35)));
			
		}
		
		public void reDim () {
			f.reDim();
		}
		
		
		public boolean getSelected () {
			return this.selected;
		}

		public boolean getEffacerPlusieurs () {
			return this.effacerPlusieurs;
		}

		public void setSelected (boolean selected) {
			this.selected = selected;
		}

		public void setEffacerPlusieurs (boolean effacerPlusieurs) {
			this.effacerPlusieurs = effacerPlusieurs;
		}
	}


	public class TailleDesPolices extends JFrame {
		
		Container5 p1;
		WindowAdapter wa;

		public TailleDesPolices () {
			super("Options graphiques");
			
			p1 = new Container5 (this);
			
			this.add(p1);
			
			this.setMinimumSize(new Dimension(300, 300));
			//this.setPreferredSize(new Dimension(640, 480));
			this.setLocationRelativeTo(null);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setVisible(true);

			wa = new WindowAdapter() {
				public void windowClosing(WindowEvent e) {

				}
			};

			this.addWindowListener(wa);		
		}

		public void erreurEntier () {
			JOptionPane fOptions = new JOptionPane();

			fOptions.showMessageDialog(this, "Veuillez entrer un entier","Entrez un entier",JOptionPane.WARNING_MESSAGE);
		}

		public void erreurValeur () {
			JOptionPane fOptions = new JOptionPane();

			fOptions.showMessageDialog(this, "Veuillez entrer une taille supérieure à 1 et inférieure à 30.","Entrez une taille supérieure à 1 et inférieure à 30.",JOptionPane.PLAIN_MESSAGE);
		}
	}

	class Container5 extends JPanel {
		TailleDesPolices f;
		Clique3 clique;
		int i;
		Color couleur;
		String s;
		int [] tabSauveStats;
		UtilFichiers utilFichiers;
		
		JTextArea label;
		
		JTextField champNom;
		
		JButton bouton;
		
		public Container5 (TailleDesPolices f) {
			super ();

			utilFichiers = new UtilFichiers ();

			tabSauveStats = new int [7];

			s = "";
			
			champNom = new JTextField ();
			this.add(champNom);
			
			label = new JTextArea ("Taille des polices : \n(Par défaut : 12)");
			
			Font font = new Font("Serif", Font.PLAIN, 16);
			
			couleur = new Color(-7232910);
			
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

			label.setBounds(getWidth/2-150/2,getHeight/2-50/2-25*2, 150,25*2);
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
					String s5 = champNom.getText();

					if (!utilFichiers.TestNombre(s5)) f.erreurEntier ();
					else {
						int n5 = utilFichiers.convertNombre(s5);

						if (n5>1 && n5<30) {
							setTailleDesPolices(n5);

							f.dispose ();
						}
						else f.erreurValeur ();
					}				
				}
				

			}
		}
	}

	public void sauvePartie () {
		int [] s5 = ff1.sauveParametres(n11,a,b,c,m11,h,ndco,nombreDePartiesJouées,coupsIllimités,premierCoups,tourEnCours,clm,heures,minutes,secondes,tailleDesPolices,avanceeDuTour,calculTemps()/1000,x);

		ff1.SauvegardePartie(1,tcoups,tabReponses,couleurs,t1,s5);
	}

	public void setTailleDesPolices (int n) {
		tailleDesPolices = n;

		System.out.println("tailleDesPolices = "+tailleDesPolices);

		if (tailleDesPolices!=12) {

			Font font2 = new Font("Calibri", Font.BOLD, tailleDesPolices);
			
			for (i=0; i<boutons.length; i++) boutons[i].setFont(font2);
			
			font2 = new Font("Calibri", Font.PLAIN, 16+(12-tailleDesPolices));
			
			for (i=0; i<4; i++) texteOptions[i].setFont(font2);
			
			font2 = new Font("Calibri", Font.PLAIN, 18+(12-tailleDesPolices));
			
			for (i=0; i<3; i++) texteTemps[i].setFont(font2);
		}
		else {
			Font font2 = new Font("Calibri", Font.BOLD, 13);
			
			for (i=0; i<boutons.length; i++) boutons[i].setFont(font2);
			
			font2 = new Font("Calibri", Font.PLAIN, 16+(12-13));
			
			for (i=0; i<4; i++) texteOptions[i].setFont(font2);
			
			font2 = new Font("Calibri", Font.PLAIN, 18+(12-13));
			
			for (i=0; i<3; i++) texteTemps[i].setFont(font2);
		}
	}

	public void enregistreParametres () {
		ff1.SauveParametresFichier (n11,a,b,c,m11,h,ndco,nombreDePartiesJouées,coupsIllimités,premierCoups,tourEnCours,clm,heures,minutes,secondes,tailleDesPolices,avanceeDuTour,calculTemps()/1000,x);
	}

	class CliqueOptions implements ActionListener {
		int n;
		public CliqueOptions (int n) {
			this.n = n;
		}

		public void actionPerformed(ActionEvent e) {
			if (n==0) { // Illimité

			}

			if (n==1) { // Définit

			}
		}
	}

	public void metAJourInfos () {
		String nbreDeCoups="";

		if (coupsIllimités) nbreDeCoups="Illimité";
		else nbreDeCoups=c+"";

		String modeDeJeu = "";

		if (m11==1) modeDeJeu = "Humain contre humain";
		if (m11==2) modeDeJeu = "Ordinateur contre humain";
		if (m11==3) modeDeJeu = "Humain contre ordinateur";
		if (m11==4) modeDeJeu = "Ordinateur contre ordinateur";

		String difficulté = "";

		if (h==1) difficulté = "Facile";
		if (h==2) difficulté = "Moyen";
		if (h==3) difficulté = "Difficile";

		infosDuJeu = "Mode de jeu : "+modeDeJeu+"   Nombre de cases : "+n11+"\nNombre de coups : "+nbreDeCoups+"   Difficulté : "+difficulté+"   Nombre de couleurs : "+ndco+"\nMode contre la montre : ";
		if (clm) infosDuJeu = infosDuJeu+"Activé";
		else infosDuJeu = infosDuJeu+"Désactivé";

		infosDuJeu = infosDuJeu+".";

		label.setText(infosDuJeu);
	}

	class JeuPause extends JPanel {
		public void paintComponent (Graphics g) {
			int getWidth = this.getWidth();
			int getHeight = this.getHeight();

			if (paused) {
				g.setColor(Color.BLACK);
				g.fillRect(0,0,getWidth,getHeight);
				
				g.drawImage(pause,getWidth/2-150/2,getHeight/2-140/2,150,140,null);
			}
		}
	}

	class PJeu extends JPanel {
		JeuPause jeuPause;
		Mastermind f;
		int x=0;
		public PJeu (Mastermind f) {
			super ();

			jeuPause = new JeuPause ();
			this.add(jeuPause);
			jeuPause.setVisible(false);

			this.f = f;
			
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			this.setSize(new Dimension(560,600-tdpi));

			box = Box.createVerticalBox();

			coups = new Coup[c+2];

			coups[x] = new Coup(0,ldPJ);
			box.add(coups[x]);

			jsp1 = new JScrollPane(box);

			jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			this.add(jsp1);

			x++;
		}

		public void paintComponent (Graphics g) {
			int getWidth = this.getWidth();
			int getHeight = this.getHeight();

			if (paused) {
				jeuPause.setBounds(0,0,getWidth,getHeight);
			}
		}

		public void setVisibleJeuPause (boolean a) {
			jeuPause.setVisible(a);
		}
	}

	class Coup extends JPanel {


		int ldPJ; //longueur du panel de jeu.

		int n;

		int i;
		int j;
		
		public Coup(int n, int ldPJ) {
			super();

			this.n=n;

			this.ldPJ = ldPJ;

			tcvV = 50; //taille verticale de la case vide
			tcvH = 50; //taille horizontale de la case vide

			tdcdr = ((tdjpH-50)/(n11+1))*(n11-2); //taille du carre de réponses

			tdjpH = ldPJ-tdcdr-15; //taille du JPanel horizontale

			ndlC=1; //nombre de lignes de cases.
			j=0;

			for (int i=0; i<n11; i++) {
				j++;
				if ((tcvH+10)*(j+1)+10 > (tdjpH)) { ndlC++; j=0; }
			}

			this.setPreferredSize(new Dimension((tdjpH)-50,(tcvV+10)*ndlC+12));

			//n11=10;
		}
		
		public void paintComponent (Graphics g) {
			int width = this.getWidth();
			int height = this.getHeight();

			int ncrH = 0; //nombre de cercles de réponse horizontaux.


			g.clearRect(0, 0, this.getWidth(), this.getHeight());

			tdcdr = (width/(n11+1)*(n11/4)); //taille du carre de réponses

			int tcr = 18; //taille des cercle de réponse.

			if (tdcdr==0) tdcdr=(tdjpH/5)*2;
			
			g.setColor(new Color(-6384846));
			g.fillRect(0,0,width,height);
			g.setColor(Color.BLACK);
			g.fillRect(4,4,width-6,height-6);
			g.setColor(Color.WHITE);
			g.fillRect(7,7,width-12,height-12);

			//if (n11>50||x<2) {g.drawImage(carreDesReponses[2],width-tdcdr-9/2,5,tdcdr,height-9, null); /*System.out.println("carreDesReponses[2]");*/ }

			//else if ((n11>30)||(x<5)) { g.drawImage(carreDesReponses[1],width-tdcdr-9/2,6,tdcdr,height-11, null); /*System.out.println("carreDesReponses[1]");*/ }

			//else { g.drawImage(carreDesReponses[0],width-tdcdr-9/2,7,tdcdr,height-12, null); /*System.out.println("carreDesReponses[0]");*/ }

			//if (n11>50||x<2) {
			if (false) {
				g.setColor(new Color(-16776961));
				g.fillRect(width-tdcdr-9/2,5,tdcdr,height-9);
				//g.drawImage(bouton3d,width-tdcdr-9/2,5,tdcdr,height-9, null);

				/*System.out.println("carreDesReponses[2]");*/
			}
			//else if ((n11>30)||(x<5)) {
			else if (false) {
				g.setColor(new Color(-29576));
				g.fillRect(width-tdcdr-9/2,6,tdcdr,height-11);
				//g.drawImage(bouton3d,width-tdcdr-9/2,6,tdcdr,height-11, null);

				/*System.out.println("carreDesReponses[1]");*/
			}

			else {
				g.setColor(new Color(-13421569));
				g.fillRect(width-tdcdr-9/2,7,tdcdr,height-12);
				//g.drawImage(bouton3d,width-tdcdr-9/2,7,tdcdr,height-12, null);
				/*System.out.println("carreDesReponses[0]");*/
			}

			i=0;
			
			while (i*((tdcdr/50)*4+tcr)<tdcdr) {
				i++;
				ncrH++;
			}

			//System.out.println("ncrH = "+ncrH);

			int j=1;
			int k1=1;

			for (int i=0; i<n11; i++) {
				/*if (tabReponses[n][i]==0) g.setColor(new Color(-10066330));
				else if (tabReponses[n][i]==1) g.setColor(new Color(-65536));
				else if (tabReponses[n][i]==2) g.setColor(new Color(-1));
				else if (tabReponses[n][i]==3) g.setColor(new Color(-16711936));
			
				g.fillOval(width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1),tcr,tcr);*/

				if (tabReponses[n][i]==0 && imageChargée [1]) g.drawImage(caseReponseVide, width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1)+1,tcr,tcr-1,null);
				else if (tabReponses[n][i]==0) {
					g.setColor(new Color(-13158601));
					g.fillOval(width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1)+1,tcr,tcr-1);
				}
				else if (tabReponses[n][i]==1) g.drawImage(pionRouge, width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1)+1,tcr,tcr-1,null);
				else if (tabReponses[n][i]==2) g.drawImage(pionBlanc, width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1)+1,tcr,tcr-1,null);
				else if (tabReponses[n][i]==3) g.drawImage(pionJaune, width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1)+1,tcr,tcr-1,null);
				
				//if (tabReponses[n][i]!=0) g.drawImage(petitPion3d,width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1)+1,tcr,tcr-1,null);
				//else g.drawImage(caseVideMarron,width-tdcdr-9/2+(tdcdr/(ncrH))*j-tcr/2,7+((tdcdr/50)*5)*k1+tcr*(k1-1)+1,tcr,tcr-1,null);

				j++;

				if (j>=ncrH) { j=1; k1++; }
			}			

			int ndlCr=1; //nombre de lignes de cases réponses.
			j=0;

			for (int i=0; i<n11; i++) {
				j++;
				if ((13+5)*(j+1)+10 > (tdcdr)) { ndlCr++; j=0; }
			}

			if (ndlCr>ndlC) ndlC=ndlCr;
			
			for (i=0; i<ndlCr; i++) {
				//g.fillOval(width-tdcdr-9/2+2+(13+5)*i,7+2+,13,13); System.out.println("carreDesReponses[0]");
			}


			//g.drawImage(carreDesReponses,(width/n11)*(n11-2),7,(width/n11),height-12, null);
			//g.drawImage(carreDesReponses,tdjpH-52,6,tdcdr+56,height-11, null);

			tdjpH = width-ldpdt-tdcdr-15; //taille du JPanel horizontale

			ndlC=1; //nombre de lignes de cases.
			j=0;

			for (int i=0; i<n11; i++) {
				j++;
				if ((tcvH+10)*(j+1)+10 > (tdjpH)) { ndlC++; j=0; }
			}

			this.setPreferredSize(new Dimension((tdjpH)-50,(tcvV+10)*ndlC+12));

			j=0;
			int k=0;
			for (i=0; i<n11; i++) {
				g.drawImage(caseVide,(tcvH+10)*k+10,(tcvV+10)*j+12,tcvH,tcvV, null);

				//if (jeuCommence) {
				if (tcoups[n][i]!=0) {
					Color rond = new Color(t10[tcoups[n][i]-1][0],t10[tcoups[n][i]-1][1],t10[tcoups[n][i]-1][2]);

					g.setColor(rond);
					g.fillOval((tcvH+10)*k+10-2,(tcvV+10)*j+12-2,tcvH+2,tcvV+2);
					g.drawImage(grandPionTransparent,(tcvH+10)*k+10-2,(tcvV+10)*j+12-2,tcvH+2,tcvV+2, null);
				}
				//}

				k++;
				
				if (j<ndlC && ((tcvH+10))*(k+1)+10>=(tdjpH)) {
					j++;
					k=0;
				}
			}
		}
	}

	class RedimBonus implements MouseListener {
		public RedimBonus () {
			System.out.print("");
		}
		
		public void mousePressed(MouseEvent e)  {
			repaint ();

			reDim();
		}
		
		public void mouseClicked(MouseEvent e) {
			System.out.print("");
		}

		public void mouseEntered(MouseEvent e) {
			System.out.print("");
		}

		public void mouseExited(MouseEvent e) {
			System.out.print("");
		}

		public void mouseReleased(MouseEvent e) {
			System.out.print("");
		}
	}

	class BoutonTransparentAction implements MouseListener {
		int n;

		public BoutonTransparentAction (int n) {
			this.n = n;
		}
		
		public void mousePressed(MouseEvent e)  {
			if (reDimBonus) {
				repaint ();

				reDim();
			}

			if (!jeuCommence) {
				if (abt) {
					//System.out.println("BoutonTransparentAction "+(n+1));

					Color essai = JColorChooser.showDialog(null,"JColorChooser Sample", null);
					if (essai!=null) {
						int [] th = new int [3];

						th[0]=essai.getRed();
						th[1]=essai.getGreen();
						th[2]=essai.getBlue();

						if (VerifieDoublons(t10,th)) {
							for (int i=0; i<3; i++) t10[this.n][i]=th[i];
							System.out.println("Ok1");

							repaint();
							reDim ();
						}
						else {
							f.afficheErreurCouleurs ();
						}
					}
				}
				else {
					System.out.println("!BoutonTransparentAction "+(n+1));
				}
			}
			else {
				if (avanceeDuTour<n11 && (m11!=3 || (m11==3 && premierCoups)) && m11!=4) {
					if (tourEnCours) {
						tcoups[x][avanceeDuTour]=(n+1);
						avanceeDuTour++;

						repaint();

						reDim();

						System.out.println((n+1));
					}
				}

				if (x>=c) {
					jeuCommence=false;

					if (m11==3 || m11==4) reDimBonus = true;
					
					boolean g1 = utilJeu.VerifieSolution(t1,tcoups[x-1]);
					
					if (!g1) {
						System.out.println("\nPerdu.");
						f.affichePerdu();

						afficheResultat2 ();						
					}

					if (g1) {
						System.out.println("\n\nC'est gagne !\n");
						f.afficheJeuGagne(x,c);

						int chrono = 0;
						if (clm) chrono = 1;

						utilFichiers.ajouteStat (n11,ndco,m11,h,x,calculTemps()/1000,chrono,true,tailleDesPolices);

						finDuJeu ();
					}
					
					afficheSolutions(t1);
					
					if (g1) {
						System.out.print("\nCoup");
						if (x>1) System.out.print("s");
						System.out.print(" : "+x+"/"+c+".");
					}
				}
			}
		}
		
		public void mouseClicked(MouseEvent e) {
			System.out.print("");
		}

		public void mouseEntered(MouseEvent e) {
			System.out.print("");
		}

		public void mouseExited(MouseEvent e) {
			System.out.print("");
		}

		public void mouseReleased(MouseEvent e) {
			System.out.print("");
		}
	}

	class Clique implements ActionListener {
		int n;

		public Clique (int n) {

			r1=0;
			r2=0;
			r3=0;

			this.n = n;
		}

		public void actionPerformed(ActionEvent e) { 
			int i;

			if (n==-1) { // cacheInfo
				if (pia) {
					tdpi = 0;

					pia = false;
				}
				else {
					tdpi = 75;

					pia = true;
				}

				repaint ();

				//reDim ();
				f.reDim1 ();
			}

			if (n==0) { //Jouer

				//commenceTemps (10000);

				for (i=0; i<5; i++) boutons[i].setVisible(false);
				for (i=5; i<9; i++) boutons[i].setVisible(true);

				reDim ();
			}

			if (n==1) { //Options
				placementB[0]+=17;

				placementB[2]+=2;

				placementB[8] = 9; //Quitter

				for (i=0; i<5; i++) boutons[i].setVisible(false);
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);

				boutons[34].setVisible(true);

				reDim ();
			}

			if (n==2) { //Instructions
				Instructions instructions = new Instructions ();
			}

			if (n==3) { //Scores
				reDim ();

				File ff2 = new File ("Sauvegardes"+File.separatorChar+"Statistiques.txt");

				if (!ff2.exists ()) f.afficheScoresNonTrouvés ();
				else {
					Scores scores = new Scores (0,0,0,false,tailleDesPolices);
				}
			}

			if (n==4) { //Crédits
				Credits credits = new Credits ();
			}

			if (n==5) { //Quitter
				if (f.exit ()==0) {
					if (!jeuCommence) {
						enregistreParametres ();

						System.exit(0);
					}
				}
			}

			if (n==6) { //Nouveau jeu
				//t10 = couleursAleat(63);

				placementB[0]+=10;

				System.out.println("ndco = "+ndco);

				for (i=0; i<ndco; i++) p2[i].setVisible(true);

				dessineCarreCouleurs = true;

				for (i=6; i<9; i++) boutons[i].setVisible(false);
				for (i=9; i<12; i++) boutons[i].setVisible(true);

				//for (i=0; i<ndco; i++) couleurs3d[i].setVisible(true);

				pJeu.setVisible(true);

				reDim ();
			}

			if (n==7) { //Charger une partie
				String [] s0 = ff1.detecteParties ();

				if ((s0.length==1) && (s0[0]=="")) f.afficheErreurPartieNonTrouvée ();
				else {
					cp = new ChargerPartie (tailleDesPolices);
				}
			}

			if (n==8) { // Retour

				//reinitialiseTemps ();

				for (i=5; i<9; i++) boutons[i].setVisible(false);
				for (i=0; i<6; i++) boutons[i].setVisible(true);

				reDim ();
			}

			if (n==9) { //Commencer
				x=0;

				if (clm) boutons[47].setVisible(true);

				/* Suppression du panel d'info */

				tdpi = 0;

				pia = false;

				repaint ();

				/*-----------------------------*/

				//reDim();
				f.reDim1 ();

				reDimBonus = false;

				if (m11==1 || m11==3) premierCoups=true; 

				tcoups = new int [c+1][n11];

				avanceeDuTour=0;

				if (m11!=1 && m11!=3) for (i=0; i<n11; i++) t1[i] = (int)(Math.random()*b+a);

				/*------------------------------------------------------------POUR LE TEST----------------------------------------------------------*/

				//for (i=0; i<n11; i++) System.out.println("t1["+i+"] = "+t1[i]); //affiche la réponse dans le terminal.

				/*----------------------------------------------------------------------------------------------------------------------------------*/

				if (avanceeDuTour<n11) if ((m11!=3 || (m11==3 && premierCoups)) && m11!=4) tourEnCours=true;
				

				r1=0;
				r2=0;
				r3=0;

				System.out.println("OK1");

				boutons[10].setBackground(couleurBouton);
				boutons[10].setEnabled(false);

				placementB[0]+=45;
				placementB[2]+=3;

				jeuCommence = true;

				if (clm) {
					reinitialiseTemps ();
					if (m11!=1 && m11!=3) commenceTemps(temps*1000);

					//System.out.println("temps*1000 = "+temps*1000);
				}

				reDim ();

				for (i=5; i<12; i++) if (i!=10) boutons[i].setVisible(false);
				for (i=30; i<34; i++) boutons[i].setVisible(true);

				if (m11==4) {
					if ((niveauDeLIA==3) && (n11<5 && b<5)){
						if (h!=3) niveauDeLIA=2; 
						else niveauDeLIA=1;
					}

					iaSMF = new IaNivFacile (n11,a,b);
					iaSMM = new IaNivMoyen (n11,a,b);
					if (n11<5 && b<5) iaCF = new IaCalculsFacile(n11,a,b); else iaCF = new IaCalculsFacile();
					if (n11<5 && b<5) iaCM = new IaCalculsMoyen(n11,a,b); else iaCM = new IaCalculsMoyen();
					if (n11<5 && b<5) iaCD = new IaCalculsDifficile(n11,a,b); else iaCD = new IaCalculsDifficile();


					t4 = new int [n11];
					th4 = new int [n11];
					t11 = new int [n11];

					finJeuIA = false;

					r1=0;
					r2=0;
					r3=0;


					while ((x<c) && (!utilJeu.VerifieSolution(t1,tcoups[x])) && !finJeuIA) {
						ia ();

						System.out.println("OK "+x);
					}

					//jeuT.stop();
				}
			}

			if (n==10) { //Changer les couleurs
				if (actb) {
					actb=false;
					abt=false;
					boutons[10].setBackground(couleurBouton);
				}
				else {
					actb=true;
					abt=true;
					boutons[10].setBackground(new Color(-16724941));
				}
			}

			if (n==11) { // Retour
				placementB[0]-=10;

				dessineCarreCouleurs = false;

				actb=false;
				abt=false;

				for (i=0; i<ndco; i++) p2[i].setVisible(false);

				for (i=6; i<9; i++) boutons[i].setVisible(true);
				for (i=9; i<12; i++) boutons[i].setVisible(false);

				//for (i=0; i<ndco; i++) couleurs3d[i].setVisible(false);

				f.reDim1 ();
				reDim ();
				f.reDim1 ();
			}

			if (n==12) { //nombre de cases
				boutons[34].setVisible(false);
				for (i=12; i<18; i++) boutons[i].setVisible(false);
				boutons[48].setVisible(false);


				boutons[39].setVisible(true);
				boutons[42].setVisible(true);

				champNombre [0].setVisible(true);
				texteOptions [0].setVisible(true);
				champNombre [0].setText(""+n11);
			}

			if (n==13) { //difficulté
				boutons[48].setVisible(false);
				for (i=12; i<18; i++) boutons[i].setVisible(false);
				for (i=18; i<22; i++) boutons[i].setVisible(true);

				reDim ();
			}

			if (n==14) { //mode de jeu
				placementB[0]+=35;

				boutons[48].setVisible(false);
				for (i=12; i<18; i++) boutons[i].setVisible(false);
				for (i=22; i<27; i++) boutons[i].setVisible(true);

				reDim ();
			}

			if (n==15) { // nombre de coups max
				boutons[34].setVisible(false);
				boutons[48].setVisible(false);
				for (i=12; i<18; i++) boutons[i].setVisible(false);


				boutons[41].setVisible(true);
				boutons[44].setVisible(true);
				boutons[46].setVisible(true);

				champNombre [2].setVisible(true);
				texteOptions [2].setVisible(true);
				champNombre [2].setText(c+"");
			}

			if (n==16) { //contre la montre
				for (i=12; i<18; i++) boutons[i].setVisible(false);

				boutons[34].setVisible(false);
				boutons[48].setVisible(false);

				boutons[29].setVisible(true);

				for (i=35; i<37; i++) boutons[i].setVisible(true);

				reDim ();
			}

			if (n==17) { // Retour
				placementB[0]-=17;

				placementB[2]-=2;

				placementB[8] = 7; //Quitter

				for (i=0; i<5; i++) boutons[i].setVisible(true);
				for (i=12; i<18; i++) boutons[i].setVisible(false);
				boutons[48].setVisible(false);

				boutons[34].setVisible(false);

				reDim ();
			}

			if (n==18) { //Facile
				h=1;

				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=18; i<22; i++) boutons[i].setVisible(false);

				metAJourInfos ();

				reDim ();
			}

			if (n==19) { //Moyen
				h=2;

				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=18; i<22; i++) boutons[i].setVisible(false);

				metAJourInfos ();

				reDim ();
			}

			if (n==20) { //Difficile
				h=3;
				
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=18; i<22; i++) boutons[i].setVisible(false);

				metAJourInfos ();

				reDim ();
			}

			if (n==21) { // Retour
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=18; i<22; i++) boutons[i].setVisible(false);

				reDim ();
			}

			if (n==22) { // h/h
				m11=1;

				placementB[0]-=35;

				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=22; i<27; i++) boutons[i].setVisible(false);

				metAJourInfos ();

				reDim ();
			}

			if (n==23) { // h/o
				m11=2;

				placementB[0]-=35;

				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=22; i<27; i++) boutons[i].setVisible(false);

				metAJourInfos ();

				reDim ();
			}

			if (n==24) { // o/h
				m11=3;

				placementB[0]-=35;

				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=22; i<27; i++) boutons[i].setVisible(false);

				metAJourInfos ();

				reDim ();
			}

			if (n==25) { // o/o
				m11=4;

				placementB[0]-=35;

				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=22; i<27; i++) boutons[i].setVisible(false);

				metAJourInfos ();

				reDim ();
			}

			if (n==26) { // Retour
				placementB[0]-=35;

				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);
				for (i=22; i<27; i++) boutons[i].setVisible(false);

				reDim ();
			}

			if (n==27) { //Activer
				clm = true;

				ldpdt = 20;

				for (i=27; i<29; i++) boutons[i].setVisible(false);
				boutons[38].setVisible(false);

				for (i=35; i<37; i++) boutons[i].setVisible(true);
				boutons[29].setVisible(true);

				boutons[37].setVisible(false);

				barrePanel.setVisible(true);

				f.setClm(true);

				metAJourInfos ();

				repaint();

				f.reDim1 ();
			}

			if (n==28) { //Désactiver
				clm = false;

				ldpdt = 0;

				for (i=27; i<29; i++) boutons[i].setVisible(false);
				boutons[38].setVisible(false);

				for (i=35; i<37; i++) boutons[i].setVisible(true);
				boutons[29].setVisible(true);

				boutons[37].setVisible(false);

				barrePanel.setVisible(false);

				f.setClm(false);

				metAJourInfos ();

				repaint();

				f.reDim1 ();
			}

			if (n==29) { // Retour
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[34].setVisible(true);
				boutons[48].setVisible(true);

				for (i=35; i<37; i++) boutons[i].setVisible(false);
				boutons[29].setVisible(false);

				reDim ();
			}

			if (n==30) {  //Valider
				if (avanceeDuTour>=n11) {
					tourEnCours=false;
					avanceeDuTour=0;
				}

				if (!tourEnCours) {

					x++;

					if (premierCoups) {
						if (clm && (m11==1 || m11==3) && x==1) {
							commenceTemps(temps*1000);
							System.out.println("ok1.");
						}

						if (!tourEnCours) {
							x=0;

							for (i=0; i<n11; i++) t1[i] = tcoups[0][i];

							tcoups = new int [c+1][n11];

							remove(pJeu);

							pJeu = new PJeu(f);

							add(pJeu);

							premierCoups=false;

							for (i=0; i<n11; i++) System.out.println("t1["+i+"] = "+t1[i]);

							tourEnCours=true;

							repaint();

							//reDim();
							f.reDim1();
						}
					}else {

						System.out.println("x = "+x);
						tourEnCours=true;

						if (h==1) {
							utilJeu.VerifieFacile(t1,tcoups[x-1]);
							tabReponses[x-1] = utilJeu.VerifieFacileOrdi(t1,tcoups[x-1]);
						}

						if (h==2) {
							t11 = utilJeu.VerifieMoyen(a,b,n11,t1,tcoups[x-1]); r1= t11[0]; r2= t11[1];

							tabReponses[x-1] = compteReponses2 (r1,r2);
							
							int a6 = (n-(r1+r2));
							
							System.out.print("\nIl y a "+r1+" chiffre");
							
							if (r1>1) System.out.print("s");
							
							System.out.print(" bien place");
							
							if (r1>1) System.out.print("s");
							
							System.out.print(" et "+r2+" chiffre");
							
							if (r2>1) System.out.print("s");
							
							System.out.print(" mal place");
							
							if (r2>1) System.out.print("s");
							
							System.out.print(". Le reste ("+a6+" chiffre");
							
							if (a6>1) System.out.print("s");
							
							System.out.print(") n'est pas dans ");
							
							if (n>1) System.out.print("les solutions");
							
							else System.out.print("la solution");
							
							System.out.println(".");
							
							//Affichage des résultats pour le joueur.	
						}
						
						if (h==3) {
							r3 = utilJeu.VerifieDifficile(a,b,n11,t1,tcoups[x-1]);

							tabReponses[x-1] = compteReponses3 (r3);

							System.out.print("\nIl y a "+r3+" chiffre");
							
							if (r3>1) System.out.print("s");
							
							System.out.print(" bien ou mal place");
							
							if (r3>1) System.out.print("s");
							
							System.out.print(" dans ");
							
							if (n>1) System.out.print("les solutions");
							
							else System.out.print("la solution");
							
							System.out.println(".");

							//Affichage des résultats pour le joueur.
						}

						if (coupsIllimités) {
							if (x>c-2) {
								c=c*2;
								
								coups = agrandieTab(coups);
								tcoups = utilFenetre.agrandieTab(tcoups);
								tabReponses = utilFenetre.agrandieTab(tabReponses);
							}				
						}
						
						if ((x>=c) || (utilJeu.VerifieSolution(t1,tcoups[x-1]))) {
							jeuCommence=false;

							if (clm) {
								paused = false;
								boutons[47].setText("Pause");
								pJeu.setVisibleJeuPause(false);	
							}
							
							boolean g1 = utilJeu.VerifieSolution(t1,tcoups[x-1]);
							
							if (!g1) {
								System.out.println("\nPerdu.");

								if (clm) {
									tempsT.suspend();

									int tmp10 = calculTemps();

									String s50 = calculTempsS ();

									if (tmp10>0) f.affichePerduContreLaMontre(s50);
									else f.affichePerdu();

									reinitialiseTemps ();
								}
								else {
									f.affichePerdu();
								}

								afficheResultat2 ();							
							}

							if (g1) {
								System.out.println("\n\nC'est gagne !\n");

								int chrono = 0;
								if (clm) chrono = 1;

								utilFichiers.ajouteStat (n11,ndco,m11,h,x,calculTemps()/1000,chrono,true,tailleDesPolices);

								if (clm) {
									tempsT.suspend();

									int tmp10 = calculTemps();

									String s50 = calculTempsS ();

									if (tmp10>0) f.afficheJeuGagneContreLaMontrePlus(x,c,s50);
									else f.afficheJeuGagne(x,c);

									reinitialiseTemps ();
								}
								else {
									f.afficheJeuGagne(x,c);
								}

								finDuJeu ();
							}
							
							afficheSolutions(t1);
							
							if (g1) {
								System.out.print("\nCoup");
								if (x>1) System.out.print("s");
								System.out.print(" : "+x+"/"+c+".");
							}
						}

						nouveauCoup (x);
						//reDim();
						f.reDim1();

					}
				}

				if ((m11==3 && !premierCoups)) {
					if ((niveauDeLIA==3) && (n11<5 && b<5)){
						if (h!=3) niveauDeLIA=2; 
						else niveauDeLIA=1;
					}

					iaSMF = new IaNivFacile (n11,a,ndco);
					iaSMM = new IaNivMoyen (n11,a,ndco);
					if (n11<5 && b<5) iaCF = new IaCalculsFacile(n11,a,ndco); else iaCF = new IaCalculsFacile();
					if (n11<5 && b<5) iaCM = new IaCalculsMoyen(n11,a,ndco); else iaCM = new IaCalculsMoyen();
					if (n11<5 && b<5) iaCD = new IaCalculsDifficile(n11,a,ndco); else iaCD = new IaCalculsDifficile();


					t4 = new int [n11];
					th4 = new int [n11];
					t11 = new int [n11];

					finJeuIA = false;

					r1=0;
					r2=0;
					r3=0;

					//jeuT.start();

					while ((x<c) && (!utilJeu.VerifieSolution(t1,tcoups[x])) && !finJeuIA) {
						ia ();

						System.out.println("OK "+x);
					}

					//jeuT.stop();
				}
			}
			
			if (n==31) {  // Retour arrière
				if (avanceeDuTour>0) {
					tcoups[x][avanceeDuTour-1]=0;
					avanceeDuTour--;

					repaint();
					
					reDim();
				}

				System.out.println(avanceeDuTour);
			}

			if (n==32) {  // Arreter et voir les réponses
				if (clm) {
					tempsT.suspend();
					paused = true;
					boutons[47].setText("Reprendre");
					pJeu.setVisibleJeuPause(true);
				}

				afficheSolutions(t1);		

				int gjdfjk=f.quitteLeJeu ();

				if (gjdfjk==0) {
					if (clm) {
						reinitialiseTemps ();

						paused = false;
						boutons[47].setText("Pause");
						pJeu.setVisibleJeuPause(false);
					}	

					System.out.print("\nCoups");
					System.out.print(" avant abandon : "+x+"/"+c+".\n");

					f.afficheCoupsAvantAbandon(x,c);

					afficheResultat ();
				}

				if (clm) {
					paused = false;
					boutons[47].setText("Pause");
					pJeu.setVisibleJeuPause(false);
					tempsT.resume();
				}
			}

			if (n==33) {  // OK
				if (clm) reinitialiseTemps ();

				/* Activation du panel d'info */

				tdpi = 75;

				pia = true;

				repaint ();

				//reDim ();
				f.reDim1 ();

				/*-----------------------------*/

				finDuJeu ();
			}

			if (n==34) {  // Nombre de couleurs
				boutons[34].setVisible(false);
				boutons[48].setVisible(false);
				for (i=12; i<18; i++) boutons[i].setVisible(false);

				boutons[40].setVisible(true);
				boutons[43].setVisible(true);

				champNombre [1].setVisible(true);
				texteOptions [1].setVisible(true);
				champNombre [1].setText(""+ndco);
			}

			if (n==35) {  // Temps
				boutons[29].setVisible(false);
				for (i=35; i<37; i++) boutons[i].setVisible(false);

				boutons[37].setVisible(true);
				boutons[45].setVisible(true);

				for (i=0; i<3; i++) {
					champTemps[0].setText(heures+"");
					champTemps[1].setText(minutes+"");
					champTemps[2].setText(secondes+"");

					champTemps[i].setVisible(true);
					texteTemps[i].setVisible(true);
					texteOptions[3].setVisible(true);
				}
			}

			if (n==36) {  // Activer/Désactiver
				for (i=27; i<29; i++) boutons[i].setVisible(true);
				boutons[38].setVisible(true);

				for (i=35; i<37; i++) boutons[i].setVisible(false);
				boutons[29].setVisible(false);

				reDim ();
			}

			if (n==37) {  // Ok
				boutons[37].setVisible(false);

				boutons[45].setVisible(false);
				for (i=0; i<3; i++) {
					champTemps[i].setVisible(false);
					texteTemps[i].setVisible(false);
					texteOptions[3].setVisible(false);
				}

				boutons[29].setVisible(true);
				for (i=35; i<37; i++) boutons[i].setVisible(true);

				String [] champTempsS = new String [3];

				for (i=0; i<3; i++) champTempsS [i] = champTemps[i].getText();

				if (utilFenetre.TestNombre(champTempsS[0]) && utilFenetre.TestNombre(champTempsS[1]) && utilFenetre.TestNombre(champTempsS[2])) {
					heures = utilFenetre.convertNombre(champTempsS[0]);
					minutes = utilFenetre.convertNombre(champTempsS[1]);
					secondes = utilFenetre.convertNombre(champTempsS[2]);
				}
			}

			if (n==38) {  // Retour
				for (i=27; i<29; i++) boutons[i].setVisible(false);
				boutons[38].setVisible(false);

				for (i=35; i<37; i++) boutons[i].setVisible(true);
				boutons[29].setVisible(true);
			}

			if (n==39) {  // Retour
				boutons[34].setVisible(true);
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);

				boutons[39].setVisible(false);
				boutons[42].setVisible(false);

				champNombre [0].setVisible(false);
				texteOptions [0].setVisible(false);
			}

			if (n==40) {  // Retour
				boutons[34].setVisible(true);
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);

				boutons[40].setVisible(false);
				boutons[43].setVisible(false);

				champNombre [1].setVisible(false);
				texteOptions [1].setVisible(false);
			}

			if (n==41) {  // Retour
				boutons[34].setVisible(true);
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);

				boutons[41].setVisible(false);
				boutons[44].setVisible(false);
				boutons[46].setVisible(false);

				champNombre [2].setVisible(false);
				texteOptions [2].setVisible(false);			
			}

			if (n==42) {  // Ok
				boutons[34].setVisible(true);
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);

				boutons[39].setVisible(false);
				boutons[42].setVisible(false);

				champNombre [0].setVisible(false);
				texteOptions [0].setVisible(false);

				String flkfdm0 = champNombre [0].getText();

				if (!utilFenetre.TestNombre(flkfdm0)) {
					System.out.println(flkfdm0);

					f.afficheMessage ("Veuillez entrer un nombre de cases","Entrer un nombre de couleurs",'i');
				}
				else {
					int klgjlkd0 = utilFenetre.convertNombre (flkfdm0);

					if (klgjlkd0<1 || klgjlkd0>500) f.erreurOption("cases",3,500);
					else {
						System.out.println(klgjlkd0);

						p1 = new P1 (f,klgjlkd0,m11,ndco,h,c,clm,heures,minutes,secondes,nombreDePartiesJouées,coupsIllimités,tailleDesPolices);
					}
				}

				metAJourInfos ();
			}

			if (n==43) {  // Ok
				boutons[34].setVisible(true);
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);


				boutons[40].setVisible(false);
				boutons[43].setVisible(false);

				champNombre [1].setVisible(false);
				texteOptions [1].setVisible(false);

				String flkfdm1 = champNombre [1].getText();

				if (!utilFenetre.TestNombre(flkfdm1)) {
					System.out.println(flkfdm1);

					f.afficheMessage ("Veuillez entrer un nombre de couleurs","Entrer un nombre de couleurs",'i');
				}
				else {
					int klgjlkd1 = utilFenetre.convertNombre (flkfdm1);

					if (klgjlkd1<1 || klgjlkd1>56) f.erreurOption("couleurs",3,56);
					else {
						System.out.println(klgjlkd1);

						int ndco2 = klgjlkd1;

						ndco = ndco2;

						//p1 = new P1(f,n11,m11,h,c,clm,heures,minutes,secondes,ndco2);

						/* Ce bout de code, permet de mettre à jour
						*la taille de p2, qui n'est pas mise à jour par
						*le constructeur de p1.
						*/

						t10 = couleursAleat(ndco);

						actionBT = new BoutonTransparentAction [ndco];

						p2 = new P2 [ndco];

						couleurs3d = new JButton[ndco];

						for (i=0; i<ndco; i++) {
							//System.out.println("i = "+i);		
							p2[i]= new P2();
							actionBT[i] = new BoutonTransparentAction(i);
							add(p2[i]);
							p2[i].addMouseListener(actionBT[i]);
							p2[i].setVisible(false); 
						}

						/*---------------------------------------------------------*/

						System.out.println(ndco);
					}
				}

				metAJourInfos ();
			}


			if (n==44) {  // Ok
				boutons[34].setVisible(true);
				for (i=12; i<18; i++) boutons[i].setVisible(true);
				boutons[48].setVisible(true);

				boutons[41].setVisible(false);
				boutons[44].setVisible(false);
				boutons[46].setVisible(false);

				champNombre [2].setVisible(false);
				texteOptions [2].setVisible(false);

				String flkfdm2 = champNombre [2].getText();

				if (!utilFenetre.TestNombre(flkfdm2)) {
					System.out.println(flkfdm2);

					f.afficheMessage ("Veuillez entrer un nombre de coups","Entrer un nombre de couleurs",'i');
				}
				else {
					int klgjlkd2 = utilFenetre.convertNombre (flkfdm2);

					if (klgjlkd2<1) f.erreurOption("coups",1,100000000);
					else {
						System.out.println(klgjlkd2);

						c = klgjlkd2;

						System.out.println(c);

						tcoups = new int [c+1][n11];

						tabReponses = new int [c+1][n11];

						coups = new Coup [c+1];

						//p1 = new P1(f,n11,m11,h,c,clm,heures,minutes,secondes,ndco);
					}
				}

				metAJourInfos ();
			}

			if (n==45) {  // Retour
				boutons[37].setVisible(false);
				boutons[45].setVisible(false);

				for (i=0; i<3; i++) {
					champTemps[i].setVisible(false);
					texteTemps[i].setVisible(false);
					texteOptions[3].setVisible(false);
				}

				boutons[29].setVisible(true);
				for (i=35; i<37; i++) boutons[i].setVisible(true);
			}

			if (n==46) {  // Activer/Désactiver : nombre de coups illimité
				if (coupsIllimités) {
					coupsIllimités = false;
					boutons[46].setBackground(couleurBouton);
					f.setCoupsIllimités (false);
				}
				else  {
					coupsIllimités = true;
					boutons[46].setBackground(new Color(-16724941));
					f.setCoupsIllimités (true);
				}

				metAJourInfos ();
			}

			if (n==47) { //Pause
				if (!paused) {
					tempsT.suspend();
					paused = true;
					boutons[47].setText("Reprendre");
					pJeu.setVisibleJeuPause(true);
				}
				else {
					paused = false;
					boutons[47].setText("Pause");
					pJeu.setVisibleJeuPause(false);
					tempsT.resume();
				}

				repaint ();
			}

			if (n==48) { //Options graphiques
				TailleDesPolices ftaillesDesPolices = new TailleDesPolices();
			}
		}
	}

	public void ia () {
		int n = n11;

		if (h==1) {
			if (niveauDeLIA==1) {
				for (i=0; i<n11; i++) tcoups[x][i] = (int)(Math.random()*b+a);
			}

			if (niveauDeLIA==2) {
				th4 = iaSMF.iAs1Facile(th4,t4);

				for (i=0; i<th4.length; i++) System.out.println("th4["+i+"] = "+th4[i]);
				for (i=0; i<n11; i++) {
					if (th4[i]>ndco) tcoups[x][i] = ndco;
					else if (th4[i]<a) tcoups[x][i] = a;
					else tcoups[x][i] = th4[i];
				}
			}

			if (niveauDeLIA==3) {
				tcoups[x] = iaCF.ia(t4);
			}
		}

		if (h==2) {
			if (niveauDeLIA==1) {
				for (i=0; i<n11; i++) tcoups[x][i] = (int)(Math.random()*b+a);
			}

			if (niveauDeLIA==2) {
				th4 = iaSMM.iAs1Moyen(t11);

				for (i=0; i<th4.length; i++) System.out.println("th4["+i+"] = "+th4[i]);
				for (i=0; i<n11; i++) {	
					if (th4[i]>ndco) tcoups[x][i] = ndco;
					else if (th4[i]<a) tcoups[x][i] = a;
					else tcoups[x][i] = th4[i];
				}
			}

			if (niveauDeLIA==3) {
				tcoups[x] = iaCM.ia(t11);
			}

		}

		if (h==3) {
			if (niveauDeLIA==1) {
				for (i=0; i<n11; i++) tcoups[x][i] = (int)(Math.random()*b+a);
			}
			
			if (niveauDeLIA==2) {
				for (i=0; i<n11; i++) tcoups[x][i] = (int)(Math.random()*b+a);
			}

			if (niveauDeLIA==3) {
				tcoups[x] = iaCD.ia(r3);
			}
		}

		repaint();

		nouveauCoup (x);
		
		//reDim();

		for (i=0; i<tcoups[x].length; i++) System.out.println("tcoups["+x+"]["+i+"] = "+tcoups[x][i]);

		x++;

		if (h==1) {
			utilJeu.VerifieFacile(t1,th4);
			t4 = utilJeu.VerifieFacileOrdi(t1,th4);
			tabReponses[x-1] = t4;
			for (i=0; i<t4.length; i++) System.out.println("t4["+i+"] = "+t4[i]);
		}

		if (h==2) {
			t11 = utilJeu.VerifieMoyen(a,b,n11,t1,tcoups[x-1]); r1= t11[0]; r2= t11[1];
			
			tabReponses[x-1] = compteReponses2(r1,r2);

			int a6 = (n-(r1+r2));
			
			System.out.print("\nIl y a "+r1+" chiffre");
			
			if (r1>1) System.out.print("s");
			
			System.out.print(" bien place");
			
			if (r1>1) System.out.print("s");
			
			System.out.print(" et "+r2+" chiffre");
			
			if (r2>1) System.out.print("s");
			
			System.out.print(" mal place");
			
			if (r2>1) System.out.print("s");
			
			System.out.print(". Le reste ("+a6+" chiffre");
			
			if (a6>1) System.out.print("s");
			
			System.out.print(") n'est pas dans ");
			
			if (n>1) System.out.print("les solutions");
			
			else System.out.print("la solution");
			
			System.out.println(".");
			
			//Affichage des résultats pour le joueur.	
		}
		
		if (h==3) {
			r3 = utilJeu.VerifieDifficile(a,b,n11,t1,tcoups[x-1]);

			tabReponses[x-1] = compteReponses3(r3);

			System.out.print("\nIl y a "+r3+" chiffre");
			
			if (r3>1) System.out.print("s");
			
			System.out.print(" bien ou mal place");
			
			if (r3>1) System.out.print("s");
			
			System.out.print(" dans ");
			
			if (n>1) System.out.print("les solutions");
			
			else System.out.print("la solution");
			
			System.out.println(".");

			//Affichage des résultats pour le joueur.
		}

		if (coupsIllimités) {
			if (x>c-2) {
				c=c*2;
				
				coups = agrandieTab(coups);
				tcoups = utilFenetre.agrandieTab(tcoups);
				tabReponses = utilFenetre.agrandieTab(tabReponses);
			}							
		}

		if ((x>=c) || (utilJeu.VerifieSolution(t1,tcoups[x-1]))) {
			finJeuIA = true;

			if (clm) {
				paused = false;
				boutons[47].setText("Pause");
				pJeu.setVisibleJeuPause(false);	
			}

			boolean g1 = utilJeu.VerifieSolution(t1,tcoups[x-1]);
			
			if (!g1) {
				System.out.println("\nPerdu.");

				if (clm) {
					tempsT.suspend();

					int tmp10 = calculTemps();

					String s50 = calculTempsS ();

					if (tmp10>0) f.affichePerduContreLaMontre(s50);
					else f.affichePerdu();

					reinitialiseTemps ();
				}
				else {
					f.affichePerdu();
				}

				afficheResultat3 ();								
			}

			if (g1) {
				System.out.println("\n\nC'est gagne !\n");

				int chrono = 0;
				if (clm) chrono = 1;

				utilFichiers.ajouteStat (n11,ndco,m11,h,x,calculTemps()/1000,chrono, false,tailleDesPolices);

				if (clm) {
					tempsT.suspend();

					int tmp10 = calculTemps();

					String s50 = calculTempsS ();

					if (tmp10>0) f.afficheJeuGagneContreLaMontrePlus(x,c,s50);
					else f.afficheJeuGagne(x,c);

					reinitialiseTemps ();
				}
				else {
					f.afficheJeuGagne(x,c);
				}

				attendGangne ();
			}
			
			afficheSolutions(t1);
			
			if (g1) {
				System.out.print("\nCoup");
				if (x>1) System.out.print("s");
				System.out.print(" : "+x+"/"+c+".");
			}
		}
	}

	public void afficheResultat () {
		tcoups[x+1]=t1;

		coups[x+1] = new Coup(x+1,ldPJ);
		box.add(coups[x+1]);
		
		repaint();

		for (i=30; i<33; i++) boutons[i].setVisible(false);
		boutons[33].setVisible(true);

		reDim();
	}

	public void attendGangne () {
		for (i=30; i<33; i++) boutons[i].setVisible(false);
		boutons[33].setVisible(true);

		reDim();
	}

	public void afficheResultat2 () {
		tcoups[x]=t1;
		
		repaint();

		for (i=30; i<33; i++) boutons[i].setVisible(false);
		boutons[33].setVisible(true);

		reDim();
	}

	public void afficheResultat3 () {
		tcoups[x]=t1;

		coups[x] = new Coup(x,ldPJ);
		box.add(coups[x]);
		
		repaint();

		for (i=30; i<33; i++) boutons[i].setVisible(false);
		boutons[33].setVisible(true);

		reDim();
	}

	public void finDuJeu () {
		placementB[0]=145;
		placementB[2]=9;

		nombreDePartiesJouées++;

		if (clm) {
			paused = false;
			boutons[47].setText("Pause");
			pJeu.setVisibleJeuPause(false);	
		}
		
		dessineCarreCouleurs = false;
		
		actb=false;
		abt=false;
		
		for (i=0; i<ndco; i++) p2[i].setVisible(false);
		
		boutons[10].setEnabled(true);
		
		boutons[10].setVisible(false);
		if (clm) boutons[47].setVisible(false);

		for (i=30; i<34; i++) boutons[i].setVisible(false);		
		boutons[10].setVisible(false);

		for (i=0; i<6; i++) boutons[i].setVisible(true);
		
		jeuCommence=false;

		this.remove(pJeu);

		pJeu = new PJeu(f);

		this.add(pJeu);

		//reDim ();
		f.reDim1 ();

		finJeuIA = false;

		tcoups = new int[c+1][n11];
		tabReponses = new int[c+1][n11];

		//pJeu.setVisible(false);
		
		box.setLayout(new BoxLayout(pJeu, BoxLayout.Y_AXIS));

		if (clm) reinitialiseTemps ();

		//reDim ();
		f.reDim1 ();
	}

	public static int [] compteReponses2 (int r1, int r2) {
		int i, k=r1+r2;

		int [] t = new int [n11];

		for (i=0; i<r1; i++) t[i]=1;
		for (i=r1; i<k&&i<t.length; i++) t[i]=2;

		return t;
	}

	public static int [] compteReponses3 (int r3) {
		int i;

		int [] t = new int [n11];

		for (i=0; i<r3&&i<t.length; i++) t[i]=3;

		return t;
	}

	public void nouveauCoup (int n) {
		coups[x+1] = new Coup(n,ldPJ);
		box.add(coups[x+1]);

		JScrollBar bar = jsp1.getVerticalScrollBar();

		bar.setValue(jsp1.getHeight());

		repaint();
	}

	public static Coup [] agrandieTab (Coup [] t) {
		int i;

		Coup [] t1 = new Coup[t.length*2];

		for (i=0; i<t.length; i++) t1[i] = t[i];

		return t1;
	}

	public static int [][] couleursAleat (int n) {
		int i, j,k,l;
		int [] a1 = new int [3];

		int [][] t1 = new int [n][3];
		
		for (i=0; i<t1.length; i++) {
			while (!VerifieDoublons(t1,a1) || (a1[0]==0&&a1[1]==0&&a1[3]==0)) {
				for (j=0; j<3; j++) {
					a1[j] = (int)(Math.random()*256);
				}
			}

			for (k=0; k<a1.length; k++) t1[i][k]=a1[k];
		}

		return t1;
	}

	public void pause () {
		tempsT.suspend();
		paused = true;
		boutons[47].setText("Reprendre");
		pJeu.setVisibleJeuPause(true);
	}

	public void reprendre () {
		paused = false;
		boutons[47].setText("Pause");
		pJeu.setVisibleJeuPause(false);
		tempsT.resume();
	}

	public void reinitialiseTemps () {
		tempsT = new Thread(new Traitement());
		bar.setValue(maxTemps);

		int [] ttemps = new int [3];

		ttemps[0] = heures;
		ttemps[1] = minutes;
		ttemps[2] = secondes;

		temps = utilDate.convertitEnSecondes(ttemps);
	}

	public int calculTemps () {
		int n = (maxTemps-iTemps)*(temps/maxTemps);

		System.out.println("n = "+n);

		return n;
	}

	public String calculTempsS () {
		String s = utilDate.afficheTemps((utilDate.convertitSecondes((calculTemps ())/1000)));

		return s;
	}

	public void commenceTemps (int temps) {
		this.temps = temps;
		tempsT.start();
	}

	class Dors implements Runnable{  
		public void run(){
			int i;

			try {
				for (i=0; i<500; i++) {
					tempsT1.sleep(1);

					if (i%100==0) {
						revalidate ();

						//System.out.println(i);
					}
				}

			}

			catch (InterruptedException  e)  {
				e.printStackTrace();
			}
		}
	}
	
	class Traitement implements Runnable{   
		public void run(){
			for(iTemps=maxTemps; iTemps>-1; iTemps--){
				bar.setValue(iTemps);
				try {
					tempsT.sleep(temps/maxTemps);  
				} catch (InterruptedException  e)  {
					e.printStackTrace();
				}
			}

			jeuCommence = false;

			paused = false;
			boutons[47].setText("Pause");
			pJeu.setVisibleJeuPause(false);	

			if (x<c) f.affichePerduContreLaMontreCoupsRestants (x,c);
			else f.affichePerdu ();

			afficheResultat2 ();

			reinitialiseTemps ();

			//finDuJeu ();
		}    
	}

	class AfficheIA implements Runnable{   
		public void run(){
			while (jeuCommence) {

				try {
					jeuT.sleep(500); 
				} 
				catch (InterruptedException  e)  {
					System.out.println("Erreur avec : jeuT.sleep(500)");
					e.printStackTrace();
				}				

				repaint();

				reDim();
			}
		}    
	}

	public static void afficheSolutions (int[] t){
		System.out.print("\nVoici ");
		
		if (t.length>1) System.out.print("les solutions");

		else System.out.print("la solution");

		System.out.println(" : \n");

		for (int i=0; i<t.length; i++) { System.out.println("Nombre "+(i+1)+" = "+t[i]); }
	}

	public static boolean VerifieDoublons (int [][] t1, int [] t2) {
		int i, j, cmp=0;

		for (i=0; i<t1.length; i++) {
			cmp=0;

			for (j=0; j<t2.length; j++) {
				if (t1[i][j]==t2[j]) cmp++;
			}

			if (cmp==t2.length) return false;
		}

		return true;
	}

	public void dessineCarreCouleurs (Graphics g, int longueur, int h, int f, int getWidth, int getHeight) {
		//int getWidth = this.getWidth();
		//int getHeight = this.getHeight();
		//int longueur = placementB[0]+3;
		//int h = 230;
		//int f = ((getHeight*2+100)/20+15);

		Color gris = new Color(-3355444);

		//Graphics2D g2 = (Graphics2D)g;
		//GradientPaint grad = new GradientPaint(getWidth()/2,getHeight()/2,Color.WHITE,getWidth(),getHeight(),Color.BLACK);

		g.setColor(Color.BLUE);
		g.fillRect((getWidth-tcd)+tcd/2-(longueur)/2-2,(getHeight*2+50)/placementB[2]-55/2+f-2,longueur+4, h+4);
		g.setColor(gris);
		g.fillRect((getWidth-tcd)+tcd/2-(longueur)/2,(getHeight*2+50)/placementB[2]-55/2+f,longueur, h);
		g.setColor(Color.WHITE);
		g.fillRect((getWidth-tcd)+tcd/2-(longueur)/2+2,(getHeight*2+50)/placementB[2]-55/2+f+2,longueur-4, h-4);

		int ndl = 9; //nombre de lignes
		int ndc = 7; //nombre de colonnes

		int hh=0;
		
		for (int i=0; i<ndl; i++) {
			for (int j=0; j<ndc; j++) {

				//t10 = couleursAleat(63);

				Color c5 = new Color(-3355444);

				if (i*ndc+j<ndco) {
					Color rond = new Color(t10[i*ndc+j][0],t10[i*ndc+j][1],t10[i*ndc+j][2]);

					g.setColor(c5);
					g.fillOval((getWidth-tcd)+tcd/2-(longueur)/2+(longueur/(ndc+1))*(j+1)-13/2,(getHeight*2+50)/placementB[2]-55/2+f+(h/(ndl+1))*(i+1)-13/2,13,13);  // Ombre
					
					g.setColor(rond);
					g.fillOval((getWidth-tcd)+tcd/2-(longueur)/2+(longueur/(ndc+1))*(j+1)-13/2,(getHeight*2+50)/placementB[2]-55/2+f+(h/(ndl+1))*(i+1)-13/2,13,13);
					g.drawImage(petitPion3d, (getWidth-tcd)+tcd/2-(longueur)/2+(longueur/(ndc+1))*(j+1)-13/2, (getHeight*2+50)/placementB[2]-55/2+f+(h/(ndl+1))*(i+1)-13/2, 13, 13+1, null);
					p2[hh].setBounds((getWidth-tcd)+tcd/2-(longueur)/2+(longueur/(ndc+1))*(j+1)-13/2, (getHeight*2+50)/placementB[2]-55/2+f+(h/(ndl+1))*(i+1)-13/2, 13, 13+1);
					hh++;
				}
			}
		}

		/*int fff=0;

		for (int i=0; i<ndl; i++) {
			for (int j=0; j<ndc; j++) {

				//t10 = couleursAleat(63);

				Color c5 = new Color(-3355444);

				if (i*ndc+j<ndco) {
					Color rond = new Color(t10[i*ndc+j][0],t10[i*ndc+j][1],t10[i*ndc+j][2]);

					g.setColor(c5);
					g.fillOval((getWidth-tcd)+tcd/2-(longueur)/2+(longueur/(ndc+1))*(j+1)-13/2,(getHeight*2+50)/placementB[2]-55/2+f+(h/(ndl+1))*(i+1)-13/2,13,13);  // Ombre
						
					g.setColor(rond);
					g.fillOval((getWidth-tcd)+tcd/2-(longueur)/2+(longueur/(ndc+1))*(j+1)-13/2,(getHeight*2+50)/placementB[2]-55/2+f+(h/(ndl+1))*(i+1)-13/2,13,13);
					couleurs3d[fff].setBounds((getWidth-tcd)+tcd/2-(longueur)/2+(longueur/(ndc+1))*(j+1)-13/2, (getHeight*2+50)/placementB[2]-55/2+f+(h/(ndl+1))*(i+1)-13/2, 13, 13+1);
					fff++;
				}
			}
		}*/
	}

	public void premierCoupHvsH (Graphics g) {
		int getWidth = f.getWidth();
		int getHeight = getHeight();

		ldPJ = f.getWidth()-tcd-ldpdt;

		//tdjpH taille du JPanel horizontale

		//tcd taille de la colonne droite
		//ndlC nombre de lignes de cases.

		//g.clearRect(0, 0, this.getWidth(), this.getHeight());

		//g.setColor(new Color(-6384846));
		//g.fillRect(ldpdt,0,getWidth-tcd-ldpdt,getHeight);

		pJeu.setBounds(ldpdt,tdpi,ldPJ,getHeight-tdpi);
		pJeu.setPreferredSize(new Dimension(ldPJ,getHeight-tdpi));

		g.setColor(CouleurDuLabel);
		g.fillRect(ldpdt,0,ldPJ,tdpi);

		int adaptL = 8;
		int adaptH = 9;

		label.setBounds(ldpdt+adaptL,adaptH,ldPJ-adaptL,tdpi-adaptH);

		this.setPreferredSize(new Dimension(f.getWidth,(tcvV+10)*ndlC+12));	

		//System.out.println("ok5 "+(++cmp5));
	}

	public void paintComponent (Graphics g) {
		int getWidth = this.getWidth();
		int getHeight = this.getHeight();
		int longueur = placementB[0]+3;
		int h = 230;
		int f = ((getHeight*2+100)/20+15);

		g.clearRect(0,0,getWidth,getHeight);

		g.setColor(Color.green);
		g.fillRect((getWidth-tcd),0,getWidth,getHeight);

		g.drawImage(titreImg,(getWidth-tcd)+tcd/2-196/2,(getHeight*1+100)/14-45/2,196,70,null);

		//g.drawImage(cacheInfo,getWidth-tcd+24,8,16,16,null);

		cacheInfoB.setBounds(getWidth-tcd+24,8,16,16);

		//monLabel.setBounds((getWidth-tcd)+tcd/2-196/2,(getHeight*1+100)/14-45/2,196,70);
		//g.drawImage(bouton3d,(getWidth-tcd)+tcd/2-196/2,(getHeight*1+100)/14-45/2,196,70,null);

		barrePanel.setBounds(0,-5,20,getHeight+6);
		barrePanel.setPreferredSize(new Dimension(20,getHeight+6));

		bar.setPreferredSize(new Dimension(20,getHeight)); 

		for (i=0; i<ndb; i++) {
			if (i==10) boutons[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*2+50)/placementB[2]-55/2+f+h-placementB[1]-2,placementB[0],placementB[1]);
			else if (i==32||i==33)  boutons[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(placementB[i+3])+100)/placementB[2]-50/2+f,placementB[0],placementB[1]*2);
			else if (i==46) boutons[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(placementB[i+3])+100)/placementB[2]-50/2+f,placementB[0],placementB[1]*2);
			//else if (i==15) boutons[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(placementB[i+3])+100)/placementB[2]-50/2+f,placementB[0],placementB[1]*2);
			else boutons[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(placementB[i+3])+100)/placementB[2]-50/2+f,placementB[0],placementB[1]);
			//g.drawImage(bouton3d,(getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(placementB[i+3])+100)/placementB[2]-50/2+f,placementB[0],placementB[1],null);
		}

		for (i=0; i<3; i++) {
			if (i==2) champNombre[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*6+100)/placementB[2]-50/2+f,placementB[0],placementB[1]);
			else champNombre[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*5+100)/placementB[2]-50/2+f,placementB[0],placementB[1]);
			champTemps[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(i+4)+100)/placementB[2]-50/2+f,placementB[0]/3,placementB[1]);

			texteTemps[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2+placementB[0]/3+10,(getHeight*(i+4)+100)/placementB[2]-50/2+f,placementB[0],placementB[1]);
			if (i==2) texteOptions[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(3)+100)/placementB[2]-50/2+f,placementB[0]+10,placementB[1]*4);
			else texteOptions[i].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(2)+100)/placementB[2]-50/2+f,placementB[0]+10,placementB[1]*4);
		}

		texteOptions[3].setBounds((getWidth-tcd)+tcd/2-placementB[0]/2,(getHeight*(1)+100)/placementB[2]-50/2+f,placementB[0]+15,placementB[1]*4);

		if (dessineCarreCouleurs) {
			dessineCarreCouleurs(g,longueur,h,f,getWidth,getHeight);
		}

		premierCoupHvsH (g);
	}

	public void reDim () {
		f.reDim();
	}

	public boolean getJeuCommence () {
		return jeuCommence;
	}
}

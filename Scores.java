import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/*Affiche les scores et les statistiques.*/

public class Scores extends JFrame {
	
	Container p1;
	
	boolean b1;
	UtilFichiers utilFichiers;

	public Scores (int i1, int i2, int i3, boolean classement, int tailleDesPolices) {
		super("Scores et statistiques");

		int i,j;

		utilFichiers = new UtilFichiers ();

		String [][] s;

		s = utilFichiers.litStat1Fichier ();
				
		p1 = new Container (this,s,classement,i1,i2,i3,tailleDesPolices);
		
		this.add(p1);
	
		this.setMinimumSize(new Dimension(750, 400));
		//this.setPreferredSize(new Dimension(600, 300));
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setVisible(true);		
	}
	
	public void reDim1 () {
		boolean dd = (getExtendedState()==this.MAXIMIZED_BOTH);

		int getWidth = this.getWidth();
		int getHeight = this.getHeight();

		resize(new Dimension(getWidth+1,getHeight+1));
		resize(new Dimension(getWidth,getHeight));

		if (dd) {
			this.setExtendedState(JFrame.MAXIMIZED_BOTH);
			b1 = true;
		}
	}

	public void reDim () {
 		this.revalidate();
	}

	public void afficheMessage (String message, String titre, char type) {
		JOptionPane fOptions = new JOptionPane();

		if (type=='i') fOptions.showMessageDialog(this, message,titre, JOptionPane.PLAIN_MESSAGE);
		else if (type=='w') fOptions.showMessageDialog(this, message,titre, JOptionPane.WARNING_MESSAGE);
		else fOptions.showMessageDialog(this, message,titre, JOptionPane.PLAIN_MESSAGE);
	}
	
	public int effacerTout () {
		JOptionPane fOptions = new JOptionPane();

		return fOptions.showConfirmDialog(this,"Voulez-vous vraiment effacer les scores et les statistiques ?","Effacer les scores et les statistiques ?",JOptionPane.WARNING_MESSAGE);
	}
	
	public static void main (String [] args) {

		int i,j;

		//String [][] s = new String [35][9];
		
		//for (int i=0; i<s.length; i++)for (int j=0; j<s[i].length; j++) s[i][j] = "bla";
		
		//s[0][6] = "blablablablablbalbal";

		//for (i=0; i<s.length; i++) for (j=0; j<s[i].length; j++) System.out.println("s["+i+"]["+j+"] = "+s[i][j]);
		//for (i=0; i<s1.length; i++) System.out.println("s1["+i+"] = "+s1[i]);
		
		Scores f = new Scores (4,4,1,false,12);
	}
}

class Container extends JPanel {
	Scores f;
	Clique [] clique;
	int i;
	int tailleColonne;
	JScrollPane jsp;
	Color couleur;
	Color couleurDunBouton;
	Color couleurBouton;
	Color couleurBoutonSelected;
	CliqueSouris cliquePanel;

	boolean toutAfficher;
	
	static UtilFichiers utilFichiers;
	static UtilDate utilDate;
	
	int tdtH; //taille horizontale  du Tableau
	int tdtV; //taille verticale du Tableau		
	JTextArea [] label;
	
	JTextArea [] label2;
	
	JTextField [] champTexte;
	
	JButton [] boutons;
	
	JComboBox [] choix;
	
	boolean selected;
	
	TabScores tabScores;
	
	String [][] s; // Tableau des scores
	String [][] s7; // Tableau des scores
	String [] s1;
	String [] s2;
	
	String [] s4;
	String [] s5;

	String [][] s8;
	
	public Container (Scores f, String [][] s, boolean classement, int i1, int i2, int i3, int tailleDesPolices) {
		super ();

		int i,j;

		int length = 8;

		if (s.length>0) length = s[0].length; 

		s8 = new String [s.length][length];

		for (i=0; i<s.length; i++) for (j=0; j<s[i].length; j++) {
			s8[i][j]=s[i][j]+"";
		}

		//System.out.println ("i1 = "+i1+"\ni2 = "+i2+"\ni3 = "+i3);

		//if (classement) s = utilFichiers.classTab3val(s,i1,i2,i3);

		couleurDunBouton = couleurBouton = UIManager.getColor("Button.background"); // attribution e la couleur d'origine des boutons (classe swing).
		
		utilFichiers = new UtilFichiers ();
		utilDate = new UtilDate ();
		
		cliquePanel = new CliqueSouris(1);
		
		this.addMouseListener(cliquePanel);
		
		this.f = f;
		
		int tdtH=10; //taille horizontale  du Tableau
		int tdtV=10; //taille verticale du Tableau

		s = utilFichiers.classTabDateD (s); 

		this.s = s; // Tableau des scores	

		classTabS ();
		
		//System.out.println("tailleColonne = "+tailleColonne);
		
		reDim ();
				
		tabScores = new TabScores();
		tabScores.setPreferredSize(new Dimension(tdtH,tdtV));
		this.add(tabScores);
						
		selected = false;
		
		couleurBouton = new Color(-2631721);
		couleurBoutonSelected = new Color(-6710887);
		
		champTexte = new JTextField [2];
		
		boutons = new JButton [7];
		
		clique = new Clique [7];
		
		label  = new JTextArea [5];
		
		label2 = new JTextArea [14];
				
		choix = new JComboBox [3];
				
		for (i=0; i<champTexte.length; i++) {
			champTexte [i]= new JTextField ();
			this.add(champTexte[i]);
		}
		
		Font font = new Font("Serif", Font.PLAIN, 16);
		if (tailleDesPolices!=12) font = new Font("Serif", Font.PLAIN, 16+(tailleDesPolices-12));
		
		couleur = new Color(238,177,88);
		
		s1 = nommeLabel (5);
		
		for (i=0; i<label.length; i++) {		
			label [i] = new JTextArea ();
			label  [i].setText(s1[i]);
			label  [i].setFont(font);
			label  [i].setEditable(false);
			label  [i].setBackground(couleur);
			label  [i].setForeground(Color.BLACK);
			label  [i].setEditable(false);
			label  [i].setWrapStyleWord(true);
			this.add(label [i]);
		}
		
		s4 = nommeStats (7);

		String [] s6 = new String [14];

		for (i=0; i<7; i++) s6 [i] = s4 [i];

		s4 = new String [14];

		for (i=0; i<7; i++) s4 [i] = s6 [i];

		s5 = attributStats (7,s);

		//for (i=0; i<7; i++) System.out.println("s5["+i+"] = "+s5[i]);
		
		for (i=0; i<7; i++) s4 [i+7] = s5 [i];
		
		for (i=0; i<label2.length; i++) {		
			label2 [i] = new JTextArea ();
			label2  [i].setText(s4[i]);
			label2  [i].setFont(font);
			label2  [i].setEditable(false);
			label2  [i].setBackground(new Color(-6710887));
			//label2  [i].setBackground(couleur);
			label2  [i].setForeground(Color.WHITE);
			label2  [i].setEditable(false);
			label2  [i].setWrapStyleWord(true);
			this.add(label2 [i]);
		}
		
		s2 = nomDesBoutons (7);

		font = new Font("Serif", Font.PLAIN, 16);
		if (tailleDesPolices!=12) font = new Font("Serif", Font.PLAIN, tailleDesPolices);
			
		for (i=0; i<boutons.length; i++) {	
			boutons [i]= new JButton(s2[i]);
			if (tailleDesPolices!=12) boutons [i].setFont(font);

			clique [i] = new Clique(i);

			boutons [i].addActionListener(clique [i]);
			this.add(boutons [i]);
		}
		
		if (!selected) {
			boutons [2].setBackground(couleurBoutonSelected);
			boutons [3].setBackground(couleurBouton);

			for (i=0; i<label2.length; i++) label2 [i].setVisible(false);	
		}
		else  {
			boutons [3].setBackground(couleurBoutonSelected);
			boutons [2].setBackground(couleurBoutonSelected);
		}
		
		for (i=0; i<choix.length; i++) {
			choix [i] = new JComboBox();
			if (tailleDesPolices!=12) choix [i].setFont(font);
			this.add(choix [i]);			
		}
		
		choix [0].addItem("Nombre de cases");	
		choix [0].addItem("Nombre de couleurs");
		choix [0].addItem("Mode de jeu");
		choix [0].addItem("Difficulté");
		choix [0].addItem("Score");
		choix [0].addItem("Temps");
		choix [0].addItem("Chronomètre");
		choix [0].addItem("Date");
			
		choix [1].addItem("Croissant         ");
		choix [1].addItem("Décroissant       ");
		
		choix [2].addItem("Humain contre humain");
		choix [2].addItem("Ordinateur contre humain");
		choix [2].addItem("Humain contre ordinateur");
		choix [2].addItem("Ordinateur contre ordinateur");

		boutons[4].setBackground(new Color(-16724941));
		toutAfficher = true;
		
		this.f = f;
	}

	public void classTabS () {
		int i,j;

		int length = 8;

		if (s.length>0) length = s[0].length; 

		s7 = new String [s.length][length];

		for (i=0; i<s.length; i++) for (j=0; j<s[i].length; j++) {
			s7[i][j]=s[i][j]+"";
		}

		//for (i=0; i<s7.length; i++) for (j=0; j<s7[i].length; j++) System.out.println("s7["+i+"]["+j+"] = "+s7[i][j]);
	
		tailleColonne = compteLengthLigneEtColonne (s)*8;

		for (i=0; i<s7.length; i++) { // modes de jeu
			if (s7[i][3].equals("1")) s7[i][3] = "Humain contre humain";
			if (s7[i][3].equals("2")) s7[i][3] = "Ordinateur contre humain";
			if (s7[i][3].equals("4")) s7[i][3] = "Humain contre ordinateur";
			if (s7[i][3].equals("4")) s7[i][3] = "Ordinateur contre ordinateur";
		}

		for (i=0; i<s7.length; i++) { // difficulté
			if (s7[i][4].equals("1")) s7[i][4] = "Facile";
			if (s7[i][4].equals("2")) s7[i][4] = "Moyen";
			if (s7[i][4].equals("3")) s7[i][4] = "Difficile";
		}

		for (i=0; i<s7.length; i++) { // modes de jeu
			int [] t5 = new int [3];

			if (s7[i][7].equals("1")) t5 = utilDate.convertitSecondes(utilFichiers.convertNombre(s7[i][6]));

			s7[i][6] = "";

			if (t5[0]>0) s7[i][6]=t5[0]+" h ";
			if (t5[1]>0) s7[i][6]=s7[i][6]+t5[1]+" min ";

			s7[i][6] = s7[i][6]+t5[2]+" s";

			//s7[i][6] = t5[0]+" h "+t5[1]+" min "+t5[2]+" s";
		}

		for (i=0; i<s7.length; i++) { // chronomètre actif ou non.
			if (s7[i][7].equals("1")) s7[i][7] = "Activé";
			if (s7[i][7].equals("0")) {
				s7[i][6] = "-";
				s7[i][7] = "Désactivé";
			}
		}
	}
	
	class CliqueSouris implements MouseListener {
		int n;
		
		public CliqueSouris(int n) {
				this.n = n;
		}
		
		public void mouseClicked(MouseEvent e) {
				if (n==1) { //panel
					repaint ();
					
					reDim();
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
	
	class TabScores extends JPanel {
		int i;
		
		Jtableau table;
		String [] entetes;
		String [][] donnees;
		CliqueSouris cliquePanel;

		public TabScores() {
						
			cliquePanel = new CliqueSouris(1);
		
			this.addMouseListener(cliquePanel);
						
				donnees = s7;
				
				entetes = new String [9]; 

				entetes [0] = "Nom du joueur";
				entetes [1] = "Nombre de cases";
				entetes [2] = "Nombre de couleurs";
				entetes [3] = "Mode de jeu";
				entetes [4] = "Difficulté";
				entetes [5] = "Score (nombre de coups)";
				entetes [6] = "Temps";
				entetes [7] = "Chronomètre actif ou non";
				entetes [8] = "Date";


				/*j+":"+n+":"+ndco+":"+m+":"+d+":"+score+":"+temps+":"+chrono+":"+date+":"*/


				table = new Jtableau(donnees, entetes);
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
																	
				jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
																
				this.add(jsp);
				
				//this.add(table);
		}

		public void metAJourTab () {
				//table.getModel().fireTableDataChanged();
		}
		
		public void paintComponent (Graphics g) {
			int getWidth = this.getWidth();
			int getHeight = this.getHeight();
						
			this.setPreferredSize(new Dimension(tdtH,tdtV));
			//System.out.println("OK1 tdtH,tdtV"+tdtH+"\n"+tdtV);
			//table.setPreferredSize(new Dimension(getWidth,getHeight));
			
			int tailleColonneF=tailleColonne;
			
			if (getWidth/9>tailleColonne) tailleColonneF = getWidth/9;
			
			for (i=0; i<9; i++) table.getColumnModel().getColumn(i).setPreferredWidth(tailleColonneF);
			
			jsp.setPreferredSize(new Dimension(getWidth,getHeight));
			jsp.setBounds(0,0,getWidth,getHeight);
		}
		
	}

	class Jtableau extends JTable{
		public Jtableau(String [][] o, String[] e) {
			super(o, e);
		} 
		
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}
	
	public static String [] nommeStats (int n) {
		int i;
		
		String [] s = new String [n];
		
		s[0]="Nombre de parties \ngagnées : ";
		s[1]="Nombre de parties \njouées par \nl'ordinateur : ";
		s[2]="Nombre de parties \njouées par des \njoueurs humains : ";
		s[3]="Nombre de parties \njouées en mode \ncontre la montre : ";
		s[4]="Nombre de parties \njouées en mode \nhumain contre humain : ";
		s[5]="Nombre de parties \njouées en mode \nordinateur contre humain : ";
		s[6]="Nombre de parties \njouées en mode \nhumain contre ordinateur : ";

		return s;		
	}
	
	public String [] attributStats (int n, String [][] s) {
		int i,j;
		
		String [] s1 = new String [n];
		
		s1[0]=s8.length+"";
		s1[1]=(compteMode(s8,3)+compteMode(s8,4))+"";
		s1[2]=(compteMode(s8,1)+compteMode(s8,2))+"";
		s1[3]=compteCLM(s)+"";
		s1[4]=compteMode(s8,1)+"";
		s1[5]=compteMode(s8,2)+"";
		s1[6]=compteMode(s8,3)+"";
		
		return s1;		
	}
	
	public static int compteMode (String [][] s, int n) {
		int i, a=0, cmp=0;

		for (i=0; i<s.length; i++) {
			if (utilFichiers.TestNombre(s[i][3])) cmp = utilFichiers.convertNombre(s[i][3]);
			else {
				System.out.println("compteMode dit : Erreur");
				System.out.println("s[i][1] : "+s[i][1]);
				return -1;
			}

			if (cmp==n) a++;
		}

		return a;
	}

	public static int compteCLM (String [][] s) {
		int i, a=0, cmp=0;

		for (i=0; i<s.length; i++) {
			if (utilFichiers.TestNombre(s[i][7])) cmp = utilFichiers.convertNombre(s[i][7]);
			else {
				System.out.println("compteMode dit : Erreur");
				return -1;
			}

			if (cmp==1) a++;
		}

		return a;
	}
		
	public static String [] nomDesBoutons (int n) {
		int i;
		
		String [] s = new String [n];
		
		s[0]="Ok";
		s[1]="Ok";
		s[2]="Scores";
		s[3]="Statistques";
		s[4]="Tout afficher";
		s[5]="Ok";
		s[6]="Effacer tout";
		
		return s;		
	}
	
	public static String [] nommeLabel (int n) {
		int i;
		
		String [] s = new String [n];
		
		s[0]="Classer les \nscores selon le :";
		s[1]="par ordre :";
		s[2]="Nombre de \ncases :";
		s[3]="Mode de \njeu :";
		s[4]="Nombre de \ncouleurs :";
		
		return s;	
	}
	
	public void paintComponent (Graphics g) {
		int getWidth = this.getWidth();
		int getHeight = this.getHeight();
		
		g.setColor(couleur);	
		g.fillRect(0,0,getWidth,getHeight);

		boutons[0].setBounds(getWidth/45*32,getHeight/100*(2),120,35);
		boutons[1].setBounds(getWidth/100*85,getHeight/100*22,120,35);
		
		int thbc = getWidth/8; //taille horizontale des boutons de choix
		int thtbc = 99; //taille horizontale du texte des boutons de choix
		if (thbc<thtbc) thbc = thtbc;
		
		for (i=2; i<4; i++) boutons[i].setBounds(getWidth/45*1,getHeight/50*(16+(i-2)*17),thbc,getHeight/3);
		
		for (i=4; i<7; i++) boutons[i].setBounds(getWidth/30*(7+(i-4)*7),getHeight/14*9+getHeight/3-35,130,35);
		
		label [2].setBounds(getWidth/100*2,getHeight/100*20,70,25*2);
		champTexte [0].setBounds(getWidth/100*13,getHeight/100*23,50,25);
		label [3].setBounds(getWidth/100*21,getHeight/100*20,70,25*2);
		choix [2].setBounds(getWidth/100*31,getHeight/100*23,191,25);
		label [4].setBounds(getWidth/100*59,getHeight/100*20,70,25*2);
		champTexte [1].setBounds(getWidth/100*71,getHeight/100*23,50,25);
		
		label [0].setBounds(getWidth/45*1,getHeight/100*1,117,25*2);
		choix [0].setBounds(getWidth/45*9,getHeight/100*3,143,25);
		label [1].setBounds(getWidth/45*19,getHeight/100*3,65,25*2);
		choix [1].setBounds(getWidth/45*24,getHeight/100*3,118,25);

		label2 [0].setBounds(getWidth/100*19,getHeight/50*(17),17*7,25*2);
		label2 [1].setBounds(getWidth/100*19,getHeight/50*(17+7),17*7,23*3);
		label2 [2].setBounds(getWidth/100*19,getHeight/50*(17+16),17*7,23*3);
		label2 [3].setBounds(getWidth/100*43,getHeight/50*(17),17*7,25*3);
		label2 [4].setBounds(getWidth/100*43,getHeight/50*(17+11),20*7,25*3);
		label2 [5].setBounds(getWidth/100*71,getHeight/50*(17),23*7,25*3);
		label2 [6].setBounds(getWidth/100*71,getHeight/50*(17+11),23*7,25*3);

		label2 [7].setBounds(getWidth/100*36,getHeight/50*(17)+25-1,5*7,25);
		label2 [8].setBounds(getWidth/100*36,getHeight/50*(17+7)+25*3-25-4,5*7,25);
		label2 [9].setBounds(getWidth/100*36,getHeight/50*(17+16)+25*2-5,5*7,23);
		label2 [10].setBounds(getWidth/100*64,getHeight/50*(17)+25*2-4,5*7,25);
		label2 [11].setBounds(getWidth/100*64,getHeight/50*(17+11)+25*2-4,5*7,25);
		label2 [12].setBounds(getWidth/100*95,getHeight/50*(17)+25*2-5,5*7,25);
		label2 [13].setBounds(getWidth/100*95,getHeight/50*(17+11)+25*2-4,5*7,25);
		
		tdtH = getWidth-getWidth/100*17-15; //taille horizontale  du Tableau
		tdtV = getHeight-getHeight/50*(16)-15-45; //taille verticale du Tableau
		
		//System.out.println("tdtH,tdtV"+tdtH+"\n"+tdtV);
				
		tabScores.setSize(new Dimension(tdtH,tdtV));
		tabScores.setBounds(getWidth/100*17,getHeight/50*(16),tdtH,tdtV);
		
		if (!selected) {
			g.setColor(new Color(-6710887));	
			g.fillRect(getWidth/100*17,getHeight/50*(16),tdtH,tdtV);
		}
	}
		
	class Clique implements ActionListener {
		int n;
		
		int i,j;

		public Clique(int n) {
			this.n = n;
		}
		public void actionPerformed(ActionEvent e) {
			if (n==0) { //Ok
				//System.out.println(choix [0].getSelectedIndex());
				//System.out.println(choix [1].getSelectedIndex());

				int choic1 = choix [0].getSelectedIndex();
				choic1++;

				int choic2 = choix [1].getSelectedIndex();

				if (choic1==6) {
					if (choic2==1) s = utilFichiers.classTempsD (s); 
					if (choic2==0) s = utilFichiers.classTempsC (s);
				}


				if (choic1==8) {
					if (choic2==1) s = utilFichiers.classTabDateD (s); 
					if (choic2==0) s = utilFichiers.classTabDateC (s);
				}

				else {
					if (choic2==1) s = utilFichiers.classTabD (s,choic1);
					if (choic2==0) s = utilFichiers.classTabC (s,choic1);
				}

				classTabS ();

				//for (i=0; i<s.length; i++) for (j=0; j<s[i].length; j++) System.out.println("s["+i+"]["+j+"] = "+s[i][j]);

				remove(tabScores);
				tabScores = new TabScores();
				tabScores.setPreferredSize(new Dimension(tdtH,tdtV));
				add(tabScores);

				selected = true;
				boutons [2].setBackground(couleurBoutonSelected);
				boutons [3].setBackground(couleurBouton);
				for (i=0; i<label2.length; i++) label2 [i].setVisible(false);

				//boutons[4].setBackground(couleurDunBouton);
										
				repaint ();

				reDim ();
			}
			
			if (n==1) { //Ok
				//System.out.println(champTexte [0].getText());
				//System.out.println(choix [2].getSelectedIndex());
				//System.out.println(champTexte [1].getText());

				String ss1 = champTexte [0].getText();
				int nn1 = choix [2].getSelectedIndex();
				nn1++;
				String ss2 = champTexte [1].getText();

				//System.out.println(ss1);
				//System.out.println(nn1);
				//System.out.println(ss2);

				if (ss1.equals("") || ss2.equals("")) f.afficheMessage("Veuillez entrer un entier.","Entrez un entier.",'w');
				else if (!utilFichiers.TestNombre(ss1) || !utilFichiers.TestNombre(ss2)) f.afficheMessage("Veuillez entrer un entier.","Entrez un entier.",'w');
				else {
					s = utilFichiers.classTab3val(s8,utilFichiers.convertNombre(ss1),utilFichiers.convertNombre(ss2),nn1);

					classTabS ();

					//for (i=0; i<s.length; i++) for (j=0; j<s[i].length; j++) System.out.println("s["+i+"]["+j+"] = "+s[i][j]);
					//for (i=0; i<s7.length; i++) for (j=0; j<s7[i].length; j++) System.out.println("s7["+i+"]["+j+"] = "+s7[i][j]);

					remove(tabScores);
					tabScores = new TabScores();
					tabScores.setPreferredSize(new Dimension(tdtH,tdtV));
					add(tabScores);

					selected = true;
					boutons [2].setBackground(couleurBoutonSelected);
					boutons [3].setBackground(couleurBouton);
					for (i=0; i<label2.length; i++) label2 [i].setVisible(false);

					boutons[4].setBackground(couleurDunBouton);
					toutAfficher = false;
										
					repaint ();

					reDim ();
				}
			}
			
			if (n==2) { //Score
				selected = true;
				boutons [2].setBackground(couleurBoutonSelected);
				boutons [3].setBackground(couleurBouton);

				for (i=0; i<label2.length; i++) label2 [i].setVisible(false);
				
				jsp.setVisible(true);
				
				repaint ();
			}
			
			if (n==3) { //Statistiques
				selected = false;
				boutons [3].setBackground(couleurBoutonSelected);
				boutons [2].setBackground(couleurBouton);

				for (i=0; i<label2.length; i++) label2 [i].setVisible(true);
				
				jsp.setVisible(false);
				
				repaint ();
			}

			if (n==4) { //Tout afficher
					if (!toutAfficher) {
						toutAfficher = true;

						int length = 8;

						if (s8.length>0) length = s8[0].length; 

						s = new String [s8.length][length];

						for (i=0; i<s8.length; i++) for (j=0; j<s8[i].length; j++) {
							s[i][j]=s8[i][j]+"";
						}
	
						classTabS ();
	
						remove(tabScores);
						tabScores = new TabScores();
						tabScores.setPreferredSize(new Dimension(tdtH,tdtV));
						add(tabScores);
	
						selected = true;
							boutons [2].setBackground(couleurBoutonSelected);
						boutons [3].setBackground(couleurBouton);
						for (i=0; i<label2.length; i++) label2 [i].setVisible(false);
	
						boutons[4].setBackground(new Color(-16724941));
			
						repaint ();
	
						reDim ();
					}
			}
			
			if (n==5) { //Ok
				f.dispose();
			}
			
			if (n==6) { //Effacer tout
				int g = f.effacerTout ();
				
				if (g==0) {
					File ff1 = new File ("Sauvegardes"+File.separatorChar+"Statistiques.txt");

					ff1.delete ();

					f.afficheMessage("Tous les scores et les statistiques ont été supprimés.","Scores et statistiques supprimés.",'i');

					s = new String [0][0];

					s5 = attributStats (7,s);

					//for (i=0; i<7; i++) System.out.println("s5["+i+"] = "+s5[i]);
		
					for (i=0; i<7; i++) s4 [i+7] = s5 [i];

					for (i=0; i<label2.length; i++) label2  [i].setText(s4[i]);				

					classTabS ();

					remove(tabScores);
					tabScores = new TabScores();
					tabScores.setPreferredSize(new Dimension(tdtH,tdtV));
					add(tabScores);

					if (!selected) jsp.setVisible(false);
										
					repaint ();

					reDim ();
				}
			}
		}
	}
	
	public static int compteLengthLigneEtColonne (String [][] s) {
		int i, b=0, cmp=0;
		
		for (i=0; i<s.length; i++){
			cmp = compteLengthLigne (s[i]);
			if (cmp>b) b = cmp;				
		}
		
		return b;
	}
	
	public static int compteLengthLigne (String [] s) {
		int i, b=0, cmp=0;
		
		for (i=0; i<s.length; i++) {
			cmp = s[i].length();
			
			if (cmp>b) {
				b=cmp;
			}
		}
		
		return b;
	}
	
	public void reDim () {
		f.reDim ();
	}
}

import java.awt.Color;

class UtilFenetre {
	public UtilFenetre () {

	}

	public static int [] agrandieTab (int [] t) {
		int i;

		int [] t1 = new int[t.length*2];

		for (i=0; i<t.length; i++) t1[i] = t[i];

		return t1;
	}

	public static int [][] agrandieTab (int [][] t) {
		int i,j;

		int [][] t1 = new int[t.length*2][t[0].length];

		for (i=0; i<t.length; i++) for (j=0; j<t[0].length; j++) t1[i][j] = t[i][j];

		return t1;
	}

	public int [] placementBoutons (int n) {
		int [] t = new int [n];

		t[0] = 145;  //taille horizontale
		t[1] = 34;  //taille verticale
		t[2] = 9;   //nombre de boutons
		t[3] = 1; //Jouer
		t[4] = 2; //Options
		t[5] = 3; //Instructions
		t[6] = 4; //Scores
		t[7] = 5; //Crédits
		t[8] = 7; //Quitter
		t[9] = 1; //Nouveau Jeu
		t[10] = 2; //Charger Partie
		t[11] = 6; //Retour
		t[12] = 1; //Commencer
		t[13] = 5; //Changer les couleurs
		t[14] = 6; //Retour
		t[15] = 1; //Nombre de cases
		t[16] = 3; //Difficulté
		t[17] = 4; //Mode de jeu
		t[18] = 5; //nombre de coup max
		t[19] = 6; //Contre la montre
		t[20] = 8; //Retour
		t[21] = 1; //Facile
		t[22] = 2; //Moyen
		t[23] = 3; //Difficile
		t[24] = 6; //Retour
		t[25] = 1; // h/h
		t[26] = 2; // h/o
		t[27] = 3; // o/h
		t[28] = 4; // o/o
		t[29] = 6; //Retour
		t[30] = 2; //Activer
		t[31] = 3; //Désactiver
		t[32] = 8; //Retour
		t[33] = 7; //Valider
		t[34] = 8; //Retour arrière
		t[35] = 9; //Arreter et voir les réponses
		t[36] = 9; //Ok
		t[37] = 2; //Nombre de couleurs
		t[38] = 3; //Temps
		t[39] = 2; //Activé/Désactivé
		t[40] = 7; //Ok
		t[41] = 8; //Retour
		t[42] = 7; //Retour
		t[43] = 7; //Retour
		t[44] = 8; //Retour
		t[45] = 6; //Ok
		t[46] = 6; //Ok
		t[47] = 7; //Ok
		t[48] = 8; //Retour
		t[49] = 1; //Activer/Désactiver : nombre de coups illimité
		t[50] = 1; //Pause
		t[51] = 7; //Options graphiques

		return t;
	}

	public static Color [] attributDefaut () {
		Color [] c = new Color [6];

		c[0] = Color.GREEN; //barre de droite
		/*c[1] = ; //pions de réponse
		c[2] = ; //carré de réponses
		c[3] = ; //fond des coups
		c[4] = ; //bordure des coups
		c[5] = ; //fond arrière JPanel des coups*/

		return c;
	}

	public String [] tabBouttons(int n) {
		int i,j;

		String [] s = new String [n];

		for (i=0; i<s.length; i++) s[i]="";

		s[0] = "Jouer";
		s[1] = "Options";
		s[2] = "Instructions";
		s[3] = "Scores";
		s[4] = "Crédits";
		s[5] = "Quitter";
		s[6] = "Nouveau jeu";
		s[7] = "Charger une partie";
		s[8] = "Retour";
		s[9] = "Commencer";
		s[10] = "Changer les couleurs";
		s[11] = "Retour";
		s[12] = "Nombre de cases";
		s[13] = "Difficulté";
		s[14] = "Mode de jeu";
		//s[15] = "<html><p>Nombre de coups<br>maximum</p></html>";
		s[15] = "Nombre de coups";
		s[16] = "Mode contre la montre";
		s[17] = "Retour";
		s[18] = "Facile";
		s[19] = "Moyen";
		s[20] = "Difficile";
		s[21] = "Retour";
		s[22] = "Humain contre humain";
		s[23] = "Ordinateur contre humain";
		s[24] = "Humain contre ordinateur";
		s[25] = "Ordinateur contre ordinateur";
		s[26] = "Retour";
		s[27] = "Activer";
		s[28] = "Désactiver";
		s[29] = "Retour";
		s[30] = "Valider";
		s[31] = "";
		s[32] = "Arrêter et voir \nles réponses";
		s[33] = "OK";
		s[34] = "Nombre de couleurs";
		s[35] = "Temps";
		s[36] = "Activer/Désactiver";
		s[37] = "Ok";
		s[38] = "Retour";
		s[39] = "Retour";
		s[40] = "Retour";
		s[41] = "Retour";
		s[42] = "Ok";
		s[43] = "Ok";
		s[44] = "Ok";
		s[45] = "Retour";
		s[46] = "<html><p>Activer/Désactiver<br> le nombre de coups illimité.</p></html>";
		s[47] = "Pause";
		s[48] = "Options graphiques";

		return s;
	}

	public static String [] texteDesOptions () {
		int i;

		String [] s = new String [4];

		for (i=0; i<4; i++) s[i] = "";

		s[0] = "\nNombre de cases : \n\nMoins de 5 : Facile\nEntre 5 et 7 : Moyen\nPlus de 7 : Difficile\nPlus de 10 : Très difficile.";
		s[1] = "\nNombre de couleurs : \n\nMoins de 6 : Facile\nEntre 6 et 8 : Moyen\nPlus de 10 : Difficile\nPlus de 15 : Très difficile.";
		s[2] = "\nNombre de coups max : \n\n30 : Facile\n12 : Moyen\n6 : Difficile\nMoins de 5 : Très difficile.";
		s[3] = "\nTemps : \n\nPlus de 20 min : Très facile\n15 min : Facile\n5 min : Moyen\nMoins de 5 min : difficile.";

		return s;
	}

	public static boolean TestNombre (String s){
		int i, a = s.length(), c=0;

		if (s.equals("")) {
			System.out.println("La fonction 'TestNombre' dit : "+'"'+" Le string est null : convertion impossible."+'"');
 		}

		for (i=0; i<a; i++) {
				if (s.charAt(i)=='0' ||
				s.charAt(i)=='1' ||
				s.charAt(i)=='2' ||
				s.charAt(i)=='3' ||
				s.charAt(i)=='4' ||
				s.charAt(i)=='5' ||
				s.charAt(i)=='6' ||
				s.charAt(i)=='7' ||
				s.charAt(i)=='8' ||
				s.charAt(i)=='9') c++;
		}

		return (c>=a);
	}

	public static int convertNombre (String s){
		int i, a = s.length(), c=0, d=0;

		if (s.equals("")) {
			System.out.println("\n\n//Le string est null : convertion impossible.\n");
		}

		if (a==1) {
			if (s.charAt(0)=='0') c=0;
			if (s.charAt(0)=='1') c=1;
			if (s.charAt(0)=='2') c=2;
			if (s.charAt(0)=='3') c=3;
			if (s.charAt(0)=='4') c=4;
			if (s.charAt(0)=='5') c=5;
			if (s.charAt(0)=='6') c=6;
			if (s.charAt(0)=='7') c=7;
			if (s.charAt(0)=='8') c=8;
			if (s.charAt(0)=='9') c=9;

			d=c;
		}else {
			for (i=0; i<a; i++) {
				c=-1;
				if (s.charAt(i)=='0') c=0;
				if (s.charAt(i)=='1') c=1;
				if (s.charAt(i)=='2') c=2;
				if (s.charAt(i)=='3') c=3;
				if (s.charAt(i)=='4') c=4;
				if (s.charAt(i)=='5') c=5;
				if (s.charAt(i)=='6') c=6;
				if (s.charAt(i)=='7') c=7;
				if (s.charAt(i)=='8') c=8;
				if (s.charAt(i)=='9') c=9;
	
				if (c!=-1) d=d*10+c;	
			}
		}

		if (d<0) System.out.println("\n//Le nombre est trop grand");

		return d;

	}
}
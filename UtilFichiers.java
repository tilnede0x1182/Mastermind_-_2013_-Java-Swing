import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

class UtilFichiers extends EnregistrementFichiers {
	static UtilDate utilDate;

	public UtilFichiers () {
		utilDate = new UtilDate();
	}

	public UtilFichiers (int m) {
		int i,j;

		//StatsFichier(sauveStat1("Joueur025", 5, 4, 3, 39, 55, 0));
		//for (int i=0; i<50; i++) StatsFichier(sauveStat1("Joueur025", 5, 4, 3, 39, 55, 0));

		String [][] t = new String [1][1];

		t = litStat1Fichier ();

		for (i=0; i<t.length; i++) for (j=0; j<t[i].length; j++) System.out.println("t["+i+"]["+j+"] = "+t[i][j]);

		String s[][] = classTab3val(t,3,3,1); //recherche(t,1,"5");

		for (i=0; i<s.length; i++) for (j=0; j<s[i].length; j++) System.out.println("s["+i+"]["+j+"] = "+s[i][j]);
	}


	public int [] demandeOptions () {
		int n=0, ndco=0, m=0;
		Scanner sc = new Scanner(System.in);

		System.out.print("n = ");
		n = sc.nextInt();

		System.out.print("ndco = ");
		ndco = sc.nextInt();

		System.out.print("m = ");
		m = sc.nextInt();

		int [] t = new int [3];

		t[0]=n;
		t[1]=ndco;
		t[2]=m;

		return t;
	}

	public String [][] recherche (String [][] s, int n, String a) {
		int i, j=0, k=0;


		int [] s1 = new int [s.length];		

		for (i=0; i<s.length; i++) {
			//System.out.println("s[i][n] = "+s[i][n]+"\n"+s[i][n].equals(a));
			if (s[i][n].equals(a)) {
				s1[i]=1;
			}
		}

		int length = 8;

		if (s.length>0) length = s[0].length;

		String [][] s2 = new String [s.length][length];

		for (i=0; i<s.length; i++) for (j=0; j<s[i].length; j++) s2[i][j]="";

		k=0;

		for (i=0; i<s.length; i++) {
			if (s1[i]!=0) {
				for (j=0; j<s[i].length; j++){
					s2[k][j]=s[i][j];
				}
				if (k<s2.length-1) k++;
			}
		}

		return s2;
	}

	public static String [][] nettoieTab (String [][] s) {
		int i,j, cmp=0, k=0;

		for (i=0; i<s.length; i++) if (s[i][0]!="") cmp++;

		int length = 8;

		if (s.length>0) length = s[0].length; 

		String [][] s1 = new String [cmp][length];


		for (i=0; i<s1.length; i++) for (j=0; j<s1[i].length; j++) s1[i][j]="";

		for (i=0; i<s.length; i++) {
			for (j=0; j<s[i].length; j++) {
				if (s[i][0]!="") s1[k][j]=s[i][j];
			}
			if (k<s1.length-1) k++;
		}

		return s1;
	}

	public String [][] classTab3val (String [][] s, int n, int ndco, int m) {
		//int [] t = demandeOptions ();

		int [] t = new int [3];

		t[0] = n;
		t[1] = ndco;
		t[2] = m;

		String [][] s0=recherche(s,1,""+t[0]);

		//System.out.println("Ok1");

		String [][] s1=recherche(s0,2,""+t[1]);

		//System.out.println("Ok1");

		String [][] s2=recherche(s1,3,""+t[2]);

		//System.out.println("Ok1");

		return nettoieTab(s2);
	}

	public static void StatsFichier (String s1) {
		creeRepertoires();
		boolean fe = false; // fichier existe

		int i;

		File f1 = new File("Sauvegardes"+File.separatorChar+"Statistiques.txt");
		int g=0;
		String [] s = new String [1];

		s[0] = "";

		boolean a = false;

		try{
			a = f1.exists();		
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
		}

		String [] s2 = new String [1];
		s2 [0] = "";

		if (a) {
			fe = true; // fichier existe

			s = litTableauString(lireFichier("Sauvegardes"+File.separatorChar+"Statistiques.txt"),',');
			s2 = new String [s.length+1];
			for (i=0; i<s.length; i++) s2[i]=s[i];	
			s2[i]=s1;		
		}
		else {
			//s2[1]=s1;
		}

		int ggg1 = s.length;
		if (!fe) ggg1 = 0;

		s2 = new String [ggg1+1];

		for (i=0; i<ggg1; i++) s2[i]=s[i];	
		s2[i]=s1;

		String s0 = sauveTableauString(s2,"stats",',');

		effaceFichier("Sauvegardes"+File.separatorChar+"Statistiques.txt");

		try{
			f1.createNewFile();		
		}
	
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
		}

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f1));
		   	pw.println(s0);
			pw.flush();
			pw.close();
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : écriture dans le fichier.");
		}
		
	}

	public static String [] genereAleatSats () {
		int i;

		String [] s = new String [100];

		String [][] t = new String [9][100];

		for (i=0; i<t[0].length; i++) {
			t[0][i] = "Joueur "+(int)(Math.random()*100+1);
			t[1][i] = ""+(int)(Math.random()*500+1); //n
			t[2][i] = ""+(int)(Math.random()*500+1); //nombre de couleurs
			t[3][i] = ""+(int)(Math.random()*4+1); //m
			t[4][i] = ""+(int)(Math.random()*3+1); //d
			t[5][i] = ""+(int)(Math.random()*500+3); //score
			t[6][i] = ""+(int)(Math.random())+" h "+(int)(Math.random()*60+1)+" min "+(int)(Math.random()*60+1)+" s"; //temps
			t[7][i] = ""+(int)(Math.random()); //chrono
			t[8][i] = ""+(int)(Math.random()*30+1)+"/"+(int)(Math.random()*11+1)+"/"+(2013-(int)(Math.random()*12)); //date

			s[i] = ""+t[0][i]+":"+t[1][i]+":"+t[2][i]+":"+t[3][i]+":"+t[4][i]+":"+t[5][i]+":"+t[6][i]+":"+t[7][i]+":";
		}

		return s;
	}

	public static String sauveStat1 (String j, int n, int ndco, int m, int d, int score, int temps, int chrono) {
		String date = utilDate.convertitDateNombres ();

		String s = j+":"+n+":"+ndco+":"+m+":"+d+":"+score+":"+temps+":"+chrono+":"+date+":";

		return s;
	}

	public static void ajouteStat (int n, int ndco, int m, int d, int score, int temps, int chrono, boolean humain, int tailleDesPolices) {
		int [] t = new int [7];

		t[0] = n;
		t[1] = ndco;
		t[2] = m;
		t[3] = d;
		t[4] = score;
		t[5] = temps;
		t[6] = chrono;

		if (humain) {
			EntrerNom f5 = new EntrerNom (tailleDesPolices);

			f5.setTabSauveStats (t);
		}
		else {
			String s1 = sauveStat1("Ordinateur",t[0],t[1],t[2],t[3],t[4],t[5],t[6]);

			StatsFichier (s1);
		}
	}

	/*
		|Nom du joueur|nombre de chiffres|nombre de couleurs|mode de jeu|score (nombre de coups)|temps|chrono actif ou non|date| => 0<n<7.
	*/

 	public static String [][] litStat1Fichier () {
		creeRepertoires();

		int i,j;

		File f1 = new File("Sauvegardes"+File.separatorChar+"Statistiques.txt");

		String [] s = new String [1];

		s[0] = "";

		boolean a = false;

		try{
			a = f1.exists();		
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
		}

		if (a) {
			s = litTableauString(lireFichier("Sauvegardes"+File.separatorChar+"Statistiques.txt"),',');

			String [][] t = new String [s.length][0];

			for (i=0; i<s.length; i++) t[i] = litStat1(s[i]);

			return t;
		}

		else {
			s = genereAleatSats ();

			String s0 = sauveTableauString(s,"stats",',');

			try{
				f1.createNewFile();		
			}
	
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
			}

			try {
				PrintWriter pw = new PrintWriter(new FileWriter(f1));
			   	pw.println(s0);
				pw.flush();
				pw.close();
			}

			catch(Exception e){
				e.printStackTrace();
				System.out.println("Echec de la sauvegarde : écriture dans le fichier.");
			}

			System.out.println("litStat1Fichier dit : Sauvegarde créée.");

			String [][] t1 = new String [1][1];

			t1[0][0]="";

			return (t1);
		}		
	}		

	public static String [][] classTabC (String [][] t, int n) {
		int i,j;
		String [] t1;

		for (i=0; i<t.length; i++) {
			for (j=i+1; j<t.length; j++) {
				if (convertNombre(t[i][n])>convertNombre(t[j][n])) { t1=t[j]; t[j]=t[i]; t[i]=t1; }
			}
		}

		return t;
	}

	public static String [][] classTabD (String [][] t, int n) {
		int i,j;
		String [] t1;

		for (i=0; i<t.length; i++) {
			for (j=i+1; j<t.length; j++) {
				if (convertNombre(t[i][n])<convertNombre(t[j][n])) { t1=t[j]; t[j]=t[i]; t[i]=t1; }
			}
		}

		return t;
	}

	public static String [][] classTabDateC (String [][] t) {
		int i,j;
		String [] t1;
		int n=8; //date

		for (i=0; i<t.length; i++) {
			for (j=i+1; j<t.length; j++) {
				if (utilDate.compareDateMax(t[i][n],t[j][n])) { t1=t[j]; t[j]=t[i]; t[i]=t1; }
			}
		}

		return t;
	}

	public static String [][] classTabDateD (String [][] t) {
		int i,j;
		String [] t1;
		int n=8; //date

		for (i=0; i<t.length; i++) {
			for (j=i+1; j<t.length; j++) {
				if (utilDate.compareDateMin(t[i][n],t[j][n])) { t1=t[j]; t[j]=t[i]; t[i]=t1; }
			}
		}

		return t;
	}

	public static String [][] classTempsC (String [][] t) {
		int i,j, a=0, b=0;
		String [] t1;
		int n=6;

		for (i=0; i<t.length; i++) {
			for (j=i+1; j<t.length; j++) {

				a=0;
				b=0;

				if (convertNombre(t[i][n+1])!=0) {
					a = convertNombre(t[i][n]);
					b = convertNombre(t[j][n]);
				}

				if (a>b) { t1=t[j]; t[j]=t[i]; t[i]=t1; }
			}
		}

		return t;
	}

	public static String [][] classTempsD (String [][] t) {
		int i,j, a=0, b=0;
		String [] t1;
		int n=6;

		for (i=0; i<t.length; i++) {
			for (j=i+1; j<t.length; j++) {

				a=0;
				b=0;

				if (convertNombre(t[i][n+1])!=0) {
					a = convertNombre(t[i][n]);
					b = convertNombre(t[j][n]);
				}

				if (a<b) { t1=t[j]; t[j]=t[i]; t[i]=t1; }
			}
		}

		return t;
	}


 	public static String [] litStat1 (String s1) {
		int i,j=0,s1length=s1.length(), cmp=0;

		for (i=0; i<s1length; i++) if (s1.charAt(i)==':') cmp++;

		String [] s0 = new String [cmp];

		for (i=0; i<s0.length; i++) s0[i] = "";

		i=0;
		j=0;
		while (i<s1length) {
			while (s1.charAt(i)!=':') {
				s0[j]=s0[j]+s1.charAt(i);

				i++;
			}

			i++;

			if (j<s0.length) j++;
		}

		return s0;
	}

	public static int convertDateJour (String s) {
		int i, slength = s.length();
		String s0 = "";

		for (i=0; s.charAt(i)!='/'; i++) {
			s0=s0+s.charAt(i);			
		}

		if (s0.length()>0) return (convertNombre(s0));
		else {
			System.out.println("convertDate dit : Erreur, string trouvé nul");
			return -1;
		}
	}

	public static int convertDateMois (String s) {
		int i, slength = s.length();
		String s0 = "";

		for (i=0; (s.charAt(i)!='/'||i<slength); i++) {			
		}

		i++;

		for (i=0; (s.charAt(i)!='/'||i<slength); i++) {
			s0=s0+s.charAt(i);			
		}

		if (s0.length()>0) return (convertNombre(s0));
		else {
			System.out.println("convertDate dit : Erreur, string trouvé nul");
			return -1;
		}
	}

	public static int convertDateAnnee (String s) {
		int i, slength = s.length();
		String s0 = "";

		for (i=0; (s.charAt(i)!='/'||i<slength); i++) {			
		}

		i++;

		for (i=0; (s.charAt(i)!='/'||i<slength); i++) {			
		}

		i++;

		for (i=0; (s.charAt(i)!='/'||i<slength); i++) {
			s0=s0+s.charAt(i);			
		}

		if (s0.length()>0) return (convertNombre(s0));
		else {
			System.out.println("convertDate dit : Erreur, string trouvé nul");
			return -1;
		}
	}

	public static Object [] convertObject (String [] s1) {
		int i;

		Object [] t = new Object[s1.length];

		for (i=0; i<s1.length; i++) {
			if (TestNombre(s1[i])) t[i] = convertNombre(s1[i]);
			else t[i] = s1[i];
		}

		for (i=0; i<t.length; i++) System.out.println("t["+i+"] = "+t[i]);

		return t;
	}

	public static String maxDate (String a, String b) {
		int i;

		int [][] t = new int [2][3];

		t[0][0] = convertDateJour(a);
		t[0][1] = convertDateMois(a);
		t[0][2] = convertDateAnnee(a);
		t[1][0] = convertDateJour(b);
		t[1][1] = convertDateMois(b);
		t[1][2] = convertDateAnnee(b);

		if (t[0][0]!=t[1][0]) {
			if (t[0][1]!=t[1][1]) {
				if (t[0][2]>t[1][2]) return a;
				else return b;
			}
			else {
				if (t[0][1]>t[1][1]) return a;
				else return b;
			}
		}
		else {
			if (t[0][0]>t[1][0]) return a;
			else return b;
		}
	}

	public static boolean maxDateB (String a, String b) {
		int i;

		int [][] t = new int [2][3];

		t[0][0] = convertDateJour(a);
		t[0][1] = convertDateMois(a);
		t[0][2] = convertDateAnnee(a);
		t[1][0] = convertDateJour(b);
		t[1][1] = convertDateMois(b);
		t[1][2] = convertDateAnnee(b);

		if (t[0][2]!=t[1][2]) return (t[0][2]>t[1][2]);
		else if (t[0][1]!=t[1][1]) return (t[0][1]>t[1][1]);
		else if (t[0][0]!=t[1][0]) return (t[0][0]>t[1][0]);
		else return true;
	}

	public static String minDate (String a, String b) {
		int i;

		int [][] t = new int [2][3];

		t[0][0] = convertDateJour(a);
		t[0][1] = convertDateMois(a);
		t[0][2] = convertDateAnnee(a);
		t[1][0] = convertDateJour(b);
		t[1][1] = convertDateMois(b);
		t[1][2] = convertDateAnnee(b);

		if (t[0][0]!=t[1][0]) {
			if (t[0][1]!=t[1][1]) {
				if (t[0][2]<t[1][2]) return a;
				else return b;
			}
			else {
				if (t[0][1]<t[1][1]) return a;
				else return b;
			}
		}
		else {
			if (t[0][0]<t[1][0]) return a;
			else return b;
		}
	}

	public static boolean minDateB (String a, String b) {
		int i;

		int [][] t = new int [2][3];

		t[0][0] = convertDateJour(a);
		t[0][1] = convertDateMois(a);
		t[0][2] = convertDateAnnee(a);
		t[1][0] = convertDateJour(b);
		t[1][1] = convertDateMois(b);
		t[1][2] = convertDateAnnee(b);


		if (t[0][2]!=t[1][2]) return (t[0][2]<t[1][2]);
		else if (t[0][1]!=t[1][1]) return (t[0][1]<t[1][1]);
		else if (t[0][0]!=t[1][0]) return (t[0][0]<t[1][0]);
		else return true;
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

	public static void main (String [] args) {
		UtilFichiers t1 = new UtilFichiers(5);
	}
}






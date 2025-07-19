import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.BufferedReader;

/* Classe qui contient les fonction<br>qui enregistrent les fichiers.*/

class EnregistrementFichiers extends LectureFichiers {

	static UtilDate d1;

	public EnregistrementFichiers () {
		d1 = new UtilDate ();
	}

	public void sauveFichier (String s1) {
		int n = 1, g=0;

		File f = new File("Fichier "+n+".txt");

		try{
			if(f.createNewFile()==false){
				while (f.createNewFile()==false && g<500) {
					n++;
					f = new File("Fichier "+n+".txt");

					g++;
				}
			}
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
		}

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f));
		   	pw.println(s1);
			pw.flush();
			pw.close();
	
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : écriture dans le fichier.");
		}
	}

	public void sauveFichier2 (String s1, String s) {
		int n = 1, g=0;

		File f = new File(s);

		try{
			if(f.createNewFile()==false){
				while (f.createNewFile()==false && g<500) {
					n++;
					f = new File(s+"("+n+")");

					g++;
				}
			}
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
		}

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f));
		   	pw.println(s1);
			pw.flush();
			pw.close();
	
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : écriture dans le fichier.");
		}
	}

	public static int SauvegardePartie (int n, int [][] tcoups, int [][] reponses, int [][] couleurs, int [] tcode, int [] tParametres) {
		creeRepertoires();

		File f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n+".txt");
		String s1 = "";
		int g=0;

		try{
			if(f.createNewFile()==false){
				while (f.createNewFile()==false && g<500) {
					n++;
					f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n+".txt");

					g++;
				}
				if (g==500) return -1;
			}
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
			return -1;
		}

		s1 = s1+d1.convertitDateNombres ()+"\n";

		s1 = s1+sauveTableau2 (tcoups,"coups",',',':');

		s1=s1+"\n;\n\n";


		s1=s1+sauveTableau2 (couleurs,"couleurs",',',':');

		s1=s1+"\n;\n\n";


		s1=s1+sauveTableauInt(encode(4564,2165,tcode),"tcode",',');

		s1=s1+"\n;\n\n";


		s1=s1+sauveTableau2 (reponses,"reponses",',',':');

		s1=s1+"\n;\n\n";

		s1=s1+sauveTableauInt(tParametres,"tParametres",',');


		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f));
		   	pw.println(s1);
			pw.flush();
			pw.close();
	
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : écriture dans le fichier.");
			return -1;
		}

		return n;	
	}

	public static boolean tabVide (int [] t) {
		int i;

		for (i=0; i<t.length; i++) if (t[i]!=0) return false;

		return true;
	}

	public static int classeSauvegardes () {
		int i=0, j;

		int [] t = new int [1000];

		creeRepertoires();

		File f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+i+".txt");

		while (i<1000) {
			i++;

			f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+i+".txt");

			if (f.exists()) t[i] = i;	
		}

		if (tabVide(t)) System.out.println("La fonction "+'"'+"detecteSauvegardes"+'"'+" dit : aucune sauvegarde détectée.");

		int [][] t1 = new int [calcNombreOccurenceFichierNonClasses(t)][2];

		System.out.println("calcNombreOccurenceFichierNonClasses(t) = "+calcNombreOccurenceFichierNonClasses(t));

		for (i=0; i<t.length; i++) if (t[i]!=0) System.out.println("t["+i+"] = "+t[i]);

		if (!tabVide(t)) {

			i=0;

			while (!fichiersClasses(t) && i<t1.length) {
				int a = calculOccurencePlacement(t);
				int b = premiereOccurencePlacement(t);


				System.out.println("i = "+i);
	
				t[a] = t[b];
				t[b] = 0;
				
				t1[i][0] = b;
	
				t1[i][1] = a;
	
				if (i<t1.length) i++;
				else System.out.println("Erreur : limite atteinte (classement fichiers).");
			}

			for (i=0; i<t.length; i++) if (t[i]!=0) System.out.println("t["+i+"] = "+t[i]);

			for (i=0; i<t1.length; i++) for (j=0; j<t1[i].length; j++) System.out.println("t1["+i+"]["+j+"] = "+t1[i][j]);
	
			for (i=0; i<t1.length; i++) renomePartie(t1[i][0],t1[i][1]);

		}

		return (calculOccurencePlacement(t)-1);
	}

	public static String [] detecteParties () {
		int i;

		creeRepertoires();

		String [] s = new String[1];

		s[0] = "";

		int w = classeSauvegardes ();

		System.out.println("w = "+w);

		if (w!=1000 && w>0) {
			s = new String[w];
		
			for (i=0; i<w; i++) s[i]=lireSauvegardeDate(i+1);
		}

		return s;
	}

	public static int calcNombreOccurenceFichierNonClasses (int [] t) {
		int i, cmp=0, a1=0;
		boolean a = false;

		for (i=1; i<t.length-1; i++) {
			if (t[i]==0) {
				a = true;
				a1=i++;
				i=t.length-1;
			}
		}

		if (a) for (i=a1; i<t.length; i++) if (t[i]!=0) cmp++;

		return cmp;
	}

	public static boolean fichiersClasses (int [] t) {
		int i, i1=0;
		boolean a = false;

		for (i=1; i<t.length-1; i++) {
			if (t[i]==0) {
				a = true;
				i1=i++;
				i=t.length-1;
			}
		}

		if (a) for (i=i1; i<t.length; i++) if (t[i]!=0) return false;

		return false;
	}

	public static int premiereOccurencePlacement (int [] t) {
		int i, i1=0;
		boolean a = false;

		for (i=1; i<t.length-1; i++) {
			if (t[i]==0) {
				a = true;
				i1=i++;
				i=t.length-1;
			}
		}

		if (a) for (i=i1; i<t.length; i++) if (t[i]!=0) return i;

		System.out.println("Le tableau est classé.");

		return t.length-1;
	}

	public static int calculOccurencePlacement (int [] t) {
		int i;

		for (i=1; i<t.length-1; i++) {
			if (t[i]==0) return i;
		}

		System.out.println('"'+"calculOccurencePlacement"+'"'+" dit : Il n'y a plus de place i="+t.length+".");

		return -1;
	}

	public static int detecteSauvegardes () {
		int i=0;

		creeRepertoires();

		File f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+i+".txt");

		while (!f.exists() && i<1000) {
			i++;

			f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+i+".txt");		
		}

		if (i==1000) System.out.println("La fonction ''detecteSauvegardes'' dit : i = 1000.");

		return i;
	}

	public void SauveParametresFichier (int n, int a, int b, int c, int m, int d, int ndco, int nombreDePartiesJouées, boolean coupsIllimités, boolean premierCoups, boolean tourEnCours, boolean clm, int he, int mi, int se, int tailleDesPolices,int avanceeDuTour,int calculTemps, int x) { //a<s<b,n : nombre de cases, c : nombre de coups, m : mode de jeu, d : difficulté, ndco : nombre de couleurs.
		String s = "";
		String s1 ="Sauvegardes"+File.separatorChar+"Parametres.txt";
		int [] t;

		creeRepertoires();

		t = sauveParametres(n,a,b,c,m,d,ndco,nombreDePartiesJouées,coupsIllimités,premierCoups,tourEnCours,clm,he,mi,se,tailleDesPolices,avanceeDuTour,calculTemps,x);

		File f = new File(s1);

		if (f.exists()) effaceFichier(s1);

		s = sauveTableauInt(t,"tparametres",',');

		sauveFichier2(s,s1);		 
	}

	public static boolean effacePartie (int n) {
		creeRepertoires();

		File f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n+".txt");

		try{
			return (f.delete());
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception à l'effacement du fichier");
			return false;
		}
	}

	public static void renomePartie (int n5, int n6) {
		renomeFichier("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n5+".txt","Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n6+".txt");
	}

	public static void renomeFichier (String s1, String s0) {
		creeRepertoires();

		try{
			File source=new File(s1); 
			source.renameTo(new File(s0)); 

			//return true;
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Erreur lors du changement de nom du fichier "+'"'+s1+'"'+".");
			//return false;
		}
	}

	public static boolean detectePartie (int n) {
		int i=0;

		creeRepertoires();

		File f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n+".txt");

		return (f.exists());
	}

	public static int [] sauveParametres (int n, int a, int b, int c, int m, int d, int ndco, int nombreDePartiesJouées, boolean coupsIllimités, boolean premierCoups, boolean tourEnCours, boolean clm, int he, int mi, int se, int tailleDesPolices,int avanceeDuTour,int calculTemps, int x) { //a<s<b,n : nombre de cases, c : nombre de coups, m : mode de jeu, d : difficulté, ndco : nombre de couleurs.
		int [] t = new int [19];

		int icoupsIllimités = 0;
		
		if (coupsIllimités) icoupsIllimités = 1;

		int ipremierCoups = 0;
		
		if (premierCoups) ipremierCoups = 1;

		int itourEnCours = 0;
		
		if (tourEnCours) itourEnCours = 1;

		int iclm = 0;
		
		if (clm) iclm = 1;		

		t[0]=n;
		t[1]=a;
		t[2]=b;
		t[3]=c;
		t[4]=m;
		t[5]=d;
		t[6]=ndco;
		t[7]=nombreDePartiesJouées;
		t[8]=icoupsIllimités;
		t[9]=ipremierCoups;
		t[10]=itourEnCours;
		t[11]=iclm;
		t[12]=he;
		t[13]=mi;
		t[14]=se;
		t[15]=tailleDesPolices;
		t[16]=avanceeDuTour;
		t[17]=calculTemps;
		t[18]=x;

		return t;
	}

	public static void creeRepertoires () {
		File f;

		if (!detecteRepertoire("Sauvegardes")) {
			f=new File("Sauvegardes");
			f.mkdir();

		}

		if (!detecteRepertoire("Sauvegardes"+File.separatorChar+"Parties")) {
			f=new File("Sauvegardes"+File.separatorChar+"Parties");
			f.mkdir();
		}
	}

	/*public Object [] classStats (Object [][][][][][] t, int croissant, int [] parametres) {
		int [] i = new int [parametres.length];

		for (i[0] = ; i[0]<t.length
	}*/

	/*public static Object [] genereStatsAleatoires () {
		int i,j,k,l,m,o, n=0;

		
			|n|4 modes de jeu|3 difficultés|chronometre|Pseudo,score (nombre de coups),temps,date|

			Les items sont classés dans l'ordre croissant (n = 1,2,...,500|modes de jeu  = 1,2,3,4, etc...).

			chronomètre : 1 = pas de chrono, 0 = chrono.
		

		
		
	}*/

	public static int [] agrandieTabInt (int [] t) {
		int i;

		int [] t1 = new int[t.length+100];

		for (i=0; i<t.length; i++) t1[i]=t[i];

		t1[i+1]=-1346545;

		return t1;
	}

	public static String [] agrandieTabString (String [] s1) {
		int i;

		String [] s0 = new String [s1.length+100];

		for (i=0; i<s1.length; i++) s0[i]=s1[i];

		s0[i+1]="-1684685168168981";

		return s0;
	}

	public static int [] affTabInt (int [] t) {
		int i, cmp=0;

		for (i=0; i<t.length; i++) if (t[i]!=-1346545) cmp++;

		int [] t1 = new int [cmp];

		for (i=0; i<t1.length; i++) t1[i]=t[i];

		return t1;
	}

	public static String [] affTabString (String [] s1) {
		int i, cmp=0;

		for (i=0; i<s1.length; i++) if (!(s1[i].equals("-1684685168168981"))) cmp++;

		String [] s0 = new String[cmp];

		for (i=0; i<s0.length; i++) s0[i]=s1[i];

		return s0;
	}

	/*public static void sauveStatistiques () {
		creeRepertoires();

		File f1 = new File("Sauvegardes"+File.separatorChar+"Statistiques.txt");
		String s1 = "", s="";
		int g=0;

		boolean a;

		try{
			a = f1.exists();		
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
		}

		//int [][] t = new int [][];
		int [][] t1;

		if (a) {
			s1 = sauveTableau2(t,"t",',',':');
		}

		else {
			s = litSauvegarde("Sauvegardes"+File.separatorChar+"Statistiques.txt");

			t1 = litTableauInt2(s,',',':');

			//mise à jour...

			s1 = sauveTableau2(t1,"t",',',':');

			boolean d2 = effaceFichier("Sauvegardes"+File.separatorChar+"Statistiques.txt");

			try{
				boolean b = f1.createNewFile();		
			}
	
			catch(Exception e){
				e.printStackTrace();
				System.out.println("Echec de la sauvegarde : enregistrement du fichier.");
			}
		}

		try {
			PrintWriter pw = new PrintWriter(new FileWriter(f1));
		   	pw.println(s1);
			pw.flush();
			pw.close();
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Echec de la sauvegarde : écriture dans le fichier.");
		}
	}*/

	public static String litSauvegarde (String s0){
		String s="";

		try{
		    File f=new File(s0);

		    BufferedReader br=new BufferedReader(new FileReader(f));

		    String chainelue;

		    chainelue=br.readLine();

		    while(chainelue!=null){
				s=s+chainelue;
				chainelue=br.readLine();
		    }

		    //System.out.println("Fin de lecture");

		    br.close();
		}

		catch(Exception e){
		    System.out.println("Fichier introuvable.");
		}

		//System.out.println("Lire sauvegarde(fichier) = "+'"'+s+'"');

		return s;
	}

	public static String sauveTableauInt (int [] t1, String n, char c) {
		String s1="";
		int i;

		for (i=0; i<t1.length; i++)
			s1 = s1+n+"["+i+"] = "+t1[i]+c+"\n";

		return s1;
	}

	public static String sauveTableauString (String [] t1, String n, char c) {
		String s1="";
		int i;

		for (i=0; i<t1.length; i++)
			s1 = s1+n+"["+i+"] = "+t1[i]+c+"\n";

		return s1;
	}

	public static String sauveTableau2 (int [][] t1, String n, char c1, char c2) {
		String s1="";
		int i, j;

		for (i=0; i<t1.length; i++) {
			for (j=0; j<t1[i].length; j++) {
				s1 = s1+n+"["+i+"]["+j+"] = "+t1[i][j]+c2;

				if (j==t1[i].length-1) s1=s1+c1;

				s1=s1+"\n";
			}
		}

		return s1;
	}

	public static String SauveTableau3 (Object [][][] t1, String n, char c1, char c2, char c3) {
		String s1="";
		int i, j, k;

		for (i=0; i<t1.length; i++) {
			for (j=0; j<t1[i].length; j++) {
				for (k=0; k<t1[i][j].length; k++) {
					s1 = s1+n+"["+i+"]["+j+"]["+k+"] = "+t1[i][j][k]+c3;

					if (k==t1[i][j].length-1) s1=s1+c2;

					if (j==t1[i].length-1&&k==t1[i][j].length-1) s1=s1+c1;

					s1=s1+"\n";
				}
			}
		}

		return s1;
	}

	public static String SauveTableau4 (Object [][][][] t1, String n, char c1, char c2, char c3, char c4) {
		String s1="";
		int i, j, k,l;

		for (i=0; i<t1.length; i++) {
			for (j=0; j<t1[i].length; j++) {
				for (k=0; k<t1[i][j].length; k++) {
					for (l=0; l<t1[i][j][k].length; l++) {
						s1 = s1+n+"["+i+"]["+j+"]["+k+"]["+l+"] = "+t1[i][j][k][l]+c4;

						if (l==t1[i][j][k].length-1) s1=s1+c3;
	
						if (k==t1[i][j].length-1&&l==t1[i][j][k].length-1) s1=s1+c2;
	
						if (j==t1[i].length-1&&k==t1[i][j].length-1&&l==t1[i][j][k].length-1) s1=s1+c1;
	
						s1=s1+"\n";
					}
				}
			}
		}

		return s1;
	}

	public static String SauveTableau5 (Object [][][][][] t1, String n, char c1, char c2, char c3, char c4, char c5) {
		String s1="";
		int i, j, k,l,m;

		for (i=0; i<t1.length; i++) {
			for (j=0; j<t1[i].length; j++) {
				for (k=0; k<t1[i][j].length; k++) {
					for (l=0; l<t1[i][j][k].length; l++) {
						for (m=0; m<t1[i][j][k][l].length; m++) {
							s1 = s1+n+"["+i+"]["+j+"]["+k+"]["+l+"]["+m+"] = "+t1[i][j][k][l][m]+c5;
	
							if (m==t1[i][j][k][l].length-1) s1=s1+c4;

							if (l==t1[i][j][k].length-1&&m==t1[i][j][k][l].length-1) s1=s1+c3;
		
							if (k==t1[i][j].length-1&&l==t1[i][j][k].length-1&&m==t1[i][j][k][l].length-1) s1=s1+c2;
		
							if (j==t1[i].length-1&&k==t1[i][j].length-1&&l==t1[i][j][k].length-1&&m==t1[i][j][k][l].length-1) s1=s1+c1;
	
							s1=s1+"\n";
						}
					}
				}
			}
		}

		return s1;
	}

	public static String SauveTableau6 (Object [][][][][][] t1, String n, char c1, char c2, char c3, char c4, char c5, char c6) {
		String s1="";
		int i, j, k,l,m,o;

		for (i=0; i<t1.length; i++) {
			for (j=0; j<t1[i].length; j++) {
				for (k=0; k<t1[i][j].length; k++) {
					for (l=0; l<t1[i][j][k].length; l++) {
						for (m=0; m<t1[i][j][k][l].length; m++) {
							for (o=0; o<t1[i][j][k][l][m].length; o++) {
								s1 = s1+n+"["+i+"]["+j+"]["+k+"]["+l+"]["+m+"]["+o+"] = "+t1[i][j][k][l][m][o]+c6;
			
								if (o==t1[i][j][k][l][m].length-1) s1=s1+c5;
	
								if ((m==t1[i][j][k][l].length-1)&&(o==t1[i][j][k][l][m].length-1)) s1=s1+c4;
	
								if ((l==t1[i][j][k].length-1)&&(m==t1[i][j][k][l].length-1)&&(o==t1[i][j][k][l][m].length-1)) s1=s1+c3;
			
								if ((k==t1[i][j].length-1)&&(l==t1[i][j][k].length-1)&&(m==t1[i][j][k][l].length-1)&&(o==t1[i][j][k][l][m].length-1)) s1=s1+c2;
			
								if ((j==t1[i].length-1) && (k==t1[i][j].length-1) && (l==t1[i][j][k].length-1) && (m==t1[i][j][k][l].length-1) && (o==t1[i][j][k][l][m].length-1)) s1=s1+c1;
		
								s1=s1+"\n";

								System.out.println("i = "+i+" j = "+j+" k = "+k+" l = "+l);
							}
						}
					}
				}
			}
		}

		return s1;
	}

	public static String SauveTableau7 (Object [][][][][][][] t1, String n, char c1, char c2, char c3, char c4, char c5, char c6, char c7) {
		String s1="";
		int i, j, k,l,m,o,p;

		for (i=0; i<t1.length; i++) {
			for (j=0; j<t1[i].length; j++) {
				for (k=0; k<t1[i][j].length; k++) {
					for (l=0; l<t1[i][j][k].length; l++) {
						for (m=0; m<t1[i][j][k][l].length; m++) {
							for (o=0; o<t1[i][j][k][l][m].length; o++) {
								for (p=0; p<t1[i][j][k][l][m][o].length; p++) {
									s1 = s1+n+"["+i+"]["+j+"]["+k+"]["+l+"]["+m+"]["+o+"]["+p+"] = "+t1[i][j][k][l][m][o][p]+c7;
				
									if (p==t1[i][j][k][l][m][o].length-1) s1=s1+c6;
									
									if (o==t1[i][j][k][l][m].length-1&&p==t1[i][j][k][l][m][o].length-1) s1=s1+c5;
		
									if (m==t1[i][j][k][l].length-1&&o==t1[i][j][k][l][m].length-1&&p==t1[i][j][k][l][m][o].length-1) s1=s1+c4;
		
									if (l==t1[i][j][k].length-1&&m==t1[i][j][k][l].length-1&&o==t1[i][j][k][l][m].length-1&&p==t1[i][j][k][l][m][o].length-1) s1=s1+c3;
				
									if (k==t1[i][j].length-1&&l==t1[i][j][k].length-1&&m==t1[i][j][k][l].length-1&&o==t1[i][j][k][l][m].length-1&&p==t1[i][j][k][l][m][o].length-1) s1=s1+c2;
				
									if (j==t1[i].length-1&&k==t1[i][j].length-1&&l==t1[i][j][k].length-1&&m==t1[i][j][k][l].length-1&&o==t1[i][j][k][l][m].length-1&&p==t1[i][j][k][l][m][o].length-1) s1=s1+c1;
			
									s1=s1+"\n";
								}
							}
						}
					}
				}
			}
		}

		return s1;
	}

	public static int [] encode (int a, int b, int [] t1) {
		int i;

		int [] t2 = new int [t1.length];

		for (i=0; i<t1.length; i++) t2[i]=t1[i];

		for (i=0; i<t2.length; i++) t2[i]=t2[i]*b+a;

		return t2;
	}

	/*public static boolean detecteFichier (String s) {
		File f = new File(s);

		try {
			return (f.exists());
		}
		catch(Exception e) {
			return false;
		}
	}*/

	public static boolean detecteRepertoire (String s) {
		File f = new File(s);

		try {
			return(f.isDirectory());
		}
		catch(Exception e) {
			return false;
		}
	}
}


import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

class LectureFichiers {
	UtilDate d1;

	public LectureFichiers () {
		d1 = new UtilDate ();
	}

	/*public static String [] afficheSauvegardes () {
		int i=0;

		File f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+i+".txt");

		while (f.exists()) {
			i++;

			f = new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+i+".txt");		
		}

		if (i==1000) System.out.println("La fonction ''detecteSauvegardes'' dit : i = 1000.");

		return i;
	}*/

	public static int [] litParametreFichiers () {
		int i=0;

		String s1 = lireFichier("Sauvegardes"+File.separatorChar+"Parametres.txt");
		int s1length = s1.length ();
		String s0 ="";
		
		while (i<s1length && s1.charAt(i)!=';') {
			s0 = s0+s1.charAt(i);
			i++;
		}

		//System.out.println("\n\nlitTparametres(s0) = "+'"'+s0+'"');

		int [] tparametres = litTableauInt(s0,',');

		return tparametres;
	}

	public static boolean [] decodeParametres (int [] t) {
		boolean [] t1 = new boolean [4];

		t1[0] = false;
		if (t[8]==1) t1[0] = true; //coupsIllimités
		t1[1] = false;
		if (t[9]==1) t1[1] = true; //premierCoups
		t1[2] = false;
		if (t[10]==1) t1[2] = true; //tourEnCours
		t1[3] = false; 
		if (t[11]==1) t1[3] = true; //clm

		return t1;
	}

	public static boolean detecteParametres () {
		File f=new File("Sauvegardes"+File.separatorChar+"Parametres.txt");

		return f.exists ();
	}

	public static String lireSauvegarde (int n){
		String s="";

		try{
		    File f=new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n+".txt");

		    BufferedReader br=new BufferedReader(new FileReader(f));

		    String chainelue;

		    chainelue=br.readLine();

		    int x=0;

		    while(chainelue!=null){
			if (x>0) {
				s=s+chainelue+"\n";
			}
				chainelue=br.readLine();
			x++;
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

	public static String lireSauvegardeDate (int n){
		String s="";

		try{
		    File f=new File("Sauvegardes"+File.separatorChar+"Parties"+File.separatorChar+"Partie"+n+".txt");

		    BufferedReader br=new BufferedReader(new FileReader(f));

		    String chainelue;

		    chainelue=br.readLine();

			int x=0;

			s=s+chainelue;
			chainelue=br.readLine();

		    //System.out.println("Fin de lecture");

		    br.close();
		}

		catch(Exception e){
		    System.out.println("Fichier introuvable.");
		}

		//System.out.println("Lire sauvegarde(fichier) = "+'"'+s+'"');

		return s;
	}

	public static String lireFichier (String s1){
		String s="";

		try{
		    File f=new File(s1);

		    BufferedReader br=new BufferedReader(new FileReader(f));

		    String chainelue;

		    chainelue=br.readLine();

		    while(chainelue!=null){
				s=s+chainelue;
				chainelue=br.readLine();
		    }

		    System.out.println("Fin de lecture");

		    br.close();
		}

		catch(Exception e){
		    System.out.println("Fichier introuvable.");
		}

		//System.out.println("Lire sauvegarde(fichier) = "+'"'+s+'"');

		return s;
	}

	public static int [][] litTcoups (String s1) {
		int i=0,s1length=s1.length();
		String s0 = "";

		while (i<s1length && s1.charAt(i)!=';') {
			s0 = s0+s1.charAt(i);
			i++;
		}

		//System.out.println("\n\nlitTcoups(s0) = "+'"'+s0+'"');

		int [][] tcoups = litTableauInt2(s0,',',':');

		return tcoups;		
	}

	public static int [][] litTcouleurs (String s1) {
		int i=0,s1length=s1.length();
		String s0 = "";

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') {
			s0 = s0+s1.charAt(i);
			i++;
		}

		//System.out.println("\n\nlitTcouleurs(s0) = "+'"'+s0+'"');

		int [][] tcouleurs = litTableauInt2(s0,',',':');

		return tcouleurs;		
	}

	public static int [][] litReponses(String s1) {
		int i=0,s1length=s1.length();
		String s0 = "";

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') {
			s0 = s0+s1.charAt(i);
			i++;
		}

		//System.out.println("\n\nlitTparametres(s0) = "+'"'+s0+'"');

		int [][] Reponses = litTableauInt2(s0,',',':');

		return Reponses;	
	}

	public static int [] litTparametres (String s1) {
		int i=0,s1length=s1.length();
		String s0 = "";

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') {
			s0 = s0+s1.charAt(i);
			i++;
		}

		//System.out.println("\n\nlitTparametres(s0) = "+'"'+s0+'"');

		int [] tparametres = litTableauInt(s0,',');

		return tparametres;	
	}

	public static int [] litTcode (String s1) {
		int i=0,s1length=s1.length();
		String s0 = "";

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') i++;

		i++;

		while (i<s1length && s1.charAt(i)!=';') {
			s0 = s0+s1.charAt(i);
			i++;
		}

		//System.out.println("\n\nlitTcode(s0) = "+'"'+s0+'"');

		int [] tcode = litTableauInt(s0,',');

		return decode(4564,2165,tcode);	
	}

	public static String [][] litTableauString2 (String s, char c1, char c2) {
		int i,j;
		String [] s1;
		String [][] s0;

		s1 = coupe (s,c1);

		s0 = new String [s1.length][];

		for (i=0; i<s1.length; i++) s0[i]=litTableauString(s1[i],c2);

		//for (i=0; i<s0.length; i++) for (j=0; j<s0[i].length; j++) System.out.println("s0["+i+"]["+j+"] = "+s0[i][j]);

		return s0;
	}

	public static int [][] litTableauInt2 (String s, char c1, char c2) {
		int i,j;
		String [] s1;
		String [][] s0;

		s1 = coupe(s,c1);

		//for (i=0; i<s1.length; i++) System.out.println("s1["+i+"] = "+s1[i]);

		s0 = new String [s1.length][];

		for (i=0; i<s1.length; i++) s0[i]=litTableauString(s1[i],c2);

		int [][] t = new int [s1.length][];

		for (i=0; i<s1.length; i++) {
			t[i] = new int[s0[i].length];

			for (j=0; (j<t[i].length && j<s0[i].length); j++) {
				t[i][j] = convertNombre(s0[i][j]);
			}
		}

		//for (i=0; i<s0.length; i++) for (j=0; j<s0[i].length; j++) System.out.println("s0["+i+"]["+j+"] = "+s0[i][j]);

		return t;		
	}

	public static String [][][] litTableauString3 (String s1, char c1, char c2, char c3) {
		int i,j,k;

		//System.out.println("s1 = "+'"'+s1+'"');

		String [] s2 = coupe3(s1,c1);

		//for (i=0; i<s2.length; i++)  System.out.println("s2["+i+"] = "+s2[i]);

		String [][] s3 = new String [s2.length][];

		for (i=0; i<s2.length; i++) s3 [i] = coupe3(s2[i],c2);

		//for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) System.out.println("s3["+i+"][j] = "+s3[i][j]);

		String [][][] s4 = new String [s2.length][s3[0].length][];

		for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) s4 [i][j] = coupe2(s3[i][j],c3);

		//for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) System.out.println("s4["+i+"]["+j+"]["+k+"] = "+s4[i][j][k]);
	
		return s4;
	}

	public static String [][][][] litTableauString4 (String s1, char c1, char c2, char c3, char c4) {
		int i,j,k,l;

		//System.out.println("s1 = "+'"'+s1+'"');

		String [] s2 = coupe3(s1,c1);

		//for (i=0; i<s2.length; i++)  System.out.println("s2["+i+"] = "+s2[i]);

		String [][] s3 = new String [s2.length][];

		for (i=0; i<s2.length; i++) s3 [i] = coupe3(s2[i],c2);

		//for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) System.out.println("s3["+i+"][j] = "+s3[i][j]);

		String [][][] s4 = new String [s2.length][s3[0].length][];

		for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) s4 [i][j] = coupe3(s3[i][j],c3);

		//for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) System.out.println("s4["+i+"]["+j+"]["+k+"] = "+s4[i][j][k]);

		String [][][][] s5 = new String [s2.length][s3[0].length][s4[0][0].length][];

		for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) s5 [i][j][k] = coupe2(s4[i][j][k],c4);

		//for (i=0; i<s5.length; i++) for (j=0; j<s5[i].length; j++) for (k=0; k<s5[i][j].length; k++) for (l=0; l<s5[i][j][k].length; l++) System.out.println("s5["+i+"]["+j+"]["+k+"]["+l+"] = "+s5[i][j][k][l]);
	
		return s5;
	}

	public static String [][][][][] litTableauString5 (String s1, char c1, char c2, char c3, char c4, char c5) {
		int i,j,k,l,m;

		String [] s2 = coupe3(s1,c1);

		//for (i=0; i<s2.length; i++)  System.out.println("s2["+i+"] = "+s2[i]);

		String [][] s3 = new String [s2.length][];

		for (i=0; i<s2.length; i++) s3 [i] = coupe3(s2[i],c2);

		//for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) System.out.println("s3["+i+"][j] = "+s3[i][j]);

		String [][][] s4 = new String [s2.length][s3[0].length][];

		for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) s4 [i][j] = coupe3(s3[i][j],c3);

		//for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) System.out.println("s4["+i+"]["+j+"]["+k+"] = "+s4[i][j][k]);

		String [][][][] s5 = new String [s2.length][s3[0].length][s4[0][0].length][];

		for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) s5 [i][j][k] = coupe3(s4[i][j][k],c4);

		//for (i=0; i<s5.length; i++) for (j=0; j<s5[i].length; j++) for (k=0; k<s5[i][j].length; k++) for (l=0; l<s5[i][j][k].length; l++) System.out.println("s5["+i+"]["+j+"]["+k+"]["+l+"] = "+s5[i][j][k][l]);
	
		String [][][][][] s6 = new String [s2.length][s3[0].length][s4[0][0].length][s5[0][0][0].length][];

		for (i=0; i<s5.length; i++) for (j=0; j<s5[i].length; j++) for (k=0; k<s5[i][j].length; k++) for (l=0; l<s5[i][j][k].length; l++) s6 [i][j][k][l] = coupe2(s5[i][j][k][l],c5);

		//for (i=0; i<s6.length; i++) for (j=0; j<s6[i].length; j++) for (k=0; k<s6[i][j].length; k++) for (l=0; l<s6[i][j][k].length; l++) for (m=0; m<s6[i][j][k][l].length; m++) System.out.println("s6["+i+"]["+j+"]["+k+"]["+l+"]["+m+"] = "+s6[i][j][k][l][m]);

		return s6;
	}

	public static String [][][][][][] litTableauString6 (String s1, char c1, char c2, char c3, char c4, char c5, char c6) {
		int i,j,k,l,m,o;

		String [] s2 = coupe3(s1,c1);

		//for (i=0; i<s2.length; i++)  System.out.println("s2["+i+"] = "+s2[i]);

		String [][] s3 = new String [s2.length][];

		for (i=0; i<s2.length; i++) s3 [i] = coupe3(s2[i],c2);

		//for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) System.out.println("s3["+i+"][j] = "+s3[i][j]);

		String [][][] s4 = new String [s2.length][s3[0].length][];

		for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) s4 [i][j] = coupe3(s3[i][j],c3);

		//for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) System.out.println("s4["+i+"]["+j+"]["+k+"] = "+s4[i][j][k]);

		String [][][][] s5 = new String [s2.length][s3[0].length][s4[0][0].length][];

		for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) s5 [i][j][k] = coupe3(s4[i][j][k],c4);

		for (i=0; i<s5.length; i++) for (j=0; j<s5[i].length; j++) for (k=0; k<s5[i][j].length; k++) for (l=0; l<s5[i][j][k].length; l++) System.out.println("s5["+i+"]["+j+"]["+k+"]["+l+"] = "+s5[i][j][k][l]);
	
		String [][][][][] s6 = new String [s2.length][s3[0].length][s4[0][0].length][s5[0][0][0].length][];

		for (i=0; i<s5.length; i++) for (j=0; j<s5[i].length; j++) for (k=0; k<s5[i][j].length; k++) for (l=0; l<s5[i][j][k].length; l++) s6 [i][j][k][l] = coupe3(s5[i][j][k][l],c5);

		for (i=0; i<s6.length; i++) for (j=0; j<s6[i].length; j++) for (k=0; k<s6[i][j].length; k++) for (l=0; l<s6[i][j][k].length; l++) for (m=0; m<s6[i][j][k][l].length; m++) System.out.println("s6["+i+"]["+j+"]["+k+"]["+l+"]["+m+"] = "+s6[i][j][k][l][m]);

		String [][][][][][] s7 = new String [s2.length][s3[0].length][s4[0][0].length][s5[0][0][0].length][s6[0][0][0][0].length][];

		for (i=0; i<s6.length; i++) for (j=0; j<s6[i].length; j++) for (k=0; k<s6[i][j].length; k++) for (l=0; l<s6[i][j][k].length; l++)  for (m=0; m<s6[i][j][k][l].length; m++) s7 [i][j][k][l][m] = coupe2(s6[i][j][k][l][m],c6);

		//for (i=0; i<s7.length; i++) for (j=0; j<s7[i].length; j++) for (k=0; k<s7[i][j].length; k++) for (l=0; l<s7[i][j][k].length; l++) for (m=0; m<s7[i][j][k][l].length; m++) for (o=0; o<s7[i][j][k][l][m].length; o++)  System.out.println("s7["+i+"]["+j+"]["+k+"]["+l+"]["+m+"]["+o+"] = "+s7[i][j][k][l][m][o]);

		return s7;
	}

	public static String [][][][][][][] litTableauString7 (String s1, char c1, char c2, char c3, char c4, char c5, char c6, char c7) {
		int i,j,k,l,m,o,p;

		String [] s2 = coupe(s1,c1);

		//for (i=0; i<s2.length; i++)  System.out.println("s2["+i+"] = "+s2[i]);

		String [][] s3 = new String [s2.length][];

		for (i=0; i<s2.length; i++) s3 [i] = coupe3(s2[i],c2);

		//for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) System.out.println("s3["+i+"][j] = "+s3[i][j]);

		String [][][] s4 = new String [s2.length][s3[0].length][];

		for (i=0; i<s3.length; i++) for (j=0; j<s3[i].length; j++) s4 [i][j] = coupe3(s3[i][j],c3);

		//for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) System.out.println("s4["+i+"]["+j+"]["+k+"] = "+s4[i][j][k]);

		String [][][][] s5 = new String [s2.length][s3[0].length][s4[0][0].length][];

		for (i=0; i<s4.length; i++) for (j=0; j<s4[i].length; j++) for (k=0; k<s4[i][j].length; k++) s5 [i][j][k] = coupe3(s4[i][j][k],c4);

		//for (i=0; i<s5.length; i++) for (j=0; j<s5[i].length; j++) for (k=0; k<s5[i][j].length; k++) for (l=0; l<s5[i][j][k].length; l++) System.out.println("s5["+i+"]["+j+"]["+k+"]["+l+"] = "+s5[i][j][k][l]);
	
		String [][][][][] s6 = new String [s2.length][s3[0].length][s4[0][0].length][s5[0][0][0].length][];

		for (i=0; i<s5.length; i++) for (j=0; j<s5[i].length; j++) for (k=0; k<s5[i][j].length; k++) for (l=0; l<s5[i][j][k].length; l++) s6 [i][j][k][l] = coupe3(s5[i][j][k][l],c5);

		//for (i=0; i<s6.length; i++) for (j=0; j<s6[i].length; j++) for (k=0; k<s6[i][j].length; k++) for (l=0; l<s6[i][j][k].length; l++) for (m=0; m<s6[i][j][k][l].length; m++) System.out.println("s6["+i+"]["+j+"]["+k+"]["+l+"]["+m+"] = "+s6[i][j][k][l][m]);

		String [][][][][][] s7 = new String [s2.length][s3[0].length][s4[0][0].length][s5[0][0][0].length][s6[0][0][0][0].length][];

		for (i=0; i<s6.length; i++) for (j=0; j<s6[i].length; j++) for (k=0; k<s6[i][j].length; k++) for (l=0; l<s6[i][j][k].length; l++)  for (m=0; m<s6[i][j][k][l].length; m++) s7 [i][j][k][l][m] = coupe3(s6[i][j][k][l][m],c6);

		//for (i=0; i<s7.length; i++) for (j=0; j<s7[i].length; j++) for (k=0; k<s7[i][j].length; k++) for (l=0; l<s7[i][j][k].length; l++) for (m=0; m<s7[i][j][k][l].length; m++) for (o=0; o<s7[i][j][k][l][m].length; o++)  System.out.println("s7["+i+"]["+j+"]["+k+"]["+l+"]["+m+"]["+o+"] = "+s7[i][j][k][l][m][o]);

		String [][][][][][][] s8 = new String [s2.length][s3[0].length][s4[0][0].length][s5[0][0][0].length][s6[0][0][0][0].length][s7[0][0][0][0][0].length][];

		for (i=0; i<s7.length; i++) for (j=0; j<s7[i].length; j++) for (k=0; k<s7[i][j].length; k++) for (l=0; l<s7[i][j][k].length; l++) for (m=0; m<s7[i][j][k][l].length; m++) for (o=0; o<s7[i][j][k][l][m].length; o++) s8 [i][j][k][l][m][o] = coupe2(s7[i][j][k][l][m][o],c7);

		//for (i=0; i<s8.length; i++) for (j=0; j<s8[i].length; j++) for (k=0; k<s8[i][j].length; k++) for (l=0; l<s8[i][j][k].length; l++) for (m=0; m<s8[i][j][k][l].length; m++) for (o=0; o<s8[i][j][k][l][m].length; o++) for (p=0; p<s8[i][j][k][l][m][o].length; p++) System.out.println("s8["+i+"]["+j+"]["+k+"]["+l+"]["+m+"]["+o+"]["+p+"] = "+s8[i][j][k][l][m][o][p]);

		return s8;
	}

	public static String [] coupe (String s1, char c) {
		int i, j, s1length=s1.length(), length=0;

		for (i=0; i<s1length; i++) if (s1.charAt(i)==c) length++;

		String [] s0 = new String [length];

		for (i=0; i<s0.length; i++) s0[i]="";

		int indice1=0;

		for (i=0; i<length; i++){
			j=indice1;
			while (j<s1length && s1.charAt(j)!=c) {
				s0[i] = s0[i]+s1.charAt(j);
				j++;
			}

			j=j+1;
		}

		for (i=0; i<s0.length; i++) System.out.println("s0["+i+"] = "+s0[i]);

		return s0;
	}

	public static String [] coupe3 (String s1, char c) {
		int i, j, s1length=s1.length(), length=0;

		for (i=0; i<s1length; i++) if (s1.charAt(i)==c) length++;

		String [] s0 = new String [length];

		for (i=0; i<s0.length; i++) s0[i]="";

		j=0;

		for (i=0; i<length; i++){
			while (j<s1length && s1.charAt(j)!=c) {
				s0[i] = s0[i]+s1.charAt(j);
				j++;
			}

			j=j+1;
		}

		//for (i=0; i<s0.length; i++) System.out.println("s0["+i+"] = "+s0[i]);

		return s0;
	}

	public static String [] coupe2 (String s1, char c) {
		int i, j, s1length=s1.length(), length=0;

		for (i=0; i<s1length; i++) if (s1.charAt(i)==c) length++;

		String [] s0 = new String [length];

		for (i=0; i<s0.length; i++) s0[i]="";

		j=0;

		for (i=0; i<length; i++){
			while (j<s1length && s1.charAt(j)!=' ') {
				j++;
			}

			j+=3;

			while (j<s1length && s1.charAt(j)!=c) {
				s0[i] = s0[i]+s1.charAt(j);
				j++;
			}

			j=j+1;
		}

		//for (i=0; i<s0.length; i++) System.out.println("s0["+i+"] = "+s0[i]);

		return s0;
	}

	public static String [] litTableauString (String s1,char c) {
		int i, j ,k ,length=0, s1length=s1.length();

		for (i=0; i<s1length; i++) if (s1.charAt(i)==c) length++;

		//System.out.println("length = "+length);

		String [] s0 = new String[length];

		for (i=0; i<length; i++) s0[i]="";

		int indice1=0;

		for (i=0; i<length; i++){
			j=indice1;
			while (j<s1length && s1.charAt(j)!=c) {
				s0[i] = s0[i]+s1.charAt(j);
				j++;
			}

			indice1=j+1;
		}

		String [] t = new String [length];

		for (i=0; i<length; i++){
			t[i]=decodeTableauString2(s0[i]);			
		}

		//for (i=0; i<s0.length; i++) System.out.println("s0["+i+"] = "+s0[i]);

		return t;			
	}

	public static int [] litTableauInt (String s1, char c) {
		int i, j ,k ,length=0, s1length=s1.length();

		for (i=0; i<s1length; i++) if (s1.charAt(i)==c) length++;

		//System.out.println("length = "+length);

		String [] s0 = new String[length];

		for (i=0; i<length; i++) s0[i]="";

		int indice1=0;

		for (i=0; i<length; i++){
			j=indice1;
			while (j<s1length && s1.charAt(j)!=c) {
				s0[i] = s0[i]+s1.charAt(j);
				j++;
			}

			indice1=j+1;
		}

		int [] t = new int [length];

		for (i=0; i<length; i++){
			t[i]=decodeTableauNombre2(s0[i]);			
		}

		//for (i=0; i<s0.length; i++) System.out.println("s0["+i+"] = "+s0[i]);

		return t;						
	}

	public static String decodeTableauString1 (String s) {
		int i=0;

		//System.out.println("s = "+'"'+s+'"');

		String s1="";
		int slength=s.length();

		while(i<slength && s.charAt(i)!=' ') {
			s1=s1+s.charAt(i);
			i++;
		}

		return s1;
	}

	public static String decodeTableauString2 (String s) {
		int i=0;

		String s1="";
		int slength=s.length();

		//System.out.println("s = "+'"'+s+'"');

		while (i<slength && s.charAt(i)!=' ') i++;

		i=i+3;

		while (i<slength && s.charAt(i)!='_') {
			s1=s1+s.charAt(i);
			i++;
		}

		return s1;
	}

	public static int decodeTableauNombre2 (String s) {
		int i=0;

		String s1="";
		int slength=s.length();

		//System.out.println("s = "+'"'+s+'"');

		while (i<slength && s.charAt(i)!=' ') i++;

		i=i+3;

		while (i<slength && s.charAt(i)!='_') {
			s1=s1+s.charAt(i);
			i++;
		}

		return convertNombre(s1);
	}

	public static boolean effacePartie (int n) {
		File f=new File("Partie"+n+".txt");

		try{
			if(f.delete()==true) return true;
			else return false;
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception à l'effacement du fichier");
		}

		return true;
 	}

	public static boolean effaceFichier (String s0) {
		File f=new File(s0);

		try{
			if(f.delete()==true) return true;
			else return false;
		}

		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception à l'effacement du fichier");
		}

		return true;
 	}

	public static int [] decode (int a, int b, int [] t1) {
		int i;

		int [] t2 = new int [t1.length];

		for (i=0; i<t1.length; i++) t2[i]=t1[i];

		for (i=0; i<t2.length; i++) t2[i]=(t2[i]-a)/b;

		return t2;
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

		if (d<0) {
			System.out.println("s = "+s);
			System.out.println("\n//Le nombre est trop grand");
		}

		return d;

	}
}
class UtilJeu {
	public UtilJeu () {

	}

	public static int compteOccurences (int [] t, int a) {
		int i, b=0;

		for (i=0; i<t.length; i++) if (t[i]==a) b++;
		
		return b;
    	}

	public static boolean Verifie2 (int [] t, int a){
		for (int i=0; i<t.length; i++) { if (t[i]==a) return true; }

		return false;
	}


	public static boolean VerifieSolution (int [] t1, int [] t2) {
		for (int i=0; i<t1.length; i++) { if (t1[i]!=t2[i]) return false; }

		return true;
	}

	public static void VerifieFacile (int [] t1, int [] t2){
		System.out.println();
		for (int i=0; i<t2.length; i++) {
			if (t1[i]==t2[i]) System.out.println("Nombre "+(i+1)+" est bien place.");
			if ((t1[i]!=t2[i]) && (Verifie2(t1,t2[i]))) System.out.println("Nombre "+(i+1)+" est mal place (il y en a un ou plus dans les solutions).");
			if ((t1[i]!=t2[i]) && (!Verifie2(t1,t2[i]))) System.out.println("Nombre "+(i+1)+" est faux (il n'est pas dans les solutions).");
		}
	}

	public static int [] VerifieFacileOrdi (int [] t111, int [] t211){
		int [] t112 = new int [t211.length];

		for (int i=0; i<t211.length; i++) {
			if (t111[i]==t211[i]) t112[i]=1;
			if ((t111[i]!=t211[i]) && (Verifie2(t111,t211[i]))) t112[i]=2;
			if ((t111[i]!=t211[i]) && (!Verifie2(t111,t211[i]))) t112[i]=0;
		}

		return t112;
	}

	public static int [] VerifieMoyen (int a, int b, int n, int [] t1, int [] t2) {
		int i, j, r1=0,r2=0, occ=0, occV=0;

		if (t1.length!=t2.length) {
			System.out.println("La fonction ''verifieMoyen'' dit : ''Les tableaux ne sont pas de la meme taille.''");
			return new int[2];
		}

		int [] t10 = new int [2];
		
		int [] t11 = new int [t1.length];

		for (i=0; i<t1.length; i++) if (t1[i]==t2[i]) r1++;
		else {
			for (i=0; i<t1.length; i++) {
				occ = compteOccurences(t1,t2[i]);

				for (j=0; j<t1.length; j++) if (t2[j]==t1[i]) {
					if (t11[i]<occ) {
						t11[i]++;
	
						r2++;
					}
				}
			}
		}

		t10[0] = r1;
		t10[1] = r2;

		return t10;
	}

	/**
		Fonction qui vérifie la combinaison entrée en mode Moyen :
		elle donne juste le nombre de chiffres bien placés et le nombre chiffres mal placés.

		r1 = nombre de chiffres bien placés.
		r2 = nombre de chiffre mal placés.
	**/

	/*public static int [] VerifieMoyen (int a, int b, int n, int [] t1, int [] t2){
		int i, j, r1=0, r2=0, bn;

		if (n>b) bn=n; else bn=b;

		int t10[] = new int[2]; 
		int [][] t0 = new int [2][bn];
		int [][] t3 = new int [2][bn];

		if (t1.length!=t2.length) {
			System.out.println("La fonction ''verifieMoyen'' dit : ''Les tableaux ne sont pas de la meme taille.''");
			return t10;
		}
		
		for (i=0; i<bn; i++) t0[0][i]=i+a;
		for (i=0; i<bn; i++) t3[0][i]=i+a;

		for (i=0; i<bn; i++) t0[1][i]=-1;
		for (i=0; i<bn; i++) t3[1][i]=-2;

		
		for (i=0; i<n; i++) if (t1[i]==t2[i]) r1++;

		for (i=0; i<n; i++) for (j=0; j<bn; j++) if (t1[i]==t3[0][j]) if (t3[1][j]==-2) t3[1][j]=1; else t3[1][j]++;

		for (i=0; i<n; i++) for (j=0; j<bn; j++) if (t2[i]==t0[0][j]) if (t0[1][j]==-1) t0[1][j]=1; else t0[1][j]++;

		for (i=0; i<bn; i++) if (t3[1][i]==t0[1][i]) r2=r2+t0[1][i]; if (r2>=r1) r2=r2-r1;

		//for (i=0; i<n; i++) System.out.println("t1["+(i+1)+"] = "+t1[i]);
		//for (i=0; i<bn; i++) System.out.println("t0["+(i+1)+"] = "+t0[1][i]);
		//for (i=0; i<bn; i++) System.out.println("t3["+(i+1)+"] = "+t3[1][i]);

		t10[0]=r1; t10[1]=r2;

		return t10;
	}*/

	/**
		Fonction qui vérifie la combinaison entrée en mode Difficile :
		elle donne juste le nombre de chiffres bien ou mal placés.
	**/

	public static int VerifieDifficile (int a, int b, int n, int [] t1, int [] t2){
		int i, j, r1=0, r2=0, bn;

		if (n>b) bn=n; else bn=b;

		int [][] t0 = new int [2][bn];
		int [][] t3 = new int [2][bn];

		if (t1.length!=t2.length) {
			System.out.println("La fonction ''verifieMoyen'' dit : ''Les tableaux ne sont pas de la meme taille.''");
			return 0;
		}
		
		for (i=0; i<bn; i++) t0[0][i]=i+a; //remplissage de la ligne qui contient les numéros.
		for (i=0; i<bn; i++) t3[0][i]=i+a; //remplissage de la ligne qui contient les numéros.

		for (i=0; i<bn; i++) t0[1][i]=-1; //il faut que t0[1][i] et t3[1][i] soit différents pour l'étape de la vérification.
		for (i=0; i<bn; i++) t3[1][i]=-2;

		
		for (i=0; i<n; i++) if (t1[i]==t2[i]) r1++; //analyse des chiffres bien placés.

		for (i=0; i<n; i++) for (j=0; j<bn; j++) if (t1[i]==t3[0][j]) if (t3[1][j]==-2) t3[1][j]=1; else t3[1][j]++; //On compte le nombre d'occurences de chaque chiffre dans les deux tableaux.

		for (i=0; i<n; i++) for (j=0; j<bn; j++) if (t2[i]==t0[0][j]) if (t0[1][j]==-1) t0[1][j]=1; else t0[1][j]++;

		for (i=0; i<bn; i++) if (t3[1][i]==t0[1][i]) r2=r2+t0[1][i]; if (r2>=r1) r2=r2-r1; //On compare les deux tableaux puis on enlève le nombre de chiffres bien placés.

		//for (i=0; i<n; i++) System.out.println("t1["+(i+1)+"] = "+t1[i]);
		//for (i=0; i<bn; i++) System.out.println("t0["+(i+1)+"] = "+t0[1][i]);
		//for (i=0; i<bn; i++) System.out.println("t3["+(i+1)+"] = "+t3[1][i]);

		return (r1+r2);
	}
}
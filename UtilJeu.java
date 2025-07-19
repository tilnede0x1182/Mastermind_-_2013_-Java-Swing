class UtilJeu {
	public UtilJeu () {

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

	/**
		Fonction qui vérifie la combinaison entrée en mode Moyen :
		elle donne juste le nombre de chiffres bien placés et le nombre chiffres mal placés.

		r1 = nombre de chiffres bien placés.
		r2 = nombre de chiffre mal placés.
	**/


	public static int [] VerifieMoyen (int a, int b, int n, int [] t1, int [] t2){
		int i, j, r1=0,r2=0, occ=0, occV=0;

		if (t1.length!=t2.length) {
			System.out.println("La fonction ''verifieMoyen'' dit : ''Les tableaux ne sont pas de la meme taille.''");
			return new int[2];
		}

		int [] t10 = new int [2];
		
		int [][] t11 = new int [2][t1.length];

		for (i=0; i<t1.length; i++) {
			t11[0][i] = t1[i];

			t11[1][i] = 1;
		}

		for (i=0; i<t1.length; i++) {
			if (t1[i]==t2[i]) {
				r1++;

				t11[1][i] = 2;
			}
			else {
				for (j=0; j<t1.length; j++) {
					if (t2[j]==t1[i]) {
						if (t11[1][j]==1) {r2++; t11[1][j] = 0; }
					}
				}
			}
		}


		t10[0] = r1;
		t10[1] = r2;

		return t10;
	}

	/**
		Fonction qui vérifie la combinaison entrée en mode Difficile :
		elle donne juste le nombre de chiffres bien ou mal placés.
	**/

	public static int VerifieDifficile (int a, int b, int n, int [] t1, int [] t2){
		int i, j, r1=0,r2=0, occ=0, occV=0;

		if (t1.length!=t2.length) {
			System.out.println("La fonction ''verifieMoyen'' dit : ''Les tableaux ne sont pas de la meme taille.''");
			return 0;
		}

		
		int [][] t11 = new int [2][t1.length];

		for (i=0; i<t1.length; i++) {
			t11[0][i] = t1[i];

			t11[1][i] = 1;
		}

		for (i=0; i<t1.length; i++) {
			if (t1[i]==t2[i]) {
				r1++;

				t11[1][i] = 2;
			}
			else {
				for (j=0; j<t1.length; j++) {
					if (t2[j]==t1[i]) {
						if (t11[1][j]==1) {r2++; t11[1][j] = 0; }
					}
				}
			}
		}

		return (r1+r2);
	}
}
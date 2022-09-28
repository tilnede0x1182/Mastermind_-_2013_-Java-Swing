
public class IaCalculsMoyen {

	public int a;
	public int b;
	public int n;

	public int cmp;

	public int [] t1;
	public int [][] t10;	
	public int [][] t11;	
	public int [][] t12;	
	public int [][] t15;	

	public IaCalculsMoyen () {
	}

	public IaCalculsMoyen (int n, int a, int b) {
		int i, j, c=puissance(b,n), d = puissance(2,n);

		this.a = a;		
		this.b = b;
		this.n = n;

		cmp = 0;

		t1 = new int [n];
		t10 = new int [c][n];
		t11 = new int [d][2];
		t12 = new int [c][n];

		t15 = new int [c][d];	
	}

	public int [] ia (int [] t2) {
		int i, j, h, min=0, max=0, c=puissance(b,n), d = puissance(2,n), indice=0;
		int [] t;

		while (t2[0]<n) {
			if (cmp==0) {
				t10 = genereAleatPossibilite2(a,b,c,n);
				t11 = genereAleatPossibilite3(1,3,d,n);
			}

			if (cmp>0) {
				for (i=0; i<t10.length; i++)
					for (j=0; j<t10.length; j++)
						if (compare1(verifieMoyen(t1,t10[j]),t2)) t12[i]=t10[j];

				indice=0;

				for (i=0; i<t10.length; i++)
					if (t12[i]!=null) indice++;

				t10 = new int [indice][n];
				
				j=0;
				for (i=0; i<t12.length; i++) {
					if (t12[i]!=null) {
						t10[j]=t12[i];
						j++;
					}
				}
			}
			
			for (i=0; i<t10.length; i++)
				for (j=0; j<t11.length; j++)
					for (h=0; h<t10.length; h++)
						if (compare1(verifieMoyen(t10[i],t10[h]),t11[j])) {
							c = t15[i][j]; c++;
							t15[i][j] = c;
						}

			t = new int [t10.length];

			for (i=0; i<t15.length; i++)
				for (j=0; j<t15[i].length; j++)
					t15[i][j] = t15.length-t15[i][j];


			for (i=0; i<t15.length; i++)
				t[i] = maxValeur(t15[i]);

			max = min(t);

			for (i=0; i<n; i++) t1[i] = t10[max][i];

			cmp++;

			return t10[max];					
		}

		return t10[max];
	}

	public int max (int [] t) {
		int i, c=0, v=0;

		for (i=0; i<t.length; i++)
			if (t[i]>v) { c=i; v=t[i]; }

		return c;
	}

	public int maxValeur (int [] t) {
		int i, c=0, v=0;

		for (i=0; i<t.length; i++)
			if (t[i]>v) { c=i; v=t[i]; }

		return v;
	}

	public int min (int [] t) {
		int i, c=0, v=1000000;

		for (i=0; i<t.length; i++)
			if (t[i]<v) { c=i; v=t[i]; }

		return c;
	}

	public int minValeur (int [] t) {
		int i, c=0, v=1000000;

		for (i=0; i<t.length; i++)
			if (t[i]<v) { c=i; v=t[i]; }

		return v;
	}

	public boolean compare1 (int [] t1, int [] t2) {
		int i;

		if (t1.length!=t2.length) {
			System.out.println("La fonction ''verifie'' dit : ''Les tableaux ne sont pas de la meme taille.''");
			return false;
		}

		for (i=0; i<t1.length; i++) if (t1[i]!=t2[i]) return false;

		return true;
	}

	/**
		r1 : nombre de chiffres bien placés.
		r2 : nombre de chiffres bien placés.

		t1 = tableau des résultats.
		t2 = tableau à vérifier.
	**/

	public int [] verifieMoyen (int [] t1, int [] t2) {
		int i, j, r1=0, r2=0;

		int [] t4 = new int [2];
		int [][] t0 = new int [2][n];
		int [][] t3 = new int [2][n];

		if (t1.length!=t2.length) {
			System.out.println("La fonction ''verifieMoyen'' dit : ''Les tableaux ne sont pas de la meme taille.''");
			return t4;
		}
		
		for (i=0; i<n; i++) t0[0][i]=i+a;
		for (i=0; i<n; i++) t3[0][i]=i+a;

		
		for (i=0; i<n; i++) if (t1[i]==t2[i]) r1++;

		for (i=0; i<n; i++) for (j=0; j<n; j++) if (t1[i]==t3[0][j]) t3[1][j]++;

		for (i=0; i<n; i++) for (j=0; j<n; j++) if (t2[i]==t0[0][j]) t0[1][j]++;

		for (i=0; i<n; i++) if (t3[1][i]==t0[1][i]) r2++;

		t4[0]=r1;
		t4[1]=r2;

		return t4;
	}

	public boolean verifieDouble (int [] t, int n) {
		int i;

		for (i=0; i<t.length; i++) if (n==t[i]) return false;

		return true;
	}

	public int [] genereAleatPossibilite (int a, int b, int n) {
		int [] t = new int [n];
		int i, c=0;

		for (i=0; i<t.length; i++) {
			while (!verifieDouble(t,n)) c = (int)(Math.random()*a+b);       

			t[i]=c;
		}

		return t;
	}

	public boolean verifieDouble2 (int [][] t1, int [] t2) {
		int i, j, a = 0;

		for (i=0; i<t1.length; i++) {
			a=0;

			for (j=0; j<t1[i].length; j++) if (t2[j]==t1[i][j]) a++;

			if (a>=n) return false;
		}

		return true;
	}

	public int [][] genereAleatPossibilite2 (int a, int b, int n1, int n2) {
		int i, j;

		int [][] t = new int [n1][n2];

		for (i=0; i<t.length; i++) {
			int [] c = new int [n2];
	
			while (!verifieDouble2(t,c)) for (j=0; j<n2; j++) c[j] = (int)(Math.random()*b+a);
  
			t[i]=c;
		}

		return t;
	}

	public int [][] genereAleatPossibilite3 (int a, int b, int n1, int n2) {
		int i, j;

		int [][] t = new int [n1][n2];

		for (i=0; i<t.length; i++) {
			int [] c = new int [n2];
	
			while (!verifieDouble2(t,c)) for (j=0; j<n2; j++) c[j] = (int)(Math.random()*b+a);

			for (j=0; j<c.length; j++) c[j] = c[j]-1;
  
			t[i]=c;
		}

		for (i=0; i<t.length; i++) t[i] = compteResultat(t[i]);

		return t;
	}

	public int [] compteResultat (int [] t1) {
		int r1=0, r2=0, i;
		int [] t2 = new int [2];

		for (i=0; i<t1.length; i++) {
			if (t1[i]==1) r1++;
			if (t1[i]==2) r2++;
		}

		t2[0]=r1;
		t2[1]=r2;

		return t2;
	}

	/**
		Calcule de a^b et renvoie le résultat.
	**/
	public int puissance (int a, int b){
		int c=1;

		for (int i=0; i<b; i++) c=c*a;

		return c;
	}
}

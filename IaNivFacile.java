

class IaNivFacile {

	public static int [][] t0;
	public static int [] t10;
	public static int [][] t11;

	public static int [] t1;
	public static int [] t2;
	public static int [] t4;

	public static int [] t5;

	public static int cmp;
	public static int cmp2;

	public static int a;
	public static int b;
	public static int n;

	public static int h;
	public static int bn;

	public static int g;



	public IaNivFacile (int n, int a, int b) {
		int i;

		if (n>b) bn=n; else bn=b;

		this.g = 1;
		this.h = 0;

		this.a = a;
		this.b = b;
		this.n = n;

		this.cmp = 0;
		this.cmp2 = 0;

		t0 = new int [2][bn];

		t1 = new int [bn];

		t5 = new int [n];

		for (i=0; i<b; i++) t0[0][i]=i+1;

	}

	public int compteOccurence (int [] t1, int j) {
		int i, cmp = 0;

		for (i=0; i<t1.length; i++) if (t1[i]==j) cmp++;

		return cmp;
	}

	public int [] recherche (int [] t1, int j) {
		int i, cmp = 0;

		int [] t2 = new int [t1.length];

		for (i=0; i<t1.length; i++) if (t1[i]==j) { t2[cmp]=i; cmp++; }

		int [] t3 = new int [cmp];

		for (i=0; i<cmp; i++) t3[i]=t2[i];

		return t3;
	}

	public int [] g1 () {
		int i, v=0;

		if (cmp>0) {
			for (i=0; i<n; i++) {
				if ((t2[i]==1) || (t2[i]==2)) {
					v = t4[i]-1;
					if (v<0) v=0;

					//System.out.println("t4["+i+"]-1 = "+(t4[i]-1));

					t0[1][v] = 1;

					//System.out.println("t0[1][v] = "+t0[1][v]);
				}
			}
		}

		for (i=0; i<n; i++) {		  
			if ((i+1+h)>b) t1[i]=b;   
			else t1[i]=i+1+h;	  
		}

		h=t1[n-1];

		if (h==b) {g=2; h=1; cmp=-1; } 		  

		for (i=0; i<n; i++) t5[i]=t1[i];  

		cmp++;

		return t5;
	}

	public int [] g2 () {
		int [] t14;
		int i, tmp=0, tmp2, v=0;

		if (cmp==0) {
			for (i=0; i<n; i++) {
				if ((t2[i]==1) || (t2[i]==2)) {
					v = t4[i]-1;
					if (v<0) v=0;

					//System.out.println("t4["+i+"]-1 = "+(t4[i]-1));

					t0[1][v] = 1;

					//System.out.println("t0[1][v] = "+t0[1][v]);
				}
			}

			t10 = new int [compteOccurence(t0[1],1)];
			t11 = new int [2][n];

			for (i=0; i<n; i++) t11[1][i] = -1;

			cmp2=0;
			
			for (i=0; i<b; i++) if (t0[1][i]==1) { t10 [tmp]=t0[0][i]; tmp++;}
		}
		
		for (i=0; i<n; i++) { 
			t1[i]=t10[cmp];
		}

		tmp2 = compteOccurence(t2,1);
		t14 = recherche(t2,1);

		//System.out.println("cmp2 = "+cmp2);
		//System.out.println("tmp2 = "+tmp2);

		for (i=0; i<tmp2; i++) {
			t11[0][cmp2+i] = t4[0];
			t11[1][cmp2+i] = t14[i];
		}

		if (cmp2+tmp2<n) cmp2 = cmp2+tmp2;

		cmp++;
							
		if (cmp==t10.length) {					
			g=3;
		}

		for (i=0; i<n; i++) t5[i]=t1[i];

		return t5;
	}

	public int [] g3 () {
		int i;

		for (i=0; i<n; i++) if (t11[1][i]!=-1) t1[t11[1][i]]=t11[0][i];
							   
		for (i=0; i<n; i++) t5[i]=t1[i];

		return t5;
	}

	/**
		Fonction qui appelle les autres fonctions de la classe.
	**/

	public int [] iAs1Facile (int [] t4, int [] t2) {
		int i;

		this.t2=t2;
		this.t4=t4;

		//for (i=0; i<n; i++) System.out.println("t4["+i+"] = "+t4[i]);
		//for (i=0; i<n; i++) System.out.println("t2["+i+"] = "+t2[i]);

		//for (i=0; i<b; i++) System.out.println("t0[1]["+i+"] = "+t0[1][i]);

		if (g>1 && cmp>0){ 
			//for (i=0; i<t11[0].length; i++) System.out.println("t11[0]["+i+"] = "+t11[0][i]);
			//for (i=0; i<t11[1].length; i++) System.out.println("t11[1]["+i+"] = "+t11[1][i]);
		}		

		/** Etape 1 **/

		if (g==1) {
			t5=g1 ();

			return t5;
		}

		/** Etape 2 **/

		if (g==2) {
			t5=g2 ();

			//for (i=0; i<b; i++) System.out.println("t4["+i+"] = "+t3[1][i]);

			return t5;
		}

		/** Etape 3 **/

		if (g==3){
			t5=g3 ();

			return t5;
		}

		for (i=0; i<n; i++) t5[i]=t1[i];

		return t5;
	}

}
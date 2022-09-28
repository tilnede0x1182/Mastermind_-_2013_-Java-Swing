// Ceci est la dernière version en classe (objet IA).

/**

   Voici le résumé de mon IA "semi-intelligente" (l'objectif) :  

   1) On tape tous les chiffres dans l'ordre (couleurs correspondantes)
   On enregistre les résultats dans un tableau : 2 bien placé, 3 mal placé, etc...
	
   2) On teste l'existence de chaque chiffre (couleur correspondante) en faisant une ligne de ce chiffre, puis on enregistre les chiffres existants dans la combinaison, et ceux qui n'y sont pas (séparément). On tient compte du nombre voulu (on s'arrête dès que le nombre de chiffres bons attendus (étape 2) a été atteint.

   3) On teste le placement des chiffres par rapport à ceux qui n'y sont pas (ordre), là aussi, en tenant compte des nombres existants dans le code (déterminés à l'étape 3) (on stocke le placement des chiffres dans un tableau).
   On s'arrête lorsqu'il reste 2 ou 3 chiffres à trouver pour passer directement à l'étape 5 (recherche de la combinaison gagnante) pour économiser deux ou trois tours en cas de chance.

   4) On entre les chiffres dans l'ordre établi à l'étape 4, puis on teste toutes 
   les possibilités restantes (2 s'il reste deux chiffres à placer, ou 3 s'il en reste trois) si la combinaison n'est pas bonne du premier coup.

   La bonne combinaison est trouvée !

   Signification des variables dans le code : 

   tabResultat : tableau des résultats envoyé par l'IA.
   tabPlacement : tableau contenant les valeurs des nombre de chiffres respectivement bien placés et mal placés au coup précédent.
   tabOccurence : tableau des occurences des chiffres dans le code à trouver (information trouvée au cours de l'étape 2 (Etape==2)).
   tabFinal : tableau que l'on renvoit au final (normalement c'est le tabResultat, mais il y une erreur à corriger : il doit contenir nbreChiffre cases et non valeurMax, donc, il faut corriger les fonctions qui utilisent tabResultat pour résoudre le problème).
   tabTrouves : tableau contenant la place des chiffres trouvé à l'étape 3 (Etape==3).

   i, j : variables pour les boucles.

   test : la variable testée à l'étape 3
   mauvaisChiffre : un chiffre qui n'est pas dans le code
   cmp : l'étape 3 s'arrete quand h==cmp
   cmp2 : la boucle de recherche d'un chiffre à tester dans le tableau tabOccurence s'arrete lorsque h==cmp2
   bn permet de jouer avec plus de 10 chiffres
   Etape = étape pour l'IA
   place : la place testée pour le chiffre test (étape 3)
   nn = compteur de tours pour la fon de l'étape 3.
   tmp : indique du chiffre test dans le tableau tabOccurence.

   valeurMin: valeur minimale d'un chiffre
   valeurMax : valeur maximale d'unn chiffre
   nbreChiffre : nombre de chiffres dans le code.



   Il reste deux problèmes à corriger.

   -Il faut que l'étape 2 prenne en compte les chiffres bien ou mal placés
   de l'étape 1 et s'arrête lorsqu'elle en trouve autant que dans l'intervalle de l'étape 1
   (s'il y en a 2 de 1 à 5, il faut que la fonction s'arrête quand elle en a trouvé 2 dans 
   cet intervalle et qu'elle passe directement à 6).

   -Il faut construire le tableau tabTrouves des placements des chiffres trouvés en fonction du nombre
   de chaque chiffre (s'il y a deux 2 dans la combinaison, il faut 2 cases pour stocker les emplacements des 2.
   Pour l'instant il y a une case par chiffre dans le tableau qui ne peut donc pas prendre en compte
   l'existance de doublons dans le code à résoudre.

**/
class IaNivMoyen {

    public static int [] tabResultat;
    public static int [] tabPlacement;
    public static int [][] tabOccurence;
    public static int [] tabFinal;	
    public static int [][] tabTrouves;

    public static int i;
    public static int j;
    int f;
    int d;
    public static int test;
    public static int mauvaisChiffre;
    public static int cmp;
    public static int cmp2;
    public static int cmp3;
    public static int cmp4;
    public static int bn;
    public static int Etape;
    public static int place;
    public static int nn;
    public static int tmp;

    public static int h;

    public static int valeurMin;
    public static int valeurMax;
    public static int nbreChiffre;
    public static int [][] tabfinal;
    /**

     **/

    public IaNivMoyen (int nbreChiffre, int valeurMin, int valeurMax) {
	if (valeurMax>nbreChiffre) bn=valeurMax; else bn=nbreChiffre;
		
	tabResultat = new int [bn];

	tabOccurence = new int [2][bn];
	tabFinal = new int [nbreChiffre];
	tabTrouves = new int [2][bn];

	f=0;
	d=0;

	i=0;
	j=0;
	test=0;
	mauvaisChiffre=0;
	cmp=0;
	cmp2=0;
	cmp3=0;
	cmp4=0;
	Etape=1;
	place=0;
	nn=0;
	tmp=-1;
	h=0;

	this.valeurMin=valeurMin;
	this.valeurMax=valeurMax;
	this.nbreChiffre=nbreChiffre;
    }
    /** 
     * cette fonction Etape1 essai la combinaison<br/>
     * Position1 =Couleur1<br/>
     * Position2 =Couleur2 <br/>
     * ...<br/>
     * Postion10 =Couleur10<br/>
     * On "perd" probablement deux coups si ca ne marche pas mais il se peut que l'on gagne à cette Etape1<br/>
     * Et tout le monde est content<br/>
     * @return un tableau qui pourrait probablement etre tabFinal si on gagne au Permier ou au 2e coup mais
     */
    public int [] Etape1 () {
	for (i=0; i<nbreChiffre; i++) {		  //Boucle for qui énumère les possibilités
	    if ((i+1+h)>valeurMax) tabResultat[i]=valeurMax;   //dans l'ordre et continue au coup suivant si nécssaire
	    else tabResultat[i]=i+1+h;	  //grâce à la valeur de h.
	}

	h=tabResultat[nbreChiffre-1];

	if (h==valeurMax) {Etape=2; h=1; cmp3=0;} 		  //On passe à l'étape 2 en attribuant à h la valeur 1 (on va le recycler à l'étape 2).

	for (i=0; i<nbreChiffre; i++) tabFinal[i]=tabResultat[i];  //Erreur à corriger (il ne faut pas que tabResultat.length>nbreChiffre normalement).

	return tabFinal;
    }

    /** 
     * cette fonction Etape2 essai trouver la bonne combinaison sans ordre<br/>
     * Position1 =Couleur1<br/>
     * Position2 =Couleur1 <br/>
     * ...<br/>
     * Postion10 =Couleur1<br/>
     * on récupere le nombre de fois que couleur1 apparaisse  puis on fait pareil pour couleur2,couleur3 ... couleur10<br/>
     * ainsi on saura exactement les couleurs exactes du code secret et leur occurences<br/>
     * ici on a utilisé un compteur cmp3 qui nous permet de ne pas perdre de coups des qu'on atteint le nbreChiffre du code on passe a Etape3<br/>
     *@return tabFinal le tableau final qui contient les coulers exactes du couleurs avec leur(s) occurence(s) respectives mais mal ordonné<br/>
     */

    public int [] Etape2 () { 


	for (i=0; i<nbreChiffre; i++) { 
	    tabResultat[i]=h;						
	}

	j=tabPlacement[0]+tabPlacement[1];							
		
	if (tabPlacement[0]>0 && h>=2) { tabOccurence[1][h-2]=tabPlacement[0]; cmp3+=tabPlacement[0]; }		
		
	for (i=0; i<tabOccurence[1].length; i++) System.out.println("tabOccurence["+1+"]["+i+"] = " + tabOccurence[1][i]);
		
	h++;
		
	if ((h>valeurMax+1) || cmp3>=nbreChiffre) {	
	    f=0; //variable de compatage des occurences pour l'étape 3.
	    d=0; //variable pour gérer l'avancement des tests dans l'étape 3.
						
	    Etape=3;
	    h=0;
	    cmp=0;
	    for (i=0; i<valeurMax; i++) if (tabOccurence[1][i]==tabPlacement[0]) cmp++; // On passe à l'étape 3 en préparant un compteur pour savoir combient il y a de chiffres trouvés dans tabOccurence (Si l'algo marche bien, il y en a toujours nbreChiffre).

	    int gg=0;

	    for (i=0; i<bn; i++) {
		if (tabOccurence[1][i]!=0) {
		    for (j=0; j<tabOccurence[1][i]; j++) {
			tabTrouves[0][gg] = tabOccurence[0][i];
			if (gg<tabTrouves[0].length) gg++;
		    }
		}
	    }

	    int [] ggg = new int [nbreChiffre];

	    j=0;

	    for (i=0; i<bn; i++) {
		if (tabTrouves[0][i]!=0) {
		    ggg[j] = tabTrouves[0][i];
		    if (j<nbreChiffre) j++;
		}
	    }

	    //trieDecroissant(ggg); //on trie ggg avant de mettre les valeurs dans tabTrouves.

	    for (i=0; i<bn; i++) System.out.println("tabTrouves[0]["+i+"] = "+tabTrouves[0][i]);

	    for (i=0; i<nbreChiffre; i++) System.out.println("ggg["+i+"] = "+ggg[i]);

	    tabTrouves = new int [2][nbreChiffre];

	    for (i=0; i<nbreChiffre; i++) {
		tabTrouves[0][i] = ggg[i]; //on copie le tableau dans tabTrouves[0].
		tabTrouves[1][i] = -1;
	    }

	    //for (i=0; i<tabOccurence.length; i++) for (j=0; j<tabOccurence[i].length; j++) System.out.println("tabOccurence["+i+"]["+j+"] = "+tabOccurence[i][j]);

	    //System.out.println();
	System.out.println("voici tabTrouves");
	afficher(tabTrouves);

	    //cmp4=0;

	    //Etape3 (); 
	}

	for (i=0; i<nbreChiffre; i++) tabFinal[i]=tabResultat[i];  //Erreur à corriger (il ne faut pas que tabResultat.length>nbreChiffre normalement).

	return tabFinal;
    }
    /** 
     * Cette fonction compte d'Occurences de chaque couleurs dans un tableau<br/>
     * @param [] t un tableau quelconque à une dimension <br/>
     * @param nbreChiffre le nombre de couleurs<br/>
     * @return valeurMin <br/>
     */
    public int compteOccurences (int [] t, int nbreChiffre) {
	int i, valeurMin=0;

	for (i=0; i<t.length; i++) if (t[i]==nbreChiffre) valeurMin++;
		
	return valeurMin;
    }

    /**
     * Cette fonction cherche la couleur qui est dans le code secret<br/>
     * @param [] t50 est un tableau <br/>
     * @param nbreChiffre le nombre de couleurs<br/>
     * @param occurences de chaque couleurs<br/>
     * @return -1<br/>
     */

    public static int chercheValeur (int [] t, int nbreChiffre, int occ) {
	int i, valeurMin=0;

	for (i=0; i<t.length; i++) {
	    if (t[i]==nbreChiffre) valeurMin++;
	    if (valeurMin>=occ) return i;
	}

	return -1;
    }

    public static boolean chercheOccurence (int [] t, int nbreChiffre) {
	int i;

	for (i=0; i<t.length; i++) if (t[i]==nbreChiffre) return true;

	return false;
    }

    public static boolean chercheOccurenceVal (int [] t, int nbreChiffre, int valeurMin) {
	int i;

	for (i=0; i<t.length; i++) {
	    if (t[i]==nbreChiffre) if (i==valeurMin) return true;
	}

	return false;
    }
	public static void afficher(int [][] tab){
	for (i=0; i<tab.length; i++) {
	    for (j=0; j<tab[i].length; j++) {
		System.out.print(tab[i][j]+"\t");
	    }
	    System.out.println();
	}
}

    public void verifieFinEtape3 () {
	int i;

	int hhh=0;

	for (i=0; i<tabTrouves[1].length; i++) if (tabTrouves[1][i]!=-1) hhh++;

	if (hhh>=nbreChiffre) Etape=4;
    }


    public static void trieDecroissant (int [] t) {
	int i, j, c=0;

	for (i=0; i<t.length; i++) for (j=i+1; j<t.length; j++) if (t[j]>t[i]) {
		    c=t[i];

		    t[i]=t[j];

		    t[j]=c;
		}
    }

    /** 
     * cette fonction Etape3 trouve le bon odre du tabfinal<br/>
     *cependant il y'a deux Methodes pour se faire
     * Methode 1: D'abord si on a une couleur qui a plusieurs occurences on commence par celle-ci<br/>
     * car Mathématiquement il est plus benefique pour nous de trouver les deux ou trois places d'une meme couleur qu(une seule d'unecouleur ********cette methode est moin intelligente mais moin chere pour nous en matiere de coups*********<br/>
     * si on trouve les 2 places par exemple et que par exemple on a 5 couleurs en tout bon il nous restera au plus 3 couleur pour 3 places qui ne depense au maximum que 6 coups, en tout on depense 16coups si on decide de faire ceci <br/>
     *  Methode 2: une autre possiblité c'est d'essayer de faire un tableau qui contient toutes les couleurs on regarde le nombre de couleurs bien placés<br/>
     * -**on echange 2 couleurs successifs (tabfinal[1][i] et tabfinal[1][i+1]) on regarde le nombre de bien placés si celui ci est:<br/>
     * > (superieur) au precedent
     * ----> on fait de meme pour tabfinal[1][i+1] et tabfinal[1][i+2] <br/>
     * sinon <=(inferieur) au precedent <br/>
     * ----> on retour au tableau precedent juste en echangeant tabfinal[1][i+1] et tabfinal[1][i]<br/>
     * puis on increment i, jusqu'à atteindre le bout du tableau, si on atteint pas la bonne combinaison on fait pareil tabfinal[1][i] et tabfinal[1][i+2], ainsi de suite on finira un jours par atteindre la bonne combinaison<br/>
     * ********cette methode est plus intelligente plus chere et pour nous en matiere de coups*********<br/>
     * @return [] t5 un tableau qui contient les differents couleurs
     */

    public int [] Etape3 () {
	//System.out.println("\ncmp = "+cmp);

	if (mauvaisChiffre==0) {	
	    for (i=0; i<valeurMax; i++){
		if (tabOccurence[1][i]==0) { mauvaisChiffre=tabOccurence[0][i]; i=valeurMax; } //Récupération d'un chiffre qui n'est pas dans la combinaison à trouver.
	    }
	}

	if (nn>0) {
	    place++;
	}

	if (nn>0){
	    if (tabPlacement[0]>=1) {
		tabTrouves[1][d+f]=place; //Enregistrement du résultat du coup précédant dans tabTrouves.

		f++;

		if (f>=compteOccurences(tabTrouves[0],tabTrouves[0][d])) {
		    d+=f;
		    f=0;
		    place=0;
		}		
	    }
	}
	//On incrémente la place si on a dépassé le premier coup.
	if (place>=nbreChiffre) {place=0; d+=f; }		  //Lorqu'on a testé toutes les places d'un nombre, on passe au nombre suivant grâce à h.						  
	//Il faudrait qu'on passe au coup suivant dès que l'on a trouvé la place d'un nombre 
	if (d>=tabTrouves[0].length) {Etape=4; Etape4();}	  //(s'il y a deux 2, par exemple, il faut attendre d'avoir trouvé les deux places de 2).

	cmp2=0;
	tmp=0;

	if (Etape==3) {								      //Si l'on a trouvé un nombre dans tabOccurence,
	    System.out.println("f = "+f+"\nd = "+d);
	    //h qui s'incrémente quand on finit de tester un nombre et cmp2 qui s'incrémente
	    test=tabTrouves[0][d];					//à chaque fois que l'on trouve un nombre dans le tableau et arrête la boucle quand cmp2==h.
			
	    for (i=0; i<place; i++) { 				//Attribution des chiffres à la combinaison envoyée (stokée dans tabResultat).
		tabResultat[i]=mauvaisChiffre;
	    }
			
	    tabResultat[place]=test;						//Valeur test à la place "place".
			
	    if (place<nbreChiffre-1){
		for (i=place+1; i<nbreChiffre; i++) { 
		    tabResultat[i]=mauvaisChiffre;
		}
	    }

	    nn++;				      //nn : numéro du tour (depuis le début de l'étape 3).
	}
		
	for (i=0; i<nbreChiffre; i++) tabFinal[i]=tabResultat[i];  //Erreur à corriger (il ne faut pas que tabResultat.length>nbreChiffre normalement).

	return tabFinal;
    }
    /** 
     * Fonction qui affiche les résulats en lisant les nombres et leur place dans le tableau tabTrouves.<br/>
     * @return [] t5 un tableau qui contient les differnts couleur
     */
 
    public int [] Etape4 () {
	for (i=0; i<tabTrouves[1].length; i++) tabTrouves[1][i]--;

	for (i=0; i<tabTrouves.length; i++) for (j=0; j<tabTrouves[i].length; j++) System.out.println("tabTrouves["+i+"]["+j+"] = "+tabTrouves[i][j]);

	for (i=0; i<nbreChiffre; i++) if (tabTrouves[1][i]>-1) tabResultat[tabTrouves[1][i]]=tabTrouves[0][i]; //On affiche les résulats en lisant
	//les nombres et leur place dans le tableau tabTrouves.
	for (i=0; i<nbreChiffre; i++) tabFinal[i]=tabResultat[i];

	return tabFinal;
    }

/**
       Fonction qui appelle les autres fonctions de la classe.<br/>
        * @param [] tabPlacement un tableau qui contient le nombre de couleurs bien placés et le nombre de couleurs mal placés<br/>
        * @return [] tabFinal un tableau qui contient les differents couleurs<br/>
	**/
    public int [] iAs1Moyen (int [] tabPlacement) {
	this.tabPlacement=tabPlacement;

	int [] tabFinal = new int [nbreChiffre];

	if ((Etape==2) && (h==1)) {
	    for (i=0; i<valeurMax; i++) tabOccurence[0][i]=i+1;
	}

	/** Etape 1 **/

	if (Etape==1) {
	    tabFinal=Etape1 ();

	    return tabFinal;
	}

	/** Etape 2 **/

	if (Etape==2) {
	    tabFinal=Etape2 ();
	    //for (i=0; i<b; i++) System.out.println("tabOccurence["+i+"] = "+tabOccurence[0][i]);
	    //for (i=0; i<valeurMax; i++) System.out.println("t4["+i+"] = "+tabOccurence[1][i]);

	    return tabFinal;
	}

	/** Etape 3 **/

	if (Etape==3) {
	    tabFinal=Etape3 ();
	    //for (i=0; i<valeurMax; i++) System.out.println("tabFinal["+i+"] = "+tabTrouves[0][i]);
	    //for (i=0; i<valeurMax; i++) System.out.println("tabTrouves["+i+"] = "+tabTrouves[1][i]);

	    return tabFinal;
	}

	/** Etape 4 **/

	if (Etape==4){
	    tabFinal=Etape4 ();

	    return tabFinal;
	}

	for (i=0; i<nbreChiffre; i++) tabFinal[i]=tabResultat[i];

	return tabFinal;
    }
}

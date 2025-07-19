import java.util.Date;

class UtilDate {
	public static void main (String [] args){
		//CompteSeconde ();

		Date date = new Date ();

		String d=date.toString();

		System.out.println(d);

		System.out.println("Nous sommes le "+convertJour(str(d,0,3))+" "+convertNombre(str(d,8,10))+" "+convertMois(str(d,4,7))+" "+convertNombre(str(d,24,29))+".");

		System.out.println(convertitDateNombres ());
	}

	public static String afficheTemps (int [] t) {
		String s = t[0]+" h "+t[1]+" min "+t[2]+" s";

		return s;
	}

	public static int [] convertitSecondes (int secondes) {
		int tmp = secondes % 3600; 

		int [] temps = new int [3];

		temps [0] = (secondes-tmp)/3600; 

		temps[2] = tmp%60; 

		temps[1] = (tmp-temps[2])/60;

		//temps[0] : heures

		//temps[1] : minutes

		//temps[2] : secondes

		return temps;
	}	

	public static int convertitEnSecondes (int [] t) {
		int s = t[0]*3600+t[1]*60+t[2];

		return s;
	}

	public static int convertitEnSecondesBis (int h, int min, int s) {
		int s1 = h*3600+min*60+s;

		return s1;
	}

	public static boolean compareDateMax (String d1, String d2) { /*11/11/1111 55 h 55 min 55 s*/
		int a1,j1,mo1,h1,mi1,s1;
		int a2,j2,mo2,h2,mi2,s2;

		j1 = convertNombre(str(d1,0,2));
		j2 = convertNombre(str(d2,0,2));

		mo1 = convertNombre(str(d1,3,5));
		mo2 = convertNombre(str(d2,3,5));

		a1 = convertNombre(str(d1,6,10));
		a2 = convertNombre(str(d2,6,10));

		h1 = convertNombre(str(d1,11,13));
		h2 = convertNombre(str(d2,11,13));

		mi1 = convertNombre(str(d1,16,18));
		mi2 = convertNombre(str(d2,16,18));

		s1 = convertNombre(str(d1,23,25));
		s2 = convertNombre(str(d2,23,25));

		int ss1 = h1*3600+mi1*60+s1;
		int ss2 = h2*3600+mi2*60+s2;

		int [][] t = new int [2][7];

		if (a1!=a2) return (a1>a2);
		else if (mo1!=mo2) return (mo1>mo2);
		else if (j1!=j2) return (j1>j2);
		else if (ss1!=ss2) return (ss1>ss2);
		else return true;
	}

	public static boolean compareDateMin (String d1, String d2) { /*11/11/1111 55 h 55 min 55 s*/
		int a1,j1,mo1,h1,mi1,s1;
		int a2,j2,mo2,h2,mi2,s2;

		j1 = convertNombre(str(d1,0,2));
		j2 = convertNombre(str(d2,0,2));

		mo1 = convertNombre(str(d1,3,5));
		mo2 = convertNombre(str(d2,3,5));

		a1 = convertNombre(str(d1,6,10));
		a2 = convertNombre(str(d2,6,10));

		h1 = convertNombre(str(d1,11,13));
		h2 = convertNombre(str(d2,11,13));

		mi1 = convertNombre(str(d1,16,18));
		mi2 = convertNombre(str(d2,16,18));

		s1 = convertNombre(str(d1,23,25));
		s2 = convertNombre(str(d2,23,25));

		int ss1 = h1*3600+mi1*60+s1;
		int ss2 = h2*3600+mi2*60+s2;

		int [][] t = new int [2][7];

		if (a1!=a2) return (a1<a2);
		else if (mo1!=mo2) return (mo1<mo2);
		else if (j1!=j2) return (j1<j2);
		else if (ss1!=ss2) return (ss1<ss2);
		else return true;
	}  

	public static String captureMot (String s, int a, char c) {
		int i, slength = s.length();

		String s0 = "";

		for (i=a; (s.charAt(i)!=c && i<slength); i++) s0=s0+s.charAt(i)+"";

		return s0;
	}

	public static int captureMotRef (String s, int a, char c) {
		int i, slength = s.length();

		for (i=a; (s.charAt(i)!=c && i<slength); i++) System.out.print("");

		return i;
	}

	public static int compteOccurence (String s, char c) {
		int i, slength = s.length(), q=0;

		for (i=0; i<slength; i++) if (s.charAt(i)==c) q++;

		return q;
	}

	public static String convertitDateNombres () {
		Date date = new Date ();
		String d=date.toString();

		String s="";
		if (convertNombre(str(d,8,10))<10) s=s+"0";
		s=s+convertNombre(str(d,8,10));

		s=s+"/";

		if (convertMoisInt(str(d,4,7))<10) s=s+"0";
		s=s+convertMoisInt(str(d,4,7));

		s=s+"/";

		s=s+convertNombre(str(d,24,29))+" ";

		if (date.getHours()<10) s=s+"0";
		s=s+date.getHours()+" h ";

		if (date.getMinutes()<10) s=s+"0";
		s=s+date.getMinutes()+" min ";

		if (date.getSeconds()<10) s=s+"0";
		s=s+date.getSeconds()+" s";

		return s;
	}

	public static void CompteSeconde(){
		Date date;
		String d="";
		int n=0, n1=0;

		while (true){
			date = new Date ();
			d = date.toString();

			n1=convertNombre(str(d,17,19));
			
			if (n1!=n){
				n=n1;
				System.out.println(n);
			}
		}
	}

	public static String convertJour(String s){
		String a="";

		if (s.equals("Mon")) a="Lundi";
		if (s.equals("Tue")) a="Mardi";
		if (s.equals("Wed")) a="Mercredi";
		if (s.equals("Thu")) a="Jeudi";
		if (s.equals("Fri")) a="Vendredi";
		if (s.equals("Sat")) a="Samedi";
		if (s.equals("Sun")) a="Dimanche";

		return a;
	}

	public static String convertMois(String s){
		String a="";

		if (s.equals("Jan")) a="Janvier";
		if (s.equals("Feb")) a="Fevrier";
		if (s.equals("Mar")) a="Mars";
		if (s.equals("Apr")) a="Avril";
		if (s.equals("May")) a="Mai";
		if (s.equals("Jun")) a="Juin";
		if (s.equals("Jul")) a="Juillet";
		if (s.equals("Aug")) a="Aout";
		if (s.equals("Sep")) a="Septembre";
		if (s.equals("Oct")) a="Octobre";
		if (s.equals("Nov")) a="Novembre";
		if (s.equals("Dec")) a="Decembre";

		return a;
	}

	public static int convertMoisInt(String s){
		int n=0;

		if (s.equals("Jan")) n=1;
		if (s.equals("Feb")) n=2;
		if (s.equals("Mar")) n=3;
		if (s.equals("Apr")) n=4;
		if (s.equals("May")) n=5;
		if (s.equals("Jun")) n=6;
		if (s.equals("Jul")) n=7;
		if (s.equals("Aug")) n=8;
		if (s.equals("Sep")) n=9;
		if (s.equals("Oct")) n=10;
		if (s.equals("Nov")) n=11;
		if (s.equals("Dec")) n=12;

		return n;
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

	public static String str(String s, int a, int b){
		String s1="";

		for (int i=a; i<b; i++) {
			if (i<s.length()) s1=s1+s.charAt(i);
		}

		return s1;
	}

}
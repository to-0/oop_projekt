package sklad;

import java.util.ArrayList;

import pouzivatelia.Skladnik;

public class Sklad {
	private static double papier;
	private static  double tvrdy_papier;
	private static double toner;
	private static double foto_papier;
	public static ArrayList<Skladnik> pozorovatel = new ArrayList<Skladnik>();
	public static double getPapier() {
		return papier;
	}
	public static void setPapier(double papier) {
		Sklad.papier = papier;
	}
	public static double getTvrdy_papier() {
		return tvrdy_papier;
	}
	public static void setTvrdy_papier(double tvrdy_papier) {
		Sklad.tvrdy_papier = tvrdy_papier;
	}
	public static double getToner() {
		return toner;
	}
	public static void setToner(double toner) {
		Sklad.toner = toner;
	}
	public static double getFoto_papier() {
		return foto_papier;
	}
	public static void setFoto_papier(double foto_papier) {
		Sklad.foto_papier = foto_papier;
	}
	public static boolean dostupnost(int pap, int tvr_pap, int ton, int fot) {
		if(papier - pap < 0 || tvrdy_papier - tvr_pap < 0 || toner - ton < 0 || foto_papier-fot <0) {
			return false;
		}
		return true;
	}
	public static void dopln_sklad(double pap, double tvr_pap, double ton, double f_pap){
		papier += pap;
		tvrdy_papier += tvr_pap;
		toner += ton;
		foto_papier += f_pap;
	}
}

package sklad;

import java.io.Serializable;
import java.util.ArrayList;

import databaza.Databaza;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Skladnik;

/**
 * Singleton trieda, obsahuje množstvo materálov v sklade.
 */
public class Sklad implements Serializable {
	public double papier;
	public static Sklad instance=null;
	public static Sklad getInstance(){
		if(instance==null)
			instance = new Sklad();
			return instance;
	}
	private Sklad(){
		this.pozorovatelia = new ArrayList<Skladnik>();
		this.papier = 1500;
		this.toner = 2000;
		this.tvrdy_papier = 600;
		this.foto_papier = 1500;
	}
	public  double tvrdy_papier;
	public  double toner;
	public  double foto_papier;
	public  ArrayList<Skladnik> pozorovatelia;

	/**
	 * Upozorní všetkých skladníkov.
	 */
	public void upozorni_pozorovatelov(){
		for(Skladnik s: this.pozorovatelia){
			s.upozorni();
		}
	}
	public void od_papier(double mnozstvo){
		this.papier -=mnozstvo;
	}
	public void od_foto_papier(double mnozstvo){
		this.foto_papier -=mnozstvo;

	}
	public void od_toner(double mnozstvo){
		this.toner -= mnozstvo;
	}
	public void od_tvr_papier(double mnozstvo){
		this.tvrdy_papier -=mnozstvo;
	}

	/**
	 * Vráti stav skladu
	 * @return String kde je celý stav skladu
	 */
	public String vrat_stav(){
		return "Papier "+ this.papier +"\n Foto papier "+this.foto_papier +"\n Toner "+this.toner +"\n Tvrdy papier "+this.tvrdy_papier;
	}
	public static void setInstance(Sklad sklad){
		instance = sklad;
	}

	/**
	 * Pridá pozorovateľa skladu.
	 * @param s Pozorovatel typu Skladník
	 */
	public void pridajPozorovatela(Skladnik s){
		this.pozorovatelia.add(s);
	}

	/**
	 * Nastaví pozorovateľov, všetkých skladníkov.
	 */
	public void nastav_pozorovatelov(){
		for (Pouzivatel p: Databaza.getUsers()){
			if(p instanceof Skladnik){
				this.pridajPozorovatela((Skladnik)p);
			}
		}
	}
	public int pocet_pozorovatelov(){
		return this.pozorovatelia.size();
	}

}

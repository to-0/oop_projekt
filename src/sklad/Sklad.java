package sklad;

import java.io.Serializable;
import java.util.ArrayList;

import databaza.Databaza;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Skladnik;

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
	//upozorni skladnikov ze treba doplnit sklad
	public void upozorni_pozorovatelov(){
		for(Skladnik s: this.pozorovatelia){
			s.upozorni();
		}
	}
	public String vrat_stav(){
		return "Papier "+ this.papier +"\n Foto papier "+this.foto_papier +"\n Toner "+this.toner +"\n Tvrdy papier "+this.tvrdy_papier;
	}
	public static void setInstance(Sklad sklad){
		instance = sklad;
	}
	public void pridajPozorovatela(Skladnik s){
		this.pozorovatelia.add(s);
	}
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

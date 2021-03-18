package model;

import java.io.Serializable;
import java.util.ArrayList;

import pouzivatelia.Klient;
import tovar.Tovar;

public class Objednavka implements Serializable{
	private int id;
	public ArrayList<Tovar> tovar = new ArrayList<Tovar>();
	int suma;
	boolean vybavena;
	Klient klient;
	public Objednavka(int id, ArrayList<Tovar> t, int suma,Klient k) {
		this.id = id;
		this.tovar = t;
		this.suma = suma;
		this.vybavena = false;
		this.klient = k;
	}
	public int getId() {
		return id;
	}
	public int get_Klient_id() {
		return klient.getId();
	}
}

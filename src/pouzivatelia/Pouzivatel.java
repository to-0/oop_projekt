package pouzivatelia;

import java.io.Serializable;
import java.util.ArrayList;

import model.Login;
import model.Objednavka;
import model.Spravy;

public abstract class Pouzivatel implements Serializable{
	private int id;
	String meno;
	Login login;
	String telefon;
	String email;
	protected Spravy spravy;
	ArrayList<Objednavka> objednavky = new ArrayList<Objednavka>();
	int pocet_obj=0;
	public int get_pocetobj() {
		return this.pocet_obj;
	}
	public void set_pocetobj(int pocet) {
		this.pocet_obj = pocet;
	}
	public Pouzivatel(int id, String meno, Login login,String telefon, String email) {
		this.meno = meno;
		this.telefon = telefon;
		this.email = email;
		this.setId(id);
		this.login = login;
		this.spravy = new Spravy();
		
	}
	public Pouzivatel(String meno,String telefon, String email) {
		this.meno = meno;
		this.telefon = telefon;
		this.email = email;
		this.setId(1);
		this.login = new Login("admin","admin");
		
	}
	public Pouzivatel() {
		
	}
	public Login getLogin() {
		return this.login;
	}
	public void pridaj_objednavku(Objednavka o) {
		objednavky.add(o);
		pocet_obj++;
	}
	public boolean validuj(String nick, String pass){
		return login.valid_login(nick, pass);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMeno(){
		return this.meno;
	}
	public ArrayList<Objednavka> getObjednavky(){
		return this.objednavky;
	}
	public String toString(){
		return this.meno  +"\n Email:"+this.email + "\nTelefon:"+this.telefon;
	}
	public Spravy getSpravy(){
		return this.spravy;
	}
}

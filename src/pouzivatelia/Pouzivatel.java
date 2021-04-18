package pouzivatelia;

import java.io.Serializable;
import java.util.ArrayList;

import model.Login;
import model.Objednavka;
import model.Spravy;

/**
 * Abstraktná trieda pre všetkých používateľov. Definuje zkladné metódy a atribúty.
 */
public abstract class Pouzivatel implements Serializable{
	/**
	 * Id pouzivatela
	 */
	private int id;
	protected String meno;
	protected Login login;
	protected String telefon;
	protected String email;
	protected Spravy spravy;
	ArrayList<Objednavka> objednavky = new ArrayList<Objednavka>();
	protected int pocet_obj=0;

	/**
	 * Metóda na zistenie počtu objednávok
	 * @return počet objednávok
	 */
	public int get_pocetobj() {
		return this.pocet_obj;
	}
	public void inc_pocet_obj() {
		this.pocet_obj++;
	}
	public void dec_pocet_obj(){
		this.pocet_obj--; //pocet aktivtnych objednavok
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

	/**
	 * Metóda na pridanie objednávky.
	 * @param o objednávka ktorú chcem pridať
	 */
	public void pridaj_objednavku(Objednavka o) { //priradim objednavku
		objednavky.add(o);
		pocet_obj++;
	}

	/**
	 * validácia prihlasovacích ádajov, volá metódu triedy login
	 * @param nick meno
	 * @param pass heslo
	 * @return true alebo false podľa toho či prihlasovacie údaje sedia alebo nie
	 */
	public boolean validuj(String nick, String pass){//skontrolujem ci argumenty su rovnake ako meno a heslo pouzivatela
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

	/**
	 * Základná toString metóda
	 * @return vracia string v tvare meno, email, teleofon
	 */
	public String toString(){
		return this.meno  +"\n Email:"+this.email + "\nTelefon:"+this.telefon;
	} //na vypis do gui

	/**
	 * Getter na správy používateľa
	 * @return
	 */
	public Spravy getSpravy(){
		return this.spravy;
	}
}

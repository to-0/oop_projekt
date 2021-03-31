package pouzivatelia;

import model.Adresa;
import model.Login;

public class Klient extends Pouzivatel{
	private Adresa adresa;
	public Klient(int id, String meno, Login login, String telefon, String email,String ulica, String obec, String cislo) {
		super(id, meno, login, telefon, email);
		this.setAdresa(new Adresa(ulica,obec,cislo));
	}
	public Adresa getAdresa() {
		return adresa;
	}
	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}
	public String toString(){ //prekonavanie polymorfizmus
		String s = "";
		s+= "Meno klienta:" +this.meno + "\nTelefon: " + this.telefon + "\n"+
		"Email: "+this.email + "\nAdresa:"+ this.adresa.toString();
		return s;
	}
	
}

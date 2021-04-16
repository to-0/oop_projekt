package pouzivatelia;

import model.Adresa;
import model.Login;

/**
 * Podtrieda používateľa, klient si vytvára objednávky.
 */
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

	/**
	 * Prekonaná metóda toString
	 * @return String v tvare Meno klienta: meno Telefon: telefon Email: email Adresa: adresa
	 */
	public String toString(){ //prekonavanie polymorfizmus
		String s = "";
		s+= "Meno klienta:" +this.meno + "\nTelefon: " + this.telefon + "\n"+
		"Email: "+this.email + "\nAdresa:"+ this.adresa.toString();
		return s;
	}
	
}

package pouzivatelia;

import model.Login;
import model.Objednavka;
import model.Spravy;

/**
 * Trieda má na starosti dopĺňanie materiálov a odosielanie, objednávok.
 */
public class Skladnik extends Zamestnanec {
	public Skladnik(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.spravy = new Spravy();
	}
	public void upozorni(){
		System.out.println("Treba doplnit sklad...");
		this.spravy.pridaj_spravu("Treba doplnit sklad");
	}

	/**
	 * Doplní množstvo materiálu (podľa parametra t)
	 * @param t ktorý material idem dopĺňať
	 * @param mnozstvo ake množstvo
	 */
	public void dopln_sklad(int t,double mnozstvo){
		switch(t){ //podla t-cka viem co idem doplnat
			case 1:
				this.sklad.papier  += mnozstvo;
				System.out.println(sklad.papier);
				break;
			case 2:
				this.sklad.foto_papier +=  mnozstvo;
				break;
			case 3:
				this.sklad.toner += mnozstvo;
				break;
			case 4:
				this.sklad.tvrdy_papier += mnozstvo;
				break;
		}
	}
	public void odosli_objednavku(Objednavka o){ //vybavi objednavku
			o.vybav();
	}

}

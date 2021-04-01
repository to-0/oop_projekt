package pouzivatelia;

import model.Login;
import model.Objednavka;
import model.Spravy;

public class Skladnik extends Zamestnanec {
	public Skladnik(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.spravy = new Spravy();
	}
	public void upozorni(){
		System.out.println("Treba doplnit sklad...");
		this.spravy.pridaj_spravu("Treba doplnit sklad");
	}
	public String sklad_stav(){
		return sklad.vrat_stav();
	}
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
		}
	}
	public void odosli_objednavku(Objednavka o){
			o.vybav();
	}

}

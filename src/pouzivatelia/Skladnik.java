package pouzivatelia;

import model.Login;
import model.PristupSklad;
import model.Spravy;
import sklad.Sklad;

import java.util.ArrayList;

public class Skladnik extends Pouzivatel implements PristupSklad {
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
				sklad.papier  += mnozstvo;
				break;
			case 2:
				sklad.foto_papier +=  mnozstvo;
				break;
			case 3:
				sklad.toner += mnozstvo;
				break;
		}
	}

}

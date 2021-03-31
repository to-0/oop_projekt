package pouzivatelia;

import model.Login;
import model.PristupSklad;
import model.Spravy;
import sklad.Sklad;

import java.util.ArrayList;

public class Skladnik extends Pouzivatel implements PristupSklad {
	private Sklad sklad;
	public Skladnik(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.spravy = new Spravy();
		this.sklad = Sklad.getInstance();

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

}

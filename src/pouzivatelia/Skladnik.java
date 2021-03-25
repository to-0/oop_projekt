package pouzivatelia;

import model.Login;
import model.PristupSklad;
import sklad.Sklad;

public class Skladnik extends Pouzivatel implements PristupSklad {

	public Skladnik(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		// TODO Auto-generated constructor stub
	}
	public void upozorni(){
		System.out.println("Treba doplnit sklad...");
	}
	public String sklad_stav(){
		return Sklad.vrat_stav();
	}

}

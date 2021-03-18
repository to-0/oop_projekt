package pouzivatelia;

import model.Login;

public class Manazer extends Pouzivatel{

	public Manazer(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		// TODO Auto-generated constructor stub
	}
	public Manazer(String meno, String telefon, String email) {
		super(meno,telefon,email);
	}

}

package pouzivatelia;

import model.Login;
import model.Objednavka;
import tovar.Tovar;

public class PracovnikZosit extends Pouzivatel implements Vyroba {

	@Override
	public void vyrob_tovar(Tovar t,Objednavka o) {
		// TODO Auto-generated method stub
		
	}
	public PracovnikZosit(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String sklad_stav() {
		// TODO Auto-generated method stub
		return null;
	}
}

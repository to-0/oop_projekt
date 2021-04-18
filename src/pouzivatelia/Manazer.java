package pouzivatelia;

import databaza.Databaza;
import model.Login;

import java.util.ArrayList;

public class Manazer extends Zamestnanec{
	private ArrayList<Zamestnanec> zamestnanci;
	public ArrayList<Zamestnanec> getZamestnanci(){
		return this.zamestnanci;
	}
	public void nastav_zamestnancov(){
		this.zamestnanci = new ArrayList<Zamestnanec>();
		for(Pouzivatel p: Databaza.getUsers())
			if(p instanceof Zamestnanec && p!=this)
				this.zamestnanci.add((Zamestnanec)p);
	}
	public Manazer(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		// TODO Auto-generated constructor stub
	}
	public Manazer(String meno, String telefon, String email) {
		super(meno,telefon,email);
	}


}

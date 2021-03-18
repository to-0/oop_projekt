package pouzivatelia;

import model.Login;
import model.Objednavka;
import sklad.Sklad;
import tovar.Fotka;
import tovar.Tovar;

public class PracovnikFotky extends Pouzivatel implements Vyroba{

	public PracovnikFotky(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		// TODO Auto-generated constructor stub
	}
	@Override
	public String sklad_stav() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void vyrob_tovar(Tovar t,Objednavka o) {
		for(Tovar tovar: o.tovar) {
			if(tovar instanceof Fotka) {
				System.out.println("Tlacim fotky...");
				double spotreba = tovar.vypocitaj_spotrebu_pap();
				double toner = ((Fotka) tovar).vypocitaj_spotrebu_tonera();
				double pap = Sklad.getFoto_papier();
				tovar.vybav();
			}
		}
		
	}

}

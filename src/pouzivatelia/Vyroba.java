package pouzivatelia;

import model.Objednavka;
import model.PristupSklad;
import tovar.Tovar;

public interface Vyroba extends PristupSklad{
	public boolean vyrob_tovar(Objednavka o);
}

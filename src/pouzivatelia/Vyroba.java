package pouzivatelia;

import model.Objednavka;
import model.PristupSklad;
import tovar.Tovar;

public interface Vyroba extends PristupSklad{
	public void vyrob_tovar(Tovar t,Objednavka o);
}

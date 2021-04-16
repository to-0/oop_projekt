package pouzivatelia;

import model.Objednavka;

/**
 * Interface, definuje metódu výroby.
 */
public interface Vyroba{
	public boolean vyrob_tovar(Objednavka o);
}

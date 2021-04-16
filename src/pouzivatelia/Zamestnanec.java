package pouzivatelia;

import model.Login;
import model.Spravy;
import sklad.Sklad;

/**
 * Abstraktná trieda, pridáva atribút Sklad pre prístup do skladu.
 */
public abstract class Zamestnanec extends Pouzivatel{
    protected Sklad sklad;
    public Zamestnanec(int id, String meno, Login login, String telefon, String email){
        super(id,meno,login,telefon,email);
        this.sklad = Sklad.getInstance();
    }
    public Zamestnanec(String meno,String telefon, String email) {
       super(meno,telefon,email);
        this.sklad = Sklad.getInstance();
    }
}

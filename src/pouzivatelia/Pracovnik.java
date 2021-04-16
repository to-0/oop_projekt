package pouzivatelia;

import model.*;
import sklad.Sklad;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;
import tovar.Zosit;

/**
 * Podtyp zamestnanca, pracovník ktorý vyrába tovar.
 */
public class Pracovnik extends Zamestnanec{
    /**
     * Jeho vlastný stroj
     */
    protected Stroj stroj;
    protected TypStroja  typPracovnika;

    public Pracovnik(int id, String meno, Login login, String telefon, String email){
        super(id,meno,login,telefon,email);
       this.sklad = Sklad.getInstance();
    }

    /**
     * Metóda ktorá skontroluje stav tovaru, vráti true ak najde aspon jeden nevyrobeny tovar v objednávke.
     * @param o objednavka
     * @return boolean
     */
    public boolean skontroluj_stav_tovaru(Objednavka o){ //vrati true ak najde aspon jeden nevyrobeny co ma na starosti
        for(Tovar t: o.tovar){
            if(t instanceof Fotka && typPracovnika==TypStroja.FOTKA){
                if(t.getStav()==false) return true;
            }
            if(t instanceof Zosit && typPracovnika==TypStroja.ZOSIT){
                if(t.getStav()==false) return true;
            }
            if(t instanceof Obalka && typPracovnika==TypStroja.OBALKA){
                if(t.getStav()==false) return true;
            }
        }
        return false;
    }

}

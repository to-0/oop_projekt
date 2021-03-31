package pouzivatelia;

import model.*;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;
import tovar.Zosit;

//TATO CLASSA IBA DEFINUJE NOVY ATRIBUT STROJ
public class Pracovnik extends Pouzivatel{
    protected Stroj stroj;
    protected TypStroja  typPracovnika;

    public Pracovnik(int id, String meno, Login login, String telefon, String email){
        super(id,meno,login,telefon,email);
       this.spravy = new Spravy();
    }
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

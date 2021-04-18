package pouzivatelia;

import javafx.scene.control.TextArea;
import model.*;
import sklad.Sklad;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;

/**
 * Pracovník, ktorý vyrába obálky
 */
public class PracovnikObalka extends Pracovnik implements Vyroba{
	/**
	 * Metóda vyrob tovar, vyberá iba obálky z celej objednávky, kontroluje dostupnosť materiálov a spúšta stroj.
	 * @param o objednávka, ktorej tovar idem vyrábať
	 * @return true ak sa podarí vyrobiť false ak je nedostatok materálov.
	 */
	@Override
	public boolean vyrob_tovar(Objednavka o) {
		Obalka f = null;
		if(!this.skontroluj_stav_tovaru(o)){ //pracovnik uz vyrobil vsetko co mohol
			System.out.println("Tovar uz bol vyrobeny");
			return false;
		}
		for(Tovar t: o.getTovar()){
			if(!(t instanceof Obalka)) //preskakujem tovar ktory nema na starosti, v tomto pripade obalku
				continue;
			f = (Obalka) t;
			//todo zmenit na tvrdy papier
			double spotreba_papiera = f.vypocitaj_spotrebu_pap();
			if(sklad.tvrdy_papier - spotreba_papiera <= 0){
				sklad.upozorni_pozorovatelov();
				this.getSpravy().pridaj_spravu("Nedostatok materialov");
				return false;
			}
			this.stroj.spusti_proces(t,o);
		}
		return true;
	}
	public PracovnikObalka(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.stroj = new Stroj(TypStroja.OBALKA,"Stroj na obalky",this);
		this.typPracovnika = TypStroja.OBALKA;
	}

}

package pouzivatelia;

import model.*;
import tovar.Fotka;
import tovar.Tovar;

/**
 * Pracovnik, torý ma na starosti výrobu fotiek. Implementuje rozhranie
 * @see ObjSpracovanie
 */
public class PracovnikFotky extends Pracovnik implements ObjSpracovanie {
	public PracovnikFotky(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.stroj = new Stroj(TypStroja.FOTKA,"Tlaciaren Fotiek",this);
		this.typPracovnika = TypStroja.FOTKA;
	}

	/**
	 * Metóda vyrob tovar, vyberá iba fotky z celej objednávky, kontroluje dostupnosť materiálov a spúšta stroj.
	 * @param o objednávka, ktorej tovar idem vyrábať
	 * @return true ak sa podarí vyrobiť false ak je nedostatok materálov.
	 */
	@Override
	public boolean spracuj_obj(Objednavka o) {
		Fotka f = null;
		for(Tovar t: o.getTovar()){ //prechadzam vsetok tovar
			if(!(t instanceof Fotka)) //preskakujem tovar ktory nema na starosti
				continue;
			f = (Fotka) t;
			//vypocitam spotrebu tonera a papiera pre fotky
			double spotreba_tonera = f.vypocitaj_spotrebu_tonera();
			double spotreba_papiera = f.vypocitaj_spotrebu_pap();
			if(sklad.foto_papier - spotreba_papiera <= 0 || sklad.toner - spotreba_tonera<=0){ //ak by to nestacilo nezacinam s vyrobou lebo v sklade nie je dostatok materialu
				sklad.upozorni_pozorovatelov();
				//toto zmenit asi aby sa to vypisalo do v gui niekde, mozno cez return boolean a tam kontrolujem
				System.out.println("NEDOSTATOK MATERIALOV");
				this.getSpravy().pridaj_spravu("Nedostatok materialov");
				return false;
			}
			//TODO odcitanie materialov zo skladu
			this.stroj.spusti_proces(t,o);
			//ProcesVyroby_old proces = new ProcesVyroby_old(t,o);
			//proces.start();
		}
		return true;
	}

}

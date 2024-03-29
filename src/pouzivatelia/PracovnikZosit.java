package pouzivatelia;

import model.*;
import tovar.Tovar;
import tovar.Zosit;

/**
 * Pracovník ktorý má na starosti výrobu zošitov.
 */
public class PracovnikZosit extends Pracovnik implements ObjSpracovanie {
	//tu skontrolujem ci mam dostatok surovin na ZOSIT(y) a potom to dam vyrobit mojmu stroju
	/**
	 * Metóda vyrob tovar, vyberá iba zosity z celej objednávky, kontroluje dostupnosť materiálov a spúšta stroj.
	 * @param o objednávka, ktorej tovar idem vyrábať
	 * @return true ak sa podarí vyrobiť false ak je nedostatok materálov.
	 */
	@Override
	public boolean spracuj_obj(Objednavka o){ //text area na vypisovanie
		Zosit z = null;
		for(Tovar t: o.getTovar()){
			if(!(t instanceof Zosit)) //preskakujem tovar ktory nema na starosti
				continue;
			z = (Zosit) t;
			double spotreba_papiera = z.vypocitaj_spotrebu_pap();
			double spotreba_tonera =0;
			if(z.getTyp()!=3)
				spotreba_tonera = z.vypocitaj_spotrebu_tonera(z.getTyp(),z.getPocet_stran());
			System.out.println("spotreba tonera: "+spotreba_tonera + "Spotreba papiera "+spotreba_papiera);
			if(sklad.papier - spotreba_papiera < 0 || sklad.toner - spotreba_tonera <0){
				sklad.upozorni_pozorovatelov();
				//toto zmenit asi aby sa to vypisalo do v gui niekde, mozno cez return boolean a tam kontrolujem
				this.getSpravy().pridaj_spravu("Nedostatok materialov");
				return false;
			}
			this.sklad.od_toner(spotreba_tonera);
			this.sklad.od_papier(spotreba_papiera);
			this.stroj.spusti_proces(t,o);
		}
		return true;
	}
	public PracovnikZosit(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.stroj = new Stroj(TypStroja.ZOSIT,"Stroj Zosit",this);
		this.typPracovnika = TypStroja.ZOSIT;
	}

}

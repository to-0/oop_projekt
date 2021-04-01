package pouzivatelia;

import javafx.scene.control.TextArea;
import model.*;
import sklad.Sklad;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;

public class PracovnikObalka extends Pracovnik implements Vyroba{

	@Override
	public boolean vyrob_tovar(Objednavka o) {
		Obalka f = null;
		if(!this.skontroluj_stav_tovaru(o)){ //pracovnik uz vyrobil vsetko co mohol
			System.out.println("Tovar uz bol vyrobeny");
			return false;
		}
		for(Tovar t: o.tovar){
			if(!(t instanceof Fotka)) //preskakujem tovar ktory nema na starosti
				continue;
			f = (Obalka) t;
			double spotreba_papiera = f.vypocitaj_spotrebu_pap();
			if(sklad.papier - spotreba_papiera <= 0){
				sklad.upozorni_pozorovatelov();
				//toto zmenit asi aby sa to vypisalo do v gui niekde, mozno cez return boolean a tam kontrolujem
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

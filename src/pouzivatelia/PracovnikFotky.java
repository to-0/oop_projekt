package pouzivatelia;

import model.*;
import sklad.Sklad;
import tovar.Fotka;
import tovar.Tovar;

import java.awt.*;
import javafx.scene.control.TextArea;

public class PracovnikFotky extends Pracovnik implements Vyroba{
	public PracovnikFotky(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.stroj = new Stroj(TypStroja.FOTKA,"Tlaciaren Fotiek",this);
		this.typPracovnika = TypStroja.FOTKA;
	}

	@Override
	public boolean vyrob_tovar(Objednavka o) {
		Fotka f = null;
		for(Tovar t: o.tovar){
			if(!(t instanceof Fotka)) //preskakujem tovar ktory nema na starosti
				continue;
			f = (Fotka) t;
			double spotreba_tonera = f.vypocitaj_spotrebu_tonera();
			double spotreba_papiera = f.vypocitaj_spotrebu_pap();
			if(Sklad.foto_papier - spotreba_papiera <= 0 || Sklad.toner - spotreba_tonera<=0){
				Sklad.upozorni_pozorovatelov();
				//toto zmenit asi aby sa to vypisalo do v gui niekde, mozno cez return boolean a tam kontrolujem
				System.out.println("NEDOSTATOK MATERIALOV");
				return false;
			}
			this.stroj.spusti_proces(t,o);
			//ProcesVyroby proces = new ProcesVyroby(t,o);
			//proces.start();
		}
		return true;
	}

}

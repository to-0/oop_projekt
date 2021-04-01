package pouzivatelia;

import javafx.scene.control.TextArea;
import model.*;
import sklad.Sklad;
import tovar.Fotka;
import tovar.Tovar;
import tovar.Zosit;

import javax.xml.soap.Text;

public class PracovnikZosit extends Pracovnik implements Vyroba {
	//tu skontrolujem ci mam dostatok surovin na ZOSIT(y) a potom to dam vyrobit mojmu stroju
	@Override
	public boolean vyrob_tovar(Objednavka o){ //text area na vypisovanie
		Zosit z = null;
		for(Tovar t: o.tovar){
			if(!(t instanceof Zosit)) //preskakujem tovar ktory nema na starosti
				continue;
			z = (Zosit) t;
			double spotreba_papiera = z.vypocitaj_spotrebu_pap();
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
	public PracovnikZosit(int id, String meno, Login login, String telefon, String email) {
		super(id, meno, login, telefon, email);
		this.stroj = new Stroj(TypStroja.ZOSIT,"Stroj Zosit",this);
		this.typPracovnika = TypStroja.ZOSIT;
	}

}

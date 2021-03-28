package pouzivatelia;

import javafx.scene.control.TextArea;
import model.Objednavka;
import model.PristupSklad;
import tovar.Tovar;

import java.util.ArrayList;

public interface Vyroba extends PristupSklad{
	public boolean vyrob_tovar(Objednavka o);
}

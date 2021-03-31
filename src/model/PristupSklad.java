package model;

import sklad.Sklad;

public interface PristupSklad {
	default String sklad_stav(){
		return Sklad.getInstance().vrat_stav();
	}
}

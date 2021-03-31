package model;

import sklad.Sklad;

public interface PristupSklad {
	Sklad sklad = Sklad.getInstance();
	default String sklad_stav(){
		return sklad.vrat_stav();
	}
}

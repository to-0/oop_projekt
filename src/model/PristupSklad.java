package model;

import sklad.Sklad;

public interface PristupSklad {
	default String sklad_stav(){
		return Sklad.vrat_stav();
	}
}

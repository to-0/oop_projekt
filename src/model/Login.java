package model;

import java.io.Serializable;
/*
Trieda slúži na uloženie prihlasovacích údajov používateľa a validáciu loginu.
 */
public class Login implements Serializable{
	/**
	 * Prihlasovacie meno
	 */
	private String meno;
	/**
	 * Heslo
	 */
	private String heslo;
	public Login(String meno, String heslo) {
		this.setMeno(meno);
		this.heslo = heslo;
	}
	public String getHeslo() {
		return heslo;
	}
	public void setHeslo(String heslo) {
		this.heslo = heslo;
	}
	public String getMeno() {
		return meno;
	}
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * Skontroluje či je login validný, ak áno vráti true.
	 * @param meno meno
	 * @param heslo heslo
	 * @return
	 */
	public boolean valid_login(String meno, String heslo) { //kontrola ci sedia, pri prihlasovani
		if(this.meno.equals(meno)&& this.heslo.equals(heslo)) {
			return true;
		}
		return false;
	}
}

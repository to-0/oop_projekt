package model;

import java.io.Serializable;
//na ulozenie prihlasovacich udajov pre pouzivatela
public class Login implements Serializable{
	private String meno;
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
	public boolean valid_login(String meno, String heslo) { //kontrola ci sedia, pri prihlasovani
		if(this.meno.equals(meno)&& this.heslo.equals(heslo)) {
			return true;
		}
		return false;
	}
}

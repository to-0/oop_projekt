package tovar;

public class Zosit extends Tovar{
	public Zosit(int mnozstvo, int typ) {
		super(mnozstvo,typ);
	}
	private int velkost;
	private String typ;
	private int pocet_stran;
	public int getVelkost() {
		return velkost;
	}
	public void setVelkost(int velkost) {
		this.velkost = velkost;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public int getPocet_stran() {
		return pocet_stran;
	}
	public void setPocet_stran(int pocet_stran) {
		this.pocet_stran = pocet_stran;
	}
	@Override
	public double vypocitaj_spotrebu_pap() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String toString(){
		return "Zosit" + "typ: "+this.typ+" mnozstvo:"+this.mnozstvo;
	}
}

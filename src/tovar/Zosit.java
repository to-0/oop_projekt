package tovar;

public class Zosit extends Tovar{
	public Zosit(int mnozstvo, int typ) {
		super(mnozstvo,typ);

	}
	private int velkost;
	private int pocet_stran;
	public int getVelkost() {
		return velkost;
	}
	public void setVelkost(int velkost) {
		this.velkost = velkost;
	}
	public String getTyp() {
		return String.valueOf(this.typ);
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
		String typ_zosita="";
		switch(this.typ){
			case 1:
				typ_zosita  = "linajkovy";
				break;
			case 2:
				typ_zosita = "stvorcekovy";
				break;
			case 3:
				typ_zosita = "cisty";
				break;
		}
		return "Zosit typ: "+typ_zosita+" mnozstvo:"+this.mnozstvo;
	}
}

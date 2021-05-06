package tovar;

/**
 * Podtyp triedy Tovar, implementuje
 * @see TonerSpotreba
 *
 */
public class Zosit extends Tovar implements TonerSpotreba{
	public Zosit(int mnozstvo, int typ) {
		super(mnozstvo,typ);
		this.pocet_stran = (int) Math.floor(Math.random()*(100-50+1)+50);
	}
	/**
	 * Počet strán
	 */
	private int pocet_stran;
	public int getTyp() {
		return this.typ;
	}
	public int getPocet_stran() {
		return pocet_stran;
	}
	public void setPocet_stran(int pocet_stran) {
		this.pocet_stran = pocet_stran;
	}
	@Override
	public double vypocitaj_spotrebu_pap() {
		return this.pocet_stran*this.mnozstvo;
	}

	/**
	 * Prekonaná toString
	 * @return
	 */
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

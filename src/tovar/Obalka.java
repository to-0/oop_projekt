package tovar;

/**
 * Obalka, podtyp triedy s rozmermi.
 */
public class Obalka extends TovarRozmer{
	public Obalka(int mnozstvo, int typ) {
		super(mnozstvo,typ);
		switch(typ) {
		case 1:
			this.a = 35;
			this.b = 20;
			break;
		case 2:
			this.a = 25;
			this.b = 10;
			break;
		case 3:
			this.a = 15;
			this.b = 10;
			break;
		default:
			this.a = 45;
			this.b = 20;
			break;
		}
		this.cena_kus = 0.2;
	}

	/**
	 *
	 * @return Mnozstvo papiera
	 */
	@Override
	public double vypocitaj_spotrebu_pap() {
		switch(this.typ){
			case 1:
				return 1.2*(this.mnozstvo);
			case 2:
				return this.mnozstvo;
			case 3:
				return 0.75*this.mnozstvo;
			default:
				return this.mnozstvo*1.5;
		}
	}
	public String toString(){
		return "Obalka " + "typ: "+this.typ+" mnozstvo:"+this.mnozstvo;
	}

}

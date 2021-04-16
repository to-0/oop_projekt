package tovar;

/**
 *Podtyp tovaru s rozmermi.
 */
public class Fotka extends TovarRozmer implements TonerSpotreba{
	public Fotka(int mnozstvo,int typ) { //3 typy proste
		super(mnozstvo,typ);
		this.cena_kus = 1;
		this.typ = typ;
		switch(typ) {
		case 1:
			this.a = 30;
			this.b = 20;
			break;
		case 2:
			this.a = 15;
			this.b = 10;
			break;
		case 3:
			this.a = 45;
			this.b = 30;
			break;
		default:
			this.a = 1;
			this.b = 1;
			break;
		}
	}
	public Fotka(int mnozstvo,int typ,boolean farebnost) { //3 typy proste
		super(mnozstvo,typ);
		this.cena_kus = 1;
		switch(typ) {
		case 1:
			this.a = 30;
			this.b = 20;
			break;
		case 2:
			this.a = 15;
			this.b = 10;
			break;
		case 3:
			this.a = 45;
			this.b = 30;
			break;
		default:
			this.a = 1;
			this.b = 1;
			break;
		}
		this.typ = typ;
		this.farebnost = farebnost;
	}
	private int typ;
	/**
	 * Farebnost fotky, boolean. Na základe farebnosti je aj vyššia spotreba
	 */
	private boolean farebnost;

	/**
	 * Vypočíta spotrebu papiera na základe množstva tovaru a typu dovaru.
	 * @return
	 */
	public double vypocitaj_spotrebu_pap() {
		switch(this.typ){
			case 1:
				return (this.mnozstvo);
			case 2:
				return 0.5*this.mnozstvo;
			case 3:
				return 1.5*this.mnozstvo;
			default:
				return this.mnozstvo*1.2;
		}
	}

	/**
	 * Vypočíta spotrebu tonera na základe typu  a farebnosti
	 * @return  spotreba tonera v ml
	 */
	@Override
	public double vypocitaj_spotrebu_tonera() { //v ml...
		double sum;
		switch(this.typ){
			case 1:
				sum = this.mnozstvo;
				break;
			case 2:
				sum =  0.7*this.mnozstvo;
				break;
			case 3:
				 sum = 1.2*this.mnozstvo;
				 break;
			default:
				sum = 1.3*this.mnozstvo;
		}
		if(this.farebnost) sum *= 1.5;
		return sum;
	}

	/**
	 *Prekonaná metóda toString
	 * @return String v tvare "Fotka typ: "+this.typ+" mnozstvo:"+this.mnozstvo + "farebnost: "+farebnost+"\n test";
	 */
	public String toString(){
		return "Fotka typ: "+this.typ+" mnozstvo:"+this.mnozstvo + "farebnost: "+farebnost+"\n test";
	}
}

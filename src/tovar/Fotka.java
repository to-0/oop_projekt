package tovar;

public class Fotka extends TovarRozmer{
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
	private boolean farebnost;
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
	public double vypocitaj_spotrebu_tonera() { //v ml...
		switch(this.typ){
			case 1:
				return this.mnozstvo;
			case 2:
				return 0.7*this.mnozstvo;
			case 3:
				return 1.2*this.mnozstvo;
			default:
				return 1.3*this.mnozstvo;
		}
	}
	public String toString(){
		return "Fotka typ: "+this.typ+" mnozstvo:"+this.mnozstvo + "farebnost: "+farebnost+"\n"
				+"\n Vyrobeny: "+this.vyrobeny;
	}
}

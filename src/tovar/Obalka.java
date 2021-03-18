package tovar;

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

	@Override
	public double vypocitaj_spotrebu_pap() {
		// TODO Auto-generated method stub
		return 0;
	}

}

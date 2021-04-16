package tovar;

/**
 * Abstraktná trieda, podtyp triedy Tovar, definuje nové atribúty.
 */
public abstract class TovarRozmer extends Tovar{
	/**
	 * Rozmer a
	 */
	int a;
	/**
	 * Rozmer b
	 */
	int b;
	public TovarRozmer(int mnozstvo,int typ) {
		super(mnozstvo,typ);
	}
}

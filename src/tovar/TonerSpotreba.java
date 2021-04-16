package tovar;

/**
 * Interface spotreba tonera, definuje 1 metodu, ktorá je preťažená.
 */
public interface TonerSpotreba {
    /**
     * Default metóda výpočtu spotreby tonera.
     * @param typ typ tovaru
     * @param mnozstvo množstvo tovaru
     * @return spotreba tonera
     */
    public default double vypocitaj_spotrebu_tonera(int typ,int mnozstvo){
        switch(typ){
            case 1: return 0.75*mnozstvo;
            case 2: return 1.2*mnozstvo;
        }
        return 0;
    }

    /**
     * Spotreba tonera bez parametrov
     * @return
     */
    public default double vypocitaj_spotrebu_tonera(){
        return 0;
    }
}

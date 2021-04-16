package model;
/**
 * Interface, ktory sluzi na pozorovanie správ, definuje metódu notify
 **/
public interface PozorovatelSprav {
    /**
     *
     * @param sprava String novej spravy
     */
    public void  notify(String sprava);
}

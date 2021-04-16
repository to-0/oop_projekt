package model;

import gui.controllers.ErrorController;

import java.lang.reflect.InvocationTargetException;

/**
 * Vlastná výnimka, podtyp RunTimeException
 */
public class Vynimka extends RuntimeException {
    /**
     * Konšstruktor
     * @param sprava správa chyby
     */
    public Vynimka(String sprava){
        this.vyhod(sprava);
    }

    /**
     * Metóda ktorá naštartuje controller chybnej hlášky.
     * @param sprava
     */
    public void vyhod(String sprava){
        ErrorController controller = new ErrorController();
        controller.startError(sprava);
    }
}

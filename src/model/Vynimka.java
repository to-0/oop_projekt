package model;

import gui.controllers.ErrorController;

import java.lang.reflect.InvocationTargetException;

public class Vynimka extends RuntimeException {
    public Vynimka(String sprava){
        this.vyhod(sprava);
    }
    public void vyhod(String sprava){
        ErrorController controller = new ErrorController();
        controller.startError(sprava);
    }
}

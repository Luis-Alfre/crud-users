package controller;

import view.View;
import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        this.view.agregarListenerBoton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = view.getTexto();
                model.setDato(text);
                System.out.println("Texto en el modelo: " + model.getDato());
            }
        });
    }

    public void mount() {
        view.render();
    }
}

package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import model.Model;
import view.View;
public class Controller {

    private Model model;
    private View view;
    private List<Model> usuarios;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.usuarios = new ArrayList<>();

        this.view.addListenerBoton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = view.getTexto();
                model.setDato(text);
                System.out.println("Texto en el modelo: " + model.getDato());

                Model nuevoUsuario = new Model(text);
                usuarios.add(nuevoUsuario);

                view.addUsers(usuarios);
            }
        });
    }

    public void mount() {
        view.render();
    }
}

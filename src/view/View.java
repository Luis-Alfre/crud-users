package view;

import view.components.User;
import model.Model;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class View extends JFrame {

    private JTextField textField;
    private JButton button;
    private JPanel usersPanel;

    public View() {
        setTitle("Crud Users");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textField = new JTextField(20);
        button = new JButton("Send");

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(button);

        this.add(panel, "North");

        usersPanel = new JPanel();
        usersPanel.setLayout(new BoxLayout(usersPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(usersPanel);
        this.add(scrollPane, "Center");
    }

    public void render() {
        setVisible(true);
    }

    public String getTexto() {
        return textField.getText();
    }

    public void setTexto(String texto) {
        textField.setText(texto);
    }

    public void addListenerBoton(ActionListener listener) {
        button.addActionListener(listener);
    }

    public void addUsers(List<Model> usuarios) {
        usersPanel.removeAll();
        for (Model usuario : usuarios) {
            User userPanel = new User(usuario);
            userPanel.addListenerBoton(e -> {
                System.out.println("Button clicked for user: " + usuario.toString());
            });
            usersPanel.add(userPanel);
        }
        usersPanel.revalidate();
        usersPanel.repaint();
    }
}

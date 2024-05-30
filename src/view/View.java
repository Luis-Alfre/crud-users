package view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class View extends JFrame {

    private JTextField textField;
    private JButton button;

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

        this.add(panel);
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

    public void agregarListenerBoton(ActionListener listener) {
        button.addActionListener(listener);
    }
}

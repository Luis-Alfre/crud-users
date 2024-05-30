package view.components;

import model.Model;
import javax.swing.*;
import java.awt.event.ActionListener;

public class User extends JPanel {
    
    private Model user;
    private JButton button;

    public User(Model user) {
        this.user = user;
        this.button = new JButton("Action");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JLabel userLabel = new JLabel(user.toString());
        add(userLabel);
        add(button);
    }

    public void addListenerBoton(ActionListener listener) {
        button.addActionListener(listener);
    }
}

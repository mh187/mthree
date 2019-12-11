package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Login extends JFrame implements ActionListener {
    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;

    Login() {
        user_label = new JLabel();
        user_label.setText("User Name :");
        userName_text = new JTextField();
        password_label = new JLabel();
        password_label.setText("Password :");
        password_text = new JPasswordField();
        submit = new JButton("SUBMIT");
        panel = new JPanel(new GridLayout(3, 1));
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        submit.addActionListener(this);
        add(panel, BorderLayout.CENTER);
        setTitle(" Login ");
        setSize(450, 350);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = String.copyValueOf(password_text.getPassword());
        if (userName.trim().equals("admin") && password.trim().equals("admin")) {
            this.setVisible(false);
            StockSearch stockSearch = new StockSearch();
            stockSearch.setVisible(true);
//            this.setVisible(false);
//            StockCalculate stockCalculate = new StockCalculate("AAPL");
//            stockCalculate.setVisible(true);
//            message.setText(" Hello " + userName + "");
        } else {
            message.setText(" Invalid user ");
        }
    }
}
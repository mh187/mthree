package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Option extends JFrame implements ActionListener {

    private JButton single;
    private JButton file;
    private JButton goBack;
    private JLabel message;

    public Option() {
        single = new JButton("Single Search");
        file = new JButton("File Search (CSV)");
        goBack = new JButton("Go Back");

        single.addActionListener(this);
        file.addActionListener(this);
        goBack.addActionListener(this);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        panel.add(single);
        panel.add(file);

        message = new JLabel("Searching!");
        message.setVisible(false);

        gbc.gridx = 3;
        gbc.gridy = 6;
        panel.add(single, gbc);
        gbc.gridx = 3;
        gbc.gridy = 8;
        panel.add(file, gbc);

        gbc.gridx = 3;
        gbc.gridy = 10;
        panel.add(goBack, gbc);

        gbc.gridx = 3;
        gbc.gridy = 20;
        panel.add(message, gbc);

        setTitle("Single or Multi");
        setSize(800, 600);
        setVisible(true);

        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == single){
            new StockSearch();
            this.dispose();
        } else if (e.getSource() == file) {
            single.setEnabled(false);
            file.setEnabled(false);
            goBack.setEnabled(false);
            message.setVisible(true);
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                new StockFile(selectedFile.getAbsolutePath());
                this.dispose();
            }
        } else {
            this.dispose();
            new Login();
        }
    }
}

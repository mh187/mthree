package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationResult extends JFrame implements ActionListener {

    private JButton resetSearchButton;
    private JButton resetCalculationButton;
    private String stockSymbol;

    public CalculationResult(String stockSymbol) {

        this.stockSymbol = stockSymbol;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel profitLossLabel = new JLabel("Profit/Loss: ");
        JTextField profitLoss = new JTextField(150);
        profitLoss.setEditable(false);

        JLabel stockNameLabel = new JLabel("Stock Name: ");
        JTextField stockName = new JTextField(150);
        stockName.setEditable(false);

        resetSearchButton = new JButton("Back to Search!");
        resetSearchButton.addActionListener(this);

        resetCalculationButton = new JButton("Back to Calculation Page!");
        resetCalculationButton.addActionListener(this);


        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(profitLossLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(profitLoss, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(stockNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(stockName, gbc);

        gbc.gridx = 3;
        gbc.gridy = 5;
        panel.add(resetSearchButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 7;
        panel.add(resetCalculationButton, gbc);

        add(panel);
        setTitle("Results");
        setVisible(true);
        setSize(800, 600);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetSearchButton) {
            new StockSearch();
            this.dispose();
        } else if (e.getSource() == resetCalculationButton) {
            new StockCalculate(stockSymbol);
            this.dispose();
        }
    }
}

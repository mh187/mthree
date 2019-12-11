package com.company;

import com.github.lgooddatepicker.zinternaltools.JIntegerTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculationResult extends JFrame implements ActionListener {

    private JButton resetSearchButton;
    private JButton resetCalculationButton;
    private String stockSymbol;
    private int amountPurchased;
    private int pricePurchasedAt;
    private int currentPrice;
    private int profitLoss;

    public CalculationResult(String stockSymbol, int amountPurchased, int pricePurchasedAt) {

        this.stockSymbol = stockSymbol;
        this.amountPurchased = amountPurchased;
        this.pricePurchasedAt = pricePurchasedAt;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel profitLossLabel = new JLabel("Profit/Loss: ");
        JIntegerTextField profitLoss = new JIntegerTextField();
        profitLoss.setEditable(false);

        JLabel costLabel = new JLabel("Cost: ($)");
        JIntegerTextField cost = new JIntegerTextField();
        cost.setValue(amountPurchased * pricePurchasedAt);
        cost.setEditable(false);

        JLabel stockNameLabel = new JLabel("Stock Name: ");
        JTextField stockName = new JTextField();
        stockName.setPreferredSize(new Dimension(180, 30));
        stockName.setText(this.stockSymbol);
        stockName.setEditable(false);

        resetSearchButton = new JButton("Back to Search!");
        resetSearchButton.addActionListener(this);

        resetCalculationButton = new JButton("Back to Calculation Page!");
        resetCalculationButton.addActionListener(this);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(stockNameLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        panel.add(stockName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(costLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(cost, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(profitLossLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(profitLoss, gbc);

        gbc.gridx = 5;
        gbc.gridy = 5;
        panel.add(resetSearchButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 7;
        panel.add(resetCalculationButton, gbc);

        add(panel);
        setTitle("Results");
        setVisible(true);
        setSize(800, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

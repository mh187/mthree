package com.company;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.JIntegerTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class StockCalculate extends JFrame implements ActionListener {
    private String stockSymbol;
    private int amount;
    private double price;
    private Date datePurchased;
    private JButton resetButton;

    public StockCalculate(String stockSymbol) {

        this.stockSymbol = stockSymbol;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel stockLabel = new JLabel("Stock: " /*+ stockName.toUpperCase()*/);

        JTextField stockNameLabel = new JTextField(this.stockSymbol);
        stockNameLabel.setEditable(false);

        JLabel amountLabel = new JLabel("Amount Purchased: ");

        JLabel priceLabel = new JLabel("Price Purchased at ($): ");
        priceLabel.putClientProperty("JComponent.sizeVariant", "large");

        JIntegerTextField priceText = new JIntegerTextField();

        JIntegerTextField amountText = new JIntegerTextField();

        JLabel datePurchasedLabel = new JLabel("Date Purchased on: ");

        JButton calculateDifferenceButton = new JButton("Calculate Profit/Loss");
        calculateDifferenceButton.addActionListener(this);

        resetButton = new JButton("Go Back");
        resetButton.addActionListener(this);

        LocalDate today = LocalDate.now();
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        DatePicker datePicker = new DatePicker(datePickerSettings);
        datePickerSettings.setAllowKeyboardEditing(false);
        datePickerSettings.setDateRangeLimits(today.minusYears(25L), today);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(stockLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(stockNameLabel, gbc);

        gbc.gridx = 4;
        gbc.gridy = 0;
        panel.add(amountLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 0;
        panel.add(amountText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(priceLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(priceText, gbc);

        gbc.gridx = 4;
        gbc.gridy = 4;
        panel.add(datePurchasedLabel, gbc);
        gbc.gridx = 5;
        gbc.gridy = 4;
        panel.add(datePicker, gbc);

        gbc.gridx = 3;
        gbc.gridy = 8;
        panel.add(calculateDifferenceButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 10;
        panel.add(resetButton, gbc);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);

        setTitle("Calculation");
        setSize(800, 600);
        setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == resetButton) {
            new StockSearch();
            this.dispose();
        } else {
            // Calculate
            new CalculationResult(stockSymbol);
            this.dispose();
        }
    }
}

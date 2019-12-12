package com.company;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.JIntegerTextField;

import javax.swing.*;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Date;

public class StockCalculate extends JFrame implements ActionListener {
    private String stockSymbol;
    private int amount;
    private double price;
    private JIntegerTextField priceText;
    private JIntegerTextField amountText;
    private Date datePurchased;
    private JButton resetButton;
    private DatePicker datePicker;

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

        priceText = new JIntegerTextField();
        priceText.setValue(1);
        priceText.setMinimumValue(1);

        amountText = new JIntegerTextField();
        amountText.setValue(1);
        amountText.setMinimumValue(1);

        JLabel datePurchasedLabel = new JLabel("Date Purchased on: ");

        JButton calculateDifferenceButton = new JButton("Calculate Profit/Loss");
        calculateDifferenceButton.addActionListener(this);

        resetButton = new JButton("Go Back");
        resetButton.addActionListener(this);

        LocalDate today = LocalDate.now();
        DatePickerSettings datePickerSettings = new DatePickerSettings();
        datePicker = new DatePicker(datePickerSettings);
        datePicker.setDateToToday();
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {
            new StockSearch();
            this.dispose();
        } else {
            // Calculate
            try {
                new CalculationResult(stockSymbol, amountText.getValue(), priceText.getValue());
                this.dispose();
            } catch (Exception f) {
                f.printStackTrace();
            }
        }
    }
}

package com.company;

import com.github.lgooddatepicker.zinternaltools.JIntegerTextField;
import com.opencsv.CSVReader;

import javax.swing.*;
import java.io.FileReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StockFile extends JFrame {

    private String stockName;
    private int sumProfit;
    private String file;

    public StockFile(String file) {

        this.file = file;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel stocksStringLabel = new JLabel("Stocks Listed:  ");


        JTextArea stocks = new JTextArea();
        stocks.append("APPL MSFT");
        stocks.setEditable(false);

        JLabel sumLabel = new JLabel("Sum of profit/loss:  ");
        JIntegerTextField sum = new JIntegerTextField();
        sum.setEditable(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(stocksStringLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(stocks, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(sumLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(sum, gbc);

        add(panel);

        setTitle("File search");
        setSize(800, 600);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void readDataLineByLine(String file) {

        try {

            // Create an object of filereader
            // class with CSV file as a parameter.
            FileReader filereader = new FileReader(file);

            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            csvReader.skip(1);
            String[] nextRecord;

            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                for (String cell : nextRecord) {
                    System.out.print(cell + "\t");
                }
                // Search symbol
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


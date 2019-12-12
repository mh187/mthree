package com.company;

import com.github.lgooddatepicker.zinternaltools.JIntegerTextField;
import com.opencsv.CSVReader;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import java.awt.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class StockFile extends JFrame implements ActionListener {

    private String stockName;
    private int sumProfit;
    private String file;
    private int rollingCost = 0;
    private int rollingCostToday = 0;
    private JTextArea stocks;
    private JIntegerTextField sum;
    private JIntegerTextField totalCost;
    private JIntegerTextField totalCostToday;
    private JLabel message;
    JButton goBack;

    public StockFile(String file) {

        this.file = file;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel stocksStringLabel = new JLabel("Stocks Listed:  ");

        stocks = new JTextArea();
        stocks.append("Stock\tPrice Today\t# Purchased\tTotal\n");
        stocks.setEditable(false);

        JLabel totalCostLabel = new JLabel("Your Total Cost ($): ");
        totalCost = new JIntegerTextField();
        totalCost.setEditable(false);

        JLabel totalCostTodayLabel = new JLabel("Total Cost Today ($): ");
        totalCostToday = new JIntegerTextField();
        totalCostToday.setEditable(false);

        message = new JLabel("Searching!");
        message.setVisible(false);

        JLabel sumLabel = new JLabel("Profit/Loss:  ");
        sum = new JIntegerTextField();
        sum.setEditable(false);

        goBack = new JButton("Go Back");
        goBack.addActionListener(this);

        readDataLineByLine(file);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(stocksStringLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(stocks, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(totalCostLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(totalCost, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(totalCostTodayLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(totalCostToday, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(sumLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(sum, gbc);

        //Go back button
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(goBack, gbc);

        gbc.gridx = 1;
        gbc.gridy = 12;
        panel.add(message, gbc);

        add(panel);

        setTitle("File search");
        setSize(800, 600);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private static String getUrl(URL url) throws IOException {
        URLConnection con = url.openConnection();
        con.connect();
        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return (sb.toString());
    }

    public void readDataLineByLine(String file) {

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
                String urlStock = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + nextRecord[0] + "&outputsize=full&apikey=OFYBOAYYDFXDHFBJ";
                URL url = new URL(urlStock);
                String sb2 = getUrl(url);
                JSONObject obj2 = new JSONObject(sb2);
                JSONObject firstItem2 = obj2.getJSONObject("Time Series (Daily)").getJSONObject(String.valueOf(LocalDate.now()));
                rollingCostToday += (Double.valueOf(firstItem2.getString("4. close"))).intValue() * Integer.valueOf(nextRecord[2]);
                stocks.append(nextRecord[0] + "\t" + firstItem2.getString("4. close") + "\t" + nextRecord[2] + "\t" + ((Double.valueOf(firstItem2.getString("4. close"))).intValue() * Integer.valueOf(nextRecord[2])) + "\n");

                // Full Cost
                rollingCost += Integer.valueOf(nextRecord[1]) * Integer.valueOf(nextRecord[2]);
            }
            System.out.println(rollingCostToday);
            System.out.println(rollingCost);
            sumProfit = rollingCostToday - rollingCost;
            sum.setValue(sumProfit);
            totalCost.setValue(rollingCost);
            totalCostToday.setValue(rollingCostToday);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == goBack) {
            this.dispose();
            new Option();
        }
    }
}


package com.company;

import com.github.lgooddatepicker.zinternaltools.JIntegerTextField;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class CalculationResult extends JFrame implements ActionListener {

    private JButton resetSearchButton;
    private JButton resetCalculationButton;
    private String stockSymbol;
    private int amountPurchased;
    private int pricePurchasedAt;
    private int moneySpent;
    private int currentPrice;
    private int profitLoss;

    public CalculationResult(String stockSymbol, int amountPurchased, int pricePurchasedAt) throws IOException, JSONException {

        this.stockSymbol = stockSymbol;
        this.amountPurchased = amountPurchased;
        this.pricePurchasedAt = pricePurchasedAt;
        moneySpent = this.amountPurchased * this.pricePurchasedAt;

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel profitLossLabel = new JLabel("Profit/Loss: ");
        JIntegerTextField profitLoss = new JIntegerTextField();
        profitLoss.setEditable(false);

        JLabel yourCostLabel = new JLabel("Your Cost: ($)");
        JIntegerTextField yourCost = new JIntegerTextField();
        yourCost.setValue(moneySpent);
        yourCost.setEditable(false);

        JLabel stockNameLabel = new JLabel("Stock Name: ");
        JTextField stockName = new JTextField();
        stockName.setPreferredSize(new Dimension(180, 30));
        stockName.setText(this.stockSymbol);
        stockName.setEditable(false);

        JLabel yourPriceLabel = new JLabel("Your Price: ");
        JTextField yourPrice = new JTextField();
        yourPrice.setEditable(false);
        yourPrice.setText(String.valueOf(pricePurchasedAt));

        JLabel currentPriceLabel = new JLabel("Current Price: ");
        JIntegerTextField currentPriceField = new JIntegerTextField(currentPrice);
        currentPriceField.setEditable(false);

        JLabel costTodayLabel = new JLabel("Cost Today: ($): ");
        JIntegerTextField costToday = new JIntegerTextField();
        costToday.setEditable(false);

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
        panel.add(yourPriceLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        panel.add(yourPrice, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(currentPriceLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 4;
        panel.add(currentPriceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(costTodayLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 6;
        panel.add(costToday, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        panel.add(yourCostLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 8;
        panel.add(yourCost, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        panel.add(profitLossLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 10;
        panel.add(profitLoss, gbc);

        gbc.gridx = 5;
        gbc.gridy = 5;
        panel.add(resetSearchButton, gbc);

        gbc.gridx = 5;
        gbc.gridy = 7;
        panel.add(resetCalculationButton, gbc);

        String urlStockDate = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stockSymbol + "&outputsize=full&apikey=OFYBOAYYDFXDHFBJ";
        URL url = new URL(urlStockDate);
        String sb2 = getUrl(url);
        JSONObject obj2 = new JSONObject(sb2);
        JSONObject firstItem2 = obj2.getJSONObject("Time Series (Daily)").getJSONObject(String.valueOf(LocalDate.now()));
        this.currentPrice = (Double.valueOf(firstItem2.getString("4. close"))).intValue();
        this.profitLoss = ( (this.currentPrice * amountPurchased) - moneySpent );

        profitLoss.setValue(this.profitLoss);
        costToday.setValue(this.currentPrice * amountPurchased);
        currentPriceField.setValue(this.currentPrice);

        System.out.println(this.currentPrice);

        add(panel);
        setTitle("Results");
        setVisible(true);
        setSize(800, 600);

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

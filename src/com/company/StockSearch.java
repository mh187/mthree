package com.company;

import org.json.JSONArray;
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

public class StockSearch extends JFrame implements ActionListener {
    private String stockName;

    JPanel panel;
    JLabel searchLabel;
    JTextField searchString;
    JButton searchButton;

    public StockSearch() {
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        searchLabel = new JLabel("Enter stock to search for: ");
        searchString = new JTextField();
        searchString.setPreferredSize(new Dimension(180, 30));

        searchButton = new JButton("Initiate Search");
        searchButton.addActionListener(this);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(this);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(searchLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(searchString, gbc);

        gbc.gridx = 3;
        gbc.gridy = 8;
        panel.add(searchButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 9;
        panel.add(resetButton, gbc);

        add(panel);

        setTitle("Search Stock Name");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            if (!searchString.getText().isEmpty()) {
                stockName = searchString.getText().trim();
                String urlStock = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + stockName + "&apikey=OFYBOAYYDFXDHFBJ";
                try {
                    URL url = new URL(urlStock);
                    String sb = getUrl(url);
                    JSONObject obj = new JSONObject(sb);
                    JSONArray item = obj.getJSONArray("bestMatches");
                    if (item.length() == 0) {
                        System.out.println("NO RESULTS FOUND");
                    } else {
                        // Grab symbol and open new Window where user can input information.
                        StockCalculate stockCalculate = new StockCalculate(item.getJSONObject(0).getString("1. symbol"));
                        this.setVisible(false);
                        stockCalculate.setVisible(true);
                    }
                } catch (IOException | JSONException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("No String Entered");
            }
        } else {
            // e.getSource = resetButton
            this.dispose();
            new Login();
        }
    }
}

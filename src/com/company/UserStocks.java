package com.company;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import static java.lang.Float.parseFloat;

public class UserStocks {
    private String stock;
    private String date;
    private float price;
    private int amount;

    public UserStocks(String stock, String date, float price, int amount) {
        this.stock = stock;
        this.date = date;
        this.price = price;
        this.amount = amount;
    }

    public void printStock(){
        System.out.println("Current Stock");
        System.out.println("Date: " + date);
        System.out.println("Stock: " + stock);
        System.out.println("Amount: " + amount);
        System.out.println("Price: $" + price);
        System.out.println("Total Value: $" + amount*price);
    }
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    private static String getUrl(URL url) throws IOException {
        URLConnection con = url.openConnection();
        con.connect();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        return(sb.toString());
    }
    private void computeDiff(float prevPrice, float currPrice, float amount){
        float priceDiff = currPrice - prevPrice;
        float diff = Math.abs(priceDiff*amount);
        if(priceDiff>0){
            System.out.println("Profit: $" + diff);
        }
        else{
            System.out.println("Loss: $" + diff);
        }
    }
    public static void main(String[] args) throws IOException, JSONException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Date: ");
        String date = input.next();
        System.out.print("Enter Stock: ");
        String stock = input.next();
        System.out.print("Enter Amount: ");
        int amount = input.nextInt();
        System.out.print("Enter Price: ");
        float price = input.nextFloat();
        UserStocks user = new UserStocks(stock, date, price, amount);
        user.printStock();
        String urlStock = "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=" + stock + "&apikey=OFYBOAYYDFXDHFBJ";
        URL url = new URL(urlStock);
        String sb = getUrl(url);
        JSONObject obj = new JSONObject(sb);
        JSONArray firstItem = obj.getJSONArray("bestMatches");
        String stockSymbol = firstItem.getJSONObject(0).getString("1. symbol");
        String urlStockDate = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stockSymbol + "&outputsize=full&apikey=OFYBOAYYDFXDHFBJ";
        URL url2 = new URL(urlStockDate);
        String sb2 = getUrl(url2);
        JSONObject obj2 = new JSONObject(sb2);
        JSONObject firstItem2 = obj2.getJSONObject("Time Series (Daily)");
        float marketPrice = parseFloat(firstItem2.getJSONObject(date).getString("1. open"));
        System.out.println("Market Price: " + marketPrice);
        user.computeDiff(price, marketPrice, amount);
    }
}
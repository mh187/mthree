package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.*;

public class PortfolioAnalysis {
//    public static void main(String[] args) throws IOException, JSONException {
//
//        URL url = new URL("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=sony&apikey=demo");
//        URLConnection con = url.openConnection();
//        con.connect();
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//        StringBuffer sb = new StringBuffer();
//        String str;
//
//        while ((str = br.readLine()) != null) {
//            sb.append(str);
//        }
//
//        JSONObject obj = new JSONObject(sb.toString());
////        JSONObject firstItem = obj.getJSONObject("Time Series FX (Monthly)");
////        System.out.println(firstItem.getJSONObject("2019-12-09").getString("1. open"));
//    }
}

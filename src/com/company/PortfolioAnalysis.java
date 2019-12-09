package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.*;

public class PortfolioAnalysis {
    public static void main(String[] args) throws IOException, JSONException {

        URL url = new URL("https://www.alphavantage.co/query?function=FX_MONTHLY&from_symbol=EUR&to_symbol=USD&apikey=demo");
        URLConnection con = url.openConnection();
        con.connect();

        InputStream is = con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        StringBuffer sb = new StringBuffer();
        String str;

        while ((str = br.readLine()) != null) {
            sb.append(str);
        }

        System.out.println(sb.toString());
        JSONObject obj = new JSONObject(sb.toString());
        System.out.println("Length is " + obj.length());
        JSONObject firstItem = obj.getJSONObject("Meta Data");
        System.out.println(firstItem.getString("5. Time Zone"));
    }
}

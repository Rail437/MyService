package com.example.myservice.service;

import com.example.myservice.configs.MyData;
import com.example.myservice.model.Root;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

@Service
public class FindRate {
    MyData myData = new MyData();
    public Root findRate(String str, String date){
        StringBuilder content = new StringBuilder();

        if (!str.isEmpty()) {
            myData.setCurrency(str);
        }
        if (!date.isEmpty()){
            myData.setDate(date);
        }
        String myUrl = myData.getRATE_HTTP();
        System.out.println(myUrl);
        final URL url;
        try {
            url = new URL(myUrl);

        final HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setUseCaches(true);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseJson(content.toString());
    }


    // парсим некоторые данные
    public Root parseJson(String resultJson) {
        Root root = new Root();
        String finder = myData.getCurrency().toUpperCase(Locale.ROOT);
        try {
            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(resultJson);
            JSONObject simpleObject = (JSONObject) jsonObject.get("rates");
            System.out.println(simpleObject.toString());
            root.setBase((String) jsonObject.get("base"));
            root.setRates((Double) simpleObject.get(finder));

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return root;
    }
}

package com.example.myservice.service;

import com.example.myservice.configs.MyData;
import com.example.myservice.model.Root;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class FindRate {
    @Autowired
    MyData myData;
    public Root findByRate(Boolean latest){
        StringBuilder content = new StringBuilder();
        String myUrl = myData.getRATE_HTTP();
        if (!latest){
            myUrl = myData.getHISTORICAL_RATE_HTTP();
        }
        //System.out.println("FindRate: " + myUrl);
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
            return new Root(0.404);
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
            root.setBase((String) jsonObject.get("base"));
            root.setRates((Double) simpleObject.get(finder));

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
            return new Root(0.404);
        }
        return root;
    }
}

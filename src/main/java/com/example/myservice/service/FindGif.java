package com.example.myservice.service;

import com.example.myservice.configs.MyData;
import com.example.myservice.model.MyGif;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
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
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FindGif {
    @Autowired
    private MyData myData;

    public MyGif findGif(Double result){
        StringBuilder content = new StringBuilder();
        String findedGif = myData.getFindRichGif();
        if(result < 0){
            findedGif = myData.getFindBrokeGif();
        }

        String myUrl = myData.getGIF_HTTP()+findedGif;
        System.out.println("FindGif: " +myUrl);
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
        return parseGifJson(content.toString());
    }

    private MyGif parseGifJson(String string) {
        MyGif myGif = new MyGif();
        String finder = myData.getCurrency().toUpperCase(Locale.ROOT);
        try {
            // конвертируем строку с Json в JSONObject для дальнейшего его парсинга
            JSONObject jsonObject = (JSONObject) JSONValue.parseWithException(string);
            JSONArray simpleObject = (JSONArray) jsonObject.get("data");
            int random = new Random().nextInt(20);

            JSONObject myObject = (JSONObject) simpleObject.get(random);
            JSONObject imgObject = (JSONObject) myObject.get("images");
            JSONObject resultJson = (JSONObject) imgObject.get("original");
            myGif.setEmbed_url((String) resultJson.get("url"));
            //System.out.println("gifurl: "+myGif.getEmbed_url());

        } catch (org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return myGif;

    }
}

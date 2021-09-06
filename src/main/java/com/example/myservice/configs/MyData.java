package com.example.myservice.configs;

import lombok.Data;
import org.intellij.lang.annotations.Pattern;
import org.springframework.stereotype.Component;

@Data
@Component
public class MyData {
    private final String RATE_API_KEY = "bb2ae58365784711a4619ef57b83e413";
    private final String GIF_API_KEY = "LEGowpHETKGQcnjdhcoDBKEt2aDWlBXr";

    private String base = "usd";
    private String currency = "EUR";
    private String findGif = "rich";

    @Pattern(value = "yyyy-MM-dd")
    private String date = "2021-08-31";

    private final String GIF_HTTP = "http://api.giphy.com/v1/gifs/search?api_key="+ GIF_API_KEY +"&q="+findGif;

    private String RATE_HTTP = "https://openexchangerates.org/api/latest.json?app_id="
            + RATE_API_KEY +"&base=" + base + "&symbols=" + currency;

    private String HISTORICAL_RATE_HTTP = "https://openexchangerates.org/api/historical/"
            +date+".json?"
            +"app_id="+RATE_API_KEY
            +"&base="+base
            +"&symbols="+currency;
}

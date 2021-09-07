package com.example.myservice.configs;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.intellij.lang.annotations.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
@Component
@RequiredArgsConstructor
@PropertySource("classpath:MyData.properties")
@ConfigurationProperties(prefix = "mydata")
public class MyData {
    @Value("${mydata.rate_api_key}")
    private String rate_api_key;
    @Value("${mydata.gif_api_key}")
    private String gif_api_key;

    @Value("${mydata.currency}")
    private String currency;

    private String findRichGif = "rich";
    private String findBrokeGif = "broke";
    @Value("${mydata.gif_api_http}")
    private String gif_api_http;
    @Value("${mydata.rate_api_http}")
    private String rate_api_http;


    @Pattern(value = "yyyy-MM-dd")
    private String date = "2021-09-07";

    private String dataLatest = "latest.json?";
    private String historicalData;


    private String GIF_HTTP;
    private String RATE_HTTP;
    private String HISTORICAL_RATE_HTTP;


    @PostConstruct
    private void init() {
        this.GIF_HTTP = gif_api_http + "search?api_key=" + gif_api_key + "&q=";
        this.RATE_HTTP = rate_api_http + "latest.json?app_id="
                + rate_api_key + "&base=usd&symbols=" + currency;
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);
        this.historicalData = "historical/" + dateFormat.format(cal.getTime()) + ".json?";
        this.HISTORICAL_RATE_HTTP = rate_api_http + historicalData
                + "app_id=" + rate_api_key
                + "&base=usd&symbols=" + currency;
    }
}

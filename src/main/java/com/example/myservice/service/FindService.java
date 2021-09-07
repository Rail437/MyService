package com.example.myservice.service;

import com.example.myservice.configs.MyData;
import com.example.myservice.model.MyGif;
import com.example.myservice.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class FindService {
    @Autowired
    private FindRate findRate;
    @Autowired
    private FindGif findGif;
    @Autowired
    private MyData myData;

    private String helloGif = "https://www.reactiongifs.com/r/fgwv.gif";

    public FindService() {
    }

    public String finder(){
        Root firstRoot = findRate.findRate(true);
        Root twoRoot = findRate.findRate(false);
        System.out.println("firstRoot.getRates(): "+firstRoot.getRates() + " twoRoot.getRates(): " + twoRoot.getRates());
        Double check = (firstRoot.getRates() - twoRoot.getRates());

        MyGif myGif = findGif.findGif(check);
        this.helloGif = myGif.getEmbed_url();
        return myGif.getEmbed_url();
    }

    public String getHelloGif() {
        return helloGif;
    }
}

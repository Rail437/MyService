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
    FindRate findRate;
    @Autowired
    FindGif findGif;

    private final MyData myData = new MyData();

    private String helloGif = "https://www.reactiongifs.com/r/fgwv.gif";

    public FindService() {
    }

    public String finder(){
        Root firstRoot = findRate.findRate(myData.getCurrency(), myData.getDate());
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, -1);

        Root twoRoot = findRate.findRate( myData.getCurrency(), dateFormat.format(cal.getTime()));
        Double check = (firstRoot.getRates() - twoRoot.getRates());

        MyGif myGif = findGif.findGif(check);
        this.helloGif = myGif.getEmbed_url();
        return myGif.getEmbed_url();
    }

    public String getHelloGif() {
        return helloGif;
    }
}

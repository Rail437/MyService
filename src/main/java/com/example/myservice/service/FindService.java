package com.example.myservice.service;

import com.example.myservice.model.MyGif;
import com.example.myservice.model.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindService {
    @Autowired
    private FindRate findRate;
    @Autowired
    private FindGif findGif;

    private String helloGif = "https://www.reactiongifs.com/r/fgwv.gif";

    public FindService() {
    }

    public String finder(){
        Root firstRoot = findRate.findByRate(true);
        Root twoRoot = findRate.findByRate(false);
        Double check = (firstRoot.getRates() - twoRoot.getRates());

        MyGif myGif = findGif.findRandomGif(check);
        this.helloGif = myGif.getEmbed_url();
        return myGif.getEmbed_url();
    }

    public String getHelloGif() {
        return helloGif;
    }
}

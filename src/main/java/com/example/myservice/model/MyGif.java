package com.example.myservice.model;

import lombok.Data;

@Data
public class MyGif {
    private String embed_url;

    public MyGif() {

    }
    public MyGif(String embed_url) {
        this.embed_url = embed_url;
    }
}

package com.example.myservice.model;

import lombok.Data;

@Data
public class Root {
    private String base;
    private Double rates;

    public Root() {
    }

    public Root(Double rates) {
        this.rates = rates;
    }

    public Root(String base) {
        this.base = base;
    }
}

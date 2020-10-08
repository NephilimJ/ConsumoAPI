package com.example.consumoapi.RetrofitPackage.POST;

import com.example.consumoapi.RetrofitPackage.State;

public class City {
    private int id;
    private String name;
    private State state;

    public City(int id, String name, State state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}

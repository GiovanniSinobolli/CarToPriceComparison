package com.example.pricetohpGraph;

public class Car {
    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public int getHorsepower() {
        return horsepower;
    }

    private final int id;
    private final String brand;       // renamed from 'make'
    private final double price;
    private final int horsepower;

    public Car(int id, String brand, double price, int horsepower) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.horsepower = horsepower;
    }

}

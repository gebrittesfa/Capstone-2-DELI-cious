package com.pluralsight;

import java.util.List;

public class  Sandwich {
    int sizes;
    String breadType;
    double price;
    String meats;
    String bread;
    String cheese;

    boolean isToasted;
    List<String> Toppings;

    public Sandwich(int sizes, String breadType, boolean isToasted, List<String> toppings) {
        this.sizes = sizes;
        this.breadType = breadType;
        this.isToasted = isToasted;
        this.price = price;
        Toppings = toppings;

    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "sizes=" + sizes +
                ", breadType='" + breadType + '\'' +
                ", isToasted=" + isToasted +
                ", isToasted=" + price +
                ", Toppings=" + Toppings +
                '}';
    }

    public int getSizes() {
        return sizes;
    }

    public void setSizes(int sizes) {
        this.sizes = sizes;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public List<String> getToppings() {
        return Toppings;
    }

    public void setToppings(List<String> toppings) {
        Toppings = toppings;
    }
    // I have to add a method of override
}

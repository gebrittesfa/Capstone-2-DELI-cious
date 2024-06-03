package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public Order() {

    }


    List<Sandwich> sandwiches = new ArrayList<>();
    int drinks = 0;

    String typeOfDrink;

    int chips = 0;


    public Order(List<Sandwich> sandwiches, String typeOfDrink, int chips, int drinks) {
        this.sandwiches = sandwiches;
        this.typeOfDrink = typeOfDrink;
        this.chips = chips;
        this.drinks = drinks;
    }

    public String getTypeOfDrink() {
        return typeOfDrink;
    }

    public void setTypeOfDrink(String typeOfDrink) {
        this.typeOfDrink = typeOfDrink;
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public void setSandwiches(List<Sandwich> sandwiches) {
        this.sandwiches = sandwiches;
    }

    public int getDrinks() {
        return drinks;
    }

    public void setDrinks(int drinks) {
        this.drinks = drinks;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addDrink(String typeOfDrink) {
        this.typeOfDrink = typeOfDrink;
    }

    public void addChips(double price) {
        this.chips += price;
    }

    public double calculateTotal() {
        double total = 0;
        for (Sandwich sandwich : sandwiches) {
            total += calculateSandwichCost(sandwich);
        }
        if (getTypeOfDrink().equalsIgnoreCase("Small")) total += getDrinks() * 2.00;
        if (getTypeOfDrink().equalsIgnoreCase("Medium")) total += getDrinks() * 2.5;
        if (getTypeOfDrink().equalsIgnoreCase("Large")) total += getDrinks() * 3.00;
        total += getChips() * 1.5; // Example price for chips
        return total;
    }

    private double calculateSandwichCost(Sandwich sandwich) {
        double basePrice = 0;
        switch (sandwich.getSizes()) {
            case 4:
                basePrice = 5.50;
                break;
            case 8:
                basePrice = 7.00;
                break;
            case 12:
                basePrice = 8.50;
                break;
        }

        for (String topping : sandwich.getToppings()) {
            switch (sandwich.getSizes()) {
                case 4:
                    if (isPremiumTopping(topping)) {
                        basePrice += 1.00;
                    }
                    if (isPremiumToppingCheese(topping)) {
                        basePrice += 0.75;
                        if (checkIfToppings(topping)) {
                            basePrice += 0.50;
                        }
                    }
                    break;
                case 8:
                    if (isPremiumTopping(topping)) {
                        basePrice += 2.00;
                    }
                    if (isPremiumToppingCheese(topping)) {
                        basePrice += 1.50;
                    }
                    break;
                case 12:
                    if (isPremiumTopping(topping)) {
                        basePrice += 3.00;
                    }
                    if (isPremiumToppingCheese(topping)) {
                        basePrice += 2.25;
                    }
                    break;
            }
        }
        return basePrice;
    }

    private boolean checkIfToppings(String topping) {
        return topping.equalsIgnoreCase("Extra Meat");
    }

    private boolean isPremiumTopping(String topping) {
        return topping.equalsIgnoreCase("steak") ||
                topping.equalsIgnoreCase("ham") ||
                topping.equalsIgnoreCase("salami") ||
                topping.equalsIgnoreCase("roast beef") ||
                topping.equalsIgnoreCase("chicken") ||
                topping.equalsIgnoreCase("bacon");
    }

    private boolean isPremiumToppingCheese(String topping) {
        return topping.equalsIgnoreCase("american") ||
                topping.equalsIgnoreCase("provolone") ||
                topping.equalsIgnoreCase("cheddar") ||
                topping.equalsIgnoreCase("swiss");
    }
}


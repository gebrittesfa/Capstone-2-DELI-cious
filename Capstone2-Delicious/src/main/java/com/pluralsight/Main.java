package com.pluralsight;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        // Main application loop
        while (running) {
            // Display the home screen menu options
            System.out.println("Home Screen: ");
            System.out.println("1. New Order");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            // Handle user input for the home screen menu
            switch (choice) {
                case 1:
                    newOrder(); // Start a new order
                    break;
                case 0:
                    running = false; // Exit the application
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

    }

    private static void  newOrder() {
        Order order = new Order();
        boolean ordering = true;
        // Display the home screen menu
        Scanner scanner = new Scanner(System.in);
        // Order creation loop
        while (ordering) {
            System.out.println("Order Screen: ");
            System.out.println("1. Add Sandwich");
            System.out.println("2. Add Drink");
            System.out.println("3. Add Chips");
            System.out.println("4. Checkout");
            System.out.println("0. Cancel Order");
            System.out.println("Choose an next option: ");
            int choice = scanner.nextInt();
            // Handle the user input for the order screen menu
            switch (choice) {
                case 1:
                    order.addSandwich(customizeSandwich());  // Add a new sandwich
                    break;
                case 2:
                    order.addDrink(chooseDrinkSize());  // Add drinks to the order
                    order.setDrinks(chooseDrink());
                    break;
                case 3:
                    order.addChips(chooseChips());  // Add chips to the order
                    break;
                case 4:
                    checkout(order);   // Checkout the order
                    ordering = false;
                    break;
                case 0:
                    ordering = false;  // Cancel the order
                    break;
                default:
                    System.out.println("Invalid choice, please try again. ");
            }

        }
    }

    private static Sandwich customizeSandwich() {
        // Prompt user to select the bread type
        System.out.print("Select your bread (white, wheat, rye, wrap): ");
        Scanner scanner = new Scanner(System.in);
        String breadType = scanner.nextLine();

        // Prompt user to select the sandwich size
        System.out.print("Choose size (4, 8, 12 inches): ");
        int size = scanner.nextInt();

        // List to hold the toppings for the sandwich
        List<String> toppings = new ArrayList<>();

        // Prompt user to add cheese toppings
        while (true) {
            System.out.println("Add meat topping (steak, ham, salami, roast beef, chicken, " +
                    "bacon (type 'done' when finished): ");
            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            toppings.add(topping);
        }

        // Prompt user to add cheese toppings
        while (true) {
            System.out.println("Add cheese topping(american, provolone, " +
                    "cheddar, swiss) (type 'done' when finished): ");
            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            toppings.add(topping);
        }

        // Prompt user to add other/regular toppings
        while (true) {
            System.out.print("Add regular topping (lettuce, peppers, onions, tomatoes, jalepenos," +
                    "cucumbers, pickles, guacamole, mushrooms)(type 'done' when finished): ");
            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            toppings.add(topping);
        }
        // Prompt user to add sauces
        while (true) {
            System.out.print("Add sauce (mayo, mustard, ketchup, ranch," +
                    "thousand islands, vinaigrette) (type 'done' when finished): ");
            String topping = scanner.nextLine();
            if (topping.equalsIgnoreCase("done")) {
                break;
            }
            toppings.add(topping);
        }

        System.out.print("Would you like the sandwich toasted? (yes/no): ");
        boolean isToasted = scanner.nextLine().equalsIgnoreCase("yes");

        return new Sandwich(size, breadType, isToasted, toppings);
    }

    private static int chooseChips() {
        // Prompt user to enter the number of chips
        System.out.print("How many chips would you like to add? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static int chooseDrink() {
        // Prompt user to enter the number of chips
        System.out.print("How many drinks would you like to add? ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String chooseDrinkSize() {
        // Prompt user to enter the number of drink
        System.out.print("Enter the size of the drinks: ");
        Scanner scanner = new Scanner(System.in);
        String sizeOfDrink = scanner.nextLine();
        if (sizeOfDrink.equalsIgnoreCase("Small")) return sizeOfDrink;

        else if (sizeOfDrink.equalsIgnoreCase("Medium")) return sizeOfDrink;

        else return sizeOfDrink;

    }

    private static void checkout(Order order) {

        System.out.print("Would you like to confirm the order? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String confirmOrder = scanner.nextLine();
        if (confirmOrder.equalsIgnoreCase("yes")) {
            try {
                saveReceipt(order); // Save the order receipt
                System.out.println("order" + order.drinks);
                // Prompt user to confirm the order
                System.out.println("Order confirmed and receipt saved.");
                // Display the order details
                displayOrder(order);
            } catch (IOException e) {
                System.err.println("Failed to save receipt: " + e.getMessage());
            }

        } else {
            System.out.println("Order cancelled.");
        }
    }

    // Display each sandwich in the order
    private static void displayOrder(Order order) {
        int index = 1;
        for (Sandwich sandwich : order.getSandwiches()) {
            System.out.println("Sandwich " + index++ + ": " + sandwich);
        }
        // display the number of drinks and chips
        System.out.println("Drinks: " + order.getDrinks());
        System.out.println("Chips: " + order.getChips());
        // display the total cost of the order
        System.out.println("Total Cost: $" + order.calculateTotal());
    }

    private static void saveReceipt(Order order) throws IOException {

        // Create a timestamp for the receipt filename
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd-HH:mm:ss"));

        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("order.txt"));
            String line;

            for (Sandwich sandwich : order.getSandwiches()) {
                writer.write("Order date = " + timestamp + "\n");
                writer.write("Sandwich[ Type of bread = " + sandwich.getBreadType() + " : Size of bread = " + sandwich.getSizes() +
                        ": Type of toppings = " + sandwich.getToppings() + " : isToasted =" + sandwich.isToasted + "\n");

            }
            writer.write("Drinks: " + order.getDrinks() + "\n");
            writer.write("Chips: " + order.getChips() + "\n");
            writer.write("Total Cost: $" + order.calculateTotal() + "\n");


            writer.close();
        } catch (Exception e) {
            System.out.println("Error found " + e.getMessage());
            e.printStackTrace();
        }


    }
}

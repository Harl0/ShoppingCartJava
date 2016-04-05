package shopping.cart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jason
 */
public class ShoppingCart {

    public static String special_offer;

    public static void main(String[] args) throws IOException {

        // Read the properties file
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("/Users/Jason/NetBeansProjects/shopping-cart/config/shoppingCart.properties"));
        } catch (IOException ex) {
            System.out.println("IOException - " + ex.getMessage());
        }

        // Imported variables from shoppingCart.properties
        special_offer = (String) properties.get("special_offer");
        String items = (String) properties.get("items");
        int apples_count = Integer.parseInt((String) properties.get("apples_count"));
        int oranges_count = Integer.parseInt((String) properties.get("oranges_count"));
        int apple_value = Integer.parseInt((String) properties.get("apple_value"));
        int orange_value = Integer.parseInt((String) properties.get("orange_value"));
        int apple_deal_amount = Integer.parseInt((String) properties.get("apple_deal_amount"));
        int orange_deal_amount = Integer.parseInt((String) properties.get("orange_deal_amount"));

        // FileReader constructor to open list of items
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(new File(items));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }

        // BufferedReader constructor to enable reading of file line by line
        BufferedReader br = new BufferedReader(fileReader);

        // Read items.txt and count number of relevant items
        String line = null;
        // If no more lines the readLine() returns null
        while ((line = br.readLine()) != null) {
            if (line.equalsIgnoreCase("apple")) {
                apples_count++;
            }

            if (line.equalsIgnoreCase("orange")) {
                oranges_count++;
            }
        }

        // Method calls
        int total_apple_cost = cost_of_items(apple_deal_amount, apples_count, apple_value);
        int total_orange_cost = cost_of_items(orange_deal_amount, oranges_count, orange_value);
        double total_basket_cost = total_basket_cost(total_apple_cost, total_orange_cost);

        System.out.println("Apples = " + apples_count);
        System.out.println("Oranges = " + oranges_count);
        System.out.format("Cost to customer: £%.2f%n", total_basket_cost / 100);

    }
    
    // Calculate cost of items in shopping basket
    public static int cost_of_items(int item_amount, int item_count, int item_value) {
        int cost_to_customer = 0;

        if (special_offer.equals("ON")) {

            int prime_value = item_amount + 1;
            int multipler = item_count / prime_value;
            int discount_to_bill = 1 * multipler;
            int total_to_bill = item_count - discount_to_bill;
            cost_to_customer = total_to_bill * item_value;

        } else {
            cost_to_customer = item_count * item_value;
        }
        return cost_to_customer;
        
    }

    // Calculate total cost of shop
    public static int total_basket_cost(int apple_total_cost, int orange_total_cost) {

        int basket_grand_total = apple_total_cost + orange_total_cost;
        return basket_grand_total;
    }

}
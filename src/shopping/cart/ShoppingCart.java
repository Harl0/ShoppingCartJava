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
            System.out.println("Loading properties file...");
            properties.load(new FileInputStream("shoppingCart.properties"));
        } catch (IOException ex) {
            System.out.println("IOException - " + ex.getMessage());
        }

        // Variables from shoppingCart.properties
        special_offer = (String) properties.get("special_offer");
        int apples_count = Integer.parseInt((String) properties.get("apples_count"));
        int oranges_count = Integer.parseInt((String) properties.get("oranges_count"));
        int apple_value = Integer.parseInt((String) properties.get("apple_value"));
        int orange_value = Integer.parseInt((String) properties.get("orange_value"));
        int apple_buy_ammount = Integer.parseInt((String) properties.get("apple_buy_ammount"));
        int orange_buy_ammount = Integer.parseInt((String) properties.get("orange_buy_ammount"));

        String items = (String) properties.get("items");

        //System.out.println("special_offer = " + special_offer);
        //System.out.println("apples_count = " + apples_count);
        //System.out.println("oranges_count = " + oranges_count);
        //System.out.println("apple_value = " + apple_value);
        //System.out.println("orange_value = " + orange_value);
        //System.out.println("items = " + items);
        FileReader fileReader = null;

        try {
            fileReader = new FileReader(new File(items));

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShoppingCart.class.getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader br = new BufferedReader(fileReader);

        // Read items.txt and count number of relevant items
        String line = null;
        // if no more lines the readLine() returns null
        while ((line = br.readLine()) != null) {
            //System.out.println("currently reading: " + line);
            if (line.contains("apple")) {
                //System.out.println("Found an apple!");
                apples_count++;
                //System.out.println("apples_count = " + apples_count);
            }

            if (line.contains("orange")) {
                //System.out.println("Found an orange!");
                oranges_count++;
                //System.out.println("oranges_count = " + oranges_count);
            }
        }

        // Method calls
        int total_apple_cost = cost_of_items(apple_buy_ammount, apples_count, apple_value);
        int total_orange_cost = cost_of_items(orange_buy_ammount, oranges_count, orange_value);

        double total_basket_cost = total_basket_cost(total_apple_cost, total_orange_cost);

        System.out.println("total_basket_cost = " + total_basket_cost);

        System.out.format("£%.2f%n", total_basket_cost /100 );

    }
    // Show cost of items

    public static int cost_of_items(int item_ammount, int item_count, int item_value) {
        int cost_to_customer = 0;

        System.out.println("Special offer = " + special_offer);

        if (special_offer.equals("ON")) {

            int prime_value = item_ammount + 1;
            int multipler = item_count / prime_value;
            int discount_to_bill = 1 * multipler;
            int total_to_bill = item_count - discount_to_bill;
            cost_to_customer = total_to_bill * item_value;

            System.out.println("prime_value = " + prime_value);
            System.out.println("multipler = " + multipler);
            System.out.println("discount_to_bill = " + discount_to_bill);
            System.out.println("total_to_bill = " + total_to_bill);

        } else {
            cost_to_customer = item_count * item_value;
        }
        System.out.println("item_ammount = " + item_ammount);
        System.out.println("item_count = " + item_count);
        System.out.println("item_value = " + item_value);
        System.out.println("cost_to_customer = " + cost_to_customer);
        return cost_to_customer;
    }

    // Total cost of shop
    public static int total_basket_cost(int apple_total_cost, int orange_total_cost) {

        int basket_grand_total = apple_total_cost + orange_total_cost;
        return basket_grand_total;
    }

}

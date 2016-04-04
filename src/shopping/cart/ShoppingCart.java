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
        String special_offer = (String) properties.get("special_offer");
        //int apples_count = (int)properties.get("apples_count");
        //int oranges_count = (int)properties.get("oranges_count");
        //int apple_value = (int)properties.get("apple_value");
        //int orange_value = (int)properties.get("orange_value");
        String items = (String) properties.get("items");

        System.out.println("special_offer = " + special_offer);
        //System.out.println("apples_count = " + apples_count);
        //System.out.println("oranges_count = " + oranges_count);
        //System.out.println("apple_value = " + apple_value);
        //System.out.println("orange_value = " + orange_value);
        System.out.println("items = " + items);

        int apples_count = 0;
        int oranges_count = 0;
        
        int apple_value = 60;
        int orange_value = 25;
        
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
                System.err.println("apples_count = " + apples_count);
            }

            if (line.contains("orange")) {
                //System.out.println("Found an orange!");
                oranges_count++;
                System.err.println("oranges_count = " + oranges_count);
            }
        }
        
        // Add up total counts of each item, check for special offer!
        if (special_offer.equals("ON")) {
            System.out.println("Special offer = " + special_offer);
        }
        
        // Cost of apples
        int apple_cost = apple_value * apples_count;
        
        System.out.println("apple_cost = " + apple_cost);
        
        // Cost of oranges
        int orange_cost = orange_value * oranges_count;
        
        System.out.println("orange_cost = " + orange_cost);
        
        // Total cost of shop
        double basket_grand_total = apple_cost + orange_cost;
        
        System.out.println("basket_grand_total = " + basket_grand_total);
        System.out.format("£%.2f%n", basket_grand_total /100);
       
    }
}

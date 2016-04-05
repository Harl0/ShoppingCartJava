
import static shopping.cart.ShoppingCart.cost_of_items;
import static shopping.cart.ShoppingCart.special_offer;

/**
 *
 * @author Jason
 */
public class TestingDealOn {

    public static void main(String[] args) {
        // Positive Tests, apples
        double test1Result = cost_of_items_test(1, 1, 60);
        System.out.println("Expected Result = £0.60");
        System.out.format("Actual Result = £%.2f%n", test1Result / 100);     
        System.out.println("");

        double test2Result = cost_of_items_test(1, 3, 60);
        System.out.println("Expected Result = £1.20");
        System.out.format("Actual Result = £%.2f%n", test2Result / 100);
        System.out.println("");

        double test3Result = cost_of_items_test(1, 4, 60);
        System.out.println("Expected Result = £1.20");
        System.out.format("Actual Result = £%.2f%n", test3Result / 100);
        System.out.println("");

        double test4Result = cost_of_items_test(1, 99, 60);
        System.out.println("Expected Result = £30.00");
        System.out.format("Actual Result = £%.2f%n", test4Result / 100);
        System.out.println("");

        // Positive Tests, oranges
        double test5Result = cost_of_items_test(2, 1, 25);
        System.out.println("Expected Result = £0.25");
        System.out.format("Actual Result = £%.2f%n", test5Result / 100);
        System.out.println("");

        double test6Result = cost_of_items_test(2, 3, 25);
        System.out.println("Expected Result = £0.50");
        System.out.format("Actual Result = £%.2f%n", test6Result / 100);
        System.out.println("");

        double test7Result = cost_of_items_test(2, 4, 25);
        System.out.println("Expected Result = £0.75");
        System.out.format("Actual Result = £%.2f%n", test7Result / 100);
        System.out.println("");

        double test8Result = cost_of_items_test(2, 99, 25);
        System.out.println("Expected Result = £16.50");
        System.out.format("Actual Result = £%.2f%n", test8Result / 100);
        System.out.println("");
        
        // Negative Tests
        double test9Result = cost_of_items_test(0, 0, 0);
        System.out.println("Expected Result = £0.00");
        System.out.format("Actual Result = £%.2f%n", test9Result / 100);
        System.out.println("");     

    }

    public static int cost_of_items_test(int item_deal_amount, int item_count, int item_value) {

        special_offer = "ON";

        int the_cost_of_items = cost_of_items(item_deal_amount, item_count, item_value);
        System.out.println("Item Deal Amount = " + item_deal_amount);
        System.out.println("Item Count In Shopping Basket = " + item_count);
        System.out.println("Item Purchase Value = " + item_value);

        return the_cost_of_items;

    }
}

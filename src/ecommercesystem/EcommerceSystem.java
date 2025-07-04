/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ecommercesystem;
import java.time.LocalDate;
/**
 *
 * @author Omar Hamdy Fathy Jaffer.
 */
public class EcommerceSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // current date and valid duration (in days)
        LocalDate today = LocalDate.of(2025, 7, 4);
        LocalDate futureExpiry = today.plusDays(10);
        LocalDate pastExpiry = today.minusDays(1000); 

        // Create products
        Product cheese = new Product("Cheese", 100, 5, true, futureExpiry, true, 0.4);
        Product biscuits = new Product("Biscuits", 150, 2, true, futureExpiry, true, 0.7);
        Product tv = new Product("TV", 300, 3, false, null, true, 5.0);
        Product scratchCard = new Product("Scratch Card", 50, 10, false, null, false, 0.0);
        Product expiredMilk = new Product("Milk", 80, 5, true, pastExpiry, true, 1.0);

        // 1.Successful checkout
        try {
            System.out.println("\n------- Case 1: Successful Checkout -------");
            Customer customer1 = new Customer("Omar", 1000);
            Cart cart1 = new Cart();
            cart1.addProduct(cheese, 2);
            cart1.addProduct(biscuits, 1);
            cart1.addProduct(scratchCard, 1);
            CheckoutService.checkout(customer1, cart1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 2. checkout with empty cart
        try {
            System.out.println("\n------- Case 2: Empty Cart -------");
            Customer customer2 = new Customer("Sarah", 500);
            Cart cart2 = new Cart();
            CheckoutService.checkout(customer2, cart2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 3.Product out of stock
        try {
            System.out.println("\n------- Case 3: Out of Stock -------");
            Customer customer3 = new Customer("Ali", 1000);
            Cart cart3 = new Cart();
            cart3.addProduct(tv, 10); // only 3 in stock
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 4.Expired product in cart
        try {
            System.out.println("\n------- Case 4: Expired Product -------");
            Customer customer4 = new Customer("Laila", 1000);
            Cart cart4 = new Cart();
            cart4.addProduct(expiredMilk, 1);
            CheckoutService.checkout(customer4, cart4);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 5.Insufficient balance
        try {
            System.out.println("\n------- Case 5: Insufficient Balance -------");
            Customer customer5 = new Customer("Ziad", 100);
            Cart cart5 = new Cart();
            cart5.addProduct(tv, 1); // costs 300
            CheckoutService.checkout(customer5, cart5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
}

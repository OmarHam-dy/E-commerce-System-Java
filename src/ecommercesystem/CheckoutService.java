/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommercesystem;

import java.util.List;

/**
 *
 * @author TOSHIA
 */
public class CheckoutService {
    public static void checkout(Customer customer, Cart cart) throws Exception {
        if (cart.isEmpty()) throw new Exception("Cart is empty!");

        double subtotal = cart.getSubtotal();
        double shipping = 0;

        List<CartItem> items = cart.getItems();

        for (CartItem item : items) {
            Product p = item.getProduct();
            if (p.isExpirable() && p.isExpired())
                throw new Exception(p.getName() + " is expired!");
            if (!p.isAvailable(item.getQuantity()))
                throw new Exception(p.getName() + " is out of stock!");
        }

        List<ShippableItem> shippables = cart.getShippableItems();
        if (!shippables.isEmpty()) {
            ShippingService.ship(shippables);
            shipping = shippables.size() * 10; // flat rate per item
        }

        double total = subtotal + shipping;

        if (!customer.canAfford(total))
            throw new Exception("Insufficient balance!");

        // Deduct stock and customer balance
        for (CartItem item : items) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }
        customer.deduct(total);

        // Print receipt
        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.printf("%dx %s\t%.0f\n", item.getQuantity(), item.getProduct().getName(), item.getTotalPrice());
        }
        System.out.println("----------------------");
        System.out.printf("Subtotal\t%.0f\n", subtotal);
        System.out.printf("Shipping\t%.0f\n", shipping);
        System.out.printf("Amount\t\t%.0f\n", total);
        System.out.printf("Balance left\t%.0f\n", customer.getBalance());
    }
}

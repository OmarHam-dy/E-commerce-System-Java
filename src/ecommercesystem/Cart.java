/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommercesystem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TOSHIA
 */
public class Cart {
    private final List<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int qty) throws Exception {
        if (!product.isAvailable(qty))
            throw new Exception("Not enough quantity for " + product.getName());
        items.add(new CartItem(product, qty));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getSubtotal() {
        double sum = 0;
        for (CartItem item : items) {
            sum += item.getTotalPrice();
        }
        return sum;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public List<ShippableItem> getShippableItems() {
        List<ShippableItem> list = new ArrayList<>();
        for (CartItem item : items) {
            Product p = item.getProduct();
            if (p.isShippable()) {
                list.add(new ShippableItem() {
                    public String getName() { return item.getQuantity() + "x " + p.getName(); }
                    public double getWeight() { return p.getWeight() * item.getQuantity(); }
                });
            }
        }
        return list;
    }
}

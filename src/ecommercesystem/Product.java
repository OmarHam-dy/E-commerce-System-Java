/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecommercesystem;

import java.time.LocalDate;

/**
 *
 * @author TOSHIA
 */
public class Product {
    private String name;
    private double price;
    private int quantity;

    private boolean isExpirable;
    private LocalDate expiryDate;

    private boolean isShippable;
    private double weight;

    public Product(String name, double price, int quantity,
                   boolean isExpirable, LocalDate expiryDate,
                   boolean isShippable, double weight) {
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        this.isExpirable = isExpirable;
        this.expiryDate = expiryDate;
        this.isShippable = isShippable;
        setWeight(weight);
    }

    public boolean isExpired() {
        return isExpirable && LocalDate.now().isAfter(expiryDate);
    }

    public boolean isAvailable(int requestedQty) {
        return quantity >= requestedQty;
    }

    public void reduceQuantity(int qty) {
        quantity -= qty;
    }

    //--------------- Getters ---------------//
    public String getName() { 
        return name; 
    }
    public double getPrice() { 
        return price; 
    }
    public int getQuantity() { 
        return quantity; 
    }
    public boolean isShippable() { 
        return isShippable; 
    }
    public double getWeight() { 
        return weight; 
    }
    public boolean isExpirable() { 
        return isExpirable; 
    }
    public LocalDate getExpiryDate() { 
        return expiryDate; 
    }

    //--------------- Setters ---------------//
    public void setName(String name) {
        if (name == null || name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price must be non-negative");
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be non-negative");
        this.quantity = quantity;
    }

    public void setWeight(double weight) {
        if (isShippable && weight <= 0) throw new IllegalArgumentException("Weight must be positive");
        this.weight = weight;
    }
}

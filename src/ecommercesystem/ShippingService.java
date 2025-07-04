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
public class ShippingService {
     public static void ship(List<ShippableItem> items) {
        System.out.println("** Shipment notice **");
        double totalWeight = 0;
        for (ShippableItem item : items) {
            double weight = item.getWeight();
            System.out.printf("%s\t%.0fg\n", item.getName(), weight * 1000);
            totalWeight += weight;
        }
        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
}

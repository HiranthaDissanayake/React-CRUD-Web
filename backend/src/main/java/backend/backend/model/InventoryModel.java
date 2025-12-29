package backend.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private int quantity;
    private double price;

    // Constructors
    public InventoryModel() {}

    public InventoryModel(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

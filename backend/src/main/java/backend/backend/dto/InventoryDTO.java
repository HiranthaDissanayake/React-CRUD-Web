package backend.backend.dto;


import org.antlr.v4.runtime.misc.NotNull;

public class InventoryDTO {

    @NotNull
    private String itemName;

    private Integer quantity;


    private Double price;

    // Getters & Setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

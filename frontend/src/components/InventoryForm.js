import React, { useEffect, useState } from "react";
import "./InventoryForm.css";

const InventoryForm = ({ onSave, selectedItem }) => {
  const [itemName, setItemName] = useState("");
  const [quantity, setQuantity] = useState("");
  const [price, setPrice] = useState("");

  useEffect(() => {
    if (selectedItem) {
      setItemName(selectedItem.itemName);
      setQuantity(selectedItem.quantity);
      setPrice(selectedItem.price);
    }
  }, [selectedItem]);

  const handleSubmit = (e) => {
    e.preventDefault();

    onSave({
      itemName,
      quantity: Number(quantity),
      price: Number(price),
    });

    setItemName("");
    setQuantity("");
    setPrice("");
  };

  return (
    <form onSubmit={handleSubmit}>
      <h3>{selectedItem ? "Update Item" : "Add Item"}</h3>

      <input
        type="text"
        placeholder="Item Name"
        value={itemName}
        onChange={(e) => setItemName(e.target.value)}
        required
      />

      <input
        type="number"
        placeholder="Quantity"
        value={quantity}
        onChange={(e) => setQuantity(e.target.value)}
        required
      />

      <input
        type="number"
        placeholder="Price"
        value={price}
        onChange={(e) => setPrice(e.target.value)}
        required
      />

      <button type="submit">
        {selectedItem ? "Update" : "Save"}
      </button>
    </form>
  );
};

export default InventoryForm;

import React, { useEffect, useState } from "react";
import InventoryForm from "./components/InventoryForm";
import InventoryList from "./components/InventoryList";
import {
  getAllItems,
  createItem,
  updateItem,
  deleteItem,
} from "./services/inventoryService";

function App() {
  const [items, setItems] = useState([]);
  const [selectedItem, setSelectedItem] = useState(null);

  const loadItems = () => {
    getAllItems().then((res) => setItems(res.data));
  };

  useEffect(() => {
    loadItems();
  }, []);

  const handleSave = (item) => {
    if (selectedItem) {
      updateItem(selectedItem.id, item).then(() => {
        loadItems();
        setSelectedItem(null);
      });
    } else {
      createItem(item).then(() => loadItems());
    }
  };

  const handleDelete = (id) => {
    deleteItem(id).then(() => loadItems());
  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Inventory Management</h2>

      <InventoryForm
        onSave={handleSave}
        selectedItem={selectedItem}
      />

      <br />

      <InventoryList
        items={items}
        onEdit={setSelectedItem}
        onDelete={handleDelete}
      />
    </div>
  );
}

export default App;

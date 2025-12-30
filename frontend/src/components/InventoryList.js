import React, { useEffect, useState } from "react";
import {
  getAllItems,
  updateItem,
  deleteItem,
} from "../services/inventoryService";
import "./InventoryList.css";

function InventoryList() {
  const [items, setItems] = useState([]);
  const [editItem, setEditItem] = useState(null);

  useEffect(() => {
    loadItems();
  }, []);

  const loadItems = () => {
    getAllItems()
      .then((res) => setItems(res.data))
      .catch((err) => console.error(err));
  };

  const handleDelete = (id) => {
    if (window.confirm("Are you sure?")) {
      deleteItem(id).then(() => loadItems());
    }
  };

  const handleEdit = (item) => {
    setEditItem({ ...item });
  };

  const handleUpdate = () => {
    updateItem(editItem.id, editItem).then(() => {
      setEditItem(null);
      loadItems();
    });
  };

  return (
    <div className="container">
      <h2>📦 Inventory Items</h2>

      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Item Name</th>
            <th>Quantity</th>
            <th>Price (Rs)</th> {/* ⭐ */}
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {items.map((item) => (
            <tr key={item.id}>
              <td>{item.id}</td>
              <td>{item.itemName}</td>
              <td>{item.quantity}</td>
              <td>{item.price}</td> {/* ⭐ */}
              <td>
                <button
                  className="edit-btn"
                  onClick={() => handleEdit(item)}
                >
                  ✏ Edit
                </button>
                <button
                  className="delete-btn"
                  onClick={() => handleDelete(item.id)}
                >
                  🗑 Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Edit Form */}
      {editItem && (
        <div className="edit-box">
          <h3>Edit Item</h3>

          <input
            type="text"
            placeholder="Item Name"
            value={editItem.itemName}
            onChange={(e) =>
              setEditItem({ ...editItem, itemName: e.target.value })
            }
          />

          <input
            type="number"
            placeholder="Quantity"
            value={editItem.quantity}
            onChange={(e) =>
              setEditItem({ ...editItem, quantity: e.target.value })
            }
          />

          <input
            type="number"
            placeholder="Price"
            value={editItem.price}         
            onChange={(e) =>
              setEditItem({ ...editItem, price: e.target.value })
            }
          />

          <button className="save-btn" onClick={handleUpdate}>
            💾 Save
          </button>
          <button
            className="cancel-btn"
            onClick={() => setEditItem(null)}
          >
            ❌ Cancel
          </button>
        </div>
      )}
    </div>
  );
}

export default InventoryList;

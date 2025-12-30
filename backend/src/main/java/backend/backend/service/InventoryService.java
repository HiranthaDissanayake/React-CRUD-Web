package backend.backend.service;

import backend.backend.model.InventoryModel;

import java.util.List;

public interface InventoryService {

    InventoryModel createItem(InventoryModel item);

    List<InventoryModel> getAllItems();

    InventoryModel getItemById(Long id);

    InventoryModel updateItem(Long id, InventoryModel item);

    void deleteItem(Long id);
}

package backend.backend.service;

import backend.backend.exception.ResourceNotFoundException;
import backend.backend.model.InventoryModel;
import backend.backend.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryModel createItem(InventoryModel item) {
        return inventoryRepository.save(item);
    }

    @Override
    public List<InventoryModel> getAllItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public InventoryModel getItemById(Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found with id " + id));
    }

    @Override
    public InventoryModel updateItem(Long id, InventoryModel item) {
        InventoryModel existing = getItemById(id);

        existing.setItemName(item.getItemName());
        existing.setQuantity(item.getQuantity());
        existing.setPrice(item.getPrice());

        return inventoryRepository.save(existing);
    }

    @Override
    public void deleteItem(Long id) {
        InventoryModel item = getItemById(id);
        inventoryRepository.delete(item);
    }
}

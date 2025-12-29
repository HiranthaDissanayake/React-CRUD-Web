package backend.backend.controller;

import backend.backend.exception.ResourceNotFoundException;
import backend.backend.model.InventoryModel;
import backend.backend.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    // CREATE
    @PostMapping
    public InventoryModel createItem(@RequestBody InventoryModel inventory) {
        return inventoryRepository.save(inventory);
    }

    // READ ALL
    @GetMapping
    public List<InventoryModel> getAllItems() {
        return inventoryRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public InventoryModel getItemById(@PathVariable Long id) {
        return inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found with id: " + id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public InventoryModel updateItem(
            @PathVariable Long id,
            @RequestBody InventoryModel updatedItem) {

        InventoryModel item = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found with id: " + id));

        item.setItemName(updatedItem.getItemName());
        item.setQuantity(updatedItem.getQuantity());
        item.setPrice(updatedItem.getPrice());

        return inventoryRepository.save(item);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {

        InventoryModel item = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Item not found with id: " + id));

        inventoryRepository.delete(item);
        return "Item deleted successfully";
    }
}

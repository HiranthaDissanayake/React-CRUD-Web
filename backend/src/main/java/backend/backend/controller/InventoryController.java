package backend.backend.controller;

import backend.backend.dto.InventoryDTO;
import backend.backend.model.InventoryModel;
import backend.backend.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public InventoryModel create(@RequestBody InventoryDTO dto) {
        InventoryModel item = new InventoryModel(
                dto.getItemName(),
                dto.getQuantity(),
                dto.getPrice()
        );
        return inventoryService.createItem(item);
    }

    @GetMapping
    public List<InventoryModel> getAll() {
        return inventoryService.getAllItems();
    }

    @GetMapping("/{id}")
    public InventoryModel getById(@PathVariable Long id) {
        return inventoryService.getItemById(id);
    }

    @PutMapping("/{id}")
    public InventoryModel update(@PathVariable Long id, @RequestBody InventoryDTO dto) {

        InventoryModel item = new InventoryModel(
                dto.getItemName(),
                dto.getQuantity(),
                dto.getPrice()
        );

        return inventoryService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        inventoryService.deleteItem(id);
        return "Deleted successfully";
    }
}

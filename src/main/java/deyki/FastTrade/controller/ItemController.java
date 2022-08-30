package deyki.FastTrade.controller;

import deyki.FastTrade.domain.bindingModels.item.BuyItemBindingModel;
import deyki.FastTrade.domain.bindingModels.item.ItemBindingModel;
import deyki.FastTrade.domain.responseModels.item.ItemResponseModel;
import deyki.FastTrade.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemServiceImpl itemService;

    @Autowired
    public ItemController(ItemServiceImpl itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/createItem/{userId}")
    public ResponseEntity<String> createItem(@PathVariable Long userId, @RequestBody ItemBindingModel itemBindingModel) {

        itemService.createNewItem(userId, itemBindingModel);

        return ResponseEntity.status(HttpStatus.CREATED).body("Item created!");
    }

    @GetMapping("/getItems/{username}")
    public ResponseEntity<List<ItemResponseModel>> getItemsByOwnerUsername(@PathVariable String username) {

        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemsByOwnerUsername(username));
    }

    @GetMapping("/forSale")
    public ResponseEntity<List<ItemResponseModel>> getItemsForSale() {

        return ResponseEntity.status(HttpStatus.OK).body(itemService.getItemsForSale());
    }

    @GetMapping("/soldItems")
    public ResponseEntity<List<ItemResponseModel>> getSoldItems() {

        return ResponseEntity.status(HttpStatus.OK).body(itemService.getSoldItems());
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyItem(@RequestBody BuyItemBindingModel buyItemBindingModel) throws Exception {

        itemService.buyItem(buyItemBindingModel);

        return ResponseEntity.status(HttpStatus.OK).body("Item bought successfully!");
    }
}

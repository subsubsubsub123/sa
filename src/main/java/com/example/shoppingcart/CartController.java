package com.example.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    @Autowired
    private CartItemRepository repository;

    @GetMapping
    public List<CartItem> getAllItems() {
        return repository.findAll();
    }

    @PostMapping
    public CartItem addItem(@RequestBody CartItem item) {
        return repository.save(item);
    }

    @PutMapping("/{id}")
    public CartItem updateItem(@PathVariable Long id, @RequestBody CartItem updatedItem) {
        CartItem item = repository.findById(id).orElseThrow();
        item.setName(updatedItem.getName());
        item.setQuantity(updatedItem.getQuantity());
        item.setPrice(updatedItem.getPrice());
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
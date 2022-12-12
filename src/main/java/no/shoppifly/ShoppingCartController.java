package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
public class ShoppingCartController {

    private final CartService cartService;

    @Autowired
    public ShoppingCartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }

    @PostMapping(path = "/cart/checkout")
    @Timed
    public String checkout(@RequestBody Cart cart) {
        return cartService.checkout(cart);
    }

    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.update(cart);
    }

    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        return cartService.getAllsCarts();
    }
}
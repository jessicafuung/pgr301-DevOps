package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController()
public class ShoppingCartController implements ApplicationListener<ApplicationReadyEvent> {

    private final MeterRegistry meterRegistry;
    private final CartService cartService;

    @Autowired
    public ShoppingCartController(MeterRegistry meterRegistry, CartService cartService) {
        this.meterRegistry = meterRegistry;
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }

    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        meterRegistry.counter("checkouts");
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

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Gauge.builder("carts_count", cartService.getAllsCarts(), List::size)
               .register(meterRegistry);
    }
}
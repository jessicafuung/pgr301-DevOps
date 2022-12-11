package no.shoppifly;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class NaiveCartImpl implements CartService, ApplicationListener<ApplicationReadyEvent> {

    private final MeterRegistry meterRegistry;
    private final Map<String, Cart> shoppingCarts = new HashMap<>();
    private double counter = 0.0;

    NaiveCartImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public Cart getCart(String id) {
        return shoppingCarts.get(id);
    }

    @Override
    public Cart update(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(UUID.randomUUID().toString());
        }
        shoppingCarts.put(cart.getId(), cart);
        return shoppingCarts.put(cart.getId(), cart);
    }

    @Override
    public String checkout(Cart cart) {
        //meterRegistry.counter("checkouts").increment(counter);
        //meterRegistry.counter("checkouts").increment(counter);
        counter++;
        shoppingCarts.remove(cart.getId());
        return UUID.randomUUID().toString();
    }

    @Override
    public List<String> getAllsCarts() {
        return new ArrayList<>(shoppingCarts.keySet());
    }

    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Gauge.builder("carts_count", shoppingCarts, Map::size)
                .register(meterRegistry);

        Gauge.builder("cartsvalue", shoppingCarts, b -> {
            float total = b.values().stream()
                        .flatMap(c -> c.getItems().stream()
                                .map(i -> i.getUnitPrice() * i.getQty()))
                        .reduce(0f, Float::sum);
                    return total;
                }).register(meterRegistry);

        Gauge.builder("checkouts", counter, b -> b.doubleValue())
                .register(meterRegistry);
    }
}

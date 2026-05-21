package flipkartlocal.fahad_kart.Controller;


import flipkartlocal.fahad_kart.Entity.Cart;
import flipkartlocal.fahad_kart.Entity.Order;
import flipkartlocal.fahad_kart.Service.CartServiceImp;
import flipkartlocal.fahad_kart.Service.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/buy")
public class OrderController {


    @Autowired
    private CartServiceImp cartService;

    @Autowired
    private OrderServiceImp orderService;

    // CHECKOUT API
    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestParam int userId){

        // 1. Check Cart
        List<Cart> cartItems = cartService.getUserCart(userId);

        if(cartItems.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Cart is empty!");
        }

        // 2. Calculate Total
        double total = 0;
        for(Cart item : cartItems) {
            total += item.getProduct().getPrice();
        }

        // 3. Save Order
        Order order = new Order(
                userId,
                total,
                LocalDateTime.now()
        );

        Order savedOrder = orderService.saveOrder(order);

        // 4. Clear Cart
        cartService.removeFromCart(userId);

        // 5. Response
        Map<String, Object> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "Order placed successfully");
        response.put("orderId", savedOrder.getId());
        response.put("totalAmount", savedOrder.getTotalAmount());

        return ResponseEntity.ok(response);
    }


}

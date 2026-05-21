package flipkartlocal.fahad_kart.Controller;

import flipkartlocal.fahad_kart.Entity.Cart;
import flipkartlocal.fahad_kart.Service.CartServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartServiceImp c_serv;

    // 1. ADD TO CART
    @PostMapping("/add/{userId}/{productId}")
    public ResponseEntity<?> addToCart(@PathVariable int userId,
                                       @PathVariable int productId) {

        c_serv.addToCart(userId, productId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Product added successfully");
    }

    // 2. GET USER CART
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserCart(@PathVariable int userId) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(c_serv.getUserCart(userId));
    }

    // 3. DELETE CART ITEM
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<?> removeFromCart(@PathVariable int cartId) {

        c_serv.removeFromCart(cartId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Item removed successfully");
    }
}

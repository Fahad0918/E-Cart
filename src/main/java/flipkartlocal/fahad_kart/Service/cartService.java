package flipkartlocal.fahad_kart.Service;

import flipkartlocal.fahad_kart.Entity.Cart;

import java.util.List;

public interface cartService {
    void addToCart(int userId, int productId);

    List<Cart> getUserCart(int userId);

    void removeFromCart(int cartId);
}

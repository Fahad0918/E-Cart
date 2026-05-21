package flipkartlocal.fahad_kart.Service;

import flipkartlocal.fahad_kart.Entity.Cart;
import flipkartlocal.fahad_kart.Repository.cartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImp implements cartService{

    @Autowired
    private cartRepository c_repo;

    @Override
    public void addToCart(int userId,int productId){
        // FIX: Correct constructor usage
        Cart c_item = new Cart(userId, productId);
        c_repo.save(c_item);
    }
    @Override
    public List<Cart> getUserCart(int userId){
        // Assuming you have findByUserId defined in cartRepository
        return c_repo.findByUserId(userId);
    }

    @Override
    public void removeFromCart(int cartId){
        c_repo.deleteById(cartId);
    }
}
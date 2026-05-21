package flipkartlocal.fahad_kart.Repository;

import flipkartlocal.fahad_kart.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface cartRepository extends JpaRepository<Cart,Integer> {
    List<Cart>findByUserId(int userId);
}

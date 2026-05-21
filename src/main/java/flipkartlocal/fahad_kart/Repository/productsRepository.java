package flipkartlocal.fahad_kart.Repository;

import flipkartlocal.fahad_kart.Entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productsRepository extends JpaRepository<Products,Integer> {
    Products findByName(String name);
}

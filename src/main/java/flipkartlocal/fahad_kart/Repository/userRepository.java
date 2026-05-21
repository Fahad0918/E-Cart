package flipkartlocal.fahad_kart.Repository;

import flipkartlocal.fahad_kart.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);


}

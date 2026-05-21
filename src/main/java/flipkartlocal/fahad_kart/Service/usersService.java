package flipkartlocal.fahad_kart.Service;

import flipkartlocal.fahad_kart.Entity.Users;

import java.util.List;

public interface usersService {
    void registerUser(Users user);
    Users findByUsername(String username);
    List<Users> getAllProducts();
    Users loginUser(String username, String password);
}

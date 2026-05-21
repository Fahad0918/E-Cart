package flipkartlocal.fahad_kart.Service;

import flipkartlocal.fahad_kart.Entity.Users;
import flipkartlocal.fahad_kart.Repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class UsersServiceImp implements usersService{
    @Autowired
    private userRepository userrepo;

    @Override
    public List<Users> getAllProducts() {
        return userrepo.findAll();
    }
    @Override
    public void registerUser(Users user){

        userrepo.save(user);
    }
    @Override
    public  Users findByUsername(String username){
       return  userrepo.findByUsername(username);
    }

    public Users loginUser(String username, String password) {
        Users user = userrepo.findByUsername(username);

        if (user != null && user.getPassword().equalsIgnoreCase(password)) {
            return user;
        }

        return null;
    }


}

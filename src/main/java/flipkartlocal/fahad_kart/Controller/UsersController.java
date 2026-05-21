/*package flipkartlocal.fahad_kart.Controller;

import flipkartlocal.fahad_kart.Entity.Users;
import flipkartlocal.fahad_kart.Service.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")

public class UsersController {

    @Autowired
    private UsersServiceImp userservice;

    // ✅ Show registration page
    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new Users());
        return "redirect:/register.html"; // loads register.html
    }

    // ✅ Handle registration form submit
    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") Users user) {
        userservice.registerUser(user);
        return "redirect:/login.html";
    }


    // ✅ Show login page
    @GetMapping("/login")
    public String showLoginPage() {
        return "redirect:/login.html" ; // loads login.html
    }

    // ✅ Handle login form submit


    // ✅ Home page (after login)
    @GetMapping("/home")
    public String homePage(Model model) {
        return "redirect:/home.html"; // loads home.html
    }
    @GetMapping("/admin/dashboard")
    public List<Users> adminDashboard(){
        return userservice.getAllProducts();
    }
    @PostMapping("/login")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model) {

        Users user = userservice.loginUser(username, password);

        if (user != null) {

            if (user.getRole().equalsIgnoreCase("ADMIN")) {
                return "redirect:/products.html";
            } else {
                return "redirect:/productList.html";
            }

        } else {

            return "redirect:/register.html";
        }
    }

}*/

package flipkartlocal.fahad_kart.Controller;

import flipkartlocal.fahad_kart.Entity.Users;
import flipkartlocal.fahad_kart.Service.UsersServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersServiceImp userservice;

    // 1️⃣ REGISTER USER
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users user) {
        userservice.registerUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    // 2️⃣ LOGIN USER
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(
            @RequestParam String username,
            @RequestParam String password) {

        Users user = userservice.loginUser(username, password);

        if (user != null) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(user);
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }

    // 3️⃣ ADMIN – GET ALL USERS
    @GetMapping("/admin/dashboard")
    public ResponseEntity<?> adminDashboard() {
        List<Users> users = userservice.getAllProducts();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }
}

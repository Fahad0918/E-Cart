/*package flipkartlocal.fahad_kart.Controller;// ... imports ...

//import ch.qos.logback.core.model.Model;
import flipkartlocal.fahad_kart.Entity.Products;
import flipkartlocal.fahad_kart.Service.ProductsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductsServiceImp service;

    // API Endpoint for frontend to fetch product data (CORRECT)
    @GetMapping("/get-products")
    @ResponseBody
    public List<Products> getProducts(){
        return service.getAllProducts();
    }
    @GetMapping("/products")
    public String viewProduct(){
        // Yahan se hume exact static HTML file ka path use karke redirect karna padta hai
        // taaki page load ho sake.
        return "redirect:/products.html?userId=\" + userId;";
    }
    @GetMapping("/add-product")
    public String showAddProductPage(Model model){
        // Yahan bhi hume exact static HTML file ka path use karke redirect karna padta hai.
        return "redirect:/add-Product.html";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {

        service.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }


    @PutMapping("/update/{id}")
    public Products updateProduct(@PathVariable int id, @RequestBody Products productData) {

        Products p = service.getById(id);

        p.setName(productData.getName());
        p.setPrice(productData.getPrice());
        p.setImageUrl(productData.getImageUrl());

        return  service.save(p);
    }


    // Handles the form submission (CORRECT LOGIC)
    @PostMapping("/add-product")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("price") Double price, // FIX: Use Double to match Entity
                             @RequestParam("description") String description,
                             @RequestParam("image") MultipartFile image) throws IOException {

        // ... Image saving logic ...

        // FIX: The path must be accessible to the browser, not just the file system.
        // It's better to save relative to the project structure and serve from static.
        String uploadDir = "src/main/resources/static/product-images/";

        // ... (File saving logic is mostly fine, but the path is developer-specific, 
        // a production path would use absolute paths or external storage.)

        File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();

        Path filePath = Paths.get(uploadDir + fileName);
        Files.write(filePath, image.getBytes());


        Products p = new Products();
        p.setName(name);
        p.setPrice(price);
        p.setDescription(description);
        p.setImageUrl("product-images/" + fileName); // FIX: Save relative path for browser access

        service.addProduct(p);

        return "redirect:/products.html?userId=\" + userId;"; // Redirect to the static product listing page
    }

    // The /products GET mapping is now redundant and removed.
}
*/

package flipkartlocal.fahad_kart.Controller;

import flipkartlocal.fahad_kart.Entity.Products;
import flipkartlocal.fahad_kart.Service.ProductsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductsServiceImp service;

    // 1️⃣ GET ALL PRODUCTS
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Products> products = service.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 2️⃣ ADD PRODUCT WITH IMAGE
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody Products productData) {

        // Validate
        if(productData.getName() == null || productData.getName().isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Product name is required");
        }

        // Save product
        Products savedProduct = service.save(productData);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedProduct);
    }


    // 3️⃣ UPDATE PRODUCT
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable int id,
            @RequestBody Products data) {

        Products p = service.getById(id);

        if (p == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        p.setName(data.getName());
        p.setPrice(data.getPrice());
        p.setDescription(data.getDescription());
        p.setImageUrl(data.getImageUrl());

        return ResponseEntity.ok(service.save(p));
    }

    // 4️⃣ DELETE PRODUCT
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id) {

        service.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}


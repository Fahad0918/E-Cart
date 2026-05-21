package flipkartlocal.fahad_kart.Service;

import flipkartlocal.fahad_kart.Entity.Products;

import java.util.List;

public interface productService {
    void addProduct(Products product);
    List<Products>getAllProducts();
    Products getById(Integer id);
    void deleteById(Integer id);

}

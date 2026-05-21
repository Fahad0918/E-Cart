package flipkartlocal.fahad_kart.Service;

import flipkartlocal.fahad_kart.Entity.Products;
import flipkartlocal.fahad_kart.Repository.productsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductsServiceImp implements productService {

    @Autowired
    private productsRepository repo;

    @Override
    public void addProduct(Products p) {
        repo.save(p);
    }

    @Override
    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    @Override
    public Products getById(Integer id) {
        return repo.findById(id).orElse(null);
    }
    @Override
    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public Products save(Products p) {
        return repo.save(p);
    }

}

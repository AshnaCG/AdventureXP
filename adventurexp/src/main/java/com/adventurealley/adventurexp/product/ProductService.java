package com.adventurealley.adventurexp.product;

import com.adventurealley.adventurexp.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Varer/Produkter

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Vare ikke fundet: " + id));
    }

    public Product createProduct(Product product) {
        product.setId(null);
        validate(product);
        product.setCategory(findCategory(product));
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = getProduct(id);
        validate(updated);

        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        existing.setPrice(updated.getPrice());
        existing.setCategory(findCategory(updated));
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Vare ikke fundet: " + id);
        }
        productRepository.deleteById(id);
    }

    // Kategorier

    public List<ProductCategory> getAllCategories() {
        return categoryRepository.findAll();
    }

    public ProductCategory createCategory(ProductCategory category) {
        if (category.getName() == null || category.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kategorien skal have et navn");
        }
        category.setId(null);
        return categoryRepository.save(category);
    }

    // Hjælpemetoder til validering af navn, pris og kategori

    private void validate(Product product) {
        if (product.getName() == null || product.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Varen skal have et navn");
        }
        if (product.getPrice() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prisen kan ikke være negativ");
        }
        if (product.getCategory() == null || product.getCategory().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vælg en kategori");
        }
    }

    // Slår kategorien op i databasen for at verificere objektet fra requesten
    private ProductCategory findCategory(Product product) {
        Long categoryId = product.getCategory().getId();
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Kategori ikke fundet: " + categoryId));
    }
}
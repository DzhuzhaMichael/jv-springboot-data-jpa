package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Product product) {
        Product productFromDb
                = productRepository.findById(product.getId()).get();
        productFromDb.setTitle(product.getTitle());
        productFromDb.setPrice(product.getPrice());
        return productRepository.save(productFromDb);
    }

    @Override
    public List<Product> getAllByPriceBetween(BigDecimal priceFrom, BigDecimal priceTo) {
        return null;
    }

    @Override
    public List<Product> getAllInCategory(Category category) {
        return null;
    }
}

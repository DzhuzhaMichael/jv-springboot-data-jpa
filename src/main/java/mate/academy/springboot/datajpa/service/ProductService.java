package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.model.Product;

public interface ProductService {
    Product save(Product product);

    Product get(Long id);

    List<Product> getAll();

    void delete(Long id);

    Product update(Product product);

    List<Product> getAllByPriceBetween(BigDecimal priceFrom,
                                      BigDecimal priceTo);

    List<Product> findAllInCategoryIsIn(List<Long> ids);
}

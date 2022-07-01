package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.impl.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @Autowired
    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @GetMapping("/inject")
    public String saveProducts() {
        Category categoryPhone = new Category();
        categoryPhone.setName("phone");
        categoryService.save(categoryPhone);
        Category categoryLaptop = new Category();
        categoryLaptop.setName("laptop");
        categoryService.save(categoryLaptop);

        Product productOne = new Product();
        productOne.setTitle("iPhone 13");
        productOne.setPrice(BigDecimal.valueOf(1500));
        productOne.setCategory(categoryPhone);
        productService.save(productOne);

        Product productTwo = new Product();
        productTwo.setTitle("Samsung S21");
        productTwo.setPrice(BigDecimal.valueOf(1400));
        productTwo.setCategory(categoryPhone);
        productService.save(productTwo);

        Product productThree = new Product();
        productThree.setTitle("Nokia 3310");
        productThree.setPrice(BigDecimal.valueOf(400));
        productThree.setCategory(categoryPhone);
        productService.save(productThree);

        Product productFour = new Product();
        productFour.setTitle("Apple Macbook Pro");
        productFour.setPrice(BigDecimal.valueOf(2000));
        productFour.setCategory(categoryLaptop);
        productService.save(productFour);

        Product productFive = new Product();
        productFive.setTitle("Razer Blade Stealth");
        productFive.setPrice(BigDecimal.valueOf(1500));
        productFive.setCategory(categoryLaptop);
        productService.save(productFive);

        return "Done";
    }

    @PostMapping("/save")
    public ProductResponseDto saveProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = productService.save(productMapper.mapToModel(requestDto));
        return productMapper.mapToDto(product);
    }

    @GetMapping("/all")
    public List<ProductResponseDto> getAll() {
        List<Product> products = productService.getAll();
        return products
                .stream()
                .map(product -> productMapper.mapToDto(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.get(id);
        return productMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productService.get(id);
        product.setTitle(requestDto.getTitle());
        product.setPrice(requestDto.getPrice());
        product.setCategory(categoryService.get(requestDto.getCategoryId()));
        productService.save(product);
        return productMapper.mapToDto(product);
    }

    @GetMapping("all/by-price")
    public List<ProductResponseDto> getAllByPriceInBetween(@RequestParam BigDecimal priceFrom,
                                                           @RequestParam BigDecimal priceTo) {
        List<ProductResponseDto> responseDtoList = new ArrayList<>();
        List<Product> allProductsByPrice
                = productService.getAllByPriceBetween(priceFrom, priceTo);
        for (Product product : allProductsByPrice) {
            responseDtoList.add(productMapper.mapToDto(product));
        }
        return responseDtoList;
    }

    @GetMapping("/all/by-category")
    public List<ProductResponseDto> getAllByCategory(@RequestParam("ids") List<Long> ids) {
        List<Product> products
                = productService.findAllInCategoryIsIn(ids);
        return products
                .stream()
                .map(product -> productMapper.mapToDto(product))
                .collect(Collectors.toList());
    }
}

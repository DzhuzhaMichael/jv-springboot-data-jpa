# Spring Data JPA

Your task is:
- add required dependencies
- use H2 DB
- enable H2 console
- enable generated SQL statement logging
- create model `Product` with fields +
    - id
    - title
    - price
    - category (one product can have one category)
- create model `Category` with fields +
    - id
    - name
- create `ProductRepository` and `CategoryRepository` +
- create `ProductService` and `CategoryService` +
- create `ProductController`. Via the controller you must be able to:
    - create a new Product +
    - get Product by ID +
    - delete Product by ID +
    - update Product +
    - get all products where price is between two values received as a `RequestParam` inputs
    - get all products in categories 
        (you should think how you will receive a list of categories as a `RequestParam` input)
- create `CategoryController`. Via the controller you must be able to:
    - create a new Category
    - get Category by ID
    - delete Category by ID
    - update Category
- create required DTOs and mappers

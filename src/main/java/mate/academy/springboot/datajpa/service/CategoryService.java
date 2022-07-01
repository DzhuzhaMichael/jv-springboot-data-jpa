package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Category;

public interface CategoryService {
    Category save(Category category);

    Category get(Long id);

    void delete(Long id);
}

package tr.com.english.learnlang.service.category;

import tr.com.english.learnlang.entity.category.Category;

import java.util.List;

public interface CategoryService {
    void addCategory(Category category);
    List<Category> getCategories();
}

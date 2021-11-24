package tr.com.english.learnlang.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.english.learnlang.dao.CategoryDao;
import tr.com.english.learnlang.entity.category.Category;

import java.util.List;

@Service
public class CategoryServiceBean  implements CategoryService{
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void addCategory(Category category) {
        categoryDao.save(category);
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.findAll();
    }
}

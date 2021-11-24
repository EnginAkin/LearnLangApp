package tr.com.english.learnlang.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tr.com.english.learnlang.entity.category.Category;

@Repository
public interface CategoryDao extends JpaRepository<Category,Long> {
}

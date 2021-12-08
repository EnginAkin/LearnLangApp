package tr.com.english.learnlang.entity.responseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWordCategories {
        private String categoryName;
        private String categoryDescription;
        private int wordCount;
        private int dayCount;

}

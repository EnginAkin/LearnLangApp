package com.eng.learnlang.feature_main.domain.repository

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.util.Resource

interface CategoryRepository {
    suspend fun getCategoriesPost():Resource<List<Category>>
}
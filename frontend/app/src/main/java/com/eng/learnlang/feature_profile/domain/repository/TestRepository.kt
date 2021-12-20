package com.eng.learnlang.feature_profile.domain.repository

import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource

interface TestRepository {

    suspend fun getThreeWrongWordAnswer(id : Long): Resource<List<Word>>
}
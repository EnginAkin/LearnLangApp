package com.eng.learnlang.feature_main.domain.use_case

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.data.repository.WordsWrapper
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository

class GetWordsByWordListUseCase (
    private val repository: MainWordRepository
    ){
    suspend operator fun invoke(wrapper: WordsWrapper):Resource<List<Word>>{
        return repository.getWordsByWordList(wrapper);
    }
}
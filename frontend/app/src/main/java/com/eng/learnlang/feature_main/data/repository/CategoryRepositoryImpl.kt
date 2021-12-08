package com.eng.learnlang.feature_main.data.repository

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.util.Constants
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_auth.data.remote.request.Credential
import com.eng.learnlang.feature_main.data.remote.WordsApi
import com.eng.learnlang.feature_main.domain.repository.CategoryRepository
import retrofit2.HttpException
import java.io.IOException

class CategoryRepositoryImpl (
    private val api :WordsApi
        ) :CategoryRepository {
    override suspend fun getCategoriesPost(): Resource<List<Category>> {
        return try {
            val categories = api.getCategoriesWithInfo()
                Resource.Success(categories);
            }catch (e: IOException) {
            Resource.Error(
                message = "Birşeyler ters gitti ! Servere ulaşılamıyor"
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = "please try again e :" + e.localizedMessage
            )
        }
    }
}
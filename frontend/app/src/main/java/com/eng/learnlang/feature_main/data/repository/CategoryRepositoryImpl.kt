package com.eng.learnlang.feature_main.data.repository

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.TopicWordDay
import com.eng.learnlang.core.domain.model.Word
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
            println("fun getcategoriesPost girildi")
            val categories = api.getCategoriesWithInfo()
            println("cateogries : "+ categories[0])
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

    override suspend fun getWordsWithPaginationByCategoryName(
        categoryName: String,
        pageNumber: Int,
        limit: Int
    ): Resource<List<Word>> {
        return  try {
            val result = api.getWordsByCategoryNameAndpageble(categoryName,pageNumber,limit)
            Resource.Success(
                data = result
            )
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

    override suspend fun getWordsByCategoryName(categoryName: String): Resource<List<Word>> {
        return  try {
            val result = api.getWordsByCategoryName(categoryName)
            println("get words by category name : result size "+result.size)
            Resource.Success(
                data = result
            )
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

    override suspend fun getUsersLearnedWords(userId: Long): Resource<List<Word>> {
        return  try {
            val result = api.getUsersLearnedWords(userId)
            Resource.Success(
                data = result
            )
        }catch (e: IOException) {
            Resource.Error(
                message = "Birşeyler ters gitti ! Servere ulaşılamıyor"
            )
        } catch (e: HttpException) {
            Resource.Error(
                message = "please try again e :" + e.localizedMessage
            )
        } catch (e: Exception) {
            Resource.Error(
                message =e.localizedMessage
            )
        }
    }
}
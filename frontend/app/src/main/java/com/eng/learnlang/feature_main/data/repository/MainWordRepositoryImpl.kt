package com.eng.learnlang.feature_main.data.repository

import com.eng.learnlang.core.domain.model.Category
import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.core.util.SimpleResource
import com.eng.learnlang.feature_main.data.remote.WordsApi
import com.eng.learnlang.feature_main.domain.repository.MainWordRepository
import retrofit2.HttpException
import java.io.IOException

class MainWordRepositoryImpl (
    private val api :WordsApi
        ) :MainWordRepository {
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
                message = "Birşeyler ters gitti ! Servere ulaşılamıyor"+e.localizedMessage
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

    override suspend fun addUserLearnedWordList(wordId: Long, userId: Long) :SimpleResource{
        return  try {
           api.addUserLearnedWordList(wordId,userId)
            Resource.Success(
                data = Unit
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

    override suspend fun addUserWordList(wordId: Long, userId: Long) :SimpleResource{

        return  try {
            api.addUserWordList(wordId,userId)
            Resource.Success(
                data = Unit
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

    override suspend fun deleteUserWordListByWordId(wordId: Long,userId: Long): SimpleResource {

        return  try {
           api.deleteWordInUserWordList(wordId,userId)
            Resource.Success(
                data =Unit
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

    override suspend fun getUserWordList(userId: Long): Resource<List<Word>> {
        return  try {
            var result=api.getUserWordList(userId)
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
package com.eng.learnlang.feature_profile.data.repository

import com.eng.learnlang.core.domain.model.Word
import com.eng.learnlang.core.util.Resource
import com.eng.learnlang.feature_main.data.remote.WordsApi
import com.eng.learnlang.feature_profile.data.remote.TestApi
import com.eng.learnlang.feature_profile.domain.repository.TestRepository
import retrofit2.HttpException
import java.io.IOException

class TestRepositoryImpl (
    private val api : TestApi
        ): TestRepository {
    override suspend fun getThreeWrongWordAnswer(id: Long): Resource<List<Word>> {
        return try {
            var result = api.getThreeWrongWordAnswer(id)
            Resource.Success(
                data = result
            )
        }catch (e: IOException) {
            Resource.Error(
                message = "Servere ulaşılamıyor ${e.localizedMessage}"
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
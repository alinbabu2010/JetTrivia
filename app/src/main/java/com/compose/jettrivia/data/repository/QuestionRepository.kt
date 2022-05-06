package com.compose.jettrivia.data.repository

import android.util.Log
import com.compose.jettrivia.data.models.DataOrException
import com.compose.jettrivia.data.models.QuestionItem
import com.compose.jettrivia.network.QuestionApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestionRepository @Inject constructor(private val questionApi: QuestionApi) {

    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = questionApi.getAllQuestions()
            if (dataOrException.data.toString().isNotEmpty()) dataOrException.loading = false
        } catch (exception: Exception) {
            dataOrException.exception = exception
            Log.d("TAG", "getAllQuestions: ${dataOrException.exception?.localizedMessage}")
        }
        return dataOrException
    }

}
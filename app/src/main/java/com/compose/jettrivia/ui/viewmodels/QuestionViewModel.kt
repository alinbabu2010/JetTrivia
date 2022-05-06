package com.compose.jettrivia.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.jettrivia.data.models.DataOrException
import com.compose.jettrivia.data.models.QuestionItem
import com.compose.jettrivia.data.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val repository: QuestionRepository,
    private val data: MutableState<DataOrException<ArrayList<QuestionItem>,
            Boolean, Exception>> = mutableStateOf(DataOrException(null, true, Exception("")))
) : ViewModel() {

    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getAllQuestions()
            if (data.value.data.toString().isNotBlank()) {
                data.value.loading = false
            }
        }
    }


}
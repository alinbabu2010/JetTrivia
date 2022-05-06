package com.compose.jettrivia.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.jettrivia.ui.components.Questions
import com.compose.jettrivia.ui.viewmodels.QuestionViewModel

@Composable
fun TriviaHome(viewModel: QuestionViewModel = hiltViewModel()) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Questions(viewModel)
    }
}
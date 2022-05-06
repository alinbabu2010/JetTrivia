package com.compose.jettrivia.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import com.compose.jettrivia.data.models.QuestionItem
import com.compose.jettrivia.ui.theme.*
import com.compose.jettrivia.ui.viewmodels.QuestionViewModel

@Composable
fun Questions(viewModel: QuestionViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
        Log.d("TAG", "Loading...")
    } else {
        questions?.forEach {
            Log.d("TAG", "Questions: ${it.question}")
        }
    }
}

@Composable
fun QuestionDisplay(
    questionItem: QuestionItem,
    questionIndex: MutableState<Int>,
    viewModel: QuestionViewModel,
    onNextClicked: () -> Unit
) {

    val choicesState = remember(questionItem) {
        questionItem.choices.toMutableList()
    }

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(DimenPadding4),
        color = DarkPurple
    ) {
        Column(
            modifier = Modifier.padding(DimenPadding12),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            QuestionTracker()
            DrawDottedLine(pathEffect)

            Column {
                Text(
                    text = "Whats the meaning",
                    modifier = Modifier
                        .padding(DimenPadding4)
                        .align(Alignment.Start)
                        .fillMaxHeight(0.3f)
                        .fillMaxWidth(),
                    fontSize = DimenFontSize17,
                    fontWeight = FontWeight.Bold,
                    lineHeight = DimenFontSize22,
                    color = OffWhite
                )
            }

            //Choices
            choicesState.forEach {
                Row(
                    modifier = Modifier
                        .padding(DimenPadding3)
                        .fillMaxWidth()
                        .height(DimenHeight45)
                        .border(
                            width = DimenWidth4, brush = Brush.linearGradient(
                                colors = listOf(
                                    OffDarkPurple, OffDarkPurple
                                )
                            ), shape = RoundedCornerShape(DimenCorner15)
                        )
                        .clip(RoundedCornerShape(percent = 50))
                        .background(Color.Transparent)
                ) {

                }
            }

        }

    }
}

@Composable
fun QuestionTracker(counter: Int = 10, outOf: Int = 100) {

    val displayText = buildAnnotatedString {
        withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
            withStyle(
                style = SpanStyle(
                    color = LightGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = DimenFontSize27
                )
            ) {
                append("Question $counter/")
            }
            withStyle(
                style = SpanStyle(
                    color = LightGray,
                    fontWeight = FontWeight.Light,
                    fontSize = DimenFontSize14
                )
            ) {
                append("$outOf")
            }
        }
    }

    Text(text = displayText, modifier = Modifier.padding(DimenPadding20))
}
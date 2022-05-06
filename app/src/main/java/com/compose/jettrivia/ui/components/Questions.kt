package com.compose.jettrivia.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
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
        if (questions != null) {
            QuestionDisplay(questionItem = questions.first()) {

            }
        }
    }
}

@Composable
fun QuestionDisplay(
    questionItem: QuestionItem,
    //questionIndex: MutableState<Int>,
    //viewModel: QuestionViewModel,
    onNextClicked: () -> Unit
) {

    val choicesState = remember(questionItem) {
        questionItem.choices.toMutableList()
    }

    val answerState = remember(questionItem) {
        mutableStateOf<Int?>(null)
    }

    val correctAnswerState = remember(questionItem) {
        mutableStateOf<Boolean?>(null)
    }

    val updateAnswer: (Int) -> Unit = remember(questionItem) {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == questionItem.answer
        }

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
                    text = questionItem.question,
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
            choicesState.forEachIndexed { index, answerText ->
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
                        .background(Color.Transparent),
                    verticalAlignment = CenterVertically
                ) {

                    RadioButton(
                        selected = (answerState.value == index), onClick = {
                            updateAnswer(index)
                        }, modifier = Modifier.padding(DimenPadding16),
                        colors = RadioButtonDefaults.colors(
                            selectedColor = if (correctAnswerState.value == true) {
                                Color.Green.copy(alpha = 0.2f)
                            } else Color.Red.copy(alpha = 0.2f)
                        )
                    )

                    Text(text = answerText)

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
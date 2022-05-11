package com.compose.jettrivia.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.compose.jettrivia.ui.theme.LightPurple
import com.compose.jettrivia.ui.theme.OffPink
import com.compose.jettrivia.ui.theme.OffPurple
import com.compose.jettrivia.ui.theme.OffWhite
import com.compose.jettrivia.util.*

@Preview
@Composable
fun ShowProgress(score: Int = 12) {

    val gradient = Brush.linearGradient(listOf(OffPink, OffPurple))

    val progressFactor = remember(score) {
        mutableStateOf(score * 0.005f)
    }

    Row(
        modifier = Modifier
            .padding(DimenPadding3)
            .fillMaxWidth()
            .height(DimenHeight45)
            .border(
                width = DimenWidth4, brush = Brush.linearGradient(
                    colors = listOf(
                        LightPurple,
                        LightPurple
                    )
                ), shape = RoundedCornerShape(DimenCorner34)
            )
            .clip(RoundedCornerShape(percent = 50))
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(
            onClick = {}, contentPadding = PaddingValues(DimenPadding1),
            modifier = Modifier
                .fillMaxWidth(progressFactor.value)
                .background(brush = gradient),
            enabled = false, elevation = null,
            colors = buttonColors(
                backgroundColor = Color.Transparent,
                disabledBackgroundColor = Color.Transparent
            )
        ) {
            Text(
                text = (score).toString(), modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(DimenCorner23)
                    )
                    .fillMaxHeight(0.87f)
                    .fillMaxWidth()
                    .padding(DimenPadding6),
                color = OffWhite, textAlign = TextAlign.Center
            )
        }


    }


}
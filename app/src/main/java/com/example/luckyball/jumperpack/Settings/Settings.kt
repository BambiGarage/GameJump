package com.example.luckyball.jumperpack.Settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.luckyball.jumperpack.HelperFunsComp.ImgStatic
import com.example.luckyball.jumperpack.Rules.NewTopRow
import com.game.jump.R

@Composable
fun Settings() {
    var selectedDifficulty by remember { mutableStateOf(Difficulty.EASY) }


    Box(modifier = Modifier.fillMaxSize()) {

        ImgStatic(img = R.drawable.bgg)
        NewTopRow(modifier = Modifier.align(Alignment.TopCenter))
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            DifficultySwitch(
                text = "Easy difficulty level",
                difficulty = Difficulty.EASY,
                selectedDifficulty = selectedDifficulty,
                onDifficultySelected = { selectedDifficulty = it }
            )

            DifficultySwitch(
                text = "Medium level of difficulty",
                difficulty = Difficulty.MEDIUM,
                selectedDifficulty = selectedDifficulty,
                onDifficultySelected = { selectedDifficulty = it }
            )

            DifficultySwitch(
                text = "Hardcore difficulty level",
                difficulty = Difficulty.HARD,
                selectedDifficulty = selectedDifficulty,
                onDifficultySelected = { selectedDifficulty = it }
            )
        }


    }
}

@Composable
fun DifficultySwitch(
    text: String,
    difficulty: Difficulty,
    selectedDifficulty: Difficulty,
    onDifficultySelected: (Difficulty) -> Unit
) {
    val enabled = selectedDifficulty == difficulty



    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (enabled) Color.Yellow else Color.Transparent)
            .clickable(enabled = enabled) {
                if (enabled) {
                    onDifficultySelected(difficulty)
                    Settings.whatIs = difficulty.name
                }
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = enabled,
            onCheckedChange = { checked ->
                if (checked) {
                    onDifficultySelected(difficulty)
                }
            },
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = text,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 8.dp),
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Black,
                    blurRadius = 6.5f
                )
            )
        )
    }
}





    enum class Difficulty {
        EASY,
        MEDIUM,
        HARD
    }


object Settings {

    var whatIs = "EASY"

}

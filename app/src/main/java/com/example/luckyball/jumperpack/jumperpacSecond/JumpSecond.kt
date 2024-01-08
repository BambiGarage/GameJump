package com.example.luckyball.jumperpack.jumperpacSecond

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.luckyball.jumperpack.HelperFunsComp.ImgStatic
import com.game.jump.R

@Composable
fun JumpSecond(){

    ImgStatic(img = R.drawable.bgg)

    Text(text = "JumpSecond",
        fontSize = 30.sp,
        color = Color.White
    )


}
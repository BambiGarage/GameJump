package com.example.luckyball.jumperpack.Settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.luckyball.jumperpack.HelperFunsComp.ImgStatic
import com.example.luckyball.jumperpack.Rules.NewTopRow
import com.game.jump.R

@Composable
fun Settings() {

    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()){

        ImgStatic(img = R.drawable.bgg)
        NewTopRow(modifier = Modifier.align(Alignment.TopCenter))




    }


}
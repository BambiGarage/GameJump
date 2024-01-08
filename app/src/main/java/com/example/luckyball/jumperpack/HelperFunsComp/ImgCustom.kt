package com.example.luckyball.jumperpack.HelperFunsComp

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

@Composable
fun ImgCustom(img : Int, modifier: Modifier){

    Image(painter = painterResource(id = img), contentDescription = "img",
        contentScale = ContentScale.FillBounds, modifier = modifier)

}
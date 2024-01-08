package com.example.luckyball.jumperpack.HelperFunsComp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel

@Composable
fun ImgStatic(img : Int){

    Image(painter = painterResource(id = img), contentDescription = "img",
        contentScale = ContentScale.FillBounds, modifier = Modifier.fillMaxSize())
    
}



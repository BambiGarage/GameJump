package com.example.luckyball.jumperpack.Rules

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.luckyball.MenuActivity
import com.example.luckyball.jumperpack.HelperFunsComp.ImgCustom
import com.example.luckyball.jumperpack.HelperFunsComp.ImgStatic
import com.game.jump.R

@Composable
fun Rules(navController: NavHostController) {

    Box(modifier = Modifier
        .fillMaxSize()){
        ImgStatic(img = R.drawable.bgg)

        NewTopRow(modifier = Modifier.align(Alignment.TopCenter))

        Column(modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 90.dp)
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {

            ImgCustom(img = R.drawable.but_rules, modifier =Modifier
                .width(120.dp)
                .height(100.dp))

            ImgCustom(img = R.drawable.rules, modifier =Modifier
                .fillMaxWidth()
                .height(400.dp))




        }

    }


}


@Composable
fun NewTopRow(modifier: Modifier) {

    val contt = LocalContext.current
    Row(
        modifier = modifier
            .padding(top = 15.dp)
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        ImgCustom(
            img = R.drawable.menu_btn, modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxHeight()
                .width(45.dp)
                .clickable {
                    val inte = Intent(contt, MenuActivity::class.java)
                    contt.startActivity(inte)
                    (contt as Activity).overridePendingTransition(0, 0)
                    (contt as Activity).finish()
                }
        )

        ImgCustom(
            img = R.drawable.pouse, modifier = Modifier
                .fillMaxHeight()
                .width(45.dp)
                .alpha(0f)

        )


        PlusCoimFrameNew(
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxHeight()
                .alpha(0f)
                .width(130.dp)

        )


    }


}

@Composable
fun PlusCoimFrameNew(modifier: Modifier) {

    Box(
        modifier = modifier

    ) {

        Image(
            painter = painterResource(id = R.drawable.fone_bleu),
            contentDescription = "c",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
                .align(
                    Alignment.Center
                )
        )

        Image(
            painter = painterResource(id = R.drawable.plus),
            contentDescription = "c",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxHeight()
                .width(50.dp)
                .align(
                    Alignment.CenterStart
                )
        )
        Image(
            painter = painterResource(id = R.drawable.coin), contentDescription = "c",
            contentScale = ContentScale.FillBounds, modifier = Modifier

                .fillMaxHeight(0.5f)
                .width(25.dp)
                .align(
                    Alignment.Center
                )
        )


    }


}



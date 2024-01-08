package com.example.luckyball.jumperpack.jamperpacFirst

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.luckyball.JumpActivity
import com.example.luckyball.MenuActivity
import com.example.luckyball.jumperpack.HelperFunsComp.ImgCustom
import com.example.luckyball.jumperpack.HelperFunsComp.ImgStatic
import com.example.luckyball.jumperpack.Navigation.WhatIsActiv
import com.game.jump.R


@Composable
fun MainJumpFirst(
    navController: NavHostController,
    jumpViewModel: JumpFirstViewModel,
    firstState: JumpData,
) {

    val maxX = LocalConfiguration.current.screenWidthDp.dp



    LaunchedEffect(key1 = "null", block = {
        jumpViewModel.startPlatformMovement(maxX)
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ImgStatic(R.drawable.bgg)

        TopRow(modifier = Modifier.align(Alignment.TopCenter), firstState, maxX, jumpViewModel)






        repeat(firstState.offsetsHeight.size) { numberPlatform ->
            PlatformsMain(
                modifier = Modifier
                    .offset(
                        y = firstState.offsetsHeight[numberPlatform],
                        x = firstState.offsetsWidth[numberPlatform]
                    )
                    .height(35.dp)
                    .width(140.dp),
                firstState,
                numberPlatform

            )
        }
        repeat(6) { numberPlatform ->
            CoinsMain(
                modifier = Modifier
                    .offset(
                        y = firstState.offsetsHeight[numberPlatform] - 30.dp,
                        x = firstState.offsetsWidth[numberPlatform] + 70.dp - 7.5.dp
                    ),
                numberPlatform,
                firstState

            )
        }

        ImgCustom(
            img = R.drawable.big_ball,
            modifier = Modifier
                .offset(
                    x = firstState.offsetsWidth[firstState.targetPlatformIndex] + 50.dp,
                    y = firstState.ballPositionY - 35.dp
                )
                .size(50.dp)
        )



        BottomJump(modifier = Modifier
            .padding(bottom = 5.dp)
            .align(Alignment.BottomCenter)
            .clickable {

                jumpViewModel.jump()
            })

        if (firstState.gameOver) {
            NextLevel(firstState, jumpViewModel)
        }


    }


}


@Composable
fun PlatformsMain(modifier: Modifier, firstState: JumpData, numberPlatform: Int) {


    if (Level.levl == 1) {
        Image(
            painter = painterResource(id = R.drawable.platform),
            contentDescription = "platform",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
        )
    } else {

        Image(
            painter = painterResource(
                id = if (numberPlatform % 2 == 0) {
                    R.drawable.platform
                } else {
                    R.drawable.earth_platform
                }
            ),
            contentDescription = "platform",
            contentScale = ContentScale.FillBounds,
            modifier = modifier
        )


    }


}

@Composable
fun CoinsMain(modifier: Modifier, size: Int, firstState: JumpData) {
    Row(
        modifier = modifier
            .width(90.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {


        Image(
            painter = painterResource(id = R.drawable.coin),
            contentDescription = "coin",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(30.dp)
                .alpha(
                    when (size) {
                        5 -> firstState.alpha5
                        4 -> firstState.alpha4[0]
                        3 -> firstState.alpha3
                        2 -> firstState.alpha2[0]
                        1 -> firstState.alpha1
                        0 -> firstState.alpha0[0]
                        else -> {
                            1f
                        }
                    }
                )
        )

        if (size % 2 == 0) {

            Image(
                painter = painterResource(id = R.drawable.coin),
                contentDescription = "coin",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(30.dp)
                    .alpha(
                        when (size) {
                            4 -> firstState.alpha4[1]
                            2 -> firstState.alpha2[1]
                            0 -> firstState.alpha0[1]
                            else -> {
                                1f
                            }
                        }
                    )
            )
            Image(
                painter = painterResource(id = R.drawable.coin),
                contentDescription = "coin",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(30.dp)
                    .alpha(
                        when (size) {
                            4 -> firstState.alpha4[2]
                            2 -> firstState.alpha2[2]
                            0 -> firstState.alpha0[2]
                            else -> {
                                1f
                            }
                        }
                    )
            )
        }
    }
}

@Composable
fun BottomJump(modifier: Modifier) {

    Image(
        painter = painterResource(id = R.drawable.jump), contentDescription = "jump",
        contentScale = ContentScale.FillBounds, modifier = modifier
            .height(80.dp)
            .fillMaxWidth(0.4f)
    )


}

@Composable
fun TopRow(modifier: Modifier, firstState: JumpData, maxX: Dp, jumpViewModel: JumpFirstViewModel) {

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
                .clickable {

                    firstState.pousePressed = !firstState.pousePressed
                    if (!firstState.pousePressed) {
                        jumpViewModel.startPlatformMovement(maxX)
                    }

                }
        )


        PlusCoimFrame(
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxHeight()
                .width(130.dp)
                .clickable {

                },
            firstState
        )


    }


}

@Composable
fun PlusCoimFrame(modifier: Modifier, firstState: JumpData) {

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
        TextCustom(
            firstState.bank.toString(), modifier = Modifier
                .padding(end = 8.dp)
                .align(Alignment.CenterEnd)
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


@Composable
fun TextCustom(text: String, modifier: Modifier) {

    Text(
        text = text,
        fontSize = 20.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = modifier,
        style = TextStyle(
            shadow = Shadow(
                color = Color.Black,
                blurRadius = 5f
            )
        )
    )


}


@Composable
fun NextLevel(firstState: JumpData, jumpViewModel: JumpFirstViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.7f))
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(400.dp)
                .align(Alignment.Center)
        ) {

            ImgStatic(img = R.drawable.stars_fone)

            Column(
                modifier = Modifier
                    .padding(top = 130.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                ImgCustom(
                    img = R.drawable.level_compillleee, modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp)
                )
                PlusCoimFrame(
                    modifier = Modifier
                        .padding(end = 10.dp, top = 20.dp)
                        .height(50.dp)
                        .width(130.dp)
                        .align(CenterHorizontally), firstState
                )
                val contt = LocalContext.current
                ImgCustom(
                    img = R.drawable.next, modifier = Modifier
                        .offset(y = 50.dp)
                        .fillMaxWidth(0.8f)
                        .height(120.dp)
                        .align(CenterHorizontally)
                        .clickable {
                            WhatIsActiv.numberAct = "start"
                            Level.levl = if (Level.levl == 1) 2 else 1
                            val inte = Intent(contt, JumpActivity::class.java)
                            contt.startActivity(inte)



                        }
                )


            }
        }
    }
}

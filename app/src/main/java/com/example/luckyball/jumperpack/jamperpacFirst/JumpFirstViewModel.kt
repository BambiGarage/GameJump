package com.example.luckyball.jumperpack.jamperpacFirst

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JumpFirstViewModel : ViewModel() {

    val myStates = mutableStateOf(JumpData())

    init {


        myStates.value.alpha5 = 0f
        wwoooooorksses()

    }

    fun jump() {
        viewModelScope.launch {
            val currentIndex = myStates.value.targetPlatformIndex
            if (currentIndex > 0) {
                val targetY = myStates.value.offsetsHeight[currentIndex - 1]
                val newX = myStates.value.offsetsWidth[currentIndex - 1] + 50.dp


                myStates.value = myStates.value.copy(
                    ballPositionY = targetY - 5.dp,
                    targetPlatformIndex = currentIndex - 1
                )

                wwoooooorksses()
                delay(200)

                val isOnPlatform =
                    myStates.value.ballPositionY in (targetY - 25.dp)..(targetY + 25.dp) &&
                            myStates.value.offsetsWidth[currentIndex] in (newX - 90.dp)..(newX + 50.dp)

                if (isOnPlatform) {

                    myStates.value = myStates.value.copy(
                        ballPositionY = targetY,
                        offsetsWidth = myStates.value.offsetsWidth.toMutableList().apply {
                            this[currentIndex - 1] = newX
                        }
                    )
                    checkOnAlpha()
                } else {

                    myStates.value = myStates.value.copy(
                        ballPositionY = myStates.value.offsetsHeight.getOrElse(currentIndex - 1) { myStates.value.offsetsHeight.last() }
                    )
                }

                wwoooooorksses()
            } else {
                if (check0() && check1() && check2() && check3() && check4() && check5()) {
                    myStates.value.pousePressed = true
                    myStates.value.gameOver = true
                    wwoooooorksses()
                } else {
                    myStates.value.targetPlatformIndex = 5
                    myStates.value.ballPositionY = myStates.value.offsetsHeight.last()
                    wwoooooorksses()
                }

            }
        }
    }

    private fun check5(): Boolean {
        return myStates.value.alpha5 == 0f
    }

    private fun check4(): Boolean {
        return myStates.value.alpha4.all { it == 0f }
    }

    private fun check3(): Boolean {
        return myStates.value.alpha3 == 0f
    }

    private fun check2(): Boolean {
        return myStates.value.alpha2.all { it == 0f }
    }

    private fun check1(): Boolean {
        return myStates.value.alpha1 == 0f
    }

    private fun check0(): Boolean {
        return myStates.value.alpha0.all { it == 0f }
    }

    fun startPlatformMovement(maxX: Dp) {
        viewModelScope.launch {
            while (!myStates.value.pousePressed) {

                val newOffsetsWidth = myStates.value.offsetsWidth.mapIndexed { index, dp ->
                    val direction = myStates.value.offsetsDirection[index]
                    val maxOffset = maxX.value - 140f
                    var newOffset = dp.value + (direction * 1f)

                    if (newOffset >= maxOffset || newOffset <= 0f) {
                        myStates.value.offsetsDirection[index] = -direction
                        newOffset = newOffset.coerceIn(0f, maxOffset)
                    }

                    newOffset.dp
                }


                myStates.value = myStates.value.copy(offsetsWidth = newOffsetsWidth.toMutableList())


                wwoooooorksses()


                delay(10)
            }
        }
    }

    private fun checkOnAlpha() {

        when (myStates.value.targetPlatformIndex) {

            4 -> {

                myStates.value.alpha4.indexOfFirst { it == 1f }.takeIf { it != -1 }?.let {
                    myStates.value.alpha4[it] = 0f
                    wwoooooorksses()
                }
                myStates.value.bank += 500
                wwoooooorksses()
            }

            3 -> {
                myStates.value.alpha3 = 0f
                myStates.value.bank += 500
                wwoooooorksses()
            }

            2 -> {
                if (myStates.value.alpha2[0] == 1f) {
                    myStates.value.alpha2[0] = 0f
                    myStates.value.bank += 500

                    wwoooooorksses()
                } else {
                    if (myStates.value.alpha2[1] == 1f) {
                        myStates.value.alpha2[1] = 0f
                        myStates.value.bank += 500

                        wwoooooorksses()
                    } else {
                        myStates.value.alpha2[2] = 0f
                        myStates.value.bank += 500

                        wwoooooorksses()
                    }
                }

            }

            1 -> {
                myStates.value.alpha1 = 0f
                myStates.value.bank += 500

                wwoooooorksses()
            }

            0 -> {
                if (myStates.value.alpha0[0] == 1f) {
                    myStates.value.alpha0[0] = 0f
                    myStates.value.bank += 500

                    wwoooooorksses()
                } else {
                    if (myStates.value.alpha0[1] == 1f) {
                        myStates.value.alpha0[1] = 0f
                        myStates.value.bank += 500

                        wwoooooorksses()
                    } else {
                        myStates.value.alpha0[2] = 0f
                        myStates.value.bank += 500

                        wwoooooorksses()
                    }
                }
            }

        }


    }


    fun wwoooooorksses() {

        val copy = myStates.value.copy()
        copy.worring = copy.worring + 44.1
        myStates.value = copy

    }


}

data class JumpData(


    var level : Int = 1,
    var gameOver : Boolean = false,
    var bank: Int = 0,
    var alpha5: Float = 1f,
    var alpha4: MutableList<Float> = mutableListOf(
        1f, 1f, 1f
    ),
    var alpha3: Float = 1f,
    var alpha2: MutableList<Float> = mutableListOf(
        1f, 1f, 1f
    ),
    var alpha1: Float = 1f,
    var alpha0: MutableList<Float> = mutableListOf(
        1f, 1f, 1f
    ),

    var ballDiameter: Dp = 50.dp,
    var maxFallHeight: Dp = 1000.dp,
    var targetPlatformIndex: Int = 5,
    var ballPositionY: Dp = 630.dp,
    var pousePressed: Boolean = false,
    var offsetsDirection: MutableList<Float> = MutableList(6) { 1f },
    var offsetsWidth: MutableList<Dp> = mutableListOf(

        0.dp,
        150.dp,
        100.dp,
        200.dp,
        250.dp,
        80.dp,

        ),
    var offsetsHeight: MutableList<Dp> = mutableListOf(

        130.dp,
        230.dp,
        330.dp,
        430.dp,
        530.dp,
        630.dp,

        ),
    var worring: Double = 2.1,
    )


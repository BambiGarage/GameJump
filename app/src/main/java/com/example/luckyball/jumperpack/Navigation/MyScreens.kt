package com.example.luckyball.jumperpack.Navigation

sealed class MyScreens(val route: String) {
    object JumpFirst : MyScreens("jumpFirst")
    object JumpSecond : MyScreens("jumpSecond")
    object Rules : MyScreens("rules")
    object Privacy : MyScreens("privacy")
    object Settings : MyScreens("settings")
}
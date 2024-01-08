package com.example.luckyball

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.luckyball.jumperpack.Navigation.MyScreens
import com.example.luckyball.jumperpack.Navigation.WhatIsActiv
import com.example.luckyball.jumperpack.jumperpacSecond.JumpSecond
import com.example.luckyball.jumperpack.jamperpacFirst.JumpFirstViewModel
import com.example.luckyball.jumperpack.jamperpacFirst.MainJumpFirst
import com.example.luckyball.jumperpack.ToWeb.ToWeb
import com.example.luckyball.jumperpack.Rules.Rules
import com.example.luckyball.jumperpack.Settings.Settings



class JumpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val jumpViewModel: JumpFirstViewModel by viewModels()
            val navController = rememberNavController()
            val firstState by jumpViewModel.myStates

            NavHost(navController = navController, startDestination = getStartDestination()) {
                composable(MyScreens.JumpFirst.route) { MainJumpFirst(navController, jumpViewModel,firstState) }
                composable(MyScreens.JumpSecond.route) { JumpSecond() }
                composable(MyScreens.Rules.route) { Rules(navController) }
                composable(MyScreens.Privacy.route) { ToWeb(this@JumpActivity) }
                composable(MyScreens.Settings.route) { Settings() }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val inte = Intent(this, MenuActivity::class.java)
        startActivity(inte)
        overridePendingTransition(0, 0)
        finish()
    }

    private fun getStartDestination(): String {
        return when (WhatIsActiv.numberAct) {
            "start" -> MyScreens.JumpFirst.route
            "rules" -> MyScreens.Rules.route
            "toWeb" -> MyScreens.Privacy.route
            "settings" -> MyScreens.Settings.route
            else -> MyScreens.JumpFirst.route
        }
    }
}






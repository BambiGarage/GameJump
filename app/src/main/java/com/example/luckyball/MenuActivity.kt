package com.example.luckyball

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.luckyball.jumperpack.Navigation.WhatIsActiv
import com.game.jump.databinding.ActivityMenuBinding


class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.startBtn.setOnClickListener {

            WhatIsActiv.numberAct = "start"
            val inte = Intent(this, JumpActivity::class.java)
            startActivity(inte)
            overridePendingTransition(0, 0)
            this.finish()

        }
        binding.rulesBtn.setOnClickListener {

            WhatIsActiv.numberAct = "rules"
            val inte = Intent(this, JumpActivity::class.java)
            startActivity(inte)
            overridePendingTransition(0, 0)
            this.finish()

        }
        binding.privBtn.setOnClickListener {

            WhatIsActiv.numberAct = "toWeb"
            val inte = Intent(this, JumpActivity::class.java)
            startActivity(inte)
            overridePendingTransition(0, 0)
            this.finish()

        }

        binding.settBtn.setOnClickListener {

            WhatIsActiv.numberAct = "settings"
            val inte = Intent(this, JumpActivity::class.java)
            startActivity(inte)
            overridePendingTransition(0, 0)
            this.finish()

        }

        binding.exitBtn.setOnClickListener {


            this.finish()

        }



    }
}

package net.juzabel.yogasolotest.main

import android.os.Bundle
import net.juzabel.server.ServerActivity
import net.juzabel.yogasolotest.R

class MainActivity : ServerActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
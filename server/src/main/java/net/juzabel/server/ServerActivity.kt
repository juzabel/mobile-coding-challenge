package net.juzabel.server

import androidx.appcompat.app.AppCompatActivity
import net.juzabel.server.internal.Server

class ServerActivity: AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        Server.init(application)
    }

    override fun onPause() {
        super.onPause()
        Server.destroy()
    }
}
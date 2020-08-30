package net.juzabel.yogasolotest.core

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import net.juzabel.yogasolotest.R

class Navigator(private val context: Context, private val navController: NavController) {

    fun moveToPostureDetail(id: String){
        val args = Bundle()
        args.putString(context.getString(R.string.posture_id), id)

        navController.navigate(R.id.action_navigate_to_detail, args)
    }
}
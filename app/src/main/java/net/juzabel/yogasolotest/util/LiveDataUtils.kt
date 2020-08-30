package net.juzabel.yogasolotest.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <U : Any, T : LiveData<U>> Fragment.observe(liveData: T, observer : (value: U) -> Unit ) {
    liveData.observe(this.viewLifecycleOwner, Observer {
        observer.invoke(it)
    })
}
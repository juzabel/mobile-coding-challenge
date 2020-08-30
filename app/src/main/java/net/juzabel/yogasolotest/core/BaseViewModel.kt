package net.juzabel.yogasolotest.core

import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import net.juzabel.domain.core.Error

abstract class BaseViewModel : ViewModel() {
    val error: LiveEvent<Error> = LiveEvent()

    fun setError(error: Error){
        this.error.value = error
    }

}
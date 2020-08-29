package net.juzabel.yogasolotest.posturelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.juzabel.domain.core.Error
import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturelist.model.Posture

class PostureListViewModel(private val useCase: UseCase<Unit, Result<List<Posture>>>) :
    ViewModel() {

    val listPostures: MutableLiveData<List<Posture>> = MutableLiveData<List<Posture>>()
    val error: LiveEvent<Error> = LiveEvent()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            var value: Result<List<Posture>> = useCase.invoke(Unit)

            withContext(Dispatchers.Main) {
                when (value) {
                    is Result.Ok -> listPostures.value = value.value
                    is Result.Err -> error.value = value.err
                }
            }
        }
    }
}
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
import net.juzabel.domain.feature.posturedetail.model.PostureDetail
import net.juzabel.domain.feature.posturelist.model.Posture

class PostureListViewModel(
    private val useCase: UseCase<Unit, Result<List<Posture>>>,
    private val detailUseCase: UseCase<Unit, Result<List<PostureDetail>>>
) : ViewModel() {

    val listPostures: MutableLiveData<List<Posture>> = MutableLiveData<List<Posture>>()
    val error: LiveEvent<Error> = LiveEvent()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            getPostureList()
            getPostureDetailList()
        }
    }

    private suspend fun getPostureList() {
        val value: Result<List<Posture>> = useCase.invoke(Unit)

        withContext(Dispatchers.Main) {
            when (value) {
                is Result.Ok -> listPostures.value = value.value
                is Result.Err -> error.value = value.err
            }
        }
    }

    private suspend fun getPostureDetailList() {
        val value: Result<List<PostureDetail>> = detailUseCase.invoke(Unit)

        withContext(Dispatchers.Main){
            if(value is Result.Err)
                error.value = value.err
        }
    }
}
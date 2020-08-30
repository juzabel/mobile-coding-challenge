package net.juzabel.yogasolotest.feature.posturelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturedetail.model.PostureDetail
import net.juzabel.domain.feature.posturelist.model.Posture
import net.juzabel.yogasolotest.core.BaseViewModel

class PostureListViewModel(
    private val useCase: UseCase<Unit, Result<List<Posture>>>,
    private val cacheDetailListUseCase: UseCase<Unit, Result<List<PostureDetail>>>
) : BaseViewModel() {

    val listPostures: MutableLiveData<List<Posture>> = MutableLiveData<List<Posture>>()

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
                is Result.Err -> setError(value.err)
            }
        }
    }

    private suspend fun getPostureDetailList() {
        val value: Result<List<PostureDetail>> = cacheDetailListUseCase.invoke(Unit)

        withContext(Dispatchers.Main){
            if(value is Result.Err)
                setError(value.err)
        }
    }
}
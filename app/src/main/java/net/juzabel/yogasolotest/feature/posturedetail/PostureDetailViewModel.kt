package net.juzabel.yogasolotest.feature.posturedetail

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
import net.juzabel.yogasolotest.core.BaseViewModel

class PostureDetailViewModel(private val id: String, private val useCase: UseCase<String, Result<PostureDetail>>) : BaseViewModel() {

    val postureDetail : MutableLiveData<PostureDetail> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val result = useCase.invoke(id)
            withContext(Dispatchers.Main) {
                when(result) {
                    is Result.Ok -> postureDetail.value = result.value
                    is Result.Err -> setError(result.err)
                }
            }
        }
    }
}
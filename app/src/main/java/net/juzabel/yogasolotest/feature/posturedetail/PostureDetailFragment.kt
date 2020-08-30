package net.juzabel.yogasolotest.feature.posturedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import kotlinx.android.synthetic.main.posture_detail_fragment.*
import net.juzabel.domain.core.Error
import net.juzabel.domain.feature.posturedetail.model.PostureDetail
import net.juzabel.yogasolotest.R
import net.juzabel.yogasolotest.util.observe
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostureDetailFragment : Fragment() {

    private val viewModel: PostureDetailViewModel by viewModel {
        parametersOf(
            this.arguments?.get(
                getString(R.string.posture_id)
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posture_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postureDetailProgressBar.show()
        subscribeData()
    }

    private fun subscribeData() {
        observe(viewModel.postureDetail) {
            setUi(it)
        }

        observe(viewModel.error) {
            Toast.makeText(context, (it as Error.GenericError).message, Toast.LENGTH_LONG).show()
        }
    }

    private fun setUi(postureDetail: PostureDetail?) {
        postureDetail?.let { detail ->
            postureDetailImage.load(detail.picture)
            postureDetailTitle.text = detail.name
            postureDetailTeacherValue.text = detail.teacher
            postureDetaildurationValue.text = detail.duration
            postureDetailDescription.text = detail.description
        }
        postureDetailProgressBar.hide()
    }

    companion object {
        const val ID = "id"
    }
}
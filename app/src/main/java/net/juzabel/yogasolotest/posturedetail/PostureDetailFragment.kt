package net.juzabel.yogasolotest.posturedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import net.juzabel.domain.core.Error
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
        subscribeData()
    }

    private fun subscribeData() {
        observe(viewModel.postureDetail) {

        }

        observe(viewModel.error) {
            Toast.makeText(context, (it as Error.GenericError).message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val ID = "id"
    }
}
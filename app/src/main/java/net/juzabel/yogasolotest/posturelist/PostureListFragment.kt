package net.juzabel.yogasolotest.posturelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.posture_list_fragment.*
import net.juzabel.domain.core.Error
import net.juzabel.domain.di.DomainModule
import net.juzabel.yogasolotest.R
import net.juzabel.yogasolotest.core.Navigator
import net.juzabel.yogasolotest.util.observe
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named


class PostureListFragment : Fragment() {

    private val viewModel: PostureListViewModel by viewModel()

    private val navigator: Navigator by inject {
        parametersOf(
            activity?.applicationContext,
            findNavController()
        )
    }

    private lateinit var postureListAdapter: PostureListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posture_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUi()
        subscribeData()
    }

    private fun setUi() {
        postureListAdapter = PostureListAdapter() {
            navigator.moveToPostureDetail(it)
        }

        fragmentPostureListRecyclerView.apply {
            adapter = postureListAdapter
            val manager = LinearLayoutManager(context)
            manager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = manager
        }
    }

    private fun subscribeData() {
        observe(viewModel.listPostures) {list ->
            postureListAdapter.list = list
            postureListAdapter.notifyDataSetChanged()
        }
        observe(viewModel.error) {
            Toast.makeText(context, (it as Error.GenericError).message, Toast.LENGTH_LONG).show()
        }
    }
}
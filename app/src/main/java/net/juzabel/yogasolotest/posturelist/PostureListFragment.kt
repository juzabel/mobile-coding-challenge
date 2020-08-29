package net.juzabel.yogasolotest.posturelist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.posture_list_fragment.*
import net.juzabel.domain.core.Error
import net.juzabel.yogasolotest.R
import net.juzabel.yogasolotest.util.observe
import org.koin.android.viewmodel.ext.android.viewModel


class PostureListFragment : Fragment() {

    private val viewModel: PostureListViewModel by viewModel()

    private val postureListAdapter: PostureListAdapter = PostureListAdapter()

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
package net.juzabel.yogasolotest.posturelist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.juzabel.yogasolotest.R
import org.koin.android.viewmodel.ext.android.viewModel


class PostureListFragment : Fragment() {

    private val viewModel: PostureListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.posture_list_fragment, container, false)
    }

}
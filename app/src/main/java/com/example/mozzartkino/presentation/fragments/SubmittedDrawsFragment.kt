package com.example.mozzartkino.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mozzartkino.R
import com.example.mozzartkino.databinding.FragmentSubmitedDrawsBinding
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.presentation.adapters.KinoAdapter
import com.example.mozzartkino.presentation.fragments.util.SubmittedDrawsFragmentUtils
import com.example.mozzartkino.presentation.view_models.KinoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SubmittedDrawsFragment : Fragment(), SubmittedDrawsFragmentUtils {
    private lateinit var binding: FragmentSubmitedDrawsBinding
    private val viewModel: KinoViewModel by viewModels()

    @Inject
    lateinit var kinoAdapter: KinoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submited_draws, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSubmitedDrawsBinding.bind(view)
        kinoAdapter.setOnItemClickListener {
            navigateToInfo(it)
        }
        initRecyclerView()
        getDrawsList()
    }

    override fun initRecyclerView() {
        binding.rvSavedDraws.run {
            adapter = kinoAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun navigateToInfo(draw: Draw) {
        val bundle = Bundle().apply {
            putSerializable("selected_draw", draw)
            putString("from", "Submited_Draws")
        }
        findNavController().navigate(R.id.action_submitedDrawsFragment_to_infoFragment, bundle)
    }

    override fun getDrawsList() {
        viewModel.getSavedDraws().onEach {
            kinoAdapter.differ.submitList(it)
        }.launchIn(lifecycleScope)
    }
}
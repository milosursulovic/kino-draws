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
import com.example.mozzartkino.presentation.adapters.KinoAdapter
import com.example.mozzartkino.presentation.view_models.KinoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SubmittedDrawsFragment : Fragment() {
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
            val bundle = Bundle().apply {
                putSerializable("selected_draw", it)
                putString("from", "Submited_Draws")
            }
            findNavController().navigate(R.id.action_submitedDrawsFragment_to_infoFragment, bundle)
        }
        initRecyclerView()
        getSavedDrawsList()
    }

    private fun initRecyclerView() {
        binding.rvSavedDraws.run {
            adapter = kinoAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun getSavedDrawsList() {
        viewModel.getSavedDraws().onEach {
            kinoAdapter.differ.submitList(it)
        }.launchIn(lifecycleScope)
    }
}
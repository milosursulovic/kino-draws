package com.example.mozzartkino.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mozzartkino.R
import com.example.mozzartkino.databinding.FragmentDrawsBinding
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.presentation.adapters.KinoAdapter
import com.example.mozzartkino.presentation.draws.DrawEvent
import com.example.mozzartkino.presentation.fragments.util.DrawsFragmentUtils
import com.example.mozzartkino.presentation.view_models.KinoViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DrawsFragment : Fragment(), DrawsFragmentUtils {
    private lateinit var binding: FragmentDrawsBinding
    private val viewModel: KinoViewModel by viewModels()

    @Inject
    lateinit var kinoAdapter: KinoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_draws, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDrawsBinding.bind(view)
        kinoAdapter.setOnItemClickListener {
            navigateToInfo(it)
        }
        initRecyclerView()
        getDrawsList()
    }

    override fun initRecyclerView() {
        binding.rvDraws.run {
            adapter = kinoAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun navigateToInfo(draw: Draw) {
        val bundle = Bundle().apply {
            putSerializable("selected_draw", draw)
            putString("from", "Draws")
        }
        findNavController().navigate(R.id.action_drawsFragment_to_infoFragment, bundle)
    }

    override fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvDraws.visibility = View.GONE
        binding.drawTimeLabel.visibility = View.GONE
        binding.leftTimeLabel.visibility = View.GONE
    }

    override fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.rvDraws.visibility = View.VISIBLE
        binding.drawTimeLabel.visibility = View.VISIBLE
        binding.leftTimeLabel.visibility = View.VISIBLE
    }

    override fun getDrawsList() {
        viewModel.setStateChangeListener { state ->
            when (state.isLoading) {
                true -> {
                    showProgressBar()
                }
                false -> {
                    hideProgressBar()
                    if (state.error != null) {
                        Toast.makeText(activity, state.error, Toast.LENGTH_SHORT).show()
                    } else {
                        kinoAdapter.differ.submitList(state.draws)
                    }
                }
            }
        }
        viewModel.triggerEvent(DrawEvent.GetDraws)
    }
}
package com.example.mozzartkino.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mozzartkino.R
import com.example.mozzartkino.data.model.results.toDraw
import com.example.mozzartkino.data.util.Resource
import com.example.mozzartkino.databinding.FragmentResultsBinding
import com.example.mozzartkino.presentation.activities.MainActivity
import com.example.mozzartkino.presentation.adapters.KinoAdapter
import com.example.mozzartkino.presentation.view_models.KinoViewModel

class ResultsFragment : Fragment() {
    private lateinit var binding: FragmentResultsBinding
    private lateinit var viewModel: KinoViewModel
    private lateinit var kinoAdapter: KinoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResultsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        kinoAdapter = (activity as MainActivity).kinoAdapter
        kinoAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_draw", it)
                putString("from", "Results")
            }
            findNavController().navigate(R.id.action_resultsFragment_to_infoFragment, bundle)
        }
        initRecyclerView()
        getDrawsList()
    }

    private fun initRecyclerView() {
        binding.rvResults.run {
            adapter = kinoAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.rvResults.visibility = View.GONE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.rvResults.visibility = View.VISIBLE
    }

    private fun getDrawsList() {
        viewModel.getResults()
        viewModel.results.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        val mappedList = it.content.map { dto -> dto.toDraw() }
                        kinoAdapter.differ.submitList(mappedList)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let {
                        Toast.makeText(
                            activity,
                            "${resources.getString(R.string.error_occurred)}: $it",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }
        }
    }
}
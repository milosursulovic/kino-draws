package com.example.mozzartkino.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mozzartkino.R
import com.example.mozzartkino.databinding.FragmentInfoBinding
import com.example.mozzartkino.presentation.activities.MainActivity
import com.example.mozzartkino.presentation.adapters.NumbersAdapter
import com.example.mozzartkino.presentation.view_models.KinoViewModel
import javax.inject.Inject

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var viewModel: KinoViewModel
    private lateinit var numbersAdapter: NumbersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)
        val args: InfoFragmentArgs by navArgs()
        val draw = args.selectedDraw

        viewModel = (activity as MainActivity).viewModel
        numbersAdapter = (activity as MainActivity).numbersAdapter
        numbersAdapter.setOnItemClickListener {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }
        initRecyclerView()
        buildNumbersList()
    }

    private fun initRecyclerView() {
        binding.rvNumbers.run {
            adapter = numbersAdapter
            layoutManager = GridLayoutManager(activity, 4)
        }
    }

    private fun buildNumbersList() {
        val numbersList = mutableListOf<String>()
        for (i in 1..80) {
            numbersList.add(i.toString())
        }
        numbersAdapter.differ.submitList(numbersList)
    }
}
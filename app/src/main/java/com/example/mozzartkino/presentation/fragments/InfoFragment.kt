package com.example.mozzartkino.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mozzartkino.R
import com.example.mozzartkino.databinding.FragmentInfoBinding
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.presentation.activities.MainActivity
import com.example.mozzartkino.presentation.adapters.NumbersAdapter
import com.example.mozzartkino.presentation.view_models.KinoViewModel

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var viewModel: KinoViewModel
    private lateinit var numbersAdapter: NumbersAdapter
    private lateinit var from: From
    private lateinit var draw: Draw
    private val chosenNumbers = mutableListOf<String>()

    private enum class From {
        Draws,
        SubmitedDraws,
        Results
    }

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
        from = when (args.from) {
            "Draws" -> From.Draws
            "Submited_Draws" -> From.SubmitedDraws
            else -> From.Results
        }
        draw = args.selectedDraw

        binding.tvDraw.run {
            visibility = View.VISIBLE
            text = draw.drawId.toString()
        }

        viewModel = (activity as MainActivity).viewModel
        numbersAdapter = (activity as MainActivity).numbersAdapter
        numbersAdapter.setOnItemClickListener { number, textView ->
            if (chosenNumbers.isEmpty() or !chosenNumbers.contains(number) || chosenNumbers.size < 8) {
                chosenNumbers.add(number)
                textView.setBackgroundResource(R.drawable.on_number_selected)
            } else {
                chosenNumbers.remove(number)
                textView.setBackgroundResource(0)
            }
        }
        initRecyclerView()
        buildNumbersList()
        buttonsListeners()
    }

    private fun buttonsListeners() {
        val action = when (from) {
            From.Draws -> R.id.action_infoFragment_to_drawsFragment
            From.SubmitedDraws -> R.id.action_infoFragment_to_submitedDrawsFragment
            else -> R.id.action_infoFragment_to_resultsFragment
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(action)
        }
        binding.btnSubmit.setOnClickListener {
            if (chosenNumbers.size < 8) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.enter_eight_numbers),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.saveDraw(
                    Draw(
                        drawId = draw.drawId,
                        drawTime = draw.drawTime,
                        submitedNumbers = chosenNumbers.toString()
                    )
                )
                Toast.makeText(
                    activity,
                    resources.getString(R.string.successfully_submited),
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(action)
            }
        }
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
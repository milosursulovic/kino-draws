package com.example.mozzartkino.presentation.fragments

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mozzartkino.R
import com.example.mozzartkino.databinding.FragmentInfoBinding
import com.example.mozzartkino.databinding.NumberItemBinding
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
    private var chosenNumbers = mutableListOf<String>()
    private var quota = 0.0

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
            handleOnClick(number, textView)
        }
        initRecyclerView()
        buildNumbersList()
        buttonsListeners()
        checkSubmitedDraw()
    }

    private fun handleOnClick(number: String, textView: TextView) {
        if (from == From.Draws) {
            chosenNumbers.run {
                if (isEmpty()) {
                    add(number)
                    incrementQuota()
                    textView.setBackgroundResource(R.drawable.on_number_selected)
                } else {
                    if (contains(number)) {
                        remove(number)
                        textView.setBackgroundResource(0)
                    } else {
                        if (size < 8) {
                            add(number)
                            incrementQuota()
                            textView.setBackgroundResource(R.drawable.on_number_selected)
                        } else {
                            Toast.makeText(activity, resources.getString(R.string.cant_enter_more_than_8), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun incrementQuota() {
        quota = when (chosenNumbers.size) {
            1 -> 4.00
            2 -> 18.00
            3 -> 85.00
            4 -> 400.00
            5 -> 2000.00
            6 -> 10000.00
            7 -> 50000.00
            8 -> 250000.00
            else -> {
                binding.tvQuota.visibility = View.GONE
                0.0
            }
        }
        binding.tvQuota.run {
            visibility = View.VISIBLE
            text = if (quota > 85.0) quota.toInt().toString() else quota.toString()
        }
    }

    private fun checkSubmitedDraw() {
        if (from != From.Draws) {
            binding.btnSubmit.visibility = View.GONE
            binding.btnCancel.text = resources.getString(R.string.close)
            binding.tvQuota.run {
                visibility = View.VISIBLE
                text = if (draw.quota > 85.0) draw.quota.toInt().toString() else draw.quota.toString()
            }
            chosenNumbers = draw.submitedNumbers.split(":") as MutableList<String>
            numbersAdapter.differ.submitList(chosenNumbers)
        }
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
            if (chosenNumbers.size == 0) {
                Toast.makeText(
                    activity,
                    resources.getString(R.string.enter_at_least_one),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                viewModel.saveDraw(
                    Draw(
                        drawId = draw.drawId,
                        drawTime = draw.drawTime,
                        submitedNumbers = chosenNumbers.joinToString(":"),
                        quota = quota
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
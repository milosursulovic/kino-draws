package com.example.mozzartkino.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mozzartkino.databinding.NumberItemBinding

class NumbersAdapter : RecyclerView.Adapter<NumbersAdapter.ViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NumberItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val number = differ.currentList[position]
        holder.bind(number)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: NumberItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(differ.currentList[adapterPosition], binding.tvNumber)
                }
            }
        }

        fun bind(number: String) {
            binding.tvNumber.text = number
        }
    }

    private var onItemClickListener: ((String, TextView) -> Unit)? = null

    fun setOnItemClickListener(listener: (String, TextView) -> Unit) {
        onItemClickListener = listener
    }
}
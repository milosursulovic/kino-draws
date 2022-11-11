package com.example.mozzartkino.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mozzartkino.databinding.DrawItemBinding
import com.example.mozzartkino.domain.model.Draw
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*

class KinoAdapter : RecyclerView.Adapter<KinoAdapter.ViewHolder>() {

    companion object {
        const val TAG = "debugTag"
    }

    private val callback = object : DiffUtil.ItemCallback<Draw>() {
        override fun areItemsTheSame(oldItem: Draw, newItem: Draw): Boolean {
            return oldItem.drawId == newItem.drawId
        }

        override fun areContentsTheSame(oldItem: Draw, newItem: Draw): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DrawItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val draw = differ.currentList[position]
        holder.bind(draw)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class ViewHolder(private val binding: DrawItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var leftTime: TextView
        private lateinit var drawTime: Date

        fun bind(draw: Draw) {
            drawTime = Date(draw.drawTime)
            val time = convertLongToTime(false, draw.drawTime)

            binding.drawTime.text = time
            leftTime = binding.leftTime

            CoroutineScope(Dispatchers.IO).launch {
                while (true) {
                    val currentTime = Date(System.currentTimeMillis())
                    if (currentTime.time <= drawTime.time) {
                        val leftTimeFormatted =
                            convertLongToTime(true, drawTime.time - currentTime.time)
                        val splited = leftTimeFormatted.split(":")
                        val newLeftTimeFormatted = if (splited[0].toInt() == 1) {
                            "${splited[1]}:${splited[2]}"
                        } else {
                            val newValue = splited[0].toInt() - 1
                            "${newValue}:${splited[1]}:${splited[2]}"
                        }
                        withContext(Dispatchers.Main) {
                            leftTime.text = newLeftTimeFormatted
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            leftTime.text = "Gotovo!"
                        }
                        break
                    }
                    delay(1000)
                }
            }

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(draw)
                }
            }
        }
    }

    fun convertLongToTime(isTimer: Boolean, time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(if (isTimer) "HH:mm:ss" else "HH:mm", Locale.getDefault())
        return format.format(date)
    }

    private var onItemClickListener: ((Draw) -> Unit)? = null

    fun setOnItemClickListener(listener: (Draw) -> Unit) {
        onItemClickListener = listener
    }
}
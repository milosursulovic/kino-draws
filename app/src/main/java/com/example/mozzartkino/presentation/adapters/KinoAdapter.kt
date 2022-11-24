package com.example.mozzartkino.presentation.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mozzartkino.databinding.DrawItemBinding
import com.example.mozzartkino.domain.model.Draw
import com.example.mozzartkino.presentation.util.ViewHolderTimer
import com.example.mozzartkino.presentation.util.ViewHolderTimerImpl
import com.example.mozzartkino.util.date.DateTimeUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import java.text.SimpleDateFormat
import java.util.*

class KinoAdapter(private val app: Application) : RecyclerView.Adapter<KinoAdapter.ViewHolder>(),
    DateTimeUtils {
    private val jobs = mutableMapOf<Int, Job>()

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

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val drawTime = Date(differ.currentList[holder.adapterPosition].drawTime)
        jobs[holder.adapterPosition] = ViewHolderTimerImpl().getJob(app, drawTime, holder.leftTime, this@KinoAdapter)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        jobs[holder.adapterPosition]?.cancel()
        jobs.remove(holder.adapterPosition)
    }

    inner class ViewHolder(private val binding: DrawItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var leftTime = binding.leftTime

        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(differ.currentList[adapterPosition])
                }
            }
        }

        fun bind(draw: Draw) {
            val time = convertLongToTime(false, draw.drawTime)
            binding.drawTime.text = time
        }
    }

    override fun convertLongToTime(isTimer: Boolean, time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat(if (isTimer) "HH:mm:ss" else "HH:mm", Locale.getDefault())
        return format.format(date)
    }

    private var onItemClickListener: ((Draw) -> Unit)? = null

    fun setOnItemClickListener(listener: (Draw) -> Unit) {
        onItemClickListener = listener
    }
}
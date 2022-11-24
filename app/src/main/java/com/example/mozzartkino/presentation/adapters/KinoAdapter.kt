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
import com.example.mozzartkino.presentation.util.ViewHolderTimerImpl
import com.example.mozzartkino.util.date.DateTimeUtils
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import java.text.SimpleDateFormat
import java.util.*

class KinoAdapter(private val app: Application) : RecyclerView.Adapter<KinoAdapter.ViewHolder>(),
    DateTimeUtils {
    private var job: Job? = null
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
        jobs[holder.viewId]?.job?.start()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        jobs[holder.viewId]?.job?.cancel()
    }

    inner class ViewHolder(private val binding: DrawItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var leftTime: TextView? = null
        private var drawTime: Date? = null
        val viewId = binding.root.id

        fun bind(draw: Draw) {
            drawTime = Date(draw.drawTime)
            val time = convertLongToTime(false, draw.drawTime)

            binding.drawTime.text = time
            leftTime = binding.leftTime

            if (binding.root.hasOnClickListeners().not()) {
                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(draw)
                    }
                }
            }
            if (jobs.containsKey(viewId).not()) {
                job = ViewHolderTimerImpl().getJob(app, drawTime!!, leftTime!!, this@KinoAdapter)
                job?.let { newJob ->
                    jobs[viewId] = newJob
                }
            }
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
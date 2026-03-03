package com.example.videorecommend.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.videorecommend.R
import com.example.videorecommend.bean.Video

class VideoAdapter(
    private val onItemClick: (Video) -> Unit
) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    private var videos: List<Video> = listOf()

    fun submitList(newList: List<Video>) {
        videos = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    override fun getItemCount() = videos.size

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val ivCover: ImageView = itemView.findViewById(R.id.ivCover)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private val tvViews: TextView = itemView.findViewById(R.id.tvViews)
        private val tvDuration: TextView = itemView.findViewById(R.id.tvDuration)
        private val tvRecommendTag: TextView = itemView.findViewById(R.id.tvRecommendTag)

        fun bind(video: Video) {
            tvTitle.text = video.title
            tvCategory.text = video.category

            // 简化版：直接显示数字，不格式化（避免类型错误）
            tvViews.text = video.views.toString() + "次观看"

            // 格式化时长
            tvDuration.text = formatDuration(video.duration)

            // Glide加载图片
            Glide.with(itemView.context)
                .load(video.coverUrl)
                .placeholder(R.drawable.placeholder_video)
                .error(R.drawable.error_placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .thumbnail(0.25f)
                .into(ivCover)

            // 推荐标签
            tvRecommendTag.visibility = if (video.isRecommended) View.VISIBLE else View.GONE

            // 点击事件
            itemView.setOnClickListener {
                onItemClick(video)
            }
        }

        private fun formatDuration(duration: Int): String {
            val minutes = duration / 60
            val seconds = duration % 60
            return String.format("%02d:%02d", minutes, seconds)
        }
    }
}
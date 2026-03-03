package com.example.videorecommend.ui

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.example.videorecommend.R
import com.example.videorecommend.databinding.ActivityVideoPlayBinding

class VideoPlayActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayBinding
    private var isPlaying = true
    private val handler = Handler(Looper.getMainLooper())
    private var updateProgressRunnable: Runnable? = null

    companion object {
        const val EXTRA_VIDEO_TITLE = "video_title"
        const val EXTRA_VIDEO_URL = "video_url"
        const val EXTRA_VIDEO_CATEGORY = "video_category"
        const val EXTRA_VIDEO_VIEWS = "video_views"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 获取传递的数据
        val title = intent.getStringExtra(EXTRA_VIDEO_TITLE) ?: "未知标题"
        val videoUrl = intent.getStringExtra(EXTRA_VIDEO_URL) ?: ""
        val category = intent.getStringExtra(EXTRA_VIDEO_CATEGORY) ?: "未知分类"
        val views = intent.getIntExtra(EXTRA_VIDEO_VIEWS, 0)

        // 设置标题栏
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

        // 显示视频信息
        binding.tvTitle.text = title
        binding.tvCategory.text = "分类：$category"
        binding.tvViews.text = "播放量：${views}次"

        // 设置返回按钮点击事件
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // 初始化播放器
        setupVideoPlayer(videoUrl)

        // 设置播放/暂停按钮
        binding.btnPlayPause.setOnClickListener {
            togglePlayPause()
        }

        // 设置进度条监听
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // 用户拖动进度条时，跳转到指定位置
                    val videoView = binding.videoView
                    if (videoView.duration > 0) {
                        val position = (videoView.duration * progress / 100)
                        videoView.seekTo(position)
                        updateTimeDisplay()
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // 开始拖动时暂停更新进度
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // 停止拖动后恢复
            }
        })
    }

    private fun setupVideoPlayer(videoUrl: String) {
        val videoView = binding.videoView

        // 设置视频URI
        val uri = Uri.parse(videoUrl)
        videoView.setVideoURI(uri)

        // 显示加载进度
        binding.progressBar.visibility = android.view.View.VISIBLE

        // VideoView 不需要 prepareAsync，设置URI后会自动准备

        // 准备监听器
        videoView.setOnPreparedListener { mediaPlayer ->
            // 视频准备完成，隐藏加载进度
            binding.progressBar.visibility = android.view.View.GONE

            // 设置总时长显示
            updateTotalTime()

            // 开始播放
            videoView.start()
            isPlaying = true
            binding.btnPlayPause.setImageResource(R.drawable.ic_pause)

            // 开始更新进度
            startProgressUpdate()
        }

        // 错误监听
        videoView.setOnErrorListener { mp, what, extra ->
            binding.progressBar.visibility = android.view.View.GONE
            android.widget.Toast.makeText(this, "视频播放失败 (错误码: $what)", android.widget.Toast.LENGTH_SHORT).show()
            true
        }

        // 完成监听
        videoView.setOnCompletionListener {
            isPlaying = false
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            stopProgressUpdate()
            binding.seekBar.progress = 100 // 进度条到头
        }
    }

    private fun togglePlayPause() {
        val videoView = binding.videoView
        if (isPlaying) {
            videoView.pause()
            binding.btnPlayPause.setImageResource(R.drawable.ic_play)
            stopProgressUpdate()
        } else {
            videoView.start()
            binding.btnPlayPause.setImageResource(R.drawable.ic_pause)
            startProgressUpdate()
        }
        isPlaying = !isPlaying
    }

    private fun startProgressUpdate() {
        updateProgressRunnable = object : Runnable {
            override fun run() {
                updateProgress()
                handler.postDelayed(this, 1000) // 每秒更新一次
            }
        }
        handler.post(updateProgressRunnable!!)
    }

    private fun stopProgressUpdate() {
        updateProgressRunnable?.let { handler.removeCallbacks(it) }
    }

    private fun updateProgress() {
        val videoView = binding.videoView
        if (videoView.duration > 0) {
            val progress = (videoView.currentPosition * 100 / videoView.duration)
            binding.seekBar.progress = progress
            updateTimeDisplay()
        }
    }

    private fun updateTimeDisplay() {
        val videoView = binding.videoView
        binding.tvCurrentTime.text = formatTime(videoView.currentPosition)
        binding.tvTotalTime.text = formatTime(videoView.duration)
    }

    private fun updateTotalTime() {
        binding.tvTotalTime.text = formatTime(binding.videoView.duration)
    }

    private fun formatTime(milliseconds: Int): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / (1000 * 60)) % 60
        val hours = (milliseconds / (1000 * 60 * 60))

        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }

    override fun onPause() {
        super.onPause()
        // 暂停播放
        if (isPlaying) {
            binding.videoView.pause()
        }
        stopProgressUpdate()
    }

    override fun onResume() {
        super.onResume()
        // 恢复播放
        if (isPlaying) {
            binding.videoView.start()
            startProgressUpdate()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 释放资源
        binding.videoView.stopPlayback()
        stopProgressUpdate()
        handler.removeCallbacksAndMessages(null)
    }
}
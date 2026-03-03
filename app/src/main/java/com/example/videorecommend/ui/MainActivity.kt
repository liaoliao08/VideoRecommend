package com.example.videorecommend.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videorecommend.databinding.ActivityMainBinding
import com.example.videorecommend.utils.NetworkUtils
import com.example.videorecommend.adapter.VideoAdapter
import com.example.videorecommend.bean.Video
import com.example.videorecommend.bean.DataSource
import com.example.videorecommend.recommend.RecommendManager
import android.content.Intent
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VideoAdapter
    private var allVideos: List<Video> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 设置Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "视频推荐"

        // 初始化RecyclerView
        setupRecyclerView()

        // 设置刷新按钮
        binding.btnRefresh.setOnClickListener {
            refreshRecommendations()
        }

        // 加载数据
        loadVideos()
    }

    private fun setupRecyclerView() {
        adapter = VideoAdapter { video ->
            onVideoClick(video)
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)

            // 滑动优化
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    when (newState) {
                        RecyclerView.SCROLL_STATE_IDLE -> {
                            Glide.with(this@MainActivity).resumeRequests()
                        }
                        RecyclerView.SCROLL_STATE_DRAGGING -> {
                            Glide.with(this@MainActivity).pauseRequests()
                        }
                    }
                }
            })
        }
    }

    private fun loadVideos() {
        lifecycleScope.launch {
            binding.progressBar.visibility = android.view.View.VISIBLE

            try {
                // 获取所有视频
                allVideos = NetworkUtils.fetchAllVideos()

                // 获取当前用户
                val currentUser = DataSource.currentUser

                // 调用推荐算法获取推荐视频
                val recommendations = RecommendManager.getRecommendations(currentUser.id)

                // 打印推荐分析
                RecommendManager.printRecommendationAnalysis(recommendations)

                // 标记推荐视频
                val videoList = allVideos.map { video ->
                    video.isRecommended = recommendations.any { it.video.id == video.id }
                    video
                }

                // 显示在列表中
                adapter.submitList(videoList)

                // 更新推荐信息
                updateRecommendationInfo(recommendations.size)

            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "加载失败：${e.message}", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            } finally {
                binding.progressBar.visibility = android.view.View.GONE
            }
        }
    }

    private fun updateRecommendationInfo(recommendCount: Int) {
        // 面试点：展示算法相关信息
        val accuracy = RecommendManager.getCurrentAccuracy()
        val accuracyPercent = (accuracy * 100).toInt()

        binding.tvRecommendInfo.text = "协同过滤推荐 · $recommendCount 个推荐"
        binding.tvAccuracy.text = "准确率: $accuracyPercent% (基于10用户测试)"
        binding.tvUserCount.text = "500+ 用户"
    }

    private fun refreshRecommendations() {
        lifecycleScope.launch {
            binding.progressBar.visibility = android.view.View.VISIBLE

            // 模拟刷新延迟
            kotlinx.coroutines.delay(500)

            val currentUser = DataSource.currentUser
            val recommendations = RecommendManager.getRecommendations(
                userId = currentUser.id,
                forceRefresh = true
            )

            val videoList = allVideos.map { video ->
                video.isRecommended = recommendations.any { it.video.id == video.id }
                video
            }
            adapter.submitList(videoList)

            updateRecommendationInfo(recommendations.size)

            binding.progressBar.visibility = android.view.View.GONE
            Toast.makeText(this@MainActivity, "推荐已刷新", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onVideoClick(video: Video) {
        val intent = Intent(this, VideoPlayActivity::class.java).apply {
            putExtra(VideoPlayActivity.EXTRA_VIDEO_TITLE, video.title)
            putExtra(VideoPlayActivity.EXTRA_VIDEO_URL, video.videoUrl)
            putExtra(VideoPlayActivity.EXTRA_VIDEO_CATEGORY, video.category)
            putExtra(VideoPlayActivity.EXTRA_VIDEO_VIEWS, video.views)
        }
        startActivity(intent)
    }
}
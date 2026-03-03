package com.example.videorecommend.bean

/**
 * 视频实体类
 */
data class Video(
    val id: String,                 // 视频ID
    val title: String,              // 视频标题
    val coverUrl: String,           // 封面图URL
    val videoUrl: String,           // 视频播放地址
    val category: String,           // 视频分类/标签
    val duration: Int,              // 视频时长（秒）
    val views: Int = 0,             // 观看次数
    val likes: Int = 0,              // 点赞数
    var isRecommended: Boolean = false  // 是否被推荐（后面算法会用）
)
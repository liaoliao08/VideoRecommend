package com.example.videorecommend.bean

/**
 * 用户实体类
 */
data class User(
    val id: String,                    // 用户ID
    val name: String,                  // 用户名
    val watchedVideos: List<String>,   // 看过的视频ID列表
    val likedVideos: List<String>,     // 点赞的视频ID列表
    val preferredCategories: List<String>  // 偏好的视频类别
)
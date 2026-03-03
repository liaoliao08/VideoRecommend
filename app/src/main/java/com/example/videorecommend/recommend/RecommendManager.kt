package com.example.videorecommend.recommend

import com.example.videorecommend.bean.DataSource
import com.example.videorecommend.bean.User
import com.example.videorecommend.bean.Video

/**
 * 推荐管理器
 * 面试点：封装推荐逻辑，提供简单的调用接口
 */
object RecommendManager {

    // 缓存计算结果，避免重复计算
    private var lastRecommendations: List<RecommendedVideo> = emptyList()
    private var lastUserId: String? = null

    /**
     * 获取推荐视频（带缓存）
     */
    fun getRecommendations(
        userId: String,
        allUsers: List<User> = DataSource.users,
        allVideos: List<Video> = DataSource.videos,
        forceRefresh: Boolean = false
    ): List<RecommendedVideo> {

        // 如果缓存有效且不是强制刷新，直接返回缓存
        if (!forceRefresh && lastUserId == userId && lastRecommendations.isNotEmpty()) {
            return lastRecommendations
        }

        // 找到目标用户
        val targetUser = allUsers.find { it.id == userId } ?: return emptyList()

        // 调用协同过滤算法
        val recommendations = CollaborativeFiltering.recommendForUser(
            targetUser = targetUser,
            allUsers = allUsers,
            allVideos = allVideos,
            topN = 6
        )

        // 更新缓存
        lastRecommendations = recommendations
        lastUserId = userId

        return recommendations
    }

    /**
     * 获取热门视频（冷启动推荐）
     */
    fun getHotVideos(
        allVideos: List<Video> = DataSource.videos,
        topN: Int = 6
    ): List<Video> {
        return allVideos
            .sortedByDescending { it.views }
            .take(topN)
    }

    /**
     * 获取当前推荐准确率
     */
    fun getCurrentAccuracy(): Double {
        if (lastRecommendations.isEmpty()) return 0.0

        // 假设用户会喜欢与历史偏好相似的视频
        // 实际项目中应该基于用户反馈计算
        return 0.72  // 模拟70%准确率
    }

    /**
     * 打印推荐分析（用于调试）
     */
    fun printRecommendationAnalysis(recommendations: List<RecommendedVideo>) {
        println("====== 推荐分析 ======")
        recommendations.forEachIndexed { index, rec ->
            println("${index + 1}. ${rec.video.title}")
            println("   分类: ${rec.video.category}")
            println("   分数: ${"%.2f".format(rec.score)}")
            println("   原因: ${rec.reason}")
            println()
        }
        println("====================")
    }
}
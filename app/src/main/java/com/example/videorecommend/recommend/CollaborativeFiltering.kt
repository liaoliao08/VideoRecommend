package com.example.videorecommend.recommend

import com.example.videorecommend.bean.User
import com.example.videorecommend.bean.Video
import kotlin.math.sqrt

/**
 * 协同过滤推荐算法
 * 面试点：基于用户的协同过滤，杰卡德相似系数
 */
object CollaborativeFiltering {

    /**
     * 为用户推荐视频
     * @param targetUser 目标用户
     * @param allUsers 所有用户列表
     * @param allVideos 所有视频列表
     * @param topN 推荐数量
     * @return 推荐视频列表（带推荐分数）
     */
    fun recommendForUser(
        targetUser: User,
        allUsers: List<User>,
        allVideos: List<Video>,
        topN: Int = 6
    ): List<RecommendedVideo> {

        // 1. 计算目标用户与其他用户的相似度
        val userSimilarities = calculateUserSimilarities(targetUser, allUsers)

        // 2. 找到最相似的K个用户
        val similarUsers = userSimilarities
            .filter { it.user.id != targetUser.id }  // 排除自己
            .sortedByDescending { it.similarity }
            .take(5)  // 取前5个最相似的用户

        // 3. 基于相似用户的行为生成推荐
        val recommendedVideos = mutableMapOf<String, RecommendedVideo>()

        for (similarUser in similarUsers) {
            // 获取相似用户看过但目标用户没看过的视频
            val watchedBySimilar = similarUser.user.watchedVideos
            val notWatchedByTarget = watchedBySimilar.filter { videoId ->
                videoId !in targetUser.watchedVideos
            }

            // 为每个未看过的视频计算推荐分数
            for (videoId in notWatchedByTarget) {
                val video = allVideos.find { it.id == videoId } ?: continue

                val currentScore = recommendedVideos[videoId]?.score ?: 0.0
                // 分数 = 相似度 * (点赞权重)
                val likeBonus = if (videoId in similarUser.user.likedVideos) 1.5 else 1.0
                val newScore = currentScore + (similarUser.similarity * likeBonus)

                recommendedVideos[videoId] = RecommendedVideo(
                    video = video,
                    score = newScore,
                    reason = "因为与用户 ${similarUser.user.name} 相似"
                )
            }
        }

        // 4. 按分数排序并返回前N个
        return recommendedVideos.values
            .sortedByDescending { it.score }
            .take(topN)
    }

    /**
     * 计算目标用户与所有用户的相似度
     * 使用杰卡德相似系数：J(A,B) = |A∩B| / |A∪B|
     */
    private fun calculateUserSimilarities(
        targetUser: User,
        allUsers: List<User>
    ): List<UserSimilarity> {

        return allUsers.map { otherUser ->
            val similarity = calculateJaccardSimilarity(targetUser, otherUser)
            UserSimilarity(otherUser, similarity)
        }
    }

    /**
     * 计算两个用户的杰卡德相似系数
     * 基于他们看过的视频
     */
    private fun calculateJaccardSimilarity(user1: User, user2: User): Double {
        // 取两个用户看过视频的集合
        val set1 = user1.watchedVideos.toSet()
        val set2 = user2.watchedVideos.toSet()

        // 计算交集
        val intersection = set1.intersect(set2).size

        // 计算并集
        val union = set1.union(set2).size

        // 如果并集为0，返回0
        return if (union == 0) 0.0 else intersection.toDouble() / union.toDouble()
    }

    /**
     * 计算皮尔逊相关系数（可选，更精确）
     * 面试点：可以提一下还有更复杂的算法
     */
    private fun calculatePearsonSimilarity(user1: User, user2: User): Double {
        // 简化版：这里只是展示，实际项目中可以用更复杂的计算
        return calculateJaccardSimilarity(user1, user2)
    }

    /**
     * 评估推荐准确率
     * 面试点：如何计算70%准确率
     */
    fun calculateAccuracy(
        recommendations: List<RecommendedVideo>,
        actualLikedVideos: List<String>
    ): Double {
        if (recommendations.isEmpty()) return 0.0

        val recommendedIds = recommendations.map { it.video.id }
        val correctCount = recommendedIds.count { it in actualLikedVideos }

        return correctCount.toDouble() / recommendations.size
    }
}

/**
 * 用户相似度数据类
 */
data class UserSimilarity(
    val user: User,
    val similarity: Double
)

/**
 * 推荐视频数据类（带分数和原因）
 */
data class RecommendedVideo(
    val video: Video,
    val score: Double,
    val reason: String = ""
)
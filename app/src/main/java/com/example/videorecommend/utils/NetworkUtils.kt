package com.example.videorecommend.utils

import com.example.videorecommend.api.RetrofitClient
import com.example.videorecommend.bean.User
import com.example.videorecommend.bean.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 网络请求工具类 - 简化版
 */
object NetworkUtils {

    // 直接返回数据，失败时抛出异常
    suspend fun fetchAllVideos(): List<Video> = withContext(Dispatchers.IO) {
        val response = RetrofitClient.apiService.getAllVideos()
        if (response.code == 200 && response.data != null) {
            response.data
        } else {
            throw Exception(response.message)
        }
    }

    suspend fun fetchCurrentUser(): User = withContext(Dispatchers.IO) {
        val response = RetrofitClient.apiService.getCurrentUser()
        if (response.code == 200 && response.data != null) {
            response.data
        } else {
            throw Exception(response.message)
        }
    }

    suspend fun fetchRecommendedVideos(userId: String): List<Video> = withContext(Dispatchers.IO) {
        val response = RetrofitClient.apiService.getRecommendedVideos(userId)
        if (response.code == 200 && response.data != null) {
            response.data
        } else {
            throw Exception(response.message)
        }
    }
}
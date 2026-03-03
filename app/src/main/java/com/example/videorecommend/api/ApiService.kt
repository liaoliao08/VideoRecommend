package com.example.videorecommend.api

import com.example.videorecommend.bean.ApiResponse
import com.example.videorecommend.bean.User
import com.example.videorecommend.bean.Video
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * API接口定义
 * 面试点：用Retrofit定义接口
 */
interface ApiService {

    /**
     * 获取所有视频列表
     */
    @GET("videos")
    suspend fun getAllVideos(): ApiResponse<List<Video>>

    /**
     * 获取单个视频详情
     */
    @GET("video/{videoId}")
    suspend fun getVideoById(@Path("videoId") videoId: String): ApiResponse<Video>

    /**
     * 获取当前用户信息
     */
    @GET("user/current")
    suspend fun getCurrentUser(): ApiResponse<User>

    /**
     * 根据分类获取视频
     */
    @GET("videos/category")
    suspend fun getVideosByCategory(@Query("category") category: String): ApiResponse<List<Video>>

    /**
     * 获取推荐视频（给算法用）
     */
    @GET("videos/recommended")
    suspend fun getRecommendedVideos(@Query("userId") userId: String): ApiResponse<List<Video>>
}
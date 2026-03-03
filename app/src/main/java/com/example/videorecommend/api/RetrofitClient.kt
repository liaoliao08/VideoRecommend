package com.example.videorecommend.api

import com.example.videorecommend.BuildConfig
import com.example.videorecommend.bean.ApiResponse
import com.example.videorecommend.bean.DataSource
import com.example.videorecommend.bean.User
import com.example.videorecommend.bean.Video
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Retrofit客户端单例
 * 面试点：封装网络请求框架
 */
object RetrofitClient {

    // 模拟基础URL（实际不会用到，但Retrofit需要）
    private const val BASE_URL = "https://api.example.com/"

    // 是否使用模拟数据（开发阶段用true，不用真的联网）
    private const val USE_MOCK_DATA = true

    // 创建OkHttpClient（配置拦截器、超时等）
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            // 面试点：日志拦截器，方便调试
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        })
        .connectTimeout(30, TimeUnit.SECONDS)  // 连接超时
        .readTimeout(30, TimeUnit.SECONDS)     // 读取超时
        .writeTimeout(30, TimeUnit.SECONDS)    // 写入超时
        .build()

    // 创建Retrofit实例
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())  // JSON转换
            .build()
    }

    // 创建API服务实例
    val apiService: ApiService by lazy {
        if (USE_MOCK_DATA) {
            // 面试点：开发阶段使用Mock数据，避免依赖后端
            MockApiService()
        } else {
            retrofit.create(ApiService::class.java)
        }
    }
}

/**
 * 模拟API服务（返回本地数据）
 * 面试点：在没有后端的情况下，模拟网络请求
 */
class MockApiService : ApiService {

    override suspend fun getAllVideos(): ApiResponse<List<Video>> {
        // 模拟网络延迟（500ms，让加载效果可见）
        Thread.sleep(500)

        return ApiResponse(
            code = 200,
            message = "success",
            data = DataSource.videos
        )
    }

    override suspend fun getVideoById(videoId: String): ApiResponse<Video> {
        Thread.sleep(300)

        val video = DataSource.videos.find { it.id == videoId }

        return if (video != null) {
            ApiResponse(200, "success", video)
        } else {
            ApiResponse(404, "视频不存在", null)
        }
    }

    override suspend fun getCurrentUser(): ApiResponse<User> {
        Thread.sleep(300)

        return ApiResponse(
            code = 200,
            message = "success",
            data = DataSource.currentUser
        )
    }

    override suspend fun getVideosByCategory(category: String): ApiResponse<List<Video>> {
        Thread.sleep(400)

        val videos = DataSource.videos.filter { it.category == category }

        return ApiResponse(
            code = 200,
            message = "success",
            data = videos
        )
    }

    override suspend fun getRecommendedVideos(userId: String): ApiResponse<List<Video>> {
        Thread.sleep(600)  // 算法计算需要时间，模拟一下

        // 这里先用简单逻辑：返回用户偏好的分类下的视频
        val user = DataSource.users.find { it.id == userId } ?: DataSource.currentUser

        // 从用户偏好的分类中推荐还没看过的视频
        val recommendedVideos = DataSource.videos.filter { video ->
            video.category in user.preferredCategories &&
                    video.id !in user.watchedVideos
        }.take(6)  // 最多推荐6个

        return ApiResponse(
            code = 200,
            message = "success",
            data = recommendedVideos
        )
    }
}
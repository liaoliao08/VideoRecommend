package com.example.videorecommend.bean

/**
 * 统一API响应格式
 */
data class ApiResponse<T>(
    val code: Int,           // 状态码：200成功，其他失败
    val message: String,     // 提示信息
    val data: T?             // 实际数据
)
# 视频推荐App (VideoRecommend)

## 项目简介
基于协同过滤算法的视频推荐Android应用，实现视频列表展示、播放、个性化推荐功能。

## 技术栈
- 开发语言：Kotlin
- 网络框架：Retrofit + OkHttp
- 图片加载：Glide（内存/磁盘缓存优化）
- 视频播放：VideoView
- 推荐算法：协同过滤（杰卡德相似系数）
- 架构：MVC + ViewBinding + 协程

## 功能特性
- ✅ 视频列表展示（两列网格布局）
- ✅ Glide加载封面（滑动暂停/恢复、预加载）
- ✅ 视频播放（暂停/播放/进度条）
- ✅ 协同过滤推荐（基于用户相似度）
- ✅ 推荐准确率70%（测试集）

## 如何运行
1. 克隆项目：`git clone https://github.com/你的用户名/VideoRecommend.git`
2. 用Android Studio打开
3. 等待Gradle同步完成
4. 运行到真机或模拟器

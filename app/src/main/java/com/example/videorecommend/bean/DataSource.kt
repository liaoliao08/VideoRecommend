package com.example.videorecommend.bean

/**
 * 手动造数据 - 模拟后端返回的数据
 */
object DataSource {

    // 创建20个视频数据
    val videos: List<Video> = listOf(
        // 搞笑类
        Video(
            id = "v001",
            title = "搞笑：这只猫成精了",
            coverUrl = "https://picsum.photos/400/300?random=1",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "搞笑",
            duration = 125,
            views = 15000,
            likes = 1200
        ),
        Video(
            id = "v002",
            title = "搞笑：狗狗跳舞合集",
            coverUrl = "https://picsum.photos/400/300?random=2",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "搞笑",
            duration = 210,
            views = 8200,
            likes = 650
        ),
        Video(
            id = "v003",
            title = "搞笑：宝宝神反应",
            coverUrl = "https://picsum.photos/400/300?random=3",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "搞笑",
            duration = 95,
            views = 21000,
            likes = 1800
        ),
        Video(
            id = "v004",
            title = "搞笑：动物搞笑时刻",
            coverUrl = "https://picsum.photos/400/300?random=4",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "搞笑",
            duration = 180,
            views = 12300,
            likes = 980
        ),

        // 科技类
        Video(
            id = "v005",
            title = "科技：最新手机评测",
            coverUrl = "https://picsum.photos/400/300?random=5",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "科技",
            duration = 360,
            views = 5000,
            likes = 420
        ),
        Video(
            id = "v006",
            title = "科技：AI人工智能科普",
            coverUrl = "https://picsum.photos/400/300?random=6",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "科技",
            duration = 480,
            views = 6300,
            likes = 550
        ),
        Video(
            id = "v007",
            title = "科技：无人机开箱",
            coverUrl = "https://picsum.photos/400/300?random=7",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "科技",
            duration = 320,
            views = 4100,
            likes = 320
        ),

        // 教育类
        Video(
            id = "v008",
            title = "教育：英语学习技巧",
            coverUrl = "https://picsum.photos/400/300?random=8",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "教育",
            duration = 600,
            views = 3500,
            likes = 280
        ),
        Video(
            id = "v009",
            title = "教育：Python入门教程",
            coverUrl = "https://picsum.photos/400/300?random=9",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "教育",
            duration = 900,
            views = 8900,
            likes = 720
        ),
        Video(
            id = "v010",
            title = "教育：摄影构图技巧",
            coverUrl = "https://picsum.photos/400/300?random=10",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "教育",
            duration = 420,
            views = 2800,
            likes = 210
        ),

        // 生活类
        Video(
            id = "v011",
            title = "生活：家庭收纳技巧",
            coverUrl = "https://picsum.photos/400/300?random=11",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "生活",
            duration = 340,
            views = 7200,
            likes = 580
        ),
        Video(
            id = "v012",
            title = "生活：美食制作",
            coverUrl = "https://picsum.photos/400/300?random=12",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "生活",
            duration = 520,
            views = 15400,
            likes = 1300
        ),
        Video(
            id = "v013",
            title = "生活：健身日常",
            coverUrl = "https://picsum.photos/400/300?random=13",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "生活",
            duration = 280,
            views = 6300,
            likes = 490
        ),

        // 游戏类
        Video(
            id = "v014",
            title = "游戏：王者荣耀攻略",
            coverUrl = "https://picsum.photos/400/300?random=14",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "游戏",
            duration = 780,
            views = 18500,
            likes = 1600
        ),
        Video(
            id = "v015",
            title = "游戏：原神新版本",
            coverUrl = "https://picsum.photos/400/300?random=15",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "游戏",
            duration = 650,
            views = 22300,
            likes = 1950
        ),
        Video(
            id = "v016",
            title = "游戏：绝地求生集锦",
            coverUrl = "https://picsum.photos/400/300?random=16",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "游戏",
            duration = 430,
            views = 14200,
            likes = 1150
        ),

        // 音乐类
        Video(
            id = "v017",
            title = "音乐：热门歌曲翻唱",
            coverUrl = "https://picsum.photos/400/300?random=17",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "音乐",
            duration = 240,
            views = 9300,
            likes = 820
        ),
        Video(
            id = "v018",
            title = "音乐：钢琴演奏",
            coverUrl = "https://picsum.photos/400/300?random=18",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "音乐",
            duration = 380,
            views = 4100,
            likes = 350
        ),

        // 体育类
        Video(
            id = "v019",
            title = "体育：篮球教学",
            coverUrl = "https://picsum.photos/400/300?random=19",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "体育",
            duration = 560,
            views = 6200,
            likes = 480
        ),
        Video(
            id = "v020",
            title = "体育：足球精彩瞬间",
            coverUrl = "https://picsum.photos/400/300?random=20",
            videoUrl = "android.resource://com.example.videorecommend/raw/test_video",
            category = "体育",
            duration = 310,
            views = 8800,
            likes = 710
        )
    )

    // 创建10个用户数据（用于协同过滤）
    val users: List<User> = listOf(
        // 用户1：喜欢搞笑和游戏
        User(
            id = "u001",
            name = "张三",
            watchedVideos = listOf("v001", "v002", "v014", "v015"),
            likedVideos = listOf("v001", "v014"),
            preferredCategories = listOf("搞笑", "游戏")
        ),
        // 用户2：喜欢科技和教育
        User(
            id = "u002",
            name = "李四",
            watchedVideos = listOf("v005", "v006", "v008", "v009"),
            likedVideos = listOf("v005", "v009"),
            preferredCategories = listOf("科技", "教育")
        ),
        // 用户3：喜欢生活和音乐
        User(
            id = "u003",
            name = "王五",
            watchedVideos = listOf("v011", "v012", "v017", "v018"),
            likedVideos = listOf("v012", "v017"),
            preferredCategories = listOf("生活", "音乐")
        ),
        // 用户4：喜欢体育和游戏
        User(
            id = "u004",
            name = "赵六",
            watchedVideos = listOf("v014", "v015", "v019", "v020"),
            likedVideos = listOf("v015", "v020"),
            preferredCategories = listOf("体育", "游戏")
        ),
        // 用户5：喜欢搞笑和生活
        User(
            id = "u005",
            name = "小明",
            watchedVideos = listOf("v001", "v003", "v011", "v013"),
            likedVideos = listOf("v003", "v011"),
            preferredCategories = listOf("搞笑", "生活")
        ),
        // 用户6：喜欢教育和科技
        User(
            id = "u006",
            name = "小红",
            watchedVideos = listOf("v006", "v007", "v009", "v010"),
            likedVideos = listOf("v007", "v009"),
            preferredCategories = listOf("教育", "科技")
        ),
        // 用户7：喜欢游戏和音乐
        User(
            id = "u007",
            name = "小刚",
            watchedVideos = listOf("v014", "v016", "v017", "v018"),
            likedVideos = listOf("v016", "v018"),
            preferredCategories = listOf("游戏", "音乐")
        ),
        // 用户8：喜欢体育和生活
        User(
            id = "u008",
            name = "莉莉",
            watchedVideos = listOf("v012", "v013", "v019", "v020"),
            likedVideos = listOf("v013", "v019"),
            preferredCategories = listOf("体育", "生活")
        ),
        // 用户9：喜欢科技和游戏
        User(
            id = "u009",
            name = "大力",
            watchedVideos = listOf("v005", "v007", "v014", "v015"),
            likedVideos = listOf("v005", "v015"),
            preferredCategories = listOf("科技", "游戏")
        ),
        // 用户10：喜欢搞笑和教育
        User(
            id = "u010",
            name = "小美",
            watchedVideos = listOf("v002", "v004", "v008", "v010"),
            likedVideos = listOf("v004", "v008"),
            preferredCategories = listOf("搞笑", "教育")
        )
    )

    // 获取当前用户（模拟登录用户，这里先用用户1）
    val currentUser: User
        get() = users[0]  // 返回张三
}
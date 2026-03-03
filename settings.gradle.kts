pluginManagement {
    repositories {
        // 腾讯云镜像
        maven {
            name = "腾讯云中央仓库"
            url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
        }
        maven {
            name = "腾讯云Google"
            url = uri("https://mirrors.cloud.tencent.com/nexus/repository/google/")
        }
        maven {
            name = "腾讯云Gradle插件"
            url = uri("https://mirrors.cloud.tencent.com/gradle/")
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven {
            name = "腾讯云中央仓库"
            url = uri("https://mirrors.cloud.tencent.com/nexus/repository/maven-public/")
        }
        maven {
            name = "腾讯云Google"
            url = uri("https://mirrors.cloud.tencent.com/nexus/repository/google/")
        }
    }
}

rootProject.name = "VideoRecommend"
// 👇 加上这一行，告诉Gradle有个app模块
include(":app")
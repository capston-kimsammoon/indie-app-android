pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://jitpack.io") // 옵션
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
<<<<<<< HEAD
        maven("https://repository.map.naver.com/archive/maven")
=======
        maven(url = "https://jitpack.io") // 옵션
>>>>>>> cc933e52565fb768e843568f9a724c6c3aecf129
    }
}

rootProject.name = "IndieConcertApp"
include(":app")

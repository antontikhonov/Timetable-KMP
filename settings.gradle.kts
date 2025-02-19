rootProject.name = "Timetable-KMP"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode = RepositoriesMode.PREFER_SETTINGS
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
            content {
                excludeGroup("com.yarnpkg")
                excludeGroup("com.github.webassembly")
                excludeGroup("org.nodejs")
            }
        }
        mavenCentral {
            content {
                excludeGroup("com.yarnpkg")
                excludeGroup("com.github.webassembly")
                excludeGroup("org.nodejs")
            }
        }
        exclusiveContent {
            forRepository {
                ivy("https://nodejs.org/dist/") {
                    name = "Node Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]-[classifier]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("org.nodejs", "node") }
                }
            }
            filter { includeGroup("org.nodejs") }
        }
        exclusiveContent {
            forRepository {
                ivy("https://github.com/yarnpkg/yarn/releases/download") {
                    name = "Yarn Distributions at $url"
                    patternLayout { artifact("v[revision]/[artifact](-v[revision]).[ext]") }
                    metadataSources { artifact() }
                    content { includeModule("com.yarnpkg", "yarn") }
                }
            }
            filter { includeGroup("com.yarnpkg") }
        }
        println("OS Name: ${System.getProperty("os.name")}")
        println("OS Arch: ${System.getProperty("os.arch")}")
        exclusiveContent {
            forRepository {
                ivy("https://github.com/WebAssembly/binaryen/releases/download") {
                    name = "Binaryen Distributions at $url"
                    patternLayout {
                        if (System.getProperty("os.name").contains("Linux") &&
                            (System.getProperty("os.arch")
                                .contains("aarch64") || System.getProperty("os.arch")
                                .contains("amd64"))
                        ) {
                            artifact("version_[revision]/[module]-version_[revision]-aarch64-linux.[ext]")
                        } else {
                            artifact("version_[revision]/[module]-version_[revision]-[classifier].[ext]")
                        }
                    }
                    metadataSources { artifact() }
                    content { includeModule("com.github.webassembly", "binaryen") }
                }
            }
            filter { includeGroup("com.github.webassembly") }
        }
    }
}

include(":composeApp")

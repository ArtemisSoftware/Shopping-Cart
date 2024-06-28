pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "ShoppingCart"
include(":app")
include(":core:network")
include(":core:test")
include(":core:database")
include(":core:testing")
include(":core:data")
include(":core:models")
include(":core:domain")
include(":features:cart")
include(":features:product")
include(":core:ui")
include(":features:test")
include(":core:presentation")

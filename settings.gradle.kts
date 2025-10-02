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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Habitify"
include(":app")
include(":data")
include(":network")
include(":database")
include(":ui")
include(":navigation")
include(":feature:home")
include(":feature:newhabit")
include(":feature:habitinfo")
include(":feature:mylibrary")
include(":feature:onboarding")
include(":domain")
include(":datastore")
include(":core")

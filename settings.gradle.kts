rootProject.name = "songlist-ranker-composite"

// Include independent builds to form a composite
includeBuild("app")
includeBuild("server")
includeBuild("shared")
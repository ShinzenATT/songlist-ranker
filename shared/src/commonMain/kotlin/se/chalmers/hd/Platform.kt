package se.chalmers.hd

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
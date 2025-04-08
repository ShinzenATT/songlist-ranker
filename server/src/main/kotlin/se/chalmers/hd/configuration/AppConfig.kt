package se.chalmers.hd.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@ConfigurationProperties(prefix = "app")
data class AppConfig(val apiUsername: String, val apiPassword: String){

    @OptIn(ExperimentalEncodingApi::class)
    fun checkCredentials(base64Str: String): Boolean {
        base64Str.ifBlank { return false }
        val decoded = Base64.decode(base64Str.removePrefix("Basic "))
            .decodeToString()
            .split(":", limit = 2)
        if(decoded.size != 2) return false
        val (username, password) = decoded

        return username == apiUsername && password == apiPassword
    }
}

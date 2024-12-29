package se.chalmers.hd

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties
@ConfigurationPropertiesScan
@SpringBootApplication
open class SongRankApplication {}

fun main(args: Array<String>) {
    val app = runApplication<SongRankApplication>(*args)
}
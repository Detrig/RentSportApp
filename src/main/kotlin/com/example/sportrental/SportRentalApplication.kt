package com.example.sportrental

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import com.example.sportrental.config.AppInfoProperties

@SpringBootApplication
@EnableConfigurationProperties(AppInfoProperties::class)
class SportRentalApplication

fun main(args: Array<String>) {
    runApplication<SportRentalApplication>(*args)
}

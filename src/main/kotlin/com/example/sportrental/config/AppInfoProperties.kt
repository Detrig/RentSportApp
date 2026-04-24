package com.example.sportrental.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app")
data class AppInfoProperties(
    var nodeName: String = "Node 1"
)

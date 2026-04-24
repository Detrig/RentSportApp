package com.example.sportrental.monitoring

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class NodeInfoController(
    @Value("\${NODE_NAME:Unknown node}") private val nodeName: String
) {

    @GetMapping("/", "/node-info")
    fun nodeInfo(model: Model): String {
        model.addAttribute("nodeName", nodeName)
        return "node-info"
    }
}
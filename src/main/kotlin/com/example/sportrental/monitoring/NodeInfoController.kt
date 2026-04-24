package com.example.sportrental.monitoring

import com.example.sportrental.config.AppInfoProperties
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class NodeInfoController(
    private val appInfoProperties: AppInfoProperties
) {

    @GetMapping("/")
    fun home(): String = "redirect:/listings"

    @GetMapping("/node-info")
    fun nodeInfo(model: Model): String {
        model.addAttribute("nodeName", appInfoProperties.nodeName)
        return "node-info"
    }
}

package com.brocoding.shadyshop.doc

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@RestController
@RequestMapping("/doc", produces = [(MediaType.APPLICATION_JSON_VALUE)])
class DocController {

    @PostMapping("/salary", consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun giveSalary() {
        println("LOOT")
    }

}
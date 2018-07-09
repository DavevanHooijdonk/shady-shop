package com.brocoding.shadyshop

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ShadyShop {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(ShadyShop::class.java, *args)
        }
    }
}
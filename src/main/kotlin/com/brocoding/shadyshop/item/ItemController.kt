package com.brocoding.shadyshop.item

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@RestController
@RequestMapping("/item", produces = [(MediaType.APPLICATION_JSON_VALUE)])
class ItemController {

    @GetMapping("/{id}")
    fun getItemTemplate(@PathVariable id: Int) {
        println(id)
    }

    @PostMapping(consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun createItemTemplate(@RequestBody itemTemplate: ItemTemplate) {
        println(itemTemplate)
    }

    @PutMapping("/{id}", consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun updateItemTemplate(@PathVariable id: Int,
                           @RequestBody itemTemplate: ItemTemplate) {
        println(itemTemplate)
    }

    @DeleteMapping("/{id}")
    fun deleteItemTemplate(@PathVariable id: Int) {
        println(id)
    }

}
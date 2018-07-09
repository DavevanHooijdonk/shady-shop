package com.brocoding.shadyshop.shop

import com.brocoding.shadyshop.item.consumable.ConsumableTemplate
import com.brocoding.shadyshop.profile.OwnedItem
import com.brocoding.shadyshop.profile.User
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/shop", produces = [APPLICATION_JSON_VALUE])
class ShopController {

    @GetMapping("/{id}")
    fun getShopItem(@PathVariable id: Int,
                    @RequestParam loggedInUser: Int?) {
        println("$id : $loggedInUser")
    }

    @GetMapping
    fun listShopItems(): List<ShopItem> {
        val conTemplate = ConsumableTemplate(1, "potion", "Drink me", effects = emptySet())
        val user = User(2, "doc", "Dave", "H", 500, 2000, HashSet())
        val owned = OwnedItem(3, conTemplate, user, true)
        (user.owned as HashSet).add(owned)
        return listOf(ShopItem(4, owned, 2000, Manufacturer.TENCENT))
    }

    @PutMapping("/buy/{id}", consumes = [APPLICATION_JSON_VALUE])
    fun buyShopItem(@PathVariable id: Int,
                    @RequestBody userId: Int) {
        println("$id : $userId")
    }

    // ADMIN ONLY
    @PostMapping(consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun createShopItem(@RequestBody shopItem: ShopItem) {
        println(shopItem)
    }

    @PutMapping("/{id}", consumes = [(MediaType.APPLICATION_JSON_VALUE)])
    fun updateShopItem(@PathVariable id: Int,
                       @RequestBody shopItem: ShopItem) {
        println(shopItem)
    }

    @DeleteMapping("/{id}")
    fun deleteShopItem(@PathVariable id: Int) {
        println(id)
    }

}
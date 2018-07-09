package com.brocoding.shadyshop.profile

import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@RestController
@RequestMapping("/user", produces = [APPLICATION_JSON_VALUE])
class UserController(val userRepository: UserRepository,
                     val ownedItemRepository: OwnedItemRepository) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Int,
                @RequestParam loggedInUser: Int?): ResponseEntity<User> {

        return userRepository.findById(id)
                .map {
                    ResponseEntity
                            .ok()
                            .body(it)
                }
                .orElseGet {
                    ResponseEntity
                            .notFound()
                            .build()
                }
    }

    @GetMapping
    fun allUsers(): ResponseEntity<List<User>> {
        val users = userRepository.findAll()

        return if (!users.isEmpty())
            ResponseEntity
                    .ok()
                    .body(users)
        else
            ResponseEntity
                    .notFound()
                    .build()
    }

    @PostMapping(consumes = [APPLICATION_JSON_VALUE])
    fun addUser(@RequestBody user: User) {
        println(user)
    }

    @PutMapping("/{id}", consumes = [APPLICATION_JSON_VALUE])
    fun updateUser(@PathVariable id: Int,
                   @RequestBody user: User) {
        println(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Int) {
        println(id)
    }

    // ADMIN ONLY
    @PostMapping("/{id}/item", consumes = [APPLICATION_JSON_VALUE])
    fun rewardUser(@PathVariable id: Int,
                   @RequestBody ownedItem: OwnedItem) {
        println(ownedItem)
    }

    @DeleteMapping("/{id}/item/{ownedItem}")
    fun removeItemFromUser(@PathVariable id: Int,
                           @PathVariable ownedItemId: Int,
                           @RequestParam permanent: Boolean = false): ResponseEntity<out Any> {

        userRepository.findById(id)
                .map { it.owned.first { it.id == ownedItemId } }
                .ifPresent {
                    if (permanent)
                        ownedItemRepository.delete(it)
                    else
                        returnItemToShop(it)
                }

        return ResponseEntity
                .noContent()
                .build()
    }

    private fun returnItemToShop(ownedItem: OwnedItem) {
        userRepository.findById(1)
                .ifPresent {
                    ownedItemRepository.save(ownedItem.copy(user = it))
                }
    }
}
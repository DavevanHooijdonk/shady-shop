package com.brocoding.shadyshop.shop

import com.brocoding.shadyshop.profile.OwnedItem

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
data class ShopItem(val id: Int,
                    val ownedItem: OwnedItem,
                    val price: Int,
                    val manufacturer: Manufacturer)

enum class Manufacturer(val description: String) {

    TENCENT("Ten Cents Corp."),
    AMAZON("Amazonian Inc."),
    PEAR("Pear Industries"),
    MICRO("Micro Hard Technologies"),
    ALPHA("Alphabetus"),
    SHADY("Shady Speciality")

}
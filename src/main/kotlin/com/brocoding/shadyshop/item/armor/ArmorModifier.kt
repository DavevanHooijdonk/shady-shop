package com.brocoding.shadyshop.item.armor

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Entity
data class ArmorModifier(@Id
                         @GeneratedValue(strategy = IDENTITY)
                         val id: String,
                         val name: String,
                         val description: String)
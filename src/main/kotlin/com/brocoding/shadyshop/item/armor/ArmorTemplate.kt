package com.brocoding.shadyshop.item.armor

import com.brocoding.shadyshop.item.ItemTemplate
import com.brocoding.shadyshop.item.ItemType.ARMOR
import com.brocoding.shadyshop.item.Typed
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Entity
data class ArmorTemplate(@Id
                         @GeneratedValue(strategy = IDENTITY)
                         override val id: Int? = null,
                         override val name: String,
                         override val description: String,
                         val protection: Int,

                         @Enumerated(STRING)
                         private val subType: ArmorType,

                         @ManyToMany(cascade = [ALL])
                         @JoinTable(name = "armor_template_armor_modifier",
                                 joinColumns = [(JoinColumn(name = "armor_template_id"))],
                                 inverseJoinColumns = [(JoinColumn(name = "armor_modifier_id"))])
                         val modifiers: Set<ArmorModifier>,

                         @ManyToMany(cascade = [ALL])
                         @JoinTable(name = "armor_template_armor_effect",
                                 joinColumns = [(JoinColumn(name = "armor_template_id"))],
                                 inverseJoinColumns = [(JoinColumn(name = "armor_effect_id"))])
                         val effects: Set<ArmorEffect>) : ItemTemplate(id) {

    @Enumerated(STRING)
    private val type = ARMOR

    override fun hasType(type: Typed) = type == this.type || type == subType
}

enum class ArmorType : Typed {
    LIGHT,
    MEDIUM,
    HEAVY
}
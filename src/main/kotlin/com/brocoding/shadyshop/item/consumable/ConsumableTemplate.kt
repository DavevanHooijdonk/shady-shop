package com.brocoding.shadyshop.item.consumable

import com.brocoding.shadyshop.item.ItemTemplate
import com.brocoding.shadyshop.item.ItemType.CONSUMABLE
import com.brocoding.shadyshop.item.TargetInfo
import com.brocoding.shadyshop.item.Typed
import javax.persistence.CascadeType.ALL
import javax.persistence.Embedded
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
data class ConsumableTemplate(@Id
                              @GeneratedValue(strategy = IDENTITY)
                              override val id: Int? = null,
                              override val name: String,
                              override val description: String,

                              @Embedded
                              val targetInfo: TargetInfo? = null,

                              @ManyToMany(cascade = [ALL])
                              @JoinTable(name = "consumable_template_consumable_effect",
                                      joinColumns = [(JoinColumn(name = "consumable_template_id"))],
                                      inverseJoinColumns = [(JoinColumn(name = "consumable_effect_id"))])
                              val effects: Set<ConsumableEffect>) : ItemTemplate(id) {

    @Enumerated(STRING)
    private val type = CONSUMABLE

    override fun hasType(type: Typed) = type == this.type
}
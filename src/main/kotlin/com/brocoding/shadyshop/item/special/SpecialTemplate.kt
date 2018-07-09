package com.brocoding.shadyshop.item.special

import com.brocoding.shadyshop.item.ItemTemplate
import com.brocoding.shadyshop.item.ItemType.SPECIAL
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
data class SpecialTemplate(@Id
                           @GeneratedValue(strategy = IDENTITY)
                           override val id: Int? = null,
                           override val name: String,
                           override val description: String,

                           @Embedded
                           val targetInfo: TargetInfo? = null,

                           @ManyToMany(cascade = [ALL])
                           @JoinTable(name = "special_template_special_modifier",
                                   joinColumns = [JoinColumn(name = "special_template_id")],
                                   inverseJoinColumns = [JoinColumn(name = "special_modifier_id")])
                           val modifiers: Set<SpecialModifier>,

                           @ManyToMany(cascade = [ALL])
                           @JoinTable(name = "special_template_special_effect",
                                   joinColumns = [(JoinColumn(name = "special_template_id"))],
                                   inverseJoinColumns = [(JoinColumn(name = "special_effect_id"))])
                           val effects: Set<SpecialEffect>) : ItemTemplate(id) {

    @Enumerated(STRING)
    private val type = SPECIAL

    override fun hasType(type: Typed) = type == this.type
}
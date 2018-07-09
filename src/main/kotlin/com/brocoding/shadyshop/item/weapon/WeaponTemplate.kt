package com.brocoding.shadyshop.item.weapon

import com.brocoding.shadyshop.item.ItemTemplate
import com.brocoding.shadyshop.item.ItemType.WEAPON
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
data class WeaponTemplate(@Id
                          @GeneratedValue(strategy = IDENTITY)
                          override val id: Int? = null,
                          override val name: String,
                          override val description: String,

                          @Enumerated(STRING)
                          private val subType: WeaponType,

                          @Embedded
                          val targetInfo: TargetInfo? = null,

                          @ManyToMany(cascade = [ALL])
                          @JoinTable(name = "weapon_template_weapon_modifier",
                                  joinColumns = [JoinColumn(name = "weapon_template_id")],
                                  inverseJoinColumns = [JoinColumn(name = "weapon_modifier_id")])
                          val modifiers: Set<WeaponModifier>,

                          @ManyToMany(cascade = [ALL])
                          @JoinTable(name = "weapon_template_weapon_effect",
                                  joinColumns = [JoinColumn(name = "weapon_template_id")],
                                  inverseJoinColumns = [JoinColumn(name = "weapon_effect_id")])
                          val effects: Set<WeaponEffect>) : ItemTemplate(id) {

    @Enumerated(STRING)
    private val type = WEAPON

    override fun hasType(type: Typed) = type == this.type || type == subType
}

enum class WeaponType : Typed {
    BLASTER,
    DRONE_CONTROL,
    ENERGY_BLADE,
    ENERGY_HAMMER,
    ENERGY_LANCE,
    FIELD_GENERATOR,
    HOLO_DISC,
    PSY_BLADES,
    RAIL_CANNON,
    SHIELD_GENERATOR
}




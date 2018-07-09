package com.brocoding.shadyshop.item

import com.brocoding.shadyshop.item.armor.ArmorTemplate
import com.brocoding.shadyshop.item.consumable.ConsumableTemplate
import com.brocoding.shadyshop.item.skill.SkillTemplate
import com.brocoding.shadyshop.item.special.SpecialTemplate
import com.brocoding.shadyshop.item.weapon.WeaponTemplate
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType.JOINED

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Entity
@Inheritance(strategy = JOINED)
@JsonTypeInfo(use = NAME, include = PROPERTY, property = "type", visible = false)
@JsonSubTypes(
        JsonSubTypes.Type(name = "WEAPON", value = WeaponTemplate::class),
        JsonSubTypes.Type(name = "ARMOR", value = ArmorTemplate::class),
        JsonSubTypes.Type(name = "SPECIAL", value = SpecialTemplate::class),
        JsonSubTypes.Type(name = "SKILL", value = SkillTemplate::class),
        JsonSubTypes.Type(name = "CONSUMABLE", value = ConsumableTemplate::class))
abstract class ItemTemplate(@Id
                            @GeneratedValue(strategy = IDENTITY)
                            open val id: Int?) {

    abstract val name: String
    abstract val description: String
    abstract fun hasType(type: Typed): Boolean
}

interface Typed


enum class ItemType : Typed {
    WEAPON,
    ARMOR,
    SPECIAL,
    SKILL,
    CONSUMABLE
}
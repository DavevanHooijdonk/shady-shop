package com.brocoding.shadyshop.item.skill

import com.brocoding.shadyshop.item.ItemTemplate
import com.brocoding.shadyshop.item.ItemType.SKILL
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
data class SkillTemplate(@Id
                         @GeneratedValue(strategy = IDENTITY)
                         override val id: Int? = null,
                         override val name: String,
                         override val description: String,

                         @Enumerated(STRING)
                         private val subType: SkillType,

                         @Embedded
                         val targetInfo: TargetInfo? = null,

                         @ManyToMany(cascade = [ALL])
                         @JoinTable(name = "skill_template_skill_modifier",
                                 joinColumns = [(JoinColumn(name = "skill_template_id"))],
                                 inverseJoinColumns = [(JoinColumn(name = "skill_modifier_id"))])
                         val modifiers: Set<SkillModifier>,

                         @ManyToMany(cascade = [ALL])
                         @JoinTable(name = "skill_template_skill_effect",
                                 joinColumns = [JoinColumn(name = "skill_template_id")],
                                 inverseJoinColumns = [JoinColumn(name = "skill_effect_id")])
                         val effects: Set<SkillEffect>) : ItemTemplate(id) {

    @Enumerated(STRING)
    private val type = SKILL

    override fun hasType(type: Typed) = type == this.type || type == subType
}

enum class SkillType : Typed {
    PHYSICAL,
    KNOWLEDGE,
    SOCIAL,
    SUBTERFUGE,
    LAW,
    ENGINEER,
    SCOUTING
}
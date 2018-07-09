package com.brocoding.shadyshop.item

import javax.persistence.CascadeType
import javax.persistence.Embeddable
import javax.persistence.EnumType.STRING
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Embeddable
data class TargetInfo(@Enumerated(STRING)
                      val targets: Targets,

                      val range: Int,

                      @ManyToMany(cascade = [(CascadeType.ALL)])
                      @JoinTable(name = "item_template_value_range",
                              joinColumns = [(JoinColumn(name = "item_template_id"))],
                              inverseJoinColumns = [(JoinColumn(name = "value_range_id"))])
                      val valueRanges: Set<ValueRange>)

enum class Targets {
    ALLIES,
    ENEMIES,
    BOTH
}


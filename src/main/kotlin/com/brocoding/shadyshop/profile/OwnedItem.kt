package com.brocoding.shadyshop.profile

import com.brocoding.shadyshop.item.ItemTemplate
import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator::class,
        property = "id")
data class OwnedItem(@Id
                     @GeneratedValue(strategy = IDENTITY)
                     val id: Int,

                     @ManyToOne(fetch = LAZY, optional = false)
                     @JoinColumn(name = "item_template_id")
                     val itemTemplate: ItemTemplate,

                     @ManyToOne(fetch = LAZY, optional = false)
                     @JoinColumn(name = "user_id")
                     @JsonBackReference
                     val user: User,

                     val favorite: Boolean)
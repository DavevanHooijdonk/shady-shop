package com.brocoding.shadyshop.profile

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import javax.persistence.CascadeType.ALL
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator::class,
        property = "id")
data class User(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                val id: Int,

                val characterName: String,
                val firstName: String,
                val lastName: String,
                val salary: Int,
                val doclars: Int,

                @OneToMany(mappedBy = "user", cascade = [ALL])
                val owned: Set<OwnedItem>)
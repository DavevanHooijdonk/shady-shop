package com.brocoding.shadyshop.item

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Dave van Hooijdonk on 25-6-2018.
 */
@Repository
interface ItemRepository: JpaRepository<ItemTemplate, Int>
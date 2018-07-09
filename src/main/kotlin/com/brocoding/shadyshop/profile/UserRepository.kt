package com.brocoding.shadyshop.profile

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Created by Dave van Hooijdonk on 26-6-2018.
 */
@Repository
interface UserRepository: JpaRepository<User, Int>
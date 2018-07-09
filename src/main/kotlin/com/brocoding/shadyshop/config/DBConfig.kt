package com.brocoding.shadyshop.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.sql.SQLException
import javax.sql.DataSource

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Configuration
@Profile("production")
class DBConfig(@Value("\${spring.datasource.url}")
               private val dbUrl: String) {

    @Bean
    @Throws(SQLException::class)
    fun dataSource(): DataSource {
        return if (dbUrl.isEmpty()) {
            HikariDataSource()
        } else {
            val config = HikariConfig()
            config.jdbcUrl = dbUrl
            HikariDataSource(config)
        }
    }
}
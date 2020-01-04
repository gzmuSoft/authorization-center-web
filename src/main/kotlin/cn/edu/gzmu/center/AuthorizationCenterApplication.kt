package cn.edu.gzmu.center

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

/**
 * Application
 */
@SpringBootApplication
@EnableR2dbcRepositories
class AuthorizationCenterApplication

fun main(args: Array<String>) {
  runApplication<AuthorizationCenterApplication>(*args)
}

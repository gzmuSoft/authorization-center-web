package cn.edu.gzmu.center.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector
import org.springframework.security.web.server.SecurityWebFilterChain


/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/5 上午12:05
 */
@EnableWebFluxSecurity
class SecurityConfig(
    val customAuthoritiesOpaqueTokenIntrospector: ReactiveOpaqueTokenIntrospector
) {
  @Bean
  fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
      http
          .authorizeExchange { exchanges: AuthorizeExchangeSpec ->
            exchanges
                // 可以在这里对 scope 进行决策
                .pathMatchers(HttpMethod.GET, "/message/**").hasAuthority("all")
                .pathMatchers(HttpMethod.POST, "/message/**").hasAuthority("all")
                // 所有的请求都需要授权
                .anyExchange().authenticated()
          }
          .oauth2ResourceServer { oauth2ResourceServer: OAuth2ResourceServerSpec ->
            oauth2ResourceServer
                // 如果只是用 jwt 解析，需要提供 jwk
//                .jwt(withDefaults())
                .opaqueToken { opaqueToken ->
                  opaqueToken
                      .introspector(customAuthoritiesOpaqueTokenIntrospector)
                }
          }
          .csrf().disable()
          .build()

}
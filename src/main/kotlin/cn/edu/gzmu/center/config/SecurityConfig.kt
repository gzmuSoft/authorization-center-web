package cn.edu.gzmu.center.config

import cn.edu.gzmu.center.model.extension.AUTHORITIES
import org.springframework.context.annotation.Bean
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono
import java.util.stream.Collectors


/**
 * 安全相关的配置.
 * 我 TM 绝对不会告诉你们这个地方我卡了四天！！！！
 * 资料真少啊，简直是少得一。
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/5 上午12:05
 */
@EnableWebFluxSecurity
class SecurityConfig(
    val resourceConfig: ResourceConfig
) {

  /**
   * webflux 安全配置
   */
  @Bean
  fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
      http
          .authorizeExchange { exchanges: AuthorizeExchangeSpec ->
            exchanges
                // 过滤，RBAC 权限校验
                .anyExchange().access(resourceConfig::authorization)
          }
          .oauth2ResourceServer { oauth2ResourceServer: OAuth2ResourceServerSpec ->
            oauth2ResourceServer
                // jwt 配置，多种情况
                // 可选择 jwt，也可选择 jwk 配置
                .jwt(withDefaults())
                .jwt { it.jwtAuthenticationConverter(grantedAuthoritiesExtractor()) }
          }
          .csrf().disable()
          .build()

  /**
   * 自定义解析扩展.
   */
  fun grantedAuthoritiesExtractor(): Converter<Jwt, Mono<AbstractAuthenticationToken>?> {
    val jwtAuthenticationConverter = JwtAuthenticationConverter()
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(GrantedAuthoritiesExtractor())
    return ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter)
  }

  /**
   * 默认情况下，spring security 会读取 scopes 作为当前登录用户的角色身份。
   * 我们需要使用 RBAC 进行动态鉴权，所以需要读取的不是 scopes 的数据，
   * 而是获取到用户的角色信息，角色信息在 jwt 中。
   */
  internal class GrantedAuthoritiesExtractor : Converter<Jwt, Collection<GrantedAuthority>> {
    override fun convert(jwt: Jwt): Collection<GrantedAuthority> =
        (jwt.claims.getOrDefault(AUTHORITIES, emptyList<Any>()) as Collection<*>)
            .stream()
            .map { it.toString() }
            .map { role: String? -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toList())
  }

}

package cn.edu.gzmu.center.config

import cn.edu.gzmu.center.model.extension.AUTHORITIES
import cn.edu.gzmu.center.model.property.OauthClient
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.server.resource.introspection.NimbusReactiveOpaqueTokenIntrospector
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionClaimNames
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.stream.Collectors

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/5 上午11:21
 */
@Component
class CustomAuthoritiesOpaqueTokenIntrospection(
    val oauthClient: OauthClient
) : ReactiveOpaqueTokenIntrospector {

  private val delegate: ReactiveOpaqueTokenIntrospector =
      NimbusReactiveOpaqueTokenIntrospector(oauthClient.introspectionUri, oauthClient.id, oauthClient.secret)

  override fun introspect(token: String?): Mono<OAuth2AuthenticatedPrincipal> {
    return delegate.introspect(token)
        .log()
        .map { principal: OAuth2AuthenticatedPrincipal ->
          DefaultOAuth2AuthenticatedPrincipal(
              principal.name, principal.attributes, extractAuthorities(principal))
        }
  }

  /**
   * spring security 是使用 scopes 作为授权的
   * 但是我们是用的 RBAC 进行授权，所以需要在这里获取角色信息
   */
  private fun extractAuthorities(principal: OAuth2AuthenticatedPrincipal): Collection<GrantedAuthority> {
    val scopes = principal.getAttribute<List<String>>(OAuth2IntrospectionClaimNames.SCOPE)!!
    // 检验 scope 是否合法
    return if (!scopes.stream().anyMatch(oauthClient.scopes::contains)) listOf()
    else principal.getAttribute<List<String>>(AUTHORITIES)!!.stream()
        .map { role: String? -> SimpleGrantedAuthority(role) }
        .collect(Collectors.toList())
  }
}


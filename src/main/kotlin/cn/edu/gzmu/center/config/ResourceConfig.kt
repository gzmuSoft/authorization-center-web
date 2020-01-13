package cn.edu.gzmu.center.config

import cn.edu.gzmu.center.service.AuthCenterResService
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authorization.AuthorizationContext
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * 资源服务器 RBAC 动态权限控制.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/9 下午10:51
 */
@Component
class ResourceConfig(
    val authCenterResService: AuthCenterResService
) {

  private val antPathMatcher = AntPathMatcher()

  fun authorization(authentication: Mono<Authentication>,
                    authorizationContext: AuthorizationContext): Mono<AuthorizationDecision> {
    val request = authorizationContext.exchange.request
    // 查出所有资源
    // 匹配路径和方法
    // 查找此资源需要的角色
    // 判断用户是否拥有此角色，进程授权
    val map = authCenterResService.findAll()
        .filter {
          antPathMatcher.match(it.url ?: "", request.path.value())
              && it.method.equals(request.methodValue)
        }
        .map { it.id!! }
        .collectList()
        .map { authCenterResService.findRoleNameByResIds(it) }
        .flatMapMany { it }
    return authentication
        .map { obj: Authentication -> obj.authorities }
        .flatMapMany { Flux.fromIterable(it) }
        .map { it.authority }
        .filterWhen { map.hasElement(it) }
        .count()
        .map { it > 0 }
        .map { AuthorizationDecision(it) }
  }

}
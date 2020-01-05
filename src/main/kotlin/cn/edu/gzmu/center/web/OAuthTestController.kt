package cn.edu.gzmu.center.web

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/5 上午12:07
 */
@RestController
class OAuthTestController {

  @GetMapping("/")
  fun index(@AuthenticationPrincipal jwt: Jwt): Mono<String> =
      Mono.just(String.format("Hello, %s!", jwt.subject))

  @GetMapping("/message")
  fun message(): Mono<String> = Mono.just("secret message")

  @PostMapping("/message")
  fun createMessage(@RequestBody message: String?): Mono<String> = Mono.just(String.format("Message was created. Content: %s", message))

  @GetMapping("/foo/1")
  fun foo(@AuthenticationPrincipal principal: OAuth2AuthenticatedPrincipal): Mono<String> =
      Mono.just(principal.getAttribute<Any>("sub").toString() + " is the subject")

  @GetMapping("/foo/2")
  fun foo(authentication: BearerTokenAuthentication): Mono<String>  {
    return Mono.just(authentication.tokenAttributes["sub"].toString() + " is the subject")
  }
}
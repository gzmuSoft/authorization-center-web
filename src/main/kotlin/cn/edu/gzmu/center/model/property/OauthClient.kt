package cn.edu.gzmu.center.model.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/5 下午2:54
 */
@Component
@ConfigurationProperties("oauth2.client")
class OauthClient {
  /**
   * 客户端 id
   */
  lateinit var id: String
  /**
   * 客户端密钥
   */
  lateinit var secret: String
  /**
   * 客户端 scopes
   */
  lateinit var scopes: Array<String>
  /**
   * 客户端 自省 端点
   */
  lateinit var introspectionUri: String
}
package cn.edu.gzmu.center.service.impl

import cn.edu.gzmu.center.model.entity.AuthCenterRes
import cn.edu.gzmu.center.repository.AuthCenterResRepository
import cn.edu.gzmu.center.repository.AuthCenterRoleResRepository
import cn.edu.gzmu.center.service.AuthCenterResService
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/11 下午11:07
 */
@Service
class AuthCenterResServiceImpl(
    val authCenterResRepository: AuthCenterResRepository,
    val authCenterRoleResRepository: AuthCenterRoleResRepository
) : AuthCenterResService {

  override fun findAll(): Flux<AuthCenterRes> = authCenterResRepository.findAll()

  override fun findRoleNameByResIds(ids: List<Long>): Flux<String> =
      authCenterRoleResRepository.findByResIdIn(ids).map { it.roleName!! }

}
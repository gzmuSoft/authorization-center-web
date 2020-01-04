package cn.edu.gzmu.center.service.impl

import cn.edu.gzmu.center.model.entity.SysRes
import cn.edu.gzmu.center.repository.SysResRepository
import cn.edu.gzmu.center.service.SysResService
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/4 下午10:03
 */
@Service
class SysResServiceImpl(val sysResRepository: SysResRepository) : SysResService {

  override fun findById(id: Long): Mono<SysRes> = sysResRepository.findById(id)

}
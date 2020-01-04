package cn.edu.gzmu.center.service

import cn.edu.gzmu.center.model.entity.SysRes
import reactor.core.publisher.Mono

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/4 下午10:02
 */
interface SysResService {

  /**
   * 通过 id 查询
   *
   * @param id iD
   * @return sysRes
   */
  fun findById(id: Long): Mono<SysRes>

}
package cn.edu.gzmu.center.service

import cn.edu.gzmu.center.model.entity.AuthCenterRes
import reactor.core.publisher.Flux

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/11 下午11:06
 */
interface AuthCenterResService {
  /**
   * 查询所有
   *
   * @return flux 结果
   */
  fun findAll(): Flux<AuthCenterRes>

  /**
   * 根据资源的 id 列表查询角色名称集合
   *
   * @param ids 资源id
   * @return 角色名称
   */
  fun findRoleNameByResIds(ids: List<Long>): Flux<String>

}
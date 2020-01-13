package cn.edu.gzmu.center.repository

import cn.edu.gzmu.center.model.entity.AuthCenterRes
import cn.edu.gzmu.center.model.entity.AuthCenterRoleRes
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

/**
 * AuthCenterRes.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/11 下午11:05
 */
interface AuthCenterResRepository : R2dbcRepository<AuthCenterRes, Long>

/**
 * AuthCenterRoleRes.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/11 下午11:05
 */
@Repository
interface AuthCenterRoleResRepository : R2dbcRepository<AuthCenterRoleRes, Long> {

  /**
   * 通过资源 ids 查询
   *
   * @param resIds 资源列表
   * @return 资源角色关联
   */
  @Query("SELECT * FROM auth_center_role_res  WHERE res_id in (:ids) AND is_enable = true")
  fun findByResIdIn(@Param("ids") resIds: List<Long>): Flux<AuthCenterRoleRes>

}
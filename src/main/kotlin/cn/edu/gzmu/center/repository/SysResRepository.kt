package cn.edu.gzmu.center.repository

import cn.edu.gzmu.center.model.entity.SysRes
import org.springframework.data.r2dbc.repository.R2dbcRepository
import org.springframework.stereotype.Repository

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/4 下午8:47
 */
@Repository
interface SysResRepository: R2dbcRepository<SysRes, Long>
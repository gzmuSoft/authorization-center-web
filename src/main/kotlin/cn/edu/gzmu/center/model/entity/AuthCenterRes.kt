package cn.edu.gzmu.center.model.entity

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

/**
 * 授权中心的资源.
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/11 下午11:01
 */
data class AuthCenterRes(
    @Id var id: Long?,
    var name: String?,
    var spell: String?,
    var sort: Int?,
    var createTime: LocalDateTime?,
    var modifyTime: LocalDateTime?,
    var createUser: String?,
    var modifyUser: String?,
    var remark: String?,
    var isEnable: Boolean?,
    var url: String?,
    var describe: String?,
    var method: String?
)
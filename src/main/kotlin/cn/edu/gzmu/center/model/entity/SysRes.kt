package cn.edu.gzmu.center.model.entity

import cn.edu.gzmu.center.annotation.PoKo
import org.springframework.data.annotation.Id
import java.time.LocalDateTime


/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/4 下午8:30
 */
@PoKo
data class SysRes(
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
    var method: String?,
    var scopes: String?
)
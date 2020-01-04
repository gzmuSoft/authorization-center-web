package cn.edu.gzmu.center.web

import cn.edu.gzmu.center.model.entity.SysRes
import cn.edu.gzmu.center.repository.SysResRepository
import cn.edu.gzmu.center.service.SysResService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * .
 *
 * @author <a href="https://echocow.cn">EchoCow</a>
 * @date 2020/1/4 下午9:53
 */
@RestController
@RequestMapping("/sysRes")
class SysResController(val sysResService: SysResService) {

  /**
   * 通过 id 查询
   *
   * @param id id
   * @return 结果
   */
  @GetMapping("/{id}")
  fun sysRes(@PathVariable id: Long): Mono<SysRes> = sysResService.findById(id)

}
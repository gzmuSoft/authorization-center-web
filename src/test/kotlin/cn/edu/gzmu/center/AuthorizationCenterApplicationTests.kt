package cn.edu.gzmu.center

import cn.edu.gzmu.center.model.entity.SysRes
import cn.edu.gzmu.center.repository.SysResRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.test.StepVerifier
import java.time.LocalDateTime

@SpringBootTest
class AuthorizationCenterApplicationTests {

  @Autowired
  private lateinit var sysResRepository: SysResRepository

  @Test
  fun contextLoads() {
    val mono = sysResRepository.findById(1L)
    StepVerifier.create(mono)
        .expectNext(SysRes(1L, "home", null, 1,
            LocalDateTime.of(2019, 8, 6, 6, 55, 21),
            LocalDateTime.of(2019, 8, 6, 6, 55, 21),
            null, null, null, true, "/", "api 主界面", "ALL", "ALL"
        ))
        .verifyComplete()
  }

}

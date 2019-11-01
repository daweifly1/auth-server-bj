package yb.ecp.fast.user.manager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

   @Value("${spring.redis.port}")
   private int L;
   @Value("${spring.redis.host}")
   private String e;
   @Value("${spring.redis.pool.max-active}")
   private int ALLATORIxDEMO;


   @Bean
   JedisPool ALLATORIxDEMO() {
      JedisPoolConfig var1;
      (var1 = new JedisPoolConfig()).setMaxTotal(a.ALLATORIxDEMO);
      return new JedisPool(var1, a.e, a.L);
   }
}

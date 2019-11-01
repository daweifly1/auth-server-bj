package yb.ecp.fast;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaServiceRegistry;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import yb.ecp.fast.annotation.EnableGenServiceClient;
import yb.ecp.fast.infra.annotation.EnableFastAccessGrant;
import yb.ecp.fast.infra.infra.eureka.EurekaDeregister;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.user.dao.entity.AuthDO;
import yb.ecp.fast.user.infra.datainit.DataInitConfig;
import yb.ecp.fast.user.infra.datainit.DataRunner;
import yb.ecp.fast.user.infra.datainit.ScriptLoader;
import yb.ecp.fast.user.service.VO.RoleMenuVO;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableFastAccessGrant
@EnableGenServiceClient
public class AuthServerApplication {

   @Bean
   public CommandLineRunner dataInitial(JdbcTemplate a1, DataInitConfig a2) {
      if(a2.isInitFlag()) {
         DataInitConfig var10000 = a2;
         String[] a3 = ScriptLoader.load(a2.getDdlPath());
         String[] var3 = ScriptLoader.load(var10000.getDataPath());
         return new DataRunner(a1, a3, var3);
      } else {
         LogHelper.monitor(RoleMenuVO.ALLATORIxDEMO("{8wwa6lwq2vw 1c$vyc\"v?,>l>vn6ejv%w2 wv8\">l>vwc\"v?\"3c#c"));
         return null;
      }
   }

   @Bean(
      initMethod = "showDeregisterInfo",
      destroyMethod = "deregister"
   )
   public EurekaDeregister eurekaDeregister(EurekaRegistration a1, EurekaServiceRegistry a2, EurekaClientConfigBean a3) {
      return new EurekaDeregister(a1, a2, a3);
   }

   public static void main(String[] a) {
      System.out.println(AuthDO.ALLATORIxDEMO("\br!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r![!q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"r\br\"q\"q\"q\"q!r\"r\"q\"r\"q\"q!r\"r!r\"r!r\"r!q\"r!r\"q\"q\"q\"q![!q\"q\"q\"q!q!q!q\"q!q\"q!q!q\"r\"q!q!q!q!q\"r\"q\"q\"q\"q\"r\br\"q\"q\"q\"r!r\"r\"q\"r\"q\"r!r\"q!q\"r\"r\"r!q\"q!q\"q\"q\"q\"q![!q\"q\"q\"q!q!q!r!q!r!q!q!q\"r\"q!r!q!q!q!r!q\"q\"q\"q\"r\br\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q![!qM3d$q2c%k>lq`(\"n=c%m#kqM3d$q2c%m#\"\'47qFO\"r\br\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q![!q\"q\"q\"q\"q\"qj%v!8~-&u&,0n=c%m#ka>oq\"q\"q\"q\"q\"q\"r\br\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q\"q![!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r!r\b"));
      SpringApplication.run(AuthServerApplication.class, a);
   }

}

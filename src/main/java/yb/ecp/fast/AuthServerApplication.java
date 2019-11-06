package yb.ecp.fast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaServiceRegistry;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import yb.ecp.fast.annotation.EnableGenServiceClient;
import yb.ecp.fast.infra.annotation.EnableFastAccessGrant;
import yb.ecp.fast.infra.infra.eureka.EurekaDeregister;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableFastAccessGrant
@EnableGenServiceClient
public class AuthServerApplication {

   public static void main(String[] args) {
      SpringApplication.run(AuthServerApplication.class, args);
   }

   @Bean(initMethod = "showDeregisterInfo", destroyMethod = "deregister")
   public EurekaDeregister eurekaDeregister(EurekaRegistration registration, EurekaServiceRegistry serviceRegistry, EurekaClientConfigBean eurekaClientConfigBean) {
      return new EurekaDeregister(registration, serviceRegistry, eurekaClientConfigBean);
   }

}

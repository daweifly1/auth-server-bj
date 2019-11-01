package yb.ecp.fast.user.infra.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yb.ecp.fast.user.dao.entity.AuthDO;
import yb.ecp.fast.user.dao.entity.TemplateDO;

@Configuration
@ServletComponentScan
public class DatasourceConfig {

   @Value("${spring.datasource.url}")
   private String d;
   @Value("${spring.datasource.driverClassName}")
   private String L;
   @Value("${spring.datasource.username}")
   private String e;
   @Value("${spring.datasource.password}")
   private String ALLATORIxDEMO;


   @Bean
   public DataSource getDataSource() throws Exception {
      Properties var1;
      (var1 = new Properties()).put(AuthDO.ALLATORIxDEMO("f#k\'g#A=c\"qc<g"), a.L);
      Object var10002 = var1.put(TemplateDO.ALLATORIxDEMO("CLZ"), a.d);
      var1.put(AuthDO.ALLATORIxDEMO("$q4p?c<g"), a.e);
      var1.put(TemplateDO.ALLATORIxDEMO("NWMEIYLR"), a.ALLATORIxDEMO);
      var1.put(AuthDO.ALLATORIxDEMO("d8n%g#q"), TemplateDO.ALLATORIxDEMO("MB_B"));
      return DruidDataSourceFactory.createDataSource((Properties)var10002);
   }

}

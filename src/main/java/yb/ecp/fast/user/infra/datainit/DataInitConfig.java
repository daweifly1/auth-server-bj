package yb.ecp.fast.user.infra.datainit;

import java.io.File;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.user.infra.datainit.DatabaseType;
import yb.ecp.fast.user.service.VO.MenusAuthsVO;
import yb.ecp.fast.user.service.VO.TmpMenusVO;

@Configuration
public class DataInitConfig implements InitializingBean {

   @Value("${fast.db.initType:init}")
   private String K;
   private String E;
   @Value("${version:0.0.3-SNAPSHOT}")
   private String J;
   @Value("${fast.db.initFlag:false}")
   private boolean F;
   public static final String TABLE_SCRIPT = "ius";
   private String m;
   private String g;
   public static final String SNAPSHOT = "-SNAPSHOT";
   public static final String TO = "TO";
   @Value("${spring.datasource.driverClassName:notFound}")
   private String d;
   public static final String SCRIPTS = "scripts";
   public static final String TABLE = "tables";
   public static final String DATA_SCRIPT = "init";
   private String L;
   @Autowired
   private JdbcTemplate e;
   private DatabaseType ALLATORIxDEMO;
   public static final String DATA = "data";


   public DatabaseType getDatabaseType() {
      return a.ALLATORIxDEMO;
   }

   public void afterPropertiesSet() throws Exception {
      if(TmpMenusVO.ALLATORIxDEMO("?<1}1*/\"0}67>0r.:*6.").equals(a.d)) {
         a.ALLATORIxDEMO = DatabaseType.MYSQL;
         a.g = "select version from version_record order by update_time desc limit 1";
      } else if(MenusAuthsVO.ALLATORIxDEMO("7W9F4@vO<G;<W1S=Wvj*D;I=a*L.@*").equals(a.d)) {
         a.ALLATORIxDEMO = DatabaseType.ORACLE;
         a.g = "SELECT VERSION FROM (\nSELECT VERSION_RECORD.*,ROWNUM rn FROM VERSION_RECORD ORDER BY UPDATE_TIME DESC\n) WHERE rn = 1";
      }

      if(TmpMenusVO.ALLATORIxDEMO("&,7=\'9").equals(a.K)) {
         DataInitConfig var10000;
         label34: {
            try {
               a.m = (String)a.e.queryForObject(a.g, String.class);
            } catch (Exception var2) {
               LogHelper.fatal(MenusAuthsVO.ALLATORIxDEMO(";D6K7QxC1K<<G@*V1J6"), var2);
               var10000 = a;
               break label34;
            }

            var10000 = a;
         }

         if(var10000.m == null) {
            a.m = "0.0.3-SNAPSHOT";
         }

         if(a.J.equals(a.m)) {
            a.F = false;
            LogHelper.monitor(TmpMenusVO.ALLATORIxDEMO("?<86|%9!/:3=|u|7>s*6. 5<2s12(04|:2:(s/0.:,\'/s+:0?|=3\'|!)=}"));
         } else {
            a.J = (new StringBuilder()).insert(0, a.m.replaceAll("-SNAPSHOT", "")).append("TO").append(a.J.replaceAll("-SNAPSHOT", "")).toString();
         }
      }

      if(null != a.ALLATORIxDEMO) {
         StringBuilder var10002 = new StringBuilder;
         super();
         var10002.L = a.append("scripts").append(File.separator).append(a.ALLATORIxDEMO.getType()).append(File.separator).append("tables").append(File.separator).append("ius").append(MenusAuthsVO.ALLATORIxDEMO("u")).append(a.J).append(TmpMenusVO.ALLATORIxDEMO("}/\"0")).toString();
         a.E = (new StringBuilder()).insert(0, "scripts").append(File.separator).append(a.ALLATORIxDEMO.getType()).append(File.separator).append("data").append(File.separator).append("init").append(MenusAuthsVO.ALLATORIxDEMO("u")).append(a.J).append(TmpMenusVO.ALLATORIxDEMO("}/\"0")).toString();
      } else {
         a.F = false;
         LogHelper.monitor(MenusAuthsVO.ALLATORIxDEMO(";D6K7QxC1K<<D,D:D+@xQ!U=\txL6L,+F*L(Q+/L4IxK7QxW-Ky"));
      }

      LogHelper.monitor((new StringBuilder()).insert(0, TmpMenusVO.ALLATORIxDEMO(",?92/6|046?8|7=\'=s5=5\'|03=::;i|")).append(a.toString()).toString());
   }

   public String getVersion() {
      return a.J;
   }

   public String getDdlPath() {
      return a.L;
   }

   public String toString() {
      return (new StringBuilder()).insert(0, MenusAuthsVO.ALLATORIxDEMO("a9Q9l6L,f7K>L?^1K1QI9Be")).append(a.F).append(TmpMenusVO.ALLATORIxDEMO("ps82(2>2/6\b*,6a")).append(a.ALLATORIxDEMO).append(MenusAuthsVO.ALLATORIxDEMO("t.@*V1J6")).append(a.J).append('\'').append(TmpMenusVO.ALLATORIxDEMO("ps870=\'4n{")).append(a.L).append('\'').append(MenusAuthsVO.ALLATORIxDEMO("\txA9Q9u9Q0")).append(a.E).append('\'').append(TmpMenusVO.ALLATORIxDEMO("ps81\n6. 5<2n{")).append(a.m).append('\'').append('}').toString();
   }

   public String getDataPath() {
      return a.E;
   }

   public boolean isInitFlag() {
      return a.F;
   }
}

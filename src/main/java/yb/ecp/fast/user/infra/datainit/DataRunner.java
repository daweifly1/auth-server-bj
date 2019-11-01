package yb.ecp.fast.user.infra.datainit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.TemplateDO;
import yb.ecp.fast.user.service.VO.RoleUserVO;

public class DataRunner implements CommandLineRunner {

   private JdbcTemplate L;
   private String[] e;
   private String[] ALLATORIxDEMO;


   public DataRunner(JdbcTemplate a1, String[] a2, String[] a3) {
      a.L = a1;
      a.ALLATORIxDEMO = a2;
      a.e = a3;
   }

   public void run(String ... a1) throws Exception {
      long var2 = System.currentTimeMillis();
      LogHelper.monitor(TemplateDO.ALLATORIxDEMO("EJWLBDKXP_PQR_B_WXWBE]DWFJE"));
      a.ALLATORIxDEMO(a.ALLATORIxDEMO);
      a.ALLATORIxDEMO(a.e);
      var2 = System.currentTimeMillis() - var2;
      LogHelper.monitor((new StringBuilder()).insert(0, RoleUserVO.ALLATORIxDEMO("]\b\b]F\b]\tQF\b\t\\]")).append(var2).append(TemplateDO.ALLATORIxDEMO("SE")).toString());
   }

   // $FF: synthetic method
   private void ALLATORIxDEMO(String[] a1) {
      int var2 = (a1 = a1).length;

      int var3;
      for(int var10000 = var3 = 0; var10000 < var2; var10000 = var3) {
         String var4;
         if(!StringUtil.isNullOrSpace(var4 = a1[var3])) {
            a.L.execute(var4);
         }

         ++var3;
      }

   }
}

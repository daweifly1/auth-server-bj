package yb.ecp.fast.user.infra.datainit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import yb.ecp.fast.infra.util.StringUtil;

public class DataRunner implements CommandLineRunner {

   private JdbcTemplate L;
   private String[] e;
   private String[] ALLATORIxDEMO;


   public DataRunner(JdbcTemplate a1, String[] a2, String[] a3) {
      L = a1;
      ALLATORIxDEMO = a2;
      e = a3;
   }

   public void run(String ... a1) throws Exception {
      long var2 = System.currentTimeMillis();

      ALLATORIxDEMO(ALLATORIxDEMO);
      ALLATORIxDEMO(e);
      var2 = System.currentTimeMillis() - var2;
   }

   // $FF: synthetic method
   private void ALLATORIxDEMO(String[] a1) {
      int var2 = (a1 = a1).length;

      int var3;
      for(int var10000 = var3 = 0; var10000 < var2; var10000 = var3) {
         String var4;
         if(!StringUtil.isNullOrSpace(var4 = a1[var3])) {
            L.execute(var4);
         }

         ++var3;
      }

   }
}

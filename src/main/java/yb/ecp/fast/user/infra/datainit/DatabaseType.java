package yb.ecp.fast.user.infra.datainit;

import yb.ecp.fast.user.dao.entity.TemplateDO;

public enum DatabaseType {

   private String e;
   MYSQL(TemplateDO.ALLATORIxDEMO("{geoz"), 0, TemplateDO.ALLATORIxDEMO("[GEOZ")),
   ORACLE(TemplateDO.ALLATORIxDEMO("qdurs"), 1, TemplateDO.ALLATORIxDEMO("QD_URS"));
   // $FF: synthetic field
   private static final DatabaseType[] ALLATORIxDEMO;


   // $FF: synthetic method
   private DatabaseType(String var1, int var2, String a1) {
      a.e = a1;
   }

   public String getType() {
      return a.e;
   }

   static {
      DatabaseType[] var10000 = new DatabaseType[2];
      boolean var10002 = true;
      ((Object[])true)[0] = (boolean)MYSQL;
      ((Object[])true)[1] = (boolean)ORACLE;
      ALLATORIxDEMO = true;
   }
}

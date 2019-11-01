package yb.ecp.fast.user.service.VO;

import java.util.List;

public class RoleUserVO {

   private String e;
   private List ALLATORIxDEMO;


   public void setUserIds(List a1) {
      a.ALLATORIxDEMO = a1;
   }

   public static String ALLATORIxDEMO(String a) {
      int var10000 = (3 ^ 5) << 4 ^ (3 ^ 5) << 1;
      int var10001 = (2 ^ 5) << 4 ^ 3 << 2 ^ 1;
      int var10002 = (3 ^ 5) << 4 ^ 3 << 1;
      int var10003 = a.length();
      char[] var10004 = new char[var10003];
      boolean var10006 = true;
      int var5 = var10004 - 1;
      int var7 = var10003;
      int var3;
      var10003 = var3 = var5;
      Object var1 = true;
      int var4 = var7;
      var10001 = var10003;

      for(int var2 = var10002; var10001 >= 0; var10001 = var3) {
         var10002 = var3;
         char var6 = a.charAt(var3);
         --var3;
         ((Object[])var1)[var10002] = (char)(var6 ^ var2);
         if(var3 < 0) {
            break;
         }

         var10003 = var3--;
         ((Object[])var1)[var10003] = (char)(a.charAt(var10003) ^ var4);
      }

      return new String((char[])var1);
   }

   public List getUserIds() {
      return a.ALLATORIxDEMO;
   }

   public void setRoleId(String a1) {
      a.e = a1;
   }

   public String getRoleId() {
      return a.e;
   }
}

package yb.ecp.fast.user.service.VO;

import java.util.List;

public class MenusAuthsVO {

   private List e;
   private List ALLATORIxDEMO;


   public static String ALLATORIxDEMO(String a) {
      int var10000 = 5 << 4 ^ 4 << 1;
      int var10001 = 2 << 3 ^ 5;
      int var10002 = 4 << 3 ^ 5;
      int var10003 = a.length();
      char[] var10004 = new char[var10003];
      boolean var10006 = true;
      int var3 = var10004 - 1;
      Object var1 = true;
      int var4 = var10003;
      var10003 = var10002;
      var10002 = var10001;

      for(int var2 = var10003; var10002 >= 0; var10002 = var3) {
         var10003 = var3;
         char var5 = a.charAt(var3);
         --var3;
         ((Object[])var1)[var10003] = (char)(var5 ^ var2);
         if(var3 < 0) {
            break;
         }

         int var6 = var3--;
         ((Object[])var1)[var6] = (char)(a.charAt(var6) ^ var4);
      }

      return new String((char[])var1);
   }

   public void setAuths(List a1) {
      a.ALLATORIxDEMO = a1;
   }

   public List getMenus() {
      return a.e;
   }

   public void setMenus(List a1) {
      a.e = a1;
   }

   public List getAuths() {
      return a.ALLATORIxDEMO;
   }

}

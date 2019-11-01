package yb.ecp.fast.user.service.VO;

import java.util.List;

public class RoleMenuVO {

   private List d;
   private Integer L;
   private String e;
   private List ALLATORIxDEMO;


   public String getRoleId() {
      return a.e;
   }

   public List getAuthIds() {
      return a.d;
   }

   public void setChannel(Integer a1) {
      a.L = a1;
   }

   public void setRoleId(String a1) {
      a.e = a1;
   }

   public static String ALLATORIxDEMO(String a) {
      int var10000 = 3 << 3 ^ 3 ^ 5;
      int var10002 = 5 << 4 ^ 2 ^ 5;
      int var10003 = a.length();
      char[] var10004 = new char[var10003];
      boolean var10006 = true;
      int var5 = var10004 - 1;
      int var8 = var10003;
      int var3;
      var10003 = var3 = var5;
      Object var1 = true;
      int var4 = var8;
      boolean var6 = true;
      int var10001 = var10003;

      for(int var2 = var10002; var10001 >= 0; var10001 = var3) {
         var10002 = var3;
         char var7 = a.charAt(var3);
         --var3;
         ((Object[])var1)[var10002] = (char)(var7 ^ var2);
         if(var3 < 0) {
            break;
         }

         var10003 = var3--;
         ((Object[])var1)[var10003] = (char)(a.charAt(var10003) ^ var4);
      }

      return new String((char[])var1);
   }

   public List getMenuIds() {
      return a.ALLATORIxDEMO;
   }

   public void setMenuIds(List a1) {
      a.ALLATORIxDEMO = a1;
   }

   public Integer getChannel() {
      return a.L;
   }

   public void setAuthIds(List a1) {
      a.d = a1;
   }
}

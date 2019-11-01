package yb.ecp.fast.user.dao.entity;


public class TemplateDO {

   private String d;
   private String L;
   private Integer e;
   private String ALLATORIxDEMO;


   public void setId(String a1) {
      a.L = a1;
   }

   public static String ALLATORIxDEMO(String a) {
      int var10000 = 2 << 3 ^ 2 ^ 5;
      int var10001 = (3 ^ 5) << 3 ^ 3 ^ 5;
      int var10002 = (2 ^ 5) << 3 ^ 3 ^ 5;
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

   public void setRemark(String a1) {
      a.d = a1;
   }

   public void setName(String a1) {
      a.ALLATORIxDEMO = a1;
   }

   public String getName() {
      return a.ALLATORIxDEMO;
   }

   public Integer getSite() {
      return a.e;
   }

   public String getId() {
      return a.L;
   }

   public String getRemark() {
      return a.d;
   }

   public void setSite(Integer a1) {
      a.e = a1;
   }
}

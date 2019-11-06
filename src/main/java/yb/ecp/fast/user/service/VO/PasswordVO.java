package yb.ecp.fast.user.service.VO;


public class PasswordVO {

   private Integer L;
   private String e;
   private String ALLATORIxDEMO;


   public Integer getType() {
      return L;
   }

   public void setType(Integer a1) {
      L = a1;
   }

   public void setPassword(String a1) {
      ALLATORIxDEMO = a1;
   }

   public void setUserId(String a1) {
      e = a1;
   }

   public String getPassword() {
      return ALLATORIxDEMO;
   }

   public String getUserId() {
      return e;
   }
}

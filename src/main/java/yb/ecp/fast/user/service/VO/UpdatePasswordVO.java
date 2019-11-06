package yb.ecp.fast.user.service.VO;


public class UpdatePasswordVO {

   private String e;
   private String ALLATORIxDEMO;


   public void setOldPassword(String a1) {
      e = a1;
   }

   public String getNewPassword() {
      return ALLATORIxDEMO;
   }

   public String getOldPassword() {
      return e;
   }

   public void setNewPassword(String a1) {
      ALLATORIxDEMO = a1;
   }
}

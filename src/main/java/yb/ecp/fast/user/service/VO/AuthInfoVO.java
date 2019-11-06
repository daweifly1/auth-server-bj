package yb.ecp.fast.user.service.VO;


public class AuthInfoVO {

   private String e;
   private String ALLATORIxDEMO;


   public String getVerifyCode() {
      return ALLATORIxDEMO;
   }

   public void setAuthId(String a1) {
      e = a1;
   }

   public String getAuthId() {
      return e;
   }

   public void setVerifyCode(String a1) {
      ALLATORIxDEMO = a1;
   }
}

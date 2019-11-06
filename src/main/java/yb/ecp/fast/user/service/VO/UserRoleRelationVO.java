package yb.ecp.fast.user.service.VO;


public class UserRoleRelationVO {

   private String e;
   private String ALLATORIxDEMO;


   public void setUserId(String a1) {
      ALLATORIxDEMO = a1;
   }

   public void setRoleId(String a1) {
      e = a1;
   }

   public String getUserId() {
      return ALLATORIxDEMO;
   }

   public String getRoleId() {
      return e;
   }
}

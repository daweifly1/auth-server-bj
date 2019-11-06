package yb.ecp.fast.user.dao.entity;


public class RoleAuthDO {

   private String e;
   private Integer ALLATORIxDEMO;


   public void setRoleId(String a1) {
      e = a1;
   }

   public void setAuthId(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public String getRoleId() {
      return e;
   }

   public Integer getAuthId() {
      return ALLATORIxDEMO;
   }

}

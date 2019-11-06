package yb.ecp.fast.user.dao.entity;


public class RoleMenuDO {

   private Integer L;
   private Integer e;
   private String ALLATORIxDEMO;


   public String getRoleId() {
      return ALLATORIxDEMO;
   }

   public void setRoleId(String a1) {
      ALLATORIxDEMO = a1;
   }

   public void setChannel(Integer a1) {
      L = a1;
   }

   public Integer getChannel() {
      return L;
   }

   public Integer getMenuId() {
      return e;
   }

   public void setMenuId(Integer a1) {
      e = a1;
   }

}

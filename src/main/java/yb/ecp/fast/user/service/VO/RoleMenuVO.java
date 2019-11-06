package yb.ecp.fast.user.service.VO;

import java.util.List;

public class RoleMenuVO {

   private List d;
   private Integer L;
   private String e;
   private List ALLATORIxDEMO;


   public String getRoleId() {
      return e;
   }

   public List getAuthIds() {
      return d;
   }

   public void setChannel(Integer a1) {
      L = a1;
   }

   public void setRoleId(String a1) {
      e = a1;
   }

   public List getMenuIds() {
      return ALLATORIxDEMO;
   }

   public void setMenuIds(List a1) {
      ALLATORIxDEMO = a1;
   }

   public Integer getChannel() {
      return L;
   }

   public void setAuthIds(List a1) {
      d = a1;
   }
}

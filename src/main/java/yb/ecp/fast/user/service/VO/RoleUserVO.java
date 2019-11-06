package yb.ecp.fast.user.service.VO;

import java.util.List;

public class RoleUserVO {

   private String roleId;
   private List userIds;


   public void setUserIds(List a1) {
      userIds = a1;
   }


   public List getUserIds() {
      return userIds;
   }

   public void setRoleId(String a1) {
      roleId = a1;
   }

   public String getRoleId() {
      return roleId;
   }
}

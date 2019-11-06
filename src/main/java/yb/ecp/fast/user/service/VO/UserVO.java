package yb.ecp.fast.user.service.VO;

import java.io.Serializable;
import java.util.List;

public class UserVO extends ProfileVO implements Serializable {

   private List roleIds;
   private String roleNames;
   private String openId;
   private List roleVOs;
   private String deptName;
   private static final long ALLATORIxDEMO = 1L;

   public UserVO() {
   }


   public List getRoleVOs() {
      return roleVOs;
   }

   public String getDeptName() {
      return deptName;
   }

   public void setDeptName(String a1) {
      deptName = a1;
   }

   public void setRoleNames(String a1) {
      roleNames = a1;
   }

   public void setRoleVOs(List a1) {
      roleVOs = a1;
   }

   public List getRoleIds() {
      return roleIds;
   }

   public String getOpenId() {
      return openId;
   }

   public String getRoleNames() {
      return roleNames;
   }

   public void setOpenId(String a1) {
      openId = a1;
   }

   public void setRoleIds(List a1) {
      roleIds = a1;
   }
}

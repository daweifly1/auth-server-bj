package yb.ecp.fast.user.service.VO;

import java.io.Serializable;
import java.util.List;
import yb.ecp.fast.user.service.VO.ProfileVO;

public class UserVO extends ProfileVO implements Serializable {

   private List m;
   private String g;
   private String d;
   private List L;
   private String e;
   private static final long ALLATORIxDEMO = 1L;


   public List getRoleVOs() {
      return a.L;
   }

   public String getDeptName() {
      return a.e;
   }

   public void setDeptName(String a1) {
      a.e = a1;
   }

   public void setRoleNames(String a1) {
      a.g = a1;
   }

   public void setRoleVOs(List a1) {
      a.L = a1;
   }

   public List getRoleIds() {
      return a.m;
   }

   public String getOpenId() {
      return a.d;
   }

   public String getRoleNames() {
      return a.g;
   }

   public void setOpenId(String a1) {
      a.d = a1;
   }

   public void setRoleIds(List a1) {
      a.m = a1;
   }
}

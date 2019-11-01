package yb.ecp.fast.user.service.VO;

import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.TmpMenusVO;

public class UserLoginVO {

   private String d;
   private String L;
   private String e;
   private String ALLATORIxDEMO;


   public void setAuthId(String a1) {
      a.d = a1;
   }

   public void setPassword(String a1) {
      a.e = a1;
   }

   public void setCode(String a1) {
      a.ALLATORIxDEMO = a1;
   }

   public String toString() {
      return (new StringBuilder()).insert(0, RoleMenuVO.ALLATORIxDEMO("W$g%N8e>lM,n8e>lc:gj%")).append(a.L).append('\'').append(TmpMenusVO.ALLATORIxDEMO("|#= /n{")).append(a.e).append('\'').append(RoleMenuVO.ALLATORIxDEMO("{\"4m3gj%")).append(a.ALLATORIxDEMO).append('\'').append('}').toString();
   }

   public String getLoginName() {
      return a.L;
   }

   public void setLoginName(String a1) {
      a.L = a1;
   }

   public String getAuthId() {
      return a.d;
   }

   public String getCode() {
      return a.ALLATORIxDEMO;
   }

   public String getPassword() {
      return a.e;
   }
}

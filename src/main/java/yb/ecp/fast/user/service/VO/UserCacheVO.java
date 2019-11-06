package yb.ecp.fast.user.service.VO;

import java.io.Serializable;

public class UserCacheVO implements Serializable {

   private String E;
   private String J;
   private String F;
   private String m;
   private String g;
   private String d;
   private String L;
   private static final long e = 1L;
   private String ALLATORIxDEMO;


   public void setOpenId(String a1) {
      m = a1;
   }

   public void setUserId(String a1) {
      g = a1;
   }

   public String getOpenId() {
      return m;
   }

   public void setName(String a1) {
      d = a1;
   }

   public String getDeptId() {
      return ALLATORIxDEMO;
   }

   public String getSpaceId() {
      return F;
   }

   public String getUserId() {
      return g;
   }

   public String getMobile() {
      return L;
   }

   public void setMobile(String a1) {
      L = a1;
   }

   public void setSpaceId(String a1) {
      F = a1;
   }

   public void setDeptName(String a1) {
      J = a1;
   }

   public void setDeptCode(String a1) {
      E = a1;
   }

   public String getDeptCode() {
      return E;
   }

   public String getName() {
      return d;
   }

   public void setDeptId(String a1) {
      ALLATORIxDEMO = a1;
   }

   public String getDeptName() {
      return J;
   }
}

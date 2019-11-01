package yb.ecp.fast.user.service.VO;

import java.util.Date;

public class AccountVO {

   private String d;
   private String L;
   private Date e;
   private Integer ALLATORIxDEMO = Integer.valueOf(0);


   public Integer getStatus() {
      return a.ALLATORIxDEMO;
   }

   public String getLoginName() {
      return a.d;
   }

   public Date getLastLoginTime() {
      return a.e;
   }

   public void setLastLoginTime(Date a1) {
      a.e = a1;
   }

   public String getUserId() {
      return a.L;
   }

   public void setUserId(String a1) {
      a.L = a1;
   }

   public void setStatus(Integer a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setLoginName(String a1) {
      a.d = a1;
   }
}

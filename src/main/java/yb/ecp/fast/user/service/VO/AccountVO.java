package yb.ecp.fast.user.service.VO;

import java.util.Date;

public class AccountVO {

   private String d;
   private String userId;
   private Date e;
   private Integer ALLATORIxDEMO = Integer.valueOf(0);


   public Integer getStatus() {
      return ALLATORIxDEMO;
   }

   public String getLoginName() {
      return d;
   }

   public Date getLastLoginTime() {
      return e;
   }

   public void setLastLoginTime(Date a1) {
      e = a1;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String a1) {
      userId = a1;
   }

   public void setStatus(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public void setLoginName(String a1) {
      d = a1;
   }
}

package yb.ecp.fast.user.dao.entity;

import java.sql.Date;

public class AccountDO {

   private String F = "";
   private Date m;
   private String g;
   private Date d;
   private String L;
   private Date e;
   private Integer ALLATORIxDEMO;


   public String getLoginName() {
      return g;
   }

   public void setUserId(String a1) {
      L = a1;
   }

   public void setUpdateTime(Date a1) {
      m = a1;
   }

   public void setStatus(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public void setLoginName(String a1) {
      g = a1 == null ? null : a1.trim();
   }

   public Integer getStatus() {
      return ALLATORIxDEMO;
   }

   public Date getCreateTime() {
      return e;
   }

   public String getLastLoginIp() {
      return F;
   }

   public Date getUpdateTime() {
      return m;
   }

   public void setLastLoginTime(Date a1) {
      d = a1;
   }

   public void setCreateTime(Date a1) {
      e = a1;
   }

   public void setLastLoginIp(String a1) {
      F = a1 == null ? null : a1.trim();
   }

   public String getUserId() {
      return L;
   }

   public AccountDO() {
      super();
   }

   public Date getLastLoginTime() {
      return d;
   }
}

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
      return a.g;
   }

   public void setUserId(String a1) {
      a.L = a1;
   }

   public void setUpdateTime(Date a1) {
      a.m = a1;
   }

   public void setStatus(Integer a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setLoginName(String a1) {
      a.g = a1 == null?null:a1.trim();
   }

   public Integer getStatus() {
      return a.ALLATORIxDEMO;
   }

   public Date getCreateTime() {
      return a.e;
   }

   public String getLastLoginIp() {
      return a.F;
   }

   public Date getUpdateTime() {
      return a.m;
   }

   public void setLastLoginTime(Date a1) {
      a.d = a1;
   }

   public void setCreateTime(Date a1) {
      a.e = a1;
   }

   public void setLastLoginIp(String a1) {
      a.F = a1 == null?null:a1.trim();
   }

   public String getUserId() {
      return a.L;
   }

   public AccountDO() {
      Date var10001 = new Date;
      super(0L);
      var10001.d = a;
   }

   public Date getLastLoginTime() {
      return a.d;
   }
}

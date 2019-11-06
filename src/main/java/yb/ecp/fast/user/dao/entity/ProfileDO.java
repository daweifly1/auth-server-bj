package yb.ecp.fast.user.dao.entity;

import java.sql.Date;

public class ProfileDO {

   private String M;
   private String k;
   private String H;
   private Date j;
   private String C;
   private String K;
   private String E;
   private String J;
   private String F;
   private String m;
   private String g;
   private Integer d;
   private String L;
   private Date e;
   private Integer ALLATORIxDEMO;


   public void setTelephone(String a1) {
      H = a1;
   }

   public String getNickname() {
      return k;
   }

   public void setIcon(String a1) {
      F = a1;
   }

   public String getName() {
      return K;
   }

   public void setUserId(String a1) {
      M = a1;
   }

   public String getSpaceId() {
      return g;
   }

   public String getUserId() {
      return M;
   }

   public void setSex(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public String getIcon() {
      return F;
   }

   public void setLocked(Integer a1) {
      d = a1;
   }

   public void setEmail(String a1) {
      E = a1;
   }

   public String getAreaCode() {
      return J;
   }

   public void setMobile(String a1) {
      m = a1;
   }

   public String getDeptId() {
      return L;
   }

   public Integer getLocked() {
      return d;
   }

   public void setAreaCode(String a1) {
      J = a1;
   }

   public void setSpaceId(String a1) {
      g = a1;
   }

   public Integer getSex() {
      return ALLATORIxDEMO;
   }

   public void setIdNumber(String a1) {
      C = a1;
   }

   public void setNickname(String a1) {
      k = a1;
   }

   public void setUpdateDate(Date a1) {
      e = a1;
   }

   public void setDeptId(String a1) {
      L = a1;
   }

   public Date getCreateDate() {
      return j;
   }

   public String getMobile() {
      return m;
   }

   public String getTelephone() {
      return H;
   }

   public void setCreateDate(Date a1) {
      j = a1;
   }

   public Date getUpdateDate() {
      return e;
   }

   public void setName(String a1) {
      K = a1;
   }

   public String getIdNumber() {
      return C;
   }

   public String getEmail() {
      return E;
   }
}

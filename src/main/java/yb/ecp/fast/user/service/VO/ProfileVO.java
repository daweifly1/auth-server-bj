package yb.ecp.fast.user.service.VO;

import java.io.Serializable;
import java.util.List;
import yb.ecp.fast.user.service.VO.AccountPwdVO;

public class ProfileVO extends AccountPwdVO implements Serializable {

   private String D;
   private String f;
   private String M;
   private Integer k;
   private String H;
   private List j;
   private String C;
   private String K;
   private String E;
   private String J;
   private String F;
   private static final long m = 1L;
   private String g;
   private Integer d;
   private String L;
   private String e;
   private List ALLATORIxDEMO;


   public void setAuthIds(List a1) {
      a.ALLATORIxDEMO = a1;
   }

   public String getMobile() {
      return a.H;
   }

   public String getIdNumber() {
      return a.L;
   }

   public void setMyself(String a1) {
      a.M = a1;
   }

   public void setName(String a1) {
      a.F = a1;
   }

   public List getRoleIds() {
      return a.j;
   }

   public String getEmail() {
      return a.e;
   }

   public void setNickname(String a1) {
      a.C = a1;
   }

   public void setDeptId(String a1) {
      a.f = a1;
   }

   public void setDeptCode(String a1) {
      a.g = a1;
   }

   public void setRoleIds(List a1) {
      a.j = a1;
   }

   public String getDeptCode() {
      return a.g;
   }

   public void setSex(Integer a1) {
      a.d = a1;
   }

   public void setTelephone(String a1) {
      a.J = a1;
   }

   public String getIcon() {
      return a.K;
   }

   public void setIcon(String a1) {
      a.K = a1;
   }

   public Integer getSex() {
      return a.d;
   }

   public String getSpaceId() {
      return a.D;
   }

   public void setMobile(String a1) {
      a.H = a1;
   }

   public String getDeptId() {
      return a.f;
   }

   public List getAuthIds() {
      return a.ALLATORIxDEMO;
   }

   public Integer getLocked() {
      return a.k;
   }

   public String getMyself() {
      return a.M;
   }

   public String getAreaCode() {
      return a.E;
   }

   public String getName() {
      return a.F;
   }

   public void setEmail(String a1) {
      a.e = a1;
   }

   public void setIdNumber(String a1) {
      a.L = a1;
   }

   public void setLocked(Integer a1) {
      a.k = a1;
   }

   public String getTelephone() {
      return a.J;
   }

   public void setSpaceId(String a1) {
      a.D = a1;
   }

   public void setAreaCode(String a1) {
      a.E = a1;
   }

   public String getNickname() {
      return a.C;
   }
}

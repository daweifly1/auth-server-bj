package yb.ecp.fast.user.service.VO;

import java.io.Serializable;
import java.util.List;

public class ProfileVO extends AccountPwdVO implements Serializable {

   private String D;
   private String f;
   private String M;
   private Integer k;
   private String mobile;
   private List<String> roleIds;
   private String C;
   private String K;
   private String E;
   private String J;
   private String F;
   private static final long m = 1L;
   private String g;
   private Integer d;
   private String idNumber;
   private String e;
   private List ALLATORIxDEMO;


   public void setAuthIds(List a1) {
      ALLATORIxDEMO = a1;
   }

   public String getMobile() {
      return mobile;
   }

   public String getIdNumber() {
      return idNumber;
   }

   public void setMyself(String a1) {
      M = a1;
   }

   public void setName(String a1) {
      F = a1;
   }

   public List getRoleIds() {
      return roleIds;
   }

   public String getEmail() {
      return e;
   }

   public void setNickname(String a1) {
      C = a1;
   }

   public void setDeptId(String a1) {
      f = a1;
   }

   public void setDeptCode(String a1) {
      g = a1;
   }

   public void setRoleIds(List a1) {
      roleIds = a1;
   }

   public String getDeptCode() {
      return g;
   }

   public void setSex(Integer a1) {
      d = a1;
   }

   public void setTelephone(String a1) {
      J = a1;
   }

   public String getIcon() {
      return K;
   }

   public void setIcon(String a1) {
      K = a1;
   }

   public Integer getSex() {
      return d;
   }

   public String getSpaceId() {
      return D;
   }

   public void setMobile(String a1) {
      mobile = a1;
   }

   public String getDeptId() {
      return f;
   }

   public List getAuthIds() {
      return ALLATORIxDEMO;
   }

   public Integer getLocked() {
      return k;
   }

   public String getMyself() {
      return M;
   }

   public String getAreaCode() {
      return E;
   }

   public String getName() {
      return F;
   }

   public void setEmail(String a1) {
      e = a1;
   }

   public void setIdNumber(String a1) {
      idNumber = a1;
   }

   public void setLocked(Integer a1) {
      k = a1;
   }

   public String getTelephone() {
      return J;
   }

   public void setSpaceId(String a1) {
      D = a1;
   }

   public void setAreaCode(String a1) {
      E = a1;
   }

   public String getNickname() {
      return C;
   }
}

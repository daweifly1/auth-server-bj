package yb.ecp.fast.user.service.VO;

import java.io.Serializable;

public class RoleVO implements Serializable {

   private Integer F;
   private String m;
   private String g;
   private String d;
   private String L;
   private String e;
   private String ALLATORIxDEMO;


   public String getDeptId() {
      return a.d;
   }

   public String getDeptName() {
      return a.L;
   }

   public String getId() {
      return a.ALLATORIxDEMO;
   }

   public void setDeptName(String a1) {
      a.L = a1;
   }

   public void setRemark(String a1) {
      a.m = a1 == null?null:a1.trim();
   }

   public void setSpaceId(String a1) {
      a.g = a1;
   }

   public Integer getType() {
      return a.F;
   }

   public String getName() {
      return a.e;
   }

   public void setId(String a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setType(Integer a1) {
      a.F = a1;
   }

   public void setName(String a1) {
      a.e = a1 == null?null:a1.trim();
   }

   public String getSpaceId() {
      return a.g;
   }

   public void setDeptId(String a1) {
      a.d = a1;
   }

   public String getRemark() {
      return a.m;
   }
}

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
      return d;
   }

   public String getDeptName() {
      return L;
   }

   public String getId() {
      return ALLATORIxDEMO;
   }

   public void setDeptName(String a1) {
      L = a1;
   }

   public void setRemark(String a1) {
      m = a1 == null?null:a1.trim();
   }

   public void setSpaceId(String a1) {
      g = a1;
   }

   public Integer getType() {
      return F;
   }

   public String getName() {
      return e;
   }

   public void setId(String a1) {
      ALLATORIxDEMO = a1;
   }

   public void setType(Integer a1) {
      F = a1;
   }

   public void setName(String a1) {
      e = a1 == null?null:a1.trim();
   }

   public String getSpaceId() {
      return g;
   }

   public void setDeptId(String a1) {
      d = a1;
   }

   public String getRemark() {
      return m;
   }
}

package yb.ecp.fast.user.dao.entity;


public class RoleDO {

   private String F;
   private String m;
   private Integer g;
   private String d;
   private String L;
   private String e;
   private Integer ALLATORIxDEMO;


   public String getSpaceId() {
      return a.m;
   }

   public void setName(String a1) {
      a.F = a1 == null?null:a1.trim();
   }

   public void setId(String a1) {
      a.e = a1;
   }

   public Integer getType() {
      return a.ALLATORIxDEMO;
   }

   public String getDeptId() {
      return a.L;
   }

   public void setDeptId(String a1) {
      a.L = a1;
   }

   public void setType(Integer a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setRemark(String a1) {
      a.d = a1 == null?null:a1.trim();
   }

   public void setChannel(Integer a1) {
      a.g = a1;
   }

   public void setSpaceId(String a1) {
      a.m = a1;
   }

   public String getId() {
      return a.e;
   }

   public String getName() {
      return a.F;
   }

   public String getRemark() {
      return a.d;
   }

   public Integer getChannel() {
      return a.g;
   }
}

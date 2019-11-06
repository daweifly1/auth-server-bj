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
      return m;
   }

   public void setName(String a1) {
      F = a1 == null?null:a1.trim();
   }

   public void setId(String a1) {
      e = a1;
   }

   public Integer getType() {
      return ALLATORIxDEMO;
   }

   public String getDeptId() {
      return L;
   }

   public void setDeptId(String a1) {
      L = a1;
   }

   public void setType(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public void setRemark(String a1) {
      d = a1 == null?null:a1.trim();
   }

   public void setChannel(Integer a1) {
      g = a1;
   }

   public void setSpaceId(String a1) {
      m = a1;
   }

   public String getId() {
      return e;
   }

   public String getName() {
      return F;
   }

   public String getRemark() {
      return d;
   }

   public Integer getChannel() {
      return g;
   }
}

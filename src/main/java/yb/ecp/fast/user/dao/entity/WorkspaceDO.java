package yb.ecp.fast.user.dao.entity;


public class WorkspaceDO {

   private String F;
   private String m;
   private String g;
   private String d;
   private Integer L;
   private String e;
   private String ALLATORIxDEMO;


   public Integer getSite() {
      return L;
   }

   public String getTempId() {
      return g;
   }

   public void setName(String a1) {
      m = a1;
   }

   public String getStatus() {
      return F;
   }

   public void setTempId(String a1) {
      g = a1;
   }

   public void setRemark(String a1) {
      d = a1;
   }

   public String getType() {
      return e;
   }

   public String getId() {
      return ALLATORIxDEMO;
   }

   public void setStatus(String a1) {
      F = a1;
   }

   public String getRemark() {
      return d;
   }

   public String getName() {
      return m;
   }

   public void setSite(Integer a1) {
      L = a1;
   }

   public void setType(String a1) {
      e = a1;
   }

   public void setId(String a1) {
      ALLATORIxDEMO = a1;
   }
}

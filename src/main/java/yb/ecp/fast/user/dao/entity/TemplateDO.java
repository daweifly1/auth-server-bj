package yb.ecp.fast.user.dao.entity;


public class TemplateDO {

   private String d;
   private String L;
   private Integer e;
   private String ALLATORIxDEMO;


   public void setId(String a1) {
      L = a1;
   }

   public void setRemark(String a1) {
      d = a1;
   }

   public void setName(String a1) {
      ALLATORIxDEMO = a1;
   }

   public String getName() {
      return ALLATORIxDEMO;
   }

   public Integer getSite() {
      return e;
   }

   public String getId() {
      return L;
   }

   public String getRemark() {
      return d;
   }

   public void setSite(Integer a1) {
      e = a1;
   }
}

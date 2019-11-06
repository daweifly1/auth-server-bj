package yb.ecp.fast.user.service.VO;


public class TemplateVO {

   private String d;
   private String L;
   private Integer e;
   private String ALLATORIxDEMO;


   public void setName(String a1) {
      L = a1;
   }

   public void setRemark(String a1) {
      d = a1;
   }

   public void setId(String a1) {
      ALLATORIxDEMO = a1;
   }

   public void setSite(Integer a1) {
      e = a1;
   }

   public String getName() {
      return L;
   }

   public String getId() {
      return ALLATORIxDEMO;
   }

   public Integer getSite() {
      return e;
   }

   public String getRemark() {
      return d;
   }

}

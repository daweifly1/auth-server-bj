package yb.ecp.fast.user.service.VO;


public class AuthVO {

   private Integer d;
   private String L;
   private String e;
   private Integer ALLATORIxDEMO;


   public Integer getAuthId() {
      return d;
   }

   public void setAuthDesc(String a1) {
      L = a1;
   }

   public String getAuthDesc() {
      return L;
   }

   public String getAuthName() {
      return e;
   }

   public Integer getParentId() {
      return ALLATORIxDEMO;
   }

   public void setAuthName(String a1) {
      e = a1;
   }

   public void setAuthId(Integer a1) {
      d = a1;
   }

   public void setParentId(Integer a1) {
      ALLATORIxDEMO = a1;
   }
}

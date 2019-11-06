package yb.ecp.fast.user.dao.entity;


public class TmpAuthDO {

   private String e;
   private Integer ALLATORIxDEMO;


   public void setAuthId(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public void setTmpId(String a1) {
      e = a1;
   }

   public String getTmpId() {
      return e;
   }

   public Integer getAuthId() {
      return ALLATORIxDEMO;
   }
}

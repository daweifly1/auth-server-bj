package yb.ecp.fast.user.service.VO;


public class SessionDataVO {

   String L;
   Object e;
   Integer[] ALLATORIxDEMO;


   public Object getData() {
      return e;
   }

   public Integer[] getCodes() {
      return ALLATORIxDEMO;
   }

   public void setCodes(Integer[] a1) {
      ALLATORIxDEMO = a1;
   }

   public void setData(Object a1) {
      e = a1;
   }

   public String getUserId() {
      return L;
   }

   public void setUserId(String a1) {
      L = a1;
   }

}

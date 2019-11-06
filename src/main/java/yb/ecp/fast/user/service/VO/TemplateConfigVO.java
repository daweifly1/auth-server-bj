package yb.ecp.fast.user.service.VO;

import java.util.List;

public class TemplateConfigVO {

   private List L;
   private String e;
   private List ALLATORIxDEMO;


   public void setMenuIds(List a1) {
      ALLATORIxDEMO = a1;
   }

   public String getTempId() {
      return e;
   }

   public List getMenuIds() {
      return ALLATORIxDEMO;
   }

   public List getAuthIds() {
      return L;
   }

   public void setAuthIds(List a1) {
      L = a1;
   }

   public void setTempId(String a1) {
      e = a1;
   }
}

package yb.ecp.fast.user.service.VO;

import java.util.List;
import yb.ecp.fast.user.service.VO.MenuBase;

public class MenuDisplayVO extends MenuBase {

   private String d;
   private List L;
   private String e;
   private List ALLATORIxDEMO;


   public String getUrl() {
      return e;
   }

   public String getState() {
      return d;
   }

   public void setUrl(String a1) {
      e = a1;
   }

   public void setChildren(List a1) {
      ALLATORIxDEMO = a1;
   }

   public void setState(String a1) {
      d = a1;
   }

   public List getAuths() {
      return L;
   }

   public List getChildren() {
      return ALLATORIxDEMO;
   }

   public void setAuths(List a1) {
      L = a1;
   }

}

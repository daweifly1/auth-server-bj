package yb.ecp.fast.user.service.VO;

import java.util.List;
import yb.ecp.fast.user.service.VO.MenuBase;

public class MenuDisplayVO extends MenuBase {

   private String d;
   private List L;
   private String e;
   private List ALLATORIxDEMO;


   public String getUrl() {
      return a.e;
   }

   public String getState() {
      return a.d;
   }

   public void setUrl(String a1) {
      a.e = a1;
   }

   public void setChildren(List a1) {
      a.ALLATORIxDEMO = a1;
   }

   public void setState(String a1) {
      a.d = a1;
   }

   public List getAuths() {
      return a.L;
   }

   public List getChildren() {
      return a.ALLATORIxDEMO;
   }

   public void setAuths(List a1) {
      a.L = a1;
   }

}

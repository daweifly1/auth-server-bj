package yb.ecp.fast.user.service.VO;

import java.util.List;

public class TmpMenusVO {

   private String e;
   private List ALLATORIxDEMO;


   public String getTemplateId() {
      return e;
   }

   public void setTemplateId(String a1) {
      e = a1;
   }

   public void setMenuIds(List a1) {
      ALLATORIxDEMO = a1;
   }

   public List getMenuIds() {
      return ALLATORIxDEMO;
   }
}

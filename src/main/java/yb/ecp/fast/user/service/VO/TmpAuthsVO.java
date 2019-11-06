package yb.ecp.fast.user.service.VO;

import java.util.List;

public class TmpAuthsVO {

   private String e;
   private List ALLATORIxDEMO;


   public List getAuthIds() {
      return ALLATORIxDEMO;
   }

   public void setTemplateId(String a1) {
      e = a1;
   }

   public void setAuthIds(List a1) {
      ALLATORIxDEMO = a1;
   }

   public String getTemplateId() {
      return e;
   }
}

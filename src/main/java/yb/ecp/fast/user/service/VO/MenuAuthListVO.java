package yb.ecp.fast.user.service.VO;

import java.util.List;

public class MenuAuthListVO {

   private List e;
   private Integer ALLATORIxDEMO;


   public List getAuths() {
      return e;
   }

   public Integer getMenuId() {
      return ALLATORIxDEMO;
   }

   public void setMenuId(Integer a1) {
      ALLATORIxDEMO = a1;
   }

   public void setAuths(List a1) {
      e = a1;
   }
}

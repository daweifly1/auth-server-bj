package yb.ecp.fast.user.service.VO;

import java.util.List;
import yb.ecp.fast.user.service.VO.MenuBase;

public class MenuConfigVO extends MenuBase {

   private List ALLATORIxDEMO;


   public void setChildren(List a1) {
      ALLATORIxDEMO = a1;
   }

   public List getChildren() {
      return ALLATORIxDEMO;
   }
}

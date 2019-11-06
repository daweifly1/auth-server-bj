package yb.ecp.fast.user.service.VO;

import yb.ecp.fast.user.service.VO.AccountVO;

public class AccountPwdVO extends AccountVO {

   private Integer e = Integer.valueOf(0);
   private String ALLATORIxDEMO;


   public Integer getType() {
      return e;
   }

   public String getPassword() {
      return ALLATORIxDEMO;
   }

   public void setType(Integer a1) {
      e = a1;
   }

   public void setPassword(String a1) {
      ALLATORIxDEMO = a1;
   }
}

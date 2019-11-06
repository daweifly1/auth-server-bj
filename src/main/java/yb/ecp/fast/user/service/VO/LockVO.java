package yb.ecp.fast.user.service.VO;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class LockVO {

   @ApiModelProperty("设置锁状态（0启用，1禁用）")
   private Integer e;
   private List ALLATORIxDEMO;


   public List getUserIds() {
      return ALLATORIxDEMO;
   }

   public void setLock(Integer a1) {
      e = a1;
   }

   public Integer getLock() {
      return e;
   }

   public void setUserIds(List a1) {
      ALLATORIxDEMO = a1;
   }
}

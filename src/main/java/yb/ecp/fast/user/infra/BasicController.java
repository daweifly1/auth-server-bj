package yb.ecp.fast.user.infra;

import javax.servlet.http.HttpServletRequest;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.user.dao.entity.AuthDO;
import yb.ecp.fast.user.infra.ErrorCode;

public class BasicController {

   public ActionResult actionResult(ErrorCode code) {
      return this.actionResult(code, (Object)null);
   }

   public ActionResult actionResult(Object value) {
      ErrorCode a = ErrorCode.Success;
      return this.actionResult(a, value);
   }

   public String getUserId(HttpServletRequest request) {
      return request.getHeader(AuthDO.ALLATORIxDEMO("z|w\"g#/8f"));
   }

   public ActionResult actionResult(ErrorCode code, Object value) {
      return new ActionResult(code.getCode(), code.getDesc(), value);
   }
}

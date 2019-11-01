package yb.ecp.fast.user.infra;

import java.sql.SQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.user.dao.entity.AuthDO;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.VO.MenusAuthsVO;

@ControllerAdvice
public class ExceptionAdvice {

   private Logger ALLATORIxDEMO;


   @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
   @ResponseBody
   public ActionResult processSQLIntegrityConstraintViolationException(NativeWebRequest a1, IllegalArgumentException a2) {
      a.ALLATORIxDEMO.error(MenusAuthsVO.ALLATORIxDEMO("迼乲昊乘丏佇夂盜弧幠"), a2);
      ErrorCode a3 = ErrorCode.SQLIntegrityConstraintViolation;
      return new ActionResult(a3.getCode(), a3.getDesc(), (Object)null);
   }

   @ExceptionHandler({IllegalArgumentException.class})
   @ResponseBody
   public ActionResult processIllegalArumentExcption(NativeWebRequest a1, IllegalArgumentException a2) {
      a.ALLATORIxDEMO.error(AuthDO.ALLATORIxDEMO("辈丨晾丂乻伝奶皆当帺"), a2);
      ErrorCode a3 = ErrorCode.IllegalArument;
      return new ActionResult(a3.getCode(), a3.getDesc(), (Object)null);
   }

   public ExceptionAdvice() {
      a.ALLATORIxDEMO = LoggerFactory.getLogger(a.getClass());
   }

   @ExceptionHandler({Exception.class})
   @ResponseBody
   public ActionResult processExcption(NativeWebRequest a1, Exception a2) {
      a.ALLATORIxDEMO.error(MenusAuthsVO.ALLATORIxDEMO("迼乲昊乘丏佇夂盜弧幠"), a2);
      ErrorCode a3 = ErrorCode.UnExceptedError;
      return new ActionResult(a3.getCode(), a3.getDesc(), a2.getMessage());
   }
}

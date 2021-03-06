package yb.ecp.fast.user.infra;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import yb.ecp.fast.infra.infra.ActionResult;

import java.sql.SQLIntegrityConstraintViolationException;


@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

   @ExceptionHandler({Exception.class})
   @ResponseBody
   public ActionResult processExcption(NativeWebRequest request, Exception e) {
      this.log.error("Exception ", e);
      ErrorCode code = ErrorCode.UnExceptedError;

      return new ActionResult(code.getCode(), code.getDesc(), e.getMessage());
   }

   @ExceptionHandler({IllegalArgumentException.class})
   @ResponseBody
   public ActionResult processIllegalArumentExcption(NativeWebRequest request, IllegalArgumentException e) {
      this.log.error("IllegalArgumentException ", e);
      ErrorCode code = ErrorCode.IllegalArument;

      return new ActionResult(code.getCode(), code.getDesc(), null);
   }

   @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
   @ResponseBody
   public ActionResult processSQLIntegrityConstraintViolationException(NativeWebRequest request, IllegalArgumentException e) {
      this.log.error("SQLIntegrityConstraintViolationException 异常", e);
      ErrorCode code = ErrorCode.SQLIntegrityConstraintViolation;
      return new ActionResult(code.getCode(), code.getDesc(), null);
   }
}

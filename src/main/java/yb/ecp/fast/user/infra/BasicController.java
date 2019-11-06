package yb.ecp.fast.user.infra;

import yb.ecp.fast.infra.infra.ActionResult;

import javax.servlet.http.HttpServletRequest;

public class BasicController {
   public <T> ActionResult<T> actionResult(ErrorCode code, T value) {
      return new ActionResult(code.getCode(), code
              .getDesc(), value);
   }

   public <T> ActionResult<T> actionResult(T value) {
      ErrorCode code = ErrorCode.Success;
      return actionResult(code, value);
   }

   public <T> ActionResult<T> actionErrorResult(String errorMsg) {
      ErrorCode code = ErrorCode.Failure;
      return new ActionResult(code.getCode(), errorMsg, null);
   }

   public <T> ActionResult<T> actionErrorResult(int code, String errorMsg) {
      return new ActionResult(code, errorMsg, null);
   }

   public ActionResult actionResult(ErrorCode code) {
      return actionResult(code, null);
   }

   public String getUserId(HttpServletRequest request) {
      String userId = request.getHeader("x-user-id");
      return userId;
   }

   public String getRemoteIp(HttpServletRequest request) {
      String userIp = request.getHeader("x-remote-ip");
      return userIp;
   }
}
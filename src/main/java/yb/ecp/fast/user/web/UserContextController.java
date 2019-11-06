package yb.ecp.fast.user.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.UserContextService;
import yb.ecp.fast.user.service.VO.SessionDataVO;

@RestController
@RequestMapping({"/usrCtx"})
public class UserContextController extends BasicController {

   @Autowired
   UserContextService userContextService;


   @RequestMapping(
      value = {"/authCode"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   @ApiOperation("根据用户查询权限CODE")
   public ActionResult authCode(@RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.userContextService.getAuthCodes(userId));
   }

   @RequestMapping(
      value = {"/session"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   @ApiOperation("将用户信息和权限CODE缓存到数据库")
   public ActionResult session(@RequestBody SessionDataVO sessionDataVO) {
      this.userContextService.setSession(sessionDataVO.getUserId(), sessionDataVO.getData(), sessionDataVO.getCodes(), (String)null);
      return this.actionResult(ErrorCode.Success);
   }

   @RequestMapping(
      value = {"/checkAuthCode"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   @ApiOperation("校验用户权限")
   public ActionResult checkAuthCode(@RequestParam("userId") String userId, @RequestParam("authCode") int authCode) {
      return this.actionResult(this.userContextService.checkAuthCode(userId, Integer.valueOf(authCode)));
   }

   @RequestMapping(
      value = {"/session"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("获取缓存中的用户信息与权限CODE")
   public ActionResult session(@RequestHeader("x-user-id") String userId) {
      SessionDataVO a = new SessionDataVO();
      Object a1 = this.userContextService.getSessionData(userId);
      Integer[] a2 = this.userContextService.getAuthCodes(userId);
      a.setCodes(a2);
      a.setData(a1);
      return this.actionResult(a);
   }

   @RequestMapping(
      value = {"/workspaceId"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   @ApiOperation("查询用户所在工作空间Id")
   public ActionResult getWorkspaceId(@RequestParam("userId") String userId) {
      return this.actionResult(this.userContextService.getWorkspaceId(userId));
   }

   @RequestMapping(
      value = {"/userData"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true,
      actionLevel = 3
   )
   @ApiOperation("获取缓存中的用户信息")
   public ActionResult sessionData(@RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.userContextService.getSessionData(userId));
   }
}

package yb.ecp.fast.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.service.AccountService;
import yb.ecp.fast.user.service.AuthService;
import yb.ecp.fast.user.service.UserContextService;
import yb.ecp.fast.user.service.VO.UpdatePasswordVO;
import yb.ecp.fast.user.service.VO.UserLoginVO;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping({"/auth"})
public class AuthController extends BasicController {

   @Autowired
   UserContextService userContextService;
   @Autowired
   AuthService authService;
   @Autowired
   AccountService accountService;


   @RequestMapping(
      value = {"/kaptcha"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = false
   )
   public void kaptcharImg(@RequestParam("authid") String authId, HttpServletResponse httpServletResponse) throws Exception {
      Object a = null;
      ByteArrayOutputStream a1 = new ByteArrayOutputStream();
      ImageIO.write(this.authService.createVerifyImg(authId), "jpg", a1);
      byte[] a2 = a1.toByteArray();
      httpServletResponse.setHeader("Cache-Control", "no-store");
      httpServletResponse.setHeader("Pragma", "no-cache");
      httpServletResponse.setDateHeader("Expires", 0L);
      httpServletResponse.setContentType("image/jpeg");
      ServletOutputStream var10001 = httpServletResponse.getOutputStream();
      var10001.write(a2);
      var10001.flush();
      var10001.close();
   }


   @RequestMapping(
      value = {"/password"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult password(@RequestHeader("x-user-id") String userId, @RequestBody UpdatePasswordVO updatePasswordVO) throws Exception {
      return this.actionResult(this.accountService.updatePassword(userId, updatePasswordVO));
   }

   @RequestMapping(
      value = {"/login"},
      method = {RequestMethod.POST}
   )
   public ActionResult login(@RequestBody UserLoginVO userLoginVO, @RequestHeader("x-from-site") Integer site) throws Exception {
      Ref ref = new Ref("");
      return this.actionResult(this.accountService.login(userLoginVO, ref, site), ref.get());
   }

   @RequestMapping(
      value = {"/logout"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult logout(@RequestHeader("x-user-id") String userId) {
      this.userContextService.clearSession(userId);
      return this.actionResult(userId);
   }

   @RequestMapping(
      value = {"/authInfo"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = false
   )
   public ActionResult authInfo() {
      return this.actionResult(this.authService.createAuthInfo());
   }

   @RequestMapping(
      value = {"/signin"},
      method = {RequestMethod.POST}
   )
   public ActionResult login(@RequestParam("loginName") String loginName, @RequestParam("password") String password, @RequestParam("authId") String authId, @RequestParam("code") String code, @RequestHeader("x-from-site") Integer site) throws Exception {
      UserLoginVO userLoginVO = new UserLoginVO();
      userLoginVO.setAuthId(authId);
      userLoginVO.setCode(code);
      userLoginVO.setLoginName(loginName);
      userLoginVO.setPassword(password);
      Ref ref = new Ref("");
      return this.actionResult(this.accountService.login(userLoginVO, ref, site), ref.get());
   }
}

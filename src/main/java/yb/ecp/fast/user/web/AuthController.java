package yb.ecp.fast.user.web;

import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.service.AccountService;
import yb.ecp.fast.user.service.AuthService;
import yb.ecp.fast.user.service.UserContextService;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.RoleUserVO;
import yb.ecp.fast.user.service.VO.UpdatePasswordVO;
import yb.ecp.fast.user.service.VO.UserLoginVO;

@RestController
@RequestMapping({"/auth"})
public class AuthController extends BasicController {

   @Autowired
   UserContextService L;
   @Autowired
   AuthService e;
   @Autowired
   AccountService ALLATORIxDEMO;


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
      ImageIO.write(this.e.createVerifyImg(authId), RoleUserVO.ALLATORIxDEMO(""), a1);
      byte[] a2 = a1.toByteArray();
      httpServletResponse.setHeader(RoleMenuVO.ALLATORIxDEMO("A6a?gzA8l#p8n"), RoleUserVO.ALLATORIxDEMO("\bK"));
      httpServletResponse.setHeader(RoleMenuVO.ALLATORIxDEMO("p6e:c"), RoleUserVO.ALLATORIxDEMO("\bK"));
      httpServletResponse.setDateHeader(RoleMenuVO.ALLATORIxDEMO("G/r>p2q"), 0L);
      httpServletResponse.setContentType(RoleUserVO.ALLATORIxDEMO("R\f\r"));
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
      return this.actionResult(this.ALLATORIxDEMO.updatePassword(userId, updatePasswordVO));
   }

   @RequestMapping(
      value = {"/login"},
      method = {RequestMethod.POST}
   )
   public ActionResult login(@RequestBody UserLoginVO userLoginVO, @RequestHeader("x-from-site") Integer site) throws Exception {
      Ref a = new Ref("");
      return this.actionResult(this.ALLATORIxDEMO.login(userLoginVO, a, site), a.get());
   }

   @RequestMapping(
      value = {"/logout"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult logout(@RequestHeader("x-user-id") String userId) {
      this.L.clearSession(userId);
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
      return this.actionResult(this.e.createAuthInfo());
   }

   @RequestMapping(
      value = {"/signin"},
      method = {RequestMethod.POST}
   )
   public ActionResult login(@RequestParam("loginName") String loginName, @RequestParam("password") String password, @RequestParam("authId") String authId, @RequestParam("code") String code, @RequestHeader("x-from-site") Integer site) throws Exception {
      UserLoginVO a = new UserLoginVO();
      a.setAuthId(authId);
      a.setCode(code);
      a.setLoginName(loginName);
      a.setPassword(password);
      Ref a1 = new Ref("");
      return this.actionResult(this.ALLATORIxDEMO.login(a, a1, site), a1.get());
   }
}

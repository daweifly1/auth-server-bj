package yb.ecp.fast.user.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.service.AccountService;
import yb.ecp.fast.user.service.VO.AccountPwdVO;
import yb.ecp.fast.user.service.VO.AccountVO;
import yb.ecp.fast.user.service.VO.PasswordVO;

@RestController
@RequestMapping({"/account"})
public class AccountController extends BasicController {

   @Autowired
   AccountService ALLATORIxDEMO;


   @RequestMapping(
      value = {"/withPwd"},
      method = {RequestMethod.PUT}
   )
   @FastMappingInfo(
      code = 1005L
   )
   public ActionResult accountPwd(@RequestBody AccountPwdVO accountPwdVO) throws Exception {
      return this.actionResult(this.ALLATORIxDEMO.addAccountPwd(accountPwdVO));
   }

   @RequestMapping(
      value = {"/password"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   public ActionResult password(@RequestParam("userId") String userId, @RequestParam("password") String password) throws Exception {
      return this.actionResult(this.ALLATORIxDEMO.updatePassword(userId, password));
   }

   @RequestMapping(
      value = {"/checkLoginName"},
      method = {RequestMethod.GET}
   )
   public ActionResult checkLoginName(String loginName) {
      return this.actionResult(this.ALLATORIxDEMO.checkLoginName(loginName));
   }

   @RequestMapping(
      value = {"/password"},
      method = {RequestMethod.DELETE}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   public ActionResult password(@RequestParam("userId") String userId) throws Exception {
      return this.actionResult(this.ALLATORIxDEMO.removeAllPassword(userId));
   }

   @RequestMapping(
      method = {RequestMethod.PUT}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   public ActionResult account(@RequestBody AccountVO accountVO) {
      return this.actionResult(this.ALLATORIxDEMO.addAccount(accountVO));
   }

   @RequestMapping(
      value = {"/password"},
      method = {RequestMethod.PUT}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   public ActionResult password(@RequestBody PasswordVO passwordVO) throws Exception {
      return this.actionResult(this.ALLATORIxDEMO.insertPassword(passwordVO));
   }

   @RequestMapping(
      value = {"/updateLoginName"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult updateLoginName(@RequestBody AccountVO accountVO) throws Exception {
      return this.actionResult(this.ALLATORIxDEMO.updateLoginName(accountVO));
   }

   @RequestMapping(
      value = {"/resetPassword"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true,
      code = 1010L
   )
   public ActionResult resetPassword(@RequestBody List userIds) throws Exception {
      return this.actionResult(this.ALLATORIxDEMO.resetPassword(userIds));
   }

   @RequestMapping(
      value = {"/removeByUserId"},
      method = {RequestMethod.DELETE}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   public ActionResult removeAccountByUserId(@RequestParam("userId") String userId) {
      return this.actionResult(this.ALLATORIxDEMO.removeAccountByUserId(userId));
   }

   @RequestMapping(
      value = {"/account"},
      method = {RequestMethod.DELETE}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   public ActionResult account(@RequestParam("loginName") String loginName) {
      return this.actionResult(this.ALLATORIxDEMO.removeAccount(loginName));
   }
}

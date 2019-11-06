package yb.ecp.fast.user.web;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.infra.infra.SearchCommonVO;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.VO.AccountPwdVO;
import yb.ecp.fast.user.service.VO.LockVO;
import yb.ecp.fast.user.service.VO.ProfileVO;

@RestController
@RequestMapping({"/profile"})
public class ProfileController extends BasicController {

   @Autowired
   private ProfileService profileService;


   @RequestMapping(
      value = {"/userInfo"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询个人信息")
   public ActionResult queryUserInfo(@RequestHeader("x-user-id") String userId) throws Exception {
      return this.actionResult(this.profileService.queryLoginUser(userId));
   }

   @RequestMapping(
      value = {"/addUserWithAccount"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("添加用户账号和密码(生成用户信息)")
   public ActionResult addUserWithAccount(@RequestBody AccountPwdVO accountPwdVO) throws Exception {
      Ref ref = new Ref("");
      return this.actionResult(this.profileService.addUserWithAccount(accountPwdVO, ref), ref.get());
   }

   @RequestMapping(
      value = {"/updateLock"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true,
      code = 1310L
   )
   @ApiOperation("用户禁用与启用")
   public ActionResult lock(@RequestBody LockVO lockVO) {
      return this.actionResult(this.profileService.updateLock(lockVO));
   }

   @RequestMapping(
      value = {"/update"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("修改个人信息（个人中心）")
   public ActionResult update(@RequestBody ProfileVO profileVO, @RequestHeader("x-user-id") String userId) throws Exception {
      profileVO.setUserId(userId);
      return this.actionResult(this.profileService.update(profileVO));
   }

   @RequestMapping(
      value = {"/getLogin"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询登录用户信息")
   public ActionResult getLoginUser(@RequestHeader("x-user-id") String userId) throws Exception {
      return this.actionResult(this.profileService.queryLoginUser(userId));
   }

   @RequestMapping(
      value = {"/listAll"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询系统所有用户")
   public ActionResult listAll(@RequestBody SearchCommonVO condition, @RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.profileService.listAll(condition, userId).getPageInfo());
   }

   @RequestMapping(
      value = {"/getUserInfo"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true,
      actionLevel = 2
   )
   @ApiOperation("查询Oauth2个人信息")
   public ActionResult getUserInfo(@RequestHeader("x-app-id") String appId, @RequestHeader("x-user-id") String userId) throws Exception {
      return this.actionResult(this.profileService.getUserInfo(appId, userId));
   }

   @RequestMapping(
      value = {"/userDetail"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("获取用户缓存信息")
   public ActionResult getUserDetail(@RequestParam("userId") String userId) {
      return this.actionResult(this.profileService.getUserCache(userId));
   }

   @RequestMapping(
      value = {"/updateUserByAccount"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      actionLevel = 3
   )
   @ApiOperation("根据登录名修改用户信息")
   public ActionResult updateUserByAccount(@RequestBody ProfileVO profileVO) throws Exception {
      return this.actionResult(this.profileService.updateByAccount(profileVO));
   }

   @RequestMapping(
      value = {"/updateUserInfo"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true,
      code = 1308L
   )
   @ApiOperation("修改用户信息（用户管理）")
   public ActionResult updateUserInfo(@RequestBody ProfileVO profileVO) throws Exception {
      return StringUtil.isNullOrEmpty(profileVO.getUserId())?this.actionResult(ErrorCode.IllegalArument):this.actionResult(this.profileService.update(profileVO));
   }

   @RequestMapping(
      value = {"/detail"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询用户列表详细信息")
   public ActionResult detail(@RequestParam("userId") String userId) throws Exception {
      return this.actionResult(this.profileService.item(userId));
   }

   @RequestMapping(
      value = {"/listByWorkspace"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询工作空间下用户集合")
   public ActionResult listByWorkspace(@RequestBody SearchCommonVO condition) {
      return this.actionResult(this.profileService.listByWorkspace(condition).getPageInfo());
   }

   @RequestMapping(
      value = {"/list"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询用户列表")
   public ActionResult list(@RequestBody SearchCommonVO condition, @RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.profileService.list(condition, userId).getPageInfo());
   }

   @RequestMapping(
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true,
      code = 1301L
   )
   @ApiOperation("添加用户")
   public ActionResult add(@RequestBody ProfileVO profileVO, @RequestHeader("x-user-id") String userId) throws Exception {
      Ref ref = new Ref("");
      if(StringUtil.isNullOrSpace(profileVO.getSpaceId())) {
         String a1 = this.profileService.item(userId).getSpaceId();
         profileVO.setSpaceId(a1);
      }

      return this.actionResult(this.profileService.insert(profileVO, ref), ref.get());
   }

   @RequestMapping(
      value = {"/remove"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true,
      code = 1306L
   )
   @ApiOperation("批量删除用户")
   public ActionResult removeUsers(@RequestBody List userIds, @RequestHeader("x-user-id") String userId) throws Exception {
      return userIds.contains(userId)?this.actionResult(ErrorCode.CannotRemoveYouself):this.actionResult(this.profileService.removeByIds(userIds));
   }
}

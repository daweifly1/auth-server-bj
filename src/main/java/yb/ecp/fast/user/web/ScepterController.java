package yb.ecp.fast.user.web;

import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
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
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.MenuService;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.RoleService;
import yb.ecp.fast.user.service.ScepterService;
import yb.ecp.fast.user.service.UserContextService;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.RoleUserVO;
import yb.ecp.fast.user.service.VO.RoleVO;
import yb.ecp.fast.user.service.VO.UserRoleRelationVO;

@RestController
@RequestMapping({"/scepter"})
public class ScepterController extends BasicController {

   @Autowired
   MenuService g;
   @Autowired
   RoleService d;
   @Autowired
   UserContextService L;
   @Autowired
   ScepterService e;
   @Autowired
   ProfileService ALLATORIxDEMO;


   @RequestMapping(
      value = {"roleUser"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("设置一个拥有该角色身份的用户")
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult roleUser(@RequestBody UserRoleRelationVO userRoleVO) {
      return this.actionResult(this.e.setRoleUser(userRoleVO.getRoleId(), userRoleVO.getUserId(), 0));
   }

   @RequestMapping(
      value = {"/getAuthCodes"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult getAuthCodes(
      @RequestParam(
         value = "userId",
         required = false
      ) String userId, @RequestHeader("x-user-id") String selfUserId) {
      return StringUtil.isNullOrSpace(userId)?this.actionResult(this.e.authCodesByUserId(selfUserId)):this.actionResult(this.e.authCodesByUserId(userId));
   }

   @RequestMapping(
      value = {"userRoleIds"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult userRoles(@RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.e.getRoleIdsByUserId(userId));
   }

   @RequestMapping(
      value = {"allRoles"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("获取平台所有角色(机场签单项目使用)")
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult allRoles() {
      return this.actionResult(this.e.getAllRoles(0, (Integer)null, (String)null));
   }

   @RequestMapping(
      value = {"deleteRoles"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("批量删除角色")
   public ActionResult removeRoles(@RequestBody List roleIds) {
      List a;
      return !ListUtil.isNullOrEmpty(a = this.e.checkRolesUsed(roleIds))?this.actionResult(ErrorCode.RoleIsUsed, a):(!ListUtil.isNullOrEmpty(a = this.e.deleteRoles(roleIds))?this.actionResult(ErrorCode.FailedToRemoveRecord, a):this.actionResult(ErrorCode.Success));
   }

   @RequestMapping(
      value = {"/queryRolesByDept"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询部门下角色集合")
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult queryRolesByDepartment(@RequestParam("deptId") String deptId, @RequestHeader("x-user-id") String userId) {
      Ref a = new Ref(new ArrayList());
      return this.actionResult(this.e.queryRolesByDepartment(deptId, userId, a), a.get());
   }

   @RequestMapping(
      value = {"/addMenu"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true,
      code = 1414L
   )
   public ActionResult addMenu(@RequestBody RoleMenuVO roleMenuVO) {
      return this.actionResult(this.e.addMenu(roleMenuVO));
   }

   @RequestMapping(
      value = {"/getMenusAuths"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult getMenusAuths(@RequestHeader("x-user-id") String userId, @RequestParam("roleId") String roleId) {
      return this.actionResult(this.e.listMenuAuthByRoleId(roleId, userId));
   }

   @RequestMapping(
      value = {"roleUserIds"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("按角色标识获取所有拥有该角色身份的用户")
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult roleUserIds(@RequestParam("roleId") String roleId) throws Exception {
      return this.actionResult(this.e.getUserIdByRoleId(roleId));
   }

   @RequestMapping(
      value = {"roles"},
      method = {RequestMethod.GET}
   )
   @ApiOperation(
      value = "查询当前用户所在工作空间的所有角色信息",
      response = RoleVO.class,
      responseContainer = "List"
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult roles(@RequestHeader("x-user-id") String userId) {
      Ref a = new Ref("");
      ErrorCode a1 = this.ALLATORIxDEMO.queryWorkspaceId(userId, a);
      if(ErrorCode.Success != a1) {
         return this.actionResult(a1);
      } else {
         String a2 = (String)a.get();
         return this.ALLATORIxDEMO(0, (Integer)null, a2);
      }
   }

   // $FF: synthetic method
   private ActionResult ALLATORIxDEMO(int a1, Integer a2, String a3) {
      return a.actionResult(a.e.getAllRoles(a1, a2, a3));
   }

   @RequestMapping(
      value = {"removeRoleUser"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("删除一个拥有该角色身份的用户")
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult removeRoleUser(@RequestBody UserRoleRelationVO userRoleVO) {
      return this.actionResult(this.e.removeRoleUser(userRoleVO.getRoleId(), userRoleVO.getUserId()));
   }

   @RequestMapping(
      value = {"/getMenu"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult getMenu(@RequestHeader("x-user-id") String userId, @RequestParam("roleId") String roleId) {
      return this.actionResult(this.e.listMenuIdByRoleId(roleId, userId));
   }

   @RequestMapping(
      value = {"/addMenusAuths"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      needLogin = true,
      code = 1415L
   )
   public ActionResult addMenusAuths(@RequestBody RoleMenuVO roleMenuVO) {
      return this.actionResult(this.e.addMenusAuths(roleMenuVO));
   }

   @RequestMapping(
      value = {"userRolesList"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult userRolesList(@RequestParam("userId") String userId) {
      return this.actionResult(this.e.getRolesByUserId(userId));
   }

   @RequestMapping(
      value = {"role"},
      method = {RequestMethod.GET}
   )
   @ApiOperation(
      value = "根据角色标识查询角色信息",
      response = RoleVO.class
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult role(@RequestParam("roleId") String roleId) {
      return this.actionResult(this.e.getRole(roleId));
   }

   @RequestMapping(
      value = {"editRole"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("编辑角色")
   @FastMappingInfo(
      needLogin = true,
      code = 1404L
   )
   public ActionResult editRole(@RequestBody RoleVO roleVO, @RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.e.editRole(roleVO, userId));
   }

   @RequestMapping(
      value = {"deleteRole"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("删除角色")
   @FastMappingInfo(
      needLogin = true,
      code = 1402L
   )
   public ActionResult removeRole(@RequestParam("roleId") String roleId) {
      return this.actionResult(this.e.deleteRole(roleId));
   }

   @RequestMapping(
      value = {"role"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("增加角色")
   @FastMappingInfo(
      needLogin = true,
      code = 1401L
   )
   public ActionResult role(@RequestBody RoleVO roleVO, @RequestHeader("x-user-id") String userId) {
      Ref a = new Ref("");
      return this.actionResult(this.e.addRole(roleVO, a, userId), a.get());
   }

   @RequestMapping(
      value = {"roleUsers"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("设置一批拥有该角色身份的用户")
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult roleUsers(@RequestBody RoleUserVO roleUserVO) {
      return this.actionResult(this.e.setRoleUser(roleUserVO.getRoleId(), roleUserVO.getUserIds()));
   }
}

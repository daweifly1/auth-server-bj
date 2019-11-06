package yb.ecp.fast.user.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.service.MenuService;
import yb.ecp.fast.user.service.ScepterService;

@RestController
@RequestMapping({"/menu"})
public class MenuController extends BasicController {

   @Autowired
   MenuService menuService;
   @Autowired
   ScepterService scepterService;


   @RequestMapping(
      value = {"/menuAuth"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = false
   )
   public ActionResult listAuthByMenu(@RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.menuService.getAuthListByMenu(userId));
   }

   @RequestMapping(
      value = {"/listMenuBySite"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询权限模板配置页面所需数据")
   public ActionResult listMenuBySite(@RequestParam("site") Integer site) {
      return this.actionResult(this.menuService.listMenuBySite(site));
   }

   @RequestMapping(
      value = {"/listUserAuth"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult listAuthByUser(@RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.menuService.listAuthByUser(userId));
   }

   @RequestMapping(
      value = {"/authorized"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询按钮集合（树形结构）")
   public ActionResult authorizedList(@RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.scepterService.getShownMenuByUser(userId));
   }

   @RequestMapping(
      value = {"/shown"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = false
   )
   public ActionResult shownList(@RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.menuService.getShownMenuTree(userId));
   }

   @RequestMapping(
      value = {"/listAuthorized"},
      method = {RequestMethod.GET}
   )
   @FastMappingInfo(
      needLogin = true
   )
   @ApiOperation("查询按钮集合")
   public ActionResult listAuthorized(@RequestHeader("x-user-id") String userId, @RequestHeader("x-from-site") Integer site) {
      return this.actionResult(this.scepterService.listShownMenuByUser(userId, site));
   }
}

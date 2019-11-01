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
import yb.ecp.fast.feign.FastGenClient;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.TemplateService;
import yb.ecp.fast.user.service.VO.TemplateConfigVO;
import yb.ecp.fast.user.service.VO.TemplateVO;
import yb.ecp.fast.user.service.VO.TmpAuthsVO;
import yb.ecp.fast.user.service.VO.TmpMenusVO;

@RestController
@RequestMapping({"/template"})
public class TemplateController extends BasicController {

   @Autowired
   TemplateService e;
   @Autowired
   private FastGenClient ALLATORIxDEMO;


   @RequestMapping(
      value = {"/queryAuthByTemplate"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询模板下的权限CODE集合")
   @FastMappingInfo(
      needLogin = true
   )
   public ActionResult queryAuthByTemplate(@RequestParam("tempId") String tempId) {
      return this.actionResult(this.e.queryAuthByTemplate(tempId));
   }

   @RequestMapping(
      value = {"/removeMenus"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("批量删除模板下的MENU")
   @FastMappingInfo(
      code = 1514L
   )
   public ActionResult removeMenus(@RequestBody TmpMenusVO tmpMenusVO) {
      return this.actionResult(this.e.removeMenus(tmpMenusVO));
   }

   @RequestMapping(
      value = {"/list"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询所有权限模板")
   public ActionResult listTmp() {
      return this.actionResult(this.e.queryList());
   }

   @RequestMapping(
      value = {"/removeMenuByTemplate"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("删除模板下的所有MENU")
   @FastMappingInfo(
      code = 1513L
   )
   public ActionResult removeMenuByTemplate(@RequestParam("tempId") String tempId) {
      return this.actionResult(this.e.removeMenuByTemplate(tempId));
   }

   @RequestMapping(
      value = {"/removeAuths"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("批量删除模板下的权限")
   @FastMappingInfo(
      code = 1515L
   )
   public ActionResult removeAuths(@RequestBody TmpAuthsVO tmpAuthsVO) {
      return this.actionResult(this.e.removeAuths(tmpAuthsVO));
   }

   @RequestMapping(
      value = {"/queryMenuByTemplate"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询模板下的MENU集合")
   public ActionResult queryMenuByTemplate(@RequestParam("tempId") String tempId) {
      return this.actionResult(this.e.queryMenuByTemplate(tempId));
   }

   @RequestMapping(
      value = {"/configTmpAuth"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("配置模板权限")
   @FastMappingInfo(
      code = 1506L
   )
   public ActionResult configTmpAuth(@RequestBody TmpAuthsVO tmpAuthsVO) {
      return this.actionResult(this.e.configTmpAuth(tmpAuthsVO));
   }

   @RequestMapping(
      value = {"/update"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("修改权限模板信息")
   @FastMappingInfo(
      code = 1504L
   )
   public ActionResult update(@RequestBody TemplateVO templateVO) {
      return this.actionResult(this.e.update(templateVO));
   }

   @RequestMapping(
      value = {"/configTemplate"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("配置模板")
   @FastMappingInfo(
      code = 1508L
   )
   public ActionResult configTemplate(@RequestBody TemplateConfigVO templateConfigVO, @RequestHeader("x-from-site") String site) {
      return this.actionResult(this.e.configTemplate(templateConfigVO));
   }

   @RequestMapping(
      value = {"/itemById"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("通过ID查看模板信息")
   public ActionResult itemById(@RequestParam("id") String id) {
      return this.actionResult(this.e.selectById(id));
   }

   @RequestMapping(
      value = {"/configTmpMenu"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("配置模板MENU")
   @FastMappingInfo(
      code = 1507L
   )
   public ActionResult configTmpMenu(@RequestBody TmpMenusVO tmpMenusVO) {
      return this.actionResult(this.e.configTmpMenu(tmpMenusVO));
   }

   @RequestMapping(
      value = {"/removeAuthByTemplate"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("删除模板下的所有权限")
   public ActionResult removeAuthByTemplate(@RequestParam("tempId") String tempId) {
      return this.actionResult(this.e.removeAuthByTemplate(tempId));
   }

   @RequestMapping(
      value = {"/queryTemplateConfig"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询模板的权限与MENU信息")
   public ActionResult queryTemplateConfig(@RequestParam("tempId") String tempId) {
      return this.actionResult(this.e.queryTemplateConfigById(tempId));
   }

   @RequestMapping(
      value = {"/remove"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("批量删除模板")
   @FastMappingInfo(
      code = 1503L
   )
   public ActionResult remove(@RequestBody List ids) {
      Ref a = new Ref(new ArrayList());
      return this.actionResult(this.e.removeByIds(ids, a), a.get());
   }

   @RequestMapping(
      value = {"/insert"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("新增一个模板")
   @FastMappingInfo(
      code = 1502L
   )
   public ActionResult insert(@RequestBody TemplateVO templateVO) {
      ActionResult a;
      if((a = this.ALLATORIxDEMO.textGuid()).getCode() != ErrorCode.Success.getCode()) {
         return a;
      } else {
         templateVO.setId((String)a.getValue());
         return this.actionResult(this.e.insert(templateVO));
      }
   }
}

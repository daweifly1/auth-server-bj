package yb.ecp.fast.user.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.infra.infra.SearchCommonVO;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.service.DepartmentService;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.VO.DepartmentVO;
import yb.ecp.fast.user.service.VO.ProfileVO;

@RestController
@RequestMapping({"/publicData"})
public class PublicDataController extends BasicController {

   @Autowired
   private ProfileService profileService;
   private String e = "1";
   @Autowired
   private DepartmentService departmentService;


   @RequestMapping(
      value = {"/userDetail"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询用户列表详细信息")
   public ActionResult userDetail(@RequestParam("userId") String userId) throws Exception {
      return this.actionResult(this.profileService.item(userId));
   }

   @RequestMapping(
      value = {"/departmentList"},
      method = {RequestMethod.GET}
   )
   public ActionResult departmentList(@RequestParam("parentId") String parentId, @RequestParam("name") String name) {
      DepartmentVO departmentVO= new DepartmentVO();
      departmentVO.setParentId(parentId);
      departmentVO.setName(name);
      departmentVO.setSpaceId(this.e);
      return this.actionResult(this.departmentService.queryDeptList(departmentVO));
   }

   @RequestMapping(
      value = {"/userList"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("查询用户列表")
   public ActionResult userList(@RequestBody SearchCommonVO condition) {
      if (null == condition.getFilters()) {
         ProfileVO f = new ProfileVO();
         condition.setFilters(f);
      }

      ((ProfileVO)condition.getFilters()).setSpaceId(this.e);
      return this.actionResult(this.profileService.listAll(condition, (String) null).getPageInfo());
   }

   @RequestMapping(
      value = {"/departmentDetail"},
      method = {RequestMethod.GET}
   )
   public ActionResult departmentDetail(@RequestParam("id") String id) {
      return this.actionResult(this.departmentService.item(id));
   }

}

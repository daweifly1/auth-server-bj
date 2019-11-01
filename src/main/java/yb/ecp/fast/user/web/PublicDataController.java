package yb.ecp.fast.user.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
   private ProfileService L;
   private String e = "1";
   @Autowired
   private DepartmentService ALLATORIxDEMO;


   @RequestMapping(
      value = {"/userDetail"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询用户列表详细信息")
   public ActionResult userDetail(@RequestParam("userId") String userId) throws Exception {
      return this.actionResult(this.L.item(userId));
   }

   @RequestMapping(
      value = {"/departmentList"},
      method = {RequestMethod.GET}
   )
   public ActionResult departmentList(@RequestParam("parentId") String parentId, @RequestParam("name") String name) {
      DepartmentVO a;
      DepartmentVO var10002 = a = new DepartmentVO();
      a.setParentId(parentId);
      a.setName(name);
      var10002.setSpaceId(this.e);
      return this.actionResult(this.ALLATORIxDEMO.queryDeptList(a));
   }

   @RequestMapping(
      value = {"/userList"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("查询用户列表")
   public ActionResult userList(@RequestBody SearchCommonVO condition) {
      if(condition.getFilters() == null) {
         ProfileVO var10001 = new ProfileVO;
         condition.<init>();
         var10001.setFilters(condition);
      }

      ((ProfileVO)condition.getFilters()).setSpaceId(this.e);
      return this.actionResult(this.L.listAll(condition, (String)null).getPageInfo());
   }

   @RequestMapping(
      value = {"/departmentDetail"},
      method = {RequestMethod.GET}
   )
   public ActionResult departmentDetail(@RequestParam("id") String id) {
      return this.actionResult(this.ALLATORIxDEMO.item(id));
   }

}

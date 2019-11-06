package yb.ecp.fast.user.web;

import java.util.ArrayList;
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
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.DepartmentService;
import yb.ecp.fast.user.service.UserContextService;
import yb.ecp.fast.user.service.VO.DepartmentVO;

@RequestMapping({"/department"})
@RestController
public class DepartmentController extends BasicController {

   @Autowired
   private UserContextService userContextService;
   @Autowired
   private FastGenClient fastGenClient;
   @Autowired
   private DepartmentService departmentService;


   @RequestMapping(
      value = {"/remove"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      code = 1105L
   )
   public ActionResult delete(@RequestBody DepartmentVO departmentVO, @RequestHeader("x-user-id") String userId) {
      String a;
      if(StringUtil.isNullOrEmpty(a = this.userContextService.getWorkspaceId(userId))) {
         return this.actionResult(ErrorCode.NeedLogin);
      } else {
         Ref a1 = new Ref(new ArrayList());
         return this.actionResult(this.departmentService.removeByCode(departmentVO.getId(), a, a1), a1.get());
      }
   }

   @RequestMapping(
      value = {"/update"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      code = 1104L
   )
   public ActionResult update(@RequestBody DepartmentVO departmentVO) {
      return this.actionResult(this.departmentService.update(departmentVO));
   }

   @RequestMapping(
      value = {"/item"},
      method = {RequestMethod.GET}
   )
   public ActionResult item(@RequestParam("id") String id) {
      return this.actionResult(this.departmentService.item(id));
   }

   @RequestMapping(
      value = {"/list"},
      method = {RequestMethod.GET}
   )
   public ActionResult list(@RequestParam("id") String id, @RequestHeader("x-user-id") String userId) {
      return this.actionResult(this.departmentService.listDept(id, userId));
   }

   @RequestMapping(
      value = {"/insert"},
      method = {RequestMethod.POST}
   )
   @FastMappingInfo(
      code = 1103L
   )
   public ActionResult insert(@RequestBody DepartmentVO departmentVO, @RequestHeader("x-user-id") String userId) {
      String a;
      if(StringUtil.isNullOrEmpty(a = this.userContextService.getWorkspaceId(userId))) {
         return this.actionResult(ErrorCode.NeedLogin);
      } else {
         ActionResult a1;
         if((a1 = this.fastGenClient.textGuid()).getCode() != ErrorCode.Success.getCode()) {
            return a1;
         } else {
            departmentVO.setId((String)a1.getValue());
            ErrorCode a2 = this.departmentService.insert(departmentVO, a);
            return ErrorCode.Success == a2?this.actionResult(a2, a1.getValue()):this.actionResult(a2);
         }
      }
   }
}

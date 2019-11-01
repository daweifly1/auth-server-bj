package yb.ecp.fast.user.web;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.user.infra.BasicController;
import yb.ecp.fast.user.service.WorkspaceService;
import yb.ecp.fast.user.service.VO.WorkspaceVO;

@RestController
@RequestMapping({"/workspace"})
public class WorkSpaceController extends BasicController {

   @Autowired
   WorkspaceService ALLATORIxDEMO;


   @RequestMapping(
      value = {"/item"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查看工作空间信息")
   public ActionResult item(@RequestParam("id") String id) {
      return this.actionResult(this.ALLATORIxDEMO.item(id));
   }

   @RequestMapping(
      value = {"/unlock"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("解锁企业工作空间")
   public ActionResult unlock(@RequestParam("workspaceId") String workspaceId) {
      return this.actionResult(this.ALLATORIxDEMO.unlock(workspaceId));
   }

   @RequestMapping(
      value = {"/lock"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("冻结锁定企业工作空间")
   public ActionResult lock(@RequestParam("workspaceId") String workspaceId) {
      return this.actionResult(this.ALLATORIxDEMO.lock(workspaceId));
   }

   @RequestMapping(
      value = {"/remove"},
      method = {RequestMethod.DELETE}
   )
   @ApiOperation("删除企业工作空间")
   public ActionResult remove(@RequestParam("workspaceId") String workspaceId) {
      return this.actionResult(this.ALLATORIxDEMO.remove(workspaceId));
   }

   @RequestMapping(
      value = {"/remove"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("批量删除工作空间")
   public ActionResult remove(@RequestBody List ids) {
      return this.actionResult(this.ALLATORIxDEMO.removeByIds(ids));
   }

   @RequestMapping(
      value = {"/add"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("增加工作空间")
   public ActionResult insert(@RequestBody WorkspaceVO workspaceVO) throws Exception {
      return this.actionResult(this.ALLATORIxDEMO.insert(workspaceVO));
   }

   @RequestMapping(
      value = {"/updateTemplate"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("修改工作空间对应的权限模板ID")
   public ActionResult updateTemplate(@RequestParam("workspaceId") String workspaceId, @RequestParam("templateId") String templateId) {
      return this.actionResult(this.ALLATORIxDEMO.updateTemplate(workspaceId, templateId));
   }

   @RequestMapping(
      value = {"/update"},
      method = {RequestMethod.POST}
   )
   @ApiOperation("修改工作空间信息")
   public ActionResult update(@RequestBody WorkspaceVO workspaceVO) {
      return this.actionResult(this.ALLATORIxDEMO.update(workspaceVO));
   }

   @RequestMapping(
      value = {"/list"},
      method = {RequestMethod.GET}
   )
   @ApiOperation("查询所有工作空间")
   public ActionResult listSpace() {
      return this.actionResult(this.ALLATORIxDEMO.queryList());
   }
}

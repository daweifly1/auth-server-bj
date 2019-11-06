package yb.ecp.fast.user.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.user.dao.entity.DepartmentDO;
import yb.ecp.fast.user.dao.entity.WorkspaceDO;
import yb.ecp.fast.user.dao.mapper.DepartmentMapper;
import yb.ecp.fast.user.dao.mapper.WorkspaceMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.VO.ProfileVO;
import yb.ecp.fast.user.service.VO.TemplateVO;
import yb.ecp.fast.user.service.VO.WorkspaceVO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class WorkspaceService {

   @Value("${role.admin.roleId}")
   private String roleAdminId;
   @Autowired
   private TemplateService templateService;
   @Autowired
   private RoleService roleService;
   @Autowired
   WorkspaceMapper workspaceMapper;
   @Autowired
   private ProfileService profileService;
   @Autowired
   private DepartmentMapper departmentMapper;

   @Transactional(rollbackFor = {Exception.class})
   public ErrorCode removeByIds(List<String> ids) {
      List<String> result = checkWorkspace(ids);
      if (CollectionUtils.isEmpty(result)) {
         result = new ArrayList();
         for (String id : ids) {
            int ret = this.workspaceMapper.removeById(id);
            if (0 >= ret) {
               result.add(id);
            }
         }
      }
      if (CollectionUtils.isEmpty(result)) {
         return ErrorCode.Success;
      }
      log.debug("工作空间删除失败！");
      return ErrorCode.FailedToRemoveRecord;
   }

   private List<String> checkWorkspace(List<String> ids) {
      List<String> result = new ArrayList();
      for (String id : ids) {
         DepartmentDO departmentDO = new DepartmentDO();
         departmentDO.setSpaceId(id);
         List<DepartmentDO> departmentDOs = departmentMapper.queryDeptList(departmentDO);
         if (!CollectionUtils.isEmpty(departmentDOs)) {
            result.add(id);
         }
      }
      return result;
   }

   public ErrorCode update(WorkspaceVO workspaceVO) {
      WorkspaceDO workspaceDO = new WorkspaceDO();
      BeanUtils.copyProperties(workspaceVO, workspaceDO);
      int ret = this.workspaceMapper.updateById(workspaceDO);
      if (0 >= ret) {
         return ErrorCode.FailedToUpdateRecord;
      }
      return ErrorCode.Success;
   }

   public ErrorCode lock(String a1) {
      WorkspaceDO var2 = new WorkspaceDO();
      var2.setId(a1);
      var2.setStatus("1");
      workspaceMapper.updateById(var2);
      return ErrorCode.Success;
   }

   public ErrorCode insert(WorkspaceVO workspaceVO) throws Exception {
      WorkspaceDO workspaceDO = new WorkspaceDO();
      BeanUtils.copyProperties(workspaceVO, workspaceDO);
      int ret = this.workspaceMapper.insert(workspaceDO);
      if (0 >= ret) {
         log.error("添加工作空间步骤错误！", Integer.valueOf(ErrorCode.FailedToInsertRecord.getCode()));
         return ErrorCode.FailedToInsertRecord;
      }
      return addDefaultUser(workspaceVO);
   }

   private ErrorCode addDefaultUser(WorkspaceVO workspaceVO)
           throws Exception {
      ProfileVO profileVO = new ProfileVO();
      List<String> roleIds = new ArrayList();

      roleIds.add(this.roleAdminId);

      profileVO.setName("管理员");
      if (StringUtils.isBlank(workspaceVO.getAccount())) {
         profileVO.setLoginName(workspaceVO.getId());
      } else {
         profileVO.setLoginName(workspaceVO.getAccount());
      }
      profileVO.setRoleIds(roleIds);
      profileVO.setPassword(workspaceVO.getPassword());
      profileVO.setSpaceId(workspaceVO.getId());
      profileVO.setDeptId("0");
      ErrorCode addUser = this.profileService.insert(profileVO, new Ref(""));
      if (addUser.getCode() != 0) {
         return addUser;
      }
      return ErrorCode.Success;
   }

   public ErrorCode updateTemplate(String a1, String a2) {
      WorkspaceVO var3 = new WorkspaceVO();
      var3.setId(a1);
      var3.setTempId(a2);
      ErrorCode a3;
      if ((a3 = update(var3)) != ErrorCode.Success) {
         return a3;
      } else {
         return roleService.releaseByWorkspace(a1);
      }
   }

   public List queryAuthsByWorkspace(String a1) {
      WorkspaceDO a2 = workspaceMapper.selectById(a1);
      if(null == a2) {
         return new ArrayList();
      } else {
         TemplateVO a3 = templateService.selectById(a2.getTempId());
         return (List) (null == a3 ? new ArrayList() : templateService.queryAuthByTemplate(a3.getId()));
      }
   }

   public ErrorCode remove(String a1) {
      workspaceMapper.removeById(a1);
      return ErrorCode.Success;
   }

   public ErrorCode unlock(String a1) {
      WorkspaceDO var2 = new WorkspaceDO();
      var2.setId(a1);
      var2.setStatus("0");
      workspaceMapper.updateById(var2);
      return ErrorCode.Success;
   }

   public WorkspaceVO item(String a1) {
      WorkspaceVO var2 = new WorkspaceVO();
      BeanUtils.copyProperties(workspaceMapper.selectById(a1), var2);
      return var2;
   }

   public List queryList() {
      ArrayList var1 = new ArrayList();
      Iterator var2;
      Iterator var10000 = var2 = workspaceMapper.queryList().iterator();

      while(var10000.hasNext()) {
         WorkspaceDO var3 = (WorkspaceDO)var2.next();
         WorkspaceVO var4 = new WorkspaceVO();
         var10000 = var2;
         BeanUtils.copyProperties(var3, var4);
         var1.add(var4);
      }

      return var1;
   }


}

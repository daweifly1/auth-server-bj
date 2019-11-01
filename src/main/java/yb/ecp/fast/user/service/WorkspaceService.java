package yb.ecp.fast.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.DepartmentDO;
import yb.ecp.fast.user.dao.entity.WorkspaceDO;
import yb.ecp.fast.user.dao.mapper.DepartmentMapper;
import yb.ecp.fast.user.dao.mapper.WorkspaceMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.RoleService;
import yb.ecp.fast.user.service.TemplateService;
import yb.ecp.fast.user.service.VO.ProfileVO;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.TemplateVO;
import yb.ecp.fast.user.service.VO.TmpMenusVO;
import yb.ecp.fast.user.service.VO.WorkspaceVO;

@Service
public class WorkspaceService {

   @Value("${role.admin.roleId}")
   private String m;
   @Autowired
   private TemplateService g;
   @Autowired
   private RoleService d;
   @Autowired
   WorkspaceMapper L;
   @Autowired
   private ProfileService e;
   @Autowired
   private DepartmentMapper ALLATORIxDEMO;


   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode removeByIds(List a1) {
      Object var2;
      if(ListUtil.isNullOrEmpty(var2 = a.ALLATORIxDEMO(a1))) {
         var2 = new ArrayList();
         Iterator a2 = a1.iterator();

         while(a2.hasNext()) {
            String var3 = (String)a2.next();
            int var4 = a.L.removeById(var3);
            if(0 >= var4) {
               ((List)var2).add(var3);
            }
         }
      }

      if(ListUtil.isNullOrEmpty((List)var2)) {
         return ErrorCode.Success;
      } else {
         LogHelper.debug(TmpMenusVO.ALLATORIxDEMO("嶹伏稦閧剼阷奭赶｝"));
         return ErrorCode.FailedToRemoveRecord;
      }
   }

   public ErrorCode lock(String a1) {
      WorkspaceDO var2 = new WorkspaceDO();
      var2.setId(a1);
      var2.setStatus("1");
      a.L.updateById(var2);
      return ErrorCode.Success;
   }

   public ErrorCode insert(WorkspaceVO a1) throws Exception {
      WorkspaceDO var2 = new WorkspaceDO();
      BeanUtils.copyProperties(a1, var2);
      int var3 = a.L.insert(var2);
      if(0 >= var3) {
         LogHelper.error(RoleMenuVO.ALLATORIxDEMO("淹勷巧伋穸閣歧髳锛许＃"), ErrorCode.FailedToInsertRecord.getCode());
         return ErrorCode.FailedToInsertRecord;
      } else {
         return a.ALLATORIxDEMO(a1);
      }
   }

   // $FF: synthetic method
   private ErrorCode ALLATORIxDEMO(WorkspaceVO a1) throws Exception {
      ProfileVO var2 = new ProfileVO();
      ArrayList var3 = new ArrayList();
      var3.add(a.m);
      var2.setName(TmpMenusVO.ALLATORIxDEMO("篽瑕各"));
      ProfileVO var10000;
      if(StringUtil.isNullOrEmpty(a1.getAccount())) {
         var10000 = var2;
         var2.setLoginName(a1.getId());
      } else {
         var10000 = var2;
         var2.setLoginName(a1.getAccount());
      }

      var10000.setRoleIds(var3);
      var2.setPassword(a1.getPassword());
      var2.setSpaceId(a1.getId());
      var2.setDeptId("0");
      ProfileService var4 = a.e;
      Ref var10002 = new Ref;
      var2.<init>("");
      ErrorCode a2;
      return (a2 = var2.insert(var10002, var2)).getCode() != 0?a2:ErrorCode.Success;
   }

   public ErrorCode updateTemplate(String a1, String a2) {
      WorkspaceVO var3 = new WorkspaceVO();
      var3.setId(a1);
      var3.setTempId(a2);
      ErrorCode a3;
      if((a3 = a.update(var3)) != ErrorCode.Success) {
         LogHelper.error(RoleMenuVO.ALLATORIxDEMO("侹攻嶲佞稭闶盓杁阇樣木擏伋夳赲＃"), a3.getCode());
         return a3;
      } else {
         return a.d.releaseByWorkspace(a1);
      }
   }

   public List queryAuthsByWorkspace(String a1) {
      WorkspaceDO a2 = a.L.selectById(a1);
      if(null == a2) {
         return new ArrayList();
      } else {
         TemplateVO a3 = a.g.selectById(a2.getTempId());
         return (List)(null == a3?new ArrayList():a.g.queryAuthByTemplate(a3.getId()));
      }
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(List a1) {
      ArrayList var2 = new ArrayList();
      Iterator a2 = a1.iterator();

      while(a2.hasNext()) {
         String var3 = (String)a2.next();
         DepartmentDO var4 = new DepartmentDO();
         var4.setSpaceId(var3);
         if(!ListUtil.isNullOrEmpty(a.ALLATORIxDEMO.queryDeptList(var4))) {
            var2.add(var3);
         }
      }

      return var2;
   }

   public ErrorCode remove(String a1) {
      a.L.removeById(a1);
      return ErrorCode.Success;
   }

   public ErrorCode unlock(String a1) {
      WorkspaceDO var2 = new WorkspaceDO();
      var2.setId(a1);
      var2.setStatus("0");
      a.L.updateById(var2);
      return ErrorCode.Success;
   }

   public WorkspaceVO item(String a1) {
      WorkspaceVO var2 = new WorkspaceVO();
      BeanUtils.copyProperties(a.L.selectById(a1), var2);
      return var2;
   }

   public List queryList() {
      ArrayList var1 = new ArrayList();
      Iterator var2;
      Iterator var10000 = var2 = a.L.queryList().iterator();

      while(var10000.hasNext()) {
         WorkspaceDO var3 = (WorkspaceDO)var2.next();
         WorkspaceVO var4 = new WorkspaceVO();
         var10000 = var2;
         BeanUtils.copyProperties(var3, var4);
         var1.add(var4);
      }

      return var1;
   }

   public ErrorCode update(WorkspaceVO a1) {
      WorkspaceDO var2 = new WorkspaceDO();
      BeanUtils.copyProperties(a1, var2);
      int a2 = a.L.updateById(var2);
      return 0 >= a2?ErrorCode.FailedToUpdateRecord:ErrorCode.Success;
   }
}

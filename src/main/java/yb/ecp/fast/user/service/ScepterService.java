package yb.ecp.fast.user.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yb.ecp.fast.feign.GenClient;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.DepartmentDO;
import yb.ecp.fast.user.dao.entity.ProfileDO;
import yb.ecp.fast.user.dao.entity.RoleAuthDO;
import yb.ecp.fast.user.dao.entity.RoleDO;
import yb.ecp.fast.user.dao.entity.RoleMenuDO;
import yb.ecp.fast.user.dao.mapper.DepartmentMapper;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.dao.mapper.RoleAuthMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.dao.mapper.RoleMenuMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.MenuService;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.RoleService;
import yb.ecp.fast.user.service.TemplateService;
import yb.ecp.fast.user.service.WorkspaceService;
import yb.ecp.fast.user.service.VO.MenuDisplayVO;
import yb.ecp.fast.user.service.VO.MenusAuthsVO;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.RoleVO;
import yb.ecp.fast.user.service.VO.TemplateVO;

@Service
public class ScepterService {

   @Autowired
   private WorkspaceService H;
   @Autowired
   RoleMapper j;
   @Autowired
   private MenuService C;
   @Autowired
   private RoleService K;
   @Autowired
   private ProfileMapper E;
   @Autowired
   RoleAuthMapper J;
   @Autowired
   private TemplateService F;
   @Value("${role.default.roleId}")
   private String m;
   @Autowired
   RoleMenuMapper g;
   @Autowired
   private ProfileService d;
   @Value("${role.admin.roleId}")
   private String L;
   @Autowired
   private GenClient e;
   @Autowired
   private DepartmentMapper ALLATORIxDEMO;


   public List authCodesByUserId(String a1) {
      ProfileDO var2 = a.E.selectById(a1);
      if(null == var2) {
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("甍扯俄怷丨嬀圍ｙ")).toString());
         return new ArrayList();
      } else {
         List var3;
         if(ListUtil.isNullOrEmpty(var3 = a.j.selectRoleIdsByUserId(a1))) {
            var3.add(a.m);
         }

         return a.K.ifAdminRole(var3)?a.H.queryAuthsByWorkspace(var2.getSpaceId()):a.C.queryAuthIdsByRoleIds(var3);
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode addMenusAuths(RoleMenuVO a1) {
      ErrorCode var2 = a.ALLATORIxDEMO(a1);
      if(0 != var2.getCode()) {
         return var2;
      } else {
         var2 = a.addMenu(a1);
         return 0 != var2.getCode()?var2:var2;
      }
   }

   public ErrorCode queryRolesByDepartment(String a1, String a2, Ref a3) {
      String var4 = "";
      if(StringUtil.isNullOrEmpty(a1) || a1.equals("0")) {
         a1 = "0";
         Ref var5 = new Ref("");
         ErrorCode var6 = a.d.queryWorkspaceId(a2, var5);
         if(ErrorCode.Success != var6) {
            return var6;
         }

         var4 = (String)var5.get();
      }

      List var10 = a.j.queryRolesByDepartment(a1, var4);
      ArrayList var11 = new ArrayList();
      Iterator a4;
      Iterator var10000 = a4 = var10.iterator();

      while(var10000.hasNext()) {
         RoleDO a5 = (RoleDO)a4.next();
         RoleVO var9 = new RoleVO();
         var10000 = a4;
         BeanUtils.copyProperties(a5, var9);
         var11.add(var9);
      }

      a3.set(var11);
      return ErrorCode.Success;
   }

   // $FF: synthetic method
   private List ALLATORIxDEMO(List a1, List a2) {
      ArrayList var3 = new ArrayList();
      Iterator a3 = a1.iterator();

      while(a3.hasNext()) {
         Integer var4 = (Integer)a3.next();
         if(a2.contains(var4)) {
            var3.add(var4);
         }
      }

      return var3;
   }

   public List getShownMenuByUser(String a1) {
      TemplateVO var2 = a.F.queryTempByUserId(a1);
      if(null == var2) {
         return new ArrayList();
      } else {
         List var3 = a.C.getShownMenuTree(a1);
         List var6 = a.F.queryMenuByTemplate(var2.getId());
         List var4;
         if(ListUtil.isNullOrEmpty(var4 = a.getRoleIdsByUserId(a1))) {
            var4.add(a.m);
         }

         ScepterService var10000;
         List var5;
         if(var4.contains(a.L)) {
            LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("拀村箄瑞命覊艗望陵ｙ")).toString());
            var5 = var6;
            var10000 = a;
         } else {
            var10000 = a;
            var5 = a.g.getMenuIdsByRoleIds(var4);
            var6.retainAll(var5);
         }

         var10000.ALLATORIxDEMO(var3, var5);
         return var3;
      }
   }

   public List checkRolesUsed(List a1) {
      ArrayList var2 = new ArrayList();
      Iterator a2 = a1.iterator();

      while(a2.hasNext()) {
         String var3 = (String)a2.next();
         Long var4 = a.j.countUserByRoleId(var3);
         if(0L < var4.longValue() || a.L.equals(var3)) {
            RoleVO var6 = a.getRole(var3);
            var2.add(var6.getName());
         }
      }

      return var2;
   }

   public ErrorCode removeRoleUser(String a1, String a2) {
      a.j.removeUserRole(a1, a2);
      return ErrorCode.Success;
   }

   public ErrorCode removeRoleByUser(String a1) {
      a.j.removeRoleByUser(a1);
      return ErrorCode.Success;
   }

   // $FF: synthetic method
   private String ALLATORIxDEMO(RoleDO a1) {
      if(null != a1 && !StringUtil.isNullOrEmpty(a1.getDeptId()) && !"0".equals(a1.getDeptId())) {
         DepartmentDO var2 = a.ALLATORIxDEMO.selectById(a1.getDeptId());
         if(null == var2) {
            LogHelper.debug((new StringBuilder()).insert(0, a1.getDeptId()).append(MenusAuthsVO.ALLATORIxDEMO("郍閰俄怷丨嬀圍ｙ")).toString());
            return "";
         } else {
            return var2.getName();
         }
      } else {
         return "";
      }
   }

   public List deleteRoles(List a1) {
      ArrayList var2 = new ArrayList();
      Iterator a2;
      Iterator var10000 = a2 = a1.iterator();

      while(var10000.hasNext()) {
         String var3 = (String)a2.next();
         if(0 == a.j.deleteByPrimaryKey(var3)) {
            RoleVO var4 = a.getRole(var3);
            var2.add(var4.getName());
         }

         var10000 = a.g.deleteMenu(var3);
         a.J.deleteAuth(var3);
      }

      return var2;
   }

   public RoleVO getRole(String a1) {
      RoleDO var2 = a.j.selectByPrimaryKey(a1);
      if(null == var2) {
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("觷航俄怷丨嬀圍ｙ")).toString());
         return null;
      } else {
         RoleVO a2 = new RoleVO();
         BeanUtils.copyProperties(var2, a2);
         if(!StringUtil.isNullOrEmpty(var2.getDeptId()) && !"0".equals(var2.getDeptId())) {
            DepartmentDO var4 = a.ALLATORIxDEMO.selectById(a2.getDeptId());
            if(null != var4) {
               a2.setDeptName(var4.getName());
            }
         }

         return a2;
      }
   }

   public List getRolesByUserId(String a1) {
      ArrayList var2 = new ArrayList();
      Iterator a2;
      Iterator var10000 = a2 = a.getRoleIdsByUserId(a1).iterator();

      while(var10000.hasNext()) {
         String var3 = (String)a2.next();
         var10000 = a2;
         var2.add(a.getRole(var3));
      }

      return var2;
   }

   public List listMenuIdByRoleId(String a1, String a2) {
      TemplateVO a3 = a.F.queryTempByUserId(a2);
      return (List)(null == a3?new ArrayList():(a.L.equals(a1.trim())?a.F.queryMenuByTemplate(a3.getId()):a.g.getMenuByRoleId(a1)));
   }

   public MenusAuthsVO listMenuAuthByRoleId(String a1, String a2) {
      TemplateVO a4 = a.F.queryTempByUserId(a2);
      if(null == a4) {
         return null;
      } else {
         MenusAuthsVO var3 = new MenusAuthsVO();
         List var4 = a.F.queryMenuByTemplate(a4.getId());
         List a5 = a.F.queryAuthByTemplate(a4.getId());
         if(a.L.equals(a1.trim())) {
            var3.setMenus(var4);
            var3.setAuths(a5);
            return var3;
         } else {
            List var5 = a.g.getMenuByRoleId(a1);
            List a3 = a.g.getAuthByRoleId(a1);
            var3.setMenus(a.ALLATORIxDEMO(var5, var4));
            var3.setAuths(a.ALLATORIxDEMO(a3, a5));
            return var3;
         }
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   // $FF: synthetic method
   private ErrorCode ALLATORIxDEMO(RoleMenuVO a1) {
      a.J.deleteAuth(a1.getRoleId());
      Iterator var2 = a1.getAuthIds().iterator();
      Iterator var10000 = var2;

      while(var10000.hasNext()) {
         Integer var3 = (Integer)var2.next();
         RoleAuthDO var4 = new RoleAuthDO();
         var10000 = var2;
         var4.setRoleId(a1.getRoleId());
         var4.setAuthId(var3);
         a.J.insertSelective(var4);
      }

      return ErrorCode.Success;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode addMenu(RoleMenuVO a1) {
      a.g.deleteMenu(a1.getRoleId());
      Iterator var2 = a1.getMenuIds().iterator();
      Iterator var10000 = var2;

      while(var10000.hasNext()) {
         Integer var3 = (Integer)var2.next();
         RoleMenuDO var4 = new RoleMenuDO();
         var10000 = var2;
         var4.setRoleId(a1.getRoleId());
         var4.setMenuId(var3);
         a.g.insertSelective(var4);
      }

      return ErrorCode.Success;
   }

   public ErrorCode editRole(RoleVO a1, String a2) {
      if(!a.L.equals(a1.getId().trim()) && !a.m.equals(a1.getId().trim())) {
         if(StringUtil.isNullOrSpace(a1.getName())) {
            LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("覊艗呕秕乢穟ｙ"), ErrorCode.FailedToUpdateRecord.getCode());
            return ErrorCode.FailedToUpdateRecord;
         } else {
            Ref var3 = new Ref("");
            ErrorCode a3 = a.d.queryWorkspaceId(a2, var3);
            if(ErrorCode.Success != a3) {
               return a3;
            } else {
               a2 = (String)var3.get();
               if(StringUtil.isNullOrEmpty(a1.getDeptId())) {
                  RoleDO var6 = a.j.selectByPrimaryKey(a1.getId());
                  a1.setDeptId(var6.getDeptId());
               }

               if(a.j.countByName(a1.getId(), a1.getName(), a2, a1.getDeptId()).longValue() > 0L) {
                  LogHelper.error(ErrorCode.RoleNameExists.getDesc(), ErrorCode.RoleNameExists.getCode());
                  return ErrorCode.RoleNameExists;
               } else {
                  RoleDO a4 = new RoleDO();
                  BeanUtils.copyProperties(a1, a4);
                  return 0 == a.j.updateByPrimaryKeySelective(a4)?ErrorCode.FailedToUpdateRecord:ErrorCode.Success;
               }
            }
         }
      } else {
         LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("粣绺凝罋覊艗侹恊乕及罎辴ｙ"), ErrorCode.CannotEditSystemData.getCode());
         return ErrorCode.CannotEditSystemData;
      }
   }

   public List getUserIdByRoleId(String a1) {
      return a.j.selectUserIdByRoleId(a1);
   }

   public ErrorCode setRoleUser(String a1, String a2, int a3) {
      if(0 == a.j.checkUserRole(a1, a2)) {
         a.j.addUserRoleRelation(a1, a2, Integer.valueOf(a3));
      }

      return ErrorCode.Success;
   }

   public List getRoleIdsByUserId(String a1) {
      List var2;
      if(ListUtil.isNullOrEmpty(var2 = a.j.selectRoleIdsByUserId(a1))) {
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("沄村刣鄕觷航俄怷Ｉ麀讁凝罋覊艗ｙ")).toString());
         var2.add(a.m);
      }

      return var2;
   }

   public List listShownMenuByUser(String a1, Integer a2) {
      List a4 = a.C.listShownMenu(a2);
      List var3;
      if((var3 = a.getRoleIdsByUserId(a1)).contains(a.L)) {
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("拀村箄瑞命覊艗望陵ｙ")).toString());
         return a4;
      } else {
         List a3 = a.g.getMenuIdsByRoleIds(var3);
         ArrayList var8 = new ArrayList();
         Iterator a5 = a4.iterator();

         while(a5.hasNext()) {
            MenuDisplayVO var4 = (MenuDisplayVO)a5.next();
            if(a3.contains(var4.getId())) {
               var8.add(var4);
            }
         }

         return var8;
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode setRoleUser(String a1, List a2) {
      a.j.deleteRoleUsers(a1);
      Iterator a3 = a2.iterator();

      while(a3.hasNext()) {
         String var3 = (String)a3.next();
         if(0 == a.j.checkUserRole(a1, var3)) {
            a.j.addUserRoleRelation(a1, var3, Integer.valueOf(0));
         }
      }

      return ErrorCode.Success;
   }

   // $FF: synthetic method
   private void ALLATORIxDEMO(List a1, List a2) {
      if(!ListUtil.isNullOrEmpty(a1)) {
         int var3;
         for(int var10000 = var3 = a1.size() - 1; var10000 >= 0; var10000 = var3) {
            MenuDisplayVO var4 = (MenuDisplayVO)a1.get(var3);
            if(!a2.contains(var4.getId())) {
               a1.remove(var3);
            }

            --var3;
         }

         Iterator var6;
         Iterator var7 = var6 = a1.iterator();

         while(var7.hasNext()) {
            MenuDisplayVO var5 = (MenuDisplayVO)var6.next();
            var7 = var6;
            a.ALLATORIxDEMO(var5.getChildren(), a2);
         }

      }
   }

   public List getAllRoles(int a1, Integer a2, String a3) {
      List a4 = a.j.selectAll(a1, a2, a3);
      ArrayList a6 = new ArrayList();
      Iterator a5;
      Iterator var10000 = a5 = a4.iterator();

      while(var10000.hasNext()) {
         RoleDO a7 = (RoleDO)a5.next();
         RoleVO var4 = new RoleVO();
         var10000 = a5;
         var4.setDeptName(a.ALLATORIxDEMO(a7));
         BeanUtils.copyProperties(a7, var4);
         a6.add(var4);
      }

      return a6;
   }

   public ErrorCode addRole(RoleVO a1, Ref a2, String a3) {
      Ref var4 = new Ref("");
      ErrorCode a4 = a.d.queryWorkspaceId(a3, var4);
      if(ErrorCode.Success != a4) {
         return a4;
      } else {
         a3 = (String)var4.get();
         if(StringUtil.isNullOrEmpty(a1.getName())) {
            LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("覊艗呕秕乢穟ｙ"), ErrorCode.IllegalArument.getCode());
            return ErrorCode.IllegalArument;
         } else {
            if(StringUtil.isNullOrEmpty(a1.getDeptId())) {
               a1.setDeptId("0");
            }

            if(a.j.countByName("0", a1.getName(), a3, a1.getDeptId()).longValue() > 0L) {
               LogHelper.error(ErrorCode.RoleNameExists.getDesc(), ErrorCode.RoleNameExists.getCode());
               return ErrorCode.RoleNameExists;
            } else {
               RoleDO var7 = new RoleDO();
               BeanUtils.copyProperties(a1, var7);
               String var5 = (String)a.e.newGuidText().getValue();
               var7.setId(var5);
               if(null == a1.getType() || 0 == a1.getType().intValue()) {
                  var7.setType(Integer.valueOf(0));
               }

               var7.setChannel(Integer.valueOf(0));
               var7.setSpaceId(a3);
               if(0 == a.j.insert(var7)) {
                  LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("涣劅覊艗奩贀ｙ"), ErrorCode.FailedToInsertRecord.getCode());
                  return ErrorCode.FailedToInsertRecord;
               } else {
                  a2.set(var5);
                  return ErrorCode.Success;
               }
            }
         }
      }
   }

   public ErrorCode deleteRole(String a1) {
      if(a.j.countUserByRoleId(a1).longValue() > 0L) {
         LogHelper.error(ErrorCode.RoleIsUsed.getDesc(), ErrorCode.RoleIsUsed.getCode());
         return ErrorCode.RoleIsUsed;
      } else if(!a.L.equals(a1) && !a.m.equals(a1)) {
         a.j.deleteByPrimaryKey(a1);
         return ErrorCode.Success;
      } else {
         LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("粣绺凝罋覊艗侹恊ｔ丨儙讝剸陁ｙ"), ErrorCode.CannotEditSystemData.getCode());
         return ErrorCode.CannotEditSystemData;
      }
   }
}

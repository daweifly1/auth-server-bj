package yb.ecp.fast.user.service;

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
import yb.ecp.fast.user.dao.entity.*;
import yb.ecp.fast.user.dao.mapper.*;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.service.VO.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ScepterService {

   @Autowired
   private WorkspaceService workspaceService;
   @Autowired
   RoleMapper roleMapper;
   @Autowired
   private MenuService menuService;
   @Autowired
   private RoleService roleService;
   @Autowired
   private ProfileMapper profileMapper;
   @Autowired
   RoleAuthMapper roleAuthMapper;
   @Autowired
   private TemplateService templateService;
   @Value("${role.default.roleId}")
   private String defaultRoleId;
   @Autowired
   RoleMenuMapper roleMenuMapper;
   @Autowired
   private ProfileService profileService;
   @Value("${role.admin.roleId}")
   private String adminRoleId;
   @Autowired
   private GenClient genClient;
   @Autowired
   private DepartmentMapper departmentMapper;


    public List authCodesByUserId(String userId) {
        ProfileDO var2 = profileMapper.selectById(userId);
      if(null == var2) {
         return new ArrayList();
      } else {
          List roleIds = roleMapper.selectRoleIdsByUserId(userId);
          if (ListUtil.isNullOrEmpty(roleIds)) {
              roleIds.add(defaultRoleId);
         }
          return roleService.ifAdminRole(roleIds) ?
                  workspaceService.queryAuthsByWorkspace(var2.getSpaceId()) : menuService.queryAuthIdsByRoleIds(roleIds);
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode addMenusAuths(RoleMenuVO a1) {
       ErrorCode var2 = inserRoleAuth(a1);
      if(0 != var2.getCode()) {
         return var2;
      } else {
          var2 = addMenu(a1);
         return 0 != var2.getCode()?var2:var2;
      }
   }

   public ErrorCode queryRolesByDepartment(String a1, String a2, Ref a3) {
      String var4 = "";
      if(StringUtil.isNullOrEmpty(a1) || a1.equals("0")) {
         a1 = "0";
         Ref var5 = new Ref("");
          ErrorCode var6 = profileService.queryWorkspaceId(a2, var5);
         if(ErrorCode.Success != var6) {
            return var6;
         }

         var4 = (String)var5.get();
      }

       List var10 = roleMapper.queryRolesByDepartment(a1, var4);
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

    public List getShownMenuByUser(String userID) {
        TemplateVO templateVO = templateService.queryTempByUserId(userID);
        if (null == templateVO) {
            return new ArrayList();
        } else {
            List<MenuDisplayVO> menuTree = menuService.getShownMenuTree(userID);
            List<Integer> menuByTemplate = templateService.queryMenuByTemplate(templateVO.getId());
            List roleIds = getRoleIdsByUserId(userID);
            if (ListUtil.isNullOrEmpty(roleIds)) {
                roleIds.add(defaultRoleId);
            }

            List<Integer> menuIds;
            if (roleIds.contains(adminRoleId)) {
                menuIds = menuByTemplate;
            } else {
                menuIds = roleMenuMapper.getMenuIdsByRoleIds(roleIds);
                menuByTemplate.retainAll(menuIds);
            }
            processMenuDisplayVOS(menuTree, menuIds);
            return menuTree;
        }
    }

    // $FF: synthetic method
    private void processMenuDisplayVOS(List<MenuDisplayVO> menuDisplayVOS, List<Integer> menuIds) {
        if (!ListUtil.isNullOrEmpty(menuDisplayVOS)) {
            for (int i = menuDisplayVOS.size() - 1; i >= 0; i = i) {
                MenuDisplayVO var4 = (MenuDisplayVO) menuDisplayVOS.get(i);
                if (!menuIds.contains(var4.getId())) {
                    menuDisplayVOS.remove(i);
                }
                --i;
            }
            Iterator iterator = menuDisplayVOS.iterator();
            while (iterator.hasNext()) {
                MenuDisplayVO var5 = (MenuDisplayVO) iterator.next();
                processMenuDisplayVOS(var5.getChildren(), menuIds);
            }
        }
    }

    // $FF: synthetic method
    private List getValidateMenusOrAuths(List<Integer> ids, List<Integer> nemus) {
        ArrayList var3 = new ArrayList();
        Iterator iterator = ids.iterator();
        while (iterator.hasNext()) {
            Integer var4 = (Integer) iterator.next();
            if (nemus.contains(var4)) {
                var3.add(var4);
            }
        }

        return var3;
    }

   public List checkRolesUsed(List a1) {
      ArrayList var2 = new ArrayList();
      Iterator a2 = a1.iterator();

      while(a2.hasNext()) {
         String var3 = (String)a2.next();
          Long var4 = roleMapper.countUserByRoleId(var3);
          if (0L < var4.longValue() || adminRoleId.equals(var3)) {
              RoleVO var6 = getRole(var3);
            var2.add(var6.getName());
         }
      }

      return var2;
   }

   public ErrorCode removeRoleUser(String a1, String a2) {
       roleMapper.removeUserRole(a1, a2);
      return ErrorCode.Success;
   }

   public ErrorCode removeRoleByUser(String a1) {
       roleMapper.removeRoleByUser(a1);
      return ErrorCode.Success;
   }

   // $FF: synthetic method
   private String ALLATORIxDEMO(RoleDO a1) {
      if(null != a1 && !StringUtil.isNullOrEmpty(a1.getDeptId()) && !"0".equals(a1.getDeptId())) {
          DepartmentDO var2 = departmentMapper.selectById(a1.getDeptId());
         if(null == var2) {
             return "";
         } else {
            return var2.getName();
         }
      } else {
         return "";
      }
   }

    public List deleteRoles(List<String> roleIds) {
        ArrayList result = new ArrayList();
        Iterator<String> iterator = roleIds.iterator();

        while (iterator.hasNext()) {
            String var3 = iterator.next();
            if (0 == roleMapper.deleteByPrimaryKey(var3)) {
                RoleVO roleVO = getRole(var3);
                result.add(roleVO.getName());
            }
            roleMenuMapper.deleteMenu(var3);
            roleAuthMapper.deleteAuth(var3);
        }
        return result;
    }

   public RoleVO getRole(String a1) {
       RoleDO var2 = roleMapper.selectByPrimaryKey(a1);
      if(null == var2) {
         return null;
      } else {
         RoleVO a2 = new RoleVO();
         BeanUtils.copyProperties(var2, a2);
         if(!StringUtil.isNullOrEmpty(var2.getDeptId()) && !"0".equals(var2.getDeptId())) {
             DepartmentDO var4 = departmentMapper.selectById(a2.getDeptId());
            if(null != var4) {
               a2.setDeptName(var4.getName());
            }
         }

         return a2;
      }
   }

    public List getRolesByUserId(String userId) {
      ArrayList var2 = new ArrayList();
      Iterator a2;
        Iterator var10000 = a2 = getRoleIdsByUserId(userId).iterator();

      while(var10000.hasNext()) {
         String var3 = (String)a2.next();
         var10000 = a2;
          var2.add(getRole(var3));
      }

      return var2;
   }

   public List listMenuIdByRoleId(String a1, String a2) {
       TemplateVO a3 = templateService.queryTempByUserId(a2);
       return (List) (null == a3 ? new ArrayList() : (adminRoleId.equals(a1.trim()) ? templateService.queryMenuByTemplate(a3.getId()) : roleMenuMapper.getMenuByRoleId(a1)));
   }

    public MenusAuthsVO listMenuAuthByRoleId(String roleId, String userId) {
        TemplateVO templateVO = templateService.queryTempByUserId(userId);
        if (null == templateVO) {
         return null;
      } else {
            MenusAuthsVO menusAuthsVO = new MenusAuthsVO();
            List<Integer> menus = templateService.queryMenuByTemplate(templateVO.getId());
            List<Integer> auths = templateService.queryAuthByTemplate(templateVO.getId());
            if (adminRoleId.equals(roleId.trim())) {
                menusAuthsVO.setMenus(menus);
                menusAuthsVO.setAuths(auths);
                return menusAuthsVO;
            } else {
                List<Integer> menuIds = roleMenuMapper.getMenuByRoleId(roleId);
                List<Integer> authIds = roleMenuMapper.getAuthByRoleId(roleId);
                menusAuthsVO.setMenus(getValidateMenusOrAuths(menuIds, menus));
                menusAuthsVO.setAuths(getValidateMenusOrAuths(authIds, auths));
                return menusAuthsVO;
            }
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   // $FF: synthetic method
   private ErrorCode inserRoleAuth(RoleMenuVO a1) {
       roleAuthMapper.deleteAuth(a1.getRoleId());
      Iterator var2 = a1.getAuthIds().iterator();
      Iterator var10000 = var2;

      while(var10000.hasNext()) {
         Integer var3 = (Integer)var2.next();
         RoleAuthDO var4 = new RoleAuthDO();
         var10000 = var2;
         var4.setRoleId(a1.getRoleId());
         var4.setAuthId(var3);
          roleAuthMapper.insertSelective(var4);
      }

      return ErrorCode.Success;
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode addMenu(RoleMenuVO a1) {
       roleMenuMapper.deleteMenu(a1.getRoleId());
      Iterator var2 = a1.getMenuIds().iterator();
      Iterator var10000 = var2;

      while(var10000.hasNext()) {
         Integer var3 = (Integer)var2.next();
         RoleMenuDO var4 = new RoleMenuDO();
         var10000 = var2;
         var4.setRoleId(a1.getRoleId());
         var4.setMenuId(var3);
          roleMenuMapper.insertSelective(var4);
      }

      return ErrorCode.Success;
   }

   public ErrorCode editRole(RoleVO a1, String a2) {
       if (!adminRoleId.equals(a1.getId().trim()) && !defaultRoleId.equals(a1.getId().trim())) {
         if(StringUtil.isNullOrSpace(a1.getName())) {
            return ErrorCode.FailedToUpdateRecord;
         } else {
            Ref var3 = new Ref("");
             ErrorCode a3 = profileService.queryWorkspaceId(a2, var3);
            if(ErrorCode.Success != a3) {
               return a3;
            } else {
               a2 = (String)var3.get();
               if(StringUtil.isNullOrEmpty(a1.getDeptId())) {
                   RoleDO var6 = roleMapper.selectByPrimaryKey(a1.getId());
                  a1.setDeptId(var6.getDeptId());
               }

                if (roleMapper.countByName(a1.getId(), a1.getName(), a2, a1.getDeptId()).longValue() > 0L) {
                  LogHelper.error(ErrorCode.RoleNameExists.getDesc(), ErrorCode.RoleNameExists.getCode());
                  return ErrorCode.RoleNameExists;
               } else {
                  RoleDO a4 = new RoleDO();
                  BeanUtils.copyProperties(a1, a4);
                    return 0 == roleMapper.updateByPrimaryKeySelective(a4) ? ErrorCode.FailedToUpdateRecord : ErrorCode.Success;
               }
            }
         }
      } else {
         return ErrorCode.CannotEditSystemData;
      }
   }

   public List getUserIdByRoleId(String a1) {
       return roleMapper.selectUserIdByRoleId(a1);
   }

    public ErrorCode setRoleUser(String roleId, String userId, int flag) {
        if (0 == roleMapper.checkUserRole(roleId, userId)) {
            roleMapper.addUserRoleRelation(roleId, userId, flag);
      }
      return ErrorCode.Success;
   }

    public List<String> getRoleIdsByUserId(String userId) {
        List<String> roleIds = roleMapper.selectRoleIdsByUserId(userId);
        if (ListUtil.isNullOrEmpty(roleIds)) {
            roleIds.add(defaultRoleId);
      }
        return roleIds;
   }

    public List<MenuDO> listShownMenuByUser(String userId, Integer a2) {
        List<MenuDO> menuDOList = menuService.listShownMenu(a2);
        List var3 = getRoleIdsByUserId(userId);
        if (var3.contains(adminRoleId)) {
            return menuDOList;
      } else {
            List a3 = roleMenuMapper.getMenuIdsByRoleIds(var3);
         ArrayList var8 = new ArrayList();
            Iterator a5 = menuDOList.iterator();

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
       roleMapper.deleteRoleUsers(a1);
      Iterator a3 = a2.iterator();

      while(a3.hasNext()) {
         String var3 = (String)a3.next();
          if (0 == roleMapper.checkUserRole(a1, var3)) {
              roleMapper.addUserRoleRelation(a1, var3, Integer.valueOf(0));
         }
      }

      return ErrorCode.Success;
   }



   public List getAllRoles(int a1, Integer a2, String a3) {
       List a4 = roleMapper.selectAll(a1, a2, a3);
      ArrayList a6 = new ArrayList();
      Iterator a5;
      Iterator var10000 = a5 = a4.iterator();

      while(var10000.hasNext()) {
         RoleDO a7 = (RoleDO)a5.next();
         RoleVO var4 = new RoleVO();
         var10000 = a5;
          var4.setDeptName(ALLATORIxDEMO(a7));
         BeanUtils.copyProperties(a7, var4);
         a6.add(var4);
      }

      return a6;
   }

   public ErrorCode addRole(RoleVO a1, Ref a2, String a3) {
      Ref var4 = new Ref("");
       ErrorCode a4 = profileService.queryWorkspaceId(a3, var4);
      if(ErrorCode.Success != a4) {
         return a4;
      } else {
         a3 = (String)var4.get();
         if(StringUtil.isNullOrEmpty(a1.getName())) {
             return ErrorCode.IllegalArument;
         } else {
            if(StringUtil.isNullOrEmpty(a1.getDeptId())) {
               a1.setDeptId("0");
            }

             if (roleMapper.countByName("0", a1.getName(), a3, a1.getDeptId()).longValue() > 0L) {
               LogHelper.error(ErrorCode.RoleNameExists.getDesc(), ErrorCode.RoleNameExists.getCode());
               return ErrorCode.RoleNameExists;
            } else {
               RoleDO var7 = new RoleDO();
               BeanUtils.copyProperties(a1, var7);
                 String var5 = (String) genClient.newGuidText().getValue();
               var7.setId(var5);
               if(null == a1.getType() || 0 == a1.getType().intValue()) {
                  var7.setType(Integer.valueOf(0));
               }

               var7.setChannel(Integer.valueOf(0));
               var7.setSpaceId(a3);
                 if (0 == roleMapper.insert(var7)) {
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
       if (roleMapper.countUserByRoleId(a1).longValue() > 0L) {
         LogHelper.error(ErrorCode.RoleIsUsed.getDesc(), ErrorCode.RoleIsUsed.getCode());
         return ErrorCode.RoleIsUsed;
       } else if (!adminRoleId.equals(a1) && !defaultRoleId.equals(a1)) {
           roleMapper.deleteByPrimaryKey(a1);
         return ErrorCode.Success;
      } else {
           return ErrorCode.CannotEditSystemData;
      }
   }
}

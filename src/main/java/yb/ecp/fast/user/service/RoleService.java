package yb.ecp.fast.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.user.dao.mapper.RoleAuthMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.dao.mapper.RoleMenuMapper;
import yb.ecp.fast.user.infra.ErrorCode;

import java.util.Iterator;
import java.util.List;

@Service
public class RoleService {

   @Value("${role.admin.roleId}")
   private String adminRoleId;
   @Autowired
   private RoleMenuMapper roleMenuMapper;
   @Autowired
   private RoleMapper roleMapper;
   @Autowired
   private RoleAuthMapper roleAuthMapper;


   public boolean ifAdminUser(String a1) {
      return ifAdminRole(queryRoleIdsByUserId(a1));
   }

   public List queryRoleIdsByUserId(String a1) {
      return roleMapper.selectRoleIdsByUserId(a1);
   }

   public boolean ifAdminRole(List a1) {
      return a1.contains(adminRoleId.trim());
   }

   public List queryIdsByWorkspaceId(String a1) {
      return roleMapper.selectByWorkspaceId(a1);
   }

   public void releaseAuthMenu(String a1) {
      roleAuthMapper.deleteAuth(a1);
      roleMenuMapper.deleteMenu(a1);
   }

   public ErrorCode releaseByWorkspace(String a1) {
      List a2;
      if (ListUtil.isNullOrEmpty(a2 = queryIdsByWorkspaceId(a1))) {
         return ErrorCode.Success;
      } else {
         Iterator a3;
         Iterator var10000 = a3 = a2.iterator();

         while(var10000.hasNext()) {
            String var2 = (String)a3.next();
            var10000 = a3;
            releaseAuthMenu(var2);
         }

         return ErrorCode.Success;
      }
   }
}

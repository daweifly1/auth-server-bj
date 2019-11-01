package yb.ecp.fast.user.service;

import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.user.dao.mapper.RoleAuthMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.dao.mapper.RoleMenuMapper;
import yb.ecp.fast.user.infra.ErrorCode;

@Service
public class RoleService {

   @Value("${role.admin.roleId}")
   private String d;
   @Autowired
   private RoleMenuMapper L;
   @Autowired
   private RoleMapper e;
   @Autowired
   private RoleAuthMapper ALLATORIxDEMO;


   public boolean ifAdminUser(String a1) {
      return a.ifAdminRole(a.queryRoleIdsByUserId(a1));
   }

   public List queryRoleIdsByUserId(String a1) {
      return a.e.selectRoleIdsByUserId(a1);
   }

   public boolean ifAdminRole(List a1) {
      return a1.contains(a.d.trim());
   }

   public List queryIdsByWorkspaceId(String a1) {
      return a.e.selectByWorkspaceId(a1);
   }

   public void releaseAuthMenu(String a1) {
      a.ALLATORIxDEMO.deleteAuth(a1);
      a.L.deleteMenu(a1);
   }

   public ErrorCode releaseByWorkspace(String a1) {
      List a2;
      if(ListUtil.isNullOrEmpty(a2 = a.queryIdsByWorkspaceId(a1))) {
         return ErrorCode.Success;
      } else {
         Iterator a3;
         Iterator var10000 = a3 = a2.iterator();

         while(var10000.hasNext()) {
            String var2 = (String)a3.next();
            var10000 = a3;
            a.releaseAuthMenu(var2);
         }

         return ErrorCode.Success;
      }
   }
}

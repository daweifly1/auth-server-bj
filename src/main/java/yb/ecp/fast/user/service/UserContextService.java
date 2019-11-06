package yb.ecp.fast.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.manager.UserContextManager;
import yb.ecp.fast.user.service.VO.ProfileVO;
import yb.ecp.fast.user.service.VO.UserCacheVO;

import java.util.Arrays;

@Service
public class UserContextService {

   @Autowired
   private UserContextManager userContextManager;
   @Autowired
   private ScepterService scepterService;
   @Autowired
   private ProfileService profileService;
   @Value("${role.admin.roleId}")
   private String adminRoleId;


   public void setSession(String a1, Object a2, Integer[] a3, String a4) {
      userContextManager.cacheUser(a1, a2, a3, a4);
   }

   public void clearSession(String a1) {
      userContextManager.clearSession(a1);
   }

   public ErrorCode checkAuthCode(String a1, Integer a2) {
      if (ifAdmin(a1)) {
         userContextManager.renewLeaseSession(a1);
         return ErrorCode.Success;
      } else {
         ErrorCode a3 = userContextManager.checkAuthCode(a1, a2);
         if(ErrorCode.Success == a3) {
            userContextManager.renewLeaseSession(a1);
            return ErrorCode.Success;
         } else {
            return a3;
         }
      }
   }

   public ProfileVO setSession(String a1) {
      return profileService.queryLoginUser(a1);
   }

   public Object getSessionData(String a1) {
      UserCacheVO var2 = (UserCacheVO) userContextManager.getUserData(a1);
      if(null != var2) {
         userContextManager.renewLeaseSession(a1);
         return var2;
      } else {
         ProfileVO a2 = setSession(a1);
         return profileService.getUserCacheVO(a2);
      }
   }

   public Integer[] getAuthCodes(String a1) {
      Integer[] var2 = userContextManager.getAuthCode(a1);
      if(null != var2 && 0 != var2.length) {
         userContextManager.renewLeaseSession(a1);
         return var2;
      } else {
         ProfileVO var3 = setSession(a1);
         if(null == var3) {
            Integer[] var10000 = new Integer[0];
            Arrays.asList(1).toArray(var10000);
            return var10000;
         } else {
            var3.getAuthIds();
            Integer[] var10001 = new Integer[0];
            var3.getAuthIds().toArray(var10001);
            return var10001;
         }
      }
   }

   public String getWorkspaceId(String a1) {
      String var2;
      if (!StringUtil.isNullOrSpace(var2 = userContextManager.getWorkspaceId(a1))) {
         userContextManager.renewLeaseSession(a1);
         return var2;
      } else {
         ProfileVO var3 = setSession(a1);
         if(null == var3) {
            return "";
         } else {
            return var3.getSpaceId();
         }
      }
   }

   public boolean ifAdmin(String a1) {
      return scepterService.getRoleIdsByUserId(a1).contains(adminRoleId);
   }
}

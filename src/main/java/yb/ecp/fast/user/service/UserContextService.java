package yb.ecp.fast.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.manager.UserContextManager;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.ScepterService;
import yb.ecp.fast.user.service.VO.MenusAuthsVO;
import yb.ecp.fast.user.service.VO.ProfileVO;
import yb.ecp.fast.user.service.VO.UserCacheVO;

@Service
public class UserContextService {

   @Autowired
   private UserContextManager d;
   @Autowired
   private ScepterService L;
   @Autowired
   private ProfileService e;
   @Value("${role.admin.roleId}")
   private String ALLATORIxDEMO;


   public void setSession(String a1, Object a2, Integer[] a3, String a4) {
      a.d.cacheUser(a1, a2, a3, a4);
   }

   public void clearSession(String a1) {
      a.d.clearSession(a1);
   }

   public ErrorCode checkAuthCode(String a1, Integer a2) {
      if(a.ifAdmin(a1)) {
         a.d.renewLeaseSession(a1);
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("甍扯丟篹琣吀Ｉ望陵桹骩遂迢ｙ")).toString());
         return ErrorCode.Success;
      } else {
         ErrorCode a3 = a.d.checkAuthCode(a1, a2);
         if(ErrorCode.Success == a3) {
            a.d.renewLeaseSession(a1);
            return ErrorCode.Success;
         } else {
            return a3;
         }
      }
   }

   public ProfileVO setSession(String a1) {
      return a.e.queryLoginUser(a1);
   }

   public Object getSessionData(String a1) {
      UserCacheVO var2 = (UserCacheVO)a.d.getUserData(a1);
      if(null != var2) {
         a.d.renewLeaseSession(a1);
         LogHelper.debug(MenusAuthsVO.ALLATORIxDEMO("莒厎甍扯缶嬀俄怷擨伄戵勇Ｉ副斕罋孽ｙ"));
         return var2;
      } else {
         ProfileVO a2 = a.setSession(a1);
         return a.e.getUserCacheVO(a2);
      }
   }

   public Integer[] getAuthCodes(String a1) {
      Integer[] var2 = a.d.getAuthCode(a1);
      if(null != var2 && 0 != var2.length) {
         a.d.renewLeaseSession(a1);
         LogHelper.debug(MenusAuthsVO.ALLATORIxDEMO("華右異戒望陵硙擨伄戵勇Ｉ副斕罋孽ｙ"));
         return var2;
      } else {
         ProfileVO var3 = a.setSession(a1);
         if(null == var3) {
            LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("莒厎甍扯杦阈砤撕佹ｔ甍扯俄怷丨嬀圍ｙ")).toString());
            Integer[] var10000 = new Integer[0];
            boolean var10002 = true;
            return true;
         } else {
            var3.getAuthIds();
            Integer[] var10001 = new Integer[0];
            boolean var10003 = true;
            return (Integer[])var10001.toArray(true);
         }
      }
   }

   public String getWorkspaceId(String a1) {
      String var2;
      if(!StringUtil.isNullOrSpace(var2 = a.d.getWorkspaceId(a1))) {
         a.d.renewLeaseSession(a1);
         LogHelper.debug(MenusAuthsVO.ALLATORIxDEMO("莒厎甍扯扥将巀伄穟閬l擨伄戵勇Ｉ副斕罋孽ｙ"));
         return var2;
      } else {
         ProfileVO var3 = a.setSession(a1);
         if(null == var3) {
            LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("華右異戒战屻嶽佹稢闑撕佹ｔ甍扯俄怷丨嬀圍ｙ")).toString());
            return "";
         } else {
            return var3.getSpaceId();
         }
      }
   }

   public boolean ifAdmin(String a1) {
      return a.L.getRoleIdsByUserId(a1).contains(a.ALLATORIxDEMO);
   }
}

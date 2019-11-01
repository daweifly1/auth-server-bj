package yb.ecp.fast.user.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yb.ecp.fast.feign.GenClient;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.security.CryptoUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.AccountDO;
import yb.ecp.fast.user.dao.entity.PasswordDO;
import yb.ecp.fast.user.dao.entity.PasswordDOKey;
import yb.ecp.fast.user.dao.entity.ProfileDO;
import yb.ecp.fast.user.dao.mapper.AccountMapper;
import yb.ecp.fast.user.dao.mapper.PasswordMapper;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.infra.AuthConstant;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.manager.AuthInfoManager;
import yb.ecp.fast.user.service.ProfileService;
import yb.ecp.fast.user.service.TemplateService;
import yb.ecp.fast.user.service.VO.AccountPwdVO;
import yb.ecp.fast.user.service.VO.AccountVO;
import yb.ecp.fast.user.service.VO.PasswordVO;
import yb.ecp.fast.user.service.VO.RoleUserVO;
import yb.ecp.fast.user.service.VO.TemplateVO;
import yb.ecp.fast.user.service.VO.TmpMenusVO;
import yb.ecp.fast.user.service.VO.UpdatePasswordVO;
import yb.ecp.fast.user.service.VO.UserLoginVO;

@Service
public class AccountService {

   @Autowired
   AccountMapper F;
   @Autowired
   ProfileService m;
   @Autowired
   ProfileMapper g;
   @Autowired
   TemplateService d;
   @Autowired
   PasswordMapper L;
   @Autowired
   GenClient e;
   @Autowired
   AuthInfoManager ALLATORIxDEMO;


   public ErrorCode checkLoginName(String a1) {
      AccountDO var2 = new AccountDO();
      var2.setUserId("");
      var2.setLoginName(a1);
      int a2 = a.F.checkLoginName(var2);
      return 0 != a2?ErrorCode.UserNameExists:(a2 == 0?ErrorCode.Success:ErrorCode.UserNameExists);
   }

   // $FF: synthetic method
   private AccountVO ALLATORIxDEMO(AccountDO a1) {
      if(null == a1) {
         return null;
      } else {
         AccountVO var2 = new AccountVO();
         var2.setLoginName(a1.getLoginName());
         var2.setStatus(a1.getStatus());
         var2.setUserId(a1.getUserId());
         var2.setLastLoginTime(a1.getLastLoginTime());
         return var2;
      }
   }

   public ErrorCode rmAccountByUserId(String a1) {
      a.F.deleteByUserId(a1);
      return ErrorCode.Success;
   }

   public String queryUserIdByAccount(String a1) {
      return a.F.selectUserIdByAccount(a1);
   }

   public ErrorCode updatePassword(String a1, UpdatePasswordVO a2) throws Exception {
      PasswordDOKey var3 = new PasswordDOKey();
      var3.setUserId(a1);
      var3.setType(Integer.valueOf(0));
      PasswordDO var4;
      if((var4 = a.L.selectByPrimaryKey(var3)) == null) {
         LogHelper.error(RoleUserVO.ALLATORIxDEMO("叢宠硼侇怒乫嬥坎ｼ"), ErrorCode.OldPwdNotRight.getCode());
         return ErrorCode.OldPwdNotRight;
      } else if(!a.ALLATORIxDEMO(a2.getOldPassword(), a1).equals(var4.getPassword())) {
         LogHelper.error(ErrorCode.OldPwdNotRight.getDesc(), ErrorCode.OldPwdNotRight.getCode());
         return ErrorCode.OldPwdNotRight;
      } else {
         return a.updatePassword(a1, a2.getNewPassword());
      }
   }

   public ErrorCode addAccountPwd(AccountPwdVO a1) throws Exception {
      ErrorCode var2;
      if((var2 = a.addAccount(a1)) != ErrorCode.Success) {
         LogHelper.error(var2.getDesc(), var2.getCode());
         return var2;
      } else {
         ErrorCode a2;
         if((a2 = a.insertPassword(a1)) != ErrorCode.Success) {
            LogHelper.error(a2.getDesc(), a2.getCode());
            return a2;
         } else {
            return ErrorCode.Success;
         }
      }
   }

   public ErrorCode insertPassword(PasswordVO a1) throws Exception {
      PasswordDO var2 = new PasswordDO();
      var2.setUserId(a1.getUserId());
      var2.setType(a1.getType());
      var2.setPassword(a1.getPassword());
      return a.ALLATORIxDEMO(var2);
   }

   // $FF: synthetic method
   private String ALLATORIxDEMO(String a1, String a2) throws Exception {
      byte[] a3 = CryptoUtil.encryptMD5((new StringBuilder()).insert(0, a1).append(TmpMenusVO.ALLATORIxDEMO("")).append(a2).toString().getBytes());
      return (new BigInteger(a3)).toString(16);
   }

   public List queryAccountsByUserId(String a1) {
      List a2 = a.F.selectListByUserId(a1);
      ArrayList var2 = new ArrayList();
      Iterator a3;
      Iterator var10000 = a3 = a2.iterator();

      while(var10000.hasNext()) {
         AccountDO var3 = (AccountDO)a3.next();
         var10000 = a3;
         var2.add(a.ALLATORIxDEMO(var3));
      }

      return var2;
   }

   public ErrorCode removeAccountByUserId(String a1) {
      if(ErrorCode.Success != a.rmAccountByUserId(a1)) {
         LogHelper.error(RoleUserVO.ALLATORIxDEMO("剝阂畕扑瘆弳赛扑奌赃ｼ"), ErrorCode.FailedToRemoveRecord.getCode());
         return ErrorCode.FailedToRemoveRecord;
      } else {
         return a.removePassword(a1, Integer.valueOf(0));
      }
   }

   public ErrorCode updatePassword(String a1, String a2) throws Exception {
      PasswordDO var3 = new PasswordDO();
      var3.setUserId(a1);
      var3.setPassword(a.ALLATORIxDEMO(a2, a1));
      var3.setType(Integer.valueOf(0));
      return a.L.updateByPrimaryKey(var3) <= 0?ErrorCode.FailedToUpdateRecord:ErrorCode.Success;
   }

   public ErrorCode resetPassword(List a1) throws Exception {
      ArrayList var2 = new ArrayList();
      Iterator a2 = a1.iterator();

      while(a2.hasNext()) {
         String var3 = (String)a2.next();
         ErrorCode var4 = a.updatePassword(var3, "123456");
         if(0 != var4.getCode()) {
            var2.add(var3);
         }
      }

      if(!var2.isEmpty()) {
         return ErrorCode.FailedToUpdateRecord;
      } else {
         return ErrorCode.Success;
      }
   }

   public ErrorCode updateLoginName(AccountVO a1) {
      AccountDO var2 = new AccountDO();
      var2.setUserId(a1.getUserId());
      var2.setLoginName(a1.getLoginName());
      int var4 = a.F.checkLoginName(var2);
      if(0 != var4) {
         LogHelper.error(ErrorCode.UserNameExists.getDesc(), ErrorCode.UserNameExists.getCode());
         return ErrorCode.UserNameExists;
      } else {
         ErrorCode var5 = a.rmAccountByUserId(a1.getUserId());
         if(ErrorCode.Success != var5) {
            return var5;
         } else {
            a1.setStatus(Integer.valueOf(0));
            ErrorCode a2 = a.addAccount(a1);
            return ErrorCode.Success != a2?a2:ErrorCode.Success;
         }
      }
   }

   public ErrorCode removeAllPassword(String a1) {
      return a.removePassword(a1, Integer.valueOf(0));
   }

   public ErrorCode removePassword(String a1, Integer a2) {
      PasswordDOKey var3 = new PasswordDOKey();
      var3.setUserId(a1);
      var3.setType(a2);
      a.L.deleteByPrimaryKey(var3);
      return ErrorCode.Success;
   }

   public ErrorCode removeAccount(String a1) {
      AccountDO var2;
      if((var2 = a.F.selectByPrimaryKey(a1)) == null) {
         LogHelper.error(TmpMenusVO.ALLATORIxDEMO("皽桛攣挲乞嬄坻｝"), ErrorCode.Success.getCode());
         return ErrorCode.Success;
      } else if(a.F.deleteByPrimaryKey(a1) <= 0) {
         LogHelper.error(RoleUserVO.ALLATORIxDEMO("剝阂瘆弳赛厑奌赃ｼ"), ErrorCode.FailedToRemoveRecord.getCode());
         return ErrorCode.FailedToRemoveRecord;
      } else if(a.F.checkByUserId(var2.getUserId()) > 0) {
         return ErrorCode.Success;
      } else {
         a.removePassword(var2.getUserId(), Integer.valueOf(0));
         return ErrorCode.Success;
      }
   }

   public ErrorCode insertPassword(AccountPwdVO a1) throws Exception {
      PasswordDO var2 = new PasswordDO();
      var2.setUserId(a1.getUserId());
      var2.setType(a1.getType());
      var2.setPassword(a1.getPassword());
      return a.ALLATORIxDEMO(var2);
   }

   public boolean checkUserOnSite(String a1, Integer a2) {
      TemplateVO a3 = a.d.queryTempByUserId(a1);
      return null != a3 && a2 == a3.getSite();
   }

   public AccountVO queryAccountById(String a1) {
      AccountVO var2 = a.ALLATORIxDEMO(a.F.selectByUserId(a1));
      if(null != var2) {
         var2.setLastLoginTime(a.F.getLastLoginTime(a1));
      }

      return var2;
   }

   public ErrorCode login(UserLoginVO a1, Ref a2, Integer a3) throws Exception {
      if(StringUtil.isNullOrSpace(a1.getCode())) {
         LogHelper.error(TmpMenusVO.ALLATORIxDEMO("髟讝硒书稩｝"), ErrorCode.FailedToVerifyCode.getCode());
         return ErrorCode.FailedToVerifyCode;
      } else {
         String var4 = a.ALLATORIxDEMO.getVerifyCode(a1.getAuthId());
         if(!a1.getCode().equalsIgnoreCase(var4)) {
            LogHelper.error(RoleUserVO.ALLATORIxDEMO("髪讼硧镤讉ｼ"), ErrorCode.FailedToVerifyCode.getCode());
            return ErrorCode.FailedToVerifyCode;
         } else {
            AccountDO var7;
            if((var7 = a.F.selectByPrimaryKey(a1.getLoginName())) == null) {
               LogHelper.error(TmpMenusVO.ALLATORIxDEMO("瘨弉赵厫乞嬄坻｝"), ErrorCode.CheckLoginFailure.getCode());
               return ErrorCode.CheckLoginFailure;
            } else {
               PasswordDOKey var5 = new PasswordDOKey();
               var5.setUserId(var7.getUserId());
               var5.setType(Integer.valueOf(0));
               PasswordDO var8;
               if((var8 = a.L.selectByPrimaryKey(var5)) == null) {
                  LogHelper.error(RoleUserVO.ALLATORIxDEMO("瘝弨宠硼乫嬥坎ｼ"), ErrorCode.CheckLoginFailure.getCode());
                  return ErrorCode.CheckLoginFailure;
               } else {
                  ProfileDO var6;
                  if((var6 = a.g.selectById(var8.getUserId())) != null && var6.getLocked() != AuthConstant.USER_LOCKED) {
                     if(!a.ALLATORIxDEMO(a1.getPassword(), var7.getUserId()).equals(var8.getPassword())) {
                        LogHelper.error(RoleUserVO.ALLATORIxDEMO("瘆弳宻硧镤讉ｼ"), ErrorCode.CheckLoginFailure.getCode());
                        return ErrorCode.CheckLoginFailure;
                     } else if(!a.checkUserOnSite(var7.getUserId(), a3)) {
                        LogHelper.error(ErrorCode.NoPermissionForThisSite.getDesc(), ErrorCode.CheckLoginFailure.getCode());
                        return ErrorCode.NoPermissionForThisSite;
                     } else {
                        a.F.updateLoginTime(var7.getUserId());
                        a2.set(var7.getUserId());
                        return ErrorCode.Success;
                     }
                  } else {
                     LogHelper.error(TmpMenusVO.ALLATORIxDEMO("畴扤狪恒裷镒｝"), ErrorCode.UserLocked.getCode());
                     return ErrorCode.UserLocked;
                  }
               }
            }
         }
      }
   }

   public ErrorCode checkExistAccountName(String a1) {
      if(a.F.checkByPrimaryKey(a1) > 0) {
         LogHelper.error(ErrorCode.UserNameExists.getDesc(), ErrorCode.UserNameExists.getCode());
         return ErrorCode.UserNameExists;
      } else {
         return ErrorCode.Success;
      }
   }

   // $FF: synthetic method
   private ErrorCode ALLATORIxDEMO(PasswordDO a1) throws Exception {
      if(a.L.checkByPrimaryKey(a1) > 0) {
         LogHelper.error(ErrorCode.PwdAlreadSet.getDesc(), ErrorCode.PwdAlreadSet.getCode());
         return ErrorCode.PwdAlreadSet;
      } else {
         String var3 = a.ALLATORIxDEMO(a1.getPassword(), a1.getUserId());
         a1.setPassword(var3);
         if(a.L.insert(a1) <= 0) {
            LogHelper.error(TmpMenusVO.ALLATORIxDEMO("涧勳畴扤定硒侽怼奭赶｝"), ErrorCode.FailedToInsertRecord.getCode());
            return ErrorCode.FailedToInsertRecord;
         } else {
            return ErrorCode.Success;
         }
      }
   }

   public ErrorCode addAccount(AccountVO a1) {
      if(a.F.checkByPrimaryKey(a1.getLoginName()) > 0) {
         LogHelper.error(ErrorCode.UserNameExists.getDesc(), ErrorCode.UserNameExists.getCode());
         return ErrorCode.UserNameExists;
      } else {
         AccountDO var3 = new AccountDO();
         var3.setUserId(a1.getUserId());
         var3.setLoginName(a1.getLoginName());
         var3.setStatus(a1.getStatus());
         var3.setLastLoginTime((Date)null);
         if(a.F.insertSelective(var3) <= 0) {
            LogHelper.error(RoleUserVO.ALLATORIxDEMO("旍壸赛厑奌赃ｼ"), ErrorCode.FailedToInsertRecord.getCode());
            return ErrorCode.FailedToInsertRecord;
         } else {
            return ErrorCode.Success;
         }
      }
   }
}

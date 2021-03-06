package yb.ecp.fast.user.service;

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
import yb.ecp.fast.user.service.VO.*;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccountService {

   @Autowired
   AccountMapper accountMapper;
   @Autowired
   ProfileService profileService;
   @Autowired
   ProfileMapper profileMapper;
   @Autowired
   TemplateService templateService;
   @Autowired
   PasswordMapper passwordMapper;
   @Autowired
   GenClient genClient;
   @Autowired
   AuthInfoManager authInfoManager;


   public ErrorCode checkLoginName(String a1) {
      AccountDO var2 = new AccountDO();
      var2.setUserId("");
      var2.setLoginName(a1);
      int a2 = accountMapper.checkLoginName(var2);
      return 0 != a2?ErrorCode.UserNameExists:(a2 == 0?ErrorCode.Success:ErrorCode.UserNameExists);
   }

   // $FF: synthetic method
   private AccountVO genVoByDo(AccountDO accountDO) {
      if (null == accountDO) {
         return null;
      } else {
         AccountVO var2 = new AccountVO();
         var2.setLoginName(accountDO.getLoginName());
         var2.setStatus(accountDO.getStatus());
         var2.setUserId(accountDO.getUserId());
         var2.setLastLoginTime(accountDO.getLastLoginTime());
         return var2;
      }
   }

   public ErrorCode rmAccountByUserId(String a1) {
      accountMapper.deleteByUserId(a1);
      return ErrorCode.Success;
   }

   public String queryUserIdByAccount(String a1) {
      return accountMapper.selectUserIdByAccount(a1);
   }

   public ErrorCode updatePassword(String a1, UpdatePasswordVO a2) throws Exception {
      PasswordDOKey var3 = new PasswordDOKey();
      var3.setUserId(a1);
      var3.setType(Integer.valueOf(0));
      PasswordDO var4;
      if ((var4 = passwordMapper.selectByPrimaryKey(var3)) == null) {
         return ErrorCode.OldPwdNotRight;
      } else if (!cryptoPassword(a2.getOldPassword(), a1).equals(var4.getPassword())) {
         LogHelper.error(ErrorCode.OldPwdNotRight.getDesc(), ErrorCode.OldPwdNotRight.getCode());
         return ErrorCode.OldPwdNotRight;
      } else {
         return updatePassword(a1, a2.getNewPassword());
      }
   }

   public ErrorCode addAccountPwd(AccountPwdVO a1) throws Exception {
      ErrorCode var2;
      if ((var2 = addAccount(a1)) != ErrorCode.Success) {
         LogHelper.error(var2.getDesc(), var2.getCode());
         return var2;
      } else {
         ErrorCode a2;
         if ((a2 = insertPassword(a1)) != ErrorCode.Success) {
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
      return ALLATORIxDEMO(var2);
   }


   private String cryptoPassword(String text, String salt) throws Exception {
      String orginalText = text + "_" + salt;
      byte[] cypherBytes = CryptoUtil.encryptMD5(orginalText.getBytes());
      String cypherText = new BigInteger(cypherBytes).toString(16);
      return cypherText;
   }

   public List queryAccountsByUserId(String a1) {
      List a2 = accountMapper.selectListByUserId(a1);
      ArrayList var2 = new ArrayList();
      Iterator iterator = a2.iterator();

      while (iterator.hasNext()) {
         AccountDO var3 = (AccountDO) iterator.next();
         var2.add(genVoByDo(var3));
      }
      return var2;
   }

   public ErrorCode removeAccountByUserId(String a1) {
      if (ErrorCode.Success != rmAccountByUserId(a1)) {
         return ErrorCode.FailedToRemoveRecord;
      } else {
         return removePassword(a1, Integer.valueOf(0));
      }
   }

   public ErrorCode updatePassword(String a1, String a2) throws Exception {
      PasswordDO var3 = new PasswordDO();
      var3.setUserId(a1);
      var3.setPassword(cryptoPassword(a2, a1));
      var3.setType(Integer.valueOf(0));
      return passwordMapper.updateByPrimaryKey(var3) <= 0 ? ErrorCode.FailedToUpdateRecord : ErrorCode.Success;
   }

   public ErrorCode resetPassword(List a1) throws Exception {
      ArrayList var2 = new ArrayList();
      Iterator a2 = a1.iterator();

      while(a2.hasNext()) {
         String var3 = (String)a2.next();
         ErrorCode var4 = updatePassword(var3, "123456");
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
      int var4 = accountMapper.checkLoginName(var2);
      if(0 != var4) {
         LogHelper.error(ErrorCode.UserNameExists.getDesc(), ErrorCode.UserNameExists.getCode());
         return ErrorCode.UserNameExists;
      } else {
         ErrorCode var5 = rmAccountByUserId(a1.getUserId());
         if(ErrorCode.Success != var5) {
            return var5;
         } else {
            a1.setStatus(Integer.valueOf(0));
            ErrorCode a2 = addAccount(a1);
            return ErrorCode.Success != a2?a2:ErrorCode.Success;
         }
      }
   }

   public ErrorCode removeAllPassword(String a1) {
      return removePassword(a1, Integer.valueOf(0));
   }

   public ErrorCode removePassword(String a1, Integer a2) {
      PasswordDOKey var3 = new PasswordDOKey();
      var3.setUserId(a1);
      var3.setType(a2);
      passwordMapper.deleteByPrimaryKey(var3);
      return ErrorCode.Success;
   }

   public ErrorCode removeAccount(String a1) {
      AccountDO var2;
      if ((var2 = accountMapper.selectByPrimaryKey(a1)) == null) {
         return ErrorCode.Success;
      } else if (accountMapper.deleteByPrimaryKey(a1) <= 0) {
        return ErrorCode.FailedToRemoveRecord;
      } else if (accountMapper.checkByUserId(var2.getUserId()) > 0) {
         return ErrorCode.Success;
      } else {
         removePassword(var2.getUserId(), Integer.valueOf(0));
         return ErrorCode.Success;
      }
   }

   public ErrorCode insertPassword(AccountPwdVO a1) throws Exception {
      PasswordDO var2 = new PasswordDO();
      var2.setUserId(a1.getUserId());
      var2.setType(a1.getType());
      var2.setPassword(a1.getPassword());
      return ALLATORIxDEMO(var2);
   }

   public boolean checkUserOnSite(String a1, Integer a2) {
      TemplateVO a3 = templateService.queryTempByUserId(a1);
      return null != a3 && a2 == a3.getSite();
   }

   public AccountVO queryAccountById(String a1) {
      AccountVO var2 = genVoByDo(accountMapper.selectByUserId(a1));
      if(null != var2) {
         var2.setLastLoginTime(accountMapper.getLastLoginTime(a1));
      }

      return var2;
   }

   public ErrorCode login(UserLoginVO a1, Ref a2, Integer a3) throws Exception {
      if(StringUtil.isNullOrSpace(a1.getCode())) {
         return ErrorCode.FailedToVerifyCode;
      } else {
         String var4 = authInfoManager.getVerifyCode(a1.getAuthId());
         if(!a1.getCode().equalsIgnoreCase(var4)) {
            return ErrorCode.FailedToVerifyCode;
         } else {
            AccountDO var7;
            if ((var7 = accountMapper.selectByPrimaryKey(a1.getLoginName())) == null) {
               return ErrorCode.CheckLoginFailure;
            } else {
               PasswordDOKey var5 = new PasswordDOKey();
               var5.setUserId(var7.getUserId());
               var5.setType(Integer.valueOf(0));
               PasswordDO var8;
               if ((var8 = passwordMapper.selectByPrimaryKey(var5)) == null) {
                  return ErrorCode.CheckLoginFailure;
               } else {
                  ProfileDO var6;
                  if ((var6 = profileMapper.selectById(var8.getUserId())) != null && var6.getLocked() != AuthConstant.USER_LOCKED) {
                     if (!cryptoPassword(a1.getPassword(), var7.getUserId()).equals(var8.getPassword())) {
                        return ErrorCode.CheckLoginFailure;
                     } else if (!checkUserOnSite(var7.getUserId(), a3)) {
                        LogHelper.error(ErrorCode.NoPermissionForThisSite.getDesc(), ErrorCode.CheckLoginFailure.getCode());
                        return ErrorCode.NoPermissionForThisSite;
                     } else {
                        accountMapper.updateLoginTime(var7.getUserId());
                        a2.set(var7.getUserId());
                        return ErrorCode.Success;
                     }
                  } else {
                     return ErrorCode.UserLocked;
                  }
               }
            }
         }
      }
   }

   public ErrorCode checkExistAccountName(String a1) {
      if (accountMapper.checkByPrimaryKey(a1) > 0) {
         LogHelper.error(ErrorCode.UserNameExists.getDesc(), ErrorCode.UserNameExists.getCode());
         return ErrorCode.UserNameExists;
      } else {
         return ErrorCode.Success;
      }
   }

   // $FF: synthetic method
   private ErrorCode ALLATORIxDEMO(PasswordDO passwordDO) throws Exception {
      if (passwordMapper.checkByPrimaryKey(passwordDO) > 0) {
         LogHelper.error(ErrorCode.PwdAlreadSet.getDesc(), ErrorCode.PwdAlreadSet.getCode());
         return ErrorCode.PwdAlreadSet;
      } else {
         String var3 = cryptoPassword(passwordDO.getPassword(), passwordDO.getUserId());
         passwordDO.setPassword(var3);
         if (passwordMapper.insert(passwordDO) <= 0) {
            return ErrorCode.FailedToInsertRecord;
         } else {
            return ErrorCode.Success;
         }
      }
   }

   public ErrorCode addAccount(AccountVO a1) {
      if (accountMapper.checkByPrimaryKey(a1.getLoginName()) > 0) {
         LogHelper.error(ErrorCode.UserNameExists.getDesc(), ErrorCode.UserNameExists.getCode());
         return ErrorCode.UserNameExists;
      } else {
         AccountDO var3 = new AccountDO();
         var3.setUserId(a1.getUserId());
         var3.setLoginName(a1.getLoginName());
         var3.setStatus(a1.getStatus());
         var3.setLastLoginTime((Date)null);
         if (accountMapper.insertSelective(var3) <= 0) {
            return ErrorCode.FailedToInsertRecord;
         } else {
            return ErrorCode.Success;
         }
      }
   }
}

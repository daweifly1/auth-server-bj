package yb.ecp.fast.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yb.ecp.fast.feign.FastGenClient;
import yb.ecp.fast.infra.infra.PageCommonVO;
import yb.ecp.fast.infra.infra.SearchCommonVO;
import yb.ecp.fast.infra.infra.log.LogHelper;
import yb.ecp.fast.infra.security.CryptoUtil;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.ProfileDO;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.manager.UserContextManager;
import yb.ecp.fast.user.service.AccountService;
import yb.ecp.fast.user.service.DepartmentService;
import yb.ecp.fast.user.service.ScepterService;
import yb.ecp.fast.user.service.WorkspaceService;
import yb.ecp.fast.user.service.VO.AccountPwdVO;
import yb.ecp.fast.user.service.VO.AccountVO;
import yb.ecp.fast.user.service.VO.DepartmentVO;
import yb.ecp.fast.user.service.VO.LockVO;
import yb.ecp.fast.user.service.VO.MenusAuthsVO;
import yb.ecp.fast.user.service.VO.ProfileConditionVO;
import yb.ecp.fast.user.service.VO.ProfileVO;
import yb.ecp.fast.user.service.VO.RoleMenuVO;
import yb.ecp.fast.user.service.VO.RoleVO;
import yb.ecp.fast.user.service.VO.UserCacheVO;
import yb.ecp.fast.user.service.VO.UserVO;
import yb.ecp.fast.user.service.VO.WorkspaceVO;
import yb.ecp.fast.user.service.base.BaseService;

@Service
public class ProfileService extends BaseService {

   @Autowired
   ScepterService E;
   @Autowired
   AccountService J;
   @Autowired
   private FastGenClient F;
   @Autowired
   RoleMapper m;
   @Autowired
   private UserContextManager g;
   @Autowired
   WorkspaceService d;
   @Autowired
   DepartmentService L;
   @Value("${role.admin.roleId}")
   private String e;
   @Autowired
   ProfileMapper ALLATORIxDEMO;


   public ErrorCode queryAuthTemplateId(String a1, Ref a2) {
      Ref var3 = new Ref("");
      ErrorCode a3;
      if((a3 = a.queryWorkspaceId(a1, var3)) != ErrorCode.Success) {
         return a3;
      } else {
         WorkspaceVO a4 = a.d.item((String)var3.get());
         if(null == a4) {
            LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("枽诇異戒战屻嶽佹稢闑侹恊镁诊ｙ"), ErrorCode.FailedToRetreiveRecord.getCode());
            return ErrorCode.FailedToRetreiveRecord;
         } else {
            a2.set(a4.getTempId());
            return ErrorCode.Success;
         }
      }
   }

   public ErrorCode checkExistMobile(String a1, String a2) {
      if(StringUtils.isNotBlank(a1)) {
         ProfileDO var3 = new ProfileDO();
         var3.setMobile(a1);
         var3.setUserId(a2);
         if(a.ALLATORIxDEMO.getCountByMobile(var3) > 0) {
            return ErrorCode.MobileExistError;
         }
      }

      return ErrorCode.Success;
   }

   // $FF: synthetic method
   private ErrorCode ALLATORIxDEMO(List a1) {
      Iterator a2 = a1.iterator();

      String var2;
      do {
         if(!a2.hasNext()) {
            return ErrorCode.Success;
         }

         var2 = (String)a2.next();
      } while(!a.E.getRoleIdsByUserId(var2).contains(a.e));

      return ErrorCode.AdminCannotRemove;
   }

   public ErrorCode update(ProfileVO a1) {
      ErrorCode var2;
      if((var2 = a.checkExistMobile(a1.getMobile(), a1.getUserId())).getCode() != ErrorCode.Success.getCode()) {
         return var2;
      } else {
         ProfileDO var3 = new ProfileDO();
         BeanUtils.copyProperties(a1, var3);
         int var4 = a.ALLATORIxDEMO.update(var3);
         if(0 >= var4) {
            LogHelper.error(RoleMenuVO.ALLATORIxDEMO("俬敮甪扠俣怸歧髳夳赲＃"), ErrorCode.FailedToUpdateRecord.getCode());
            return ErrorCode.FailedToUpdateRecord;
         } else {
            if(!StringUtil.isNullOrSpace(a1.getSpaceId())) {
               a.m.removeRoleByUser(a1.getUserId());
            }

            List var5;
            if(!ListUtil.isNullOrEmpty(var5 = a1.getRoleIds())) {
               a.m.removeRoleByUser(a1.getUserId());
               a.ALLATORIxDEMO(var5, a1.getUserId());
            }

            return ErrorCode.Success;
         }
      }
   }

   public ErrorCode updateByAccount(ProfileVO a1) {
      if(null != a1 && !StringUtil.isNullOrSpace(a1.getLoginName())) {
         String var2;
         if(StringUtil.isNullOrSpace(var2 = a.J.queryUserIdByAccount(a1.getLoginName()))) {
            return ErrorCode.IllegalArument;
         } else {
            a1.setUserId(var2);
            return a.update(a1);
         }
      } else {
         return ErrorCode.IllegalArument;
      }
   }

   public UserCacheVO getUserCache(String a1) {
      UserCacheVO var2 = (UserCacheVO)a.g.getUserData(a1);
      if(null != var2) {
         a.g.renewLeaseSession(a1);
         LogHelper.debug(MenusAuthsVO.ALLATORIxDEMO("莒厎甍扯缶嬀俄怷擨伄戵勇Ｉ副斕罋孽ｙ"));
         return var2;
      } else {
         return a.getUserCacheVO(a.queryLoginUser(a1));
      }
   }

   public UserVO getUserInfo(String a1, String a2) throws Exception {
      UserVO var3;
      if((var3 = a.item(a2)) != null) {
         var3.setOpenId(a.ALLATORIxDEMO(a1, a2));
      }

      return var3;
   }

   public Integer queryListByDept(String a1) {
      return a.ALLATORIxDEMO.queryCountByDept(a1);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode removeByIds(List a1) throws Exception {
      ErrorCode var2;
      if((var2 = a.ALLATORIxDEMO(a1)) != ErrorCode.Success) {
         return var2;
      } else {
         Iterator a2;
         Iterator var10000 = a2 = a1.iterator();

         while(var10000.hasNext()) {
            String var4 = (String)a2.next();
            var10000 = a2;
            a.ALLATORIxDEMO.removeById(var4);
            a.m.removeRoleByUser(var4);
            a.J.removeAccountByUserId(var4);
         }

         return ErrorCode.Success;
      }
   }

   // $FF: synthetic method
   private ErrorCode ALLATORIxDEMO(ProfileVO a1) {
      ErrorCode var2;
      if((var2 = a.J.checkExistAccountName(a1.getLoginName())).getCode() == ErrorCode.Success.getCode()) {
         var2 = a.checkExistMobile(a1.getMobile(), (String)null);
      }

      return var2;
   }

   public ErrorCode updateLock(LockVO a1) {
      Iterator var2;
      Iterator var10000 = var2 = a1.getUserIds().iterator();

      while(var10000.hasNext()) {
         String var3 = (String)var2.next();
         ProfileDO var4 = new ProfileDO();
         var10000 = var2;
         var4.setUserId(var3);
         var4.setLocked(a1.getLock());
         a.ALLATORIxDEMO.update(var4);
      }

      return ErrorCode.Success;
   }

   public void init() {
      super.addMapper(a.ALLATORIxDEMO);
   }

   // $FF: synthetic method
   private PageCommonVO ALLATORIxDEMO(SearchCommonVO a1) {
      PageCommonVO var2 = new PageCommonVO();
      PageHelper.orderBy(RoleMenuVO.ALLATORIxDEMO("4p2c#g\bf6v2\"3g$a"));
      PageHelper.startPage(a1.getPageNum().intValue(), a1.getPageSize().intValue());
      List a2 = a.ALLATORIxDEMO.list((ProfileConditionVO)a1.getFilters());
      List var10001 = a.getVOList(a2);
      PageInfo var10004 = new PageInfo;
      var2.<init>(a2);
      var10004.setPageInfo(var2);
      var10001.setPageInfoList(var2);
      return var2;
   }

   public UserVO item(String a1) {
      ProfileVO var2 = new ProfileVO();
      ProfileDO a2 = a.ALLATORIxDEMO.selectById(a1);
      if(null == a2) {
         LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("異戒侹恊乕孽坰"), -1);
         return null;
      } else {
         BeanUtils.copyProperties(a2, var2);
         return a.ALLATORIxDEMO(var2);
      }
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode insert(ProfileVO a1, Ref a2) throws Exception {
      ErrorCode var3;
      if((var3 = a.ALLATORIxDEMO(a1)).getCode() != ErrorCode.Success.getCode()) {
         return var3;
      } else {
         String var7 = (String)a.F.textGuid().getValue();
         ProfileDO var4 = new ProfileDO();
         a1.setUserId(var7);
         a1.setType(Integer.valueOf(0));
         if(StringUtil.isNullOrEmpty(a1.getPassword())) {
            LogHelper.debug(RoleMenuVO.ALLATORIxDEMO("赱戵宑砃乭穸｛佽畿黚诳寄硖＃"));
            a1.setPassword("123456");
         }

         AccountPwdVO var5 = new AccountPwdVO();
         BeanUtils.copyProperties(a1, var5);
         ErrorCode var9 = a.J.addAccountPwd(var5);
         if(ErrorCode.Success != var9) {
            return var9;
         } else {
            BeanUtils.copyProperties(a1, var4);
            var4.setSpaceId(a1.getSpaceId());
            if(null == var4.getLocked()) {
               var4.setLocked(Integer.valueOf(0));
            }

            if(StringUtil.isNullOrSpace(var4.getDeptId())) {
               var4.setDeptId("0");
            }

            int var8 = a.ALLATORIxDEMO.insert(var4);
            if(0 >= var8) {
               LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("涣劅異戒侹恊欽骁彚帝ｙ"), ErrorCode.FailedToInsertRecord.getCode());
               return ErrorCode.FailedToInsertRecord;
            } else {
               List a3;
               if(ListUtil.isNullOrEmpty(a3 = a1.getRoleIds())) {
                  a2.set(var7);
                  LogHelper.debug(RoleMenuVO.ALLATORIxDEMO("甪扠沣杞讼缹觐舥俣怸＃"));
                  return ErrorCode.Success;
               } else {
                  a.ALLATORIxDEMO(a3, var7);
                  a2.set(var7);
                  return ErrorCode.Success;
               }
            }
         }
      }
   }

   public ProfileVO queryLoginUser(String a1) {
      UserVO var3 = a.item(a1);
      if(null == var3) {
         LogHelper.debug((new StringBuilder()).insert(0, a1).append(MenusAuthsVO.ALLATORIxDEMO("甍扯俄怷丨嬀圍ｙ")).toString());
         return null;
      } else {
         List var2 = a.E.authCodesByUserId(a1);
         int var4 = var2.size();
         var3.setAuthIds(var2);
         UserContextManager var10000 = a.g;
         UserCacheVO var10002 = a.getUserCacheVO(var3);
         Integer[] var10004 = new Integer[var4];
         boolean var10006 = true;
         ErrorCode a2 = a1.cacheUser(var10002, var2, (Integer[])var10004.toArray(true), var3.getSpaceId());
         if(ErrorCode.Success != a2) {
            LogHelper.error(RoleMenuVO.ALLATORIxDEMO("罄孚畿戵侶恭欲骦录帺"), ErrorCode.FailedToCacheUserDate.getCode());
            return null;
         } else {
            return var3;
         }
      }
   }

   public PageCommonVO list(SearchCommonVO a1, String a2) {
      a.init();
      String var3 = a.g.getWorkspaceId(a2);
      ((ProfileConditionVO)a1.getFilters()).setSpaceId(var3);
      ((ProfileConditionVO)a1.getFilters()).setMyself(a2);
      ProfileConditionVO a4;
      if(!StringUtil.isNullOrSpace((a4 = (ProfileConditionVO)a1.getFilters()).getName())) {
         a4.setName(a4.getName().trim().replaceAll(MenusAuthsVO.ALLATORIxDEMO("s"), RoleMenuVO.ALLATORIxDEMO("\'")));
         a1.setFilters(a4);
      }

      PageCommonVO a3;
      List a5 = (a3 = a.ALLATORIxDEMO(a1)).getPageInfoList();
      ArrayList var9 = new ArrayList();
      Iterator a6;
      Iterator var10000 = a6 = a5.iterator();

      while(var10000.hasNext()) {
         ProfileVO var4 = (ProfileVO)a6.next();
         UserVO var10 = a.ALLATORIxDEMO(var4);
         var10000 = a6;
         var9.add(var10);
      }

      a3.setPageInfoList(var9);
      return a3;
   }

   public ProfileService() {
      super(ProfileVO.class, ProfileDO.class);
   }

   public ErrorCode queryWorkspaceId(String a1, Ref a2) {
      String var3;
      if(!StringUtil.isNullOrEmpty(var3 = a.g.getWorkspaceId(a1))) {
         a2.set(var3);
         return ErrorCode.Success;
      } else {
         ProfileVO a3 = a.queryLoginUser(a1);
         if(null == a3) {
            LogHelper.error(MenusAuthsVO.ALLATORIxDEMO("異戒侹恊彚帝ｙ"), ErrorCode.NeedLogin.getCode());
            return ErrorCode.NeedLogin;
         } else {
            LogHelper.debug(RoleMenuVO.ALLATORIxDEMO("菠叔畿戵罄孚侶恭奦敊｛曶旧缑嬏戒勈＃"));
            a2.set(a3.getSpaceId());
            return ErrorCode.Success;
         }
      }
   }

   // $FF: synthetic method
   private void ALLATORIxDEMO(List a1, String a2) {
      int var3;
      for(int var10000 = var3 = 0; var10000 < a1.size(); var10000 = var3) {
         byte var4 = 0;
         if(var3 == 0) {
            var4 = 1;
         }

         ScepterService var5 = a.E;
         String var10001 = (String)a1.get(var3);
         ++var3;
         var5.setRoleUser(var10001, a2, var4);
      }

   }

   public PageCommonVO listByWorkspace(SearchCommonVO a1) {
      a.init();
      ProfileVO var2;
      if(!StringUtil.isNullOrSpace((var2 = (ProfileVO)a1.getFilters()).getName())) {
         var2.setName(var2.getName().trim().replaceAll(MenusAuthsVO.ALLATORIxDEMO("s"), RoleMenuVO.ALLATORIxDEMO("\'")));
         a1.setFilters(var2);
      }

      PageCommonVO a2;
      List var6 = (a2 = super.list(a1)).getPageInfoList();
      ArrayList var3 = new ArrayList();
      Iterator var7;
      Iterator var10000 = var7 = var6.iterator();

      while(var10000.hasNext()) {
         ProfileVO var4 = (ProfileVO)var7.next();
         UserVO var8 = a.ALLATORIxDEMO(var4);
         var10000 = var7;
         var3.add(var8);
      }

      a2.setPageInfoList(var3);
      return a2;
   }

   public UserCacheVO getUserCacheVO(ProfileVO a1) {
      if(null == a1) {
         return new UserCacheVO();
      } else {
         UserCacheVO var2;
         UserCacheVO var10001 = var2 = new UserCacheVO();
         var2.setUserId(a1.getUserId());
         var2.setSpaceId(a1.getSpaceId());
         var2.setDeptId(a1.getDeptId());
         var2.setName(a1.getName());
         var10001.setMobile(a1.getMobile());
         if(!StringUtil.isNullOrEmpty(a1.getDeptId()) && !"0".equals(a1.getDeptId())) {
            DepartmentVO a2 = a.L.item(a1.getDeptId());
            if(null == a2) {
               return var2;
            } else {
               var2.setDeptName(a2.getName());
               var2.setDeptCode(a2.getCode());
               return var2;
            }
         } else {
            return var2;
         }
      }
   }

   // $FF: synthetic method
   private UserVO ALLATORIxDEMO(ProfileVO a1) {
      UserVO var2 = new UserVO();
      BeanUtils.copyProperties(a1, var2);
      ArrayList var3 = new ArrayList();
      StringBuilder var4 = new StringBuilder();
      AccountVO var5 = a.J.queryAccountById(a1.getUserId());
      if(null != var5) {
         var2.setLoginName(var5.getLoginName());
         var2.setLastLoginTime(var5.getLastLoginTime());
      }

      if(!StringUtil.isNullOrEmpty(a1.getDeptId())) {
         var2.setDeptName(a.L.queryFullDeptName(a1.getDeptId()));
      }

      List a2;
      Iterator var8;
      Iterator var10000 = var8 = (a2 = a.E.getRolesByUserId(a1.getUserId())).iterator();

      while(var10000.hasNext()) {
         RoleVO var6 = (RoleVO)var8.next();
         if(null == var6) {
            var10000 = var8;
         } else {
            var10000 = var3.add(var6.getId());
            var4.append(var6.getName()).append(MenusAuthsVO.ALLATORIxDEMO("t"));
         }
      }

      if(0 < var4.length()) {
         var4.deleteCharAt(var4.length() - 1);
      }

      var2.setRoleNames(var4.toString());
      var2.setRoleIds(var3);
      var2.setRoleVOs(a2);
      return var2;
   }

   public PageCommonVO listAll(SearchCommonVO a1, String a2) {
      a.init();
      ((ProfileVO)a1.getFilters()).setMyself(a2);
      ProfileVO a4;
      if(!StringUtil.isNullOrSpace((a4 = (ProfileVO)a1.getFilters()).getName())) {
         a4.setName(a4.getName().trim().replaceAll(RoleMenuVO.ALLATORIxDEMO("w)"), MenusAuthsVO.ALLATORIxDEMO("}")));
         a1.setFilters(a4);
      }

      PageCommonVO a3;
      List a5 = (a3 = super.list(a1)).getPageInfoList();
      ArrayList var3 = new ArrayList();
      Iterator a6;
      Iterator var10000 = a6 = a5.iterator();

      while(var10000.hasNext()) {
         ProfileVO var4 = (ProfileVO)a6.next();
         UserVO var9 = a.ALLATORIxDEMO(var4);
         var10000 = a6;
         var3.add(var9);
      }

      a3.setPageInfoList(var3);
      return a3;
   }

   // $FF: synthetic method
   private String ALLATORIxDEMO(String a1, String a2) throws Exception {
      byte[] a3 = CryptoUtil.encryptMD5((new StringBuilder()).insert(0, RoleMenuVO.ALLATORIxDEMO("m6w#j\bc\'r\bk3?")).append(a1).append(MenusAuthsVO.ALLATORIxDEMO("~P+@*z1Ae")).append(a2).toString().getBytes(RoleMenuVO.ALLATORIxDEMO("WDz:")));
      return (new BigInteger(a3)).toString(16);
   }

   @Transactional(
      rollbackFor = {Exception.class}
   )
   public ErrorCode addUserWithAccount(AccountPwdVO a1, Ref a2) throws Exception {
      ProfileVO var3 = new ProfileVO();
      var3.setSpaceId("1");
      var3.setPassword(a1.getPassword());
      var3.setLoginName(a1.getLoginName());
      return a.insert(var3, a2);
   }
}

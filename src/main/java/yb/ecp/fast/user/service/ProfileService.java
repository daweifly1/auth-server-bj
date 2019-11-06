package yb.ecp.fast.user.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yb.ecp.fast.feign.FastGenClient;
import yb.ecp.fast.infra.infra.PageCommonVO;
import yb.ecp.fast.infra.infra.SearchCommonVO;
import yb.ecp.fast.infra.security.CryptoUtil;
import yb.ecp.fast.infra.util.ListUtil;
import yb.ecp.fast.infra.util.Ref;
import yb.ecp.fast.infra.util.StringUtil;
import yb.ecp.fast.user.dao.entity.ProfileDO;
import yb.ecp.fast.user.dao.mapper.ProfileMapper;
import yb.ecp.fast.user.dao.mapper.RoleMapper;
import yb.ecp.fast.user.infra.ErrorCode;
import yb.ecp.fast.user.manager.UserContextManager;
import yb.ecp.fast.user.service.VO.*;
import yb.ecp.fast.user.service.base.BaseService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class ProfileService extends BaseService<ProfileVO, ProfileDO> {

   @Autowired
   ScepterService scepterService;
   @Autowired
   AccountService accountService;
   @Autowired
   private FastGenClient fastGenClient;
   @Autowired
   RoleMapper roleMapper;
   @Autowired
   private UserContextManager userContextManager;
   @Autowired
   WorkspaceService workspaceService;
   @Autowired
   DepartmentService departmentService;
   @Value("${role.admin.roleId}")
   private String adminRoleId;
   @Autowired
   ProfileMapper profileMapper;


   public ErrorCode queryAuthTemplateId(String spaceId, Ref ref1) {
      Ref ref = new Ref("");
      ErrorCode errorCode;
      if ((errorCode = queryWorkspaceId(spaceId, ref)) != ErrorCode.Success) {
         return errorCode;
      } else {
         WorkspaceVO workspaceVO = workspaceService.item((String) ref.get());
         if (null == workspaceVO) {
            return ErrorCode.FailedToRetreiveRecord;
         } else {
            ref1.set(workspaceVO.getTempId());
            return ErrorCode.Success;
         }
      }
   }

   public ErrorCode checkExistMobile(String a1, String a2) {
      if(StringUtils.isNotBlank(a1)) {
         ProfileDO var3 = new ProfileDO();
         var3.setMobile(a1);
         var3.setUserId(a2);
         if (profileMapper.getCountByMobile(var3) > 0) {
            return ErrorCode.MobileExistError;
         }
      }

      return ErrorCode.Success;
   }

   public ErrorCode update(ProfileVO profileVO) {
      ErrorCode errorCode;
      if ((errorCode = checkExistMobile(profileVO.getMobile(), profileVO.getUserId())).getCode() != ErrorCode.Success.getCode()) {
         return errorCode;
      } else {
         ProfileDO profileDO = new ProfileDO();
         BeanUtils.copyProperties(profileVO, profileDO);
         int update = profileMapper.update(profileDO);
         if (0 >= update) {
            return ErrorCode.FailedToUpdateRecord;
         } else {
            if (!StringUtil.isNullOrSpace(profileVO.getSpaceId())) {
               roleMapper.removeRoleByUser(profileVO.getUserId());
            }
            List<String> roleIds = profileVO.getRoleIds();
            if (!ListUtil.isNullOrEmpty(roleIds)) {
               roleMapper.removeRoleByUser(profileVO.getUserId());
               saveUserRolesRelation(roleIds, profileVO.getUserId());
            }
            return ErrorCode.Success;
         }
      }
   }

   public ErrorCode updateByAccount(ProfileVO a1) {
      if(null != a1 && !StringUtil.isNullOrSpace(a1.getLoginName())) {
         String var2;
         if (StringUtil.isNullOrSpace(var2 = accountService.queryUserIdByAccount(a1.getLoginName()))) {
            return ErrorCode.IllegalArument;
         } else {
            a1.setUserId(var2);
            return update(a1);
         }
      } else {
         return ErrorCode.IllegalArument;
      }
   }

   public UserCacheVO getUserCache(String a1) {
      UserCacheVO var2 = (UserCacheVO) userContextManager.getUserData(a1);
      if(null != var2) {
         userContextManager.renewLeaseSession(a1);
         return var2;
      } else {
         return getUserCacheVO(queryLoginUser(a1));
      }
   }

   public UserVO getUserInfo(String appId, String userId) throws Exception {
      UserVO userVO;
      if ((userVO = item(userId)) != null) {
         //TODO ??
         userVO.setOpenId(cryptoPassword(appId, userId));
      }

      return userVO;
   }

   public Integer queryListByDept(String deptId) {
      return profileMapper.queryCountByDept(deptId);
   }


   private ErrorCode checkContainAdminRole(List<String> userIds) {
      Iterator iterator = userIds.iterator();
      String userId;
      do {
         if (!iterator.hasNext()) {
            return ErrorCode.Success;
         }

         userId = (String) iterator.next();
      } while (!scepterService.getRoleIdsByUserId(userId).contains(adminRoleId));

      return ErrorCode.AdminCannotRemove;
   }


   @Transactional(
           rollbackFor = {Exception.class}
   )
   public ErrorCode removeByIds(List<String> userIds) throws Exception {
      ErrorCode var2 = checkContainAdminRole(userIds);
      if (var2 != ErrorCode.Success) {
         return var2;
      } else {
         Iterator iterator = userIds.iterator();
         while (iterator.hasNext()) {
            String var4 = (String) iterator.next();
            profileMapper.removeById(var4);
            roleMapper.removeRoleByUser(var4);
            accountService.removeAccountByUserId(var4);
         }
         return ErrorCode.Success;
      }
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
         profileMapper.update(var4);
      }

      return ErrorCode.Success;
   }


   public UserVO item(String id) {
      ProfileVO profileVO = new ProfileVO();
      ProfileDO a2 = profileMapper.selectById(id);
      if(null == a2) {
         return null;
      } else {
         BeanUtils.copyProperties(a2, profileVO);
         return getUserVoByProfileVo(profileVO);
      }
   }

   @Transactional(
           rollbackFor = {Exception.class}
   )
   public ErrorCode insert(ProfileVO profileVO, Ref ref) throws Exception {
      ErrorCode var3 = checkAccountName(profileVO);
      if ((var3).getCode() != ErrorCode.Success.getCode()) {
         return var3;
      } else {
         String duid = (String) fastGenClient.textGuid().getValue();
         ProfileDO var4 = new ProfileDO();
         profileVO.setUserId(duid);
         profileVO.setType(Integer.valueOf(0));
         if (StringUtil.isNullOrEmpty(profileVO.getPassword())) {
            profileVO.setPassword("123456");
         }

         AccountPwdVO var5 = new AccountPwdVO();
         BeanUtils.copyProperties(profileVO, var5);
         ErrorCode var9 = accountService.addAccountPwd(var5);
         if(ErrorCode.Success != var9) {
            return var9;
         } else {
            BeanUtils.copyProperties(profileVO, var4);
            var4.setSpaceId(profileVO.getSpaceId());
            if(null == var4.getLocked()) {
               var4.setLocked(Integer.valueOf(0));
            }

            if(StringUtil.isNullOrSpace(var4.getDeptId())) {
               var4.setDeptId("0");
            }

            int var8 = profileMapper.insert(var4);
            if(0 >= var8) {
               return ErrorCode.FailedToInsertRecord;
            } else {
               List<String> roleIds = profileVO.getRoleIds();
               if (ListUtil.isNullOrEmpty(roleIds)) {
                  ref.set(duid);
                  return ErrorCode.Success;
               } else {
                  saveUserRolesRelation(roleIds, duid);
                  ref.set(duid);
                  return ErrorCode.Success;
               }
            }
         }
      }
   }

   public ProfileVO queryLoginUser(String userId) {
      UserVO var3 = item(userId);
      if(null == var3) {
         return null;
      } else {
         List<Integer> codes = scepterService.authCodesByUserId(userId);
         var3.setAuthIds(codes);
         UserCacheVO var10002 = getUserCacheVO(var3);
         Integer[] codeArr = new Integer[codes.size()];
         codes.toArray(codeArr);
         ErrorCode a2 = userContextManager.cacheUser(userId, var10002, codeArr, var3.getSpaceId());
         if(ErrorCode.Success != a2) {
            return null;
         } else {
            return var3;
         }
      }
   }


   public PageCommonVO<UserVO> list(SearchCommonVO<ProfileConditionVO> condtion, String userId) {
      String spaceId = this.userContextManager.getWorkspaceId(userId);

      ((ProfileConditionVO) condtion.getFilters()).setSpaceId(spaceId);
      ((ProfileConditionVO) condtion.getFilters()).setMyself(userId);

      ProfileConditionVO temp = (ProfileConditionVO) condtion.getFilters();
      if (StringUtils.isNoneBlank(new CharSequence[]{temp.getName()})) {
         temp.setName(temp.getName().trim().replaceAll(" +", "%"));
         condtion.setFilters(temp);
      }
      PageCommonVO pageCommonVO = pageList(condtion);
      List<ProfileVO> profileVOList = pageCommonVO.getPageInfoList();
      List<UserVO> userVOs = new ArrayList();
      for (ProfileVO pv : profileVOList) {
         userVOs.add(getUserVoByProfileVo(pv));
      }
      pageCommonVO.setPageInfoList(userVOs);
      return pageCommonVO;
   }


//批量方法带实现
//   private void batchPackageUserInfo(List<ProfileVO> profileVOList, List<UserVO> userVOs) {
//      if (CollectionUtils.isEmpty(profileVOList)) {
//         return;
//      }
//      Set<String> userSet = new HashSet();
//      Set<String> deptSet = new HashSet();
//      for (ProfileVO profileVO : profileVOList) {
//         userSet.add(profileVO.getUserId());
//         deptSet.add(profileVO.getDeptId());
//      }
//      if (CollectionUtils.isEmpty(userSet)) {
//         return;
//      }
//      Object accountVOMap = this.accountService.queryAccountsByUserIds(userSet);
//
//      Map<String, DepartmentDO> userDeptMap = this.departmentService.queryUserDeptMapByIds(deptSet);
//
//      Map<String, List<SysRoleDO>> userRolesMap = this.sysUserRolesService.queryUserRolesByUserIds(userSet);
//      for (ProfileVO profileVO : profileVOList) {
//         UserVO userVO = new UserVO();
//         BeanUtils.copyProperties(profileVO, userVO);
//         AccountDO accountVO = (AccountDO) ((Map) accountVOMap).get(profileVO.getUserId());
//         if (null != accountVO) {
//            userVO.setLoginName(accountVO.getLoginName());
//            userVO.setLastLoginTime(accountVO.getLastLoginTime());
//         }
//         DepartmentDO departmentDO = (DepartmentDO) userDeptMap.get(profileVO.getDeptId());
//         if (null != departmentDO) {
//            userVO.setDeptName(departmentDO.getName());
//         }
//         List<SysRoleDO> roleVOs = (List) userRolesMap.get(profileVO.getUserId());
//         if (CollectionUtils.isNotEmpty(roleVOs)) {
//            List<String> roleIds = new ArrayList();
//            List<String> roleName = new ArrayList();
//            for (SysRoleDO roleVO : roleVOs) {
//               if (null != roleVO) {
//                  roleIds.add(roleVO.getId());
//                  roleName.add(roleVO.getName());
//               }
//            }
//            userVO.setRoleNames(StringUtils.join(new List[]{roleName}));
//            userVO.setRoleIds(roleIds);
//            userVO.setRoleVOs(BeanUtil.do2bo4List(roleVOs, SysRoleVO.class));
//         }
//         userVOs.add(userVO);
//      }
//   }

   private PageCommonVO<ProfileVO> pageList(SearchCommonVO<ProfileConditionVO> condition) {
      PageCommonVO pageCommonVO = new PageCommonVO();
      if (StringUtils.isEmpty(condition.getSort())) {
         PageHelper.orderBy("create_date");
      } else {
         PageHelper.orderBy(condition.getSort());
      }
      PageHelper.startPage(condition.getPageNum().intValue(), condition.getPageSize().intValue());
      List<ProfileDO> doList = this.profileMapper.list((ProfileConditionVO) condition.getFilters());
      List<ProfileVO> voList = getVOList(doList);
      pageCommonVO.setPageInfo(new PageInfo(doList));
      pageCommonVO.setPageInfoList(voList);
      return pageCommonVO;
   }

//   public PageCommonVO list(SearchCommonVO searchCommonVO, String userId) {
//      String workspaceId = userContextManager.getWorkspaceId(userId);
//      ((ProfileConditionVO) searchCommonVO.getFilters()).setSpaceId(workspaceId);
//      ((ProfileConditionVO) searchCommonVO.getFilters()).setMyself(userId);
//      ProfileConditionVO profileConditionVO;
//      if (!StringUtil.isNullOrSpace((profileConditionVO = (ProfileConditionVO) searchCommonVO.getFilters()).getName())) {
//         profileConditionVO.setName(profileConditionVO.getName().trim());
//         searchCommonVO.setFilters(profileConditionVO);
//      }
//      PageCommonVO pageCommonVO;
//      List a5 = (pageCommonVO = ALLATORIxDEMO(searchCommonVO)).getPageInfoList();
//      ArrayList var9 = new ArrayList();
//      Iterator a6;
//      Iterator var10000 = a6 = a5.iterator();
//
//      while(var10000.hasNext()) {
//         ProfileVO var4 = (ProfileVO)a6.next();
//         UserVO var10 = ALLATORIxDEMO(var4);
//         var10000 = a6;
//         var9.add(var10);
//      }
//
//      pageCommonVO.setPageInfoList(var9);
//      return pageCommonVO;
//   }

   public ProfileService() {
      super(ProfileVO.class, ProfileDO.class);
   }

   public ErrorCode queryWorkspaceId(String userId, Ref ref) {
      String var3 = userContextManager.getWorkspaceId(userId);
      if (!StringUtil.isNullOrEmpty(var3)) {
         ref.set(var3);
         return ErrorCode.Success;
      } else {
         ProfileVO a3 = queryLoginUser(userId);
         if(null == a3) {
            return ErrorCode.NeedLogin;
         } else {
            ref.set(a3.getSpaceId());
            return ErrorCode.Success;
         }
      }
   }


   public PageCommonVO listByWorkspace(SearchCommonVO searchCommonVO) {
      ProfileVO profileVO;
      if (!StringUtil.isNullOrSpace((profileVO = (ProfileVO) searchCommonVO.getFilters()).getName())) {
         profileVO.setName(profileVO.getName().trim());
         searchCommonVO.setFilters(profileVO);
      }

      PageCommonVO<ProfileVO> pageCommonVO = super.list(searchCommonVO);
      List var6 = pageCommonVO.getPageInfoList();
      ArrayList var3 = new ArrayList();
      Iterator iterator = var6.iterator();

      while (iterator.hasNext()) {
         ProfileVO profileVO1 = (ProfileVO) iterator.next();
         UserVO userVO = getUserVoByProfileVo(profileVO1);
         var3.add(userVO);
      }
      pageCommonVO.setPageInfoList(var3);
      return pageCommonVO;
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
            DepartmentVO a2 = departmentService.item(a1.getDeptId());
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


   public PageCommonVO listAll(SearchCommonVO a1, String a2) {
      ((ProfileVO)a1.getFilters()).setMyself(a2);
      ProfileVO a4;
      if(!StringUtil.isNullOrSpace((a4 = (ProfileVO)a1.getFilters()).getName())) {
         a4.setName(a4.getName().trim());
         a1.setFilters(a4);
      }

      PageCommonVO a3;
      List<ProfileVO> profileVOList = (a3 = super.list(a1)).getPageInfoList();
      ArrayList var3 = new ArrayList();
      Iterator iterator = profileVOList.iterator();

      while (iterator.hasNext()) {
         ProfileVO profileVO = (ProfileVO) iterator.next();
         UserVO userVO = getUserVoByProfileVo(profileVO);
         var3.add(userVO);
      }
      a3.setPageInfoList(var3);
      return a3;
   }


   @Transactional(
           rollbackFor = {Exception.class}
   )
   public ErrorCode addUserWithAccount(AccountPwdVO a1, Ref a2) throws Exception {
      ProfileVO var3 = new ProfileVO();
      var3.setSpaceId("1");
      var3.setPassword(a1.getPassword());
      var3.setLoginName(a1.getLoginName());
      return insert(var3, a2);
   }


   // $FF: synthetic method
   private ErrorCode checkAccountName(ProfileVO profileVO) {
      ErrorCode var2 = accountService.checkExistAccountName(profileVO.getLoginName());
      if (var2.getCode() == ErrorCode.Success.getCode()) {
         var2 = checkExistMobile(profileVO.getMobile(), (String) null);
      }
      return var2;
   }

   // $FF: synthetic method
   private void saveUserRolesRelation(List<String> roleIds, String userId) {
      for (int i = 0; i < roleIds.size(); i++) {
         byte flag = 0;
         if (i == 0) {
            flag = 1;
         }
         String roleId = (String) roleIds.get(i);
         scepterService.setRoleUser(roleId, userId, flag);
      }
   }

   private String cryptoPassword(String text, String salt) {
      String orginalText = text + "_" + salt;
      byte[] cypherBytes = new byte[0];
      try {
         cypherBytes = CryptoUtil.encryptMD5(orginalText.getBytes());
         String cypherText = new BigInteger(cypherBytes).toString(16);
         return cypherText;
      } catch (Exception e) {
         log.error("登录密码加密错误", e);
         return "";
      }
   }


   // $FF: synthetic method
   private UserVO getUserVoByProfileVo(ProfileVO profileVO) {
      UserVO var2 = new UserVO();
      BeanUtils.copyProperties(profileVO, var2);
      ArrayList var3 = new ArrayList();
      StringBuilder stringBuilder = new StringBuilder();
      AccountVO accountVO = accountService.queryAccountById(profileVO.getUserId());
      if (null != accountVO) {
         var2.setLoginName(accountVO.getLoginName());
         var2.setLastLoginTime(accountVO.getLastLoginTime());
      }

      if (!StringUtil.isNullOrEmpty(profileVO.getDeptId())) {
         var2.setDeptName(departmentService.queryFullDeptName(profileVO.getDeptId()));
      }
      List<RoleVO> a2 = scepterService.getRolesByUserId(profileVO.getUserId());
      Iterator iterator = a2.iterator();

      while (iterator.hasNext()) {
         RoleVO var6 = (RoleVO) iterator.next();
         if (null != var6) {
            var3.add(var6.getId());
            stringBuilder.append(var6.getName()).append("-");
         }
      }

      if (0 < stringBuilder.length()) {
         stringBuilder.deleteCharAt(stringBuilder.length() - 1);
      }

      var2.setRoleNames(stringBuilder.toString());
      var2.setRoleIds(var3);
      var2.setRoleVOs(a2);
      return var2;
   }
}
